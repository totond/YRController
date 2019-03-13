package controller;

import connection.ConnectManager;
import controller.message.Message;
import controller.message.MessageManager;
import ui.MainFrame;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainController {
    public static final int STATE_IDLE = 0;
    public static final int STATE_LISTENING = 1;
    public static final int STATE_CONNECTED = 2;

    public static final int ACTION_LISTEN = 0;
    public static final int ACTION_CONNECT = 1;
    public static final int ACTION_DISCONNECT = 2;

    private int mState = STATE_IDLE;

    private ConnectManager mConnectManager;
    private MessageManager mMessageManager;
    private MainFrame mMainFrame;
    private WorkerThread mWorkerThread;
    private ListenThread mListenThread;
    private OnStateChangedListener mStateChangedListener;

    public MainController(MainFrame mainFrame, ConnectManager connectManager) {
        mMainFrame = mainFrame;
        mConnectManager = connectManager;
        mMessageManager = new MessageManager();
    }

    public boolean startListen(int port) {
        if (mState != STATE_IDLE) {
            return false;
        }
        if (mListenThread == null || !mListenThread.isAlive()) {
            mListenThread = new ListenThread(port);
        }
        mListenThread.start();
        return true;
    }

    public void changeState(int state) {
        mState = state;
        // if (mStateChangedListener != null) {
        //     SwingUtilities.invokeLater(
        //             () -> {
        //                 // 此处处于 事件调度线程
        //                 mStateChangedListener.onStateChanged(mState);
        //             }
        //     );
        // }
        SwingUtilities.invokeLater(
                () -> {
                    // 此处处于 事件调度线程
                    mMainFrame.updateControlBtn(mState);
                }
        );
    }

    public void stop(){
        switch (mState){
            case STATE_LISTENING:
                mListenThread.stopSocket();
                break;
            case STATE_CONNECTED:
                mWorkerThread.stopWorking();
                break;
        }
        changeState(STATE_IDLE);
    }

    private class WorkerThread extends RunningThread {

        private BufferedReader mBufferedReader;
        private Socket mSocket;

        public WorkerThread(Socket socket) throws IOException {
            mSocket = socket;
            mBufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
        }

        @Override
        public void run() {
            super.run();
            while (mRunning){
                try {
                    System.out.println("working");
                    String result = mBufferedReader.readLine();
                    if (result != null && result.length() > 0){
                        mMessageManager.handleMessage(new Message(result));
                    }
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopWorking() {
            if (mBufferedReader != null){
                try {
                    mBufferedReader.close();
                    mSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private class ListenThread extends RunningThread{
        private int port;
        private ServerSocket mServerSocket;

        public ListenThread(int port) {
            this.port = port;
        }

        public void startListen(){
            try {
                mServerSocket = new ServerSocket(port);
                changeState(STATE_LISTENING);
                System.out.println("listening");
                Socket socket = mServerSocket.accept();
                mWorkerThread = new WorkerThread(socket);
                mWorkerThread.start();
            } catch (IOException e) {
                System.out.println("此端口已经被占用！");
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            super.run();
            startListen();
            changeState(STATE_CONNECTED);
        }

        public void stopSocket(){
            try {
                mServerSocket.close();
                System.out.println("stop");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnStateChangedListener {
        void onStateChanged(int newState);
    }
}
