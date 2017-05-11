package com.core.app.message;

public class ActionEvent {

    private Object data;

    public ActionEvent(String action) {
        mAction = action;
    }

    private String mAction;

    public void setAction(String action) {
        mAction = action;
    }

    public String getAction() {
        return mAction;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }


}
