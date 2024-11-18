package Console;

import Collection.CollectionManager;
import Commands.*;

import java.util.HashMap;
import java.util.Scanner;
/**
 * Данный класс обеспечивает связь между командами и CollectionManager
 * */
public class CommandManager {
    private static HashMap<String, Command> commandList = new HashMap<>();
    private boolean isWorking = true;
    private String filename;
    /**
     * Конструктор класса CommandManager
     * Инициализирует список доступных команд и их соответствующие объекты
     * @param collectionManager менеджер коллекции объектов
     */
    public CommandManager(CollectionManager collectionManager) {
        commandList.put("add", new Add(collectionManager));
        commandList.put("clear", new Clear(collectionManager));
        commandList.put("exit", new Exit(collectionManager));
        commandList.put("help", new Help(collectionManager));
        commandList.put("info", new Info(collectionManager));
        commandList.put("show", new Show(collectionManager));
        commandList.put("update", new Update(collectionManager));
        commandList.put("remove_by_id", new RemoveById(collectionManager));
        commandList.put("add_if_max", new AddIfMax(collectionManager));
        commandList.put("add_if_min", new AddIfMin(collectionManager));
        commandList.put("sum_of_oscars_count", new SumOfOscarsCount(collectionManager));
        commandList.put("print_unique_genre", new PrintUniqueGenre(collectionManager));
        commandList.put("group_counting_by_name", new GroupCountingByName(collectionManager));
        commandList.put("history", new History(collectionManager));
        commandList.put("save", new Save(collectionManager));
        commandList.put("execute_script", new ExecuteScript(collectionManager));

    }
    /**
     * Метод для установки имени файла.
     * @param filename
     */
    public void setFilename(String filename){
        this.filename = filename;
    }
    /**
     * Метод для получения списка доступных команд.
     * @return commandList список доступных команд
     */
    public static HashMap<String, Command> getCommandList() {
        return commandList;
    }
    /**
     * Метод для получения статуса работы менеджера команд.
     * @return isWorking
     */
    public boolean getWork() {
        return this.isWorking;
    }
    /**
     * Метод для выполнения команды
     * Выполняет команду, введенную пользователем
     */
    public void existCommand(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("I'm listen u, my heart: ");
            String command = scanner.nextLine().trim().toLowerCase();
            String[] args = command.split(" ");
            if (commandList.containsKey(args[0])){
                try{
                    commandList.get(args[0]).execute(args);
                }catch (IllegalArgumentException e){
                    System.out.println("Something went wrong. " + e.getMessage() + "Try again.");
                }
            }else{
                System.out.println("Command \"" + args[0] + "\" not found.");
            }
        }catch (Exception e){
            System.out.println("Something went wrong. " + e.getMessage() + ". See you next time.");
            this.isWorking = false;
            System.exit(0);
        }
    }
    }



