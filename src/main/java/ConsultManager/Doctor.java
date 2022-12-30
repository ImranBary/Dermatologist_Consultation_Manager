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
        setSpecialisation(specialisation);

    }
    public Doctor( String name,String surname, String telNum, String licenceNum, String specialisation,Date dob){
        super(name,surname,telNum,dob);
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

    /**
     * Method to set licence number - only accepting a parsable integer in string format
     * @param licenceNum
     */
    public void setLicenceNum(String licenceNum) {
        try{
            Integer.parseInt(licenceNum);
            this.licenceNum = licenceNum;
        }catch(NumberFormatException nfe){
            throw new NumberFormatException(nfe.getMessage());
        }

    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    @Override
    public String toString(){
        return getSurname()+"#"+getName()+"#"+getLicenceNum()+"#"+getTelNum()+"#"+getSpecialisation()+"#"+getDateOfBrth();
    }
    //needs dob
    public String forPrint(){
        return String.format("Doctor: Surname %s FirstName %s Date Of Birth %s LicenceNum %s TelNum %s Specialisation %s",
                getSurname(),getName(),getDateOfBrth(),getLicenceNum(),getTelNum(),getSpecialisation());

    }
    public String forDropDownList(){
        return String.format("%s %s %s",
                getSurname(),getName(),getLicenceNum());

    }
    //to sort from Doctor's surnames
    @Override
    public int compareTo(Doctor doctor) {
        return (this.getSurname().compareTo(doctor.getSurname()));
    }

}
