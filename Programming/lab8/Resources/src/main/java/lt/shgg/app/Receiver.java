package lt.shgg.app;

import lt.shgg.commands.Command;
import lt.shgg.data.*;
import lt.shgg.database.DatabaseParser;
import lt.shgg.network.Response;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <h1>Класс приемник</h1>
 * экземпляр класса содержит методы выполняющие всю логику команд и некоторые служебные
 */
public class Receiver {
    /**
     * Коллекция, которой манипулирует приложение
     */
    private final Collection<Ticket> data;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * Стандартный конструктор
     * @param data коллекция экземпляров класса {@link Ticket}
     */
    public Receiver(Storage data) {
        this.data = data.collection();
    }

    /**
     * Метод соответствующий команде help<br>
     * команда help выводит список доступных команд и краткое описание их действия
     * @param commands список всех доступных пользователю команд
     */
    public Response help(Collection<Command> commands){
        var res = new StringBuilder("вот список доступных вам команд: ");
        commands.forEach(cmd -> res.append(cmd.description()).append("\n"));
        return new Response(res.toString());
    }

    /**
     * Метод соответствующий команде info<br>
     * команда info выводит информацию о коллекции
     */
    public Response info(){
        return new Response("Тип коллекции: " + data.getClass().getSimpleName() + "\n" +
                "Размер коллекции: " + data.size());
    }

    /**
     * Метод соответствующий команде save<br>
     * команда save сохраняет коллекцию в файл переданный в качестве аргумента при запуске приложения
     */
    public void save() throws IOException {
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            var databaseParser = new DatabaseParser();
            databaseParser.save(this.data);
            System.out.println("Коллекция сохранена успешно");
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Метод соответствующий команде show<br>
     * команда show выводит всю коллекцию
     */
    public Response show(){
        Lock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            if (data.isEmpty())
                return new Response("Коллекция пуста. Используйте команду add, чтоб добавить элементы");
            var res = new StringBuilder();
            data.forEach(t -> res.append(t).append("\n"));
            return new Response(res.toString());
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Метод соответствующий команде add<br>
     * команда add добавляет новый билет в коллекцию
     */
    public Response add(Ticket ticket){
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            data.add(ticket);
            return new Response("элемент\n" + ticket + "\nдобавлен успешно");
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Метод соответствующий команде add_if_max<br>
     * команда add_if_max добавляет новый билет в коллекцию, если он превосходит максимальный в коллекции
     */
    public Response addIfMax(Ticket ticket){
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            if (ticket.compareTo(Collections.max(data)) > 0) {
                data.add(ticket);
                return new Response(" коллекцию добавлен элемент: " + ticket);
            } else return new Response("элемент не добавлен");
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Метод соответствующий команде remove_greater<br>
     * команда remove_greater удаляет из коллекции все элементы, большие введенного
     */
    public Response removeGreater(Ticket ticket, String login){
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            if (data.removeIf(t -> t.compareTo(ticket) > 0 && t.getAuthor().equals(login)))
                return new Response("элементы удалены");
            else return new Response("коллекция не изменилась");
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Метод соответствующий команде remove_lower<br>
     * команда remove_lower удаляет из коллекции все элементы, меньшие введенного
     */
    public Response removeLower(Ticket ticket, String login){
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            if (data.removeIf(t -> t.compareTo(ticket) < 0 && t.getAuthor().equals(login)))
                return new Response("элементы удалены");
            else return new Response("коллекция не изменилась");
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Метод соответствующий команде print_descending<br>
     * команда print_descending выводит все элементы коллекции в порядке убывания
     */
    public Response printDescending() {
        Lock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            var res = new StringBuilder();
            data.stream().sorted(Comparator.reverseOrder()).forEach(t -> res.append(t).append("\n"));
            return new Response(res.toString());
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Метод соответствующий команде filter_by_type<br>
     * команда filter_by_type выводит все элементы коллекции с типом type
     * @param type один из типов {@link Ticket.TicketType}
     */
    public Response filterByType(Ticket.TicketType type){
        Lock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            var res = new StringBuilder();
            data.stream().filter(ticket -> ticket.getType() == type).forEach(t -> res.append(t).append("\n"));
            return new Response(res.toString());
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Метод соответствующий команде count_greater_than_type<br>
     * команда count_greater_than_type выводит количество элементов коллекции с типом type
     * @param type один из типов {@link Ticket.TicketType}
     */
    public Response countGreaterThanType(Ticket.TicketType type){
        Lock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            long counter = data.stream().filter(ticket -> ticket.getType().compareTo(type) > 0).count();
            return new Response(Long.toString(counter));
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Метод соответствующий команде clear<br>
     * команда clear очищает коллекцию
     */
    public Response clear(String login){
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            data.removeIf(t -> t.getAuthor().equals(login));
            return new Response("Коллекция успешно очищена");
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Метод соответствующий команде remove_by_id<br>
     * команда remove_by_id удаляет из коллекции элемент по его id,
     * в случае отсутствия элемента с нужным id коллекция не меняется
     * @param id искомое id
     */
    public Response removeById(Long id){
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            var success = data.removeIf(ticket -> ticket.getId().equals(id));
            if (success) return new Response("Элемент успешно удалён");
            else return new Response("В коллекции нет элемента с id " + id);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Метод соответствующий команде update<br>
     * команда update обновляет значения полей элемента по id,
     * в случае отсутствия элемента с нужным id коллекция не меняется
     * @param id искомое id
     */
    public Response update(Long id, Ticket ticket){
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            if (data.stream().noneMatch(t -> t.getId().equals(id)))
                return new Response("Элемента с таким id не существует");
            else {
                var builder = new TicketBuilder();
                Ticket old = data.stream().filter(t -> t.getId().equals(id)).toList().get(0);
                builder.withId(old.getId());
                builder.withCreationDate(old.getCreationDate());
                builder.withName(ticket.getName());//ПОРА БЫ НЕ ЛЕНИТЬСЯ И ДОБАВИТЬ toBuilder() В БИЛДЕР
                builder.withPrice(ticket.getPrice());
                builder.withCoordinates(ticket.getCoordinates());
                builder.withType(ticket.getType());
                builder.withVenue(ticket.getVenue());
                data.removeIf(t -> t.getId().equals(id));
                data.add(builder.build());
                return new Response("объект обновлен");
            }
        } finally {
            writeLock.unlock();
        }
    }
}