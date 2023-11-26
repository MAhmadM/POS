package BusinessLayer;

public class Product {
    private String code;
    private String name;
    private String description;
    private int quantityOrdered;
    private int price;

    public boolean updateStock(){
        return true;
    }

    public int getCurrentPrice(){
        return 0;
    }

}
