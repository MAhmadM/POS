package DAO;

import BusinessLayer.Order;
import BusinessLayer.Item;
import BusinessLayer.ItemContainer;
import BusinessLayer.Product;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

public class OrderDAO {
    private final Datastore datastore;

    public OrderDAO() {
        this.datastore = MongoDBConnection.getDatastore();
    }

    public void createOrder(Order order) {
        datastore.save(order);
    }

    public Order findOrderById(ObjectId id) {
        Query<Order> query = datastore.createQuery(Order.class)
                .field("_id").equal(id);
        return query.get();
    }

//    public void updateOrder(Order order) {
//        
//        datastore.save(order);
//    }
    public void deleteItemOrder(Item item) {
        datastore.delete(item);
        Query<Order> query = datastore.createQuery(Order.class)
                .field("items").hasThisOne(item);
        UpdateOperations<Order> ops = datastore.createUpdateOperations(Order.class)
                .removeAll("items", item);
        datastore.update(query, ops);
    }
    public void deleteOrder(Order order) {
        datastore.delete(order);
    }

    public Order getOrder(Order order) {
        Order container = findOrderById( order.getId());
        return order;
    }


    private void displaySubcategoriesAndProducts(Order container) {
        StringBuilder indent = new StringBuilder();

        for (Item item : container.getItems()) {
            System.out.println(indent + "Item: " + item.getProduct().getName());
            System.out.println(indent + "Quanity Ordered: " + item.getQuantityOrdered());
        }
    }
    public List<Order> GetALlOrders() {
        Query<Order> query = datastore.createQuery(Order.class).order("_id");
        return query.asList();
    }

}

