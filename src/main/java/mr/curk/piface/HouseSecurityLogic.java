package mr.curk.piface;

/**
 * Created by Mr.Curk@gmail.com on 7.1.2016.
 */
public class HouseSecurityLogic  implements PiLogicInterface{
    private  PiFaceModule piFaceModule;

    public  HouseSecurityLogic(PiFaceModule piFaceModule){
        this.piFaceModule=piFaceModule;
    }
    @Override
    public void setInput(int pin, State state) {
        if (pin == 0 && state == State.ON && piFaceModule.getStatusInput(3) == State.ON){
            piFaceModule.setCommand(PiCommand.OUTPUT_1_ON);
            piFaceModule.setCommand(PiCommand.OUTPUT_2_ON);
            piFaceModule.setCommand(PiCommand.OUTPUT_3_ON);
            piFaceModule.setCommand(PiCommand.OUTPUT_4_ON);
            piFaceModule.setCommand(PiCommand.OUTPUT_5_ON);
            piFaceModule.setCommand(PiCommand.OUTPUT_6_ON);
            piFaceModule.setCommand(PiCommand.OUTPUT_7_ON);
        }

        if (pin == 1 && state == State.ON){
            piFaceModule.setCommand(PiCommand.OUTPUT_1_OFF);
            piFaceModule.setCommand(PiCommand.OUTPUT_2_OFF);
            piFaceModule.setCommand(PiCommand.OUTPUT_3_OFF);
            piFaceModule.setCommand(PiCommand.OUTPUT_4_OFF);
            piFaceModule.setCommand(PiCommand.OUTPUT_5_OFF);
            piFaceModule.setCommand(PiCommand.OUTPUT_6_OFF);
            piFaceModule.setCommand(PiCommand.OUTPUT_7_OFF);
        }
    }
}
