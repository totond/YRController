package controller.command;

public abstract class Command {
    protected String id;

    protected abstract void init();

    public String getId() {
        return id;
    }
}
