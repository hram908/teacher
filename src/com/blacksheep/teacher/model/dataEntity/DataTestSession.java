package com.blacksheep.teacher.model.dataEntity;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/17/12
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataTestSession {
    
    private int id;
    private int testID;
    private int userID;
    private Timestamp timesStart;
    private Timestamp timesEnd;
    private float result;

    public DataTestSession(int id, int testID, int userID, Timestamp timesStart, Timestamp timesEnd, float result) {
        this.result = result;
        this.id = id;
        this.userID = userID;
        this.timesStart = timesStart;
        this.timesEnd = timesEnd;
        this.testID = testID;
    }

    public int getId() {
        return id;
    }

    public int getTestID() {
        return testID;
    }

    public int getUserID() {
        return userID;
    }

    public Timestamp getTimesStart() {
        return timesStart;
    }

    public void setTimesStart(Timestamp timesStart) {
        this.timesStart = timesStart;
    }

    public Timestamp getTimesEnd() {
        return timesEnd;
    }

    public float getResult() {
        return result;
    }
}
