package mr.curk.piface;

import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.impl.PiFaceDevice;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import mr.curk.common.State;

import java.io.IOException;

import static com.pi4j.wiringpi.Spi.CHANNEL_0;

public class PiFaceModule {
    final private PiFace piFace;
    private  HouseSecurityLogic houseSecurityLogic;

    public PiFaceModule() throws IOException {
        piFace = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, CHANNEL_0);
        setAllOutputOff();
        setAllListeners();
        setCommand(PiCommand.HELP);
        houseSecurityLogic = new HouseSecurityLogic(this);
    }

    //SET COMMAND FOR PIFACE MODULE
    public void setCommand(PiCommand piCommand) {

        switch (piCommand) {
            case OUTPUT_0_OFF:
                setOutputOff(0);
                break;
            case OUTPUT_0_ON:
                setOutputOn(0);
                break;
            case OUTPUT_1_OFF:
                setOutputOff(1);
                break;
            case OUTPUT_1_ON:
                setOutputOn(1);
                break;
            case OUTPUT_2_OFF:
                setOutputOff(2);
                break;
            case OUTPUT_2_ON:
                setOutputOn(2);
                break;
            case OUTPUT_3_OFF:
                setOutputOff(3);
                break;
            case OUTPUT_3_ON:
                setOutputOn(3);
                break;
            case OUTPUT_4_OFF:
                setOutputOff(4);
                break;
            case OUTPUT_4_ON:
                setOutputOn(4);
                break;
            case OUTPUT_5_OFF:
                setOutputOff(5);
                break;
            case OUTPUT_5_ON:
                setOutputOn(5);
                break;
            case OUTPUT_6_OFF:
                setOutputOff(6);
                break;
            case OUTPUT_6_ON:
                setOutputOn(6);
                break;
            case OUTPUT_7_OFF:
                setOutputOff(7);
                break;
            case OUTPUT_7_ON:
                setOutputOn(7);
                break;
            case RESET:
                removeAllListeners();
                setAllOutputOff();
                setAllListeners();
                break;
            case STATUS:
                getStatusAll();
                break;
            case EXIT:
                removeAllListeners();
                setAllOutputOff();
                System.exit(1);
                break;
            case HELP:
            default:
                PiCommand.printHelp();
                break;
        }
    }

    //PRINT ALL INPUT & OUTPUT STATUS
    private void getStatusAll() {
        for (int i = 0; i < 8; i++) {
            System.out.println("input " + i + " : " + getStatusInput(i)
                    + "\t output " + i + " : " + getStatusOutput(i));
        }
        System.out.println("Alarm mode: " + houseSecurityLogic.isAlarmEnabled());
    }

    //GET SPECIFIC INPUT STATUS
    public State getStatusInput(int i) {
        if (piFace.getInputPin(i).isLow()) {
            return State.ON;
        } else {
            return State.OFF;
        }
    }

    //GET SPECIFIC OUTPUT STATUS
    public State getStatusOutput(int i) {
        if (piFace.getOutputPin(i).isHigh()) {
            return State.ON;
        } else {
            return State.OFF;
        }
    }

    //SET ALL OUTPUT OFF
    private void setAllOutputOff() {
        for (int i = 0; i < 8; i++) {
            setOutputOff(i);
        }
    }

    //SET OUTPUT OFF
    private void setOutputOff(int pin) {
        piFace.getOutputPin(pin).low();
    }

    //SET OUTPUT ON
    private void setOutputOn(int pin) {
        piFace.getOutputPin(pin).high();
    }

    //REMOVE LISTENERS FOR ALL INPUTS
    private void removeAllListeners() {
        for (int i = 0; i < 8; i++) {
            removeListener(i);
        }
    }

    //REMOVE LISTENER FOR SPECIFIC INPUT
    private void removeListener(int pin) {
        piFace.getInputPin(pin).removeAllListeners();
    }

    //SET LISTENER FOR ALL 8 INPUTS
    private void setAllListeners() {
        //INPUT 0 Listener
        piFace.getInputPin(0).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //OFF
                if (gpioPinDigitalStateChangeEvent.getState().isHigh()) {
                    System.out.println("Input 0 off");
                    houseSecurityLogic.setInput(0,State.OFF);
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 0 on");
                    houseSecurityLogic.setInput(0,State.ON);
                }
            }
        });
//INPUT 1 Listener
        piFace.getInputPin(1).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //OFF
                if (gpioPinDigitalStateChangeEvent.getState().isHigh()) {
                    System.out.println("Input 1 off");
                    houseSecurityLogic.setInput(1,State.OFF);
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 1 on");
                    houseSecurityLogic.setInput(1,State.ON);
                }
            }
        });
//INPUT 2 Listener
        piFace.getInputPin(2).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //OFF
                if (gpioPinDigitalStateChangeEvent.getState().isHigh()) {
                    System.out.println("Input 2 off");
                    houseSecurityLogic.setInput(2,State.OFF);
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 2 on");
                    houseSecurityLogic.setInput(2,State.ON);
                }
            }
        });
//INPUT 3 Listener
        piFace.getInputPin(3).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //OFF
                if (gpioPinDigitalStateChangeEvent.getState().isHigh()) {
                    System.out.println("Input 3 off");
                    houseSecurityLogic.setInput(3,State.OFF);
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 3 on");
                    houseSecurityLogic.setInput(3,State.ON);
                }
            }
        });
//INPUT 4 Listener
        piFace.getInputPin(4).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //OFF
                if (gpioPinDigitalStateChangeEvent.getState().isHigh()) {
                    System.out.println("Input 4 off");
                    houseSecurityLogic.setInput(4,State.OFF);
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 4 on");
                    houseSecurityLogic.setInput(4,State.ON);
                }
            }
        });
//INPUT 5 Listener
        piFace.getInputPin(5).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //OFF
                if (gpioPinDigitalStateChangeEvent.getState().isHigh()) {
                    System.out.println("Input 5 off");
                    houseSecurityLogic.setInput(5,State.OFF);
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 5 on");
                    houseSecurityLogic.setInput(5,State.ON);
                }
            }
        });
//INPUT 6 Listener
        piFace.getInputPin(6).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //OFF
                if (gpioPinDigitalStateChangeEvent.getState().isHigh()) {
                    //System.out.println("Input 6 off");
                    houseSecurityLogic.setInput(6,State.OFF);
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    //System.out.println("Input 6 on");
                    houseSecurityLogic.setInput(6,State.ON);
                }
            }
        });
//INPUT 7 Listener
        piFace.getInputPin(7).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //OFF
                if (gpioPinDigitalStateChangeEvent.getState().isHigh()) {
                    System.out.println("Input 7 off");
                    houseSecurityLogic.setInput(7,State.OFF);
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 7 on");
                    houseSecurityLogic.setInput(7,State.ON);
                }
            }
        });
    }
}
