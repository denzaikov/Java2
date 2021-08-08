package LocalChat.Server;

/**
 * 1. Написать консольный вариант клиент\серверного приложения, в котором пользователь может писать сообщения,
 * как на клиентской стороне, так и на серверной. Т.е. если на клиентской стороне написать "Привет",
 * нажать Enter то сообщение должно передаться на сервер и там отпечататься в консоли.
 * Если сделать то же самое на серверной стороне, сообщение соответственно передается клиенту и печатается у него в консоли.
 * Есть одна особенность, которую нужно учитывать: клиент или сервер может написать несколько сообщений подряд,
 * такую ситуацию необходимо корректно обработать
 * ВАЖНО! Сервер общается только с одним клиентом, т.е. не нужно запускать цикл, который будет ожидать второго/третьего/n-го клиентов
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private ServerSocket socket;
    private Socket client;

    public Server() {
        init();
        communicate();
        System.out.println("Closing the connection...");
        System.out.println("Shutting down...");
        System.out.println("STATUS OK.");
    }
    private void init() {
        try {
            socket = new ServerSocket(8899);
            System.out.println("Socket created...");
            System.out.println("Waiting for a connection...");
            client = socket.accept();
            System.out.println("Client connected...");
            System.out.println(client);
            System.out.println("STATUS OK.");
        } catch (IOException e) {
            System.out.println("Not connect...");
            e.printStackTrace();
        }
    }

    private void communicate() {
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            new Thread(() -> {
                try {
                    while (true) {
                        System.out.print("Server: ");
                        String outMessage = scanner.nextLine();
                        out.writeUTF(outMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            while (true) {
                String inMessage = "";
                try {
                    inMessage = in.readUTF();
                } catch (Exception e) {
                    System.out.print("Client disconnected");
                    break;
                }

                if (inMessage.equals("-exit")) {
                    out.writeUTF(" Good bye!");
                    out.writeUTF("-end");
                    break;
                }

                System.out.println("\nClient: " + inMessage);
                System.out.print("Server: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}