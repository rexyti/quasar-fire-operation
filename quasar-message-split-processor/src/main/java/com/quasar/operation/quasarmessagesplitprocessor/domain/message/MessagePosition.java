package com.quasar.operation.quasarmessagesplitprocessor.domain.message;

public class MessagePosition {
    private Float x;
    private Float y;

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public boolean isValid (){
        return x!=null && y!=null;
    }

}
