package steekezexchange.yaid.com.steekezexchange.entity;

/**
 * Created by ikhokhlov on 5/15/2015.
 */
public class SteekezItem {

    private int id, quantity;
    private String name;

    public SteekezItem() {
        this(0,0,"");
    }

    public SteekezItem(int id, int quantity, String name) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SteekezItem)
        {
            if(((SteekezItem)o).getId()==id)
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
