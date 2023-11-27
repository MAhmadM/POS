package DAO;

import BusinessLayer.Cart;
import BusinessLayer.Item;
import BusinessLayer.ItemContainer;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

public class CartDAO {
    private final Datastore datastore;

    public CartDAO() {
        this.datastore = MongoDBConnection.getDatastore();
    }

    public void createCart(Cart cart) {
        datastore.save(cart);
    }

    public Cart findCartById(ObjectId id) {
        Query<Cart> query = datastore.createQuery(Cart.class)
                .field("_id").equal(id);
        return query.get();
    }

//    public void updateCart(Cart cart) {
//
//        datastore.save(cart);
//    }
    public void deleteItemCart(Item item) {
        datastore.delete(item);
        Query<Cart> query = datastore.createQuery(Cart.class)
                .field("items").hasThisOne(item);
        UpdateOperations<Cart> ops = datastore.createUpdateOperations(Cart.class)
                .removeAll("items", item);
        datastore.update(query, ops);
    }
    public void deleteCart(Cart cart) {
        datastore.delete(cart);
    }

    public void getCart(Cart cart) {
        System.out.println(cart.getId());
        Cart container = findCartById(cart.getId());
        if (container != null) {
            System.out.println("ItemContainer: " + container.getId());
            displaySubcategoriesAndProducts(container);
        } else {
            System.out.println("Category not found.");
        }
    }

    private void displaySubcategoriesAndProducts(Cart container) {
        StringBuilder indent = new StringBuilder();

        for (Item item : container.getItems()) {
            System.out.println(indent + "Item: " + item.getProduct().getName());
            System.out.println(indent + "Quanity Ordered: " + item.getQuantityOrdered());
        }
    }
}

