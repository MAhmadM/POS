package BusinessLayer;

import java.sql.Timestamp;

public class Order extends ItemContainer{
    private String customer;
    private Timestamp time;

    public boolean cancel(){
        return true;
    }

    public boolean generateInvoice(){
        return true;
    }


}
