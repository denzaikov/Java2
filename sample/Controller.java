package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    TextField textChat;

    @FXML
    TextArea chatField;

    public void Send(){

        chatField.appendText(textChat.getText() + "\n");
        textChat.clear();
    }

    public void PressText(){

        chatField.appendText(textChat.getText() + "\n");
        textChat.clear();
    }
}
