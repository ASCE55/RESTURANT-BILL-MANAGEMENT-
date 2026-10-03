import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class HomeFrame extends JFrame {
    private final String username;
    private JPanel contentPanel;
    private JLabel cartCountLabel;

    public HomeFrame(String username) {
        this.username = username;
        UITheme.setupFrame(this, "Food Restaurant", 1200, 760);
        buildUI();
    }

    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(UITheme.BACKGROUND);

        root.add(createHeader(), BorderLayout.NORTH);
        root.add(createSidebar(), BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(UITheme.BACKGROUND);
        contentPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        showHome();

        root.add(contentPanel, BorderLayout.CENTER);
        setContentPane(root);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(15, 22, 15, 22));

        JLabel logo = UITheme.title("🍽  FOOD RESTAURANT", 21);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        right.setOpaque(false);

        JLabel user = new JLabel("Hello, " + username);
        user.setFont(new Font("SansSerif", Font.BOLD, 14));

        cartCountLabel = new JLabel();
        updateCartCount();

        JButton cartButton = UITheme.button("🛒 CART");
        cartButton.addActionListener(e -> showCart());

        right.add(user);
        right.add(cartCountLabel);
        right.add(cartButton);

        header.add(logo, BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);

        return header;
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(190, 0));
        sidebar.setBackground(UITheme.DARK);
        sidebar.setBorder(new EmptyBorder(25, 15, 25, 15));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        addNavButton(sidebar, "🏠  Home", e -> showHome());
        addNavButton(sidebar, "🍕  Menu", e -> showMenu());
        addNavButton(sidebar, "🔎  Search", e -> showSearch());
        addNavButton(sidebar, "🛒  Cart", e -> showCart());
        addNavButton(sidebar, "📦  Orders", e -> showOrders());

        sidebar.add(Box.createVerticalGlue());

        JButton logout = new JButton("↩  Logout");
        logout.setAlignmentX(Component.LEFT_ALIGNMENT);
        logout.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        logout.setFocusPainted(false);
        logout.setForeground(Color.WHITE);
        logout.setBackground(new Color(55, 58, 65));
        logout.setBorder(new EmptyBorder(10, 15, 10, 15));
        logout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        sidebar.add(logout);
        return sidebar;
    }

    private void addNavButton(JPanel sidebar, String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(UITheme.DARK);
        button.setBorder(new EmptyBorder(10, 12, 10, 8));
        button.addActionListener(action);
        sidebar.add(button);
        sidebar.add(Box.createVerticalStrut(8));
    }

    private void showHome() {
        JPanel page = new JPanel();
        page.setBackground(UITheme.BACKGROUND);
        page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));

        JLabel heading = UITheme.title("Delicious food, delivered simply.", 28);
        JLabel sub = UITheme.muted("Choose your favourite food and add it to your cart.");

        page.add(heading);
        page.add(Box.createVerticalStrut(7));
        page.add(sub);
        page.add(Box.createVerticalStrut(25));

        JPanel categories = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        categories.setOpaque(false);
        String[] cats = {"🍕 Pizza", "🍔 Burger", "🍚 Biryani", "🍣 Sushi", "🥤 Drinks"};

        for (String cat : cats) {
            JButton b = new JButton(cat);
            b.setFocusPainted(false);
            b.setBackground(Color.WHITE);
            b.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UITheme.BORDER),
                    new EmptyBorder(10, 16, 10, 16)
            ));
            categories.add(b);
        }

        page.add(categories);
        page.add(Box.createVerticalStrut(25));

        JLabel popular = UITheme.title("Popular Restaurants", 21);
        page.add(popular);
        page.add(Box.createVerticalStrut(12));

        JPanel restaurantGrid = new JPanel(new GridLayout(0, 2, 15, 15));
        restaurantGrid.setOpaque(false);

        for (Restaurant restaurant : AppData.restaurants) {
            restaurantGrid.add(createRestaurantCard(restaurant));
        }

        page.add(restaurantGrid);
        contentPanel.removeAll();
        contentPanel.add(new JScrollPane(page), BorderLayout.CENTER);
        refresh();
    }

    private JPanel createRestaurantCard(Restaurant restaurant) {
        JPanel card = new JPanel(new BorderLayout(12, 8));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.BORDER),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel icon = new JLabel(getRestaurantIcon(restaurant.getCategory()));
        icon.setFont(new Font("SansSerif", Font.PLAIN, 38));

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));

        text.add(UITheme.title(restaurant.getName(), 17));
        text.add(UITheme.muted(restaurant.getCategory()));
        text.add(UITheme.muted("⭐ " + restaurant.getRating() + "  •  " + restaurant.getMenu().size() + " items"));

        JButton view = UITheme.button("VIEW MENU");
        view.addActionListener(e -> showRestaurantMenu(restaurant));

        card.add(icon, BorderLayout.WEST);
        card.add(text, BorderLayout.CENTER);
        card.add(view, BorderLayout.SOUTH);
        return card;
    }

    private String getRestaurantIcon(String category) {
        if (category.equalsIgnoreCase("Pizza")) return "🍕";
        if (category.equalsIgnoreCase("Fast Food")) return "🍔";
        if (category.equalsIgnoreCase("Asian")) return "🍣";
        return "🍛";
    }

    private void showRestaurantMenu(Restaurant restaurant) {
        showFoodList(restaurant.getName() + " Menu", restaurant.getMenu());
    }

    private void showMenu() {
        showFoodList("All Food", AppData.getAllFoods());
    }

    private void showSearch() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(UITheme.BACKGROUND);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel top = new JPanel(new BorderLayout(10, 10));
        top.setOpaque(false);

        JTextField searchField = new JTextField();
        searchField.setBorder(BorderFactory.createTitledBorder("Search food or category"));
        JButton search = UITheme.button("SEARCH");

        top.add(searchField, BorderLayout.CENTER);
        top.add(search, BorderLayout.EAST);

        panel.add(top, BorderLayout.NORTH);

        JPanel results = new JPanel(new GridLayout(0, 3, 15, 15));
        results.setOpaque(false);

        search.addActionListener(e -> {
            results.removeAll();
            List<Food> foods = AppData.searchFoods(searchField.getText().trim());

            for (Food food : foods) {
                results.add(createFoodCard(food));
            }

            if (foods.isEmpty()) {
                results.add(UITheme.muted("No food found."));
            }

            results.revalidate();
            results.repaint();
        });

        panel.add(new JScrollPane(results), BorderLayout.CENTER);

        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        refresh();
    }

    private void showFoodList(String title, List<Food> foods) {
        JPanel page = new JPanel(new BorderLayout(10, 15));
        page.setBackground(UITheme.BACKGROUND);

        JLabel heading = UITheme.title(title, 25);
        page.add(heading, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 3, 15, 15));
        grid.setOpaque(false);

        for (Food food : foods) {
            grid.add(createFoodCard(food));
        }

        page.add(new JScrollPane(grid), BorderLayout.CENTER);
        contentPanel.removeAll();
        contentPanel.add(page, BorderLayout.CENTER);
        refresh();
    }

    private JPanel createFoodCard(Food food) {
        JPanel card = new JPanel(new BorderLayout(8, 8));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.BORDER),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel image = new JLabel(getFoodEmoji(food.getCategory()), SwingConstants.CENTER);
        image.setFont(new Font("SansSerif", Font.PLAIN, 45));
        image.setPreferredSize(new Dimension(80, 80));

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));

        text.add(UITheme.title(food.getName(), 16));
        text.add(UITheme.muted(food.getCategory()));
        text.add(UITheme.muted("₹" + String.format("%.2f", food.getPrice())));
        text.add(Box.createVerticalStrut(5));
        text.add(UITheme.muted(food.getDescription()));

        JButton add = UITheme.button("+ ADD TO CART");
        add.addActionListener(e -> {
            AppData.cart.addFood(food);
            updateCartCount();
            JOptionPane.showMessageDialog(this, food.getName() + " added to cart.");
        });

        card.add(image, BorderLayout.NORTH);
        card.add(text, BorderLayout.CENTER);
        card.add(add, BorderLayout.SOUTH);
        return card;
    }

    private String getFoodEmoji(String category) {
        if (category.toLowerCase().contains("pizza")) return "🍕";
        if (category.toLowerCase().contains("burger")) return "🍔";
        if (category.toLowerCase().contains("sushi")) return "🍣";
        if (category.toLowerCase().contains("drink")) return "🥤";
        if (category.toLowerCase().contains("biryani")) return "🍛";
        return "🍽";
    }

    private void showCart() {
        JPanel page = new JPanel(new BorderLayout(10, 15));
        page.setBackground(UITheme.BACKGROUND);

        JLabel heading = UITheme.title("Your Cart", 25);
        page.add(heading, BorderLayout.NORTH);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setOpaque(false);
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));

        if (AppData.cart.isEmpty()) {
            itemsPanel.add(UITheme.muted("Your cart is empty. Add some delicious food."));
        } else {
            for (int i = 0; i < AppData.cart.getItems().size(); i++) {
                itemsPanel.add(createCartRow(i));
                itemsPanel.add(Box.createVerticalStrut(10));
            }
        }

        JScrollPane scroll = new JScrollPane(itemsPanel);
        scroll.setBorder(null);
        page.add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(Color.WHITE);
        bottom.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel total = UITheme.title(
                "Total: ₹" + String.format("%.2f", AppData.cart.getGrandTotal()),
                20
        );

        JButton checkout = UITheme.button("CHECKOUT");
        checkout.setEnabled(!AppData.cart.isEmpty());
        checkout.addActionListener(e -> showCheckout());

        bottom.add(total, BorderLayout.WEST);
        bottom.add(checkout, BorderLayout.EAST);
        page.add(bottom, BorderLayout.SOUTH);

        contentPanel.removeAll();
        contentPanel.add(page, BorderLayout.CENTER);
        refresh();
    }

    private JPanel createCartRow(int index) {
        CartItem item = AppData.cart.getItems().get(index);

        JPanel row = new JPanel(new BorderLayout(12, 8));
        row.setBackground(Color.WHITE);
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.BORDER),
                new EmptyBorder(12, 12, 12, 12)
        ));

        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.add(UITheme.title(item.getFood().getName(), 16));
        info.add(UITheme.muted("₹" + String.format("%.2f", item.getFood().getPrice()) +
                " × " + item.getQuantity()));
        info.add(UITheme.muted("Item total: ₹" + String.format("%.2f", item.getTotal())));

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controls.setOpaque(false);

        JButton minus = new JButton("-");
        JButton plus = new JButton("+");
        JButton remove = new JButton("REMOVE");

        minus.addActionListener(e -> {
            AppData.cart.decreaseItem(index);
            showCart();
            updateCartCount();
        });

        plus.addActionListener(e -> {
            AppData.cart.increaseItem(index);
            showCart();
            updateCartCount();
        });

        remove.addActionListener(e -> {
            AppData.cart.removeItem(index);
            showCart();
            updateCartCount();
        });

        controls.add(minus);
        controls.add(plus);
        controls.add(remove);

        row.add(info, BorderLayout.CENTER);
        row.add(controls, BorderLayout.EAST);
        return row;
    }

    private void showCheckout() {
        if (AppData.cart.isEmpty()) {
            return;
        }

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(25, 35, 25, 35));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(UITheme.title("Checkout", 25));
        panel.add(Box.createVerticalStrut(20));

        JTextField nameField = new JTextField(username);
        JTextField phoneField = new JTextField();
        JTextArea addressField = new JTextArea(4, 20);

        nameField.setBorder(BorderFactory.createTitledBorder("Customer Name"));
        phoneField.setBorder(BorderFactory.createTitledBorder("Phone Number"));
        addressField.setBorder(BorderFactory.createTitledBorder("Delivery Address"));

        panel.add(nameField);
        panel.add(Box.createVerticalStrut(12));
        panel.add(phoneField);
        panel.add(Box.createVerticalStrut(12));
        panel.add(new JScrollPane(addressField));
        panel.add(Box.createVerticalStrut(18));
        panel.add(UITheme.title("Payable: ₹" + String.format("%.2f", AppData.cart.getGrandTotal()), 19));

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Checkout",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            if (nameField.getText().trim().isEmpty()
                    || phoneField.getText().trim().isEmpty()
                    || addressField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all checkout fields.");
                return;
            }

            Order order = new Order(nameField.getText().trim(), AppData.cart.getGrandTotal());
            double total = AppData.cart.getGrandTotal();
            AppData.cart.clear();
            updateCartCount();

            JOptionPane.showMessageDialog(
                    this,
                    "Order placed successfully!\n\n" +
                            "Order ID: " + order.getOrderId() + "\n" +
                            "Customer: " + order.getCustomerName() + "\n" +
                            "Total: ₹" + String.format("%.2f", total) + "\n" +
                            "Time: " + order.getFormattedTime(),
                    "Order Confirmed",
                    JOptionPane.INFORMATION_MESSAGE
            );

            showOrders();
        }
    }

    private void showOrders() {
        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(UITheme.BACKGROUND);

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(25, 25, 25, 25));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        card.add(UITheme.title("Orders", 25));
        card.add(Box.createVerticalStrut(20));
        card.add(UITheme.muted("Orders are demonstrated in-memory for this Basic Java project."));
        card.add(Box.createVerticalStrut(12));
        card.add(UITheme.muted("After restarting the application, in-memory cart/order data resets."));

        page.add(card, BorderLayout.NORTH);

        contentPanel.removeAll();
        contentPanel.add(page, BorderLayout.CENTER);
        refresh();
    }

    private void updateCartCount() {
        if (cartCountLabel != null) {
            cartCountLabel.setText("Items: " + AppData.cart.getItemCount());
        }
    }

    private void refresh() {
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
