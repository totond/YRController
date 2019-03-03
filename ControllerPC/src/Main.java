import connection.ConnectManager;
import controller.MainController;
import ui.MainFrame;

import javax.swing.*;

public class Main {

    private static ConnectManager sConnectManager;

    private static MainFrame sMainFrame;


    public static void main(String[] args) {
        sConnectManager = new ConnectManager();
        SwingUtilities.invokeLater(() -> createGUI());
    }

    private static void createGUI() {
        sMainFrame = new MainFrame();
        sMainFrame.setIps(sConnectManager.getIpList());
        MainController mainController = new MainController(sMainFrame, sConnectManager);
        sMainFrame.setMainController(mainController);
    }


}
