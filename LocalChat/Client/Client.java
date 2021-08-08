package LocalChat.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {

    private Socket socket;

    public Client() {
        init();
        communicate();
    }

    private void init() {
        try {
            socket = new Socket("localhost", 8899);
            System.out.println("Connection success...");
            System.out.println(socket);
            System.out.println("STATUS OK.");

        } catch (Exception e) {
            System.out.println("Connection failed! Server not found....");
            System.out.println("STATUS ERROR.");
            System.exit(-1);
        }
    }

    private void communicate() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            AtomicBoolean isAlive = new AtomicBoolean(true);

            new Thread(() -> {
                try {
                    while (true) {

                        String inMessage = in.readUTF();

                        if (inMessage.equals("-end")) {
                            isAlive.set(false);
                            System.out.println("Please press ENTER to close client...");
                            break;
                        }
                        System.out.println("\nServer: " + inMessage);
                        System.out.print("Client: ");
                    }
                } catch (IOException e) {
                    System.out.print("Server disconnected");
                    System.exit(-1);
                }
            }).start();

            while (true) {
                if (!isAlive.get()) {
                    System.out.println("Client closing...");
                    System.out.println("STATUS OK.");
                    break;
                }

                System.out.print("Client: ");
                String outMessage = scanner.nextLine();
                out.writeUTF(outMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}