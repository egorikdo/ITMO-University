package Commands;

import Collection.CollectionManager;
/**
 * Класс команды clear
 */
public class Clear implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения очистки коллекции
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args) {
        collectionManager.addToHistory(args[0]);
        if (args.length == 1){
            collectionManager.clear();
            System.out.println("The collection has been cleared.");
        }else throw new IllegalArgumentException("Incorrect number of arguments.");
    }

    @Override
    public String getName() {
        return "clear ";
    }

    @Override
    public String getDescription() {
        return " clear collection";
    }
}
