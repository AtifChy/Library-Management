import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class LoginFrame extends JFrame implements MouseListener {
    private final JPanel loginPanel;
    private final JPanel imagePanel;
    private JPanel introPanel;
    private final JLabel introLabel;
    private JPanel radioPanel;
    private ButtonGroup loginGroup;
    private JRadioButton librarianButton;
    private JRadioButton studentButton;
    private final JLabel nameLabel;
    private final JLabel passLabel;
    private final JTextField nameField;
    private final JPasswordField passField;
    private final JToggleButton showHideButton;
    private final JPanel rememberPanel;
    private final JCheckBox rememberBox;
    private final JButton forgotButton;
    private final JPanel buttonPanel;
    private final JButton loginButton;
    private final JButton signupButton;
    private JPanel naviPanel;
    private JButton backButton;
    private final Color backgroundColor;
    private final ImageIcon showIcon;
    private final ImageIcon hideIcon;
    private final Font introFont;
    private final Font normalFont;
    private final Font smallFont;
    private final Font smallBoldFont;

    private final Font bigFont;
    private final BufferedImage resizedImage;
    private SignupFrame signupFrame;

    public LoginFrame() throws IOException {
        super("LogIn");
        Image iconImage = Toolkit.getDefaultToolkit().getImage("src/images/library.png");
        this.setIconImage(iconImage);
        this.setSize(1260, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        backgroundColor = new Color(220, 220, 225);
        introFont = new Font("Century Gothic", Font.BOLD, 25);
        normalFont = new Font("Inter", Font.PLAIN, 16);
        smallFont = new Font("Inter", Font.PLAIN, 14);
        smallBoldFont = new Font("Inter", Font.BOLD, 14);
        bigFont = new Font("Inter", Font.PLAIN, 18);

        loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(560, 700));
        loginPanel.setBackground(backgroundColor);

        introPanel = new JPanel();
        introPanel.setPreferredSize(new Dimension(560, 130));
        introPanel.setBorder(BorderFactory.createEmptyBorder(60, 0, 20, 0));
        introPanel.setBackground(backgroundColor);

        introLabel = new JLabel("Welcome");
        introLabel.setFont(introFont);
        introPanel.add(introLabel);
        loginPanel.add(introPanel);

        radioPanel = new JPanel();
        radioPanel.setLayout(new BorderLayout());
        radioPanel.setPreferredSize(new Dimension(200, 30));
        radioPanel.setBackground(backgroundColor);

        ImageIcon checkedIcon = new ImageIcon("src/images/radio_checked.png");
        ImageIcon unCheckedIcon = new ImageIcon("src/images/radio_unchecked.png");
        librarianButton = new JRadioButton("Librarian", unCheckedIcon);
        librarianButton.setSelectedIcon(checkedIcon);
        librarianButton.setFont(smallBoldFont);
        librarianButton.setBackground(backgroundColor);
        librarianButton.setFocusable(false);
        studentButton = new JRadioButton("Student", unCheckedIcon);
        studentButton.setSelectedIcon(checkedIcon);
        studentButton.setFont(smallBoldFont);
        studentButton.setBackground(backgroundColor);
        studentButton.setFocusable(false);

        loginGroup = new ButtonGroup();
        loginGroup.add(librarianButton);
        loginGroup.add(studentButton);

        radioPanel.add(librarianButton, BorderLayout.WEST);
        radioPanel.add(studentButton, BorderLayout.EAST);
        loginPanel.add(radioPanel);

        nameLabel = new JLabel("User Name");
        nameLabel.setFont(normalFont);
        nameLabel.setPreferredSize(new Dimension(420, 28));
        loginPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(420, 56));
        nameField.setFont(bigFont);
        nameField.setToolTipText("Enter your user name");
        nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        nameField.setBackground(backgroundColor);
        loginPanel.add(nameField);

        passLabel = new JLabel("Password");
        passLabel.setFont(normalFont);
        passLabel.setPreferredSize(new Dimension(420, 28));
        loginPanel.add(passLabel);

        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(390, 56));
        passField.setFont(bigFont);
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
        showHideButton.setBorderPainted(false);
        showHideButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showHideButton.setUI(new BasicButtonUI() {
            private Color getSelectColor() {
                return backgroundColor;
            }
        });
        showHideButton.addMouseListener(this);
        loginPanel.add(showHideButton);

        rememberPanel = new JPanel();
        rememberPanel.setLayout(new BorderLayout());
        rememberPanel.setPreferredSize(new Dimension(420, 40));
        rememberPanel.setBackground(backgroundColor);

        rememberBox = new JCheckBox("Remember me");
        rememberBox.setFocusable(false);
        rememberBox.setIcon(new ImageIcon("src/images/checkbox_blank.png"));
        rememberBox.setSelectedIcon(new ImageIcon("src/images/checkbox_selected.png"));
        rememberBox.setFont(smallFont);
        rememberBox.setBackground(backgroundColor);
        rememberBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rememberPanel.add(rememberBox, BorderLayout.WEST);

        forgotButton = new JButton("Forgot password?");
        forgotButton.setFont(smallFont);
        forgotButton.setBorderPainted(false);
        forgotButton.setFocusable(false);
        forgotButton.setBackground(backgroundColor);
        forgotButton.setForeground(Color.BLUE);
        forgotButton.setMargin(new Insets(0, 0, 0, 0));
        forgotButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotButton.setUI(new BasicButtonUI() {
            private Color getSelectColor() {
                return backgroundColor;
            }
        });
        forgotButton.addMouseListener(this);
        rememberPanel.add(forgotButton, BorderLayout.EAST);

        loginPanel.add(rememberPanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setBorder(new EmptyBorder(45, 0, 35, 0));
        buttonPanel.setPreferredSize(new Dimension(420, 140));
        buttonPanel.setBackground(backgroundColor);

        loginButton = new JButton("Log in");
        loginButton.setFont(bigFont);
        loginButton.setFocusable(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addMouseListener(this);
        buttonPanel.add(loginButton);
        loginPanel.add(buttonPanel);

        signupButton = new JButton("Don't have an account yet? Sign up");
        signupButton.setPreferredSize(new Dimension(420, 50));
        signupButton.setFont(normalFont);
        signupButton.setBorderPainted(false);
        signupButton.setFocusable(false);
        signupButton.setBackground(backgroundColor);
        signupButton.setMargin(new Insets(0, 0, 0, 0));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.addMouseListener(this);
        signupButton.setUI(new BasicButtonUI() {
            private Color getSelectColor() {
                return backgroundColor;
            }
        });
        loginPanel.add(signupButton, BorderLayout.SOUTH);

        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(700, 700));
        imagePanel.setBackground(Color.WHITE);

        naviPanel = new JPanel();
        naviPanel.setLayout(new BorderLayout());
        naviPanel.setPreferredSize(new Dimension(700, 60));
        naviPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        naviPanel.setBackground(Color.WHITE);

        ImageIcon backIcon = new ImageIcon("src/images/arrow_back.png");
        backButton = new JButton(backIcon);
        backButton.setBackground(Color.WHITE);
        backButton.setFocusable(false);
        backButton.setBorderPainted(false);
        backButton.setToolTipText("Go Back");
        backButton.addMouseListener(this);

        naviPanel.add(backButton, BorderLayout.WEST);
        imagePanel.add(naviPanel);

        resizedImage = Utils.resizeImage("src/images/login.jpg", 450, 450);
        ImageIcon image = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 20));
        imagePanel.add(imageLabel);

        this.add(loginPanel, BorderLayout.EAST);
        this.add(imagePanel, BorderLayout.WEST);

        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == showHideButton) {
            if (showHideButton.isSelected()) {
                passField.setEchoChar((char) 0);
                showHideButton.setIcon(hideIcon);
            } else {
                passField.setEchoChar((char) UIManager.get("PasswordField.echoChar"));
                showHideButton.setIcon(showIcon);
            }
        } else if (e.getSource() == forgotButton) {

        } else if (e.getSource() == loginButton) {
            if (!librarianButton.isSelected() && !studentButton.isSelected()) {
                JLabel errorLabel = new JLabel("Please select either `Librarian` or `Student`.");
                errorLabel.setFont(normalFont);
                JOptionPane.showMessageDialog(
                        this,
                        errorLabel,
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                String name = nameField.getText();
                String password = Arrays.toString(passField.getPassword());
                if (name.isEmpty() || password.isEmpty()) {
                    JLabel warningLabel = new JLabel("Please enter your name and password.");
                    warningLabel.setFont(normalFont);
                    JOptionPane.showMessageDialog(
                            this,
                            warningLabel,
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        } else if (e.getSource() == signupButton) {
            try {
                signupFrame = new SignupFrame();
                this.setVisible(false);
                signupFrame.setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == forgotButton) {
            forgotButton.setForeground(Color.RED);
        } else if (e.getSource() == signupButton) {
            signupButton.setForeground(Color.BLUE);
        } else if (e.getSource() == backButton) {
            backButton.setBackground(Color.LIGHT_GRAY);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == forgotButton) {
            forgotButton.setForeground(Color.BLUE);
        } else if (e.getSource() == signupButton) {
            signupButton.setForeground(Color.BLACK);
        } else if (e.getSource() == backButton) {
            backButton.setBackground(Color.WHITE);
        }
    }
}
