package netchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private final List<ClientHandler> loggedUser;
    private final AuthService authService;


    public Server() {
        authService = new AuthService();
        loggedUser = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void subscribe(ClientHandler user){
        loggedUser.add(user);
    }

    public synchronized void unsubscribe(ClientHandler user){
        loggedUser.remove(user);
    }

    public synchronized boolean isUserNotOccupied(String name){
        return !isUserOccupied(name);
    }

    public synchronized boolean isUserOccupied(String name){
        /**
        for (ClientHandler user : loggedUser)
              { if (user.getName().equals(user)) {
                  return true;
              }
            return false;
        }
        */

        /**
        loggedUser.stream()
                .filter(u -> user.getName().equals(u))
                .findFirst()
                .isPresent();
        */
        return loggedUser.stream()
                .anyMatch(u -> u.getName().equals(name));
    }

    public synchronized void broadcastMessage(String outboundMesage){
        /**
        for (ClientHandler user: loggedUser){
            user.sendMessage(outboundMesage);
        }
        */
        loggedUser.forEach(clientHandler -> clientHandler.sendMessage(outboundMesage));
    }

    public synchronized void privateMessage(String[] receivers, String outboundMessage) {
        //receivers.re
        for (ClientHandler user: loggedUser) {
            if (Arrays.asList(receivers).indexOf(user.getName()) >= 0) {
                user.sendMessage(outboundMessage);
            }
        }
    }
}
