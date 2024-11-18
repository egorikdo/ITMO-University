package Commands;

import Collection.CollectionManager;
/**
 * Класс команды exit
 */
public class Exit implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public Exit(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды выхода из программы
     * @param args массив аргументов команды
     * @throws IllegalArgumentException если количество аргументов некорректно
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 1){
            collectionManager.addToHistory(args[0]);
            collectionManager.exit();
        }else throw new IllegalArgumentException("Incorrect number of arguments. ");
    }

    @Override
    public String getName() {
        return "exit ";
    }

    @Override
    public String getDescription() {
        return " end the program (without saving to a file)";
    }
}
