package Commands;
import Collection.CollectionManager;
import Collection.Validator;
import Console.CommandManager;
import Data.*;
import FileSystem.FileReaderManager;
import FileSystem.FileStack;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Класс команды execute_script
 */
public class ExecuteScript implements Command{
    private final CollectionManager collectionManager;
    private final HashMap<String, Command> commands;
    /**
     * Конструктор класса ExecuteScript
     * @param collectionManager
     */
    public ExecuteScript(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.commands = CommandManager.getCommandList();
    }
    Validator validator = new Validator();
    /**
     * Метод для выполнения скрипта из файла
     * @param args массив аргументов команды
     * @throws Exception
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 2) {
            collectionManager.addToHistory(args[0]);
            String filename = args[1];
            if (!FileStack.getFileStack().contains(filename)) {
                FileStack.addFile(filename);
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filename));
                    FileReaderManager fileReaderManager = new FileReaderManager(reader);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        try {
                            String[] commandAndArg = readCommandAndArgument(line);
                            String command = commandAndArg[0];
                            if (commands.containsKey(command)) {
                                if ((command.contains("add") || command.contains("update") || command.contains("add_if_max") || command.contains("add_if_min"))) {
                                    String name = fileReaderManager.readName();
                                    float x = fileReaderManager.readCoordinateX();
                                    float y = fileReaderManager.readCoordinateY();
                                    int oscarsCount = fileReaderManager.readOscarsCount();
                                    long budget = fileReaderManager.readBudget();
                                    MovieGenre genre = fileReaderManager.readGenre();
                                    MpaaRating mpaaRating = fileReaderManager.readMpaaRating();
                                    String operatorsName = fileReaderManager.readOperatorsName();
                                    int weight = fileReaderManager.readOperatorsWeight();
                                    Color eyeColor = fileReaderManager.readEyeColor();
                                    Movie movie = validator.getValidated(new Movie(null, name, new Coordinates(x, y), null, oscarsCount, budget, genre, mpaaRating, new Person(operatorsName, weight, eyeColor)));
                                    if (movie != null) {
                                        switch (command) {
                                            case "add" -> {
                                                collectionManager.add(movie);
                                                System.out.println("The element has been successfully added to the collection.");
                                            }
                                            case "update" -> {
                                                collectionManager.updateId(movie, Integer.parseInt(commandAndArg[1]));
                                                System.out.println("The element has been updated.");
                                            }
                                            case "add_if_max" -> collectionManager.addIfMax(movie);
                                            case "add_if_min" -> collectionManager.addIfMin(movie);
                                        }
                                    }
                                } else {
                                    commands.get(command).execute(commandAndArg);
                                }

                            }
                        } catch (IllegalArgumentException | NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                }finally{
                    FileStack.removeFile();
                }

            }
        }
    }
    /**
     * Метод для проверки количества аргументов у команды (если в строке содержится команда)
     * @param commandString строка с командой и аргументом
     * @return массив из команды и аргумента
     * @throws IllegalArgumentException
     */
    public String[] readCommandAndArgument(String commandString) throws IllegalArgumentException {
        String[] commandAndArgument = commandString.trim().toLowerCase().split(" ");
        String command = commandAndArgument[0].trim();
        if (CommandManager.getCommandList().containsKey(command)) {
            if (command.equals("execute_script") || command.equals("remove_by_id") || command.equals("update")) {
                if (commandAndArgument.length == 2) {
                    return new String[]{command, commandAndArgument[1].trim()};
                } else throw new IllegalArgumentException("Illegal count of arguments: " + command);
            } else {
                if (commandAndArgument.length == 1) {
                    return new String[]{command};
                } else throw new IllegalArgumentException("Illegal count of arguments: " + command);
            }
        } else throw new IllegalArgumentException("Unknown command: " + command);
    }

    @Override
    public String getName() {
        return "execute_script file_name ";
    }

    @Override
    public String getDescription() {
        return " read and execute the script from the specified file. The script contains commands in the same form as they are entered by the user interactively";
    }
}
