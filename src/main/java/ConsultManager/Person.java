package ConsultManager;

public class Person {
    private String name;
    private String surname;
    private DoB dateOfBrth;
    private String telNum;

    public Person(){
    }
    public Person(String name,String surname,String telNum){
        setName(name);
        setSurname(surname);
        setTelNum(telNum);
    }

    public void setDateOfBrth(DoB dateOfBrth) {
        this.dateOfBrth = dateOfBrth;
    }
    public DoB getDateOfBrth() {
        return dateOfBrth;
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
