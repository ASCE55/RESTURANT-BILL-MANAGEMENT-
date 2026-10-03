import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        UITheme.setupFrame(this, "Restaurant Ordering System - Login", 950, 620);
        buildUI();
    }

    private void buildUI() {
        JPanel root = new JPanel(new GridLayout(1, 2));
        root.setBackground(UITheme.BACKGROUND);

        JPanel left = new JPanel();
        left.setBackground(UITheme.DARK);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(new EmptyBorder(100, 60, 100, 60));

        JLabel logo = new JLabel("🍽");
        logo.setFont(new Font("SansSerif", Font.PLAIN, 60));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel brand = new JLabel("FOOD RESTAURANT");
        brand.setFont(new Font("SansSerif", Font.BOLD, 30));
        brand.setForeground(Color.WHITE);
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tagline = new JLabel("<html>Simple ordering system<br>built with Core Java + Swing</html>");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tagline.setForeground(new Color(215, 218, 224));
        tagline.setAlignmentX(Component.LEFT_ALIGNMENT);

        left.add(logo);
        left.add(Box.createVerticalStrut(20));
        left.add(brand);
        left.add(Box.createVerticalStrut(12));
        left.add(tagline);

        JPanel right = new JPanel();
        right.setBackground(Color.WHITE);
        right.setBorder(new EmptyBorder(90, 70, 90, 70));
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        JLabel welcome = UITheme.title("Welcome Back", 28);
        JLabel info = UITheme.muted("Login to continue to your restaurant");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));

        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginButton = UITheme.button("LOGIN");
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.addActionListener(e -> login());

        right.add(welcome);
        right.add(Box.createVerticalStrut(8));
        right.add(info);
        right.add(Box.createVerticalStrut(35));
        right.add(usernameField);
        right.add(Box.createVerticalStrut(18));
        right.add(passwordField);
        right.add(Box.createVerticalStrut(25));
        right.add(loginButton);
        right.add(Box.createVerticalStrut(15));

        JLabel demo = UITheme.muted("Demo login: admin / 1234");
        right.add(demo);

        root.add(left);
        root.add(right);
        setContentPane(root);

        getRootPane().setDefaultButton(loginButton);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("1234")) {
            dispose();
            new HomeFrame(username).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.\nUse admin / 1234 for the demo.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
