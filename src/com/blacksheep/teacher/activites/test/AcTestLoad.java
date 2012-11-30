package com.blacksheep.teacher.activites.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.blacksheep.teacher.R;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/20/12
 * Time: 1:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class AcTestLoad extends Activity{
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
                int testNumber = AcTestLoad.this.getIntent().getExtras().getInt(AcStartTest.TEST_ID);
                Intent intent = new Intent(AcTestLoad.this, AcStartTest.class);
                intent.putExtra(AcStartTest.TEST_ID,testNumber);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        thread.start();
    }
}
