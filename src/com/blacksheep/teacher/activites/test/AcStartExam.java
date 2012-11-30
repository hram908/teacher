package com.blacksheep.teacher.activites.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
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
import com.blacksheep.teacher.activites.lecture.AcLectureLoad;
import com.blacksheep.teacher.activites.lecture.AcStartLecture;
import com.blacksheep.teacher.activites.setting.AcSetting;
import com.blacksheep.teacher.game.animated_view.SurfaceViewTeach;
import com.blacksheep.teacher.game.animated_view.ThreadLoadAnimation;
import com.blacksheep.teacher.game.examtest.ExamManager;
import com.blacksheep.teacher.game.examtest.RecognizeRequest;
import com.blacksheep.teacher.game.examtest.TestManager;
import com.blacksheep.teacher.model.database.DBManager;
import com.blacksheep.teacher.nuanceApp.ResponceRecognize;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/13/12
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcStartExam extends Activity{

    public static final String EXAM_QUESTION_COUNT = "EXAM_QUESTION_COUNT";
    ExamManager examManager;
    public static final String EXAM_QUESTION_LEVEL = "EXAM_QUESTION_LEVEL";


    public static final String TEST_ID = "test_id";


    int testNumber;
    /**
     * Called when the activity is first created.
     */

    TextView textViewNuance;

    Animation animShowMenu;
    Animation animHideMenu;
    LinearLayout llMenu;
    Button btnStartStop;
    PowerManager pm;
    PowerManager.WakeLock wl;
    private Button questHelp1;
    private Button questHelp2;
    private Button questHelp3;
    private Animation animShowHelp;
    private Animation animHideHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int countQuestion = getIntent().getExtras().getInt(EXAM_QUESTION_COUNT);
        int levelQuestion = getIntent().getExtras().getInt(EXAM_QUESTION_LEVEL);

        super.onCreate(savedInstanceState);
        examManager = new ExamManager(countQuestion, levelQuestion,AcStartExam.this,new QuestHelpListener());
        SurfaceViewTeach surfaceViewTeach = new SurfaceViewTeach(this,null);
        examManager.setActivity(this);

        setContentView(R.layout.ac_start_test);

        llMenu = (LinearLayout) findViewById(R.id.ac_start_lecture_menu);
        animHideMenu = AnimationUtils.loadAnimation(this, R.anim.anim_ac_start_lecture_menu_hide);
        animShowMenu = AnimationUtils.loadAnimation(this,R.anim.anim_ac_start_lecture_menu_show);
        animHideMenu.setFillAfter(true);
        animShowMenu.setFillAfter(true);
        llMenu.setVisibility(View.INVISIBLE);
        animHideMenu = AnimationUtils.loadAnimation(this, R.anim.anim_ac_start_lecture_menu_hide);
        animShowMenu = AnimationUtils.loadAnimation(this,R.anim.anim_ac_start_lecture_menu_show);
        animHideMenu.setFillAfter(true);
        animShowMenu.setFillAfter(true);

        int widthTeach=(int)(MyApplication.instance.getWidthTeach()*MyApplication.instance.getCoefficientTension());
        int heightTeach=(int)(MyApplication.instance.getHeightTeach()*MyApplication.instance.getCoefficientTension());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(widthTeach,heightTeach);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        RelativeLayout linearLayout = (RelativeLayout)  findViewById(R.id.ll);
        setBackgroundGreeting(linearLayout);
        LinearLayout llExamples =(LinearLayout) findViewById(R.id.ll_examples);
        examManager.setLl_board(llExamples);
        linearLayout.addView(surfaceViewTeach, lp);
        examManager.setOnCompletionListener(new TestManager.OnCompletionListener() {
            @Override
            public void onCompletion() {
                end = true;
            }

            @Override
            public void onCompletionForShowResult(int countOkAnswer) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

        });

        textViewNuance = (TextView) findViewById(R.id.ac_start_lecture_tv_nuance);

        examManager.setOnNuanceResponse(new TestManager.OnNuanceResponse() {
            @Override
            public void response(String str) {
                    textViewNuance.setText(str);
            }
        });
        examManager.start();
        btnStartStop = (Button) findViewById(R.id.ac_start_lecture_start_stop);
        Button btn = (Button) findViewById(R.id.ac_start_test_lectute_test);
        btn.setVisibility(View.GONE);
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
        wl.acquire();
        initQuestHelp();
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

    public void clickanim1(View v) {
        //testManager.responseListener.response(TestManager.RESULT_QUESTION_OK);\
        // RecognizeRequest.setResponse(RecognizeRequest.RESULT_QUESTION_OK);
        ResponceRecognize responceRecognize = new ResponceRecognize();
        responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_CORRECTLY);
        RecognizeRequest.setRespRecognize(responceRecognize);
    }

    public void clickanim2(View v) {
        //testManager.responseListener.response(TestManager.RESULT_QUESTION_INACCURATELY);
        // RecognizeRequest.setResponse(RecognizeRequest.RESULT_QUESTION_INACCURATELY);
        ResponceRecognize responceRecognize = new ResponceRecognize();
        responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_INCORRECTLY);
        RecognizeRequest.setRespRecognize(responceRecognize);
    }

    public void clickanim3(View v) {
        // testManager.responseListener.response(TestManager.RESULT_QUESTION_SILENCE);
        //RecognizeRequest.setResponse(RecognizeRequest.RESULT_QUESTION_SILENCE);
        ResponceRecognize responceRecognize = new ResponceRecognize();
        responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_SILENCE);
        RecognizeRequest.setRespRecognize(responceRecognize);
    }

    public void clickanim4(View v) {
        // testManager.responseListener.response(TestManager.RESULT_QUESTION_WRONG);
        //RecognizeRequest.setResponse(RecognizeRequest.RESULT_QUESTION_WRONG);
        ResponceRecognize responceRecognize = new ResponceRecognize();
        responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_WRONG);
        RecognizeRequest.setRespRecognize(responceRecognize);
    }



    @Override
    protected void onResume() {

        super.onResume();
        MyApplication.startedApp=true;


    }

    @Override
    public void onBackPressed() {
        if (end)return;
        llMenu.setVisibility(View.VISIBLE);
        if(examManager.isWork)
        {
            llMenu.startAnimation(animShowMenu);
            examManager.pause();
            btnStartStop.setBackgroundResource(R.drawable.button_start);
        }
        else
        {
            MyApplication.startedApp=false;
            finish();
        }

    }

    @Override
    protected void onPause() {

        super.onPause();
        examManager.pause();
        llMenu.setVisibility(View.VISIBLE);
        llMenu.startAnimation(animShowMenu);
        btnStartStop.setBackgroundResource(R.drawable.button_start);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        wl.release();
        MyApplication.startedApp=false;
        examManager.stopRecognize();
        ThreadLoadAnimation.setRunning(false);
    }

    private boolean end = false;

    public void clickStopTest(View v) {
        if(end)
            return;
        llMenu.setVisibility(View.VISIBLE);
        if(examManager.isWork)
        {
            llMenu.startAnimation(animShowMenu);
            examManager.pause();
            btnStartStop.setBackgroundResource(R.drawable.button_start);
        }
        else
        {
            llMenu.startAnimation(animHideMenu);
            examManager.resume();
            btnStartStop.setBackgroundResource(R.drawable.button_pause);
        }



    }

//
//    public void clickanim1(View v) {
//        //testManager.responseListener.response(TestManager.RESULT_QUESTION_OK);\
//        // RecognizeRequest.setResponse(RecognizeRequest.RESULT_QUESTION_OK);
//        ResponceRecognize responceRecognize = new ResponceRecognize();
//        responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_CORRECTLY);
//        RecognizeRequest.setRespRecognize(responceRecognize);
//    }
//    int i=1;
//    public void clickanim2(View v) {
//        //testManager.responseListener.response(TestManager.RESULT_QUESTION_INACCURATELY);
//        // RecognizeRequest.setResponse(RecognizeRequest.RESULT_QUESTION_INACCURATELY);
////        ResponceRecognize responceRecognize = new ResponceRecognize();
////        responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_INCORRECTLY);
////        RecognizeRequest.setRespRecognize(responceRecognize);
//        examManager.addContentToBoard("1x"+i+"="+i);
//        i++;
//    }
//
//    public void clickanim3(View v) {
//        // testManager.responseListener.response(TestManager.RESULT_QUESTION_SILENCE);
//        //RecognizeRequest.setResponse(RecognizeRequest.RESULT_QUESTION_SILENCE);
//        ResponceRecognize responceRecognize = new ResponceRecognize();
//        responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_SILENCE);
//        RecognizeRequest.setRespRecognize(responceRecognize);
//    }
//
//    public void clickanim4(View v) {
//        // testManager.responseListener.response(TestManager.RESULT_QUESTION_WRONG);
//        //RecognizeRequest.setResponse(RecognizeRequest.RESULT_QUESTION_WRONG);
//        ResponceRecognize responceRecognize = new ResponceRecognize();
//        responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_WRONG);
//        RecognizeRequest.setRespRecognize(responceRecognize);
//    }

    public void clickStartLecture(View v)
    {
        finish();
        Intent intent = new Intent(this, AcLectureLoad.class);
        intent.putExtra(AcStartLecture.LECTURE_ID,testNumber);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);

    }

    public void clickGoInMenu(View v)
    {
        finish();
    }

    public void clickHelpQuest(View v) {
        if(examManager.isWork)
            examManager.clickHelpQuestion(v);
    }
    private class QuestHelpListener implements TestManager.OnQuestHelpAction{

        @Override
        public void show(String content) {
            if(MyApplication.getInstance().getPrefs().getBoolean(AcSetting.SETTING_QUEST_HELP,true)){
                //testManager.isShownQuestHelp=true;
                String newString=content.replace(" ","").replace("=","");
                final List<Integer> lst = DBManager.getInstance().getTips(newString);
                questHelp1.setText(lst.get(0).toString());
                questHelp2.setText(lst.get(1).toString());
                questHelp3.setText(lst.get(2).toString());

                questHelp1.setVisibility(View.VISIBLE);
                questHelp1.startAnimation(animShowHelp);

                questHelp2.setVisibility(View.VISIBLE);
                questHelp2.startAnimation(animShowHelp);

                questHelp3.setVisibility(View.VISIBLE);
                questHelp3.startAnimation(animShowHelp);
            }
            setClickable(true);
        }

        @Override
        public void hide() {
            if(MyApplication.getInstance().getPrefs().getBoolean(AcSetting.SETTING_QUEST_HELP,true)){
                questHelp1.setBackgroundResource(R.drawable.button_ac_start_test_quest_help);
                questHelp2.setBackgroundResource(R.drawable.button_ac_start_test_quest_help);
                questHelp3.setBackgroundResource(R.drawable.button_ac_start_test_quest_help);
                questHelp1.setVisibility(View.INVISIBLE);
                questHelp1.startAnimation(animHideHelp);

                questHelp2.setVisibility(View.INVISIBLE);
                questHelp2.startAnimation(animHideHelp);

                questHelp3.setVisibility(View.INVISIBLE);
                questHelp3.startAnimation(animHideHelp);
            }
        }

        @Override
        public void setBg(View view) {

            switch (view.getId()){
                case R.id.ac_start_test_help_1:{
                    questHelp1.setBackgroundResource(R.drawable.help_bg_selected);
                    questHelp2.setBackgroundResource(R.drawable.button_ac_start_test_quest_help);
                    questHelp3.setBackgroundResource(R.drawable.button_ac_start_test_quest_help);
                    return;
                }
                case R.id.ac_start_test_help_2:{
                    questHelp2.setBackgroundResource(R.drawable.help_bg_selected);
                    questHelp1.setBackgroundResource(R.drawable.button_ac_start_test_quest_help);
                    questHelp3.setBackgroundResource(R.drawable.button_ac_start_test_quest_help);
                    return;
                }
                case R.id.ac_start_test_help_3:{
                    questHelp3.setBackgroundResource(R.drawable.help_bg_selected);
                    questHelp2.setBackgroundResource(R.drawable.button_ac_start_test_quest_help);
                    questHelp1.setBackgroundResource(R.drawable.button_ac_start_test_quest_help);
                    return;
                }
            }
            setClickable(false);


        }
    }
    private void setClickable(boolean isClickable){
        questHelp1.setClickable(isClickable);
        questHelp2.setClickable(isClickable);
        questHelp3.setClickable(isClickable);
    }
    private void initQuestHelp(){
        animShowHelp = AnimationUtils.loadAnimation(this, R.anim.anim_ac_start_test_show_help);
        animShowHelp.setFillAfter(true);
        animHideHelp = AnimationUtils.loadAnimation(this, R.anim.anim_ac_start_test_hide_help);
        animHideHelp.setFillAfter(false);
        questHelp1=(Button)findViewById(R.id.ac_start_test_help_1);
        questHelp2=(Button)findViewById(R.id.ac_start_test_help_2);
        questHelp3=(Button)findViewById(R.id.ac_start_test_help_3);
        questHelp1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        questHelp2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/segoepr.ttf"));
        questHelp3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/segoepr.ttf"));


    }


}
