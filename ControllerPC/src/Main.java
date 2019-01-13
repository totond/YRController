import connection.ConnectManager;
import ui.MainFrame;

import javax.swing.*;

public class Main {

    private static ConnectManager sConnectManager;

    public static void main(String[] args) {
        sConnectManager = new ConnectManager();
        SwingUtilities.invokeLater(() -> createGUI());
    }

    private static void createGUI(){
        MainFrame mainFrame = new MainFrame();
        mainFrame.setIps(sConnectManager.getIpList());
    }


}
