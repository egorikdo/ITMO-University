package FileSystem;

import java.util.Stack;

/**
 * Класс хранит стек файлов, с которыми работает пользователь
 */
public class FileStack {
    /**
     * Стек файлов
     */
    private static final Stack<String> fileStack = new Stack<>();

    /**
     * Конструктор класса
     */
    public FileStack(){

    }
    /**
     * Метод для получения стека файлов.
     * @return стек имен файлов
     */
    public static Stack<String> getFileStack(){
        return fileStack;
    }
    /**
     * Метод для добавления имени файла в стек
     * @param filename
     */
    public static void addFile(String filename){
        fileStack.push(filename);
    }
    /**
     * Метод для удаления имени файла из стека
     */
    public static void removeFile() {
        fileStack.pop();
    }
}
