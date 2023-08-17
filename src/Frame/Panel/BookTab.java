package Frame.Panel;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookTab extends JPanel implements KeyListener, ActionListener {
    private final JTextField searchField;
    private final DefaultTableModel borrowTableModel;
    private final JTable borrowTable;
    private final JButton addButton;
    private final JTextField titleField;
    private final JTextField authorField;
    private final JTextField pubField;
    private final JTextField quantityField;
    private final JButton removeButton;

    public BookTab() {
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

        String[] columnTitles = {"ID", "Title", "Author", "Publisher", "Quantity", "Available", "Added dates"};

        borrowTableModel = new DefaultTableModel(columnTitles, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/data/library/books.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                borrowTableModel.addRow(rowData);
            }
            reader.close();
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

        int[] columnWidths = {50, 100, 135, 135, 95, 95, 120};
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

        JLabel titleLabel = new JLabel("Title");
        titleLabel.setFont(Utils.NORMAL_FONT);
        titleField = new JTextField(20);
        titleField.setFont(Utils.BIG_FONT);

        JLabel authorLabel = new JLabel("Author");
        authorLabel.setFont(Utils.NORMAL_FONT);
        authorField = new JTextField(20);
        authorField.setFont(Utils.BIG_FONT);

        JLabel pubLabel = new JLabel("Publisher");
        pubLabel.setFont(Utils.NORMAL_FONT);
        pubField = new JTextField(20);
        pubField.setFont(Utils.BIG_FONT);

        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(Utils.NORMAL_FONT);
        quantityField = new JTextField(10);
        quantityField.setFont(Utils.BIG_FONT);

        addPanel.add(titleLabel);
        addPanel.add(authorLabel);
        addPanel.add(pubLabel);
        addPanel.add(quantityLabel);
        addPanel.add(titleField);
        addPanel.add(authorField);
        addPanel.add(pubField);
        addPanel.add(quantityField);

        this.add(addPanel);

        JPanel managePanel = new JPanel();
        managePanel.setPreferredSize(new Dimension(940, 200));
        managePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        addButton = new JButton("Add Book");
        addButton.setPreferredSize(new Dimension(150, 60));
        addButton.setFont(Utils.BIG_BOLD_FONT);
        addButton.addActionListener(this);
        managePanel.add(addButton);

        removeButton = new JButton("Remove Book");
        removeButton.setPreferredSize(new Dimension(170, 60));
        removeButton.setFont(Utils.BIG_BOLD_FONT);
        removeButton.addActionListener(this);
        managePanel.add(removeButton);
        this.add(managePanel);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String title = titleField.getText();
            String author = authorField.getText();
            String publisher = pubField.getText();
            String quantify = quantityField.getText();

            if (title.isEmpty() || author.isEmpty() || publisher.isEmpty() || quantify.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "All fields are required to add a book.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date = LocalDate.now().format(formatter);

                String[] newRowData = new String[7];
                newRowData[0] = String.valueOf(borrowTable.getRowCount() + 1);
                newRowData[1] = titleField.getText();
                newRowData[2] = authorField.getText();
                newRowData[3] = pubField.getText();
                newRowData[4] = quantityField.getText();
                newRowData[5] = quantityField.getText();
                newRowData[6] = date;

                borrowTableModel.addRow(newRowData);

                titleField.setText("");
                authorField.setText("");
                pubField.setText("");
                quantityField.setText("");

                String addRowData = String.join(",", newRowData);
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/library/books.txt", true));
                    writer.write(addRowData);
                    writer.newLine();
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (e.getSource() == removeButton) {
            int selectedRow = borrowTable.getSelectedRow();
            if (selectedRow >= 0) {
                borrowTableModel.removeRow(selectedRow);

                for (int i = 0; i < borrowTableModel.getRowCount(); i++) {
                    borrowTableModel.setValueAt(String.valueOf(i + 1), i, 0);
                }
            }
            removeUpdate();
        }
    }

    public void removeUpdate() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/library/books.txt"));
            for (int i = 0; i < borrowTableModel.getRowCount(); i++) {
                StringBuilder rowData = new StringBuilder();
                for (int j = 0; j < borrowTableModel.getColumnCount(); j++) {
                    rowData.append(borrowTableModel.getValueAt(i, j));
                    if (j < borrowTableModel.getColumnCount() - 1) {
                        rowData.append(",");
                    }
                }
                writer.write(rowData.toString());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
