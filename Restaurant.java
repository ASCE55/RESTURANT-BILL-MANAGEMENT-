import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final String name;
    private final String category;
    private final double rating;
    private final List<Food> menu = new ArrayList<>();

    public Restaurant(String name, String category, double rating) {
        this.name = name;
        this.category = category;
        this.rating = rating;
    }

    public void addFood(Food food) {
        menu.add(food);
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getRating() { return rating; }
    public List<Food> getMenu() { return menu; }
}
