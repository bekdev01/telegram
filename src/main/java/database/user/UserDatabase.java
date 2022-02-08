package database.user;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import database.base.BaseDatabase;
import database.utils.MongoUtils;
import model.Message;
import model.User;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;


public class UserDatabase implements BaseDatabase {

    public Document addUser(User user) {

        MongoDatabase database = getDatabase();
        MongoCollection<Document> users = database.getCollection("users");
        Document user1 = users.
                find(Filters.eq("email", user.getEmail())).first();
        if (user1 != null)
            return null;

        Document doc = MongoUtils.getUserDocument(user);
        users.insertOne(doc);

        return doc;

    }

    public Document getUser(String id) {

        return getDatabase().getCollection("users").find(Filters.eq(new ObjectId(id))).first();
    }


    public boolean addMessage(String toId, String fromId, String text) {
        MongoDatabase connection = getDatabase();
        MongoCollection<Document> users = connection.getCollection("users");

        Document fromUser = getUser(fromId);
        Document toUser = getUser(toId);

        Bson filterFrom = and(eq("_id", new ObjectId(fromId)), eq("messages.id", toId));
        Bson filterTo = and(eq("_id", new ObjectId(toId)), eq("messages.id", fromId));

        Document docFrom = users.find(filterFrom).first();
        Document docTo = users.find(filterTo).first();
        if (docFrom == null && docTo == null) {
            Document message = new Document();
            message.append("text", text)
                    .append("date", new Date())
                    .append("isMine", true);
            ArrayList<Document> msg = new ArrayList<>();
            msg.add(message);
            Document document = new Document()
                    .append("id", toId)
                    .append("messageUser", msg);


            Bson updates = Updates.push("messages", document);
            users.updateOne(fromUser, updates);

            message.put("isMine", false);
            msg = new ArrayList<>();
            msg.add(message);
            document = new Document()
                    .append("id", fromId)
                    .append("messageUser", msg);

            updates = Updates.push("messages", document);
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

    public ArrayList<Document> getMessage(String fromId, String toId) {

        MongoCollection<Document> users = getDatabase().getCollection("users");
        Document user = getUser(fromId);
        ArrayList<Document> messages = (ArrayList<Document>) user.get("messages");
        for (var msg : messages) {
            if (msg.get("id").equals(toId)) {
                return  (ArrayList<Document>) msg.get("messageUser");

            }
        }


        return null;
    }


//
//    @Override
//    public Document getObject(String id) {
//        MongoDatabase database = getDatabase();
//        Document user = database.getCollection("users").find(Filters.eq("_id", new ObjectId(id))).first();
//
//        return user;
//
//    }
//
//    @Override
//    public boolean deleteObject(String id) {
//
//        MongoDatabase database = getDatabase();
//        MongoCollection<Document> users = database.getCollection("users");
//        DeleteResult id1 = users.deleteOne(Filters.eq("_id", new ObjectId(id)));
//        System.out.println(id1);
//
//
//        return false;
//    }
//
//    @Override
//    public List<Document> getObjectList() {
//        List<Document> userList = new ArrayList<>();
//        MongoDatabase tm = getDatabase();
//        MongoCollection<Document> users =
//                tm.getCollection("users");
//        for (var doc:users.find()) {
//            userList.add(doc);
//        }
//
//        return userList;
//    }
//
//    public boolean addTaskToUser(Document task, Document user) {
//        MongoDatabase connection = getDatabase();
//        MongoCollection<Document> users = connection.getCollection("users");
//        Bson updates = Updates.combine(Updates.addToSet("tasks", task));
//        users.updateOne(user, updates);
//
//        return true;
//    }


//
//    public static void main(String[] args) {
//        UserDatabase userDatabase = new UserDatabase();
////        User user = new User("mmmm" ,"asa" , "asads ," ,
////                null);
////       userDatabase.addObject(user);
//        MongoDatabase database = userDatabase.getDatabase();
//        MongoCollection<Document> users = database.getCollection("users");
//        for (var doc: users.find()) {
//            System.out.println(doc.get("_id"));
//        }
//
//
//    }

//    public static void main(String[] args) {
//        UserDatabase userDatabase = new UserDatabase();
//        ArrayList<Document> message = userDatabase.getMessage("62014ae137b08d3d00883e32", "6201ea601781a52c21662c41");
////        userDatabase.addUser(new User("forbid","accept", "party","drive"));
//        System.out.println(message);
////        userDatabase.addMessage("6201ea601781a52c21662c41", "62014ae137b08d3d00883e32" , "sd");
//    }


}
