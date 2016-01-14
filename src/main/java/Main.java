import mr.curk.piface.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PiFaceModule piFaceModule;
        PiConsole piConsole;


        piFaceModule = new PiFaceModule();
        piConsole = new PiConsole(piFaceModule);

        new Thread(piConsole).start();
        System.out.println("Malina started!");

    }
}
