package ui;

import connection.StateUtil;
import controller.MainController;
import gobal.ConstantStrings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static controller.MainController.*;
import static controller.MainController.STATE_CONNECTED;
import static gobal.ConstantStrings.*;

public class MainFrame extends JFrame {
    private final String[] test = {"wewe","ewew", "asdas"};

    private JComboBox<String> cbIPs;
    private JButton btnControl, btnCommand;
    private Box mainBox, group1;
    private Panel group2;
    private SpringLayout lyGroup2;
    private JLabel lbPort, lbIP;
    private JTextField tfPort;
    private JTextArea taState;

    private MainController mMainController;

    private int state = STATE_IDLE;

    public MainFrame(){
        init();
    }

    private void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainBox = Box.createVerticalBox();
        group1 = Box.createHorizontalBox();

        initGroup1();
//        initGroup2();

        mainBox.add(group1);
//        mainBox.add(group2);

        setContentPane(mainBox);
        pack();
        setVisible(true);
    }

    public void setMainController(MainController mainController) {
        mMainController = mainController;
    }

    private void initGroup1(){
        btnControl = new JButton(LISTEN_START);
        btnControl.addActionListener(controllActionListener);
        lbPort = new JLabel(ConstantStrings.PORT);
        lbIP = new JLabel(ConstantStrings.LOACL_IPS);
        tfPort = new JTextField(1);
        cbIPs = new JComboBox<String>();

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
        group2 = new Panel(lyGroup2);
        group2.setSize(500,500);
//        group2.setLayout(lyGroup2);

        btnCommand = new JButton(ConstantStrings.COMMOND_SET);
        taState = new JTextArea();
        taState.setEnabled(false);

        group2.add(btnControl);
        group2.add(taState);

        SpringLayout.Constraints groupCon = lyGroup2.getConstraints(group2);

//        SpringLayout.Constraints btnCon = lyGroup2.getConstraints(btnCommand);
//        btnCon.setX(Spring.constant(50));
//        btnCon.setY(Spring.constant(150));
//        btnCon.setWidth(Spring.constant(100));
//        btnCon.setHeight(Spring.constant(100));
//
//        SpringLayout.Constraints taCon = lyGroup2.getConstraints(taState);
//        taCon.setX(btnCon.getConstraint(SpringLayout.NORTH));
//        taCon.setY(Spring.constant(50));
//        taCon.setConstraint(SpringLayout.EAST, groupCon.getConstraint(SpringLayout.EAST));
//        taCon.setConstraint(SpringLayout.SOUTH, groupCon.getConstraint(SpringLayout.SOUTH));
    }

    public void setIps(List<String> ips){
        for (String ip : ips) {
            cbIPs.addItem(ip);
        }
    }

    public void updateControlBtn(int state){
        this.state = state;
        switch (state){
            case STATE_IDLE:
                btnControl.setText(LISTEN_START);
                break;
            case STATE_LISTENING:
                btnControl.setText(LISTEN_STOP);

                break;
            case STATE_CONNECTED:
                btnControl.setText(DISCONNENT);

                break;
            default:
                btnControl.setText(LISTEN_START);
                break;
        }
    }

    private void onBtnClick(){
        switch (state){
            case STATE_IDLE:
                mMainController.startListen(9999);
                break;
            case STATE_LISTENING:
                mMainController.stop();
                break;
            case STATE_CONNECTED:
                mMainController.stop();
                break;

        }
    }

    private final ActionListener controllActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            onBtnClick();
            // state = StateUtil.getNextState(state);
            // updateControlBtn();

        }
    };
}
