import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private final JPanel loginPanel;
    private final JPanel imagePanel;
    private final JLabel introLabel;

    private final JLabel mailLabel;
    private final JLabel passLabel;
    private final JTextField mailField;
    private final JPasswordField passField;
    private final JToggleButton showHideButton;
    private JCheckBox rememberBox;
    private JButton forgotButton;
    private final JPanel buttonPanel;
    private final JButton loginButton;
    private final Color backgroundColor;
    private final ImageIcon showIcon;
    private final ImageIcon hideIcon;
    private Font introFont;
    private Font normalFont;
    private Font smallFont;
    private Font fieldFont;

    public LoginFrame() {
        this.setTitle("Login Window");
        this.setSize(1260, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        backgroundColor = new Color(192, 192, 192);
        introFont = new Font("Century Gothic", Font.BOLD, 25);
        normalFont = new Font("Inter", Font.PLAIN, 16);
        smallFont = new Font("Inter", Font.PLAIN, 14);
        fieldFont = new Font("Inter", Font.PLAIN, 18);

        loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(560, 700));
        loginPanel.setBackground(backgroundColor);

        introLabel = new JLabel("Hello, World!");
        introLabel.setBorder(new EmptyBorder(70, 0, 56, 0));
        introLabel.setFont(introFont);
        loginPanel.add(introLabel);

        mailLabel = new JLabel("Email address");
        mailLabel.setFont(normalFont);
        mailLabel.setPreferredSize(new Dimension(420, 28));
        loginPanel.add(mailLabel);

        mailField = new JTextField();
        mailField.setPreferredSize(new Dimension(420, 56));
        mailField.setFont(fieldFont);
        mailField.setToolTipText("Enter your email address");
        mailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        mailField.setBackground(backgroundColor);
        loginPanel.add(mailField);

        passLabel = new JLabel("Password");
        passLabel.setFont(normalFont);
        passLabel.setPreferredSize(new Dimension(420, 28));
        loginPanel.add(passLabel);

        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(390, 56));
        passField.setFont(fieldFont);
        passField.setToolTipText("Enter your password");
        passField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        passField.setBackground(backgroundColor);
        loginPanel.add(passField);

        showIcon = new ImageIcon("src/images/show.png");
        hideIcon = new ImageIcon("src/images/hide.png");

        showHideButton = new JToggleButton(showIcon);
        showHideButton.setPreferredSize(new Dimension(25, 56));
        showHideButton.setFocusable(false);
        showHideButton.setBackground(backgroundColor);
        UIManager.put("ToggleButton.select", backgroundColor);
        SwingUtilities.updateComponentTreeUI(showHideButton);
        showHideButton.setBorderPainted(false);
        showHideButton.addActionListener(this);
        loginPanel.add(showHideButton);

        rememberBox = new JCheckBox("Remember me");
        rememberBox.setPreferredSize(new Dimension(420, 40));
        rememberBox.setFocusable(false);
        rememberBox.setIcon(new ImageIcon("src/images/checkbox_blank.png"));
        rememberBox.setSelectedIcon(new ImageIcon("src/images/checkbox_selected.png"));
        rememberBox.setFont(smallFont);
        rememberBox.setBackground(backgroundColor);
        loginPanel.add(rememberBox);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setBorder(new EmptyBorder(28, 0, 28, 0));
        buttonPanel.setPreferredSize(new Dimension(420, 110));
        buttonPanel.setBackground(backgroundColor);

        loginButton = new JButton("Log in");
        loginButton.setFont(normalFont);
        loginButton.setFocusable(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(loginButton);
        loginPanel.add(buttonPanel);

        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(700, 700));

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
        }
    }
}
