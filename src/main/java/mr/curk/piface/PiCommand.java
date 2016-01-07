package mr.curk.piface;

/**
 * Created by Mr.Curk@gmail.com on 5.1.2016.
 */
public enum PiCommand {
    OUTPUT_0_ON, OUTPUT_0_OFF,
    OUTPUT_1_ON, OUTPUT_1_OFF,
    OUTPUT_2_ON, OUTPUT_2_OFF,
    OUTPUT_3_ON, OUTPUT_3_OFF,
    OUTPUT_4_ON, OUTPUT_4_OFF,
    OUTPUT_5_ON, OUTPUT_5_OFF,
    OUTPUT_6_ON, OUTPUT_6_OFF,
    OUTPUT_7_ON, OUTPUT_7_OFF,
    HELP, RESET, EXIT, STATUS
    ;

    public  static  PiCommand setPiCommand(String commandString){
        switch (commandString.toUpperCase()){
            case "OP0 ON":
                return  OUTPUT_0_ON;
            case "OP0 OFF":
                return  OUTPUT_0_OFF;
            case "OP1 ON":
                return  OUTPUT_1_ON;
            case "OP1 OFF":
                return  OUTPUT_1_OFF;
            case "OP2 ON":
                return  OUTPUT_2_ON;
            case "OP2 OFF":
                return  OUTPUT_2_OFF;
            case "OP3 ON":
                return  OUTPUT_3_ON;
            case "OP3 OFF":
                return  OUTPUT_3_OFF;
            case "OP4 ON":
                return  OUTPUT_4_ON;
            case "OP4 OFF":
                return  OUTPUT_4_OFF;
            case "OP5 ON":
                return  OUTPUT_5_ON;
            case "OP5 OFF":
                return  OUTPUT_5_OFF;
            case "OP6 ON":
                return  OUTPUT_6_ON;
            case "OP6 OFF":
                return  OUTPUT_6_OFF;
            case "OP7 ON":
                return  OUTPUT_7_ON;
            case "OP7 OFF":
                return  OUTPUT_7_OFF;
            case "STATUS":
            case "S":
                return  STATUS;
            case "RESET":
                return RESET;
            case "Q":
            case "QUIT":
            case "EXIT":
                return EXIT;
            default:
                return HELP;
        }

    }

    public static void printHelp(){
        System.out.println("op[0-7] on - turn output n on.");
        System.out.println("op[0-7] off - turn output n off.");
        System.out.println("status or s -  display status of inputs and outputs ");
        System.out.println("reset -  reset PiFaceModul ");
        System.out.println("exit or quit or q - exit");
    }
}
