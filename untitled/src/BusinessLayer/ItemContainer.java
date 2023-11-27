package BusinessLayer;

//import DAO.ItemContainerDAO;
import DAO.ItemDAO;
import DAO.ProductDAO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;

import java.util.ArrayList;
import java.util.List;


public abstract class ItemContainer {
    @Id
    protected ObjectId id;
    @Reference
    protected List<Item> items = new ArrayList<>();

    public abstract boolean add(Item item);

    public abstract boolean remove(Item item);

    public abstract boolean update(Item item, int quantity);

    public abstract double total();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    @Transient
    ItemDAO itemDAO=new ItemDAO();

}
