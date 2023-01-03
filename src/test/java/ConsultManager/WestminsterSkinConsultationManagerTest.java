package ConsultManager;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class WestminsterSkinConsultationManagerTest {

    @Test
    void addDoctorPaul() throws IOException {
        WestminsterSkinConsultationManager w = new WestminsterSkinConsultationManager();
        ArrayList<Doctor> doc = w.getDocArray();
        doc.add(new Doctor("McCartney","Paul","071283218","001","Srg Pepper",new Date(12,12,1956)));
        //check the last index of the array
        assertEquals("001",doc.get(doc.size()-1).getLicenceNum());

    }
    @Test
    void addDoctorJohn() throws IOException {
        WestminsterSkinConsultationManager w = new WestminsterSkinConsultationManager();
        ArrayList<Doctor> doc = w.getDocArray();
        doc.add(new Doctor("Lennon","John","071282181","009","The walrus",new Date(12,12,1956)));
        //check the last index of the array
        assertEquals("The walrus",doc.get(doc.size()-1).getSpecialisation());

    }
    @Test
    void deleteDoctorGeorge() throws IOException {
        //first we need to add George
        WestminsterSkinConsultationManager w = new WestminsterSkinConsultationManager();
        ArrayList<Doctor> doc = w.getDocArray();
        doc.add(new Doctor("Harrison","George","07128323","010","Something",new Date(12,12,1956)));
        //deleting George from his licence number
        w.deleteDoctor(doc,"010");
        //confirm that the last doctor's name in the array is not George
        assertNotEquals("George",doc.get(doc.size()-1).getName());
    }
    @Test
    void saveFile() throws IOException {
        WestminsterSkinConsultationManager w = new WestminsterSkinConsultationManager();
        String fileName = "doctors.txt";
        //save file
        w.saveFile(fileName);
        //check if file exist with that name
        File file = new File(fileName);
        assertTrue(file.exists());

    }
    @Test
    void settingDoctorTelNumToString()  {
        Doctor d = new Doctor();
        assertThrows(NumberFormatException.class,() ->{
            d.setTelNum("DefinitelyNotANumber");
        });
    }
    @Test
    void settingDoctorLicenceNumToString()  {
        Doctor d = new Doctor();
        assertThrows(NumberFormatException.class,() ->{
            d.setLicenceNum("DefinitelyNotANumber");
        });
    }



}