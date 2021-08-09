package netchat.client.gui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ChatFrame {

    private final JFrame mainFrame;
    private JTextArea chattingArea;
    private JButton pushButton;
    private final Consumer<String> inMessageConsumer;
    private final Consumer<String> outMessageConsumer;


    public ChatFrame(Consumer<String> outMessageConsumer) {
        this.outMessageConsumer = outMessageConsumer;

        inMessageConsumer = createinMessageConsumer();

        mainFrame = new JFrame();
        mainFrame.setTitle("Outstanding Chat v1.0");

        mainFrame.setBounds(new Rectangle(400, 700));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());


        mainFrame.add(createTop(), BorderLayout.CENTER);
        mainFrame.add(createBottom(), BorderLayout.SOUTH );

        mainFrame.setVisible(true);
    }

    public Consumer<String> getInMessageConsumer() {
        return inMessageConsumer;
    }

    private Consumer<String> createinMessageConsumer() {
        return inMessage -> chattingArea.append(inMessage + "\n");
    }

    private JPanel createTop(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        chattingArea = new JTextArea();
        chattingArea.setEditable(false);
        jPanel.add(chattingArea, BorderLayout.CENTER);

        return jPanel;
    }

    private JPanel createBottom(){

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        JTextField inputArea = new JTextField();
        pushButton = new JButton("Push");

        pushButton.addActionListener(event -> {
            String text = inputArea.getText();
            outMessageConsumer.accept(text);
        });

        jPanel.add(inputArea, BorderLayout.CENTER);
        jPanel.add(pushButton, BorderLayout.EAST);

        return jPanel;

    }
}
