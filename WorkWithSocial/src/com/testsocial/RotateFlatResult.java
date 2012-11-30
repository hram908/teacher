package com.testsocial;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by IntelliJ IDEA.
 * FriendFFFF: default
 * Date: 4/4/12
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class RotateFlatResult {

    View viewResult;
    View imageViewFront;
    View imageViewBack;
    private String resultWord;
    private int result;
    Activity activity;
    private RelativeLayout relativeLayout;
    private int countError;

    public RotateFlatResult(int result, RelativeLayout relativeLayout1, Activity activity, String resultWord)
    {
        this.activity = activity;
        this.result = result;
        this.resultWord = resultWord;
        TextView textView = new TextView(activity);
        textView.setText(resultWord);
        textView.setTextColor(R.color.mycolors);
        textView.setTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/segoepr.ttf"));
        viewResult = textView;
        this.relativeLayout = relativeLayout1;
        imageViewFront = new ImageView(activity);
         viewResult.setVisibility(View.INVISIBLE);
        imageViewFront.setVisibility(View.INVISIBLE);
        imageViewFront.setBackgroundResource(R.drawable.ac_game_element_back);
        initComponent();
        addView(imageViewBack);
        addView(imageViewFront);
        addView(viewResult);
    }

    public void showResult()
    {
       // imageViewFront=null;
        //imageViewBack = viewResult;
        imageViewBack.setBackgroundResource(R.drawable.ac_game_element_back);
        viewResult.setVisibility(View.VISIBLE);
        imageViewBack.setVisibility(View.INVISIBLE);
        imageViewFront.setVisibility(View.INVISIBLE);
    }

    public void showError()
    {
        countError++;
//        if(countError==1)
//        {
//            ((ImageView)imageViewFront).setImageResource(R.drawable.im_ac_game_error1);
//        }
//        if(countError==2)
//        {
//            ((ImageView)imageViewFront).setImageResource(R.drawable.im_ac_game_error2);
//        }
//        if(countError==3)
//        {
//            ((ImageView)imageViewFront).setImageResource(R.drawable.im_ac_game_error3);
//        }
        if(countError==3)
        {
            ((ImageView)imageViewBack).setImageResource(R.drawable.im_ac_game_error3);
            nextChar();
            imageViewBack.setBackgroundResource(R.drawable.ac_game_element_back);
        }
    }

    private void nextChar() {


    }

    protected void addView(View v)
    {
        //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
        relativeLayout.addView(v,layoutParams);
    }




    protected void initComponent() {
        imageViewBack = new ImageView(activity);
        imageViewBack.setBackgroundResource(R.drawable.ac_game_element_back);
        switch (result)
        {
            case 1:
            {
                ((ImageView)imageViewBack).setImageResource(R.drawable.im_ac_game_m1);
                break;
            }
            case 2:
            {
                ((ImageView)imageViewBack).setImageResource(R.drawable.im_ac_game_m2);
                break;
            }
            case 3:
            {
                ((ImageView)imageViewBack).setImageResource(R.drawable.im_ac_game_m3);
                break;
            }
            case 4:
            {
                ((ImageView)imageViewBack).setImageResource(R.drawable.im_ac_game_m4);
                break;
            }
            case 5:
            {
                ((ImageView)imageViewBack).setImageResource(R.drawable.im_ac_game_m5);
                break;
            }
            case 6:
            {
                ((ImageView)imageViewBack).setImageResource(R.drawable.im_ac_game_m6);
                break;
            }
            case 7:
            {
                ((ImageView)imageViewBack).setImageResource(R.drawable.im_ac_game_m7);
                break;
            }
            case 8:
            {
                ((ImageView)imageViewBack).setImageResource(R.drawable.im_ac_game_m8);
                break;
            }
            default:
                break;
        }
    }

    public int getResult() {
        return result;
    }

    public int getCountError()
    {
        return countError;
    }


    public void showSelector() {
        imageViewBack.setBackgroundResource(R.drawable.ac_game_element_select);

    }
}
