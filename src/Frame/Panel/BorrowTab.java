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
import java.util.ArrayList;
import java.util.List;

public class BorrowTab extends JPanel implements ActionListener, KeyListener {
    private final DefaultTableModel borrowTableModel;
    private final JButton borrowAddButton;
    private final JTable borrowTable;
    private String borrowPath;
    private String returnPath;
    private String name;
    private HomeTab homeTab;
    private ReturnTab returnTab;
    private JTextField searchField;

    public BorrowTab(String name) {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(940, 100));

        JLabel borrowLabel = new JLabel("Borrow Books");
        borrowLabel.setFont(Utils.BIG_BOLD_FONT);
        topPanel.add(borrowLabel);

        searchField = new JTextField();
        searchField.setFont(Utils.BIG_FONT);
        searchField.setPreferredSize(new Dimension(300, 60));
        searchField.addKeyListener(this);
        topPanel.add(searchField);

        this.add(topPanel, BorderLayout.NORTH);

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
        tableHeader.setFont(Utils.SMALL_FONT);
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 40));
        borrowTable.setTableHeader(tableHeader);

        int rowHeight = 30;
        borrowTable.setRowHeight(rowHeight);

        int[] columnWidths = {50, 100, 150, 150, 80, 80, 120};
        for (int i = 0; i < columnWidths.length; i++) {
            borrowTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        JScrollPane borrowPane = new JScrollPane(borrowTable);
        borrowPane.setPreferredSize(new Dimension(600, 300));
        this.add(borrowPane, BorderLayout.CENTER);

        JPanel borrowAdd = new JPanel();
        borrowAdd.setPreferredSize(new Dimension(940, 200));
        borrowAddButton = new JButton("Borrow Book");
        borrowAddButton.setFont(Utils.BIG_BOLD_FONT);
        borrowAddButton.addActionListener(this);
        borrowAdd.add(borrowAddButton);
        this.add(borrowAdd, BorderLayout.SOUTH);

        this.name = name;
    }

    public void setHomeTab(HomeTab homeTab) {
        this.homeTab = homeTab;
    }

    public void setReturnTab(ReturnTab returnTab) {
        this.returnTab = returnTab;
    }

    public void updateData() {
        borrowTableModel.setRowCount(0); // Clear existing data

        String borrowPath = "src/data/library/books.txt";
        File borrowFile = new File(borrowPath);

        try {
            if (!borrowFile.getParentFile().exists()) {
                borrowFile.getParentFile().mkdir();
                borrowFile.createNewFile();
            } else if (!borrowFile.exists()) {
                borrowFile.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(borrowFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                borrowTableModel.addRow(rowData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Borrow Tab updated.");
    }

    public int getTotalBookCount() {
        int total = 0;
        int quantityColumnIndex = borrowTableModel.findColumn("Quantity");

        for (int i = 0; i < borrowTableModel.getRowCount(); i++) {
            int quantity = Integer.parseInt(borrowTableModel.getValueAt(i, quantityColumnIndex).toString());
            total += quantity;
        }

        return total;
    }

    public int getAvailableBooKCount() {
        int total = 0;
        int issueColumnIndex = borrowTableModel.findColumn("Available");

        for (int i = 0; i < borrowTableModel.getRowCount(); i++) {
            int quantity = Integer.parseInt(borrowTableModel.getValueAt(i, issueColumnIndex).toString());
            total += quantity;
        }

        return total;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == borrowAddButton) {
            borrowPath = "src/data/library/books.txt";
            returnPath = "src/data/library/" + name + "_return.txt";

            int selectedRow = borrowTable.getSelectedRow();
            if (selectedRow != -1) {
                int issuedColumnIndex = borrowTableModel.findColumn("Available");
                int issuedCount = Integer.parseInt(borrowTableModel.getValueAt(selectedRow, issuedColumnIndex).toString());

                if (issuedCount > 0) {
                    issuedCount--;
                    if (returnTab.getReturnTableRowCount() < 5) {
                        borrowTable.setValueAt(issuedCount, selectedRow, issuedColumnIndex);
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "You already borrowed maximum about of books.",
                                "Unavailable",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "No available copies to borrow for this book.",
                            "Unavailable",
                            JOptionPane.WARNING_MESSAGE
                    );
                }

                List<String> lines = new ArrayList<>();
                try {
                    File borrowFile = new File(returnPath);
                    if (!borrowFile.getParentFile().exists()) {
                        borrowFile.getParentFile().mkdir();
                        borrowFile.createNewFile();
                    } else if (!borrowFile.exists()) {
                        borrowFile.createNewFile();
                    }

                    BufferedReader reader = new BufferedReader(new FileReader(borrowPath));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                    reader.close();
                    String[] rowData = lines.get(selectedRow).split(",");
                    rowData[5] = String.valueOf(issuedCount);
                    String updatedLine = String.join(",", rowData);
                    lines.set(selectedRow, updatedLine);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(borrowPath));
                    for (int i = 0; i < lines.size(); i++) {
                        writer.write(lines.get(i));
                        writer.newLine();
                    }
                    writer.flush();
                    writer.close();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String returnDate = LocalDate.now().plusDays(30).format(formatter);
                    String returnLine = String.join(",", rowData[0], rowData[1], rowData[2], rowData[3], returnDate);
                    BufferedWriter returnWriter = new BufferedWriter(new FileWriter(borrowFile, true));
                    returnWriter.write(returnLine);
                    returnWriter.newLine();
                    returnWriter.flush();
                    returnWriter.close();

                    returnTab.updateData();
                    homeTab.updateBookCount();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else {

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
