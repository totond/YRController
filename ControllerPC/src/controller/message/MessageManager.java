package controller.message;

import controller.command.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class MessageManager {

    private Robot mRobot;

    private HashMap<String, ShortCutCommand> mCommandHashMap;

    public MessageManager(){
        try {
            mRobot = new Robot();
            initMap();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void initMap() {
        mCommandHashMap = new HashMap<>();
        mCommandHashMap.put("8888", new ScreenOffShortCut());
        mCommandHashMap.put("10001", new ScreenOffShortCut());
        mCommandHashMap.put("10002", new MusicPreShortCut());
        mCommandHashMap.put("10003", new MusicNextShortCut());
        mCommandHashMap.put("10004", new MusicPauseShortCut());
        mCommandHashMap.put("10005", new MusicUpShortCut());
        mCommandHashMap.put("10006", new MusicDownShortCut());
    }

    public void handleMessage(Socket socket, Message message){
        System.out.println("接收到信息:" + message.getContent());

        Command command = mCommandHashMap.get(message.getContent());
        if (command instanceof HeartBeatCommand) {
            sendCommand(socket, "8888");
        }
        handleShortCut((ShortCutCommand) command);

    }


    private void sendCommand(Socket socket, String content){
        if (socket != null) {
            try {
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(content.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleShortCut(ShortCutCommand shortCutCommand){
        for (int i : shortCutCommand.getPressList()){
            mRobot.keyPress(i);
        }

        for (int i : shortCutCommand.getReleaseList()){
            mRobot.keyRelease(i);
        }
    }
}
