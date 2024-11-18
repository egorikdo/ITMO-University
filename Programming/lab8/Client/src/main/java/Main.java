import lt.shgg.data.User;
import utils.Authorisator;
import utils.ClientConsole;
import utils.Sender;

import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать, пользователь!");
        User user = Authorisator.authorise();
        var console = new ClientConsole();
        var sender = new Sender("localhost", 1488, 5000, 7);
        console.runApp(new InputStreamReader(System.in), sender, false, user);
    }
}
