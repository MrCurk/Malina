package mr.curk.common;

public class ResetItAfterSeconds implements Runnable {
    private final ResetIt item;
    private final int seconds;

    public ResetItAfterSeconds(ResetIt item, int seconds) {
        this.item=item;
        this.seconds=seconds;
    }

    //after 3 seconds it run resetButtonPressed
    @Override
    public void run() {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        item.resetIt();
    }
}
