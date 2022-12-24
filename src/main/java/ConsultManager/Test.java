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
//
//        ArrayList<ArrayList<String>> arr = new ArrayList<>();
//
//        ArrayList<String> temp = new ArrayList<>();
//        Scanner input = new Scanner(System.in);
//
//        String name = "Imran";
//
//        String surname = "Bary";
//
//        String tel ="0127732";
//        temp.add(name);
//        temp.add(surname);
//        temp.add(tel);
//        temp.add(tel);
//        temp.add(tel);
//
//        arr.add(temp);
//        arr.add(temp);
//
//        ArrayList<String> s = arr.get(0);
//        //System.out.println(s);
//        Doctor doc = new Doctor(s.get(0),s.get(1),s.get(2),s.get(3),s.get(4));
        String ss = "10:45";
        String[] s = ss.split(":");
        Random rnd = new Random();
        System.out.println(getRandomWithExclusion(rnd,0,3,2));

    }
    public static int getRandomWithExclusion(Random rnd, int start, int end, int... exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }

}
