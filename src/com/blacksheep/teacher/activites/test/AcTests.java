package com.blacksheep.teacher.activites.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.activites.lecture.AcLecture;
import com.blacksheep.teacher.activites.lecture.AcStartLecture;
import com.blacksheep.teacher.activites.lecture.AdtLecture;
import com.blacksheep.teacher.model.dataEntity.DataTest;
import com.blacksheep.teacher.model.database.DBManager;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/9/12
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcTests extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_tests);
        ListView listView = (ListView) findViewById(R.id.list_lectures);
        LinkedList<DataTest> dataTests = new LinkedList<DataTest>(DBManager.getInstance().getTests());

        AdtTest lectureAdapter = new AdtTest(this,dataTests,new AdtTest.IClickLecture() {
            @Override
            public void click(int lectureID) {
                Intent intent = new Intent(AcTests.this, AcStartTest.class);
                intent.putExtra(AcStartTest.TEST_ID,lectureID);
                startActivity(intent);
            }
        });
        listView.setAdapter(lectureAdapter);

    }
}
