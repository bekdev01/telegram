package database.base;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


import java.util.List;

public interface BaseDatabase {

    default MongoDatabase getDatabase() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        return mongoClient.getDatabase("telegram");
    }


}
