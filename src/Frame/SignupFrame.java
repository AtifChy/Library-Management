package Frame;

import Extra.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class SignupFrame extends JFrame implements MouseListener {
    private final JPanel introPanel;
    private final JLabel introLabel;
    private final JPanel radioPanel;
    private final JRadioButton librarianButton;
    private final JRadioButton studentButton;
    private final ButtonGroup loginGroup;
    private final JPanel leftPanel;
    private final JPanel naviPanel;
    private final JButton backButton;
    private final JPanel rightPanel;
    private final JTextField nameField;
    private final JTextField mailField;
    private final JTextField idField;
    private final JComboBox<String> genderBox;
    private final JPasswordField passField;
    private final JPasswordField confirmPassField;
    private final JTextField captchaField;
    private final int captchaValue;
    private final BufferedImage resizedImage;
    private LoginFrame loginFrame;
    private final JButton signupButton;
    private final JButton loginButton;

    public SignupFrame() {
        super("SignUp");
        Image iconImage = Toolkit.getDefaultToolkit().getImage("src/images/library.png");
        this.setIconImage(iconImage);
        this.setSize(1260, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        UIManager.put("OptionPane.messageFont", Utils.NORMAL_FONT);
        UIManager.put("OptionPane.buttonFont", Utils.NORMAL_FONT);

        // Start of Left Panel
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(660, 800));
        leftPanel.setBackground(Color.WHITE);

        naviPanel = new JPanel();
        naviPanel.setLayout(new BorderLayout());
        naviPanel.setPreferredSize(new Dimension(60, 60));
        naviPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
        naviPanel.setBackground(Color.WHITE);

        ImageIcon backIcon = new ImageIcon("src/images/arrow_back.png");
        backButton = new JButton(backIcon);
        backButton.setBackground(Color.WHITE);
        backButton.setFocusable(false);
        backButton.setBorderPainted(false);
        backButton.setToolTipText("Go Back");
        backButton.addMouseListener(this);

        naviPanel.add(backButton, BorderLayout.WEST);
        leftPanel.add(naviPanel, BorderLayout.NORTH);

        try {
            resizedImage = Utils.resizeImage("src/images/signup.jpg", 600, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon image = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
        leftPanel.add(imageLabel, BorderLayout.CENTER);
        // End of Left Panel

        // Start of Right Panel
        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        rightPanel.setPreferredSize(new Dimension(600, 800));
        rightPanel.setBackground(Utils.BACKGROUND_COLOR);

        introPanel = new JPanel();
        introPanel.setPreferredSize(new Dimension(600, 130));
        introPanel.setBorder(BorderFactory.createEmptyBorder(60, 0, 20, 0));
        introPanel.setBackground(Utils.BACKGROUND_COLOR);

        introLabel = new JLabel("Create Account");
        introLabel.setFont(Utils.INTRO_FONT);
        introPanel.add(introLabel);
        rightPanel.add(introPanel);

        radioPanel = new JPanel();
        radioPanel.setLayout(new BorderLayout());
        radioPanel.setPreferredSize(new Dimension(200, 30));
        radioPanel.setBackground(Utils.BACKGROUND_COLOR);

        ImageIcon checkedIcon = new ImageIcon("src/images/radio_checked.png");
        ImageIcon unCheckedIcon = new ImageIcon("src/images/radio_unchecked.png");
        librarianButton = new JRadioButton("Librarian", unCheckedIcon);
        librarianButton.setActionCommand("Librarian");
        librarianButton.setSelectedIcon(checkedIcon);
        librarianButton.setFont(Utils.SMALL_BOLD_FONT);
        librarianButton.setBackground(Utils.BACKGROUND_COLOR);
        librarianButton.setFocusable(false);
        studentButton = new JRadioButton("Student", unCheckedIcon);
        studentButton.setActionCommand("Student");
        studentButton.setSelectedIcon(checkedIcon);
        studentButton.setFont(Utils.SMALL_BOLD_FONT);
        studentButton.setBackground(Utils.BACKGROUND_COLOR);
        studentButton.setFocusable(false);

        loginGroup = new ButtonGroup();
        loginGroup.add(librarianButton);
        loginGroup.add(studentButton);

        radioPanel.add(librarianButton, BorderLayout.WEST);
        radioPanel.add(studentButton, BorderLayout.EAST);
        rightPanel.add(radioPanel);

        JLabel nameLabel = new JLabel("User Name");
        nameLabel.setFont(Utils.NORMAL_FONT);
        nameLabel.setPreferredSize(new Dimension(420, 30));
        rightPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(Utils.BIG_FONT);
        nameField.setPreferredSize(new Dimension(420, 50));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        rightPanel.add(nameField);

        JLabel mailLabel = new JLabel("Email Address");
        mailLabel.setFont(Utils.NORMAL_FONT);
        mailLabel.setPreferredSize(new Dimension(420, 30));
        rightPanel.add(mailLabel);

        mailField = new JTextField();
        mailField.setFont(Utils.BIG_FONT);
        mailField.setPreferredSize(new Dimension(420, 50));
        mailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        rightPanel.add(mailField);

        JPanel optionalPanel = new JPanel();
        optionalPanel.setLayout(new GridLayout(2, 2, 10, 0));
        optionalPanel.setPreferredSize(new Dimension(420, 80));
        optionalPanel.setBackground(Utils.BACKGROUND_COLOR);

        JLabel idLabel = new JLabel("ID");
        idLabel.setFont(Utils.SMALL_FONT);
        optionalPanel.add(idLabel);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(Utils.SMALL_FONT);
        optionalPanel.add(genderLabel);

        idField = new JTextField();
        idField.setFont(Utils.NORMAL_FONT);
        idField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        optionalPanel.add(idField);

        String[] gender = {"Male", "Female", "Other"};
        genderBox = new JComboBox<>(gender);
        genderBox.setFont(Utils.NORMAL_FONT);
        optionalPanel.add(genderBox);

        rightPanel.add(optionalPanel);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new GridLayout(2, 2, 10, 0));
        passwordPanel.setPreferredSize(new Dimension(420, 80));
        passwordPanel.setBackground(Utils.BACKGROUND_COLOR);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(Utils.SMALL_FONT);
        passwordPanel.add(passLabel);

        JLabel confirmPassLabel = new JLabel("Confirm Password");
        confirmPassLabel.setFont(Utils.SMALL_FONT);
        passwordPanel.add(confirmPassLabel);

        passField = new JPasswordField();
        passField.setFont(Utils.NORMAL_FONT);
        passField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        passwordPanel.add(passField);

        confirmPassField = new JPasswordField();
        confirmPassField.setFont(Utils.NORMAL_FONT);
        confirmPassField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        passwordPanel.add(confirmPassField);

        rightPanel.add(passwordPanel);

        JLabel captchaLabel = new JLabel("Captcha");
        captchaLabel.setFont(Utils.SMALL_FONT);
        captchaLabel.setPreferredSize(new Dimension(420, 25));
        rightPanel.add(captchaLabel);

        JPanel captchaPanel = new JPanel();
        captchaPanel.setPreferredSize(new Dimension(425, 50));
        captchaPanel.setBackground(Utils.BACKGROUND_COLOR);

        Random random = new Random();
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        captchaValue = num1 + num2;

        JPanel theCaptchaPanel = new JPanel();
        theCaptchaPanel.setBackground(Utils.LIGHT_BLUE);
        theCaptchaPanel.setPreferredSize(new Dimension(80, 40));
        theCaptchaPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
        JLabel theCaptcha = new JLabel(num1 + " + " + num2);
        theCaptcha.setFont(Utils.BIG_BOLD_FONT);
        theCaptchaPanel.add(theCaptcha);
        captchaPanel.add(theCaptchaPanel);

        captchaField = new JTextField();
        captchaField.setPreferredSize(new Dimension(335, 40));
        captchaField.setFont(Utils.NORMAL_FONT);
        captchaField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        captchaPanel.add(captchaField);

        rightPanel.add(captchaPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        buttonPanel.setPreferredSize(new Dimension(420, 80));
        buttonPanel.setBackground(Utils.BACKGROUND_COLOR);

        signupButton = new JButton("Sign Up");
        signupButton.setFont(Utils.BIG_BOLD_FONT);
        signupButton.setFocusable(false);
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.addMouseListener(this);
        buttonPanel.add(signupButton);
        rightPanel.add(buttonPanel);

        JLabel loginLabel = new JLabel("Already have an account?");
        loginLabel.setPreferredSize(new Dimension(200, 30));
        loginLabel.setFont(Utils.NORMAL_FONT);
        rightPanel.add(loginLabel);

        loginButton = new JButton("Log in");
        loginButton.setPreferredSize(new Dimension(60, 30));
        loginButton.setFont(Utils.NORMAL_BOLD_FONT);
        loginButton.setBorderPainted(false);
        loginButton.setFocusable(false);
        loginButton.setBackground(Utils.BACKGROUND_COLOR);
        loginButton.setMargin(new Insets(0, 0, 0, 0));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addMouseListener(this);
        loginButton.setUI(new BasicButtonUI() {
            private Color getSelectColor() {
                return Utils.BACKGROUND_COLOR;
            }
        });
        rightPanel.add(loginButton);
        // End of Right Panel

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == backButton) {
            if (loginFrame == null) {
                loginFrame = new LoginFrame();
            }
            this.setVisible(false);
            loginFrame.setVisible(true);
        } else if (e.getSource() == signupButton) {
            if (loginGroup.getSelection() == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please, select a whether you are a 'Librarian' or a 'Student'.",
                        "SignUp Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            } else if (nameField.getText().isEmpty() ||
                    mailField.getText().isEmpty() ||
                    idField.getText().isEmpty() ||
                    passField.getPassword().length == 0 ||
                    passField.getPassword().length == 0
            ) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please, Fill up the blank areas.",
                        "Incomplete Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            } else if (!Arrays.equals(passField.getPassword(), confirmPassField.getPassword())) {
                JOptionPane.showMessageDialog(
                        this,
                        "Passwords do not match. Please, try again.",
                        "Password Error",
                        JOptionPane.ERROR_MESSAGE
                );
                passField.setText("");
                confirmPassField.setText("");
            } else if (captchaField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please, complete the captcha.",
                        "Captcha Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if (!captchaField.getText().equals(String.valueOf(captchaValue))) {
                JOptionPane.showMessageDialog(
                        this,
                        "Incorrect captcha value. Please, try again.",
                        "Captcha Incorrect",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if (captchaField.getText().equals(String.valueOf(captchaValue))) {
                String userType = loginGroup.getSelection().getActionCommand();
                String id = idField.getText();
                String name = nameField.getText();
                String mail = mailField.getText();
                String password = String.valueOf(passField.getPassword());
                String gender = (String) genderBox.getSelectedItem();

                Account account = new Account(this);
                if (!account.accountExists(userType, name, id)) {
                    account.addAccount(userType, id, name, mail, password, gender);
                    JOptionPane.showMessageDialog(
                            this,
                            " Account creation successful. Now you can try logging in.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    if (loginFrame == null) {
                        loginFrame = new LoginFrame();
                    }
                    loginFrame.setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Account with same Username or ID already exists.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        } else if (e.getSource() == loginButton) {
            if (loginFrame == null) {
                loginFrame = new LoginFrame();
            }
            this.setVisible(false);
            loginFrame.setVisible(true);
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
        if (e.getSource() == backButton) {
            backButton.setBackground(Color.LIGHT_GRAY);
        } else if (e.getSource() == loginButton) {
            loginButton.setForeground(Utils.BLUE);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == backButton) {
            backButton.setBackground(Color.WHITE);
        } else if (e.getSource() == loginButton) {
            loginButton.setForeground(Color.BLACK);
        }
    }
}
