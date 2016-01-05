package mr.curk.piface;

import java.util.Scanner;

/**
 * Created by mitja on 5.1.2016.
 */
public class PiConsole implements  Runnable {
    private  PiFaceModul piFaceModul;

    public PiConsole(PiFaceModul piFaceModul){
        this.piFaceModul=piFaceModul;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String consoleInputString;
        PiCommand piCommand = PiCommand.HELP;

        System.out.println("Pi Console started!");

        while (!(piCommand == piCommand.EXIT)){
            consoleInputString = scanner.nextLine();
            piCommand = PiCommand.setPiCommand(consoleInputString);
            piFaceModul.setCommand(piCommand);
        }

        scanner.close();
        System.out.println("Pi Console stopped!");
    }
}
