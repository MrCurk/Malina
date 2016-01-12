package mr.curk.piface;

import mr.curk.mail.ConfigFile;
import mr.curk.mail.SendMail;

public class HouseSecurityLogic implements PiLogicInterface {
    private PiFaceModule piFaceModule;

    static private int numberButtonPressed = 0;
    static private boolean alarmMode = true;
    static private boolean alarmState = false;
    private ConfigFile mailConfig;

    private State input_0;
    private State input_1;
    private State input_2;
    private State input_3;
    private State input_4;
    private State input_5;
    private State input_6;
    private State input_7;
    private State output_0;
    private State output_1;
    private State output_2;
    private State output_3;
    private State output_4;
    private State output_5;
    private State output_6;
    private State output_7;

    private boolean input_0_running;

    public HouseSecurityLogic(PiFaceModule piFaceModule) {
        this.piFaceModule = piFaceModule;
        input_0 = piFaceModule.getStatusInput(0);
        input_1 = piFaceModule.getStatusInput(1);
        input_2 = piFaceModule.getStatusInput(2);
        input_3 = piFaceModule.getStatusInput(3);
        input_4 = piFaceModule.getStatusInput(4);
        input_5 = piFaceModule.getStatusInput(5);
        input_6 = piFaceModule.getStatusInput(6);
        input_7 = piFaceModule.getStatusInput(7);

        mailConfig = new ConfigFile("/home/pi/Malina/mail.config");

        //new Thread(new SendMail(mailConfig, "HomeSecurety started", "HomeSecurety started")).start();
    }

    public static boolean isAlarmMode() {
        return alarmMode;
    }

    @Override
    public void setInput(int pin, State state) {
        switch (pin) {
            case 0:
                input_0 = (state == State.ON ? State.ON : State.OFF);
                break;
            case 1:
                input_1 = (state == State.ON ? State.ON : State.OFF);
                break;
            case 2:
                input_2 = (state == State.ON ? State.ON : State.OFF);
                break;
            case 3:
                input_3 = (state == State.ON ? State.ON : State.OFF);
                break;
            case 4:
                input_4 = (state == State.ON ? State.ON : State.OFF);
                break;
            case 5:
                input_5 = (state == State.ON ? State.ON : State.OFF);
                break;
            case 6:
                input_6 = (state == State.ON ? State.ON : State.OFF);
                break;
            case 7:
                input_7 = (state == State.ON ? State.ON : State.OFF);
                break;
            default:
                break;
        }
        logic();
    }

    private void logic() {
        if (input_0 == State.ON && HouseSecurityLogic.alarmMode && !input_0_running && !HouseSecurityLogic.alarmState) {

            input_0_running = true;

            new CountDown(20, 2);

            if (piFaceModule.getStatusInput(0) == State.ON && HouseSecurityLogic.alarmMode && !HouseSecurityLogic.alarmState) {
                riseAlarm();
                new Thread(new SendMail(mailConfig, "sensor 0", "sensor 0 message")).start();
            } else {
                System.out.println("False alarm!");
            }

            input_0_running = false;
        }

        if (input_1 == State.ON) {
            setButtonPressed();
            new Thread(new ButtonPresedReset()).start();
            if (HouseSecurityLogic.numberButtonPressed >= 3) {
                resetAlarm();
            }
        }
    }

    private void resetAlarm() {
        if (HouseSecurityLogic.alarmMode) {
            dismissAlarm();
        }else {
            System.out.println("Alarm set!");
        }
        HouseSecurityLogic.alarmState = false;

        HouseSecurityLogic.alarmMode = !HouseSecurityLogic.alarmMode;
    }

    private void dismissAlarm() {

        System.out.println("Alarm dismissed!");
        piFaceModule.setCommand(PiCommand.OUTPUT_0_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_1_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_2_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_3_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_4_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_5_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_6_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_7_OFF);
    }

    private void riseAlarm() {
        HouseSecurityLogic.alarmState = true;

        piFaceModule.setCommand(PiCommand.OUTPUT_1_ON);
        piFaceModule.setCommand(PiCommand.OUTPUT_2_ON);
        piFaceModule.setCommand(PiCommand.OUTPUT_3_ON);
    }

    public static void setButtonPressed() {
        HouseSecurityLogic.numberButtonPressed++;
        System.out.println("button pressed " + HouseSecurityLogic.numberButtonPressed);
    }

    public static void resetButtonPressed() {
        HouseSecurityLogic.numberButtonPressed = 0;
    }
}
