package mr.curk.piface;

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
