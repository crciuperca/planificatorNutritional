package pojos;

public class FoodQtyPair {
    String name;
    String qty;

    public FoodQtyPair() {
    }

    public FoodQtyPair(String name, String qty) {
        this.name = name;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
