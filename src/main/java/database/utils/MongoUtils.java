package database.utils;

import model.User;
import org.bson.Document;

import java.util.ArrayList;


public class MongoUtils {

    public static Document getUserDocument(User user) {
        Document doc = new Document();
        ArrayList<Document> messages = new ArrayList<>();
        doc.append("firstname", user.getFirstName())
                .append("lastname", user.getLastName())
                .append("email", user.getEmail())
                .append("messages", messages);



        return doc;
    }


}
