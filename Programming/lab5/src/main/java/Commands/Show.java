package Commands;

import Collection.CollectionManager;
/**
 * Класс команды show
 */
public class Show implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды показывающий все элементы коллекции
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 1){
            collectionManager.addToHistory(args[0]);
            collectionManager.show();
        }else throw new IllegalArgumentException("Incorrect number of arguments. ");

    }

    @Override
    public String getName() {
        return "show ";
    }

    @Override
    public String getDescription() {
        return " output all the elements of the collection in a string representation to the standard output stream";
    }
}
