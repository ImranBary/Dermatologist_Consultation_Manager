package ConsultManager;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

public class DoctorTableModel extends AbstractTableModel {
    private ArrayList<Doctor> doctorsInfo;
    private String[] columnNames = {"Name","Surname","DOB", "TelNum", "LicenceNum",  "Specialisation"};

    public DoctorTableModel(ArrayList<Doctor> doctorsInfo){
        this.doctorsInfo = doctorsInfo;
    }
    @Override
    public int getRowCount() {
        return doctorsInfo.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        switch(columnIndex) {
            case 0 -> temp = doctorsInfo.get(rowIndex).getName();
            case 1 -> temp = doctorsInfo.get(rowIndex).getSurname();
            case 2 -> temp = doctorsInfo.get(rowIndex).getDateOfBrth();
            case 3 -> temp = doctorsInfo.get(rowIndex).getTelNum();
            case 4 -> temp = doctorsInfo.get(rowIndex).getLicenceNum();
            case 5 -> temp = doctorsInfo.get(rowIndex).getSpecialisation();
        }
        return temp;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    public static void displayTable(ArrayList<Doctor> doctorsInfo){
        DoctorTableModel tableModel = new DoctorTableModel(doctorsInfo);
        JTable myTable = new JTable(tableModel);

        //handles the sorting by first name
        TableRowSorter<DoctorTableModel> sorter = new TableRowSorter<>(tableModel);
        myTable.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        int columnIndexToSort = 0;
        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();

        JScrollPane panel = new JScrollPane(myTable);
        JFrame myFrame = new JFrame();
        //myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(panel);
        myFrame.setVisible(true);
        myFrame.setSize(500,400);
    }

}
