package controller;

import connection.ConnetManager;
import ui.MainFrame;

public class MainController {
    public static final int STATE_IDLE = 0;
    public static final int STATE_LISTENING = 1;
    public static final int STATE_CONNECTED = 2;

    private int mState = STATE_IDLE;

    private ConnetManager mConnectManager;
    private MainFrame mMainFrame;

    public MainController(MainFrame mainFrame, ConnetManager connetManager){
        mMainFrame = mainFrame;
        mConnectManager = connetManager;
    }
}
