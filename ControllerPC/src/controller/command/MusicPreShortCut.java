package controller.command;

import static java.awt.event.KeyEvent.*;

public class MusicPreShortCut extends ShortCutCommand{

    @Override
    protected void init() {
        id = "10002";

        pressList.add(VK_CONTROL);
        pressList.add(VK_SHIFT);
        pressList.add(VK_LEFT_PARENTHESIS);

        releaseList.add(VK_LEFT_PARENTHESIS);
        releaseList.add(VK_SHIFT);
        releaseList.add(VK_CONTROL);
    }
}
