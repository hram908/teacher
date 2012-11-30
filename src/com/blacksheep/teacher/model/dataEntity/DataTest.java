package com.blacksheep.teacher.model.dataEntity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/9/12
 * Time: 12:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataTest {
    
    public final static int TYPE_SCIENCE = -1;
    public final static int TYPE_THEME = -2;
    public final static int TYPE_LECTURE = -3;

    
    private int id;
    private int type;
    private int targerID;
    private String title;
    private String decription;
    
    private ArrayList<DataQuest> listQuests;

    public DataTest(int id, int type, int targerID, String title, String decription) {
        this.id = id;
        this.type = type;
        this.targerID = targerID;
        this.decription = decription;
        this.title = title;
    }

    public DataTest() {
        
    }


    public int getType() {
        return type;
    }

    public int getTargerID() {
        return targerID;
    }

    public String getTitle() {
        return title;
    }

    public String getDecription() {
        return decription;
    }

    public int getId() {
        return id;
    }

    public ArrayList<DataQuest> getListQuests() {
        return listQuests;
    }

    public void setListQuests(List<DataQuest> listQuests) {
        this.listQuests = new ArrayList<DataQuest>(listQuests);
    }
}
