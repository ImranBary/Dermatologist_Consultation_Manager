package ConsultManager;

import java.io.IOException;
import java.util.*;


public class Test {

    public static void main(String[] args)throws IOException {
//        WestminsterSkinConsultationManager w = new WestminsterSkinConsultationManager();
//        String s = "James#Paul Mccartney#12013#07564261192#Sgt pepper";
//        String[] arr = s.split("#");
//        System.out.println(arr[4]);
//        for(String ss: arr){
//            System.out.println(ss);
//        }

        ArrayList<ArrayList<String>> arr = new ArrayList<>();

        ArrayList<String> temp = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        String name = "Imran";

        String surname = "Bary";

        String tel ="0127732";
        temp.add(name);
        temp.add(surname);
        temp.add(tel);
        temp.add(tel);
        temp.add(tel);

        arr.add(temp);
        arr.add(temp);

        ArrayList<String> s = arr.get(0);
        //System.out.println(s);
        Doctor doc = new Doctor(s.get(0),s.get(1),s.get(2),s.get(3),s.get(4));


    }
}
