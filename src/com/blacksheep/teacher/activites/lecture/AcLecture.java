package com.blacksheep.teacher.activites.lecture;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import com.blacksheep.teacher.FasActiv;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.activites.adminka.AcChooseAnimation;
import com.blacksheep.teacher.activites.loadApp.AcLoadApp;
import com.blacksheep.teacher.activites.loadApp.AcLoadResources;
import com.blacksheep.teacher.activites.mainScreen.AcMainTeachScreen;
import com.blacksheep.teacher.game.CheckInternet;
import com.blacksheep.teacher.game.TestTestActivity;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.LogTeach;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;
import com.blacksheep.teacher.model.dataEntity.DataAnimationToAudio;
import com.blacksheep.teacher.model.dataEntity.DataLecture;
import com.blacksheep.teacher.model.dataEntity.DataTest;
import com.blacksheep.teacher.model.database.DBManager;
import com.blacksheep.teacher.synchronization.DownloadService;
import com.blacksheep.teacher.synchronization.NotificationHelper;
import com.blacksheep.teacher.synchronization.SuperLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testsocial.AcGame;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/6/12
 * Time: 2:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class AcLecture extends Activity{


    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
        
     //   if(MyApplication.startedApp)
     //       finish();
        setContentView(R.layout.ac_lecture);



        MyApplication.startedApp=true;

        String ss =  Environment.getExternalStorageDirectory().getAbsolutePath();
     //   ListView listView = (ListView) findViewById(R.id.list_lectures);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int v = metrics.heightPixels;
        int v1 =metrics.widthPixels;

        LinkedList<DataLecture> lectures = new LinkedList<DataLecture>(DBManager.getInstance().getLectures());
        AdtLecture lectureAdapter = new AdtLecture(this,lectures,new AdtLecture.IClickLecture() {
            @Override
            public void click(int lectureID) {
                lectureID--;
                Intent intent = new Intent(AcLecture.this, AcStartLecture.class);
                intent.putExtra(AcStartLecture.LECTURE_ID,lectureID);
                startActivity(intent);
            }
        });
//        listView.setAdapter(lectureAdapter);

        //DataManager.initTEST();

        buildProgress();
        String[] list = new File("/data/data/com.blacksheep.teacher/files/TeachData/animations/10/").list();

       DBManager.getInstance().getDataAnimationToAudio();
       // testGenerateTestSessions();
       // getStartsForLecture();
    }

    public void clickAdminka(View v)
    {
        //testGenerateTestSessions();
        SuperLoader.getHwForLoad();
        startActivity(new Intent(this, AcChooseAnimation.class));
        //startActivity(new Intent(this, AcAnimation.class));
    }

    private void buildProgress()
    {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("A message");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    NotificationHelper mNotificationHelper = new NotificationHelper(this);
    DownloadService downloadService = new DownloadService();
    public void clickStartNotification(View v) throws IOException {
//      boolean  c =  downloadService.mustLoadRes();
//        Log.i(this.getClass().getName(),"load "+c);
//       // mNotificationHelper.createNotification();
        generateExam1();
    }

    private void generateExam1() {
        testGenerateTestSessions(0.8f);
    }

    public void clickCloseNotification(View v)
    {
        generateExam2();
//        downloadService.addFlagLoadResources();
//        Log.i(this.getClass().getName(),"put true load");
       // mNotificationHelper.completed();
    }

    private void generateExam2() {
        testGenerateTestSessions(0.9f);

    }

    public void clickStartNotificationCompleated(View v)
    {
       // mNotificationHelper.createNotificationCompleate();
       // testGenerateTestSessions();
        genereateExam3();
       // startActivity(new Intent(this, AcGame.class));
    }

    private void genereateExam3() {

        testGenerateTestSessions(1.0f);
    }

    private void sendPhoto(String path,String type)
    {
        Intent picMessageIntent = new Intent(android.content.Intent.ACTION_SEND);
        picMessageIntent.setType("image/"+type);
        File downloadedPic =  new File(path);
        picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(downloadedPic));
        startActivity(Intent.createChooser(picMessageIntent, "Отправьте вашу открытку используя:"));
    }

    private void testGenerateTestSessions(float value)
    {
        int testSessionID= (int) LogTeach.addTest(new DataTest(1,1,1,"",""), -1);
      //  LogTeach.updateTest(testSessionID,0.8f);
        testSessionID= (int) LogTeach.addTest(new DataTest(1,1,1,"",""), -1);
        LogTeach.updateTest(testSessionID,value);
        testSessionID= (int) LogTeach.addTest(new DataTest(2,1,1,"",""), -1);
       // LogTeach.updateTest(testSessionID,0.8f);
        testSessionID= (int) LogTeach.addTest(new DataTest(2,1,1,"",""), -1);
        LogTeach.updateTest(testSessionID,value);
        testSessionID= (int) LogTeach.addTest(new DataTest(3,1,1,"",""), -1);
       // LogTeach.updateTest(testSessionID,0.8f);
        testSessionID= (int) LogTeach.addTest(new DataTest(3,1,1,"",""), -1);
        LogTeach.updateTest(testSessionID,value);
        testSessionID= (int) LogTeach.addTest(new DataTest(4,1,1,"",""), -1);
       // LogTeach.updateTest(testSessionID,0.8f);
        testSessionID= (int) LogTeach.addTest(new DataTest(4,1,1,"",""), -1);
        LogTeach.updateTest(testSessionID,value);
        testSessionID= (int) LogTeach.addTest(new DataTest(5,1,1,"",""), -1);
       // LogTeach.updateTest(testSessionID,0.8f);
        testSessionID= (int) LogTeach.addTest(new DataTest(5,1,1,"",""), -1);
        LogTeach.updateTest(testSessionID,value);
        testSessionID= (int) LogTeach.addTest(new DataTest(6,1,1,"",""), -1);
       // LogTeach.updateTest(testSessionID,0.8f);
        testSessionID= (int) LogTeach.addTest(new DataTest(6,1,1,"",""), -1);
        LogTeach.updateTest(testSessionID,value);
        testSessionID= (int) LogTeach.addTest(new DataTest(7,1,1,"",""), -1);
       // LogTeach.updateTest(testSessionID,0.8f);
        testSessionID= (int) LogTeach.addTest(new DataTest(7,1,1,"",""), -1);
        LogTeach.updateTest(testSessionID,value);
        testSessionID= (int) LogTeach.addTest(new DataTest(8,1,1,"",""), -1);
       // LogTeach.updateTest(testSessionID,0.8f);
        testSessionID= (int) LogTeach.addTest(new DataTest(8,1,1,"",""), -1);
        LogTeach.updateTest(testSessionID,value);
        testSessionID= (int) LogTeach.addTest(new DataTest(9,1,1,"",""), -1);
       // LogTeach.updateTest(testSessionID,0.8f);
        testSessionID= (int) LogTeach.addTest(new DataTest(9,1,1,"",""), -1);
        LogTeach.updateTest(testSessionID,value);
        testSessionID= (int) LogTeach.addTest(new DataTest(10,1,1,"",""), -1);
       // LogTeach.updateTest(testSessionID,0.8f);
        testSessionID= (int) LogTeach.addTest(new DataTest(10,1,1,"",""), -1);
        LogTeach.updateTest(testSessionID,value);

    }


    public void clickTestAnim(View v)
    {
        startActivity(new Intent(this, TestTestActivity.class));
    }

    public void clickGame(View v)
    {
        String animData = "[{\"animation_type\":0,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":576,\"id\":22,\"name\":\"1\\u043e\\u0435 \\u043f\\u0440\\u0438\\u0432\\u0435\\u0442\\u0441\\u0442\\u0432\\u0438\\u0435 (0_01_5) f-1\",\"option_code\":0,\"start_frame\":0},{\"animation_type\":2,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":326,\"id\":20,\"name\":\"(33_0_04_1024_)\",\"option_code\":1,\"start_frame\":0},{\"animation_type\":2,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":418,\"id\":21,\"name\":\"(34_1024_)\",\"option_code\":2,\"start_frame\":0},{\"animation_type\":3,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":31,\"id\":24,\"name\":\"quest\",\"option_code\":0,\"start_frame\":0},{\"animation_type\":4,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":13,\"id\":18,\"name\":\"(Bla_bla_1)\",\"option_code\":null,\"start_frame\":0},{\"animation_type\":6,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":26,\"id\":16,\"name\":\"\\u043e\\u0442\\u0432\\u0435\\u0442 - \\u0432\\u0435\\u0440\\u043d\\u043e (14_15) f-6-S-5\",\"option_code\":0,\"start_frame\":0},{\"animation_type\":6,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":15,\"id\":15,\"name\":\"\\u043e\\u0442\\u0432\\u0435\\u0442 - \\u0432\\u0435\\u0440\\u043d\\u043e (14_18) f-6-S-8\",\"option_code\":0,\"start_frame\":0},{\"animation_type\":6,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":48,\"id\":17,\"name\":\"\\u043e\\u0442\\u0432\\u0435\\u0442 - \\u0432\\u0435\\u0440\\u043d\\u043e (14_8) f-6-PP-3\",\"option_code\":0,\"start_frame\":0},{\"animation_type\":6,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":19,\"id\":13,\"name\":\"\\u043e\\u0442\\u0432\\u0435\\u0442 - \\u0432\\u0435\\u0440\\u043d\\u043e (14_5_01_02) f-6-PP-1\",\"option_code\":1,\"start_frame\":0},{\"animation_type\":6,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":78,\"id\":23,\"name\":\"\\u043e\\u0442\\u0432\\u0435\\u0442 - \\u043d\\u0435\\u0432\\u0435\\u0440\\u043d\\u043e (15_10) f-7-N-4\",\"option_code\":1,\"start_frame\":0},{\"animation_type\":6,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":33,\"id\":12,\"name\":\"\\u043e\\u0442\\u0432\\u0435\\u0442 - \\u043d\\u0435\\u043f\\u0440\\u0430\\u0432  (14_20)   f-7-S-1\",\"option_code\":1,\"start_frame\":0},{\"animation_type\":6,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":36,\"id\":11,\"name\":\"\\u043e\\u0442\\u0432\\u0435\\u0442 - \\u043d\\u0435\\u043f\\u0440\\u0430\\u0432  (15_15_02) f-7-N-7\",\"option_code\":1,\"start_frame\":0},{\"animation_type\":6,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":42,\"id\":8,\"name\":\"\\u0427\\u0442\\u043e \\u0442\\u044b \\u043f\\u0438\\u0449\\u0438\\u0448\\u044c ? (13_5_01) f-5-NN-2\",\"option_code\":2,\"start_frame\":0},{\"animation_type\":6,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":36,\"id\":10,\"name\":\"\\u043e\\u0442\\u0432\\u0435\\u0442 - \\u043d\\u0435\\u0442\\u043e\\u0447\\u043d (16_2) f-21-N-1\",\"option_code\":2,\"start_frame\":0},{\"animation_type\":6,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":47,\"id\":9,\"name\":\"\\u043e\\u0442\\u0432\\u0435\\u0447\\u0430\\u0439 \\u0447\\u0435\\u0442\\u0447\\u0435 \\u0447\\u0438\\u0441\\u043b\\u043e(11_1) f-3-1\",\"option_code\":2,\"start_frame\":0},{\"animation_type\":8,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":131,\"id\":3,\"name\":\"\\u0420\\u0435\\u0437 0\\u0438\\u0437 10 (19_1) f-8-A0-1\",\"option_code\":0,\"start_frame\":0},{\"animation_type\":8,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":111,\"id\":1,\"name\":\"(19_5) f-8-A1-2\",\"option_code\":1,\"start_frame\":0},{\"animation_type\":8,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":121,\"id\":2,\"name\":\"(19_7)    f-8-A2-2\",\"option_code\":2,\"start_frame\":0},{\"animation_type\":8,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":101,\"id\":19,\"name\":\"\\u0440\\u0435\\u0437 6 \\u0438\\u0437 10 (19_14_01) f-8-A6-1\",\"option_code\":6,\"start_frame\":0},{\"animation_type\":8,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":106,\"id\":7,\"name\":\"\\u0441\\u0434\\u0430\\u043b \\u0443\\u0440\\u043e\\u043a (20_1) f-144-1\",\"option_code\":10,\"start_frame\":0},{\"animation_type\":9,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":48,\"id\":6,\"name\":\"\\u041f\\u0440\\u0438\\u0433\\u043b\\u0430\\u0448\\u0435\\u043d\\u0438\\u0435 \\u043a \\u044d\\u043a\\u0437\\u0430\\u043c (23) f-11\",\"option_code\":0,\"start_frame\":0},{\"animation_type\":10,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":166,\"id\":25,\"name\":\"\\u0432\\u0441\\u0442\\u0443\\u043f\\u043b\\u0435\\u043d\\u0438\\u0435 3\\u0439 \\u044d\\u043a\\u0437 (24_4) f-12-3\",\"option_code\":null,\"start_frame\":0},{\"animation_type\":10,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":126,\"id\":5,\"name\":\"(24_5) f-13\",\"option_code\":0,\"start_frame\":0},{\"animation_type\":11,\"eq_end\":null,\"eq_start\":null,\"fps\":12,\"frame_count\":106,\"id\":4,\"name\":\"(27_2) f-15-2\",\"option_code\":0,\"start_frame\":0}]";

        Gson gson = new Gson();
        Collection<DataAnimation> data = gson.fromJson(animData, new TypeToken<Collection<DataAnimation>>() {
        }.getType());
        DBManager.getInstance().fillAnimations(data);
        DataManager.mountAnimationFolders(data);


        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(1,"f-8-A1-2.mp3",1));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(2,"f-8-A2-2.mp3",2));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(3,"f-8-A0-1.mp3",3));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(4,"f-15-A50-a.mp3",4));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(5,"f-13.mp3",5));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(6,"f-11.mp3",6));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(7,"f-144-1.mp3",7));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(8,"f-5-NN-2.mp3",8));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(9,"f-3-1.mp3",9));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(10,"f-21-S-1.mp3",10));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(11,"f-7-N-7.mp3",11));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(12,"f-7-S-1.mp3",12));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(13,"f-6-PP-1.mp3",13));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(14,"",14));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(15,"f-6-S-8.mp3",15));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(16,"f-6-S-5.mp3",16));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(17,"f-6-PP-3.mp3",17));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(18,"",18));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(19,"f-8-A6-1.mp3",19));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(20,"l1.mp3",20));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(21,"l2.mp3",21));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(22,"f-1.mp3",22));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(23,"f-7-N-4.mp3",23));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(24,"",24));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(25,"f-12-3.mp3",25));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(26,"l3.mp3",26));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(27,"l7.mp3",27));
        DBManager.getInstance().fillAnimationToAudioOne(new DataAnimationToAudio(28,"",28));


    }
    public void clickGame1(View v)
    {
        Random rnd = new Random();
       List<Integer> lst = DBManager.getInstance().getTips(rnd.nextInt(11)+ "x"+rnd.nextInt(11));
        int b=0;

    }

    public void clickLoadResDebug(View v)
    {
        DownloadService downloadService = new DownloadService();
        try {
            downloadService.downloadFile();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void clickLoadRes(View v)
    {
        startActivity(new Intent(this, AcLoadResources.class));
    }


    @Override
    protected void onRestart() {
        super.onRestart();    //To change body of overridden methods use File | Settings | File Templates.
        Log.i(getClass().getName(),"onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
        Log.i(getClass().getName(),"onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();    //To change body of overridden methods use File | Settings | File Templates.
        Log.i(getClass().getName(),"onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        Log.i(getClass().getName(),"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        Log.i(getClass().getName(),"onResume");
    }

    public void clickAcTest(View v)
    {
        Intent intent = new Intent(this, FasActiv.class);
        startActivity(intent);
    }
    
    public void clickAcMain(View v)
    {
        Intent intent = new Intent(this, AcMainTeachScreen.class);
        startActivity(intent);
    }

    public void clickAcLoadApp(View v)
    {
        Intent intent = new Intent(this, AcLoadApp.class);
        startActivity(intent);
    }


}
