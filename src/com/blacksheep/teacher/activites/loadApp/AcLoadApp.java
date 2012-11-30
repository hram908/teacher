package com.blacksheep.teacher.activites.loadApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.activites.greeting.AcGreeting;


/**
 * Created by IntelliJ IDEA.
 * User: vovi
 * Date: 16.04.12
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class AcLoadApp extends Activity{
    Animation animation;
    ImageView logoNse;
    ImageView logoBs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_load_app);
        logoNse=(ImageView)findViewById(R.id.load_app_logo_nse);
        logoBs=(ImageView)findViewById(R.id.load_app_logo_bs);
        logoBs.setVisibility(View.INVISIBLE);
        logoNse.setImageResource(R.drawable.nse_logo_color);
        logoBs.setImageResource(R.drawable.bs_logo);
        Thread thr=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    animation= AnimationUtils.loadAnimation(AcLoadApp.this, R.anim.anim_ac_load_app_logo);
                    animation.setFillAfter(true);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            logoBs = null;
                            logoNse=null;
                            finish();
                            startGreeting();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }
                    });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            logoBs.setVisibility(View.VISIBLE);
                            logoNse.setVisibility(View.INVISIBLE);
                            logoBs.startAnimation(animation);
                        }
                    });



                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        thr.start();

    }
    private void startGreeting()
    {
        Intent intent=new Intent(this, AcGreeting.class);
        startActivity(intent);
    }
}
