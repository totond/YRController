package controller.command;

import java.util.ArrayList;

public abstract class ShortCutCommand extends Command {
    protected ArrayList<Integer> pressList = new ArrayList<>(), releaseList = new ArrayList<>();

    public ArrayList<Integer> getPressList() {
        return pressList;
    }

    public ArrayList<Integer> getReleaseList() {
        return releaseList;
    }
}
