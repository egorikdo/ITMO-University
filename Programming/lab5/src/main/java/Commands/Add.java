package Commands;

import Collection.CollectionManager;
import Data.Movie;

/**
 * Класс команды add
 */
public class Add implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager менеджер коллекции, к которому будет производиться добавление элемента
     */
    public Add(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды добавления элемента в коллекцию
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args){
        if (args.length == 1){
            collectionManager.addToHistory(args[0]);
            try{
                Movie movie = collectionManager.getClientManager().getMovie();
                boolean flag = true;
                for (Movie movie1: collectionManager.getMovies()){
                    if (movie1.equals(movie)){
                        flag = false;
                    }
                }
                if (flag) {
                    collectionManager.add(movie);
                    System.out.println("The movie has been successfully added to the collection.");
                }else{
                    System.out.println("The element is already in collection.");
                }

            }catch (IllegalArgumentException e){
                System.out.println("Incorrect arguments have been entered. Try again.");
            }
        }else throw new IllegalArgumentException("Incorrect number of arguments.");
    }

    @Override
    public String getName() {
        return "add {element} ";
    }

    @Override
    public String getDescription() {
        return " use this command to add an element";
    }
}