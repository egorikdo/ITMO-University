package Commands;

import Collection.CollectionManager;
import Data.Movie;

/**
 * Класс команды add_if_max
 */
public class AddIfMax implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public AddIfMax(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды добавления элемента в коллекцию, если поле количества оскаров наибольшее
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 1){
            collectionManager.addToHistory(args[0]);
            try{
                Movie movie = collectionManager.getClientManager().getMovie();
                collectionManager.addIfMax(movie);
            }catch (IllegalArgumentException e){
                System.out.println("Incorrect arguments have been entered. Try again.");
            }
        }else throw new IllegalArgumentException("Incorrect number of arguments.");

    }

    @Override
    public String getName() {
        return "add_if_max {element} ";
    }

    @Override
    public String getDescription() {
        return " add a new element to the collection if its value exceeds the value of the largest element in this collection";
    }
}
