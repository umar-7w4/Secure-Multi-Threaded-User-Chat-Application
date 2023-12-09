package csc8370;
import java.io.Serializable;

public class Message implements Serializable {

    String text;
    public Message(String input) {
        text = input;
    }

}
