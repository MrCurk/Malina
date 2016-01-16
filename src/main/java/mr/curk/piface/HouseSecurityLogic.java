package mr.curk.piface;

import mr.curk.common.*;
import mr.curk.mail.MailConfigFile;

public class HouseSecurityLogic implements PiLogicInterface, ResetIt, Condition {
    private final PiFaceModule piFaceModule;

    private int numberButtonPressed = 0;
    private boolean alarmMode = true;
    private boolean alarmState = false;

    private MailConfigFile mailConfig;

    private State input_0;
    private State input_1;
    private State input_2;
    private State input_3;
    private State input_4;
    private State input_5;
    private State input_6;
    private State input_7;

    private boolean input_0_running = false;
    private boolean input_6_running = false;

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

        //mail config, read from file
        mailConfig = new MailConfigFile("/home/pi/Malina/mail.config");

        //new Thread(new SendMail(mailConfig, "HomeSecurity started", "HomeSecurity started")).start();
    }

    //is alarm enabled
    public boolean isAlarmEnabled() {
        return alarmMode;
    }

    //is alarm fired
    public boolean isAlarmFired() {
        return alarmState;
    }

    //enable alarm
    public void enableAlarm() {
        if (!isAlarmEnabled()) {
            alarmMode = true;
            System.out.println("Alarm is set!");
        }
    }

    //disable alarm
    public void disableAlarm() {
        if (isAlarmEnabled()) {
            dismissAlarm();
            alarmMode = false;
            alarmState = false;
        }
    }

    //set inputs.....
    @Override
    public void setInput(int pin, State state) {
        boolean correctInput = true;

        switch (pin) {
            case 0:
                input_0 = state;
                break;
            case 1:
                input_1 = state;
                break;
            case 2:
                input_2 = state;
                break;
            case 3:
                input_3 = state;
                break;
            case 4:
                input_4 = state;
                break;
            case 5:
                input_5 = state;
                break;
            case 6:
                input_6 = state;
                break;
            case 7:
                input_7 = state;
                break;
            default:
                correctInput = false;
        }
        if (correctInput) {
            logic();
        }
    }

    private void logic() {

        //main logic for rise alarm
        if (input_0.toBoolean() && isAlarmEnabled() && !input_0_running && !isAlarmFired()) {

            input_0_running = true;

            //wait 18 second, to eliminate sensor fault
            new CountDown(this, 18, 2);
            //if is still on, rise alarm
            if (piFaceModule.getStatusInput(0) == State.ON && isAlarmEnabled() && !isAlarmFired()) {
                riseAlarm();
            } else {
                System.out.println("False alarm!");
            }
            input_0_running = false;
        }

        //reset button - enable, disable and dismiss alarm
        if (input_6.toBoolean() && !input_6_running) {

            input_6_running = true;
            setButtonPressed();

            if (numberButtonPressed >= 3) {
                resetAlarm();
            }
            input_6_running = false;
        }
    }

    //enable, disable and dismiss alarm
    private void resetAlarm() {

        if (isAlarmEnabled()) {
            //dismiss alarm only if it is fired
            if (isAlarmFired()) {
                dismissAlarm();
            }
            //disable alarm
            alarmMode = false;
            numberButtonPressed = 0;
        } else {
            //set alarm after n seconds
            System.out.println("Alarm will set in ");
            new CountDown(40, 1);
            enableAlarm();
        }
        alarmState = false;
    }

    //dismiss alarm
    private void dismissAlarm() {
        alarmState = false;
        piFaceModule.setCommand(PiCommand.OUTPUT_0_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_1_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_2_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_3_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_4_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_5_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_6_OFF);
        piFaceModule.setCommand(PiCommand.OUTPUT_7_OFF);
        System.out.println("Alarm dismissed!");
    }

    //alarm rise
    private void riseAlarm() {
        if (!isAlarmFired()) {
            alarmState = true;
            //send mail
            //new Thread(new SendMail(mailConfig, "sensor 0", "sensor 0 message")).start();
            //piFaceModule.setCommand(PiCommand.OUTPUT_0_ON);

            piFaceModule.setCommand(PiCommand.OUTPUT_1_ON);
            piFaceModule.setCommand(PiCommand.OUTPUT_2_ON);
            piFaceModule.setCommand(PiCommand.OUTPUT_3_ON);
            System.out.println("Alarm Alarm Alarm Alarm Alarm");
            new CountDown(this,120,60);
            dismissAlarm();
        }
    }

    //increase number of button pressed
    private void setButtonPressed() {

        if (numberButtonPressed == 0) {
            new Thread(new ResetItAfterSeconds(this, 3)).start();
        }
        numberButtonPressed++;
        System.out.println("button pressed " + numberButtonPressed);
    }


    //interface method to reset button count pressed
    @Override
    public void resetIt() {
        numberButtonPressed = 0;
    }

    //interface method for count down
    @Override
    public boolean isStillValid() {
        return isAlarmEnabled();
    }


}
