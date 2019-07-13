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

    public boolean Find(int target, int [][] array) {
        if(array == null || array.length == 0 || array[0].length == 0){
            return false;
        }

        int bottom = array.length;
        int right = array[0].length;
        int top = 0;

        while (top <= bottom && right >= 0){
            int cur = array[top][right];
            if (target == cur){
                return true;
            }else if (target < cur){
                right--;
            }else {
                top++;
            }
        }
        return false;
    }

    private static void createGUI() {
        sMainFrame = new MainFrame();
        sMainFrame.setIps(sConnectManager.getIpList());
        MainController mainController = new MainController(sMainFrame, sConnectManager);
        sMainFrame.setMainController(mainController);
    }


}
