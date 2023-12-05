package BusinessLayer;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity("products")
public class Product {
    @Id
    private String code;
    private String name;
    private String description;
    private int stockQuantity;
    private double price;
    private LocalDate expirydate;
    public Product(){
        LocalDate currentDate = LocalDate.now();
        expirydate = currentDate.plusMonths(3);
    }
    public LocalDate getExpirydate() {
        return expirydate;
    }

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

    public boolean checkStock(){
        if(this.stockQuantity<=0) {
            Product P = new Product();
            P.setCode(this.getCode());
            P.setName(this.getName());
            P.setStockQuantity(20);
            P.setPrice(this.getPrice());
            P.setDescription(this.getDescription());
            productDAO.createProduct(P);
            productDAO.deleteProduct(this);
        }
        return true;
    }
    public boolean checkExpiryDate(){
        if(this.expirydate.isBefore(LocalDate.now())) {
            Product P = new Product();
            P.setCode(this.getCode());
            P.setName(this.getName());
            P.setStockQuantity(20);
            P.setPrice(this.getPrice());
            P.setDescription(this.getDescription());
            productDAO.createProduct(P);
            productDAO.deleteProduct(this);
        }
        return true;
    }
    public boolean updateStock(int num){
        if(this.stockQuantity-num>=0) {
            this.stockQuantity = this.stockQuantity - num;
            productDAO.updateStockProduct(this);
        }
        return true;
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public String getCategoryofProduct(){
        return productDAO.getCategoryofProduct(categoryDAO.findCategoryByName("Main Category"),this);
    }
    public void display()
    {
        System.out.println( "Product: " + this.getName());
        System.out.println( "Product: " + this.getCode());
        System.out.println( "Product: " + this.getPrice());
        System.out.println( "Product: " + this.getStockQuantity());
    }

    public Product copy()
    {
        Product p = new Product();
        p.setCode(this.code);
        p.setName(this.name);
        p.setPrice(this.price);
        p.setDescription(this.description);
        p.setStockQuantity(this.stockQuantity);
        return p;
    }

    public int getCurrentPrice(){

        return 0;
    }
    @Transient
    ProductDAO productDAO=new ProductDAO();
    @Transient
    CategoryDAO categoryDAO=new CategoryDAO();

    public void updateProduct(Product change) {
        productDAO.updateProduct(change);
    }

    public void delete() {
        productDAO.deleteProduct(this);
    }
}
