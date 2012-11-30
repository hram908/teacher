package com.blacksheep.teacher.activites.mainScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.activites.Info;
import com.blacksheep.teacher.activites.lecture.AcStartLecture;
import com.blacksheep.teacher.activites.setting.AcSetting;
import com.blacksheep.teacher.activites.test.AcStartExam;
import com.blacksheep.teacher.game.examtest.ExamManager;
import com.blacksheep.teacher.model.database.DBManager;
import com.blacksheep.teacher.presentCard.AcPresentCard;
import com.zubrilka.AcZubrilka;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 4/5/12
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcMainTeachScreen extends Activity{

    private ViewFlow viewFlow;
    private ListView listView;
    TableLayout tableLayoutLesson;
    PowerManager pm;
    PowerManager.WakeLock wl;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.title_layout);
        setContentView(R.layout.ac_main_teach_screen);
        viewFlow = (ViewFlow) findViewById(R.id.viewflow);
        AdtMainTeachScreen adapter = new AdtMainTeachScreen(this);
        viewFlow.setAdapter(adapter,1);
        // TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
        CircleFlowIndicator indicator = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
        // indicator.setTitleProvider(adapter);
        viewFlow.setFlowIndicator(indicator);
        tableLayoutLesson = (TableLayout) findViewById(R.id.tbLesson);
        Button settingsText=(Button)findViewById(R.id.AcMainScreenSettings_textSettings);
        settingsText.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/segoepr.ttf"));


        Log.i(this.getClass().getName(), "onCreate");


    }

    @Override
    protected void onStart() {
        super.onStart();    //To change body of overridden methods use File | Settings | File Templates.
        fillLessons();
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Button ex1=(Button) findViewById(R.id.ac_main_teach_screen_main_ex1);
        ex1.setBackgroundResource(R.drawable.button_ac_main_teach_screen_main_ex1);
        if(checkAvailableExamAndStar(1))
        {
            ex1.setBackgroundResource(R.drawable.button_ac_main_teach_screen_main_ex1_star);
            if(preferences.getBoolean(ExamManager.IS_FINISH_EXAM_1,false))
            {

                ex1.setBackgroundResource(R.drawable.button_ac_main_teach_screen_main_ex1_enabled);

            }
        }


        Button ex2=(Button) findViewById(R.id.ac_main_teach_screen_main_ex2);
        ex2.setBackgroundResource(R.drawable.button_ac_main_teach_screen_main_ex2);
        if(checkAvailableExamAndStar(2))
        {
            ex2.setBackgroundResource(R.drawable.button_ac_main_teach_screen_main_ex2_star);
            if(preferences.getBoolean(ExamManager.IS_FINISH_EXAM_2,false))
            {

                ex2.setBackgroundResource(R.drawable.button_ac_main_teach_screen_main_ex2_enabled);
            }
        }
        Button ex3=(Button) findViewById(R.id.ac_main_teach_screen_main_ex3);
        ex3.setBackgroundResource(R.drawable.button_ac_main_teach_screen_main_ex3);
        if(checkAvailableExamAndStar(3))
        {
            ex3.setBackgroundResource(R.drawable.button_ac_main_teach_screen_main_ex3_star);
            if(preferences.getBoolean(ExamManager.IS_FINISH_EXAM_3,false))
            {
                ex3.setBackgroundResource(R.drawable.button_ac_main_teach_screen_main_ex3_enabled);
            }
        }

        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
        wl.acquire();
        Log.i(this.getClass().getName(),"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        /*CheckInternet connInternet=new CheckInternet(AcMainTeachScreen.this);
        if(!connInternet.isOnline())
        {
            connInternet.dialogAppear();
        }
        Log.i(this.getClass().getName(),"onResume");*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
        wl.release();
        Log.i(this.getClass().getName(),"onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        viewFlow.onConfigurationChanged(newConfig);
    }




    ArrayList<CnTeachTableItem> cnTeachTableItems = new ArrayList<CnTeachTableItem>();

    private void fillLessons()
    {
        tableLayoutLesson.removeAllViewsInLayout();
        TableRow tableRow=null;
         cnTeachTableItems.clear();
        HashMap<Integer,Float> lessonsData = DBManager.getInstance().getStarForTest();

        for (int i=0;i<10;i++)
        {
            if(i%5==0)
            {
                tableRow = new TableRow(this);
                tableLayoutLesson.addView(tableRow);
            }
            TableRow.LayoutParams lp = new  TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10, 10, 10, 10);
            float fresult = 0;
            if(lessonsData.containsKey(i+1))
                  fresult = lessonsData.get(i+1);
            int iresult=0;
            if(fresult>=0.8f)
                iresult=1;
            if(fresult>=0.9f)
                iresult=2;
            if(fresult>=1.0f)
                iresult=3;
            
            CnTeachTableItem cnTeachTableItem = new CnTeachTableItem(this,getLessonImage(i+1),iresult);             
            cnTeachTableItem.button.setTag(i+1);
            cnTeachTableItem.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*CheckInternet connInternet=new CheckInternet(AcMainTeachScreen.this);
                    if(false)
                    {
                        connInternet.dialogAppear();
                    }
                    else{*/
                    int lectureID =(Integer) ((Button) view).getTag();
                    Intent intent = new Intent(AcMainTeachScreen.this, AcStartLecture.class);
                    intent.putExtra(AcStartLecture.LECTURE_ID,lectureID);
                    startActivity(intent);
                //    }
                }
            });
            //cnTeachTableItem.setLayoutParams(lp);
            tableRow.addView(cnTeachTableItem,lp);
            cnTeachTableItems.add(cnTeachTableItem);
        }
    }

    public void clickTestLecture(View v)
    {
        /*CheckInternet connInternet=new CheckInternet(AcMainTeachScreen.this);
        if(false)
        {
            connInternet.dialogAppear();
        }
        else {*/
        Intent intent = new Intent(AcMainTeachScreen.this, AcStartLecture.class);
        intent.putExtra(AcStartLecture.LECTURE_ID,11);
        startActivity(intent);
       // }
    }
    
    public void clickPresent(View v)
    {

        Intent intent = new Intent(this, AcPresentCard.class);
        startActivity(intent);

    }

    public void clickFbGroup(View v)
    {
        String url = "http://www.facebook.com/pages/%D0%A3%D1%87%D0%B8%D0%BB%D0%BA%D0%B0-%D0%90%D0%B3%D0%BD%D0%B5%D1%81%D1%81%D0%B0-%D0%98%D0%B2%D0%B0%D0%BD%D0%BE%D0%B2%D0%BD%D0%B0/205915576187961";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void clickVkGroup(View v)
    {
        String url = "http://vk.com/club38231720";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void clickInfo(View v)
    {
        startActivity(new Intent(this, Info.class));
        /*String url = "http://uchilka.pro/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);*/
    }




    public void clickExam1(View v)
    {

        if(!checkAvailableExamAndStarBlink(1))
            return;

       /* CheckInternet connInternet=new CheckInternet(AcMainTeachScreen.this);
        if(!connInternet.isOnline())
        {
            connInternet.dialogAppear();
        }
        else {*/
        Intent intent = new Intent(this,AcStartExam.class);
        intent.putExtra(AcStartExam.EXAM_QUESTION_COUNT,13);
        intent.putExtra(AcStartExam.EXAM_QUESTION_LEVEL,0);
        startActivity(intent);
        //}
    }
    public void clickExam2(View v)
    {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if(!checkAvailableExamAndStarBlink(2)|!preferences.getBoolean(ExamManager.IS_FINISH_EXAM_1,false))
            return;

        /*CheckInternet connInternet=new CheckInternet(AcMainTeachScreen.this);
        if(!connInternet.isOnline())
        {
            connInternet.dialogAppear();
        }
        else {*/
        Intent intent = new Intent(this,AcStartExam.class);
        intent.putExtra(AcStartExam.EXAM_QUESTION_COUNT,21);
        intent.putExtra(AcStartExam.EXAM_QUESTION_LEVEL,1);
        startActivity(intent);
        //}
    }
    public void clickExam3(View v)
    {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if(!checkAvailableExamAndStarBlink(3)|!preferences.getBoolean(ExamManager.IS_FINISH_EXAM_2,false))
            return;

        /*CheckInternet connInternet=new CheckInternet(AcMainTeachScreen.this);
        if(!connInternet.isOnline())
        {
            connInternet.dialogAppear();
        }
        else {*/
        Intent intent = new Intent(this,AcStartExam.class);
        intent.putExtra(AcStartExam.EXAM_QUESTION_COUNT,34);
        intent.putExtra(AcStartExam.EXAM_QUESTION_LEVEL,2);
        startActivity(intent);
        //}
    }

    private void disableExamStarBlink(int count)
    {
         for(CnTeachTableItem cn:cnTeachTableItems)
         {
             if(cn.getCountStar()<count)
                 cn.startBlink(count);
         }
    }

    private boolean checkAvailableExamAndStarBlink(int count)
    {
        boolean k=true;
        for(CnTeachTableItem cn:cnTeachTableItems)
        {
            if(cn.getCountStar()<count)
            {
                cn.startBlink(count);
                k=false;
            }
        }
        return k;
    }
    private boolean checkAvailableExamAndStar(int count)
    {
        boolean k=true;
        for(CnTeachTableItem cn:cnTeachTableItems)
        {
            if(cn.getCountStar()<count)
            {
                k=false;
            }
        }
        return k;
    }

     public void clickSetting(View v)
     {
         startActivity(new Intent(this,AcSetting.class));
     }
    
    private Drawable getLessonImage(int i)
    {
        switch (i)
        {
            case 1:
                return getResources().getDrawable(R.drawable.button_ac_main_teach_screen_main_test_1);
            case 2:
                return getResources().getDrawable(R.drawable.button_ac_main_teach_screen_main_test_2);
            case 3:
                return getResources().getDrawable(R.drawable.button_ac_main_teach_screen_main_test_3);
            case 4:
                return getResources().getDrawable(R.drawable.button_ac_main_teach_screen_main_test_4);
            case 5:
                return getResources().getDrawable(R.drawable.button_ac_main_teach_screen_main_test_5);
            case 6:
                return getResources().getDrawable(R.drawable.button_ac_main_teach_screen_main_test_6);
            case 7:
                return  getResources().getDrawable(R.drawable.button_ac_main_teach_screen_main_test_7);
            case 8:
                return  getResources().getDrawable(R.drawable.button_ac_main_teach_screen_main_test_8);
            case 9:
                return  getResources().getDrawable(R.drawable.button_ac_main_teach_screen_main_test_9);
            case 10:
                return  getResources().getDrawable(R.drawable.button_ac_main_teach_screen_main_test_10);
            default:
                return  getResources().getDrawable(R.drawable.im_ac_main_teach_screen_main_1);
        }
    }
    public void clickZubrilka(View v)
    {
        Intent intent = new Intent(this, AcZubrilka.class);
        //intent.setComponent(new ComponentName("com.zubrilka", "com.zubrilka.AcZubrilka"));

        startActivity(intent);
    }
}