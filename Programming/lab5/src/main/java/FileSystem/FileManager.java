package FileSystem;

import Data.Movie;

import java.util.HashSet;
/**
 * Базовый класс для управления файлами
 */
public abstract class FileManager {
    /**
     * Имя файла
     */
    private String fileName;
    /**
     * Конструктор класса FileManager
     * @param fileName
     */
    public FileManager(String fileName){
        this.fileName = fileName;
    }
    /**
     * Получает имени файла
     * @return имя файла
     */
    public String getFileName(){
        return fileName;
    }
    /**
     * Устанавливает имя файла
     * @param fileName
     */
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    /**
     * Абстрактный метод для сохранения данных коллекции в CSV формате
     * @param movies коллекция фильмов, которую необходимо сохранить
     * @throws Exception если произошла ошибка при сохранении в файл
     */
    protected abstract void saveToCSV(HashSet<Movie> movies) throws Exception;
    /**
     * Абстрактный метод для загрузки данных из файла в коллекцию.
     * @return коллекция фильмов из файла
     * @throws Exception если произошла ошибка при загрузке данных из файла
     */
    public abstract HashSet<Movie> loadFromCSV() throws Exception;
}
