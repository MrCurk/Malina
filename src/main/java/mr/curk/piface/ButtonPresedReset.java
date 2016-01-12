package mr.curk.piface;

public class ButtonPresedReset implements  Runnable {


    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HouseSecurityLogic.resetButtonPressed();
    }
}
