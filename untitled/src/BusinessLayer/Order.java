package BusinessLayer;

import DAO.OrderDAO;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Transient;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity("orders")
public class Order extends ItemContainer{
    private String customer;
    private Instant time;

    public boolean cancel(){
        return true;
    }

    public boolean generateInvoice(){
        return true;
    }
    @Override
    public boolean add(Item item){
        itemDAO.createItem(item);
        items.add(item);
        return true;
    }
    @Override
    public double total() {
        double totalPrice = 0.0;

        List<Item> items = this.getItems();

        for (Item item : items) {
            totalPrice += item.total();
        }

        return totalPrice;
    }

    @Override
    public boolean remove(Item item){
        orderDAO.deleteItemOrder(item);
        return true;
    }
    @Override
    public boolean update(Item item, int quantity){
        itemDAO.updateQuantity(item,quantity);
        return true;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime() {
        this.time = Instant.now();
    }
    @Transient
    OrderDAO orderDAO=new OrderDAO();
    public boolean save(){
        orderDAO.createOrder(this);
        return true;
    }
    public List<Order> getAllOrders()
    {
        return orderDAO.GetALlOrders();
    }

    // Get orders for the current day
    public List<Order> getOrdersForCurrentDay() {
        LocalDate today = LocalDate.now();
        Instant startOfDay = today.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfDay = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return orderDAO.getOrdersWithinTimeFrame(startOfDay, endOfDay);
    }

    // Get orders for the current week
    public List<Order> getOrdersForCurrentWeek() {
        LocalDate startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY);
        Instant startOfTheWeek = startOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfTheWeek = startOfTheWeek.plus(7, ChronoUnit.DAYS);
        return orderDAO.getOrdersWithinTimeFrame(startOfTheWeek, endOfTheWeek);
    }

    // Get orders for the current month
    public List<Order> getOrdersForCurrentMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDate startOfMonth = currentMonth.atDay(1);
        Instant startOfTheMonth = startOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfTheMonth = currentMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant();
        return orderDAO.getOrdersWithinTimeFrame(startOfTheMonth, endOfTheMonth);
    }

    // Get orders for a specified year and month
    public List<Order> getOrdersForYearAndMonth(int year, Month month) {
        YearMonth specifiedMonth = YearMonth.of(year, month);
        LocalDate startOfSpecifiedMonth = specifiedMonth.atDay(1);
        Instant start = startOfSpecifiedMonth.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = specifiedMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant();
        return orderDAO.getOrdersWithinTimeFrame(start, end);
    }
}


