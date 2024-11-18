import Collection.CollectionManager;
import Console.CommandManager;
import Data.Movie;
import FileSystem.Parser;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        try{
            //System.setProperty("FILE_PATH", "/Users/alyssanabokova/Documents/2сем/лабы/прога/Lab5/Lab5/src/main/java/Parsing/MovieCollection.csv");
            String filePath = System.getenv("FILE_PATH");
            System.out.println(filePath);
            Parser parser = new Parser(filePath);
            CollectionManager collectionManager = new CollectionManager(parser);
            HashSet<Movie> movies = parser.loadFromCSV();
            collectionManager.setCollection(movies);
            CommandManager commandManager = new CommandManager(collectionManager);
            while (commandManager.getWork()) {
                commandManager.existCommand();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

}