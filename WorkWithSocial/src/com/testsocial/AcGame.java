package com.testsocial;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * FriendFFFF: Defafault
 * Date: 05.05.12
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
public class AcGame extends Activity {

    public static final String COUNT_WORD="CountWord";

    LinearLayout linearLayout;
    TableLayout tbField;
    LinearLayout llAnswers;
    LinearLayout llError;
    RotateFlat[] fieldCells;
    RotateFlatResult[] answerCells;
    int[] currentWait;
    int currAnswer;
    Animation animationTension;
    Animation animationCompression;
    Animation animationFirst;
   // TextView tvTime;
    int countError = 0;
    Button btnShow1;
    Button btnShow2;
    Button btnShow3;
    Button btnStart;
    SharedPreferences preferences;
    SharedPreferences.Editor stats_editor;
    boolean[] block;
    static LinkedList<String[]> listWords;
    public static int COUNT_WORDS=6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        preferences= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        stats_editor = PreferenceManager.getDefaultSharedPreferences(this).edit();

        setContentView(R.layout.ac_game);
        TextView tvHeader = (TextView) findViewById(R.id.ac_game_tv_header);
        TextView tvDecode = (TextView) findViewById(R.id.ac_game_tv_decode);
//        tvTime = (TextView) findViewById(R.id.ac_game_tv_time);
//        TextView tvShowField = (TextView) findViewById(R.id.ac_game_tv_show_field);
        tvHeader.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        tvDecode.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
     //   tvTime.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
     //   tvShowField.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        tvHeader.setText(Html.fromHtml("<u>Запомни где какая картинка ученик</u>"));
        tvDecode.setText(Html.fromHtml("<u>Расшифруй тайное слово!</u>"));
      //  tvShowField.setText(Html.fromHtml("<u>Показать поле</u>"));
     //   tvTime.setText(Html.fromHtml("00:05"));
        btnShow1 = (Button) findViewById(R.id.ac_game_btn_show_1);
//        btnShow2 = (Button) findViewById(R.id.ac_game_btn_show_2);
//        btnShow3 = (Button) findViewById(R.id.ac_game_btn_show_3);
        btnStart = (Button) findViewById(R.id.ac_game_btn_start);

        block = new boolean[3];
        block[2] = false;
        block[0] = true;
        currentWait = new int[1];
        tbField = (TableLayout) findViewById(R.id.ac_game_table_field);
        llAnswers = (LinearLayout) findViewById(R.id.ac_game_ll_word);
        fillField();
        fillWord();
        btnShow1.setEnabled(false);
    }

    boolean flagStart;
    public void clickStartGame(View v) {
        flagStart=true;
        btnStart.setEnabled(false);
        startGame();
    }

//    boolean flagShow1;
//    public void clickShowField1(View v)
//    {
//        if(!flagStart)
//            return;
//        btnShow1.setBackgroundResource(R.drawable.im_ac_game_showb);
//        btnShow1.setEnabled(false);
//        showField();
//        flagShow1=true;
//    }
//
//    boolean flagShow2;
//    public void clickShowField2(View v)
//    {
//        if(!flagShow1)
//            return;
//        btnShow2.setBackgroundResource(R.drawable.im_ac_game_showb);
//        btnShow2.setEnabled(false);
//        showField();
//        flagShow2=true;
//    }
//    public void clickShowField3(View v)
//    {
//        if(!flagShow2)
//            return;
//        btnShow3.setBackgroundResource(R.drawable.im_ac_game_showb);
//        btnShow3.setEnabled(false);
//        showField();
//    }
    public void clickExit(View v)
    {
        finish();
    }

    int countShow = 1;
    public void clickShowField(View v)
    {
       countShow++;
        switch (countShow)
        {
            case 2:
            {
                btnShow1.setBackgroundResource(R.drawable.im_ac_game_show2);
                break;
            }
            case 3:
            {
                btnShow1.setBackgroundResource(R.drawable.im_ac_game_show3);
                break;
            }
            case 4:
            {
                btnShow1.setBackgroundResource(R.drawable.im_ac_game_show4);
                btnShow1.setEnabled(false);
                break;
            }
            default:
                break;
        }
        showField();
    }


    private void showField()
    {
        for (RotateFlat rf : fieldCells)
            if (!rf.isOpen)
                rf.showBack();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }

                for (int i=1;i<=5;i++)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    final int finalI = i;
//               //     runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            tvTime.setText("00:0"+(5- finalI));
//                        }
//                    });


                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        block[1] = false;
                        for (RotateFlat rf : fieldCells) {
                            if (!rf.isOpen)
                            {
                                rf.showFront();
                                rf.setAnimationListener();
                            }

                        }
                        block[0] = false;
                    }
                });
            }
        });
        thread.start();
    }
   /* private void setCountWord(){
        int currentWord=preferences.getInt(COUNT_WORD,0);
        if(currentWord<countWords-1){
            stats_editor.putInt(COUNT_WORD,currentWord+1);
        }
        else {
            stats_editor.putInt(COUNT_WORD,0);
        }
        stats_editor.commit();
    }*/
    private void setResultListener(RotateFlat rf) {
        rf.setTrueAnswerListener(new RotateFlat.TrueAnswerListener() {
            @Override
            public void result(boolean result) {
                if (result) {
                    answerCells[currAnswer].showResult();
                    currAnswer++;
                    if (currAnswer == answerCells.length) {
                        Toast.makeText(AcGame.this, "Запомни кодовое слово для бонуса ", 1000).show();
                        block[2] = true;
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                }
                                AcGame.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        finish();
                                    }
                                });
                            }
                        }).start();
                        return;
                    }
                    answerCells[currAnswer].showSelector();
                    currentWait[0] = answerCells[currAnswer].getResult();
                } else {
                    answerCells[currAnswer].showError();
                    if (answerCells[currAnswer].getCountError() == 3) {
                        currAnswer++;
                        if (currAnswer == answerCells.length) {
                            block[2] = true;
                            Toast.makeText(AcGame.this, "Запомни кодовое слово для бонуса ", 1000).show();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                    }
                                    AcGame.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            finish();
                                        }
                                    });
                                }
                            }).start();
                            return;
                        }
                        answerCells[currAnswer].showSelector();
                        currentWait[0] = answerCells[currAnswer].getResult();
                    }


                }
            }
        });
    }


    private void startGame() {
        btnShow1.setEnabled(true);
        for (RotateFlat rf : fieldCells)
             if (!rf.isOpen)
                 rf.showBack();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }


                for (int i=1;i<=5;i++)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                  //  final int finalI = i;
                  ///  runOnUiThread(new Runnable() {
                 //       @Override
                 //       public void run() {
                 //           tvTime.setText("00:0"+(5- finalI));
                //        }
                 //   });


                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        block[1] = false;
                        for (RotateFlat rf : fieldCells) {
                            if (!rf.isOpen)
                            {
                                rf.showFront();
                                rf.setAnimationListener();
                            }

                        }
                        block[0] = false;
                        answerCells[currAnswer].showSelector();
                        currentWait[0] = answerCells[currAnswer].getResult();

                    }
                });
            }
        });
        thread.start();
    }


    private void fillWord() {

        answerCells = new RotateFlatResult[6];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //layoutParams.setMargins(5, 5, 5, 5);
        listWords=fillStartWords();
        //String[] answer = {"В", "У", "Л", "К", "А", "Н"};
        String[] answer=listWords.get(preferences.getInt(COUNT_WORD,0));
        int [] shufffle = new int[answer.length];
        for(int i=0;i<answer.length;i++)
            shufffle[i]=i+1;
        shuffleArray(shufffle);

        for (int i = 0; i < answer.length; i++) {
            RelativeLayout relativeLayout = new RelativeLayout(this);
            llAnswers.addView(relativeLayout, layoutParams);
            answerCells[i] = new RotateFlatResult(shufffle[i], relativeLayout, this, answer[i]);
        }
    }
    private LinkedList<String[]> fillStartWords()
    {
           LinkedList<String[]> list=new LinkedList<String[]>();
        String[] answer1 = {"В", "У", "Л", "К", "А", "Н"};
        String[] answer2 = {"К", "А", "Ш", "Т", "А", "Н"};
        String[] answer3 = {"П", "О", "Н", "Ч", "И", "К"};
        String[] answer4 = {"К", "Л", "Я", "К", "С", "А"};
        String[] answer5 = {"К", "А", "М", "Е", "Н", "Ь"};
        String[] answer6 = {"Л", "И", "С", "Т", "О", "К"};

        list.add(answer1);
        list.add(answer2);
        list.add(answer3);
        list.add(answer4);
        list.add(answer5);
        list.add(answer6);

        return  list;
    }

    static void shuffleArray(int[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i >= 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    private void fillField() {

        int [] shufarr = new int[16];

        for (int i = 0; i <16; i++)
              shufarr[i]=i+1;
        shuffleArray(shufarr);
        fieldCells = new RotateFlat[16];
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        //TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(50, 50);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        // layoutParams.setMargins(5,5,5,5);
        for (int i = 1; i <= 16; i++) {
            RelativeLayout relativeLayout = new RelativeLayout(this);
            // relativeLayout.setBackgroundColor(Color.WHITE);
            relativeLayout.setLayoutParams(layoutParams);
            // RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(50, 50);
            //  layoutParams.setMargins(5, 5, 5, 5);

            tableRow.addView(relativeLayout);
            animationCompression = AnimationUtils.loadAnimation(this, R.anim.rotate_flat);
            animationCompression.setFillAfter(true);
            animationTension = AnimationUtils.loadAnimation(this, R.anim.rorr);
            animationTension.setFillAfter(true);
            animationFirst = AnimationUtils.loadAnimation(this, R.anim.start);
            animationFirst.setFillAfter(true);
            RotateFlat rotateFlat = new RotateFlat(shufarr[i-1], relativeLayout, currentWait, this, animationTension, animationCompression, animationFirst);
            setResultListener(rotateFlat);
            rotateFlat.block = block;
            fieldCells[shufarr[i-1]-1] = rotateFlat;
            if (i % 4 == 0) {
                tbField.addView(tableRow, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tableRow = new TableRow(this);
            }
        }
    }
    public static LinkedList<String> getListWords() {
        LinkedList<String> list=new LinkedList<String>();
        String answer1 = "вулкан";
        String answer2 = "каштан";
        String answer3 = "пончик";
        String answer4 = "клякса";
        String answer5 = "камень";
        String answer6 = "листок";

        list.add(answer1);
        list.add(answer2);
        list.add(answer3);
        list.add(answer4);
        list.add(answer5);
        list.add(answer6);

        return  list;
    }
}
