package com.blacksheep.teacher.game.lecture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.game.animated_view.SurfaceViewTeach;
import com.blacksheep.teacher.game.examtest.ManagerActions;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.dataEntity.DataContentForLecture;
import com.blacksheep.teacher.model.dataEntity.DataLecture;
import com.blacksheep.teacher.model.database.DBManager;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 2/25/12
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class LectureManager {


    private Activity activity;
    private TextView textViewFrameCount;
    private DataLecture dataLecture;
    private LinearLayout ll_board;
    public SurfaceViewTeach.IFrameListener iFrameListener;
    private int lectureNumber;
    private Activity context_ac;
    ArrayList<TextView> contentList;
    boolean isPlay;
    Thread thread;
    int countFrame;

    private OnCompletionListener onCompletionListener;

    LinearLayout curLinearLayout;
    int max_component = 5;
    int countExaple = 0;

    int width;
    int height;

    public LectureManager(Activity context) {
        context_ac = context;
        implementListener();
        contentList = new ArrayList<TextView>();
        Display d=((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        width=d.getWidth();
        height=d.getHeight();
        Log.i(getClass().getName(), "display width " + width);
        Log.i(getClass().getName(),"display height "+height);
    }

    private int frameCount;

    private void implementListener() {
        iFrameListener = new SurfaceViewTeach.IFrameListener() {
            @Override
            public void frameNumber(final int frame) {
                frameCount = frame;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (textViewFrameCount != null)
                            textViewFrameCount.setText(String.valueOf(frame));

                    }
                });

                LinkedList<DataContentForLecture> list = dataLecture.getListContentForLectures();
                for (final DataContentForLecture dataContentForLecture : list) {
                    if (dataContentForLecture.getEnd_frame() == frame) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                clearBoardData(dataContentForLecture);
                            }
                        });

                       // break;
                    }
                }
                for (final DataContentForLecture dataContentForLecture : list) {

                    if (dataContentForLecture.getStart_frame() == frame) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                addContentToBoard(dataContentForLecture.getContent());
                            }
                        });
                    }


                }
            }
        };
    }

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
        curLinearLayout = layoutBuilder();
        this.ll_board.addView(curLinearLayout);
        countExaple = 0;
    }

    private void clearBoardOne() {

        ll_board.removeViewAt(0);

    }

    private void addContentToBoard(String text) {

        if(lectureNumber==7||lectureNumber==8||lectureNumber==9)
            if(replaceText(text,lectureNumber))
                return;

        TextView textView = new TextView(activity);
        if(lectureNumber!=11)
        {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,MyApplication.instance.getTextSize());
            textView.setTypeface(Typeface.createFromAsset(context_ac.getAssets(), "fonts/segoepr.ttf"));
        }
        else
        {
            max_component=100;
            if(MyApplication.instance.getTypeDisplay()==MyApplication.EXTA_LARGE_DISPLAY)
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,130);
            else if(MyApplication.instance.getTypeDisplay()==MyApplication.LARGE_DISPLAY)
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,55);
            else
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40);
            textView.setTypeface(Typeface.createFromAsset(context_ac.getAssets(), "fonts/BRADHITC.ttf"));
        }

        //textView.setText(Html.fromHtml(text.replace("х","x")));
        textView.setText(text.replace("х","x"));
        //textView.setText(Html.fromHtml("<FONT SIZE=50>`</FONT> x 2"));
        textView.setPadding(0, 0, 45, 0);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        if (calcTvIsVisible(curLinearLayout) >= max_component) {
            if(ll_board.getChildCount()==2)
                clearBoardOne();
            curLinearLayout = layoutBuilder();
            ll_board.addView(curLinearLayout);
            countExaple = 0;
        }
        contentList.add(textView);
        curLinearLayout.addView(textView);
        countExaple++;

    }

    private boolean replaceText(String text,int num) {

        String [] arr = text.split("=");


        if(arr[0].length()>=7)
            return false;

        if(Integer.parseInt(text.substring(4,5))>=num)
            return false;

        for(int i=0;i<ll_board.getChildCount();i++)
        {
            LinearLayout ll = (LinearLayout)ll_board.getChildAt(i);
            for(int j=0;j<ll.getChildCount();j++)
            {
                TextView textView = (TextView) ll.getChildAt(j);
                String textTv = textView.getText().toString();

                if(Integer.parseInt(textTv.substring(4,5))==Integer.parseInt(text.substring(0,1)))
                {
                    textView.setText(text);
                    return true;
                }
            }
        }
        return false;

    }

    private int calcTvIsVisible(LinearLayout curLinearLayout)
    {
        int count=0;
          for(int i=0;i<curLinearLayout.getChildCount();i++)
          {
              TextView textView = (TextView) curLinearLayout.getChildAt(i);
              if(textView.getVisibility()==View.VISIBLE)
                  count++;

          }
        return count;
    }


    private LinearLayout layoutBuilder() {
        LinearLayout linearLayout1 = new LinearLayout(activity);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 4;
        layoutParams.rightMargin = 4;
        linearLayout1.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout1.setLayoutParams(layoutParams);
        return linearLayout1;
    }

    int sleepPause = 1000/12;


    public void startLecture() {
        isPlay=true;
        countFrame=1;
         dataLecture = generateLecture();
        final ManagerActions managerActions =new ManagerActions(context_ac);
        managerActions.doLecture(lectureNumber,new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {

               /* CheckInternet checkForOnline = new CheckInternet(context_ac);
                if (!checkForOnline.isOnline())
                    managerActions.generateAnimationOfflineInternet(checkForOnline,new ManagerActions.IActionComplete() {
                        @Override
                        public void onComplete() {

                        }
                    });*/

                if (onCompletionListener != null)
                   onCompletionListener.onCompletion();
            }
        });
        startThread();
    }
     private void startThread(){
         thread = new Thread(new Runnable() {
             int frameNumber = countFrame;

             @Override
             public void run() {
                 Thread.currentThread().setName("thread frame count");
                 while (true) {

                     if(!isPlay) break;
                  //   Log.i(this.getClass().getName(),"work thread");
                     countFrame=frameNumber;
                         iFrameListener.frameNumber(frameNumber);
                     frameNumber++;
                     try {
                         Thread.sleep(sleepPause);
                     } catch (InterruptedException e) {
                         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                     }

                 }
             }
         });
         thread.start();
     }




    private DataLecture generateLecture() {


//        //  DataAnimation daj = DBManager.getInstance().getAnimationByTypeAndCode(2,lectureNumber);
//        DataAnimation daj = DBManager.getInstance().getAnimationByAudioFileName("l" + lectureNumber + ".mp3");
//
//        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
//
//        if (daj != null)
//        {
//            if(lectureNumber==1)
//                daj.setEq_start(-12);
//            dataAnimations.add(daj);
//        }
        //dataAnimations.add(new DataAnimation("packed", 1, 85));
        // dataAnimations.add(new DataAnimation("test", 1, 85));
        // dataAnimations.add(new DataAnimation("test", 1, 85));
        // dataAnimations.add(new DataAnimation("test", 1, 85));
        //dataAnimations.add(new DataAnimation("test", 1, 85));
//        dataAnimations.add(new DataAnimation("test", 1, 170));


        LinkedList<DataContentForLecture> dataContentForLectures = new LinkedList<DataContentForLecture>(DBManager.getInstance().getContentForLecture(lectureNumber==0?11:lectureNumber));

        DataLecture dataLecture = new DataLecture();

        dataLecture.setFrameCount(1000);
        dataLecture.setName("first1");
        dataLecture.setAudioPath(DataManager.getLecturFilePath(lectureNumber));
        dataLecture.setListContentForLectures(dataContentForLectures);
        return dataLecture;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setLl_board(LinearLayout ll_board) {
        this.ll_board = ll_board;
        curLinearLayout = layoutBuilder();
        this.ll_board.addView(curLinearLayout);
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
    public void stopFrameCount()
    {
          isPlay=false;
    }
    public void resumeFrameCount()
    {
          isPlay=true;
        startThread();
    }

    public void destroy() {


    }


    public interface OnCompletionListener {
        public void onCompletion();
    }
}
