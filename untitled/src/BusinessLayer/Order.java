package BusinessLayer;

import DAO.CartDAO;
import DAO.OrderDAO;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Transient;

import java.time.Instant;
import java.util.List;

@Entity("order")
public class Order extends ItemContainer{
    private String customer;
    private Instant time;

    public boolean cancel(){
        return true;
    }

    public boolean generateInvoice(){
        return true;
    }
    @Override
    public boolean add(Item item){
        itemDAO.createItem(item);
        items.add(item);
        return true;
    }
    @Override
    public double total() {
        double totalPrice = 0.0;

        List<Item> items = this.getItems();

        for (Item item : items) {
            totalPrice += item.total();
        }

        return totalPrice;
    }

    @Override
    public boolean remove(Item item){
        orderDAO.deleteItemOrder(item);
        return true;
    }
    @Override
    public boolean update(Item item, int quantity){
        itemDAO.updateQuantity(item,quantity);
        return true;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime() {
        this.time = Instant.now();
    }
    @Transient
    OrderDAO orderDAO=new OrderDAO();
}
