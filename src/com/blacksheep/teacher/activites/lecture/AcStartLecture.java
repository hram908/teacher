package com.blacksheep.teacher.activites.lecture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.activites.setting.AcSetting;
import com.blacksheep.teacher.activites.test.AcStartTest;
import com.blacksheep.teacher.activites.test.AcTestLoad;
import com.blacksheep.teacher.game.animated_view.SurfaceViewTeach;
import com.blacksheep.teacher.game.animated_view.ThreadAnimation;
import com.blacksheep.teacher.game.animated_view.ThreadLoadAnimation;
import com.blacksheep.teacher.game.examtest.AudioPhraseBuilder;
import com.blacksheep.teacher.game.lecture.LectureManager;
import com.blacksheep.teacher.model.database.DBManager;

public class AcStartLecture extends Activity
{
    public static final String LECTURE_ID="lecture_id";

    //static  MediaPlayer mediaPlayer;
    int lectureNumber;
    /** Called when the activity is first created. */

    LectureManager lectureManager;
    SurfaceViewTeach  surfaceViewTeach;
    Animation animShowMenu;
    Animation animHideMenu;
    Button btnStartStop;
    LinearLayout llMenu;
    PowerManager pm;
    PowerManager.WakeLock wl;
    private boolean endLecture;
    RelativeLayout rlStartStop;
    ImageView ivStartStop;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        lectureNumber = getIntent().getExtras().getInt(LECTURE_ID);
        endLecture =false;
        lectureManager = new LectureManager(AcStartLecture.this);
        lectureManager.setLectureNumber(lectureNumber);
        surfaceViewTeach = new SurfaceViewTeach(this,lectureManager.iFrameListener);
        lectureManager.setActivity(this);
//
       setContentView(R.layout.ac_start_lecture);
        llMenu = (LinearLayout) findViewById(R.id.ac_start_lecture_menu);
        animHideMenu = AnimationUtils.loadAnimation(this,R.anim.anim_ac_start_lecture_menu_hide);
        animShowMenu = AnimationUtils.loadAnimation(this,R.anim.anim_ac_start_lecture_menu_show);
        animHideMenu.setFillAfter(true);
        animShowMenu.setFillAfter(true);
        llMenu.setVisibility(View.INVISIBLE);
        btnStartStop = (Button) findViewById(R.id.ac_start_lecture_start_stop);
        rlStartStop = (RelativeLayout) findViewById(R.id.ac_start_lecture_rl_start_stop);
        ivStartStop = (ImageView) findViewById(R.id.ac_start_lecture_iv_start_stop);

        rlStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStopNew();
                if(endLecture)
                {
                    finish();
                    Intent intent = new Intent(AcStartLecture.this, AcLectureLoad.class);
                    intent.putExtra(AcStartLecture.LECTURE_ID,lectureNumber);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }
            }
        });

        Button menuStartTest=(Button) findViewById(R.id.AcStartLecture_start_test);
        if(lectureNumber==11)
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
        lectureManager.setTextViewFrameCount(textViewFrameCount);
        RelativeLayout linearLayout = (RelativeLayout)  findViewById(R.id.ll);
        setBackgroundGreeting(linearLayout);
        LinearLayout llExamples =(LinearLayout) findViewById(R.id.ll_examples);
        lectureManager.setLl_board(llExamples);
        surfaceViewTeach.setLayoutParams(lp);
        linearLayout.addView(surfaceViewTeach);
        DBManager.testAnim=true;
         pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
          wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
        wl.acquire();
        lectureManager.setOnCompletionListener(new LectureManager.OnCompletionListener() {
            @Override
            public void onCompletion() {
                llMenu.setVisibility(View.VISIBLE);
                llMenu.startAnimation(animShowMenu);
                lectureManager.stopFrameCount();
                endLecture = true;
                btnStartStop.setBackgroundResource(R.drawable.button_start);
            }
        });
        lectureManager.startLecture();

        Log.i(this.getClass().getName(),"onCreate");




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
    protected void onResume() {
        super.onResume();
        MyApplication.startedApp=true;
        Log.i(this.getClass().getName(),"onResume");

//        if(!DBManager.testAnim)
//            startStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(this.getClass().getName(),"onPause");
        if(DBManager.testAnim)
            startStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.i(this.getClass().getName(),"onDestroy");
        MyApplication.startedApp=false;
        lectureManager.stopFrameCount();
        ThreadLoadAnimation.setRunning(false);
        wl.release();
    }


    @Override
    public void onBackPressed() {

        Log.i(this.getClass().getName(),"onBackPressed");
        if(DBManager.testAnim)
            startStop();
        else
        {
            finish();
        }

//        llMenu.setVisibility(View.VISIBLE);
//        if(DBManager.testAnim)
//        {
//            llMenu.startAnimation(animShowMenu);
//            DBManager.testAnim=false;
//            AudioPhraseBuilder.mediaPlayerHead.pause();
//            btnStartStop.setBackgroundResource(R.drawable.button_start);
//        }
//        else
//        {
//
//            MyApplication.startedApp=false;
//            finish();
//        }



    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int eventaction = event.getAction();
//        event.ge
//
//        switch (eventaction) {
//            case MotionEvent.ACTION_DOWN:
//                // finger touches the screen
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                // finger moves on the screen
//                break;
//
//            case MotionEvent.ACTION_UP:
//                // finger leaves the screen
//                break;
//        }
//
//        // tell the system that we handled the event and no further processing is required
//        return true;
//    }

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
        if(endLecture)
        {

//            Intent intent = new Intent(this, AcStartLecture.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            intent.putExtra(AcStartLecture.LECTURE_ID,lectureNumber);
//            startActivity(intent);
//            finish();

            finish();
            Intent intent = new Intent(this, AcLectureLoad.class);
            intent.putExtra(AcStartLecture.LECTURE_ID,lectureNumber);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    private void startStop()
    {

        llMenu.setVisibility(View.VISIBLE);
        if(DBManager.testAnim)
        {
            lectureManager.stopFrameCount();
            try{
                AudioPhraseBuilder.mediaPlayerHead.pause();
            }
            catch (Exception e)
            {
                return;
            }


            llMenu.startAnimation(animShowMenu);
            DBManager.testAnim=false;
            btnStartStop.setBackgroundResource(R.drawable.button_start);
        }
        else
        {
           lectureManager.resumeFrameCount();
            try{
                AudioPhraseBuilder.mediaPlayerHead.start();
            }
            catch (Exception e)
            {
                return;
            }

            llMenu.startAnimation(animHideMenu);
            DBManager.testAnim=true;
            btnStartStop.setBackgroundResource(R.drawable.button_pause);
        }
    }
    private void startStopNew()
    {

        llMenu.setVisibility(View.VISIBLE);

        if(DBManager.testAnim)
        {
            ivStartStop.setVisibility(View.VISIBLE);
            lectureManager.stopFrameCount();
            try{
                AudioPhraseBuilder.mediaPlayerHead.pause();
            }
            catch (Exception e)
            {
                return;
            }


            llMenu.startAnimation(animShowMenu);
            DBManager.testAnim=false;
            ivStartStop.setImageResource(R.drawable.im_ac_start_lecture_pause_new);
        }
        else
        {
           // ivStartStop.setVisibility(View.INVISIBLE);
            lectureManager.resumeFrameCount();
            try{
                AudioPhraseBuilder.mediaPlayerHead.start();
            }
            catch (Exception e)
            {
                return;
            }

            llMenu.startAnimation(animHideMenu);
            DBManager.testAnim=true;
            ivStartStop.setImageResource(R.drawable.im_ac_start_lecture_play_new);
        }
    }

    private void startStopTest()
    {

        llMenu.setVisibility(View.VISIBLE);
        if(DBManager.testAnim)
        {
            lectureManager.stopFrameCount();
            try{
                AudioPhraseBuilder.mediaPlayerHead.pause();
            }
            catch (Exception e)
            {
                return;
            }


            llMenu.startAnimation(animShowMenu);
            DBManager.testAnim=false;
            btnStartStop.setBackgroundResource(R.drawable.button_start);
        }
        else
        {
            lectureManager.resumeFrameCount();
            try{
                AudioPhraseBuilder.mediaPlayerHead.start();
            }
            catch (Exception e)
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
