package com.blacksheep.teacher.activites.lecture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.blacksheep.teacher.R;

/**
 * Created with IntelliJ IDEA.
 * User: default
 * Date: 4/22/12
 * Time: 9:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcLectureLoad extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_activity);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                finish();
                int testNumber = AcLectureLoad.this.getIntent().getExtras().getInt(AcStartLecture.LECTURE_ID);
                Intent intent = new Intent(AcLectureLoad.this, AcStartLecture.class);
                intent.putExtra(AcStartLecture.LECTURE_ID,testNumber);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        thread.start();
    }

}
