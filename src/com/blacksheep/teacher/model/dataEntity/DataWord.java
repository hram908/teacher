package com.blacksheep.teacher.model.dataEntity;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/18/12
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataWord {

    public static final int TYPE_NAME=1;
    public static final int TYPE_PHRASE=2;
    public static final int TYPE_OPERATION=3;
    
    private int type;
    private int number;
    private String fileName;
    private int id;
    private int phraseID;


    public DataWord(int id, int phraseID, String fileName, int number, int type) {
        this.phraseID = phraseID;
        this.id = id;
        this.fileName = fileName;
        this.number = number;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public String getFileName() {
        return fileName;
    }

    public int getId() {
        return id;
    }

    public int getPhraseID() {
        return phraseID;
    }
}
