package com.blacksheep.teacher.model.dataEntity;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 2/25/12
 * Time: 10:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataLecture {

    private int id;

    private String name;
    private LinkedList<DataAnimation> listAnimations;
    private String audioPath;
    private LinkedList<DataContentForLecture> listContentForLectures;
    private int frameCount;


    public DataLecture()
    {

    }

    public DataLecture(String name)
    {
        this.name = name;
    }


    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public LinkedList<DataAnimation> getListAnimations()
    {
        return this.listAnimations;
    }
    public void setListAnimations(LinkedList<DataAnimation> list)
    {
        this.listAnimations=list;
    }

    public LinkedList<DataContentForLecture> getListContentForLectures() {
        return listContentForLectures;
    }

    public void setListContentForLectures(LinkedList<DataContentForLecture> listContentForLectures) {
        this.listContentForLectures = listContentForLectures;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }
}
