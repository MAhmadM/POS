package tests;

import BusinessLayer.Category;
import DAO.CategoryDAO;
import BusinessLayer.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    private CategoryDAO categoryDAO;
    private Category category;

    @BeforeEach
    void setUp() {
        categoryDAO = new CategoryDAO();
        category = new Category();
        category.setName("TestCategory");
        category.setDescription("Test description");
    }

    @Test
    void testGetName() {
        assertEquals("TestCategory", category.getName());
    }

    @Test
    void testSetName() {
        category.setName("NewTestCategory");
        assertEquals("NewTestCategory", category.getName());
    }

    @Test
    void testAddSubcategory() {
        Category subcategory = new Category();
        subcategory.setName("Subcategory");
        assertTrue(category.add(subcategory));
        assertTrue(category.getSubcategories().contains(subcategory));
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setName("TestProduct");
        assertTrue(category.add(product));
        assertTrue(category.getProducts().contains(product));
    }

    @Test
    void testRemoveProduct() {
        Product product = new Product();
        product.setName("TestProduct");
        category.add(product);
        assertTrue(category.remove(product));
        assertFalse(category.getProducts().contains(product));
    }

    @Test
    void testRemoveSubcategory() {
        Category subcategory = new Category();
        subcategory.setName("Subcategory");
        category.add(subcategory);
        assertTrue(category.remove(subcategory));
        assertFalse(category.getSubcategories().contains(subcategory));
    }

    @Test
    void testGetSubcategories() {
        Category subcategory1 = new Category();
        subcategory1.setName("Subcategory1");
        Category subcategory2 = new Category();
        subcategory2.setName("Subcategory2");

        category.add(subcategory1);
        category.add(subcategory2);

        List<Category> subcategories = category.getSubcategories();
        assertEquals(2, subcategories.size());
        assertTrue(subcategories.contains(subcategory1));
        assertTrue(subcategories.contains(subcategory2));
    }

    @Test
    void testGetCategories() {
        Category mainCategory = new Category();
        mainCategory.setName("MainCategory");
        category.add(mainCategory);

        List<String> categories = category.getCategories();
        assertTrue(categories.contains("MainCategory"));
    }

    @Test
    void testGetProducts() {
        Product product1 = new Product();
        product1.setName("Product1");
        Product product2 = new Product();
        product2.setName("Product2");

        category.add(product1);
        category.add(product2);

        List<Product> products = category.getProducts();
        assertEquals(2, products.size());
        assertTrue(products.contains(product1));
        assertTrue(products.contains(product2));
    }
}
