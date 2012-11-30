package com.blacksheep.teacher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/18/12
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestView extends View{
    public TestView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.at_a46);
        canvas.setDensity(40);
        canvas.drawBitmap(bitmap,0,0,null);
       // super.onDraw(canvas);
    }


}
