package com.blacksheep.teacher.nuanceApp;


import com.blacksheep.teacher.model.dataEntity.DataNames;

import java.util.LinkedList;

public class ResponceRecognize implements Cloneable {

    public static final int KEY_UNKNOWN_ERROR=0;
    public static final int KEY_NOT_INTERNET_ACCESS=1;
    public static final int KEY_WRITING_HAS_STOP=2;
    public static final int KEY_RESPONSE_SILENCE=3;
    public static final int KEY_NAME_FOUND=4;
    public static final int KEY_NAME_NOT_FOUND=5;
    public static final int KEY_RESPONSE_CORRECTLY=6;
    public static final int KEY_RESPONSE_NOT_STANDARD_BAD=7;
    public static final int KEY_RESPONSE_NOT_STANDARD_GOOD=8;
    public static final int KEY_RESPONSE_INCORRECTLY=9;
    public static final int KEY_RESPONSE_WRONG=10;
    public static final int KEY_WORD_FOUND=11;
    public static final int KEY_WORD_NOT_FOUND=12;




    private int key;
    private String text;

    public LinkedList<DataNames> getDatanames() {
        return datanames;
    }

    public void setDatanames(LinkedList<DataNames> datanames) {
        this.datanames = datanames;
    }

    private LinkedList<DataNames> datanames;
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
