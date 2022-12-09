package ConsultManager;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DoctorTableModel extends AbstractTableModel {
    private ArrayList<Doctor> doctorsInfo;
    private String[] columnNames = {"Name","Surname", "TelNum", "LicenceNum",  "Specialisation"};

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
            case 0:
                temp = doctorsInfo.get(rowIndex).getName();
                break;
            case 1:
                temp = doctorsInfo.get(rowIndex).getSurname();
                break;
            case 2:
                temp = doctorsInfo.get(rowIndex).getTelNum();
                break;
            case 3:
                temp = doctorsInfo.get(rowIndex).getLicenceNum();
                break;
            case 4:
                temp = doctorsInfo.get(rowIndex).getSpecialisation();
                break;
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
}
