package ConsultManager;
import com.google.crypto.tink.subtle.AesGcmJce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Random;
public class ConsultManagerGui extends JFrame implements ActionListener {
    private JButton view;
    private JButton checkAvailability;
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private JComboBox yearComboBox;
    private JComboBox time;
    private JComboBox ageDayComboBox;
    private JComboBox ageMonthComboBox;
    private JComboBox ageYearComboBox;
    private JLabel doctorDetails;
    private JLabel consultDateTime;
    private JLabel availabilityPrompt;
    private JTextField patientName;
    private JTextField patientSurname;
    private JTextField patientTelNum;
    private JTextField patientId;
    private JTextField consultNotes;
    private JButton submit;
    private JButton previewConsultation;
    private WestminsterSkinConsultationManager w = new WestminsterSkinConsultationManager();
    private int selectedDocIndex;
    private int selectedDayIndex;
    private int selectedMonthIndex;
    private int selectedYearIndex;
    private int selectedTimeIndex;
    private ArrayList<Doctor> doc = w.getDocArray();
    private ArrayList<Consultation> consultations = new ArrayList<>();
    private String encryptionKey = "2s5v8y/B?E(G+KbPeShVmYq3t6w9z$C&";
    private AesGcmJce agjEncryption = new AesGcmJce(encryptionKey.getBytes());
    public ConsultManagerGui(ArrayList<Doctor> d) throws IOException, GeneralSecurityException {
        super("Consultation Manager");
        JPanel doctorSelectPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        view = new JButton("view Doctors");
        //setSize(view);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 3;
        //view doctors list on a separate window
        doctorSelectPanel.add(view,c);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dispose();
                DoctorTableModel.displayTable(d);
            }
        });
        //creates and reads in data from Object doctor array list
        String [] docArrayList = new String [doc.size()+1];
        docArrayList[0] = "Select Doctor";
        for(int i = 0; i < doc.size(); i++){
            docArrayList[i+1] = doc.get(i).forDropDownList();
        }
        JComboBox<String> jComboBox = new JComboBox<>(docArrayList);
        //setSize(jComboBox);
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 4;
        doctorSelectPanel.add(jComboBox,c);
        JLabel dateLabel = new JLabel("Date");
        setSize(dateLabel);
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 2;
        doctorSelectPanel.add(dateLabel,c);
        String[] day = new String[32];
        String[] month = new String[13];
        String[] year = new String[11];
        day[0] = "DD";
        month[0] = "MM";
        year[0] = "YYYY";
        for (int i = 1; i < day.length; i++){
            day[i] = String.valueOf(i);
        }
        for (int i = 1; i < month.length; i++){
            month[i] = String.valueOf(i);
        }
        for (int i = 1; i < year.length; i++){
            year[i] = String.valueOf(i+2021);
        }
        dayComboBox = new JComboBox(day);
        monthComboBox = new JComboBox(month);
        yearComboBox = new JComboBox(year);
        JPanel datePanel = new JPanel(new FlowLayout());
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);
        //setSize(datePanel);
        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 2;
        doctorSelectPanel.add(datePanel,c);

        JLabel timePanel = new JLabel("Time");
        setSize(timePanel);
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 2;
        doctorSelectPanel.add(timePanel,c);
        String[] timeArr = {"9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30"
                ,"14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00"};
        time = new JComboBox<>(timeArr);
        setSize(timePanel);
        c.gridx = 2;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 2;
        doctorSelectPanel.add(time,c);
        //time = new JTextField(5);
        availabilityPrompt = new JLabel();
        checkAvailability = new JButton("Check Availability");
        checkAvailability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset availability prompt - in case this is already filled
                availabilityPrompt.setText("");
                //retrieve information from combo boxes user selections
                selectedDocIndex = jComboBox.getSelectedIndex();
                selectedDayIndex = dayComboBox.getSelectedIndex();
                selectedMonthIndex = monthComboBox.getSelectedIndex();
                selectedYearIndex = yearComboBox.getSelectedIndex();
                selectedTimeIndex = time.getSelectedIndex();
                //prepare user selections to string to put into corresponding jlabel text
                String docDetails = jComboBox.getItemAt(selectedDocIndex);
                String dateDetails = dayComboBox.getItemAt(selectedDayIndex)+
                        "/"+ monthComboBox.getItemAt(selectedMonthIndex)+
                        "/"+ yearComboBox.getItemAt(selectedYearIndex);
                //performs check if doctor is busy at the date and time
                boolean docNotBusy = true;
                for (int i = 0; i < consultations.size(); i++){
                    String[] hourAndMin = timeArr[selectedTimeIndex].split(":");
                    if(
                        //Compare the doctor Licences
                    consultations.get(i).getDoctor().getLicenceNum().equals(doc.get(selectedDocIndex-1).getLicenceNum())
                        //compare the two dates
                    && consultations.get(i).getDate().getDate().equals(dateDetails)
                        //compare the hour
                    && consultations.get(i).getDate().getTime()[0]== Integer.parseInt(hourAndMin[0])
                        //compare the min
                    && consultations.get(i).getDate().getTime()[1]== Integer.parseInt(hourAndMin[1])){
                        //if true set docNotBusy to false
                        docNotBusy = false;
                    }
                }
                if(docNotBusy){
                    //if the doctor is available continue with user selections
                    availabilityPrompt.setText("Available");
                    availabilityPrompt.setForeground(Color.green);
                    setSize(availabilityPrompt);
                    c.gridx = 0;
                    c.gridy = 4;
                    c.gridheight = 1;
                    c.gridwidth = 2;
                    doctorSelectPanel.add(availabilityPrompt,c);
                    doctorDetails.setText(docDetails);
                    consultDateTime.setText(dateDetails);
                    jComboBox.setEnabled(false);
                    dayComboBox.setEnabled(false);
                    monthComboBox.setEnabled(false);
                    yearComboBox.setEnabled(false);
                    time.setEnabled(false);
                }else {
                    //if doc not available randomise doc selection
                    availabilityPrompt.setText("Not Available - Doctor Randomised");
                    Random rnd = new Random();
                    //get random doc excluding the already selected
                    int newSelectedDocIndex = getRandomWithExclusion(rnd,1,docArrayList.length-1,selectedDocIndex);
                    selectedDocIndex = newSelectedDocIndex;
                    docDetails = jComboBox.getItemAt(selectedDocIndex);
                    availabilityPrompt.setForeground(Color.red);
                    setSize(availabilityPrompt);
                    c.gridx = 0;
                    c.gridy = 4;
                    c.gridheight = 1;
                    c.gridwidth = 2;
                    doctorSelectPanel.add(availabilityPrompt,c);
                    doctorDetails.setText(docDetails);
                    consultDateTime.setText(dateDetails);
                    jComboBox.setEnabled(false);
                    dayComboBox.setEnabled(false);
                    monthComboBox.setEnabled(false);
                    yearComboBox.setEnabled(false);
                    time.setEnabled(false);
                }
            }
        });
        //setSize(checkAvailability);
        c.gridx = 3;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 2;
        doctorSelectPanel.add(checkAvailability,c);
        doctorDetails = new JLabel();
        consultDateTime = new JLabel();
        setSize(doctorDetails);
        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 2;
        doctorSelectPanel.add(doctorDetails,c);
        setSize(consultDateTime);
        c.gridx = 2;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 2;
        doctorSelectPanel.add(consultDateTime,c);



        JPanel patientSelectPanel = new JPanel(new GridBagLayout());

        JLabel pNamePanel = new JLabel("Patient Name");
        setSize(pNamePanel);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 2;
        patientSelectPanel.add(pNamePanel,c);
        patientName = new JTextField(20);
        setSize(patientName);
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 2;
        patientSelectPanel.add(patientName,c);
        JLabel pSurnameLabel = new JLabel("Patient Surname");
        setSize(pSurnameLabel);
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 2;
        patientSelectPanel.add(pSurnameLabel,c);
        patientSurname = new JTextField(20);
        setSize(patientSurname);
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 2;
        patientSelectPanel.add(patientSurname,c);
        JLabel pDOBLabel = new JLabel("Patient DOB");
        setSize(pDOBLabel);
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 2;
        patientSelectPanel.add(pDOBLabel,c);

        JPanel patientDatePanel = new JPanel(new FlowLayout());
        ageDayComboBox = new JComboBox<>(day);
        ageMonthComboBox = new JComboBox<>(month);
        String[] ageYears = new String[101];
        ageYears[0] = "YYYY";
        for (int i = 1; i < ageYears.length; i++){
            ageYears[i] = String.valueOf(i+1949);
        }
        ageYearComboBox = new JComboBox<>(ageYears);
        patientDatePanel.add(ageDayComboBox);
        patientDatePanel.add(ageMonthComboBox);
        patientDatePanel.add(ageYearComboBox);
        //setSize(patientDatePanel);
        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        patientSelectPanel.add(patientDatePanel,c);

        JLabel pTelNumLabel  = new JLabel("Patient TelNum");
        setSize(pTelNumLabel);
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 2;
        patientSelectPanel.add(pTelNumLabel,c);

        patientTelNum = new JTextField(20);
        setSize(patientTelNum);
        c.gridx = 2;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 2;
        patientSelectPanel.add(patientTelNum,c);

        JLabel pIDLabel = new JLabel("Patient ID");
        setSize(pIDLabel);
        c.gridx = 0;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 2;
        patientSelectPanel.add(pIDLabel,c);

        patientId = new JTextField(20);
        setSize(patientId);
        c.gridx = 2;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 3;
        patientSelectPanel.add(patientId,c);

        JLabel consultNotesLabel = new JLabel("Consultation Notes");
        setSize(consultNotesLabel);
        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 2;
        patientSelectPanel.add(consultNotesLabel,c);
        consultNotes = new JTextField(20);
        consultNotes.setPreferredSize(new Dimension(30,30));
        //setSize(consultNotes);
        c.gridx = 2;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 2;
        patientSelectPanel.add(consultNotes,c);
        JPanel submitButtonPanel = new JPanel(new FlowLayout());
        submit = new JButton("Submit");
        previewConsultation = new JButton("Preview Consultations");
        submitButtonPanel.add(submit);
        submitButtonPanel.add(previewConsultation);
        c.gridx = 1;
        c.gridy = 10;
        c.gridheight = 1;
        c.gridwidth = 4;
        patientSelectPanel.add(submitButtonPanel,c);


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //code that retrieves info from the text fields and adds them to a consultation object
                Consultation c = null;

                try {
                    c = new Consultation();
                } catch (GeneralSecurityException ex) {
                    throw new RuntimeException(ex);
                }


                c.setDoctor(doc.get(selectedDocIndex - 1));
                Date d = new Date(Integer.parseInt(day[selectedDayIndex]), Integer.parseInt(month[selectedMonthIndex])
                        , Integer.parseInt(year[selectedYearIndex]), timeArr[selectedTimeIndex]);
                c.setDate(d);
                Patient p = new Patient(patientSurname.getText(), patientName.getText(), patientTelNum.getText(),
                        patientId.getText());
                p.setDateOfBrth(new Date(Integer.parseInt(day[ageDayComboBox.getSelectedIndex()]),
                        Integer.parseInt(month[ageMonthComboBox.getSelectedIndex()]),
                        Integer.parseInt(ageYears[ageYearComboBox.getSelectedIndex()])
                ));
                c.setPatient(p);
                //cost check, patient that has already had a consultation will be charged more
                boolean patientFound = false;
                for (int i = 0; i < consultations.size(); i++) {
                    if (consultations.get(i).getPatient().getiD().equals(patientId.getText())) {
                        patientFound = true;
                    }
                }
                if (patientFound)
                    c.setCost(25);
                else
                    c.setCost(15);

                try {
                    c.setNotes(consultNotes.getText());
                } catch (GeneralSecurityException ex) {
                    throw new RuntimeException(ex);
                }
                consultations.add(c);



                //reset the entire j frame
                doctorDetails.setText("");
                consultDateTime.setText("");
                patientName.setText("");
                patientSurname.setText("");
                patientId.setText("");
                patientTelNum.setText("");
                consultNotes.setText("");
                jComboBox.setEnabled(true);
                dayComboBox.setEnabled(true);
                monthComboBox.setEnabled(true);
                yearComboBox.setEnabled(true);
                time.setEnabled(true);
                dayComboBox.setSelectedIndex(0);
                monthComboBox.setSelectedIndex(0);
                yearComboBox.setSelectedIndex(0);
                ageYearComboBox.setSelectedIndex(0);
                ageMonthComboBox.setSelectedIndex(0);
                ageDayComboBox.setSelectedIndex(0);
                jComboBox.setSelectedIndex(0);
                availabilityPrompt.setText("");
            }
        });

        previewConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(consultations.size() == 0){
                    JOptionPane.showMessageDialog(null,"No consultations to display");
                }else {
                    //convert consultation data into string
                    String[] consultationsArr = new String[consultations.size()];
                    for (int i = 0; i < consultations.size(); i++) {
                        consultationsArr[i] = (i + 1) + " " + consultations.get(i).toString();
                    }
                    //concat the string array into a string, to be used for the showMessageDialog method
                    String s = String.join("\n", consultationsArr);
                    JOptionPane.showMessageDialog(null, s);
                }
            }
        });


        //container for both panels
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new GridLayout(2,1));
        containerPanel.add(doctorSelectPanel);
        containerPanel.add(patientSelectPanel);
        this.add(containerPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    /**
     * https://stackoverflow.com/questions/6443176/how-can-i-generate-a-random-number-within-a-range-but-exclude-some
     * Method returns a randomised number within a range excluding a specific value
     * @param rnd
     * @param start
     * @param end
     * @param exclude
     * @return 
     */
    public int getRandomWithExclusion(Random rnd, int start, int end, int... exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }
    private static void setSize(Component label) {
        label.setMinimumSize(new Dimension(130, 20));
        label.setPreferredSize(new Dimension(130, 20));
    }
}
