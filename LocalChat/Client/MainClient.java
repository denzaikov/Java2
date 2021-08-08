package LocalChat.Client;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("Клиент: ");
        new Thread(() -> new Client()).start();
    }
}
