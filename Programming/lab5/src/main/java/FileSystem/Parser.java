package FileSystem;
import Collection.IdGenerator;
import Collection.Validator;
import Data.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.time.LocalDate;
/**
 * Парсер для сохранения и загрузки данных о фильмах в формате CSV
 *
 */
public class Parser extends FileManager {
    /**
     * Конструктор класса Parser
     * @param fileName имя файла, с которым будет работать парсер
     */
    public Parser(String fileName) {
        super(fileName);
    }
    /**
     * Метод сохранения данных о фильмах в формате CSV
     * @param movies множество фильмов, которое необходимо сохранить
     * @throws Exception
     */
    @Override
    public void saveToCSV(HashSet<Movie> movies) throws Exception {
        FileWriter writer = null;
        try {
            Path filePath = Paths.get(this.getFileName());
            if (!Files.isWritable(filePath)){
                throw new IOException("Insufficient permissions to write to the file");
            }
            writer = new FileWriter(this.getFileName());
            writer.write("id;name;coordinates_x;coordinates_y;creationDate;oscarsCount;budget;genre;mpaaRating;operator_name;operator_weight;operator_eyeColor\n");

            for (Movie movie : movies) {
                writer.write(movie.getId() + ";");
                writer.write(movie.getName() + ";");
                writer.write(movie.getCoordinates().getX() + ";");
                writer.write(movie.getCoordinates().getY() + ";");
                writer.write(movie.getCreationDate() + ";");
                writer.write(movie.getOscarsCount() + ";");
                writer.write(movie.getBudget() + ";");
                writer.write(movie.getMovieGenre() + ";");
                writer.write(movie.getMpaaRating() + ";");
                writer.write(movie.getOperator().getName() + ";");
                writer.write(movie.getOperator().getWeight() + ";");
                writer.write(movie.getOperator().getEyeColor() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing the file: " + e.getMessage());
                }
            }
        }
    }
    /**
     * Метод загрузки данных о фильмах из формата CSV
     * @return множество фильмов, загруженных из файла
     * @throws Exception
     */
    @Override
    public HashSet<Movie> loadFromCSV() throws Exception {
        HashSet<Movie> movies = new HashSet<>();
        Validator validator = new Validator();
        IdGenerator idGenerator = new IdGenerator();
        try {
            File file = new File(this.getFileName());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            if (reader.readLine() == null) {
                throw new IllegalArgumentException("CSV file is empty.");
            }
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";");
                if (columns.length < 12) {
                    throw new IllegalArgumentException("Incorrect CSV format. Skipping line.");
                }

                Movie movie = new Movie(Integer.parseInt(columns[0]),
                        columns[1],
                        new Coordinates(Float.parseFloat(columns[2]), Float.parseFloat(columns[3])),
                        LocalDate.parse(columns[4]),
                        Integer.parseInt(columns[5]),
                        Long.parseLong(columns[6]),
                        MovieGenre.valueOf(columns[7]),
                        MpaaRating.valueOf(columns[8]),
                        new Person(columns[9], Float.parseFloat(columns[10]), Color.valueOf(columns[11])));

                Movie validatedMovie = validator.getValidated(movie);
                if (validatedMovie != null && !idGenerator.getIdList().contains(validatedMovie.getId())) {
                    movies.add(validatedMovie);
                    idGenerator.addId(validatedMovie);
                } else {
                    System.out.println("Skipping invalid element.");
                }
            }
            return movies;
        } catch (IOException e) {
            System.out.println("An error occurred while loading data from CSV file: " + e.getMessage());
            return movies;
        }
    }
}