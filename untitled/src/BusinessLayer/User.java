package BusinessLayer;

import DAO.CartDAO;
import DAO.UserDAO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

@Entity("user")
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private Role role;
    public User logIn(String name, String password){
        return userDAO.findUserByUsernameAndPassword(name,password);

    }
    public boolean save(){
        userDAO.createUser(this);
        return true;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    @Transient
    UserDAO userDAO=new UserDAO();
}
