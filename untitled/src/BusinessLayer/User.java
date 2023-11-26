package BusinessLayer;

public class User {
    private String id;
    private String name;
    public boolean logIn(){
        return true;
    }
    public boolean save(){
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
