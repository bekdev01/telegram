package uz.pdp.database.user;

import com.google.gson.Gson;
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
import uz.pdp.payload.UserDTO;
import uz.pdp.payload.Response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

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

    public static Document getUserByPhoneAndPassword(String phone, String password) {
        return BaseDatabase.getDatabase().getCollection("users").find(Filters.and(eq("phone", phone), eq("password", password))).first();
    }

    public static Document getUserByPhone(String phone) {
        return BaseDatabase.getDatabase().getCollection("users").find(Filters.eq("phone", phone)).first();
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
                .append("date", LocalDateTime.now().toString())
                .append("isMine", true);
        Bson push = Updates.push("messages.$.messageUser", document);
        users.updateOne(filterFrom, push);

        document.put("isMine", false);
        push = Updates.push("messages.$.messageUser", document);
        users.updateOne(filterTo, push);
        return true;


    }
    public static boolean addMessageByPhone(String phone, String toId, String text) {
        MongoDatabase connection = BaseDatabase.getDatabase();
        MongoCollection<Document> users = connection.getCollection("users");

        Document fromUser = getUserByPhone(phone);
        Document toUser = getUser(toId);
        String fromId = fromUser.get("_id").toString();

        Bson filterFrom = and(eq("phone",phone),
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
                .append("date", LocalDateTime.now().toString())
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

    public static Response getMessageByPhone(String fromPhone, String toId) {

        Document user = getUserByPhone(fromPhone);
        ArrayList<Document> messages = (ArrayList<Document>) user.get("messages");
        if(messages==null)return null;
        for (Document msg : messages) {
            if (msg.get("id").equals(toId)) {
                System.out.println(msg.toJson());
                return  new Gson().fromJson(msg.toJson(), Response.class);
            }
        }
        return null;
    }

    public static List<UserDTO> getUserList() {
        List<UserDTO> userList = new ArrayList<>();
        MongoCollection<Document> users = BaseDatabase.getDatabase().getCollection("users");
        for (var user : users.find()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setPhone(user.get("phone", String.class));
            userDTO.setFullName(user.get("firstname", String.class) + "  " + user.get("lastname", String.class));
            userDTO.setId(user.getObjectId("_id").toString());
            userList.add(userDTO);
        }

        return userList;
    }


}
