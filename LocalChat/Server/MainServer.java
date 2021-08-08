package LocalChat.Server;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("Сервер: ");
        new Thread(() -> new Server()).start();
    }
}
