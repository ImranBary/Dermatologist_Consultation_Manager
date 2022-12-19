package ConsultManager;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsultManagerGui extends JFrame implements ActionListener {
    private JButton view;
    private WestminsterSkinConsultationManager w = new WestminsterSkinConsultationManager();
    private ArrayList<Doctor> doc = w.getDocArray();

    public ConsultManagerGui(ArrayList<Doctor> d) throws IOException {
        super("Consultation Manager");


        JPanel p1 = new JPanel();
        //p1.setLayout(new FlowLayout(FlowLayout.CENTER,5,30));
        String [] docArrayList = new String [doc.size()];
        for(int i = 0; i < doc.size(); i++){
            docArrayList[i] = doc.get(i).forDropDownList();
        }
        JComboBox<String> jComboBox = new JComboBox<>(docArrayList);
        p1.add(jComboBox);

        view = new JButton("view Doctors");

        p1.add(new JLabel("Time"));
        p1.add(view);
        p1.add(new JTextField(9));

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DoctorTableModel.displayTable(d);
            }
        });
       // view.addActionListener(this);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(1,2));


        p3.add(p1);
        this.add(p3);


    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }




}
