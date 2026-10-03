import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private static int nextOrderId = 1001;

    private final int orderId;
    private final String customerName;
    private final double total;
    private final LocalDateTime orderTime;

    public Order(String customerName, double total) {
        this.orderId = nextOrderId++;
        this.customerName = customerName;
        this.total = total;
        this.orderTime = LocalDateTime.now();
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getTotal() { return total; }
    public LocalDateTime getOrderTime() { return orderTime; }

    public String getFormattedTime() {
        return orderTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
