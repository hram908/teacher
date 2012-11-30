package com.blacksheep.teacher.model.database;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.blacksheep.teacher.R;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/4/12
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBtestAcrtiv extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbtest);
       // SQLiteDatabase db = (new TeachDB(this)).getWritableDatabase();
        //DBManager dbManager = new DBManager();
        //dbManager.getLectures(this);
    }
}
