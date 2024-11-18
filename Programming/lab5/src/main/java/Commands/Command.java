package Commands;

/**
 * Интерфейс Command, который имплементируют все классы команд
 */
public interface Command {
    /**
     * Метод выполнения команды
     * @param args
     * @throws Exception
     */
    public void execute(String[] args) throws Exception;

    /**
     * Метод для возврата названия команды
     * @return name
     */
    public String getName();

    /**
     * Метод для возврата описания команды
     * @return description
     */
    public String getDescription();
}
