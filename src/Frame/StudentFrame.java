package Frame;

import Extra.Utils;
import Frame.Panel.BorrowTab;
import Frame.Panel.HomeTab;
import Frame.Panel.ProfileTab;
import Frame.Panel.ReturnTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StudentFrame extends JFrame implements ActionListener, MouseListener {
    private final JTabbedPane tabbedPane;
    private final JButton homeButton;
    private final JButton borrowButton;
    private final JButton returnButton;
    private final JButton profileButton;
    private final JButton logoutButton;
    private final HomeTab homeTab;
    private final BorrowTab borrowTab;
    private final ReturnTab returnTab;
    private final ProfileTab profileTab;
    private LoginFrame loginFrame;

    public StudentFrame(String name, String userType) {
        super("LibraTrack - Logged in as Student");
        Image iconImage = Toolkit.getDefaultToolkit().getImage("src/images/library.png");
        this.setIconImage(iconImage);
        this.setSize(1260, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(300, 800));
        leftPanel.setBackground(Utils.BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("LibraTrack");
        titleLabel.setFont(Utils.BIG_BOLD_FONT);
        titleLabel.setPreferredSize(new Dimension(300, 200));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JPanel tabPanel = new JPanel();
        tabPanel.setLayout(new GridLayout(7, 1, 5, 0));
        tabPanel.setPreferredSize(new Dimension(300, 500));

        homeButton = new JButton("Home");
        homeButton.setFont(Utils.NORMAL_BOLD_FONT);
        homeButton.setBackground(Utils.LIGHT_BLUE);
        homeButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, Utils.BLUE),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        homeButton.addMouseListener(this);
        borrowButton = new JButton("Borrow");
        borrowButton.setFont(Utils.NORMAL_BOLD_FONT);
        borrowButton.setBackground(Utils.LIGHTER_BLUE);
        borrowButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        borrowButton.addMouseListener(this);
        returnButton = new JButton("Return");
        returnButton.setFont(Utils.NORMAL_BOLD_FONT);
        returnButton.setBackground(Utils.LIGHTER_BLUE);
        returnButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        returnButton.addMouseListener(this);
        profileButton = new JButton("Profile");
        profileButton.setFont(Utils.NORMAL_BOLD_FONT);
        profileButton.setBackground(Utils.LIGHTER_BLUE);
        profileButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        profileButton.addMouseListener(this);
        logoutButton = new JButton("Log Out");
        logoutButton.setFont(Utils.NORMAL_BOLD_FONT);
        logoutButton.setBackground(Utils.LIGHTER_BLUE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        logoutButton.addMouseListener(this);

        tabPanel.add(homeButton);
        tabPanel.add(borrowButton);
        tabPanel.add(returnButton);
        tabPanel.add(profileButton);
        tabPanel.add(logoutButton);

        leftPanel.add(titleLabel, BorderLayout.NORTH);
        leftPanel.add(tabPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(940, 800));
        rightPanel.setLayout(null);
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(-2, -25, 944, 825);

        borrowTab = new BorrowTab(name);
        returnTab = new ReturnTab(name);
        profileTab = new ProfileTab(name, userType);
        JPanel logoutTab = new JPanel();
        homeTab = new HomeTab(borrowTab, returnTab, name, userType);

        borrowTab.setHomeTab(homeTab);
        borrowTab.setReturnTab(returnTab);
        returnTab.setHomeTab(homeTab);
        returnTab.setBorrowTab(borrowTab);

        tabbedPane.add("Home", homeTab);
        tabbedPane.add("Borrow", borrowTab);
        tabbedPane.add("Return", returnTab);
        tabbedPane.add("Profile", profileTab);
        tabbedPane.add("Log Out", logoutTab);

        rightPanel.add(tabbedPane);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        homeButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        homeButton.setBackground(Utils.LIGHTER_BLUE);
        borrowButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        borrowButton.setBackground(Utils.LIGHTER_BLUE);
        returnButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        returnButton.setBackground(Utils.LIGHTER_BLUE);
        profileButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        profileButton.setBackground(Utils.LIGHTER_BLUE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        logoutButton.setBackground(Utils.LIGHTER_BLUE);

        clickedButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, Utils.BLUE),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        clickedButton.setBackground(Utils.LIGHT_BLUE);

        if (e.getSource() == homeButton) {
            tabbedPane.setSelectedIndex(0);
        } else if (e.getSource() == borrowButton) {
            tabbedPane.setSelectedIndex(1);
        } else if (e.getSource() == returnButton) {
            tabbedPane.setSelectedIndex(2);
        } else if (e.getSource() == profileButton) {
            tabbedPane.setSelectedIndex(3);
        } else if (e.getSource() == logoutButton) {
            int answer = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to log out?",
                    "Log Out Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (answer == JOptionPane.YES_OPTION) {
                tabbedPane.setSelectedIndex(4);
                if (loginFrame == null) {
                    loginFrame = new LoginFrame();
                }
                loginFrame.setVisible(true);
                this.dispose();
            } else {
                tabbedPane.setSelectedIndex(0);
                homeButton.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 5, 0, 0, Utils.BLUE),
                        BorderFactory.createEmptyBorder(0, 0, 0, 0)
                ));
                homeButton.setBackground(Utils.LIGHT_BLUE);
                logoutButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                logoutButton.setBackground(Utils.LIGHTER_BLUE);
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
