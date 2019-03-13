package controller.message;

import controller.command.*;

import java.awt.*;
import java.awt.event.KeyEvent;
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
        mCommandHashMap.put("10001", new ScreenOffShortCut());
        mCommandHashMap.put("10002", new MusicPreShortCut());
        mCommandHashMap.put("10003", new MusicNextShortCut());
        mCommandHashMap.put("10004", new MusicPauseShortCut());
        mCommandHashMap.put("10005", new MusicUpShortCut());
        mCommandHashMap.put("10006", new MusicDownShortCut());
    }

    public void handleMessage(Message message){
        System.out.println("接收到信息:" + message.getContent());

        handleShortCut(mCommandHashMap.get(message.getContent()));

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
