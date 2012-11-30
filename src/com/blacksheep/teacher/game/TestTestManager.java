package com.blacksheep.teacher.game;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.blacksheep.teacher.game.animated_view.*;
import com.blacksheep.teacher.game.examtest.ManagerActions;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;
import com.blacksheep.teacher.model.dataEntity.DataContentForLecture;
import com.blacksheep.teacher.model.dataEntity.DataLecture;
import com.blacksheep.teacher.model.database.DBManager;
import com.blacksheep.teacher.nuanceApp.RecognizeTest;
import com.blacksheep.teacher.nuanceApp.ResponceRecognize;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Defafault
 * Date: 07.07.12
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public class TestTestManager {

//    private Activity activity;
    private TextView textViewFrameCount;
    private DataLecture dataLecture;
    private LinearLayout ll_board;
    public SurfaceViewTeach.IFrameListener iFrameListener;
    private int lectureNumber;
    private Activity context_ac;
    ArrayList<TextView> contentList;

    private Integer responseTESTTEST =  null;

    public AnimationHead animationHead;

    private OnCompletionListener onCompletionListener;

    LinearLayout curLinearLayout;
    int max_component = 5;
    int countExaple = 0;

    int width;
    int height;

    public TestTestManager(Activity context) {
        context_ac = context;
        contentList = new ArrayList<TextView>();
        Display d=((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        width=d.getWidth();
        height=d.getHeight();
        Log.i(getClass().getName(),"display width " + width);
        Log.i(getClass().getName(),"display height "+height);
    }

    private int frameCount;



    private void clearBoardData(DataContentForLecture dataContentForLecture) {
        for (TextView tv : contentList) {
            if (tv.getText().equals(dataContentForLecture.getContent())) {
                tv.setVisibility(View.GONE);
                return;
            }
        }
    }

    private void clearBoard() {

        ll_board.removeAllViews();

        this.ll_board.addView(curLinearLayout);
        countExaple = 0;


    }





    int sleepPause = 1000/12;


    public void startLecture() {
        //dataLecture = generateLecture();
//        LinkedList<String> strings = new LinkedList<String>();
//        strings.add(dataLecture.getAudioPath());

//        LinkedList<DataAnimation>  dataAnimations1 = new LinkedList<DataAnimation>();
//        dataAnimations1.add(new DataAnimation("18",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
//        LinkedList<String> audio1 = new LinkedList<String>();
//        audio1.add(DataManager.getNameTest("1p_azamatik"));
//
//        LinkedList<DataAnimation>  dataAnimations2 = new LinkedList<DataAnimation>();
//        dataAnimations2.add(new DataAnimation("37",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
//        LinkedList<String> audio2 = new LinkedList<String>();
//        audio2.add(DataManager.getPhrases("f-4-3"));
//
//
//
//        LinkedList<DataAnimation>  dataAnimations3 = new LinkedList<DataAnimation>();
//        dataAnimations3.add(new DataAnimation("18",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
//        LinkedList<String> audio3 = new LinkedList<String>();
//        audio3.add(DataManager.getOperation("8x10"));
//
//        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();
//
//        animationSimples.add(new AnimationSimple(dataAnimations1,audio1,context_ac));
//        animationSimples.add(new AnimationSimpleBlaBla(audio2,context_ac));
//        animationSimples.add(new AnimationSimpleBlaBla(audio3,context_ac));
//
//        //animationHead = new AnimationHead(dataLecture.getListAnimations(), strings, context_ac, 0);
//        animationHead = new AnimationHead(animationSimples,context_ac);
//        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
//            @Override
//            public void onCompletion(AnimationHead animationHead) {
//
//                Toast.makeText(context_ac,"end",1000).show();
//            }
//        });
//        animationHead.start();
//        ManagerActions managerActions = new ManagerActions(context_ac);
//
//        managerActions.doWaitShort(new ManagerActions.IActionComplete() {
//            @Override
//            public void onComplete() {
//                //To change body of implemented methods use File | Settings | File Templates.
//            }
//        });

    }

    public void responseOk()
    {

        LinkedList<DataAnimation>  dataAnimations1 = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation1 = new DataAnimation("13",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200);
        dataAnimations1.add(dataAnimation1);
        LinkedList<String> audio1 = new LinkedList<String>();
        audio1.add(DataManager.getPhrases("f-6-PP-1"));


        LinkedList<String> audio2 = new LinkedList<String>();
        audio2.add(DataManager.getNameTest("1p_azamatik"));

        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();


        animationSimples.add(new AnimationSimple(dataAnimations1,audio1,context_ac));
        animationSimples.add(new AnimationSimpleBlaBla(audio2,context_ac));

        animationHead = new AnimationHead(animationSimples,context_ac);
        ((AnimationHead) animationHead).setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {

                Toast.makeText(context_ac,"end",1000).show();


            }
        });
       animationHead.start();
    }

    public void responseOk22()
    {
        LinkedList<DataAnimation>  dataAnimations1 = new LinkedList<DataAnimation>();
        dataAnimations1.add(new DataAnimation("18",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
        LinkedList<String> audio1 = new LinkedList<String>();
        audio1.add(DataManager.getNameTest("1p_azamatik"));

        LinkedList<DataAnimation>  dataAnimations2 = new LinkedList<DataAnimation>();
        dataAnimations2.add(new DataAnimation("16",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
        LinkedList<String> audio2 = new LinkedList<String>();
        audio2.add(DataManager.getPhrases("f-6-PP-1"));

        LinkedList<DataAnimation>  dataAnimations3 = new LinkedList<DataAnimation>();
        dataAnimations3.add(new DataAnimation("18",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
        LinkedList<String> audio3 = new LinkedList<String>();
        audio3.add(DataManager.getOperation("8x10"));

        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();

        animationSimples.add(new AnimationSimpleBlaBla(audio1,context_ac));
        animationSimples.add(new AnimationSimple(dataAnimations2,audio2,context_ac));
        animationSimples.add(new AnimationSimpleBlaBla(audio3,context_ac));

        //animationHead = new AnimationHead(dataLecture.getListAnimations(), strings, context_ac, 0);
        animationHead = new AnimationHead(animationSimples,context_ac);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {

                Toast.makeText(context_ac,"end",1000).show();
            }
        });
        animationHead.start();
    }

    public void responseOk2()
    {

    }

    AnimationHeadForWait animationHeadForWait;

    public void setResponse()
    {
        ResponceRecognize responceRecognize = new ResponceRecognize();
        responceRecognize.setKey(2);
        RecognizeTest.setResponceRecognize(responceRecognize);
        if(animationHeadForWait!=null&&animationHeadForWait.isWork())
            return;
        doResult();
    }


    public void doQuestion()
    {
        RecognizeTest.setResponceRecognize(null);
        LinkedList<DataAnimation>  dataAnimations1 = new LinkedList<DataAnimation>();
        dataAnimations1.add(new DataAnimation("18",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
        LinkedList<String> audio1 = new LinkedList<String>();
        audio1.add(DataManager.getNameTest("1p_azamatik"));

        LinkedList<DataAnimation>  dataAnimations2 = new LinkedList<DataAnimation>();
        dataAnimations2.add(new DataAnimation("37",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
        LinkedList<String> audio2 = new LinkedList<String>();
        audio2.add(DataManager.getPhrases("f-4-3"));

        LinkedList<DataAnimation>  dataAnimations3 = new LinkedList<DataAnimation>();
        dataAnimations3.add(new DataAnimation("18",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
        LinkedList<String> audio3 = new LinkedList<String>();
        audio3.add(DataManager.getOperation("8x10"));

        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();

        animationSimples.add(new AnimationSimpleBlaBla(audio1,context_ac));
        animationSimples.add(new AnimationSimple(dataAnimations2,audio2,context_ac));
        animationSimples.add(new AnimationSimpleBlaBla(audio3,context_ac));

        //animationHead = new AnimationHead(dataLecture.getListAnimations(), strings, context_ac, 0);
        animationHead = new AnimationHead(animationSimples,context_ac);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                            if(RecognizeTest.getResponceRecognize()==null)
                                doWaitAnim();
                        }
                    }).start();

            }
        });
        animationHead.start();
    }

    public void doWaitAnim()
    {
        LinkedList<DataAnimation>  dataAnimations1 = new LinkedList<DataAnimation>();
        dataAnimations1.add(new DataAnimation("18",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
        LinkedList<String> audio1 = new LinkedList<String>();
        audio1.add(DataManager.getNameTest("1p_azamatik"));

        LinkedList<DataAnimation>  dataAnimations2 = new LinkedList<DataAnimation>();
        dataAnimations2.add(new DataAnimation("16",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
        LinkedList<String> audio2 = new LinkedList<String>();
        audio2.add(DataManager.getPhrases("f-6-PP-1"));

        LinkedList<DataAnimation>  dataAnimations3 = new LinkedList<DataAnimation>();
        dataAnimations3.add(new DataAnimation("18",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200));
        LinkedList<String> audio3 = new LinkedList<String>();
        audio3.add(DataManager.getOperation("8x10"));

        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();


        AnimationSimpleBlaBla animationSimpleBlaBla = new AnimationSimpleBlaBla(audio3,context_ac) ;
        animationSimpleBlaBla.setForWaitStart(true);
        animationSimples.add(new AnimationSimpleBlaBla(audio1,context_ac));

        for(int i=0;i<100;i++)
            animationSimples.add(new AnimationSimple(dataAnimations2,audio2,context_ac));

        animationSimples.add(animationSimpleBlaBla);

        //animationHead = new AnimationHead(dataLecture.getListAnimations(), strings, context_ac, 0);
        animationHeadForWait = new AnimationHeadForWait(animationSimples,context_ac);
        animationHeadForWait.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {

                Toast.makeText(context_ac,"Wait end",1000).show();
               //doResult();
            }
        });
        animationHeadForWait.start();
    }

    public void doResult()
    {
         responseOk22();
    }

    private void generateAnimationOfflineInternet(final CheckInternet checkInternet) {
        //Todo привязать анимацию и звук при отсутствии интернет соединения

        String phrase = "f-757";

        LinkedList<DataAnimation> animlist = new LinkedList<DataAnimation>();
        DataAnimation daj = DBManager.getInstance().getAnimationByAudioFileName(phrase + ".mp3");
        if (daj != null)
            animlist.add(daj);
        LinkedList<String> soundlist = new LinkedList<String>();
        soundlist.add(DataManager.getPhrases(phrase));
        animationHead = new AnimationHead(animlist, soundlist, context_ac, 0);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                animationHead.destroy();
                animationHead = null;
                checkInternet.dialogAppear();
            }
        });
        animationHead.start();
    }

    private DataLecture generateLecture() {
        DataAnimation daj = DBManager.getInstance().getAnimationByAudioFileName("l" + lectureNumber + ".mp3");

        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();

        if (daj != null)
        {
            if(lectureNumber==1)
                daj.setEq_start(-12);
            dataAnimations.add(daj);
        }


        LinkedList<DataContentForLecture> dataContentForLectures = new LinkedList<DataContentForLecture>(DBManager.getInstance().getContentForLecture(lectureNumber==0?11:lectureNumber));

        DataLecture dataLecture = new DataLecture();

        dataLecture.setFrameCount(1000);
        dataLecture.setName("first1");
        dataLecture.setAudioPath(DataManager.getLecturFilePath(lectureNumber));
        dataLecture.setListAnimations(dataAnimations);
        dataLecture.setListContentForLectures(dataContentForLectures);
        return dataLecture;
    }





    public void setLectureNumber(int lectureNumber) {
        this.lectureNumber = lectureNumber;
    }

    public void setTextViewFrameCount(TextView textViewFrameCount) {
        this.textViewFrameCount = textViewFrameCount;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.onCompletionListener = onCompletionListener;
    }


    public interface OnCompletionListener {
        public void onCompletion();
    }

}
