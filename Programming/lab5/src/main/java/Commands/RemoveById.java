package Commands;

import Collection.CollectionManager;
import Data.Movie;
/**
 * Класс команды remove_by_id
 */
public class RemoveById implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды удаляющей фильм из коллекции оп его id
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
                    collectionManager.removeById(id);
                }else{
                    System.out.println("The element with this id is not in the collection.");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Incorrect arguments have been entered. Try again.");
            }

        }else throw new IllegalArgumentException("The number of arguments is incorrect.. ");
    }

    @Override
    public String getName() {
        return "remove_by_id id ";
    }

    @Override
    public String getDescription() {
        return " delete an item from the collection by its id";
    }
}
