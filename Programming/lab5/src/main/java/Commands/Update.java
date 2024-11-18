package Commands;

import Collection.CollectionManager;
import Data.Movie;
/**
 * Класс команды update
 */
public class Update implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public Update(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды обновляющий значения фильма по id
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 2){
            collectionManager.addToHistory(args[0]);
            try{
                Integer id = Integer.parseInt(args[1]);
                boolean flag = false;
                for (Movie movie: collectionManager.getMovies()){
                    if (movie.getId() == id){
                        flag = true;
                        break;
                    }
                }
                if (flag){
                    Movie movie = collectionManager.getClientManager().getMovie();
                    collectionManager.updateId(movie, id);
                }else{
                    System.out.println("The element with this id is not in the collection.");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Incorrect arguments have been entered. Try again.");
            }

        }else throw new IllegalArgumentException("The number of arguments is incorrect. ");
    }

    @Override
    public String getName() {
        return "update id {element} ";
    }

    @Override
    public String getDescription() {
        return " update the value of a collection item whose id is equal to the specified one";
    }
}
