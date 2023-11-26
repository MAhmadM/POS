package BusinessLayer;

public class SalesAssistant extends Role{
    public boolean permission(){
        return true;
    }

    public boolean processOrder(){
        return true;
    }
}
