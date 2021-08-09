package netchat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler {

    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private String privateRegExp = "private\\[([a-z0-9\\;]+)\\]:\\s(.*)";
    private boolean isAuth = false;


    public ClientHandler(Server server, Socket socket) {

        try {
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    doAuthentication();

                    listenMessage();
                } catch (IOException e){
                    e.printStackTrace();
                }  finally {
                    server.unsubscribe(this);
                    server.broadcastMessage(String.format("User[%s] is out.", name));
                    closeConnection(socket);
                }
            }).start();

            new Thread(() -> {
                try {
                    Thread.sleep(120000);
                    timerAuth(socket);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            })
                    .start();

        } catch (IOException e) {
            throw new  RuntimeException("Something went wrong establishing", e);
        }
    }

    private void closeConnection(Socket socket){

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    private void doAuthentication() throws IOException {

        sendMessage("Greeting you in the Outstanding Chat.");
        sendMessage("Please do authentication in 120 seconds. Template is: -auth [login] [password]");

        while (true) {
            String maybeCredentials = in.readUTF();
            /** sample: -auth login1 password1 */
            if (maybeCredentials.startsWith("-auth")) {
                String[] credentials = maybeCredentials.split("\\s");

                Optional<AuthService.Entry> maybeUser = server.getAuthService()
                        .findUserByLoginAndPassword(credentials[1], credentials[2]);

                if  (maybeUser.isPresent()) {
                    AuthService.Entry user = maybeUser.get();
                    if (server.isUserNotOccupied(user.getName())) {
                        name = user.getName();
                        sendMessage("Authentication OK");
                        sendMessage("Welcome");
                        server.broadcastMessage(String.format("User[%s] entered chat.", name));
                        server.subscribe(this);
                        isAuth = true;
                        return;

                    } else {
                        sendMessage("Current user is already logged in");
                        //respond with user occupie
                    }
                } else {
                    sendMessage("Invalid credentials");
                    //respond with bad credentials
                }
            } else {
                sendMessage("Don't authentication, please type: -auth [login] [password]");
            }
        }
    }

    public void sendMessage(String outMessage) {
        try {
            out.writeUTF(outMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listenMessage() throws IOException{
        while (true){
            String inMessage = in.readUTF();
            if (inMessage.equals("-exit")){
                break;
            }
            String[] receivers = getPrivateReceivers(inMessage);

            if (receivers.length > 0) {
                server.privateMessage(receivers, "["+name+"]: " + inMessage);
            }
            else {
                server.broadcastMessage("["+name+"]: " + inMessage);
            }
        }
    }
    private String[] getPrivateReceivers(String inboundMessage) {
        if (inboundMessage.matches(privateRegExp)) {
            Pattern p = Pattern.compile(privateRegExp);
            Matcher m = p.matcher(inboundMessage);
            m.find();
            String receiversString = m.group(1);
            receiversString += ";" + name;
            return receiversString.split(";");
        }
        String[] receivers = {};
        return receivers;
    }
    public void timerAuth(Socket socket) {

        TimerTask timeConnection = new TimerTask() {
            @Override
            public void run(){
                sendMessage("Time out\nConnection lost");
                closeConnection(socket);
            }
        };

        Timer terminatingTimer = new Timer();
        if(isAuth == false) {
            terminatingTimer.schedule(timeConnection, 5000);
        }
    }
}
