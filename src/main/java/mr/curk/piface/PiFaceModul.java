package mr.curk.piface;

import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.impl.PiFaceDevice;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.io.IOException;

import static com.pi4j.wiringpi.Spi.CHANNEL_0;

/**
 * Created by one on 29.12.2015.
 */
public class PiFaceModul implements Runnable {
    final private PiFace piFace;
    private  PiCommand piCommand;
    private  boolean runningCondition;

    public PiFaceModul() throws IOException {

        piFace = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, CHANNEL_0);
        for (int i = 0; i < 8; i++) {
            piFace.getInputPin(i).setName("Input " + i);
        }
    }

    //RUNNABLE
    @Override
    public void run() {

        startPi();

        if ( piCommand != PiCommand.WAITING){
        while (runningCondition) {
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
                case STOP:
                    stopPi();
                    break;
                case START:
                    startPi();
                    break;
                case STATUS:
                    getStatusAll();
                    break;
                case EXIT:
                    stopPi();
                    System.exit(1);
                    break;
                case HELP:
                default:
                    PiCommand.printHelp();
                    break;
            }
            piCommand=PiCommand.WAITING;
        }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getStatusAll() {
        for (int i = 0; i < 8; i++) {
            System.out.println("input " + i + " : " + getStatusInput(i)
                    + "\t output " + i + " : " + getStatusOutput(i));
        }
    }

    private String getStatusInput(int i) {
        if (piFace.getInputPin(i).isLow()) {
            return "On";
        } else {
            return "Off";
        }
    }

    private String getStatusOutput(int i) {
        if (piFace.getOutputPin(i).isHigh()) {
            return "On";
        } else {
            return "Off";
        }
    }

    //START PI FACE
    private void startPi(){
        if (!runningCondition) {
            runningCondition = true;
            piCommand = PiCommand.HELP;
            setListeners();
            setOutputAllOff();
            System.out.println("PiFaceModul started!");
        }else {
            System.out.println("PiFaceModul allready running!");
        }
    }

    //STOP PI FACE
    private void stopPi(){
        if (runningCondition) {
            runningCondition = false;
            piCommand = PiCommand.HELP;
            //setListeners();
            setOutputAllOff();
            System.out.println("PiFaceModul stopped!");
        }else {
            System.out.println("PiFaceModul allready stopped!");
        }
    }

    //SET ALL OUTPUT OFF
    private void setOutputAllOff() {
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


    //SET COMMAND FOR PIFACE MODUL
    public void setCommand(PiCommand piCommand) {
        this.piCommand=piCommand;
    }

    //SET LISTENER FOR ALL 8 INPUTS
    private void setListeners() {
        //INPUT 0 Listener
        piFace.getInputPin(0).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //OFF
                if (gpioPinDigitalStateChangeEvent.getState().isHigh()) {
                    System.out.println("Input 0 off");
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 0 on");
                    System.out.println( "get pin"+gpioPinDigitalStateChangeEvent.getPin());
                    System.out.println("get pin get name"+gpioPinDigitalStateChangeEvent.getPin().getName());
                    System.out.println("get pin get pin"+gpioPinDigitalStateChangeEvent.getPin().getPin());
                    System.out.println("get state VAlue" + gpioPinDigitalStateChangeEvent.getState().getValue());
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
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 1 on");
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
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 2 on");
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
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 3 on");
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
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 4 on");
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
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 5 on");
                }
            }
        });
//INPUT 6 Listener
        piFace.getInputPin(6).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
                //OFF
                if (gpioPinDigitalStateChangeEvent.getState().isHigh()) {
                    System.out.println("Input 6 off");
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 6 on");
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
                }
                //ON
                if (gpioPinDigitalStateChangeEvent.getState().isLow()) {
                    System.out.println("Input 7 on");
                }
            }
        });
    }
}
