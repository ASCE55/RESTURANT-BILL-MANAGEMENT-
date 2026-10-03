import java.util.ArrayList;
import java.util.List;

public class AppData {
    public static final List<Restaurant> restaurants = new ArrayList<>();
    public static final Cart cart = new Cart();

    static {
        createSampleData();
    }

    private static void createSampleData() {
        Restaurant spicyHub = new Restaurant("Spicy Hub", "Indian", 4.6);
        spicyHub.addFood(new Food(1, "Chicken Biryani", "Biryani", 160, "Aromatic basmati rice with spicy chicken."));
        spicyHub.addFood(new Food(2, "Chicken Fried Rice", "Rice", 140, "Indo-Chinese fried rice with chicken."));
        spicyHub.addFood(new Food(3, "Paneer Butter Masala", "Main Course", 150, "Creamy paneer curry with rich spices."));

        Restaurant burgerPoint = new Restaurant("Burger Point", "Fast Food", 4.5);
        burgerPoint.addFood(new Food(4, "Chicken Burger", "Burger", 120, "Crispy chicken patty with fresh vegetables."));
        burgerPoint.addFood(new Food(5, "Veg Burger", "Burger", 95, "Crispy vegetable patty with special sauce."));
        burgerPoint.addFood(new Food(6, "French Fries", "Sides", 90, "Golden crispy salted fries."));

        Restaurant pizzaCorner = new Restaurant("Pizza Corner", "Pizza", 4.4);
        pizzaCorner.addFood(new Food(7, "Chicken Pizza", "Pizza", 180, "Cheesy pizza topped with chicken."));
        pizzaCorner.addFood(new Food(8, "Veg Pizza", "Pizza", 150, "Loaded vegetable pizza with mozzarella."));
        pizzaCorner.addFood(new Food(9, "Garlic Bread", "Sides", 80, "Toasted garlic bread with herbs."));

        Restaurant sushiHouse = new Restaurant("Sushi House", "Asian", 4.8);
        sushiHouse.addFood(new Food(10, "Veg Sushi Roll", "Sushi", 180, "Fresh vegetable sushi roll."));
        sushiHouse.addFood(new Food(11, "Chicken Sushi Roll", "Sushi", 210, "Chicken-filled sushi roll."));
        sushiHouse.addFood(new Food(12, "Fresh Juice", "Drinks", 70, "Chilled seasonal fruit juice."));

        restaurants.add(spicyHub);
        restaurants.add(burgerPoint);
        restaurants.add(pizzaCorner);
        restaurants.add(sushiHouse);
    }

    public static List<Food> getAllFoods() {
        List<Food> foods = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            foods.addAll(restaurant.getMenu());
        }
        return foods;
    }

    public static List<Food> searchFoods(String keyword) {
        List<Food> result = new ArrayList<>();
        String search = keyword.toLowerCase();

        for (Food food : getAllFoods()) {
            if (food.getName().toLowerCase().contains(search)
                    || food.getCategory().toLowerCase().contains(search)) {
                result.add(food);
            }
        }
        return result;
    }
}
