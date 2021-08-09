package netchat.client;

import netchat.client.communication.ClientCommunicator;
import netchat.client.gui.ChatFrame;

import java.util.function.Consumer;

public class OutStandingChat {

    private final ChatFrame frame;
    private final ClientCommunicator communicator;

    public OutStandingChat() {

        communicator = new ClientCommunicator();
        Consumer<String> outMessageConsumer = communicator::sendMessage;

        frame = new ChatFrame(outMessageConsumer);

        new Thread(() -> {
            while (true) {
                String inMessage = communicator.receiveMessage();
                frame.getInMessageConsumer().accept(inMessage);
            }
        })
                .start();

    }

    private String receiveMessage(){
        return communicator.receiveMessage();
    }
}
