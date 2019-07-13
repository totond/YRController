package com.yanzhikai.controllerandroid;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.Callable;

import static com.yanzhikai.controllerandroid.ConstantKt.*;

public class ConnectManager {
    public static final String TAG = "yjk" + ConnectManager.class.getName();

    private Socket mSocket;

    private String mHost;

    private HandlerThread connectThread;

    private Handler mConnectHandler;

    private Handler mHandler;

    private int mPort = 9998;

    private ConnectListener mConnectListener;

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
        Log.i(TAG, "init: ");
        mHandler = new MainHandler(Looper.getMainLooper());
        connectThread = new HandlerThread("ConnectThread") {
            @Override
            protected void onLooperPrepared() {
                if (linkSocket()) {
                    mConnectHandler = new Handler(connectThread.getLooper());
                    InputStream is = null;
                    try {
                        is = mSocket.getInputStream();
                        Log.d(TAG, "onLooperPrepared: ");

                        state = STATE_CONNECTED;
                        onStateChange();
                        while (state == STATE_CONNECTED) {
                            InputStreamReader isReader = new InputStreamReader(is);
                            BufferedReader bufferedReader = new BufferedReader(isReader);
                            String command = bufferedReader.readLine();
                            Log.i(TAG, "get Msg: " +command);
                            onMsg(command);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "onLooperPrepared: ", e);
                    }finally {
                        state = STATE_DISCONNECT;
                    }
                }
            }
        };
        connectThread.start();

    }

    private void onStateChange(){
        Message message = mHandler.obtainMessage();
        message.what = MSG_TYPE_COMMAND;
        mHandler.sendMessage(message);
    }

    private void onMsg(String msg){
        Message message = mHandler.obtainMessage();
        message.what = 1;
        message.obj = msg;
        mHandler.sendMessage(message);
    }

    public void sendCommand(final String content) {
        Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                if (mSocket != null) {
                    try {
                        OutputStream outputStream = mSocket.getOutputStream();
                        outputStream.write(content.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                return true;
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    private boolean linkSocket() {
        try {
            mSocket = new Socket();
            SocketAddress address = new InetSocketAddress(mHost, mPort);
            mSocket.connect(address, 1000);
            Log.i(TAG, "linkSocket: " + mHost);
        } catch (Throwable e) {
            e.printStackTrace();
            Log.e(TAG, "linkSocket: ",e );
            return false;
        }
        return true;
    }



    public void setConnectListener(ConnectListener connectListener) {
        mConnectListener = connectListener;
    }

    private class MainHandler extends Handler {
        public MainHandler(Looper mainLooper) {
            super(mainLooper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_TYPE_COMMAND:
                    if (mConnectListener != null){
                        Log.i(TAG, "handleMessage: MSG_TYPE_COMMAND " + state);
                        mConnectListener.onSocketChanged(state);
                    }
                    break;
                case MSG_TYPE_STATE:

                    break;
            }
        }
    }

    public interface ConnectListener {
        void onSocketChanged(int state);
    }
}
