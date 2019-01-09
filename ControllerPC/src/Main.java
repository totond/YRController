import connection.ConnetManager;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        ConnetManager connetManager = new ConnetManager();
        mainFrame.setIps(connetManager.getIpList());
    }


}
