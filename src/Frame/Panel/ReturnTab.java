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
import java.util.ArrayList;
import java.util.List;

public class ReturnTab extends JPanel implements ActionListener, KeyListener {
    private final JButton returnAddButton;
    private final JTable returnTable;
    private final JTextField searchField;
    private DefaultTableModel returnTableModel;
    private String name;
    private String returnPath;
    private String borrowPath;
    private HomeTab homeTab;
    private BorrowTab borrowTab;

    public ReturnTab(String name) {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setPreferredSize(new Dimension(940, 100));

        JLabel returnLabel = new JLabel("Return Books");
        returnLabel.setFont(Utils.BIG_BOLD_FONT);
        topPanel.add(returnLabel);

        searchField = new JTextField();
        searchField.setFont(Utils.BIG_FONT);
        searchField.setPreferredSize(new Dimension(300, 60));
        searchField.addKeyListener(this);
        topPanel.add(searchField);

        this.add(topPanel, BorderLayout.NORTH);


        String[] columnTitle = {"ID", "Name", "Author", "Publisher", "Return Date"};
        returnTableModel = new DefaultTableModel(columnTitle, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String returnPath = "src/data/library/" + name + "_return.txt";
        File returnFile = new File(returnPath);

        try {
            if (!returnFile.getParentFile().exists()) {
                returnFile.getParentFile().mkdir();
                returnFile.createNewFile();
            } else if (!returnFile.exists()) {
                returnFile.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(returnFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                returnTableModel.addRow(rowData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        returnTable = new JTable(returnTableModel);
        returnTable.setFont(Utils.NORMAL_FONT);
        returnTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader tableHeader = new JTableHeader(returnTable.getColumnModel());
        tableHeader.setFont(Utils.SMALL_FONT);
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 40));
        returnTable.setTableHeader(tableHeader);

        int rowHeight = 30;
        returnTable.setRowHeight(rowHeight);

        int[] columnWidths = {50, 100, 150, 150, 120};
        for (int i = 0; i < columnWidths.length; i++) {
            returnTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        JScrollPane returnPane = new JScrollPane(returnTable);
        returnPane.setPreferredSize(new Dimension(500, 300));
        this.add(returnPane, BorderLayout.CENTER);

        JPanel returnAdd = new JPanel();
        returnAdd.setPreferredSize(new Dimension(940, 200));
        returnAddButton = new JButton("Return Book");
        returnAddButton.setFont(Utils.BIG_BOLD_FONT);
        returnAddButton.addActionListener(this);
        returnAdd.add(returnAddButton);
        this.add(returnAdd, BorderLayout.SOUTH);

        this.name = name;
    }

    public void setHomeTab(HomeTab homeTab) {
        this.homeTab = homeTab;
    }

    public void setBorrowTab(BorrowTab borrowTab) {
        this.borrowTab = borrowTab;
    }

    public int getReturnTableRowCount() {
        return returnTable.getRowCount();
    }

    public void updateData() {
        returnTableModel.setRowCount(0); // Clear existing data

        String returnPath = "src/data/library/" + name + "_return.txt";
        File returnFile = new File(returnPath);

        try {
            if (!returnFile.getParentFile().exists()) {
                returnFile.getParentFile().mkdir();
                returnFile.createNewFile();
            } else if (!returnFile.exists()) {
                returnFile.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(returnFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                returnTableModel.addRow(rowData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Return Tab updated.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnAddButton) {
            borrowPath = "src/data/library/books.txt";
            returnPath = "src/data/library/" + name + "_return.txt";

            int selectedRow = returnTable.getSelectedRow();
            if (selectedRow != -1) {
                int idColumnIndex = returnTableModel.findColumn("ID");
                int id = Integer.parseInt(returnTableModel.getValueAt(selectedRow, idColumnIndex).toString());

                List<String> returnData = new ArrayList<>();
                List<String> borrowData = new ArrayList<>();
                try {
                    // Remove Row from Table
                    File returnFile = new File(returnPath);
                    if (!returnFile.getParentFile().exists()) {
                        returnFile.getParentFile().mkdir();
                        returnFile.createNewFile();
                    } else if (!returnFile.exists()) {
                        returnFile.createNewFile();
                    }
                    BufferedReader returnReader = new BufferedReader(new FileReader(returnFile));
                    String line;
                    while ((line = returnReader.readLine()) != null) {
                        returnData.add(line);
                    }
                    returnReader.close();
                    returnData.remove(selectedRow);
                    BufferedWriter returnWriter = new BufferedWriter(new FileWriter(returnFile));
                    for (int i = 0; i < returnData.size(); i++) {
                        returnWriter.write(returnData.get(i));
                        returnWriter.newLine();
                    }
                    returnWriter.flush();
                    returnWriter.close();
                    returnTableModel.removeRow(selectedRow);

                    // Update Borrow list
                    BufferedReader reader = new BufferedReader(new FileReader(borrowPath));
                    while ((line = reader.readLine()) != null) {
                        borrowData.add(line);
                    }
                    reader.close();
                    for (int i = 0; i < borrowData.size(); i++) {
                        String[] rowData = borrowData.get(i).split(",");
                        int foundId = Integer.parseInt(rowData[0]);
                        if (foundId == id) {
                            int issuedCount = Integer.parseInt(rowData[5]);
                            issuedCount++;
                            rowData[5] = String.valueOf(issuedCount);
                            String updatedLine = String.join(",", rowData);
                            borrowData.set(i, updatedLine);
                            BufferedWriter writer = new BufferedWriter(new FileWriter(borrowPath));
                            for (int j = 0; j < borrowData.size(); j++) {
                                writer.write(borrowData.get(j));
                                writer.newLine();
                            }
                            writer.flush();
                            writer.close();
                            break;
                        }
                    }

                    // Reload Borrow Panel
                    borrowTab.updateData();
                    homeTab.updateBookCount();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
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
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(returnTableModel);
        returnTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(searchField.getText()));
    }
}
