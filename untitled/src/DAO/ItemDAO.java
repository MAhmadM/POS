package DAO;
import BusinessLayer.Item;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

public class ItemDAO {
    private final Datastore datastore;

    public ItemDAO() {
        this.datastore = MongoDBConnection.getDatastore();
    }

    public void createItem(Item item) {
        datastore.save(item);
    }

    public Item findProductByName(String id) {
        Query<Item> query = datastore.createQuery(Item.class)
                .field("_id").equal(id);
        return query.get();
    }

    public void updateQuantity(Item item, int qty) {
        Query<Item> query = datastore.createQuery(Item.class)
                .field("_id").equal(item.getId());

        UpdateOperations<Item> ops = datastore.createUpdateOperations(Item.class)
                .set("quantityOrdered", qty);

        datastore.update(query, ops);
    }
}
