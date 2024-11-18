package Commands;

import Collection.CollectionManager;
/**
 * Класс команды help
 */
public class Help implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public Help(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды показывающей все возможные команды с их описанием
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 1){
            collectionManager.addToHistory(args[0]);
            collectionManager.help();
        }else throw new IllegalArgumentException("Incorrect number of arguments. ");

    }

    @Override
    public String getName() {
        return "help ";
    }

    @Override
    public String getDescription() {
        return " output help for available commands";
    }
}
