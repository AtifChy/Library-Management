package Frame.Panel;

import Extra.Account;
import Extra.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AccountTab extends JPanel implements ActionListener, KeyListener {
    private final JTextField searchField;
    private final DefaultTableModel borrowTableModel;
    private final JTable borrowTable;
    private final JButton addButton;
    private final JTextField nameField;
    private final JTextField idField;
    private final JTextField mailField;
    private final JButton removeButton;
    private final JComboBox<String> genderBox;

    public AccountTab() {
        this.setLayout(new FlowLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(940, 150));

        JLabel borrowLabel = new JLabel("Books");
        borrowLabel.setFont(Utils.BIG_BOLD_FONT);
        topPanel.add(borrowLabel);

        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        searchPanel.setPreferredSize(new Dimension(940, 80));

        ImageIcon searchIcon = new ImageIcon("src/images/search.png");
        JLabel iconLabel = new JLabel(searchIcon);
        searchPanel.add(iconLabel);

        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(300, 50));
        searchField.setFont(Utils.BIG_FONT);
        searchField.addKeyListener(this);
        searchPanel.add(searchField);
        topPanel.add(searchPanel);

        this.add(topPanel);

        String[] columnTitles = {"ID", "Name", "Email", "Gender", "Registration Date"};

        borrowTableModel = new DefaultTableModel(columnTitles, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/data/student.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rawData = line.split(",");
                String[] rowData = new String[5];
                int index = 0;

                // Skip password column
                for (int i = 0; i < rawData.length; i++) {
                    if (i != 3) {
                        rowData[index] = rawData[i];
                        index++;
                    }
                }

                borrowTableModel.addRow(rowData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        borrowTable = new JTable(borrowTableModel);
        borrowTable.setFont(Utils.NORMAL_FONT);
        borrowTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader tableHeader = new JTableHeader(borrowTable.getColumnModel());
        tableHeader.setFont(Utils.NORMAL_FONT);
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 40));
        borrowTable.setTableHeader(tableHeader);

        int rowHeight = 30;
        borrowTable.setRowHeight(rowHeight);

        int[] columnWidths = {100, 110, 160, 80, 150};
        for (int i = 0; i < columnWidths.length; i++) {
            borrowTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        JScrollPane borrowPane = new JScrollPane(borrowTable);
        borrowPane.setPreferredSize(new Dimension(700, 300));
        this.add(borrowPane);

        JPanel addPanel = new JPanel();
        addPanel.setPreferredSize(new Dimension(800, 100));
        addPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        addPanel.setLayout(new GridLayout(2, 4, 10, 5));

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(Utils.NORMAL_FONT);
        nameField = new JTextField(20);
        nameField.setFont(Utils.BIG_FONT);

        JLabel idLabel = new JLabel("ID");
        idLabel.setFont(Utils.NORMAL_FONT);
        idField = new JTextField(10);
        idField.setFont(Utils.BIG_FONT);

        JLabel mailLabel = new JLabel("Email");
        mailLabel.setFont(Utils.NORMAL_FONT);
        mailField = new JTextField(20);
        mailField.setFont(Utils.BIG_FONT);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(Utils.NORMAL_FONT);
        String[] gender = {"Male", "Female", "Other"};
        genderBox = new JComboBox<String>(gender);
        genderBox.setFont(Utils.NORMAL_FONT);

        addPanel.add(nameLabel);
        addPanel.add(idLabel);
        addPanel.add(mailLabel);
        addPanel.add(genderLabel);
        addPanel.add(nameField);
        addPanel.add(idField);
        addPanel.add(mailField);
        addPanel.add(genderBox);

        this.add(addPanel);

        JPanel managePanel = new JPanel();
        managePanel.setPreferredSize(new Dimension(940, 200));
        managePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        addButton = new JButton("Add Account");
        addButton.setPreferredSize(new Dimension(150, 60));
        addButton.setFont(Utils.BIG_BOLD_FONT);
        addButton.addActionListener(this);
        managePanel.add(addButton);

        removeButton = new JButton("Remove Account");
        removeButton.setPreferredSize(new Dimension(200, 60));
        removeButton.setFont(Utils.BIG_BOLD_FONT);
        removeButton.addActionListener(this);
        managePanel.add(removeButton);
        this.add(managePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String userType = "Student";
            String name = nameField.getText();
            String id = idField.getText();
            String mail = mailField.getText();
            String gender = (String) genderBox.getSelectedItem();
            String password = "NO_PASSWORD_SET";

            Account account = new Account();

            if (name.isEmpty() || id.isEmpty() || mail.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "All fields are required to add a book.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            } else if (!account.accountExists(userType, name, id)) {
                String[] rawData = new String[6];
                rawData[0] = idField.getText();
                rawData[1] = nameField.getText();
                rawData[2] = mailField.getText();
                rawData[3] = password;
                rawData[4] = (String) genderBox.getSelectedItem();
                rawData[5] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma"));

                String[] rowData = new String[5];
                int index = 0;
                for (int i = 0; i < rawData.length; i++) {
                    if (i != 3) {
                        rowData[index] = rawData[i];
                        index++;
                    }
                }

                borrowTableModel.addRow(rowData);

                nameField.setText("");
                idField.setText("");
                mailField.setText("");

                String writeData = String.join(",", rawData);
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/student.txt", true));
                    writer.write(writeData);
                    writer.newLine();
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Account already exists.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } else if (e.getSource() == removeButton) {
            int selectedRow = borrowTable.getSelectedRow();
            String id = borrowTableModel.getValueAt(selectedRow, 0).toString();
            String name = borrowTableModel.getValueAt(selectedRow, 1).toString();

            try {
                List<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader("src/data/student.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] rawData = line.split(",");
                    if (rawData[0].equals(id) && rawData[1].equals(name)) {
                        continue;
                    } else {
                        lines.add(line);
                    }
                }
                reader.close();
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/student.txt"));
                for (int i = 0; i < lines.size(); i++) {
                    writer.write(lines.get(i));
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            borrowTableModel.removeRow(selectedRow);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(borrowTableModel);
        borrowTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(searchField.getText()));
    }
}
