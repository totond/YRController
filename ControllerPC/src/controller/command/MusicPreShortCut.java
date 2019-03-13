package controller.command;

import static java.awt.event.KeyEvent.*;

public class MusicPreShortCut extends ShortCutCommand{

    @Override
    protected void init() {
        id = "10002";

        pressList.add(VK_CONTROL);
        pressList.add(VK_ALT);
        pressList.add(VK_OPEN_BRACKET);

        releaseList.add(VK_OPEN_BRACKET);
        releaseList.add(VK_ALT);
        releaseList.add(VK_CONTROL);
    }
}
