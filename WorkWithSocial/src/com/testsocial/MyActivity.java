package com.testsocial;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class MyActivity extends Activity
{

    TextView textView;
    LinearLayout linearLayout;
    TableLayout tbField;
    LinearLayout llAnswers;
    LinearLayout llError;
    RotateFlat [] fieldCells;
    RotateFlatResult [] answerCells;
    int [] currentWait;
    int currAnswer;
    Animation animationTension;
    Animation animationCompression;
    Animation animationFirst;
    int countError=0;

    boolean [] block;
    


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView(R.layout.test);
        animationCompression =  AnimationUtils.loadAnimation(this, R.anim.rotate_flat);
        animationCompression.setFillAfter(true);
        animationTension =   AnimationUtils.loadAnimation(this, R.anim.rorr);
        animationTension.setFillAfter(true);
        animationFirst =   AnimationUtils.loadAnimation(this, R.anim.start);
        animationFirst.setFillAfter(true);
        tbField = (TableLayout) findViewById(R.id.tb_field);
        llAnswers = (LinearLayout) findViewById(R.id.ll_answers);
        llError = (LinearLayout) findViewById(R.id.ll_error);
        currentWait = new int[1];
        currAnswer=0;
        block= new boolean[3];
        block[2] = false;
        block[0]=true;
        fillField();
        fillAnswer();
        
       // Animation animation  = AnimationUtils.loadAnimation(this, R.anim.start);
      //  animation.setFillAfter(true);
//        linearLayout = (LinearLayout) findViewById(R.id.ll_flat_rotate);
//        //linearLayout.startAnimation(animation);
//        textView = (TextView) findViewById(R.id.tv_test);
//        textView.startAnimation(animation);
    }
    
    @Override
    public void onResume()
    {
        super.onResume();

        for(RotateFlat rf:fieldCells)
            rf.showBack();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        block[1]=false;
                        for(RotateFlat rf:fieldCells)
                        {
                            rf.showFront();
                            rf.setAnimationListener();
                        }
                        block[0]=false;
                        answerCells[currAnswer].showSelector();
                        currentWait[0] = answerCells[currAnswer].getResult();

                    }
                });
            }
        });
        thread.start();
    }
    
    private void fillField()
    {


        fieldCells = new RotateFlat[9];
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(50, 50);
        layoutParams.setMargins(5,5,5,5);
        for(int i=1;i<=9;i++)
        {
            RelativeLayout relativeLayout = new RelativeLayout(this);
           // relativeLayout.setBackgroundColor(Color.WHITE);
            relativeLayout.setLayoutParams(layoutParams);
           // RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(50, 50);
          //  layoutParams.setMargins(5, 5, 5, 5);

            tableRow.addView(relativeLayout);
            animationCompression =  AnimationUtils.loadAnimation(this, R.anim.rotate_flat);
            animationCompression.setFillAfter(true);
            animationTension =   AnimationUtils.loadAnimation(this, R.anim.rorr);
            animationTension.setFillAfter(true);
            animationFirst =   AnimationUtils.loadAnimation(this, R.anim.start);
            animationFirst.setFillAfter(true);
            RotateFlat rotateFlat =  new RotateFlat(i,relativeLayout,currentWait,this,animationTension,animationCompression,animationFirst);
            setResultListener(rotateFlat);
            rotateFlat.block = block;
            fieldCells[i-1]= rotateFlat;
             if(i%3==0)
             {
                 tbField.addView(tableRow,new TableLayout.LayoutParams(
                 TableRow.LayoutParams.FILL_PARENT,
                 TableRow.LayoutParams.WRAP_CONTENT));
                 tableRow = new TableRow(this);
             }
        }
    }
    
    private void setResultListener(RotateFlat rf)
    {
       rf.setTrueAnswerListener(new RotateFlat.TrueAnswerListener() {
           @Override
           public void result(boolean result) {
              if(result)
              {
                  answerCells[currAnswer].showResult();
                  currAnswer++;
                  if(currAnswer==answerCells.length)
                  {
                      Toast.makeText(MyActivity.this,"Запомни кодовое слово для бонуса ",1000).show();
                      block[2]=true;
                      return;
                  }
                  answerCells[currAnswer].showSelector();
                  currentWait[0] =answerCells[currAnswer].getResult();
              }
               else 
              {
                  if(countError>2)
                  {
                      Toast.makeText(MyActivity.this,"Вы проиграли ",1000).show();
                      Thread thread = new Thread(new Runnable() {
                          @Override
                          public void run() {
                              try {
                                  Thread.sleep(3000);
                              } catch (InterruptedException e) {
                                  e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                              }
                             runOnUiThread(new Runnable() {
                                 @Override
                                 public void run() {
                                     finish();
                                     Intent intent = new Intent(MyActivity.this,MyActivity.class);
                                     startActivity(intent);
                                 }
                             });
                          }
                      });
                      thread.start();
//

                  }
                  else 
                  {
                      LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(4,50);
                      layoutParams.setMargins(7,7,7,7);
                      View v = new View(MyActivity.this);
                      v.setBackgroundColor(Color.RED);
                      v.setLayoutParams(layoutParams);
                      llError.addView(v);
                      countError++;
                  }
                  
                  
              }
           }
       }); 
    }



    private void fillAnswer()
    {
        answerCells = new RotateFlatResult[6];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
        layoutParams.setMargins(5,5,5,5);
        String [] answer ={"А","З","Б","У","К","А"};

        for (int i=0;i<6;i++)
        {
            animationCompression =  AnimationUtils.loadAnimation(this, R.anim.rotate_flat);
            animationCompression.setFillAfter(true);
            animationTension =   AnimationUtils.loadAnimation(this, R.anim.rorr);
            animationTension.setFillAfter(true);
            animationFirst =   AnimationUtils.loadAnimation(this, R.anim.start);
            animationFirst.setFillAfter(true);
            RelativeLayout relativeLayout = new RelativeLayout(this);
            llAnswers.addView(relativeLayout,layoutParams);
            answerCells[i] = new RotateFlatResult(i+1,relativeLayout,this,answer[i]);
        }
    }


    
    public void clickRotateFlat2(View v)
    {
        Animation animation  = AnimationUtils.loadAnimation(this, R.anim.rorr);
        animation.setFillAfter(true);
       // LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_flat_rotate);
        linearLayout.startAnimation(animation);
    }

    public void clickRotateFlat(View v)
    {
        final Animation animation12  = AnimationUtils.loadAnimation(this, R.anim.rotate_flat);
        animation12.setFillAfter(true);
        final Animation animation13  = AnimationUtils.loadAnimation(this, R.anim.rorr);
        animation13.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                animation.setAnimationListener(null);
                linearLayout.startAnimation(animation13);
                textView.startAnimation(animation12);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        animation13.setFillAfter(true);
        linearLayout.startAnimation(animation12);
        textView.startAnimation(animation13);

        //linearLayout.startAnimation(animation1);

    }
}
