package DAO;

import BusinessLayer.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import com.mongodb.MongoClient;

public class UserDAO {
    private final Datastore datastore;

    public UserDAO() {
        this.datastore = MongoDBConnection.getDatastore();
    }

    public void createUser(User user) {
        datastore.save(user);
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        Query<User> query = datastore.createQuery(User.class)
                .field("username").equal(username)
                .field("password").equal(password);
        return query.get();
    }
}

