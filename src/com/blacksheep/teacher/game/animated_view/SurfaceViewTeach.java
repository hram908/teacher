package com.blacksheep.teacher.game.animated_view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import com.blacksheep.teacher.game.lecture.LectureManager;
import com.blacksheep.teacher.model.database.DBManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 2/25/12
 * Time: 6:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class SurfaceViewTeach extends SurfaceView implements SurfaceHolder.Callback{

    public ThreadAnimation threadAnimation;

    private SurfaceHolder surfaceHolder;



    private IFrameListener iFrameListener;
    private int frameCount;

    public SurfaceViewTeach(Context context, IFrameListener iFrameListener) {
        super(context);
        //setLayoutParams(new ViewGroup.LayoutParams(50,50));

        this.iFrameListener = iFrameListener;
        this.setZOrderOnTop(true);
        getHolder().addCallback(this);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

//        //TODO:нах гавно не надо
//        if(!DBManager.testAnim)
//            return;
        this.surfaceHolder = surfaceHolder;
        threadAnimation = new ThreadAnimation(surfaceHolder,12,getResources());
        threadAnimation.setFrameCount(frameCount);
        threadAnimation.setRunning(true,iFrameListener);
        threadAnimation.start();




    }



    public void startThreadAnim()
    {
        threadAnimation = new ThreadAnimation(surfaceHolder,12,getResources());
        threadAnimation.setFrameCount(frameCount);
        threadAnimation.setRunning(true,iFrameListener);
        threadAnimation.start();

    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        //TODO:нах гавно не надо
//        if(!DBManager.testAnim)
//            return;

        boolean retry = true;
        // завершаем работу потока
        threadAnimation.setRunning(false,null);
        while (retry) {
            try {
                threadAnimation.join();
                retry = false;
            } catch (InterruptedException e) {
                // если не получилось, то будем пытаться еще и еще
            }
        }
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }

    public interface IFrameListener{
        public void frameNumber(int frame);
    }



}
