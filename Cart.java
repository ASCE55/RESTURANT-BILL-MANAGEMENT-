import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void addFood(Food food) {
        for (CartItem item : items) {
            if (item.getFood().getId() == food.getId()) {
                item.increase();
                return;
            }
        }
        items.add(new CartItem(food, 1));
    }

    public void increaseItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.get(index).increase();
        }
    }

    public void decreaseItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.get(index).decrease();
        }
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public double getDeliveryFee() {
        return items.isEmpty() ? 0 : 40;
    }

    public double getGrandTotal() {
        return getSubtotal() + getDeliveryFee();
    }

    public int getItemCount() {
        int count = 0;
        for (CartItem item : items) {
            count += item.getQuantity();
        }
        return count;
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
