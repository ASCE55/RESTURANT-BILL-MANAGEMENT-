public class CartItem {
    private final Food food;
    private int quantity;

    public CartItem(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public Food getFood() { return food; }
    public int getQuantity() { return quantity; }

    public void increase() {
        quantity++;
    }

    public void decrease() {
        if (quantity > 1) {
            quantity--;
        }
    }

    public double getTotal() {
        return food.getPrice() * quantity;
    }
}
