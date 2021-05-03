package view;

import javax.swing.*;

public class Table extends JTable {
    public Table(Object[][] data, String[] column){
       super(data,column);
       this.setBounds(30, 40, 200, 300);


    }
    /**
     * Constructors in JTable:
     *
     *     JTable(): A table is created with empty cells.
     *     JTable(int rows, int cols): Creates a table of size rows * cols.
     *     JTable(Object[][] data, Object []Column): A table is created with the specified name where []Column defines the column names.
     *
     * Functions in JTable:
     *
     *     addColumn(TableColumn []column) : adds a column at the end of the JTable.
     *     clearSelection() : Selects all the selected rows and columns.
     *     editCellAt(int row, int col) : edits the intersecting cell of the column number col and row number row programmatically, if the given indices are valid and the corresponding cell is editable.
     *     setValueAt(Object value, int row, int col) : Sets the cell value as ‘value’ for the position row, col in the JTable.
     */
}
class TestTable{
    public static void main(String[] args) {
       JFrame frame = new JFrame();
       JScrollPane scrollPane = new JScrollPane();
        frame.setTitle("JTable Example");

        // Data to be displayed in the JTable
        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" }
        };

        // Column Names
        String[] columnNames = { "Name", "Roll Number", "Department" };

        // Initializing the JTable
        JTable table = new JTable(data, columnNames);
        table.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        // Frame Size
        frame.setSize(500, 200);
        // Frame Visible = true
        frame.setVisible(true);
    }
}