package Data;

/**
 * Класс, представляющий оператора с информацией о нём
 * name, weight, eyeColor
 */
public class Person {
    /**
     * Имя оператора
     * Поле не может быть null, Строка не может быть пустой
     */
    private String name;
    /**
     * Вес оператора
     * Значение поля должно быть больше 0
     */
    private float weight;
    /**
     * Цвет глаз оператора
     * Поле может быть null
     */
    private Color eyeColor;

    /**
     * Конструктор объекта оператора с указанными параметрами
     * @param name      Имя
     * @param weight    Вес
     * @param eyeColor  Цвет глаз
     */
    public Person(String name, float weight, Color eyeColor){
        this.name = name;
        this.weight = weight;
        this.eyeColor = eyeColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", eyeColor=" + eyeColor +
                '}';
    }
}
