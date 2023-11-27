package DAO;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static final String DATABASE_NAME = "POS";
    private static final String MONGO_HOST = "localhost";
    private static final int MONGO_PORT = 27017;

    private static MongoClient mongoClient;
    private static Datastore datastore;

    public static Datastore getDatastore() {
        if (datastore == null) {
            connect();
        }
        return datastore;
    }

    private static void connect() {
        mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
        Morphia morphia = new Morphia();
        morphia.mapPackage("BusinessLayer");
        datastore = morphia.createDatastore(mongoClient, DATABASE_NAME);
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
