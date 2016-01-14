package mr.curk.common;

public class CountDown {
    private final Condition condition;
    private final int secondsInterval;
    private final int loopRepeat;

    public CountDown(Condition condition, int secondsWait, int secondsInterval) {
        this.condition = condition;
        this.secondsInterval = secondsInterval;
        if (secondsInterval > 0) {
            loopRepeat = secondsWait / secondsInterval;
        } else {
            loopRepeat = 1;
        }
        run();
    }

    public CountDown(int secondsWait, int secondsInterval) {
        this(null, secondsWait, secondsInterval);
    }

    private void run() {

        for (int i = 0; i < loopRepeat; i++) {
            System.out.println("Wait " + (loopRepeat - i));
            if (condition != null && !condition.isStillValid()) {
                break;
            }
            try {
                Thread.sleep(secondsInterval * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
