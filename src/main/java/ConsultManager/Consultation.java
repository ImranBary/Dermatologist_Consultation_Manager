package ConsultManager;



public class Consultation {
    private Date date;
    private Patient patient;
    private Doctor doctor;
    private int cost;
    private String notes;
    public Consultation(){

    }
    public Consultation(Doctor doctor,Date date,Patient patient, int Cost, String notes){
        this.doctor = doctor;
        this.date = date;
        this.patient = patient;
        this.cost = cost;
        this.notes = notes;
    }
    public void setDate(Date date) {
        this.date = date;
    }
//hello
    public void something(){

    }
    public Date getDate() {
        return date;
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


}
