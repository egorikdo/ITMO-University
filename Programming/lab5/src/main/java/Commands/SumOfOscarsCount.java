package Commands;

import Collection.CollectionManager;
/**
 * Класс команды sum_of_oscars_count
 */
public class SumOfOscarsCount implements Command{
    CollectionManager collectionManager;
    /**
     * Конструктор класса
     * @param collectionManager
     */
    public SumOfOscarsCount(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод выполнения команды показывающий сумму всех оскаров в коллекции
     * @param args массив аргументов команды
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 1){
            collectionManager.addToHistory(args[0]);
            collectionManager.sumOfOscarsCount();
        }else throw new IllegalArgumentException("Incorrect number of arguments.");

    }

    @Override
    public String getName() {
        return "sum_of_oscars_count ";
    }

    @Override
    public String getDescription() {
        return " print the sum of the values of the oscarsCount field for all items in the collection";
    }
}
