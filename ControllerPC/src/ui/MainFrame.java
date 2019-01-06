package ui;

import gobal.ConstantStrings;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final String[] test = {"wewe","ewew", "asdas"};

    private JComboBox cbIPs;
    private JButton btnControl, btnCommand;
    private Box mainBox, group1;
    private Panel group2;
    private SpringLayout lyGroup2;
    private JLabel lbPort, lbIP;
    private JTextField tfPort;

    public MainFrame(){
        init();
    }

    private void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainBox = Box.createVerticalBox();
        group1 = Box.createHorizontalBox();
        group2 = new Panel();

        initGroup1();
        initGroup2();

        mainBox.add(group1);
        mainBox.add(group2);

        setContentPane(mainBox);
        pack();
        setVisible(true);
    }


    private void initGroup1(){
        btnControl = new JButton(ConstantStrings.LISTEN_START);
        lbPort = new JLabel(ConstantStrings.PORT);
        lbIP = new JLabel(ConstantStrings.LOACL_IPS);
        tfPort = new JTextField(1);
        cbIPs = new JComboBox(test);

        tfPort.setPreferredSize(new Dimension(50,30));
        tfPort.setSize(50,30);
        tfPort.setMinimumSize(new Dimension(50,30));
//        tfPort.setMaximumSize(new Dimension(1280,35));


        group1.add(Box.createRigidArea(new Dimension(50,50)));
        group1.add(btnControl);
        group1.add(Box.createHorizontalStrut(20));
        group1.add(lbPort);
        group1.add(tfPort);
        group1.add(Box.createHorizontalStrut(20));
        group1.add(lbIP);
        group1.add(cbIPs);
        group1.add(Box.createHorizontalStrut(20));


    }

    private void initGroup2() {
        lyGroup2 = new SpringLayout();
        group2.setLayout(lyGroup2);


    }

}
