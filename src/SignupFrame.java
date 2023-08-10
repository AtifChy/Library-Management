import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SignupFrame extends JFrame implements MouseListener {
    private JPanel leftPanel;
    private JPanel naviPanel;
    private JButton backButton;
    private JPanel rightPanel;
    private BufferedImage resizedImage;
    private LoginFrame loginFrame;

    public SignupFrame() throws IOException {
        super("SignUp");
        Image iconImage = Toolkit.getDefaultToolkit().getImage("src/images/library.png");
        this.setIconImage(iconImage);
        this.setSize(1260, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

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

        resizedImage = Utils.resizeImage("src/images/signup.jpg", 600, 600);
        ImageIcon image = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
        leftPanel.add(imageLabel, BorderLayout.CENTER);


        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(600, 800));

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == backButton) {
            try {
                if (loginFrame == null) {
                    loginFrame = new LoginFrame();
                }
                this.setVisible(false);
                loginFrame.setVisible(true);
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
