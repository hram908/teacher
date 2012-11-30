package com.blacksheep.teacher.model;

import com.blacksheep.teacher.model.dataEntity.DataTest;
import com.blacksheep.teacher.model.database.DBManager;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/17/12
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogTeach {

    public static Timestamp startTime;

    public static void addAnswer(int testSeesioID,int questionID,int result)
    {
        DBManager.getInstance().addAnswer(testSeesioID,questionID,startTime,new Timestamp(System.currentTimeMillis()),result);
    }

    public static long addTest(DataTest dataTest,int userID)
    {
        return DBManager.getInstance().addTestSession(dataTest.getId(),userID,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),0f);
    }

    public static long addTest(DataTest dataTest,int userID,Timestamp start,float result)
    {
        return DBManager.getInstance().addTestSession(dataTest.getTargerID(),userID,start,new Timestamp(System.currentTimeMillis()),result);
    }

    public static void updateTest(DataTest dataTest,Timestamp endTime,float result)
    {
         DBManager.getInstance().updateTestSessionEndTime(dataTest.getId(),endTime,result);
    }
    public static void updateTest(int testSessionID,float result)
    {
        DBManager.getInstance().updateTestSessionEndTime(testSessionID,new Timestamp(System.currentTimeMillis()),result);
    }
}
