package BusinessLayer;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("items")
public class Item {
    @Id
    private ObjectId id;
    private int quantityOrdered;
    private double price;
    @Reference
    Product product = new Product();

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public double total() {
        return price;
    }

    public double getItemPrice() {
        return price;
    }

    public void setPrice() {
        this.price = product.getPrice() * quantityOrdered;
    }

    public void setTotalPrice() {
        this.price = product.getPrice() * quantityOrdered;
    }

}
