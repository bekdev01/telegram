package uz.pdp.database.user;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import uz.pdp.database.base.BaseDatabase;
import uz.pdp.database.utils.MongoUtils;
import uz.pdp.model.User;

import java.util.ArrayList;
import java.util.Date;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class UserDatabase implements BaseDatabase {

    public static Document addUser(User user) {

        MongoDatabase database = BaseDatabase.getDatabase();
        MongoCollection<Document> users = database.getCollection("users");
        Document user1 = users.
                find(Filters.eq("phone", user.getPhone())).first();
        if (user1 != null)
            return null;
        Document doc = MongoUtils.getUserDocument(user);
        users.insertOne(doc);
        return doc;
    }

    public static Document getUser(String id) {

        return BaseDatabase.getDatabase().getCollection("users").find(Filters.eq(new ObjectId(id))).first();
    }


    public static boolean addMessage(String fromId, String toId, String text) {
        MongoDatabase connection = BaseDatabase.getDatabase();
        MongoCollection<Document> users = connection.getCollection("users");

        Document fromUser = getUser(fromId);
        Document toUser = getUser(toId);

        Bson filterFrom = and(eq("_id", new ObjectId(fromId)),
                eq("messages.id", toId));
        Bson filterTo = and(eq("_id", new ObjectId(toId)),
                eq("messages.id", fromId));

        Document docFrom = users.find(filterFrom).first();
        Document docTo = users.find(filterTo).first();
        if (docFrom == null && docTo == null) {
            Bson updates = MongoUtils.pushMessage(text, toId, true);
            users.updateOne(fromUser, updates);

            updates = MongoUtils.pushMessage(text, fromId, false);
            users.updateOne(toUser, updates);

            return true;
        }

        Document document = new Document();
        document.append("text", text)
                .append("date", new Date())
                .append("isMine", true);
        Bson push = Updates.push("messages.$.messageUser", document);
        users.updateOne(filterFrom, push);

        document.put("isMine", false);
        push = Updates.push("messages.$.messageUser", document);
        users.updateOne(filterTo, push);
        return true;


    }

    public static ArrayList<Document> getMessage(String fromId, String toId) {

        Document user = getUser(fromId);
        ArrayList<Document> messages = (ArrayList<Document>) user.get("messages");
        for (var msg : messages) {
            if (msg.get("id").equals(toId)) {
                return (ArrayList<Document>) msg.get("messageUser");
            }
        }
        return null;
    }


}
