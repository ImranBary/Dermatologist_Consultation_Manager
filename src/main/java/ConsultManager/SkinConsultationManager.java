package ConsultManager;

import java.io.IOException;

public interface SkinConsultationManager {
    public int MAX_DOCS  = 10;
    public void addDoctor(Doctor doctor) throws IOException;
    public void deleteDoctor(String licenceNum);
    public void printListOfDoctors();
    public void saveFile() throws IOException;


}
