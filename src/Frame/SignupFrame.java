package Frame;

import Extra.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    private final Color backgroundColor = Utils.BACKGROUND_COLOR;
    private final Font introFont = Utils.INTRO_FONT;
    private final Font normalFont = Utils.NORMAL_FONT;
    private final Font normalBoldFont = Utils.NORMAL_BOLD_FONT;

    private final Font smallFont = Utils.SMALL_FONT;
    private final Font smallBoldFont = Utils.SMALL_BOLD_FONT;

    private final Font bigFont = Utils.BIG_FONT;
    private final Font bigBoldFont = Utils.BIG_BOLD_FONT;


    public SignupFrame() {
        super("SignUp");
        Image iconImage = Toolkit.getDefaultToolkit().getImage("src/images/library.png");
        this.setIconImage(iconImage);
        this.setSize(1260, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        UIManager.put("OptionPane.messageFont", normalFont);
        UIManager.put("OptionPane.buttonFont", normalFont);

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
        rightPanel.setBackground(backgroundColor);

        introPanel = new JPanel();
        introPanel.setPreferredSize(new Dimension(600, 130));
        introPanel.setBorder(BorderFactory.createEmptyBorder(60, 0, 20, 0));
        introPanel.setBackground(backgroundColor);

        introLabel = new JLabel("Create Account");
        introLabel.setFont(introFont);
        introPanel.add(introLabel);
        rightPanel.add(introPanel);

        radioPanel = new JPanel();
        radioPanel.setLayout(new BorderLayout());
        radioPanel.setPreferredSize(new Dimension(200, 30));
        radioPanel.setBackground(backgroundColor);

        ImageIcon checkedIcon = new ImageIcon("src/images/radio_checked.png");
        ImageIcon unCheckedIcon = new ImageIcon("src/images/radio_unchecked.png");
        librarianButton = new JRadioButton("Librarian", unCheckedIcon);
        librarianButton.setActionCommand("librarian");
        librarianButton.setSelectedIcon(checkedIcon);
        librarianButton.setFont(smallBoldFont);
        librarianButton.setBackground(backgroundColor);
        librarianButton.setFocusable(false);
        studentButton = new JRadioButton("Student", unCheckedIcon);
        studentButton.setActionCommand("student");
        studentButton.setSelectedIcon(checkedIcon);
        studentButton.setFont(smallBoldFont);
        studentButton.setBackground(backgroundColor);
        studentButton.setFocusable(false);

        loginGroup = new ButtonGroup();
        loginGroup.add(librarianButton);
        loginGroup.add(studentButton);

        radioPanel.add(librarianButton, BorderLayout.WEST);
        radioPanel.add(studentButton, BorderLayout.EAST);
        rightPanel.add(radioPanel);

        JLabel nameLabel = new JLabel("User Name");
        nameLabel.setFont(normalFont);
        nameLabel.setPreferredSize(new Dimension(420, 30));
        rightPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(bigFont);
        nameField.setPreferredSize(new Dimension(420, 50));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        rightPanel.add(nameField);

        JLabel mailLabel = new JLabel("Email Address");
        mailLabel.setFont(normalFont);
        mailLabel.setPreferredSize(new Dimension(420, 30));
        rightPanel.add(mailLabel);

        mailField = new JTextField();
        mailField.setFont(bigFont);
        mailField.setPreferredSize(new Dimension(420, 50));
        mailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        rightPanel.add(mailField);

        JPanel optionalPanel = new JPanel();
        optionalPanel.setLayout(new GridLayout(2, 2, 10, 0));
        optionalPanel.setPreferredSize(new Dimension(420, 80));
        optionalPanel.setBackground(backgroundColor);

        JLabel idLabel = new JLabel("ID");
        idLabel.setFont(smallFont);
        optionalPanel.add(idLabel);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(smallFont);
        optionalPanel.add(genderLabel);

        idField = new JTextField();
        idField.setFont(normalFont);
        idField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        optionalPanel.add(idField);

        String[] gender = {"Male", "Female", "Other"};
        genderBox = new JComboBox<>(gender);
        genderBox.setFont(normalFont);
        optionalPanel.add(genderBox);

        rightPanel.add(optionalPanel);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new GridLayout(2, 2, 10, 0));
        passwordPanel.setPreferredSize(new Dimension(420, 80));
        passwordPanel.setBackground(backgroundColor);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(smallFont);
        passwordPanel.add(passLabel);

        JLabel confirmPassLabel = new JLabel("Confirm Password");
        confirmPassLabel.setFont(smallFont);
        passwordPanel.add(confirmPassLabel);

        passField = new JPasswordField();
        passField.setFont(normalFont);
        passField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        passwordPanel.add(passField);

        confirmPassField = new JPasswordField();
        confirmPassField.setFont(normalFont);
        confirmPassField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        passwordPanel.add(confirmPassField);

        rightPanel.add(passwordPanel);

        JLabel captchaLabel = new JLabel("Captcha");
        captchaLabel.setFont(smallFont);
        captchaLabel.setPreferredSize(new Dimension(420, 25));
        rightPanel.add(captchaLabel);

        JPanel captchaPanel = new JPanel();
        captchaPanel.setPreferredSize(new Dimension(425, 50));
        captchaPanel.setBackground(backgroundColor);

        Random random = new Random();
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        captchaValue = num1 + num2;

        JPanel theCaptchaPanel = new JPanel();
        theCaptchaPanel.setBackground(Utils.LIGHT_BLUE);
        theCaptchaPanel.setPreferredSize(new Dimension(80, 40));
        theCaptchaPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
        JLabel theCaptcha = new JLabel(num1 + " + " + num2);
        theCaptcha.setFont(bigBoldFont);
        theCaptchaPanel.add(theCaptcha);
        captchaPanel.add(theCaptchaPanel);

        captchaField = new JTextField();
        captchaField.setPreferredSize(new Dimension(335, 40));
        captchaField.setFont(normalFont);
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
        buttonPanel.setBackground(backgroundColor);

        signupButton = new JButton("Sign Up");
        signupButton.setFont(bigBoldFont);
        signupButton.setFocusable(false);
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.addMouseListener(this);
        buttonPanel.add(signupButton);
        rightPanel.add(buttonPanel);

        JLabel loginLabel = new JLabel("Already have an account?");
        loginLabel.setPreferredSize(new Dimension(200, 30));
        loginLabel.setFont(normalFont);
        rightPanel.add(loginLabel);

        loginButton = new JButton("Log in");
        loginButton.setPreferredSize(new Dimension(60, 30));
        loginButton.setFont(normalBoldFont);
        loginButton.setBorderPainted(false);
        loginButton.setFocusable(false);
        loginButton.setBackground(backgroundColor);
        loginButton.setMargin(new Insets(0, 0, 0, 0));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addMouseListener(this);
        loginButton.setUI(new BasicButtonUI() {
            private Color getSelectColor() {
                return backgroundColor;
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
            } else if (!String.valueOf(passField.getPassword()).equals(
                    String.valueOf(confirmPassField.getPassword())
            )) {
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

                Account account = new Account(this, userType, id, name, mail, password, gender);
                if (!account.accountExists(name, id)) {
                    account.addAccount();
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Account with same Name & id already exists.",
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
