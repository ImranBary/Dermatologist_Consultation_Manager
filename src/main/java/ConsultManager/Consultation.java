package ConsultManager;


import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AeadFactory;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.subtle.AesGcmJce;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class Consultation {
    private Date date;
    private Patient patient;
    private Doctor doctor;
    private int cost;

    /*https://stackoverflow.com/questions/55070513/how-to-easily-encrypt-and-decrypt-a-string-using-tink*/
    private String encryptionKey = "2s5v8y/B?E(G+KbPeShVmYq3t6w9z$C&";
    private byte[] encryptedConsultationNotes;


    private AesGcmJce agjEncryption = new AesGcmJce(encryptionKey.getBytes());
    public Consultation() throws GeneralSecurityException {
        //empty constructor
    }
    public Consultation(Doctor doctor,Date date,Patient patient, String notes) throws GeneralSecurityException {
        this.doctor = doctor;
        this.date = date;
        this.patient = patient;
        setNotes(notes);
    }
    public Consultation(Doctor doctor,Date date,Patient patient, int Cost, String notes) throws GeneralSecurityException {
        this.doctor = doctor;
        this.date = date;
        this.patient = patient;
        this.cost = cost;
        setNotes(notes);
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setCost(int cost){
        this.cost = cost;
    }
    public int getCost(){
        return cost;
    }

    /**
     *Method to set the consultation Notes and encrypt it
     * @param notes
     * @throws GeneralSecurityException
     */
    public void setNotes(String notes) throws GeneralSecurityException {
        try {
            encryptedConsultationNotes = agjEncryption.encrypt(notes.getBytes(), null);
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Method to get consultation notes and decrypt it
     * @return decrypted consultation notes string
     * @throws GeneralSecurityException
     */
    public String getNotes() throws GeneralSecurityException {
        byte[] decryptedConsultationNotes;
        try {
            decryptedConsultationNotes = agjEncryption.decrypt(encryptedConsultationNotes, null);
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
        return new String(decryptedConsultationNotes,StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        try {
            return String.format("Doctor %s Patient %s \nIs booked at %d:%d Cost = £%d\nConsultation Notes: %s",doctor.forDropDownList()
                    ,patient.toString(),date.getTime()[0],date.getTime()[1],cost,getNotes());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
