package database.task;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import database.base.BaseDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


import javax.print.Doc;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;

public class TaskDateBase implements BaseDatabase {

//    @Override
//    public List<Document> getObjectList() {
//        return null;
//    }
//
//    public boolean addTaskToUser(Task task, String userId) {
//        MongoDatabase database = getDatabase();
//        MongoCollection<Document> users = database.getCollection("users");
//        Document user = database.getCollection("users").
//                find(eq("_id", new ObjectId(userId))).first();
//        user.remove("tasks");
//
//
//        Document taskDocument = MongoUtils.getTaskDocument(task);
//
//        return true;
//    }
//
//    @Override
//    public boolean addObject(Task task) {
//        MongoDatabase database = getDatabase();
//        MongoCollection<Document> tasks = database.getCollection("tasks");
//        Document document = MongoUtils.getTaskDocument(task);
//        tasks.insertOne(document);
//        return true;
//
//
//    }
//
//
//    @Override
//    public Document getObject(String id) {
//        MongoDatabase database = getDatabase();
//        Document user = database.getCollection("tasks").find(eq("_id", new ObjectId(id))).first();
//
//        return user;
//    }
//
//    @Override
//    public boolean deleteObject(String id) {
//        return false;
//    }
//
//    private int countVote(List<Document> reactions) {
//
//        int votes = 0;
//        for (var reac : reactions) {
//            if ((int) reac.get("reaction") == 1)
//                votes++;
//        }
//
//        return votes;
//    }
//
//
//    public boolean addReaction(Document user, String taskId, ReactionType reactionType) {
//
//        MongoCollection<Document> tasks = getDatabase().getCollection("tasks");
//        Document task = tasks.find(eq("_id", taskId)).first();
//
//
//        Bson filter = and(eq("_id", new ObjectId(taskId)), eq("votes.userId", user.get("_id").toString()));
//        Document doc = tasks.find(filter).first();
//
//        if (doc == null) {
//            Document document = new Document();
//            document.append("name", user.get("name"))
//                    .append("userId", user.get("_id").toString())
//                    .append("reaction", reactionType);
//            Bson updates = Updates.push("votes", document);
//            tasks.updateOne(task, updates);
//            return true;
//
//        }
//
//        String oldReaction = doc.get("reaction").toString();
//        if (oldReaction.equals(ReactionType.DISLIKE.name()) && reactionType == ReactionType.LIKE) {
//            Bson update = set("votes.$.reaction", reactionType.name());
//            UpdateResult result = tasks.updateOne(filter, update);
//            return true;
//
//        } else if (oldReaction.equals(ReactionType.LIKE.name()) && reactionType == ReactionType.DISLIKE) {
//            Bson update = set("votes.$.reaction", reactionType.name());
//            UpdateResult result = tasks.updateOne(filter, update);
//            return true;
//
//        }else {
//            // remove
//            return  true;
//
//        }
//
//
//
//    }


}
