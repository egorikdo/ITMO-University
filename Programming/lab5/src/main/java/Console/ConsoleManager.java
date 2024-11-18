package Console;
import java.util.Scanner;

/**
 * Класс реализующий интерфейс ReaderWriter для чтения и записи различных типов данных из консоли
 */
public class ConsoleManager implements ReaderWriter{
    public ConsoleManager(){
    }

    @Override
    public int readInt() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    @Override
    public float readFloat() {
        Scanner scanner = new Scanner(System.in);
        return Float.parseFloat(scanner.nextLine().trim());
    }

    @Override
    public long readLong() {
        Scanner scanner = new Scanner(System.in);
        return Long.parseLong(scanner.nextLine().trim());
    }

    @Override
    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    @Override
    public void write(String text) {
        System.out.println(text);
    }

    @Override
    public String getValidatedValue(String message) {
        write(message);
        while (true){
            String usersInput = readLine();
            if (!usersInput.isEmpty() && !usersInput.isBlank()){
                return usersInput;
            }
        }

    }
}

