package controller;

import connection.ConnectManager;
import ui.MainFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

public class MainController {
    public static final int STATE_IDLE = 0;
    public static final int STATE_LISTENING = 1;
    public static final int STATE_CONNECTED = 2;

    public static final int ACTION_LISTEN = 0;
    public static final int ACTION_CONNECT = 1;
    public static final int ACTION_DISCONNECT = 2;

    private int mState = STATE_IDLE;

    private ConnectManager mConnectManager;
    private MainFrame mMainFrame;
    private WorkerThread mWorkerThread;
    private OnStateChangedListener mStateChangedListener;

    public MainController(MainFrame mainFrame, ConnectManager connectManager) {
        mMainFrame = mainFrame;
        mConnectManager = connectManager;
    }

    public boolean startListen(int port) {
        if (mState != STATE_IDLE) {
            return false;
        }
        if (mWorkerThread == null || !mWorkerThread.isAlive()) {
            mWorkerThread = new WorkerThread();
        }
        mWorkerThread.start();
        return true;
    }

    public void changeState(int state){
        mState = state;
        if (mStateChangedListener != null){
            SwingUtilities.invokeLater(
                    new Runnable() {
                        @Override
                        public void run() {
                            // 此处处于 事件调度线程
                            mStateChangedListener.onStateChanged(mState);
                        }
                    }
            );
        }
    }

    private class WorkerThread extends Thread {
        private ServerSocket mServerSocket;
        private int mAction = ACTION_LISTEN;
        private int port;

        public WorkerThread(int port){
            this.port = port;
        }

        public void startListen() throws IOException {
            mServerSocket = new ServerSocket(port);
            mState = STATE_LISTENING;
            mServerSocket.accept();
            mAction = ACTION_CONNECT;
        }

        @Override
        public void run() {
            switch (mAction){
                case ACTION_LISTEN:
                    try {
                        startListen();
                    }catch (IOException e){
                        System.out.println("此端口已经被占用！");
                        e.printStackTrace();
                    }
                    break;
                case ACTION_CONNECT:
                    mState = STATE_CONNECTED;
                    break;
                case ACTION_DISCONNECT:

                    break;
            }
        }

    }

    public interface OnStateChangedListener {
        void onStateChanged(int newState);
    }
}
