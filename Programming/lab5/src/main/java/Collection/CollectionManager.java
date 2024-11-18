package Collection;

import Console.CommandManager;
import Data.Movie;
import Console.ClientManager;
import Data.MovieGenre;
import FileSystem.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Данный класс отвечает за взаимодействие с коллекцией и содержит коллекцию команд
 * */

public class CollectionManager {
    /**
     * Главная коллекция фильмов
     */
    HashSet<Movie> movies = new HashSet<>();
    /**
     * Дата инициализации коллекции
     */
    private LocalDate localDate = LocalDate.now();
    /**
     * Класс, возвращающий фильм
     */
    private ClientManager clientManager = new ClientManager();
    /**
     * Парсер для работы с файлом
     * используется в команде save
     */
    private Parser parser;
    /**
     * Коллекция для хранения команд
     * используется в команде history
     */
    private LinkedList<String> commandHistory = new LinkedList<>();

    /**
     * Конструктор класса CollectionManager
     * @param parser    Парсер для работы с файлом
     */
    public CollectionManager(Parser parser) {
        this.parser = parser;
    }
    public void setCollection(HashSet<Movie> movies){
        this.movies = movies;
    }
    public ClientManager getClientManager(){
        return this.clientManager;
    }
    public  HashSet<Movie> getMovies(){
        return movies;
    }

    /**
     * Команда info
     * выводит в стандартный поток вывода информацию о коллекции:
     * тип, дату инициализации, количество элементов
     */
    public void info() {
        String info = "Type of collection: " + movies.getClass().getSimpleName() + "\nInitialization date: " + localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\nNumber of elements: " + movies.size();
        System.out.println(info);
    }

    /**
     * Команда show
     * выводит в стандартный поток вывода все элементы коллекции в строковом представлении
     */
    public void show() {
        if (!movies.isEmpty()) {
            for (Movie movie : movies) {
                System.out.println(movie.toString());
            }
        } else {
            System.out.println("Collection does not contain elements.");
        }
    }

    /**
     * Команда help
     * выводит справку по доступным командам
     */
    public void help() {
        CommandManager.getCommandList().values().forEach(command -> System.out.println(command.getName() + "-" + command.getDescription()));
    }

    /**
     * Команда add
     * добавляет новый элемент в коллекцию
     * @param movie
     */
    public void add(Movie movie) {
        movies.add(movie);
    }

    /**
     * Команда clear
     * очищает коллекцию
     */
    public void clear(){
        movies.clear();
    }

    /**
     * Команда exit
     * завершает программу (без сохранения в файл)
     */
    public void exit(){
        System.out.println("The application is completed.");
        System.exit(0);
    }

    /**
     * Команда update
     * обновляет значение элемента коллекции, id которого равен заданному
     * @param newMovie
     * @param id
     */
    public void updateId(Movie newMovie, int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movie.setName(newMovie.getName());
                movie.setCoordinates(newMovie.getCoordinates());
                movie.setOscarsCount(newMovie.getOscarsCount());
                movie.setBudget(newMovie.getBudget());
                movie.setMovieGenre(newMovie.getMovieGenre());
                movie.setMpaaRating(newMovie.getMpaaRating());
                movie.setOperator(newMovie.getOperator());
                System.out.println("The element has been successfully updated.");
                break;
            }
        }

    }

    /**
     * Команда remove_by_id
     * удаляет элемент из коллекции по его id
     * @param id
     */
    public void removeById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movies.remove(movie);
                System.out.println("The element has been successfully deleted.");
                break;
            }
        }
    }

    /**
     * Команда add_if_max
     * добавить новый элемент в коллекцию, если его количество оскаров превышает значение наибольшего элемента этой коллекции
     * @param newMovie
     */
    public void addIfMax(Movie newMovie) {
        int maxOscarsCount = 0;
        for (Movie movie : movies) {
            if (movie.getOscarsCount() > maxOscarsCount) {
                maxOscarsCount = movie.getOscarsCount();
            }
        }
        if (newMovie.getOscarsCount() > maxOscarsCount){
            add(newMovie);
            System.out.println("The element was successfully added to the collection.");
        } else {
            System.out.println("The element is not the largest.");
        }
    }

    /**
     * Команда add_if_min
     * добавит новый элемент в коллекцию, если его количество оскаров меньше, чем у наименьшего элемента этой коллекции
     * @param newMovie
     */
    public void addIfMin(Movie newMovie) {
        int minOscarsCount = 999999;
        for (Movie movie : movies) {
            if (movie.getOscarsCount() < minOscarsCount) {
                minOscarsCount = movie.getOscarsCount();
            }
        }
        if (newMovie.getOscarsCount() < minOscarsCount) {
            add(newMovie);
            System.out.println("The element was successfully added to the collection.");
        } else {
            System.out.println("The element is not the smallest.");
            }
        }

    /**
     * Команда sum_of_oscars_count
     * вывести сумму значений поля oscarsCount для всех элементов коллекции
     */
    public void sumOfOscarsCount(){
        int sumOfOscarsCount = 0;
        for (Movie movie: movies){
            sumOfOscarsCount += movie.getOscarsCount();
        }
        System.out.println("The sum of the field values oscarsCount: " + sumOfOscarsCount);
    }

    /**
     * Команда print_unique_genre
     * выводит уникальные значения поля genre всех элементов в коллекции
     */
    public void printUniqueGenre(){
        HashSet<MovieGenre> uniqueGenres = new HashSet<>();
        for (Movie movie: movies){
            uniqueGenres.add(movie.getMovieGenre());
        }
        System.out.println("Unique genres in the collection:\n" + uniqueGenres);
    }

    /**
     * Команда group_by_counting
     * сгруппирует элементы коллекции по значению поля name, выведет количество элементов в каждой группе
     */
    public void groupCountingByName() {
        Map<String, Integer> nameCountMap = new HashMap<>();
        for (Movie movie : movies) {
            String name = movie.getName();
            nameCountMap.put(name, nameCountMap.getOrDefault(name, 0) + 1);
        }
        System.out.println("Grouped counting by name:");
        nameCountMap.forEach((name, count) ->
                System.out.println(name + ": " + count)
        );
    }

    /**
     * Проверяет количество введённыз команд и добавляет их в коллекцию команд
     * @param command
     */
    public void addToHistory(String command) {
        commandHistory.add(command);
        if (commandHistory.size() > 7) {
            commandHistory.removeFirst();
        }
    }

    /**
     * Команда history
     * выводит последние 7 команд (без их аргументов)
     */
    public void history() {
        System.out.println("History of commands:");
        for (String command : commandHistory) {
            System.out.println(command);
        }
    }

    /**
     * Команда save
     * сохраняет коллекцию в файл
     */
    public void save(){
        try{
            parser.saveToCSV(this.movies);
        }catch (Exception e){
            System.out.println("Something went wrong.");
        }

    }

}
