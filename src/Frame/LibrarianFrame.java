package Frame;

import Extra.Utils;
import Frame.Panel.AccountTab;
import Frame.Panel.BookTab;
import Frame.Panel.HomeAdminTab;
import Frame.Panel.HomeTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LibrarianFrame extends JFrame implements MouseListener {
    private JTabbedPane tabbedPane;
    private JButton homeButton;
    private JButton bookButton;
    private JButton accountButton;
    private JButton logoutButton;
    private LoginFrame loginFrame;

    public LibrarianFrame(String name, String userType) {
        super("LibraTrack - Logged in as Librarian");
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
        bookButton = new JButton("Books");
        bookButton.setFont(Utils.NORMAL_BOLD_FONT);
        bookButton.setBackground(Utils.LIGHTER_BLUE);
        bookButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bookButton.addMouseListener(this);
        accountButton = new JButton("Account");
        accountButton.setFont(Utils.NORMAL_BOLD_FONT);
        accountButton.setBackground(Utils.LIGHTER_BLUE);
        accountButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        accountButton.addMouseListener(this);
        logoutButton = new JButton("Log Out");
        logoutButton.setFont(Utils.NORMAL_BOLD_FONT);
        logoutButton.setBackground(Utils.LIGHTER_BLUE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        logoutButton.addMouseListener(this);

        tabPanel.add(homeButton);
        tabPanel.add(bookButton);
        tabPanel.add(accountButton);
        tabPanel.add(logoutButton);

        leftPanel.add(titleLabel, BorderLayout.NORTH);
        leftPanel.add(tabPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(940, 800));
        rightPanel.setLayout(null);
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(-2, -25, 944, 825);

        BookTab bookTab = new BookTab();
        AccountTab accountTab = new AccountTab();
        HomeAdminTab homeAdminTab = new HomeAdminTab(name, userType);
        JPanel logoutTab = new JPanel();

        tabbedPane.add("Home", homeAdminTab);
        tabbedPane.add("Books", bookTab);
        tabbedPane.add("Account", accountTab);
        tabbedPane.add("Log Out", logoutTab);

        rightPanel.add(tabbedPane);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);

        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        homeButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        homeButton.setBackground(Utils.LIGHTER_BLUE);
        bookButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bookButton.setBackground(Utils.LIGHTER_BLUE);
        accountButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        accountButton.setBackground(Utils.LIGHTER_BLUE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        logoutButton.setBackground(Utils.LIGHTER_BLUE);

        clickedButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, Utils.BLUE),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        clickedButton.setBackground(Utils.LIGHT_BLUE);

        if (e.getSource() == homeButton) {
            tabbedPane.setSelectedIndex(0);
        } else if (e.getSource() == bookButton) {
            tabbedPane.setSelectedIndex(1);
        } else if (e.getSource() == accountButton) {
            tabbedPane.setSelectedIndex(2);
        } else if (e.getSource() == logoutButton) {
            int answer = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to log out?",
                    "Log Out Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (answer == JOptionPane.YES_OPTION) {
                // tabbedPane.setSelectedIndex(3);
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
