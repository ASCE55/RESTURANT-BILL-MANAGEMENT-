public class Food {
    private final int id;
    private final String name;
    private final String category;
    private final double price;
    private final String description;

    public Food(int id, String name, String category, double price, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return name + " - ₹" + String.format("%.2f", price);
    }
}
