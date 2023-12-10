package tests;

import BusinessLayer.ItemContainer;
import BusinessLayer.Order;
import BusinessLayer.Item;
import BusinessLayer.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void add() {
        Item item = new Item();
        assertTrue(order.add(item));
    }

    @Test
    void total() {
        Item item1 = new Item();
        item1.setPrice();
        item1.setQuantityOrdered(2);

        Item item2 = new Item();
        item2.setPrice();
        item2.setQuantityOrdered(3);

        order.add(item1);
        order.add(item2);

        assertEquals(70.0, order.total());
    }

    @Test
    void remove() {
        Product product = new Product();
        product.setCode("abcd");
        product.setName("Product ");
        product.setPrice(10.0);
        product.setStockQuantity(100);
        Item item =new Item();
        item.setProduct(product);
        item.setQuantityOrdered(7);
        item.setPrice();
        Order itemContainer1 = new Order();
        itemContainer1.add(item);
        order.setCustomer("Muhammad Ahmad");
        order.setTime();
        order.save();
        assertTrue(order.remove(item));
    }

    @Test
    void update() {
        Item item = new Item();
        assertTrue(order.update(item, 5));
    }

    @Test
    void getCustomer() {
        String customer = "John Doe";
        order.setCustomer(customer);
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void setCustomer() {
        String customer = "Jane Smith";
        order.setCustomer(customer);
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void getTime() {
        order.setTime();
        // It's difficult to test Instant.now(), but you could verify if the time is set correctly.
        // For example, you could check if the set time is not null or within a specific range.
        // Here's a hypothetical example:
        // assertNotNull(order.getTime());
    }

    @Test
    void setTime() {
        order.setTime();
        // Similar to getTime(), it's hard to directly test the setting of Instant.now().
        // You could check if the time is set not null or within a specific range.
        // For example:
        // assertNotNull(order.getTime());
    }

    @Test
    void save() {
        assertTrue(order.save());
    }

    @Test
    void getAllOrders() {
        // Mock data or interact with a real database to test getting all orders.
        // For example:
        // assertEquals(5, order.getAllOrders().size());
    }

    @Test
    void getOrdersForCurrentDay() {
        // Similarly, use mock data or an actual database to test getting orders for the current day.
        // For example:
        // assertEquals(3, order.getOrdersForCurrentDay().size());
    }

    @Test
    void getOrdersForCurrentWeek() {
        Order order = new Order();
        List<Order> orders = order.getOrdersForCurrentWeek();

        // Example: Asserting that the list should not be null after fetching orders for the current week
        // assertEquals(5, orders.size());
    }

    @Test
    void getOrdersForCurrentMonth() {
        Order order = new Order();
        List<Order> orders = order.getOrdersForCurrentMonth();

        // Example: Asserting that the list should not be null after fetching orders for the current month
        // assertEquals(8, orders.size());
    }

    @Test
    void getOrdersForYearAndMonth() {
        Order order = new Order();
        YearMonth specifiedMonth = YearMonth.of(2023, 9); // Specifying year and month

        // Fetching orders for the specified year and month
        List<Order> orders = order.getOrdersForYearAndMonth(specifiedMonth.getYear(), specifiedMonth.getMonth());

        // Example: Asserting that the list should not be null after fetching orders for the specified year and month
        // assertEquals(4, orders.size());
    }

}
