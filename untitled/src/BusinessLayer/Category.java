package BusinessLayer;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;

import java.util.ArrayList;
import java.util.List;
@Entity("categories")
public class Category {
    @Id
    private ObjectId code;
    private String name;
    private String description;
    @Reference
    private List<Category> subcategories = new ArrayList<>();

    @Reference
    private List<Product> products = new ArrayList<>();
    public ObjectId getCode() {
        return code;
    }

    public void setCode(ObjectId code) {
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

    public Category getCategorybyName(String name)
    {
        Category categoryByName = categoryDAO.findCategoryByName(name);
        return categoryByName;
    }


    public boolean add(Product product){
        products.add(product);
        productDAO.createProduct(product);
        return true;
    }

    public boolean remove(Product product){
        products.removeIf(pro -> pro.getCode().equals(product.getCode()));
        productDAO.deleteProduct(product);
        return true;
    }

    public boolean add(Category category){
        subcategories.add(category);
        categoryDAO.createCategory(category);
        categoryDAO.selfSave(this, category);

        return true;
    }

    public boolean remove(Category category){
        categoryDAO.deleteCategory(category);
        return true;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }
    public List<String> getCategories(){
        return categoryDAO.getSubcategories(categoryDAO.findCategoryByName("Main Category"));
    }
    public List<Product> getProducts() {
        return products;
    }
    @Transient
    ProductDAO productDAO=new ProductDAO();
    @Transient
    CategoryDAO categoryDAO=new CategoryDAO();

}
