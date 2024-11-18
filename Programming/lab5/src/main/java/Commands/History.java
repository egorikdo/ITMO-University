package Commands;

import Collection.CollectionManager;
/**
 * Класс команды history
 */
public class History implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public History(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды показывающей предыдущие 7 команд без их аргументов
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 1) {
            collectionManager.addToHistory(args[0]);
            collectionManager.history();
        }else throw new IllegalArgumentException("Incorrect number of arguments. ");

    }

    @Override
    public String getName() {
        return "history ";
    }

    @Override
    public String getDescription() {
        return " output help for available commands";
    }
}
