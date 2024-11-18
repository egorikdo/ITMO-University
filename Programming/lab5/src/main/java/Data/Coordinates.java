package Data;

/**
 * Класс, представляющий координаты координат
 */
public class Coordinates {
    /**
     * 'X' координата
     * Максимальное значение поля: 347
     */
    private float x;
    /**
     * 'Y' координата
     */
    private float y;

    /**
     * Конструктор объекта координат с указанными параметрами
     * @param x     Координата 'X'
     * @param y     Координата 'Y'
     */
    public Coordinates(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public void setX(long x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    public boolean equals(Coordinates c) {
        return c.getX() == this.x && c.getY() == this.y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
