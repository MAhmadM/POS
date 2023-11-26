package BusinessLayer;

public abstract class ItemContainer {
    protected String id;

    public boolean add(Item item){
        return true;
    }

    public boolean remove(Item item){
        return true;
    }

    public boolean update(Item item, int quantity){
        return true;
    }

    public int total(){
        return 0;
    }
}
