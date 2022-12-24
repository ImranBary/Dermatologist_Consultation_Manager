package ConsultManager;

public class Date {


    private int day;
    private int month;
    private int year;
    private int hours;
    private int min;
    public Date(){

    }
    public Date(int day,int month,int year, String time){
        setDate(day,month,year);
        setTime(time);
    }
    public Date(int day,int month,int year ){
        setDate(day,month,year);

    }

    public void setDate(int day,int month,int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setTime(String time) {
        String[] hourAndMin = time.split(":");
        this.hours = Integer.parseInt(hourAndMin[0]);
        this.min = Integer.parseInt(hourAndMin[1]);
    }

    public String toString(){
        return  String.format("%d/%d/%d - %d:%d",day,month,year,hours,min);
    }
    public String getDate(){
        return  String.format("%d/%d/%d",day,month,year);
    }
    public int[] getTime(){
        return new int[]{hours,min};
    }
}
