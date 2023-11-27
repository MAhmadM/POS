package BusinessLayer;
import DAO.ProductDAO;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;

import java.util.List;
@Entity("products")
public class Product {
    @Id
    private String code;
    private String name;
    private String description;
    private int stockQuantity;
    private double price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


    public boolean updateStock(int num){
        this.stockQuantity = this.stockQuantity - num;
        productDAO.updateStockProduct(this);
        return true;
    }

    public int getCurrentPrice(){

        return 0;
    }
    @Transient
    ProductDAO productDAO=new ProductDAO();

}
