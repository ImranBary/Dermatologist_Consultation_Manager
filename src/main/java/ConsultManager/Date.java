package ConsultManager;

public class Date {
    private int date;
    private int time;
    public Date(){

    }
    public Date(int date, int time){
        this.date = date;
        this.time = time;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public String toString(){
        return date + " " + time;
    }
}
