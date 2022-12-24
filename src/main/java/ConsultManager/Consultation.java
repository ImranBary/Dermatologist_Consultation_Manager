package ConsultManager;



public class Consultation {
    private Date date;
    private Patient patient;
    private Doctor doctor;
    private int cost;
    private String notes;
    public Consultation(){
        //empty constructor
    }
    public Consultation(Doctor doctor,Date date,Patient patient, String notes){
        this.doctor = doctor;
        this.date = date;
        this.patient = patient;
        this.notes = notes;
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

    @Override
    public String toString() {
        return String.format("Doctor %s Patient %s \nIs booked at %d:%d Cost = £%d",doctor.forDropDownList()
                ,patient.toString(),date.getTime()[0],date.getTime()[1],cost);
    }
}
