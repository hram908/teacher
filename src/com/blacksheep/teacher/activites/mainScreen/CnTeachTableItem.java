package com.blacksheep.teacher.activites.mainScreen;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.blacksheep.teacher.R;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 08.04.12
 * Time: 21:33
 * To change this template use File | Settings | File Templates.
 */
public class CnTeachTableItem extends RelativeLayout{
    public Button button;
    ImageView star1;
    ImageView star2;
    ImageView star3;
    public int lectionID;
    private int countStar;
    Context context;

    public CnTeachTableItem(Context context,Drawable drawable,int countStrar) {
        super(context);
        this.context = context;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.cn_teach_table_item, this, true);
        button = (Button) findViewById(R.id.cn_teach_table_item_button);
        star1 = (ImageView) findViewById(R.id.cn_teach_table_item_star2);
        star2 = (ImageView) findViewById(R.id.cn_teach_table_item_star1);
        star3 = (ImageView) findViewById(R.id.cn_teach_table_item_star3);
        button.setBackgroundDrawable(drawable);
        enableStar(countStrar);
    }

    public void disableStar()
    {
        star1.setVisibility(INVISIBLE);
        star2.setVisibility(INVISIBLE);
        star3.setVisibility(INVISIBLE);
    }
    public void enableStar(int count)
    {
        countStar=count;
        disableStar();
        if(count>0)
            star1.setVisibility(VISIBLE);
        if(count>1)
            star2.setVisibility(VISIBLE);
        if(count>2)
            star3.setVisibility(VISIBLE);
    }

    public int getCountStar() {
        return countStar;
    }

    public void startBlink(int count) {
         int c = count-countStar;
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.anim_ac_main_teach_screen_star);
        animation.setFillAfter(true);
        if(countStar==0)
        {
            star1.setVisibility(VISIBLE);
            star1.startAnimation(animation);
        }
        if(countStar==1||((c==2||c==3)&&countStar==0))
        {
            star2.setVisibility(VISIBLE);
            star2.startAnimation(animation);
        }
        if(countStar==2||(c==3&&countStar==0)||(c==2&&countStar==1))
        {
            star3.setVisibility(VISIBLE);
            star3.startAnimation(animation);
        }
    }
}