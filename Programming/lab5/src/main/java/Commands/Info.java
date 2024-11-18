package Commands;

import Collection.CollectionManager;
/**
 * Класс команды history
 */
public class Info implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды показывающей тип коллекции, количество элементов в коллекции и дату инициализации коллекции
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 1) {
            collectionManager.addToHistory(args[0]);
            collectionManager.info();
        }else throw new IllegalArgumentException("Incorrect number of arguments. ");
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "output information about the collection (type, initialization date, number of items, etc.) to the standard output stream";
    }
}
