import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ForgotPassFrame extends JFrame implements MouseListener {
    private final JPanel naviPanel;
    private final JButton backButton;
    private final BufferedImage resizedImage;
    private final JPanel introPanel;
    private final JLabel introLabel;
    private final JPanel radioPanel;
    private final JRadioButton librarianButton;
    private final JRadioButton studentButton;
    private final ButtonGroup loginGroup;
    private final JPanel leftPanel;
    private final JPanel rightPanel;
    private LoginFrame loginFrame;
    private final Color backgroundColor = Utils.BACKGROUND_COLOR;
    private final Font introFont = Utils.INTRO_FONT;
    private final Font normalFont = Utils.NORMAL_FONT;
    private final Font normalBoldFont = Utils.NORMAL_BOLD_FONT;

    private final Font smallFont = Utils.SMALL_FONT;
    private final Font smallBoldFont = Utils.SMALL_BOLD_FONT;

    private final Font bigFont = Utils.BIG_FONT;

    public ForgotPassFrame() {
        super("Forgot Password");
        Image iconImage = Toolkit.getDefaultToolkit().getImage("src/images/library.png");
        this.setIconImage(iconImage);
        this.setSize(1260, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        // Start of Left Panel
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(660, 700));
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
            resizedImage = Utils.resizeImage("src/images/forgot-pass.jpg", 500, 500);
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
        rightPanel.setPreferredSize(new Dimension(600, 700));

        introPanel = new JPanel();
        introPanel.setPreferredSize(new Dimension(600, 130));
        introPanel.setBorder(BorderFactory.createEmptyBorder(60, 0, 20, 0));
        introPanel.setBackground(backgroundColor);

        introLabel = new JLabel("Forgot Password?");
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
        rightPanel.add(radioPanel);


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
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == backButton) {
            backButton.setBackground(Color.WHITE);
        }
    }
}
