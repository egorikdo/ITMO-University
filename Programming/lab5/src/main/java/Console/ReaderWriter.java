package Console;
/**
 * Интерфейс для чтения и записи различных типов данных
 */
public interface ReaderWriter {
    int readInt();
    float readFloat();
    long readLong();
    String readLine();
    void write(String text);
    String getValidatedValue(String message);

}
