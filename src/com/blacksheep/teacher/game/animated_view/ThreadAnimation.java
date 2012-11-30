package com.blacksheep.teacher.game.animated_view;

import android.content.res.Resources;
import android.graphics.*;
import android.util.Log;
import android.view.SurfaceHolder;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.model.database.DBManager;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 2/25/12
 * Time: 10:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThreadAnimation extends Thread {
    private boolean runFlag = false;
    private final SurfaceHolder surfaceHolder;
    private int msFps;
    public static AnimationSimple animationSimple;
    private SurfaceViewTeach.IFrameListener iFrameListener;




    public ThreadAnimation(SurfaceHolder holder, int fps, Resources res) {

        this.surfaceHolder = holder;
        //  fps=4;
        msFps = 1000 / fps;
        frameCount = 0;
        setName("thread animation");
    }




    public void setRunning(boolean run, SurfaceViewTeach.IFrameListener iFrameListener) {
        this.runFlag = run;
        this.iFrameListener = iFrameListener;
    }

    private int frameCount;
    Bitmap bitmap;
    byte[] renderarray;


    //int countFrame=1;

    @Override
    public void run() {

        // BitmapFactory.Options options  =new BitmapFactory.Options();
        // options.inPreferredConfig  = Bitmap.Config.RGB_565;
        // int imageWidth = ConfigManager.getInstance().getImageWidth();
        //  int imageHeight = ConfigManager.getInstance().getIageHeight();
        //int  transparentColor = -16777216;
        // int countPixeles = imageHeight * imageWidth;
        Matrix matrix = new Matrix();
        matrix.postScale(MyApplication.instance.getCoefficientTension(), MyApplication.instance.getCoefficientTension());
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        //  int[] pixeles = new int[640 * 480];


        while (runFlag) {
            long startTime = System.currentTimeMillis();
            if (animationSimple == null)
                continue;
            if (!DBManager.testAnim)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                continue;
            }

            renderarray = animationSimple.getNextFrameForCode();

            if (renderarray == null) {
                // countFrame=1;
                //  Log.i("null renderarray","null");
                if (!animationSimple.animCompleate)
                    animationSimple.onCompleateAnimation();
                continue;
            }


            bitmap = BitmapFactory.decodeByteArray(renderarray, 0, renderarray.length);

            if (bitmap == null) {
                //  Log.i("null bitmap","null");
                continue;
            }


            Canvas canvas = null;
            canvas = surfaceHolder.lockCanvas(null);
            animationSimple.canCompletion = true;
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.setMatrix(matrix);
            canvas.drawBitmap(bitmap, 0, 0, paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
            long sleepTime = msFps - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    Log.i("pausethreadanim", String.valueOf(sleepTime));
            } catch (Exception e) {
            }
        }
        Log.i(getClass().getName(),"thread anim finish");
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

}
