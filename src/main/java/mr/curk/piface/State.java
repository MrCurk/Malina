package mr.curk.piface;

/**
 * Created by Mr.Curk@gmail.com on 7.1.2016.
 */
public enum State {
    ON, OFF;
    public  static  State setState(String stateString){
        switch (stateString.toUpperCase()){
            case "ON":
                return  ON;
            case "OFF":
                return  OFF;
            default:
                return OFF;
        }

    }
}
