package mr.curk.piface;

import java.util.Scanner;

/**
 * Created by Mr.Curk@gmail.com on 5.1.2016.
 */
public class PiConsole implements  Runnable {
    private  PiFaceModule piFaceModule;

    public PiConsole(PiFaceModule piFaceModule){
        this.piFaceModule = piFaceModule;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        PiCommand piCommand = PiCommand.HELP;

        System.out.println("Pi Console started!");

        while (piCommand != piCommand.EXIT){
            piCommand = PiCommand.setPiCommand(scanner.nextLine());
            piFaceModule.setCommand(piCommand);
        }

        scanner.close();
        System.out.println("Pi Console stopped!");
    }
}
