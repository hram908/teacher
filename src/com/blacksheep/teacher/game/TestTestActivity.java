package com.blacksheep.teacher.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.activites.setting.AcSetting;
import com.blacksheep.teacher.activites.test.AcStartTest;
import com.blacksheep.teacher.activites.test.AcTestLoad;
import com.blacksheep.teacher.game.animated_view.SurfaceViewTeach;
import com.blacksheep.teacher.game.examtest.AudioPhraseBuilder;
import com.blacksheep.teacher.game.examtest.ManagerActions;
import com.blacksheep.teacher.game.examtest.RecognizeRequest;
import com.blacksheep.teacher.game.lecture.LectureManager;
import com.blacksheep.teacher.model.database.DBManager;

/**
 * Created with IntelliJ IDEA.
 * User: Defafault
 * Date: 08.07.12
 * Time: 17:51
 * To change this template use File | Settings | File Templates.
 */
public class TestTestActivity extends Activity {

    public static final String LECTURE_ID="lecture_id";

    //static  MediaPlayer mediaPlayer;
    int lectureNumber;
    /** Called when the activity is first created. */


    TestTestManager testTestManager;
    SurfaceViewTeach surfaceViewTeach;
    Animation animShowMenu;
    Animation animHideMenu;
    Button btnStartStop;
    LinearLayout llMenu;
    PowerManager pm;
    PowerManager.WakeLock wl;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        testTestManager = new TestTestManager(this);

        surfaceViewTeach = new SurfaceViewTeach(this,testTestManager.iFrameListener);


        setContentView(R.layout.ac_start_lecture);
        llMenu = (LinearLayout) findViewById(R.id.ac_start_lecture_menu);
        animHideMenu = AnimationUtils.loadAnimation(this, R.anim.anim_ac_start_lecture_menu_hide);
        animShowMenu = AnimationUtils.loadAnimation(this,R.anim.anim_ac_start_lecture_menu_show);
        animHideMenu.setFillAfter(true);
        animShowMenu.setFillAfter(true);
        llMenu.setVisibility(View.INVISIBLE);
        btnStartStop = (Button) findViewById(R.id.ac_start_lecture_start_stop);


        Button menuStartTest=(Button) findViewById(R.id.AcStartLecture_start_test);
        if(lectureNumber==0)
        {
            menuStartTest.setVisibility(View.INVISIBLE);
        }


        //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(300, 288);
        int widthTeach=(int)(MyApplication.instance.getWidthTeach()*MyApplication.instance.getCoefficientTension());
        int heightTeach=(int)(MyApplication.instance.getHeightTeach()*MyApplication.instance.getCoefficientTension());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(widthTeach, heightTeach);
        //   RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        // lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        TextView textViewFrameCount = (TextView) findViewById(R.id.tv_frame_count);

        RelativeLayout linearLayout = (RelativeLayout)  findViewById(R.id.ll);
       // setBackgroundGreeting(linearLayout);
        LinearLayout llExamples =(LinearLayout) findViewById(R.id.ll_examples);

        surfaceViewTeach.setLayoutParams(lp);
        linearLayout.addView(surfaceViewTeach);
        DBManager.testAnim=true;
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
        wl.acquire();


      // testTestManager.startLecture();




    }


    public void clickTestTest(View v)
    {
       // testTestManager.responseOk();
        //testTestManager.doQuestion();
//        ManagerActions managerActions = new ManagerActions(this);
//        managerActions.doWaitShort(new ManagerActions.IActionComplete() {
//            @Override
//            public void onComplete() {
//                //To change body of implemented methods use File | Settings | File Templates.
//            }
//        });
    }

    public void clickTestTest2(View v)
    {
        //testTestManager.responseOk2();
        //testTestManager.setResponse();
        ManagerActions managerActions = new ManagerActions(this);
        managerActions.doWaitLong(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void clickTestTest3(View v)
    {
        //testTestManager.responseOk2();
        //testTestManager.setResponse();
        //RecognizeRequest.setResponse(RecognizeRequest.RESULT_QUESTION_OK);
    }

    public void clickTestTest4(View v)
    {
        //testTestManager.responseOk2();
        //testTestManager.setResponse();
        ManagerActions managerActions = new ManagerActions(this);
        managerActions.doWaitLong(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    private void setBackgroundGreeting(RelativeLayout relativeLayout)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
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

    @Override
    protected void onStart() {
        super.onStart();

    }


    boolean bbbb=true;

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.startedApp=true;
        if(!bbbb)
        {

        }
        else
        {
            // DBManager.testAnim=true;
            //  AudioPhraseBuilder.mediaPlayerHead.start();
        }
        bbbb=false;
//        if(!DBManager.testAnim)
//        {
//            DBManager.testAnim=true;
//            if(!AudioPhraseBuilder.isReleased)
//                AudioPhraseBuilder.mediaPlayerHead.start();
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //MyApplication.startedApp=false;
//        if(DBManager.testAnim)
//        {
//            DBManager.testAnim=false;
//            if(!AudioPhraseBuilder.isReleased)
//                AudioPhraseBuilder.mediaPlayerHead.pause();
//        }
        // startStop();
        llMenu.setVisibility(View.VISIBLE);
        try{
            AudioPhraseBuilder.mediaPlayerHead.pause();
        }
        catch (IllegalStateException e)
        {
            return;
        }


        llMenu.startAnimation(animShowMenu);
        DBManager.testAnim=false;
        btnStartStop.setBackgroundResource(R.drawable.button_start);

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        MyApplication.startedApp=false;

        wl.release();
    }


    @Override
    public void onBackPressed() {

        llMenu.setVisibility(View.VISIBLE);
        if(DBManager.testAnim)
        {
            llMenu.startAnimation(animShowMenu);
            DBManager.testAnim=false;
            AudioPhraseBuilder.mediaPlayerHead.pause();
            btnStartStop.setBackgroundResource(R.drawable.button_start);
        }
        else
        {

            MyApplication.startedApp=false;
            finish();
        }



    }

    public void clickStartTest(View v) {
        finish();
        //Thread.sleep(2000);
        // Intent intent = new Intent(this, AcStartTest.class);
        //intent.putExtra(AcStartTest.TEST_ID,lectureNumber);
        //startActivity(intent);

        Intent intent = new Intent(this, AcTestLoad.class);
        intent.putExtra(AcStartTest.TEST_ID,lectureNumber);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }


    public void clickStop(View v)
    {
        startStop();
    }

    private void startStop()
    {
        llMenu.setVisibility(View.VISIBLE);
        if(DBManager.testAnim)
        {
            try{
                AudioPhraseBuilder.mediaPlayerHead.pause();
            }
            catch (IllegalStateException e)
            {
                return;
            }


            llMenu.startAnimation(animShowMenu);
            DBManager.testAnim=false;
            btnStartStop.setBackgroundResource(R.drawable.button_start);
        }
        else
        {
            try{
                AudioPhraseBuilder.mediaPlayerHead.start();
            }
            catch (IllegalStateException e)
            {
                return;
            }

            llMenu.startAnimation(animHideMenu);
            DBManager.testAnim=true;
            btnStartStop.setBackgroundResource(R.drawable.button_pause);
        }
    }

    public void clickGoInMenu(View v)
    {
        finish();
    }
}
