package com.yanzhikai.controllerandroid;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketAddress;

import static com.yanzhikai.controllerandroid.ConstantKt.STATE_CONNECTED;
import static com.yanzhikai.controllerandroid.ConstantKt.STATE_DISCONNECT;

public class ConnectManager {
    public static final String TAG = "yjk" + ConnectManager.class.getName();

    private Socket mSocket;

    private String mHost;

    private HandlerThread connectThread;

    private Handler mConnectHandler;

    private Handler mHandler;

    private int mPort = 9999;

    public static int state = STATE_DISCONNECT;

    private static final class Singleton {
        private static final ConnectManager sInstance = new ConnectManager();
    }

    private ConnectManager() {
    }

    public static ConnectManager getInstance() {
        return Singleton.sInstance;
    }

    public void configure(String host, int port) {
        mHost = host;
        mPort = port;
    }

    public void init() {
        mHandler = new Handler(Looper.getMainLooper());
        connectThread = new HandlerThread("ConnectThread") {
            @Override
            protected void onLooperPrepared() {
                if (linkSocket()) {
                    mConnectHandler = new Handler(connectThread.getLooper());
                    InputStream is = null;
                    try {
                        is = mSocket.getInputStream();

                        state = STATE_CONNECTED;
                        while (state == STATE_CONNECTED) {
                            InputStreamReader isReader = new InputStreamReader(is);
                            BufferedReader bufferedReader = new BufferedReader(isReader);
                            String command = bufferedReader.readLine();
                            Log.i(TAG, "get Msg: " +command);
                            onMsg(command);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        state = STATE_DISCONNECT;
                    }
                }
            }
        };

    }

    private void onMsg(String msg){
        Message message = mHandler.obtainMessage();
        message.what = 1;
        message.obj = msg;
        mHandler.dispatchMessage(message);
    }

    private boolean linkSocket() {
        try {
            mSocket = new Socket(mHost, mPort);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private class ConnectingRunnable implements Runnable {

        @Override
        public void run() {

        }
    }

    private class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

        }
    }


}
