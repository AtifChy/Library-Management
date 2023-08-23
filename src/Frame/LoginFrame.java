package Frame;

import Extra.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;


public class LoginFrame extends JFrame implements MouseListener {
    private ButtonGroup loginGroup;
    private JRadioButton librarianButton;
    private JRadioButton studentButton;
    private JTextField nameField;
    private JPasswordField passField;
    private JToggleButton showHideButton;
    private JCheckBox rememberBox;
    private JButton forgotButton;
    private JButton loginButton;
    private JButton signupButton;
    private JButton backButton;
    private ImageIcon showIcon;
    private ImageIcon hideIcon;
    private File file;

    public LoginFrame() {
        super("LogIn");
        Image iconImage = Toolkit.getDefaultToolkit().getImage("src/images/library.png");
        this.setIconImage(iconImage);
        this.setSize(1260, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        UIManager.put("OptionPane.messageFont", Utils.NORMAL_FONT);
        UIManager.put("OptionPane.buttonFont", Utils.NORMAL_FONT);

        JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(560, 700));
        loginPanel.setBackground(Utils.BACKGROUND_COLOR);

        JPanel introPanel = new JPanel();
        introPanel.setPreferredSize(new Dimension(560, 130));
        introPanel.setBorder(BorderFactory.createEmptyBorder(60, 0, 20, 0));
        introPanel.setBackground(Utils.BACKGROUND_COLOR);

        JLabel introLabel = new JLabel("Welcome");
        introLabel.setFont(Utils.INTRO_FONT);
        introPanel.add(introLabel);
        loginPanel.add(introPanel);

        JPanel radioPanel = new JPanel();
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
        librarianButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        studentButton = new JRadioButton("Student", unCheckedIcon);
        studentButton.setActionCommand("Student");
        studentButton.setSelectedIcon(checkedIcon);
        studentButton.setFont(Utils.SMALL_BOLD_FONT);
        studentButton.setBackground(Utils.BACKGROUND_COLOR);
        studentButton.setFocusable(false);
        studentButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginGroup = new ButtonGroup();
        loginGroup.add(librarianButton);
        loginGroup.add(studentButton);
        loginGroup.setSelected(studentButton.getModel(), true);

        radioPanel.add(librarianButton, BorderLayout.WEST);
        radioPanel.add(studentButton, BorderLayout.EAST);
        loginPanel.add(radioPanel);

        JLabel nameLabel = new JLabel("User Name");
        nameLabel.setFont(Utils.NORMAL_FONT);
        nameLabel.setPreferredSize(new Dimension(420, 28));
        loginPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(420, 56));
        nameField.setFont(Utils.BIG_FONT);
        nameField.setToolTipText("Enter your user name");
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        nameField.setBackground(Utils.BACKGROUND_COLOR);
        loginPanel.add(nameField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(Utils.NORMAL_FONT);
        passLabel.setPreferredSize(new Dimension(420, 28));
        loginPanel.add(passLabel);

        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(390, 56));
        passField.setFont(Utils.BIG_FONT);
        passField.setToolTipText("Enter your password");
        passField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        passField.setBackground(Utils.BACKGROUND_COLOR);
        loginPanel.add(passField);

        showIcon = new ImageIcon("src/images/show.png");
        hideIcon = new ImageIcon("src/images/hide.png");
        showHideButton = new JToggleButton(showIcon);
        showHideButton.setPreferredSize(new Dimension(25, 56));
        showHideButton.setFocusable(false);
        showHideButton.setBackground(Utils.BACKGROUND_COLOR);
        showHideButton.setBorderPainted(false);
        showHideButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showHideButton.setUI(new BasicButtonUI() {
            private Color getSelectColor() {
                return Utils.BACKGROUND_COLOR;
            }
        });
        showHideButton.addMouseListener(this);
        loginPanel.add(showHideButton);

        JPanel rememberPanel = new JPanel();
        rememberPanel.setLayout(new BorderLayout());
        rememberPanel.setPreferredSize(new Dimension(420, 40));
        rememberPanel.setBackground(Utils.BACKGROUND_COLOR);

        rememberBox = new JCheckBox("Remember me");
        rememberBox.setFocusable(false);
        rememberBox.setIcon(new ImageIcon("src/images/checkbox_blank.png"));
        rememberBox.setSelectedIcon(new ImageIcon("src/images/checkbox_selected.png"));
        rememberBox.setFont(Utils.SMALL_FONT);
        rememberBox.setBackground(Utils.BACKGROUND_COLOR);
        rememberBox.setSelected(false);
        rememberBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rememberPanel.add(rememberBox, BorderLayout.WEST);

        forgotButton = new JButton("Forgot password?");
        forgotButton.setFont(Utils.SMALL_FONT);
        forgotButton.setBorderPainted(false);
        forgotButton.setFocusable(false);
        forgotButton.setBackground(Utils.BACKGROUND_COLOR);
        forgotButton.setForeground(Utils.BLUE);
        forgotButton.setMargin(new Insets(0, 0, 0, 0));
        forgotButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotButton.setUI(new BasicButtonUI() {
            private Color getSelectColor() {
                return Utils.BACKGROUND_COLOR;
            }
        });
        forgotButton.addMouseListener(this);
        rememberPanel.add(forgotButton, BorderLayout.EAST);

        loginPanel.add(rememberPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(45, 0, 35, 0));
        buttonPanel.setPreferredSize(new Dimension(420, 140));
        buttonPanel.setBackground(Utils.BACKGROUND_COLOR);

        loginButton = new JButton("Log in");
        loginButton.setFont(Utils.BIG_BOLD_FONT);
        loginButton.setFocusable(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addMouseListener(this);
        buttonPanel.add(loginButton);
        loginPanel.add(buttonPanel);

        JLabel signupLabel = new JLabel("Don't have an account yet?");
        signupLabel.setPreferredSize(new Dimension(210, 50));
        signupLabel.setFont(Utils.NORMAL_FONT);
        loginPanel.add(signupLabel);

        signupButton = new JButton("Sign up");
        signupButton.setPreferredSize(new Dimension(70, 50));
        signupButton.setFont(Utils.NORMAL_BOLD_FONT);
        signupButton.setBorderPainted(false);
        signupButton.setFocusable(false);
        signupButton.setBackground(Utils.BACKGROUND_COLOR);
        signupButton.setMargin(new Insets(0, 0, 0, 0));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.addMouseListener(this);
        signupButton.setUI(new BasicButtonUI() {
            private Color getSelectColor() {
                return Utils.BACKGROUND_COLOR;
            }
        });
        loginPanel.add(signupButton);

        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(700, 700));
        imagePanel.setBackground(Color.WHITE);

        JPanel naviPanel = new JPanel();
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

        ImageIcon image = new ImageIcon("src/images/login.jpg");
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 20));
        imagePanel.add(imageLabel);

        this.add(loginPanel, BorderLayout.EAST);
        this.add(imagePanel, BorderLayout.WEST);

        this.setVisible(true);
    }

    public LoginFrame(String userType, String name, String password) {
        UIManager.put("OptionPane.messageFont", Utils.NORMAL_FONT);
        UIManager.put("OptionPane.buttonFont", Utils.NORMAL_FONT);

        login(userType, name, password);
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
            ForgotPassFrame forgotPassFrame = new ForgotPassFrame();
            this.setVisible(false);
            forgotPassFrame.setVisible(true);
        } else if (e.getSource() == loginButton) {
            if (!librarianButton.isSelected() && !studentButton.isSelected()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select either 'Librarian' or 'Student'.",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                String userType = loginGroup.getSelection().getActionCommand();
                String name = nameField.getText();
                String password = String.valueOf(passField.getPassword());

                if (userType.equals("Librarian")) {
                    file = new File("src/data/librarian.txt");
                } else if (userType.equals("Student")) {
                    file = new File("src/data/student.txt");
                }

                boolean newPassSet = false;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(",");
                        if (data[0].equals(name) && data[3].equals("NO_PASSWORD_SET")) {
                            newPassSet = true;
                        }
                    }
                    reader.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (nameField.getText().isEmpty() && passField.getPassword().length == 0 && !newPassSet) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please, Fill the black areas.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    login(userType, name, password);
                }
            }
        } else if (e.getSource() == signupButton) {
            SignupFrame signupFrame = new SignupFrame();
            this.setVisible(false);
            signupFrame.setVisible(true);
        }
    }

    public void login(String userType, String name, String password) {
        Account account = new Account(this);
        boolean isLoggedIn = account.loginAccount(userType, name, password);
        if (isLoggedIn) {
            try {
                rememberMe();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            if (userType.equals("Librarian")) {
                LibrarianFrame librarianFrame = new LibrarianFrame(name, userType);
                librarianFrame.setVisible(true);
            } else if (userType.equals("Student")) {
                StudentFrame studentFrame = new StudentFrame(name, userType);
                studentFrame.setVisible(true);
            }

            this.setVisible(false);
            System.out.println("Logged in as \"" + name + " - " + userType + "\"");
        }
    }

    public void rememberMe() throws IOException {
        if (this.rememberBox != null && this.rememberBox.isSelected()) {
            String userType = loginGroup.getSelection().getActionCommand();
            String name = nameField.getText();
            String password = String.valueOf(passField.getPassword());

            File rememberFile = new File("src/data/remember.txt");
            if (!rememberFile.exists()) {
                rememberFile.createNewFile();
            }

            String rememberData = userType + "," + name + "," + password;
            BufferedWriter writer = new BufferedWriter(new FileWriter(rememberFile));
            writer.write(rememberData);
            writer.flush();
            writer.close();
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
            signupButton.setForeground(Utils.BLUE);
        } else if (e.getSource() == backButton) {
            backButton.setBackground(Color.LIGHT_GRAY);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == forgotButton) {
            forgotButton.setForeground(Utils.BLUE);
        } else if (e.getSource() == signupButton) {
            signupButton.setForeground(Color.BLACK);
        } else if (e.getSource() == backButton) {
            backButton.setBackground(Color.WHITE);
        }
    }
}
