package ConsultManager;


import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{
    static Scanner input = new Scanner(System.in);
    private String fileName = "doctors.txt";
    //keeps info of the docs
    private ArrayList<Doctor> doctorsInfo = new ArrayList<>();
    public WestminsterSkinConsultationManager() throws IOException {
        //create the file
        File doctors = new File(fileName);
        doctors.createNewFile();
        //code reads in each entry from the file and puts it in an array list
        Scanner scanner = new Scanner(new File(fileName));
        while(scanner.hasNext()){
            String s = scanner.nextLine();
            if(s.length() != 0 ){
                String[] ss = s.split("#");
                doctorsInfo.add(new Doctor(ss[0],ss[1],ss[2],ss[3],ss[4]));
            }
        }
        scanner.close();
    }

    @Override
    public void addDoctor(Doctor doctor) {
        //make doc object with the above inputs, better for vacillation
        //add info to array if we're not yet at max capacity
        if (doctorsInfo.size() < MAX_DOCS){
            doctorsInfo.add(doctor);
        }else {
            System.out.println("You have too many doctors");
        }
    }

    @Override
    public void deleteDoctor(ArrayList<Doctor> doctors,String licenceNum) {
        //loop through
        boolean removedFlag = false;
        for (int i = 0; i<doctors.size();i++){
            //temp Doc
            Doctor temp = doctors.get(i);
            if (temp.getLicenceNum().equals(licenceNum)) {
                //check if the input licence number exists as a substring
                System.out.println(temp.toString() + "  - IS REMOVED");
                System.out.println(doctors.size()+" - Doctor(s) remaining.");
                //remove if so
                doctors.remove(i);
                removedFlag = true;
            }

            if(removedFlag == false)
                System.out.println("Doctor does not exist with that licence number");

        }
    }

    @Override
    public void printListOfDoctors(ArrayList<Doctor> doctors) {
        Collections.sort(doctors);
        for (Doctor doc: doctors){
            System.out.println(doc.forPrint());
        }

    }

    @Override
    public void saveFile(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for(Doctor doc:doctorsInfo){
            writer.write(doc.toString()+System.lineSeparator());
        }
        writer.close();
    }

    public static void runGui(ArrayList<Doctor> doc ) throws IOException, GeneralSecurityException {
        ConsultManagerGui g = new ConsultManagerGui(doc);
        g.setTitle("Consultation manager");
        g.setSize(400,400);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.setVisible(true);
    }
    public void menu(String choice) throws IOException, GeneralSecurityException {
        switch(choice){
            case "A":
                System.out.println("Add doc");
                //take surname,name,telnum,licenceNum,specialisation as inputs
                System.out.println("Enter the name:");
                String name = input.nextLine();
                System.out.println("Enter the surname:");
                String sunrname = input.nextLine();
                System.out.println("Enter the telephone number:");
                String telNum = input.nextLine();
                System.out.println("Enter the licence number:");
                String licenceNum = input.nextLine();
                System.out.println("Enter the specialisation:");
                String specialisation = input.nextLine();
                addDoctor(new Doctor(sunrname,name,telNum,licenceNum,specialisation));
                //take in details
                break;
            case "D":
                System.out.println("Enter the licence number of the doctor you wish to delete");
                String licence = input.next();
                deleteDoctor(doctorsInfo,licence);
                break;
            case "P":
                System.out.println("Print docs");
                printListOfDoctors(doctorsInfo);
                break;
            case "S":
                System.out.println("Save to file");
                saveFile(fileName);
                break;
            case "G":
                System.out.println("Gui");
                runGui(doctorsInfo);
                break;
            case "Q":
                System.out.println("You selected Quit, press S to save to not lose your data \n" +
                        "or press anything else to Quit without saving.");
                String saveOrQuit = input.next();
                if (saveOrQuit.toUpperCase().equals("S")){
                    saveFile(fileName);
                    System.out.println("You saved the file");
                }else{
                    System.out.println("You quit without saving");
                }
                break;
        }
    }
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        WestminsterSkinConsultationManager w = new WestminsterSkinConsultationManager();
        String choice;
        do{
            //print a menu of options
            menuPrint();
            choice = input.nextLine().toUpperCase();
            w.menu(choice);
        }while (!choice.equals("Q"));
    }
    public static void menuPrint() {
        System.out.println("\n\nPlease enter a choice:");
        System.out.println("A: Add Doctor");
        System.out.println("D: Delete a Doctor");
        System.out.println("P: Print list of all Doctors");
        System.out.println("S: Store program data into file");
        System.out.println("G: Launch GUI");
        System.out.println("Q: To Quit program\n");
    }
    public ArrayList getDocArray(){
        return doctorsInfo;
    }
}
