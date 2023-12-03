package DAO;
import BusinessLayer.Category;
import BusinessLayer.Product;
import com.mongodb.client.MongoClients;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

public class ProductDAO {
    private final Datastore datastore;
    private List<String> categoryNames;

    public ProductDAO() {
        this.datastore = MongoDBConnection.getDatastore();
    }

    public void createProduct(Product product) {
        datastore.save(product);
    }

    public void deleteProduct(Product product) {
        datastore.delete(product);
        Query<Category> query = datastore.createQuery(Category.class)
                .field("products").hasThisOne(product);
        UpdateOperations<Category> ops = datastore.createUpdateOperations(Category.class)
                .removeAll("products", product);
    }

    public Product findProductByName(String name) {
        Query<Product> query = datastore.createQuery(Product.class)
                .field("name").equal(name);
        return query.get();
    }

    public void updateStockProduct(Product product)
    {
        Query<Product> query = datastore.createQuery(Product.class)
                .field("_id").equal(product.getCode());

        UpdateOperations<Product> ops = datastore.createUpdateOperations(Product.class)
                .set("stockQuantity", product.getStockQuantity());
        datastore.update(query, ops);
    }

    public String getCategoryofProduct(Category category, Product P){
        for (Category subcategory : category.getSubcategories()) {
            if(subcategory.getSubcategories().isEmpty()) {
                if(!subcategory.getProducts().isEmpty()){
                    for(Product product : subcategory.getProducts())
                    {
                        if(product.getName()==P.getName())
                            return subcategory.getName();
                    }
                }
            }
        }
        return "";
    }

    public List<Product> getAllProducts() {
        Query<Product> query = datastore.createQuery(Product.class);
        return query.asList();
    }

}
