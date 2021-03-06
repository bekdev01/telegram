package uz.pdp.database.utils;

import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import uz.pdp.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


public class MongoUtils {

    public static Document getUserDocument(User user) {
        Document doc = new Document();
        ArrayList<Document> messages = new ArrayList<>();
        doc.append("firstname", user.getFirstName())
                .append("lastname", user.getLastName())
                .append("phone", user.getPhone())
                .append("messages", messages)
                .append("password",user.getPassword());
        return doc;
    }

    public static Bson pushMessage(String text , String id  , boolean isMine){
        Document message = new Document();
        message.append("text", text)
                .append("date", LocalDateTime.now().toString())
                .append("isMine", isMine);
        ArrayList<Document> msg = new ArrayList<>();
        msg.add(message);
        Document document = new Document()
                .append("id", id)
                .append("messageUser", msg);


        return Updates.push("messages", document);
    }


}
