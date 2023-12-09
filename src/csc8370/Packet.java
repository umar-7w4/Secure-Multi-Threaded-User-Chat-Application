package csc8370;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Packet implements Serializable {
    Message core;
    Timestamp timestamp;
    public Packet(Message input) {
        timestamp = new Timestamp((new Date()).getTime());
        core = input;
    }
}
