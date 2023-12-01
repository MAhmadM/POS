package DAO;
import BusinessLayer.Category;
import BusinessLayer.Product;
import com.mongodb.client.MongoClients;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import DAO.ProductDAO;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private final Datastore datastore;
    ProductDAO productDAO = new ProductDAO();
    public CategoryDAO() {
        this.datastore = MongoDBConnection.getDatastore();
    }

    public void createCategory(Category category) {
        datastore.save(category);
    }

    public Category findCategoryByName(String name) {
        Query<Category> query = datastore.createQuery(Category.class)
                .field("name").equal(name);
        return query.get();
    }

    public void getCategoriesWithSubcategoriesAndProductsByName(String categoryName) {
        Category mainCategory = findCategoryByName(categoryName);
        if (mainCategory != null) {
            System.out.println("Category: " + mainCategory.getName());
            displaySubcategoriesAndProducts(mainCategory, 0);
        } else {
            System.out.println("Category not found.");
        }
    }

    private void displaySubcategoriesAndProducts(Category category, int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("\t");
        }

        for (Category subcategory : category.getSubcategories()) {
            System.out.println(indent + "Subcategory: " + subcategory.getName());
            displaySubcategoriesAndProducts(subcategory, level + 1);
        }

        for (Product product : category.getProducts()) {
            System.out.println(indent + "Product: " + product.getName());
            System.out.println(indent + "Product: " + product.getCode());
            System.out.println(indent + "Product: " + product.getPrice());
            System.out.println(indent + "Product: " + product.getStockQuantity());

        }
    }


    private List<Product> getAllInventory(Category category, int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("\t");
        }
        List<Product> AllProducts = new ArrayList<>();
        for (Category subcategory : category.getSubcategories()) {
            System.out.println(indent + "Subcategory: " + subcategory.getName());
            displaySubcategoriesAndProducts(subcategory, level + 1);
        }

        for (Product product : category.getProducts()) {
            AllProducts.add(product);
        }
        return AllProducts;
    }

    public void deleteCategory(Category category) {
        for (Product product : category.getProducts()) {
            productDAO.deleteProduct(product);
        }
        for (Category subcategory : category.getSubcategories()) {
            deleteCategory(subcategory);
        }
        datastore.delete(category);
        Query<Category> query = datastore.createQuery(Category.class)
                .field("subcategories").hasThisOne(category);
        UpdateOperations<Category> ops = datastore.createUpdateOperations(Category.class)
                .removeAll("subcategories", category);
        datastore.update(query, ops);
    }

}
