package controller.command;

import static java.awt.event.KeyEvent.*;

public class MusicUpShortCut extends ShortCutCommand{

    @Override
    protected void init() {
        id = "10004";

        pressList.add(VK_CONTROL);
        pressList.add(VK_ALT);
        pressList.add(VK_UP);

        releaseList.add(VK_UP);
        releaseList.add(VK_ALT);
        releaseList.add(VK_CONTROL);
    }
}
