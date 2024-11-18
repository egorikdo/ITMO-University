package Commands;

import Collection.CollectionManager;
/**
 * Класс команды save
 */
public class Save implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public Save(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды сохраняющий изменения в коллекцию
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 1){
            collectionManager.addToHistory(args[0]);
            collectionManager.save();
        }else throw new IllegalArgumentException("The number of arguments is incorrect.");

    }

    @Override
    public String getName() {
        return "save ";
    }

    @Override
    public String getDescription() {
        return " save collection in file";
    }
}
