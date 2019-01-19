package controller;

public class RunningThread extends Thread {
    protected boolean mRunning = false;

    @Override
    public void run() {
        super.run();
        mRunning = true;
    }

    public void stopRunning(){
        mRunning = false;
    }
}
