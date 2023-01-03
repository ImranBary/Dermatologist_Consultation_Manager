package ConsultManager;

import java.io.IOException;
import java.util.ArrayList;

public interface SkinConsultationManager {
    public int MAX_DOCS  = 10;
    public void addDoctor(ArrayList<Doctor> doctorsInfo, int MAX_DOCS) ;
    public void deleteDoctor(ArrayList<Doctor>doctors,String licenceNum);
    public void printListOfDoctors(ArrayList<Doctor> doctors);
    public void saveFile(String fileName) throws IOException;


}
