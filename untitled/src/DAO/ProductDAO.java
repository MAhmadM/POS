package DAO;
import BusinessLayer.Category;
import BusinessLayer.Product;
import com.mongodb.client.MongoClients;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;
import java.util.Objects;

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
        datastore.update(query, ops);
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

    public void updateProduct(Product product)
    {
        Query<Product> query = datastore.createQuery(Product.class)
                .field("_id").equal(product.getCode());

        UpdateOperations<Product> ops = datastore.createUpdateOperations(Product.class)
                .set("name", product.getName())
                .set("price", product.getPrice())
                .set("stockQuantity", product.getStockQuantity())
                .set("description", product.getDescription());
        datastore.update(query, ops);
    }

    public String getCategoryofProduct(Category category, Product P) {
        for (Category subcategory : category.getSubcategories()) {
            for (Product product : subcategory.getProducts()) {
                System.out.println(product.getName() + P.getName());
                if (Objects.equals(product.getName(), P.getName())) {
                    System.out.println("hehehe");
                    return subcategory.getName();
                }
            }
            String result = getCategoryofProduct(subcategory, P);
            if (!result.isEmpty()) {
                return result;
            }
        }
        return "";
    }

    public List<Product> getAllProducts() {
        Query<Product> query = datastore.createQuery(Product.class).order("_id");
        return query.asList();
    }

}
