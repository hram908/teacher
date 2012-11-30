package com.zubrilka;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;


/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 4/5/12
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcZubrilka extends Activity {

    public final static String FONT_SEGOEPR = "fonts/segoepr.ttf";
    private ViewFlow viewFlow;

    TextView lesson1;
    TextView lesson2;
    TextView lesson3;
    TextView lesson4;
    TextView lesson5;
    TextView lesson6;
    TextView lesson7;
    TextView lesson8;
    TextView lesson9;
    TextView lesson10;
    Button play;
    public static final String PATH_OPERATION = "/TeachData/audios/op/_op_";
    public static final String PATH_RESULT = "/TeachData/audios/results/_result_";
    public static final String exten = ".mp3";
    public static boolean isPlay;
    LinearLayout ll_lessons;
    LinkedList<LinkedList<LinearLayout>> ll_examples;

    @Override
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.

    }

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        if (isPlay) {
            AudioPleer.mediaPlayerHead.stop();
            AudioPleer.mediaPlayerHead.release();
            isPlay = false;
            play.setBackgroundResource(R.drawable.btn_play);
        }
        cleanField(viewFlow.getCurrentScreen());

    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ac_zubrilka);

        viewFlow = (ViewFlow) findViewById(R.id.ac_zubrilka_viewflow);
        ll_lessons = (LinearLayout) findViewById(R.id.ac_zubrilka_ll_lessons);
        viewFlow.setLinearLayoutLessons(ll_lessons);
        play = (Button) findViewById(R.id.ac_zubrilka_play);
        AdtZubrilka adapter = new AdtZubrilka(this, viewFlow, play);
        //AdtZubrilka adapter = new AdtZubrilka(this,viewFlow);
        ll_examples = adapter.getListsLayout();
        viewFlow.setAdapter(adapter, 0);
        initLessons();

        clickPlay();
        isPlay = false;


    }

    private void clickPlay() {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (viewFlow.mScroller.isFinished()) {


                    if (!isPlay) {
                        try {
                            AudioPleer.mediaPlayerHead.stop();
                            AudioPleer.mediaPlayerHead.release();
                            cleanField(AudioPleer.currentScreen);
                        } catch (NullPointerException ex) {
                        }
                        catch (IllegalStateException ex2){}
                        cleanField(viewFlow.getCurrentScreen());
                        play.setBackgroundResource(R.drawable.btn_stop);
                        AudioPleer audioPleer = new AudioPleer(ll_examples.get(viewFlow.getCurrentScreen()), AcZubrilka.this,viewFlow.getCurrentScreen());
                        LinkedList<Integer> shuffle_list = new LinkedList<Integer>();
                        LinkedList<String> audio = fillAudio(viewFlow.getCurrentScreen() + 1, shuffle_list,1);
                        audioPleer.play(audio, shuffle_list);
                        isPlay = true;
                        audioPleer.setOnCompletionListener(new AudioPleer.OnCompletionListener() {
                            @Override
                            public void onCompletion(AudioPleer audioPhraseBuilder) {
                                //cleanField();
                                playRandom();
                                /*play.setBackgroundResource(R.drawable.im_ac_zubrilka_button_play);

                                isPlay = false;*/
                            }
                        });
                    } else {
                        isPlay = false;
                        cleanField(AudioPleer.currentScreen);
                        AudioPleer.mediaPlayerHead.stop();
                        AudioPleer.mediaPlayerHead.release();
                        play.setBackgroundResource(R.drawable.btn_play);
                    }
                }
            }
        });
    }
     private void playRandom(){
         AudioPleer audioPleer = new AudioPleer(ll_examples.get(viewFlow.getCurrentScreen()), AcZubrilka.this,viewFlow.getCurrentScreen());
         LinkedList<Integer> shuffle_list = new LinkedList<Integer>();
         LinkedList<String> audio = fillAudio(viewFlow.getCurrentScreen() + 1, shuffle_list,2);
         audioPleer.play(audio, shuffle_list);
         audioPleer.setOnCompletionListener(new AudioPleer.OnCompletionListener() {
             @Override
             public void onCompletion(AudioPleer audioPhraseBuilder) {
                 //cleanField();
                 playRandom();
             }
         });
     }
    private LinkedList<String> fillAudio(int lesson, LinkedList<Integer> numbers_example, int random) {
        LinkedList<String> audio = new LinkedList<String>();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        for (int i = 0; i < 10; i++) {
            numbers_example.add(i);
        }

        Collections.shuffle(numbers_example);
        for (int i = 0; i < 10; i++) {
            Random r1=new Random();
            int a= r1.nextInt(random);
            if(a==0)
            {
            audio.add(path + PATH_OPERATION + lesson + "x" + (numbers_example.get(i) + 1) + exten);
            audio.add(path + PATH_RESULT + lesson * (numbers_example.get(i) + 1) + exten);
            }
            else
            {
            audio.add(path+PATH_OPERATION+(numbers_example.get(i)+1)+"x"+lesson+exten);
            audio.add(path+PATH_RESULT+lesson*(numbers_example.get(i)+1)+exten);
            }


        }
        return audio;
    }
    private void initLessons() {
        lesson1 = (TextView) findViewById(R.id.ac_zubrilka_lesson_1);
        lesson2 = (TextView) findViewById(R.id.ac_zubrilka_lesson_2);
        lesson3 = (TextView) findViewById(R.id.ac_zubrilka_lesson_3);
        lesson4 = (TextView) findViewById(R.id.ac_zubrilka_lesson_4);
        lesson5 = (TextView) findViewById(R.id.ac_zubrilka_lesson_5);
        lesson6 = (TextView) findViewById(R.id.ac_zubrilka_lesson_6);
        lesson7 = (TextView) findViewById(R.id.ac_zubrilka_lesson_7);
        lesson8 = (TextView) findViewById(R.id.ac_zubrilka_lesson_8);
        lesson9 = (TextView) findViewById(R.id.ac_zubrilka_lesson_9);
        lesson10 = (TextView) findViewById(R.id.ac_zubrilka_lesson_10);
        lesson1.setTypeface(Typeface.createFromAsset(getAssets(), FONT_SEGOEPR));
        lesson2.setTypeface(Typeface.createFromAsset(getAssets(), FONT_SEGOEPR));
        lesson3.setTypeface(Typeface.createFromAsset(getAssets(), FONT_SEGOEPR));
        lesson4.setTypeface(Typeface.createFromAsset(getAssets(), FONT_SEGOEPR));
        lesson5.setTypeface(Typeface.createFromAsset(getAssets(), FONT_SEGOEPR));
        lesson6.setTypeface(Typeface.createFromAsset(getAssets(), FONT_SEGOEPR));
        lesson7.setTypeface(Typeface.createFromAsset(getAssets(), FONT_SEGOEPR));
        lesson8.setTypeface(Typeface.createFromAsset(getAssets(), FONT_SEGOEPR));
        lesson9.setTypeface(Typeface.createFromAsset(getAssets(), FONT_SEGOEPR));
        lesson10.setTypeface(Typeface.createFromAsset(getAssets(), FONT_SEGOEPR));
        lesson1.setOnClickListener(new LessinListenet());
        lesson2.setOnClickListener(new LessinListenet());
        lesson3.setOnClickListener(new LessinListenet());
        lesson4.setOnClickListener(new LessinListenet());
        lesson5.setOnClickListener(new LessinListenet());
        lesson6.setOnClickListener(new LessinListenet());
        lesson7.setOnClickListener(new LessinListenet());
        lesson8.setOnClickListener(new LessinListenet());
        lesson9.setOnClickListener(new LessinListenet());
        lesson10.setOnClickListener(new LessinListenet());
        TextView learn = (TextView) findViewById(R.id.ac_zubrilka_learn);
        learn.setTypeface(Typeface.createFromAsset(getAssets(), FONT_SEGOEPR));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        viewFlow.onConfigurationChanged(newConfig);
    }

    private void cleanField(int screen) {
        for (int i = 0; i < 10; i++) {
            LinearLayout linearLayout = ll_examples.get(screen).get(i);
            linearLayout.setBackgroundResource(R.drawable.lesson_bg);
            TextView tv1 = (TextView) linearLayout.getChildAt(0);
            tv1.setTextColor(getResources().getColor(R.color.color_numbers));

        }
    }

    public void clickEsc(View view) {
        finish();
    }


    class LessinListenet implements View.OnClickListener {
        @Override
        public void onClick(View view) {


            if (viewFlow.mScroller.isFinished()) {
                Integer number = Integer.valueOf((String) view.getTag());
                viewFlow.snapToSlide(number);
            }
        }
    }


}

