package uz.pdp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.swing.text.Document;
import java.util.ArrayList;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    String id;
    String firstName;
    String lastName;
    String phone;
    String password;
    ArrayList<Document> messages;

    public User(String firstName, String lastName, String phone, String password, ArrayList<Document> messages) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
        this.messages = messages;
    }

    public User(String firstName, String lastName, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
    }
}
