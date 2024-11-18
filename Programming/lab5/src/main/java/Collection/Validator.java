package Collection;
import Data.Movie;

import java.time.LocalDate;

/**
 * Класс, отвечающий за валидацию объектов класса Movie и генерацию уникальных идентификаторов
 */
public class Validator {
    private final IdGenerator idGenerator = new IdGenerator();
    /**
     * Проверяет и возвращает валидный объект класса Movie
     * Если переданный объект не удовлетворяет условиям валидации, возвращает null
     * Если идентификатор объекта равен 0, генерирует и устанавливает новый id
     * Если дата создания объекта не указана, устанавливает текущую дату
     * @param movie объект класса Movie для валидации
     * @return валидный объект класса Movie или null, если объект не прошел валидацию
     */
    public Movie getValidated(Movie movie){
        if (movie.getId() < 0 || movie.getName() == null || movie.getName().isBlank() || movie.getCoordinates() == null || movie.getCoordinates().getX() > 347 || movie.getOscarsCount() <= 0 || movie.getBudget() <= 0 || movie.getMovieGenre() == null || movie.getMpaaRating() == null || movie.getOperator() == null || movie.getOperator().getName() == null || movie.getOperator().getName().isBlank() || movie.getOperator().getWeight() <= 0){
            return null;
        }else{
            if (movie.getId() == 0){
                movie.setId(IdGenerator.generateId());
            }
        }   if (movie.getCreationDate() == null){
                movie.setCreationDate(LocalDate.now());
            }
            return movie;
    }
    /**
     * Возвращает объект IdGenerator, используемый для генерации id
     * @return объект IdGenerator
     */
    public IdGenerator getIdGenerator(){
        return idGenerator;
    }
}