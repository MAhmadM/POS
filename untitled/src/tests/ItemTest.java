package tests;

import BusinessLayer.Item;
import BusinessLayer.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {
    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item();
    }

    @Test
    void getProduct() {
        Product product = new Product();
        item.setProduct(product);
        assertEquals(product, item.getProduct());
    }

    @Test
    void setProduct() {
        Product product = new Product();
        item.setProduct(product);
        assertEquals(product, item.getProduct());
    }

    @Test
    void getQuantityOrdered() {
        int quantity = 5;
        item.setQuantityOrdered(quantity);
        assertEquals(quantity, item.getQuantityOrdered());
    }

    @Test
    void setQuantityOrdered() {
        int quantity = 10;
        item.setQuantityOrdered(quantity);
        assertEquals(quantity, item.getQuantityOrdered());
    }

    @Test
    void total() {
        item.setQuantityOrdered(5);
        Product product = new Product();
        product.setPrice(10.0);
        item.setProduct(product);
        assertEquals(50.0, item.total());
    }

    @Test
    void getItemPrice() {
        Product product = new Product();
        product.setPrice(15.0);
        item.setProduct(product);
        assertEquals(15.0, item.getItemPrice());
    }

    @Test
    void setPrice() {
        Product product = new Product();
        product.setPrice(20.0);
        item.setProduct(product);
        item.setPrice();
        assertEquals(20.0, item.getItemPrice());
    }

    @Test
    void setTotalPrice() {
        Product product = new Product();
        product.setPrice(25.0);
        item.setProduct(product);
        item.setQuantityOrdered(3);
        item.setTotalPrice();
        assertEquals(75.0, item.getItemPrice());
    }
}
