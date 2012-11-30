package com.blacksheep.teacher.activites.greeting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.activites.mainScreen.AcMainTeachScreen;

/**
 * Created with IntelliJ IDEA.
 * User: vovi
 * Date: 27.04.12
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
public class AcGreetingLoad extends Activity {
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
                    Intent intent = new Intent(AcGreetingLoad.this, AcMainTeachScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }
            });
            thread.start();
        }

}
