package model;

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
    String email;
    String emailPassword;
    ArrayList<Document> messages;

    public User(String firstName, String lastName, String email, String emailPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailPassword = emailPassword;
    }
}
