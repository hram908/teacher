package com.blacksheep.teacher.activites.greeting;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.activites.setting.AcSetting;
import com.blacksheep.teacher.game.animated_view.SurfaceViewTeach;
import com.blacksheep.teacher.game.animated_view.ThreadLoadAnimation;
import com.blacksheep.teacher.game.examtest.AudioPhraseBuilder;
import com.blacksheep.teacher.game.greeting.GreetingManager;
import com.blacksheep.teacher.model.database.DBManager;

/**
 * Created by IntelliJ IDEA.
 * User: vovi
 * Date: 16.04.12
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
public class AcGreeting extends Activity{
    GreetingManager greetingManager;
    SurfaceViewTeach surfaceViewTeach;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_greeting);
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        greetingManager=new GreetingManager(this);
        surfaceViewTeach = new SurfaceViewTeach(this,greetingManager.iFrameListener);
        greetingManager.setActivity(this);

        int widthTeach=(int)(MyApplication.instance.getWidthTeach()*MyApplication.instance.getCoefficientTension());
        int heightTeach=(int)(MyApplication.instance.getHeightTeach()*MyApplication.instance.getCoefficientTension());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(widthTeach,heightTeach);
        //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 427);
        //   RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        TextView textViewFrameCount = (TextView) findViewById(R.id.tv_frame_count2);
        greetingManager.setTextViewFrameCount(textViewFrameCount);
        RelativeLayout linearLayout = (RelativeLayout)  findViewById(R.id.ac_greeting_desk);



        setBackgroundGreeting(linearLayout);
        surfaceViewTeach.setLayoutParams(lp);
        linearLayout.addView(surfaceViewTeach);
        DBManager.testAnim=true;
        if(prefs.getBoolean("isFirstEnter",true))
        {
            greetingManager.startFirstGreeting();
        }
        else greetingManager.startGreeting();
       // greetingManager.startFirstGreeting();


    }
    private void setBackgroundGreeting(RelativeLayout relativeLayout)
    {

        switch (prefs.getInt(AcSetting.SETTINGS_DESK,AcSetting.SETTINGS_DESK_GREEN))
        {
            case 0:{
                relativeLayout.setBackgroundResource(R.drawable.back_yellow);
                break;
            }
            case 1:{
                relativeLayout.setBackgroundResource(R.drawable.back_green);
                break;
            }
            case 2:{
                relativeLayout.setBackgroundResource(R.drawable.back_red);
                break;
            }
            case 3:{
                relativeLayout.setBackgroundResource(R.drawable.back_blue);
                break;
            }
            case 4:{
                relativeLayout.setBackgroundResource(R.drawable.back_voilet);
                break;
            }
            default:break;
        }

    }

    PowerManager pm;
    PowerManager.WakeLock wl;

    @Override
    protected void onStart() {
        super.onStart();
        surfaceViewTeach.setFrameCount(greetingManager.getFrameCount());
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
        wl.acquire();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.startedApp=true;
        if(greetingManager.isListenResponce)greetingManager.resume();
        if(!DBManager.testAnim)
        {
            DBManager.testAnim=true;
            if(!AudioPhraseBuilder.isReleased)
                AudioPhraseBuilder.mediaPlayerHead.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //MyApplication.startedApp=false;
        if(greetingManager.isListenResponce)greetingManager.stop();
        if(DBManager.testAnim)
        {
            DBManager.testAnim=false;
            if(!AudioPhraseBuilder.isReleased)
                AudioPhraseBuilder.mediaPlayerHead.pause();
        }
    }

    @Override
    protected void onDestroy() {


        MyApplication.startedApp=false;
        if( greetingManager.animationHead!=null)
            greetingManager.animationHead.destroy();
        ThreadLoadAnimation.setRunning(false);
        super.onDestroy();
        wl.release();

        //if(greetingManager.isListenResponce)greetingManager.pause();


    }


    @Override
    public void onBackPressed() {

        MyApplication.startedApp=false;
        finish();
    }

}
