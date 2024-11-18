package Commands;

import Collection.CollectionManager;
/**
 * Класс команды print_unique_genre
 */
public class PrintUniqueGenre implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public PrintUniqueGenre(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды показывающей уникальные жанры в коллекции
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 1){
            collectionManager.addToHistory(args[0]);
            collectionManager.printUniqueGenre();
        }else throw new IllegalArgumentException("Incorrect number of arguments.");
    }

    @Override
    public String getName() {
        return "print_unique_genre ";
    }

    @Override
    public String getDescription() {
        return " print the unique values of the genre field of all elements in the collection";
    }
}
