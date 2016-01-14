package mr.curk.piface;

public enum State {
    ON, OFF;

    public boolean toBoolean(){
       return  (this==State.ON ? true : false);
    }
}
