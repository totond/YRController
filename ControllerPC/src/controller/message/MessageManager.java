package controller.message;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MessageManager {

    private Robot mRobot;

    public MessageManager(){
        try {
            mRobot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void handleMessage(Message message){
        System.out.println("接收到信息:" + message);
        mRobot.keyPress(KeyEvent.VK_CONTROL);
        mRobot.keyPress(KeyEvent.VK_ALT);
        mRobot.keyPress(KeyEvent.VK_P);
        mRobot.keyRelease(KeyEvent.VK_P);
        mRobot.keyRelease(KeyEvent.VK_CONTROL);
        mRobot.keyRelease(KeyEvent.VK_ALT);

    }
}
