import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public final class UITheme {
    public static final Color BACKGROUND = new Color(247, 248, 250);
    public static final Color CARD = Color.WHITE;
    public static final Color TEXT = new Color(30, 33, 38);
    public static final Color MUTED = new Color(105, 110, 120);
    public static final Color ACCENT = new Color(230, 82, 55);
    public static final Color DARK = new Color(31, 34, 40);
    public static final Color BORDER = new Color(225, 227, 231);

    private UITheme() {}

    public static void setupFrame(JFrame frame, String title, int width, int height) {
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static JButton button(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(ACCENT);
        button.setBorder(new EmptyBorder(11, 18, 11, 18));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static JLabel title(String text, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, size));
        label.setForeground(TEXT);
        return label;
    }

    public static JLabel muted(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        label.setForeground(MUTED);
        return label;
    }
}
