package com.testsocial;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by IntelliJ IDEA.
 * FriendFFFF: default
 * Date: 4/4/12
 * Time: 1:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class RotateFlat {

    View viewFront;
    View viewBack;
    public boolean isOpen;

    int result;

    protected Animation animationTension;
    protected Animation animationCompression;
    protected Animation animationFirst;
    RelativeLayout relativeLayout;
    int [] currentWait;
    protected Activity activity;
    public boolean [] block;
    private TrueAnswerListener trueAnswerListener;


    public RotateFlat(int result, RelativeLayout relativeLayout1, Activity activity, Animation animationTension, Animation animationCompression, Animation animationFirst)
    {
        this.result = result;
        this.relativeLayout = relativeLayout1;
        this.activity = activity;
        this.animationTension = animationTension;
        this.animationCompression = animationCompression;
        this.animationFirst = animationFirst;
        initComponent();

    }

    public RotateFlat(int result, RelativeLayout relativeLayout1, int[] currentWait, Activity activity, Animation animationTension, Animation animationCompression, Animation animationFirst)
    {

        this.result = result;
        this.currentWait = currentWait;
        this.relativeLayout = relativeLayout1;
        this.relativeLayout.setBackgroundResource(R.drawable.ac_game_element_back);
        this.activity = activity;
        setListener(relativeLayout);
        this.animationTension = animationTension;
        this.animationCompression = animationCompression;
        this.animationFirst = animationFirst;
        initComponent();
    }
    
    protected void addView(View v)
    {
        //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relativeLayout.addView(v,layoutParams);
    }

    public void setAnimationListener()
    {
        block[0]=true;
        animationTension.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                block[0]=true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(!block[1])
                    block[0]=false;
                else
                    block[1]=false;
                Log.i("tension","false");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
    
    protected void initComponent()
    {
        ImageView imageViewBack = new ImageView(activity);
        switch (result)
        {
            case 1:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p1);
                break;
            }
            case 2:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p2);
                break;
            }
            case 3:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p3);
                break;
            }
            case 4:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p4);
                break;
            }
            case 5:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p5);
                break;
            }
            case 6:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p6);
                break;
            }
            case 7:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p7);
                break;
            }
            case 8:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p8);
                break;
            }
            case 9:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p9);
                break;
            }
            case 10:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p10);
                break;
            }
            case 11:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p11);
                break;
            }
            case 12:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p12);
                break;
            }
            case 13:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p13);
                break;
            }
            case 14:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p14);
                break;
            }
            case 15:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p15);
                break;
            }
            case 16:
            {
                imageViewBack.setImageResource(R.drawable.im_ac_game_p16);
                break;
            }
            default:
                break;
        }
        ImageView imageViewFront  = new ImageView(activity);
        imageViewFront.setImageResource(R.drawable.im_ac_game_pv);
        viewBack = imageViewBack;
        viewFront = imageViewFront;
        addView(viewBack);
        addView(viewFront);
        viewBack.startAnimation(animationFirst);
        
        
    }
    
    public int getResult()
    {
        return result;
    }

    private void setListener(RelativeLayout listener)
    {
        listener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(block[0]||block[2])
                    return;
                if(result == currentWait[0])
                {
                    trueAnswerListener.result(true);
                    isOpen = true;
                    showBack();
                }
                else
                {
                    trueAnswerListener.result(false);
                   showBackToFront();
                }
            }
        });
    }

    public void showBack()
    {
        viewBack.startAnimation(animationTension);
        viewFront.startAnimation(animationCompression);
    }
    public void showFront()
    {
        viewBack.startAnimation(animationCompression);
        viewFront.startAnimation(animationTension);
    }

    public void showBackToFront()
    {
        block[0]=true;
        Log.i("tension", "true");
        animationTension.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewBack.startAnimation(animationCompression);
                viewFront.startAnimation(animationTension);
                Log.i("tension", "set listener");
                block[1]=true;
                setAnimationListener();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        viewBack.startAnimation(animationTension);
        viewFront.startAnimation(animationCompression);

    }

    public void setTrueAnswerListener(TrueAnswerListener trueAnswerListener) {
        this.trueAnswerListener = trueAnswerListener;
    }


    public interface TrueAnswerListener{
        public void result(boolean result);

    }

}
