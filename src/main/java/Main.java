import mr.curk.piface.PiFaceLogic;

import java.io.IOException;

/**
 * Created by one on 29.12.2015.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Malina started!");
        try {
            PiFaceLogic pi = new PiFaceLogic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
