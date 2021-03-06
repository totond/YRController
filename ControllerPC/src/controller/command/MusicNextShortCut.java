package controller.command;

import static java.awt.event.KeyEvent.*;

public class MusicNextShortCut extends ShortCutCommand{

    @Override
    protected void init() {
        id = "10003";

        pressList.add(VK_CONTROL);
        pressList.add(VK_ALT);
        pressList.add(VK_CLOSE_BRACKET);

        releaseList.add(VK_CLOSE_BRACKET);
        releaseList.add(VK_ALT);
        releaseList.add(VK_CONTROL);
    }
}
