import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private final JPanel loginPanel;
    private final JPanel imagePanel;
    private final JLabel mailLabel;
    private final JLabel passLabel;
    private final JTextField mailField;
    private final JPasswordField passField;
    private final JToggleButton showHideButton;
    private final JLabel introLabel;
    private final JPanel buttonPanel;
    private final JButton loginButton;
    private final Color backgroundColor;
    private final ImageIcon showIcon;
    private final ImageIcon hideIcon;

    public LoginFrame() {
        this.setTitle("Login Window");
        this.setSize(900, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        backgroundColor = new Color(192, 192, 192);

        loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(400, 500));
        loginPanel.setBackground(backgroundColor);

        introLabel = new JLabel("Hello, World!");
        introLabel.setBorder(new EmptyBorder(50, 0, 40, 0));
        introLabel.setFont(new Font("Century Gothic", Font.BOLD, 18));
        loginPanel.add(introLabel);

        mailLabel = new JLabel("Email address");
        mailLabel.setPreferredSize(new Dimension(300, 20));
        // mailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginPanel.add(mailLabel);

        mailField = new JTextField();
        mailField.setPreferredSize(new Dimension(300, 40));
        mailField.setToolTipText("Enter your email address");
        // mailField.setBorder(null);
        // mailField.setBackground(backgroundColor);
        loginPanel.add(mailField);

        passLabel = new JLabel("Password");
        passLabel.setPreferredSize(new Dimension(300, 20));
        // passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginPanel.add(passLabel);

        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(265, 40));
        passField.setToolTipText("Enter your password");
        // passField.setBorder(null);
        // passField.setBackground(backgroundColor);
        loginPanel.add(passField);

        showIcon = new ImageIcon("src/images/show.png");
        hideIcon = new ImageIcon("src/images/hide.png");

        showHideButton = new JToggleButton(showIcon);
        showHideButton.setPreferredSize(new Dimension(30, 40)); // Set button dimensions
        showHideButton.setFocusable(false);
        showHideButton.setBackground(backgroundColor);
        UIManager.put("ToggleButton.select", backgroundColor);
        SwingUtilities.updateComponentTreeUI(showHideButton);
        showHideButton.setBorder(null);
        showHideButton.addActionListener(this);
        loginPanel.add(showHideButton);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        buttonPanel.setPreferredSize(new Dimension(300, 80));
        buttonPanel.setBackground(backgroundColor);

        loginButton = new JButton("Log in");
        loginButton.setFocusable(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(loginButton);
        loginPanel.add(buttonPanel);

        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(500, 500));

        this.add(loginPanel, BorderLayout.WEST);
        this.add(imagePanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showHideButton) {
            if (showHideButton.isSelected()) {
                passField.setEchoChar((char) 0);
                showHideButton.setIcon(hideIcon);
            } else {
                passField.setEchoChar((char) UIManager.get("PasswordField.echoChar"));
                showHideButton.setIcon(showIcon);
            }
            showHideButton.repaint();
        }
    }
}
