package controller.command;

import static java.awt.event.KeyEvent.*;

public class MusicNextShortCut extends ShortCutCommand{

    @Override
    protected void init() {
        id = "10003";

        pressList.add(VK_CONTROL);
        pressList.add(VK_SHIFT);
        pressList.add(VK_RIGHT_PARENTHESIS);

        releaseList.add(VK_RIGHT_PARENTHESIS);
        releaseList.add(VK_SHIFT);
        releaseList.add(VK_CONTROL);
    }
}
