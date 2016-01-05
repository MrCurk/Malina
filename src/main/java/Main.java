import mr.curk.piface.PiConsole;
import mr.curk.piface.PiFaceModul;

import java.io.IOException;

/**
 * Created by one on 29.12.2015.
 */
public class Main {

    public static void main(String[] args) {
        PiFaceModul piFaceModul = null;
        PiConsole piConsole;


        piConsole = new PiConsole(piFaceModul);

        try {
            piFaceModul = new PiFaceModul();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(piFaceModul).start();
        new Thread(piConsole).start();
        System.out.println("Malina started!");

    }
}