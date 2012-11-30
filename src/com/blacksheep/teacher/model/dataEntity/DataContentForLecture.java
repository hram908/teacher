package com.blacksheep.teacher.model.dataEntity;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/3/12
 * Time: 10:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataContentForLecture {
    private String content;
    private int start_frame;
    private int end_frame;
    private int lecture_id;

    
    
    
    public DataContentForLecture(int start_frame, int end_frame, String content, int ID)
    {
        this.content = content;
        this.start_frame = start_frame;
        this.end_frame = end_frame;
        this.lecture_id = ID;
    }

    public String getContent() {
        return content;
    }

    public int getStart_frame() {
        return start_frame;
    }

    public int getEnd_frame() {
        return end_frame;
    }

    public int getLecture_id() {
        return lecture_id;
    }
}
