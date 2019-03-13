package controller.command;

import static java.awt.event.KeyEvent.*;

public class ScreenOffShortCut extends ShortCutCommand{

    @Override
    protected void init() {
        id = "10001";

        pressList.add(VK_CONTROL);
        pressList.add(VK_ALT);
        pressList.add(VK_B);

        releaseList.add(VK_B);
        releaseList.add(VK_ALT);
        releaseList.add(VK_CONTROL);
    }
}
