package com.blacksheep.teacher.game.animated_view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.blacksheep.teacher.game.ConfigManager;
import com.blacksheep.teacher.game.lecture.LectureManager;
import com.blacksheep.teacher.game.nativefunction.NativeFunction;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 2/26/12
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThreadRendering extends Thread {
    private LinkedList<int[]> imagesRender = new LinkedList<int[]>();
    boolean runFlag = false;
    private int bufferSize;
    private int transparentColor;
    private AnimationHead lectureManager;


    public ThreadRendering(AnimationHead lectureManager) {
        this.lectureManager = lectureManager;
        bufferSize = 4;
        transparentColor = -16777216;
      //  transparentColor = -65281;
        setName("thread rendering");
    }


    public void setRun(boolean run) {
        runFlag = run;
    }


    int tr = 0;
    Bitmap bitmap;
    byte[] renderarray;


    @Override
    public void run() {



        int imageWidth = ConfigManager.getInstance().getImageWidth();
        int imageHeight = ConfigManager.getInstance().getIageHeight();
        int countPixeles = imageHeight * imageWidth;

        while (runFlag) {
            Log.i(this.getName(), "work");
            if (imagesRender.size() >= bufferSize) {
                try {
                    Log.i(this.getName(), "sleep");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                continue;
            }
            renderarray = getNextImageForRender();
            if (renderarray == null)
                break;
            //long start = System.currentTimeMillis();
            int[] pixeles = new int[640 * 480];
            bitmap = BitmapFactory.decodeByteArray(renderarray, 0, renderarray.length);
            bitmap.getPixels(pixeles, 0, imageWidth, 0, 0, imageWidth, imageHeight);
            NativeFunction.bitmapCodeNative(pixeles, pixeles.length, transparentColor);
            imagesRender.add(pixeles);
            bitmap.recycle();
        }

    }

    public int[] getImageForView() {
//        if (imagesRender != null) {
//            if (!imagesRender.isEmpty()) {
//                try {
//                    int[] target = imagesRender.getFirst();
//                    imagesRender.removeFirst();
//                    return target;
//                } catch (NoSuchElementException e) {
//                    return null;
//                }
//                catch (NullPointerException e)
//                {
//                    return null;
//                }
//            }
//        }
        if (imagesRender != null)
            if (!imagesRender.isEmpty())
                 {
                    int[] target = imagesRender.getFirst();
                    imagesRender.removeFirst();
                    return target;
                }
//
        return null;
    }

    private byte[] getNextImageForRender() {
        return lectureManager.getNextFrameForCode();
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void setTransparentColor(int transparentColor) {
        this.transparentColor = transparentColor;
    }

    public void clearBuffer() {
        imagesRender = null;
    }
}
