package Data;
import Collection.IdGenerator;
import java.time.LocalDate;

/**
 * Класс, представляющий фильм с информацией о нём:
 * id, name, coordinates, creationDate, oscarsCount,
 * budget, genre, mpaaRating, operator
 */
public class Movie implements Comparable<Movie>{
    /** Уникальный идентификатор фильма
     * Поле не может быть null, Значение поля должно быть больше 0,
     * Значение этого поля должно быть уникальным,
     * Значение этого поля должно генерироваться автоматически
     */
    private Integer id;
    /**
     * Название
     * /Поле не может быть null, Строка не может быть пустой
     */
    private String name;
    /**
     * Какие-то координаты
     * Поле не может быть null
     */
    private Coordinates coordinates;
    /**
     * Дата создания записи о фильме
     * Поле не может быть null,
     * Значение этого поля должно генерироваться автоматически
     */
    private java.time.LocalDate creationDate;
    /**
     * Количество оскаров
     * Значение поля должно быть больше 0
     */
    private int oscarsCount;
    /**
     * Бюджет
     * Значение поля должно быть больше 0
     */
    private long budget;
    /**
     * Жанр
     * Поле не может быть null
     */
    private MovieGenre genre;
    /**
     * Возрастное ограничение
     * Поле не может быть null
     */
    private MpaaRating mpaaRating;
    /**
     * Оператор
     * Поле может быть null
     */
    private Person operator;
    IdGenerator idGenerator = new IdGenerator();

    /**
     * Конструктор объект фильма с указанными параметрами
     *
     * @param id           Уникальный идентификатор фильма
     * @param name         Название
     * @param coordinates  Какие-то координаты
     * @param creationDate Дата создания записи о фильме
     * @param oscarsCount  Количество оскаров
     * @param budget       Бюджет
     * @param genre        Жанр
     * @param mpaaRating   Возрастное ограничение
     * @param operator     Оператор
     */
    public Movie(Integer id, String name, Coordinates coordinates, LocalDate creationDate, int oscarsCount, long budget, MovieGenre genre, MpaaRating mpaaRating, Person operator) {
        if (name == null || name.isBlank() || coordinates == null || oscarsCount <= 0 || genre == null || mpaaRating == null || operator == null) {
            throw new IllegalArgumentException("The fields can't be null or empty");
        } else {
            if (id == null){
                this.id = IdGenerator.generateId();
            }else{
                this.id = id;
            }
            this.name = name;
            this.coordinates = coordinates;
            this.creationDate = java.time.LocalDate.now();
            this.oscarsCount = oscarsCount;
            this.budget = budget;
            this.genre = genre;
            this.mpaaRating = mpaaRating;
            this.operator = operator;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public java.time.LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = java.time.LocalDate.now();
    }
    public Integer getOscarsCount() {
        return oscarsCount;
    }

    public void setOscarsCount(Integer oscarsCount) {
        this.oscarsCount = oscarsCount;
    }
    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }
    public MovieGenre getMovieGenre() {
        return genre;
    }

    public void setMovieGenre(MovieGenre genre) {
        this.genre = genre;
    }
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public Person getOperator() {
        return operator;
    }

    public void setOperator(Person operator) {
        this.operator = operator;
    }
    /**
     * Сравнивает этот фильм с другим фильмом на основе количества полученных оскаров
     *
     * @param movie Фильм для сравнения
     * @return Результат сравнения по количеству оскаров
     */
    @Override
    public int compareTo(Movie movie){
        if (oscarsCount > movie.getOscarsCount()){
            return 1;
        }else if (oscarsCount < movie.oscarsCount){
            return -1;
        }else{
            return 0;
        }
    }
    public boolean equals(Movie movie){
        return (this.id == movie.getId()) || (this.name.equals(movie.getName()) && this.coordinates.equals(movie.getCoordinates()) && this.oscarsCount == movie.getOscarsCount() && this.budget == movie.getBudget() && this.genre.equals(movie.getMovieGenre()) && this.mpaaRating.equals(movie.getMpaaRating()) && this.operator.equals(movie.getOperator()));
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", oscarsCount=" + oscarsCount +
                ", budget=" + budget +
                ", genre=" + genre +
                ", mpaaRating=" + mpaaRating +
                ", operator=" + operator + "}";
    }
}
