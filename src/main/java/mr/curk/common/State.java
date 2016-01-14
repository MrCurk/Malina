package mr.curk.common;

public enum State {
    ON, OFF;

    public boolean toBoolean(){
       return  (this == State.ON);
    }
}
