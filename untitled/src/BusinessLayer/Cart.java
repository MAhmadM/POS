package BusinessLayer;

import DAO.CartDAO;
import DAO.ItemDAO;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Transient;

import java.util.List;

@Entity("cart")
public class Cart extends ItemContainer{

    public boolean clear(){
        return true;
    }

    public boolean generateOrder(){
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
    public void save() {

    }

    @Override
    public boolean remove(Item item){
        cartDAO.deleteItemCart(item);
        return true;
    }
    @Override
    public boolean update(Item item, int quantity){
        itemDAO.updateQuantity(item,quantity);
        return true;
    }
    @Transient
    CartDAO cartDAO=new CartDAO();

}
