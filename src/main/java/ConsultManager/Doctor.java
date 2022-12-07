package ConsultManager;

public class Doctor extends Person implements Comparable<Doctor>{
    private String licenceNum;
    private String specialisation;
    public Doctor(){
        super();
    }
    public Doctor( String name,String surname, String telNum, String licenceNum, String specialisation){
        super(name,surname,telNum);
        setLicenceNum(licenceNum);
        setSpecialisation(specialisation);//for the validation
        //this.specialisation = specialisation;
    }

    public String getLicenceNum() {
        return licenceNum;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setLicenceNum(String licenceNum) {
        this.licenceNum = licenceNum;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    @Override
    public String toString(){
        return getSurname()+"#"+getName()+"#"+getLicenceNum()+"#"+getTelNum()+"#"+getSpecialisation();
    }

    //to sort from Doctor's surnames
    @Override
    public int compareTo(Doctor doctor) {
        return (this.getSurname().compareTo(doctor.getSurname()));
    }
}
