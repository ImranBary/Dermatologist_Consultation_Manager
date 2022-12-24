package ConsultManager;

public class Person {
    private String name;
    private String surname;
    private Date dateOfBrth;
    private String telNum;

    public Person(){
    }
    public Person(String name,String surname,String telNum){
        setName(name);
        setSurname(surname);
        setTelNum(telNum);
    }
    public Person(String name,String surname,String telNum,Date dateOfBrth){
        setName(name);
        setSurname(surname);
        setTelNum(telNum);
        setDateOfBrth(dateOfBrth);
    }

    public void setDateOfBrth(Date dateOfBrth) {
        this.dateOfBrth = dateOfBrth;
    }
    public String getDateOfBrth() {
        return dateOfBrth.getDate();
    }
    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getTelNum() {
        return telNum;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname.toUpperCase();
    }
}
