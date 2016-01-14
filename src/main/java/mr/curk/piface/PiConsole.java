package mr.curk.piface;

import java.util.Scanner;

public class PiConsole implements  Runnable {
    private final PiFaceModule piFaceModule;

    public PiConsole(PiFaceModule piFaceModule){
        this.piFaceModule = piFaceModule;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        PiCommand piCommand = PiCommand.HELP;

        System.out.println("Pi Console started!");

        while (piCommand != PiCommand.EXIT){
            piCommand = PiCommand.setPiCommand(scanner.nextLine());
            piFaceModule.setCommand(piCommand);
        }

        scanner.close();
        System.out.println("Pi Console stopped!");
    }
}
