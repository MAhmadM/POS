package tests;

import BusinessLayer.Product;
import DAO.ProductDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private ProductDAO productDAO;
    private Product product;

    @BeforeEach
    void setUp() {
        productDAO = new ProductDAO();
        product = new Product();
        product.setCode("P001");
        product.setName("TestProduct");
        product.setDescription("Test description");
        product.setPrice(20.0);
        product.setStockQuantity(10);
    }

    @Test
    void testGetExpiryDate() {
        assertNotNull(product.getExpirydate());
    }

    @Test
    void testGetCode() {
        assertEquals("P001", product.getCode());
    }

    @Test
    void testSetCode() {
        product.setCode("P002");
        assertEquals("P002", product.getCode());
    }

    @Test
    void testGetName() {
        assertEquals("TestProduct", product.getName());
    }

    @Test
    void testSetName() {
        product.setName("NewTestProduct");
        assertEquals("NewTestProduct", product.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("Test description", product.getDescription());
    }

    @Test
    void testSetDescription() {
        product.setDescription("New description");
        assertEquals("New description", product.getDescription());
    }

    @Test
    void testGetPrice() {
        assertEquals(20.0, product.getPrice());
    }

    @Test
    void testSetPrice() {
        product.setPrice(30.0);
        assertEquals(30.0, product.getPrice());
    }

    @Test
    void testGetStockQuantity() {
        assertEquals(10, product.getStockQuantity());
    }

    @Test
    void testSetStockQuantity() {
        product.setStockQuantity(20);
        assertEquals(20, product.getStockQuantity());
    }

    @Test
    void testCheckStock() {
        product.setStockQuantity(20);
        assertTrue(product.checkStock());
        assertEquals(20, product.getStockQuantity());
    }

    @Test
    void testUpdateStock() {
        product.updateStock(5);
        assertEquals(5, product.getStockQuantity());
        // Ensure the stock is updated in the database
    }
    @Test
    void testCopy() {
        Product copiedProduct = product.copy();
        assertEquals(product.getCode(), copiedProduct.getCode());
        assertEquals(product.getName(), copiedProduct.getName());
        assertEquals(product.getPrice(), copiedProduct.getPrice());
        assertEquals(product.getDescription(), copiedProduct.getDescription());
        assertEquals(product.getStockQuantity(), copiedProduct.getStockQuantity());
    }

    @Test
    void testGetAllProducts() {
        // Assuming the getAllProducts() method returns a non-null list
        assertNotNull(product.getAllProducts());
        // Add additional assertions as needed for the returned product list
    }

    @Test
    void testGetCategoryofProduct() {
        // Assuming a category name is retrieved for the given product
        assertNotNull(product.getCategoryofProduct());
        // Add specific assertions to validate the retrieved category
    }

    @Test
    void testGetCurrentPrice() {
        // Assuming the getCurrentPrice() method returns a non-null value
        assertNotNull(product.getCurrentPrice());
        // Add assertions to validate the returned price
    }
}
