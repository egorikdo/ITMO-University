package FileSystem;
import Data.Color;
import Data.MovieGenre;
import Data.MpaaRating;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Класс FileReaderManager представляет собой управление чтением данных из файла с использованием BufferedReader
 */
public class FileReaderManager {
    private BufferedReader reader;
    /**
     * Конструктор класса FileReaderManager
     * @param reader
     * @throws FileNotFoundException
     */
    public FileReaderManager(BufferedReader reader) throws FileNotFoundException {
        this.reader = reader;
    }

    /**
     * Метод для получения объекта BufferedReader
     * @return reader
     */
    public BufferedReader getReader() {
        return this.reader;
    }
    /**
     * Метод для чтения названия фильма
     * @return name
     * @throws IllegalArgumentException
     */
    public String readName() throws IllegalArgumentException {
        try {
            String name = reader.readLine();
            if (name == null || name.isEmpty() || name.isBlank()) {
                throw new IllegalArgumentException("");
            }
            return name;
        } catch (IOException e) {
            throw new IllegalArgumentException("");
        }
    }
    /**
     * Метод для чтения координаты X
     * @return значение координаты X
     * @throws IOException
     */
    public float readCoordinateX() throws IOException {
        return Float.parseFloat(reader.readLine());

    }
    /**
     * Метод для чтения координаты Y
     * @return значение координаты Y
     * @throws IOException
     */
    public float readCoordinateY() throws IOException {
        float y = Float.parseFloat(reader.readLine());
        if (y > 347){
            throw new IllegalArgumentException("");
        }else{
            return y;
        }

    }
    /**
     * Метод для чтения количества оскаров
     * @return oscarsCount
     * @throws IOException
     */
    public int readOscarsCount() throws IllegalArgumentException {
        try {
            int oscarsCount = Integer.parseInt(reader.readLine());
            if (oscarsCount <= 0) {
                throw new IllegalArgumentException("");
            }
            return oscarsCount;
        } catch (IOException | NumberFormatException e) {
            throw new IllegalArgumentException("");
        }
    }
    /**
     * Метод для чтения бюджета
     * @return budget
     * @throws IOException
     */
    public long readBudget() throws IllegalArgumentException {
        try {
            long budget = Long.parseLong(reader.readLine());
            if (budget <= 0) {
                throw new IllegalArgumentException("");
            }
            return budget;
        } catch (IOException | NumberFormatException e) {
            throw new IllegalArgumentException("");
        }
    }
    /**
     * Метод для чтения жанра
     * @return genre
     * @throws IOException
     */
    public MovieGenre readGenre() throws IOException {
        String genre = reader.readLine().trim().toUpperCase();
        return switch (genre) {
            case "ACTION" -> MovieGenre.ACTION;
            case "WESTERN" -> MovieGenre.WESTERN;
            case "MUSICAL" -> MovieGenre.MUSICAL;
            case "HORROR" -> MovieGenre.HORROR;
            default -> null;
        };
    }
    /**
     * Метод для чтения возрастного ограничения
     * @return mpaaRating
     * @throws IOException
     */
    public MpaaRating readMpaaRating() throws IOException {
        String mpaaRating = reader.readLine().trim().toUpperCase();
        return switch (mpaaRating) {
            case "G" -> MpaaRating.G;
            case "PG_13" -> MpaaRating.PG_13;
            case "R" -> MpaaRating.R;
            case "NC_17" -> MpaaRating.NC_17;
            default -> null;
        };
    }
    /**
     * Метод для чтения имени оператора
     * @return name
     * @throws IOException
     */
    public String readOperatorsName() throws IllegalArgumentException {
        try {
            String name = reader.readLine();
            if (name == null || name.isEmpty() || name.isBlank()) {
                throw new IllegalArgumentException("");
            }
            return name;
        } catch (IOException e) {
            throw new IllegalArgumentException("");
        }
    }
    /**
     * Метод для чтения веса оператора
     * @return weight
     * @throws IOException
     */
    public int readOperatorsWeight() throws IllegalArgumentException {
        try {
            int weight = Integer.parseInt(reader.readLine());
            if (weight <= 0) {
                throw new IllegalArgumentException("");
            }
            return weight;
        } catch (IOException | NumberFormatException e) {
            throw new IllegalArgumentException("");
        }
    }
    /**
     * Метод для чтения цвета глаз оператора
     * @return eyeColor
     * @throws IOException
     */
    public Color readEyeColor() throws IOException {
        String eyeColor = reader.readLine().trim().toUpperCase();
        return switch (eyeColor) {
            case "GREEN" -> Color.GREEN;
            case "BLUE" -> Color.BLUE;
            case "ORANGE" -> Color.ORANGE;
            case "WHITE" -> Color.WHITE;
            default -> null;
        };
    }
}
