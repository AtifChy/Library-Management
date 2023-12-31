package Frame.Panel;

import Extra.Account;
import Extra.Utils;

import javax.swing.*;
import java.awt.*;

public class HomeAdminTab extends JPanel {
    private final JLabel totalBook;
    private final JLabel availBook;
    private final JLabel returnBook;

    public HomeAdminTab(String name, String userType) {
        Account account = new Account(null, userType, name, null);

        this.setLayout(new BorderLayout());
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setPreferredSize(new Dimension(940, 300));
        userPanel.setBackground(Color.WHITE);

        Component padding = Box.createRigidArea(new Dimension(0, 50));
        userPanel.add(padding);

        ImageIcon userImage = new ImageIcon("src/images/user.png");
        JLabel userIcon = new JLabel(userImage);
        userIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.add(userIcon);

        JLabel userLabel = new JLabel("Name: " + name.toUpperCase());
        userLabel.setFont(Utils.NORMAL_BOLD_FONT);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.add(userLabel);

        JLabel accountType = new JLabel("Account Type: " + userType);
        accountType.setFont(Utils.NORMAL_BOLD_FONT);
        accountType.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.add(accountType);

        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(new GridLayout(2, 3, 50, 0));
        aboutPanel.setPreferredSize(new Dimension(940, 400));
        aboutPanel.setBorder(BorderFactory.createEmptyBorder(70, 90, 70, 90));
        aboutPanel.setBackground(Color.WHITE);

        JLabel totalBookLabel = new JLabel("Number of Books");
        totalBookLabel.setFont(Utils.NORMAL_BOLD_FONT);
        aboutPanel.add(totalBookLabel);

        JLabel availBookLabel = new JLabel("Available Books");
        availBookLabel.setFont(Utils.NORMAL_BOLD_FONT);
        aboutPanel.add(availBookLabel);

        JLabel returnBookLabel = new JLabel("Number of Student");
        returnBookLabel.setFont(Utils.NORMAL_BOLD_FONT);
        aboutPanel.add(returnBookLabel);

        BorrowTab borrowTab = new BorrowTab(name);

        JPanel totalBookPanel = new JPanel();
        totalBookPanel.setLayout(new BoxLayout(totalBookPanel, BoxLayout.X_AXIS));
        totalBookPanel.setBackground(Utils.LIGHTER_BLUE);
        totalBookPanel.setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, Utils.BLUE));
        totalBook = new JLabel(String.valueOf(borrowTab.getTotalBookCount()));
        totalBook.setFont(Utils.HUGE_FONT);
        totalBookPanel.add(Box.createHorizontalGlue());
        totalBookPanel.add(totalBook);
        totalBookPanel.add(Box.createHorizontalGlue());
        aboutPanel.add(totalBookPanel);

        JPanel availBookPanel = new JPanel();
        availBookPanel.setLayout(new BoxLayout(availBookPanel, BoxLayout.X_AXIS));
        availBookPanel.setBackground(Utils.LIGHTER_BLUE);
        availBookPanel.setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, Utils.BLUE));
        availBook = new JLabel(String.valueOf(borrowTab.getAvailableBooKCount()));
        availBook.setFont(Utils.HUGE_FONT);
        availBookPanel.add(Box.createHorizontalGlue());
        availBookPanel.add(availBook);
        availBookPanel.add(Box.createHorizontalGlue());
        aboutPanel.add(availBookPanel);

        JPanel returnBookPanel = new JPanel();
        returnBookPanel.setLayout(new BoxLayout(returnBookPanel, BoxLayout.X_AXIS));
        returnBookPanel.setBackground(Utils.LIGHTER_BLUE);
        returnBookPanel.setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, Utils.BLUE));
        returnBook = new JLabel(String.valueOf(account.accountCount()));
        returnBook.setFont(Utils.HUGE_FONT);
        returnBookPanel.add(Box.createHorizontalGlue());
        returnBookPanel.add(returnBook);
        returnBookPanel.add(Box.createHorizontalGlue());
        aboutPanel.add(returnBookPanel);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(940, 90));
        emptyPanel.setBackground(Color.WHITE);

        this.add(userPanel, BorderLayout.NORTH);
        this.add(aboutPanel, BorderLayout.CENTER);
        this.add(emptyPanel, BorderLayout.SOUTH);
    }
}
