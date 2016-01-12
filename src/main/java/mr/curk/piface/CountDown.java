package mr.curk.piface;

public class CountDown {

    private int secondsInterval;
    private int loopRepeat;

    public CountDown(int secondsWait, int secondsInterval) {

        this.secondsInterval = secondsInterval;
        if (secondsInterval > 0) {
            loopRepeat = secondsWait / secondsInterval;
        } else {
            loopRepeat = 1;
        }

        run();
    }

    private void run() {

        for (int i = 0; i < loopRepeat; i++) {
            System.out.println("Wait "+(loopRepeat-i));
            if (!HouseSecurityLogic.isAlarmMode()){
                break;
            }
            try {
                Thread.sleep(secondsInterval*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
