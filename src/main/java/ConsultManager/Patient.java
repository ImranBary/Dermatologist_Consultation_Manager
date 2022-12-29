package ConsultManager;

public class Patient extends Person{
    private String iD;
    public Patient(){super();}
    public Patient(String surname,String name, String telNum,String iD){
        super(name,surname,telNum);
        setiD(iD);
    }
    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }
    @Override
    public String toString(){
        return String.format("%s %s %s %s %s",
                getSurname(),getName(),getTelNum(),getDateOfBrth(),getiD());

    }
}
