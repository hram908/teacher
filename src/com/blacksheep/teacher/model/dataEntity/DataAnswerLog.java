package com.blacksheep.teacher.model.dataEntity;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/17/12
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataAnswerLog {
    
    private int id;
    private int testSessionID;
    private int questionID;
    private Timestamp timeStart;
    private Timestamp timeEnd;
    private int result;


    public DataAnswerLog(int id, int testSessionID, int questionID, Timestamp timeStart, Timestamp timeEnd, int result) {
        this.result = result;
        this.id = id;
        this.timeStart = timeStart;
        this.questionID = questionID;
        this.timeEnd = timeEnd;
        this.testSessionID = testSessionID;
    }

    public int getId() {
        return id;
    }

    public int getTestSessionID() {
        return testSessionID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public Timestamp getTimeStart() {
        return timeStart;
    }

    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public int getResult() {
        return result;
    }
}
