package Commands;

import Collection.CollectionManager;
/**
 * Класс команды group_counting_by_name
 */
public class GroupCountingByName implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public GroupCountingByName(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды группировки и подсчёта количества фильмов с одинаковым названием
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 1){
            collectionManager.addToHistory(args[0]);
            collectionManager.groupCountingByName();
        }else throw new IllegalArgumentException("Incorrect number of arguments.");

    }

    @Override
    public String getName() {
        return "group_counting_by_name";
    }

    @Override
    public String getDescription() {
        return "group the collection items by the value of the name field, print the number of items in each group";
    }
}
