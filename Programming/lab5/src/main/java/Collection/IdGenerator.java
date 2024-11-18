package Collection;

import Data.Movie;

import java.util.ArrayList;
import java.util.HashSet;
/**
 * Класс для генерации уникальных идентификаторов и управления списком идентификаторов
 */
public class IdGenerator {
    /**
     * Нижняя граница диапазона
     */
    private static final Integer min = 1;
    /**
     * Верхняя граница диапазона
     */
    private static final Integer max = 10000000;
    /**
     * Коллекция для хранения списка id
     */
    private static HashSet<Integer> idList = new HashSet<>();

    /**
     * Генерирует уникальный идентификатор в заданном диапазоне и добавляет его в список
     * @return id
     */
    public static Integer generateId() {
        Integer id = (int) Math.floor(Math.random() * (max - min + 1)) + min;
        while (idList.contains(id)) {
            id = (int) Math.floor(Math.random() * (max - min + 1)) + min;
        }
        idList.add(id);
        return id;
    }

    /**
     * Добавляет идентификатор, присвоенный фильму, в список идентификаторов
     * @param movie
     */
    public void addId(Movie movie){
        idList.add(movie.getId());
    }

    /**
     * Возвращает список уникальных идентификаторов
     * @return idList
     */
    public HashSet<Integer> getIdList(){
        return idList;
    }
}
