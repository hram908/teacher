package com.blacksheep.teacher.model.dataEntity;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/9/12
 * Time: 1:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataQuest {

    private int id;
    private int testID;
    private String content;
    private String answer;



    public DataQuest(int id, int testID, String answer, String content) {
        this.id = id;
        this.answer = answer;
        this.testID = testID;
        this.content = content;
    }


    public int getTestID() {
        return testID;
    }

    public String getContent() {
        return content;
    }

    public String getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }

}
