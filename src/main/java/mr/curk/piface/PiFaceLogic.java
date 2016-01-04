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
public class PiFaceLogic {
    final private PiFace piFace;

    public PiFaceLogic() throws IOException {
        System.out.println("PiFace starting........");
        piFace = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, CHANNEL_0);

//INPUT 0
        piFace.getInputPin(0).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {

                if (gpioPinDigitalStateChangeEvent.getState()== PinState.HIGH) {
                    System.out.println("Input 0 off");
                }
                if (gpioPinDigitalStateChangeEvent.getState()== PinState.LOW) {
                    System.out.println("Input 0 on");
                }
            }
        });
//INPUT 1
        piFace.getInputPin(1).addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {

                if (gpioPinDigitalStateChangeEvent.getState()== PinState.HIGH) {
                    System.out.println("Input 1 off");
                }
                if (gpioPinDigitalStateChangeEvent.getState()== PinState.LOW) {
                    System.out.println("Input 1 on");
                }
            }
        });


        runIt(  );
    }

    private void runIt() {
    while (true)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
