package Console;
import Data.*;

/**
 * Данный класс используется для получения объекта класса Movie
 * */

public class ClientManager {
    /**
     * Объект классам, читающий параметры фильма
     */
    ReaderManager readerManager = new ReaderManager();

    /**
     * Получение параметров объкта класса Movie
     * @return new Movie
     */

    public Movie getMovie() {
        String name = readerManager.readName();
        float x = readerManager.readCoordinateX();
        float y = readerManager.readCoordinateY();
        int oscarsCount = readerManager.readOscarsCount();
        int budget = readerManager.readBudget();
        MovieGenre genre = readerManager.readMovieGenre();
        MpaaRating mpaaRating = readerManager.readMpaaRating();
        String operatorsName = readerManager.readOperatorName();
        float weight = readerManager.readWeight();
        Color eyeColor = readerManager.readEyeColor();
        return new Movie(null, name, new Coordinates(x, y), null, oscarsCount, budget, genre, mpaaRating, new Person(operatorsName, weight, eyeColor));
    }
}
