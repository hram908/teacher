package com.blacksheep.teacher.game.animated_view;

import android.util.Log;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/10/12
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThreadLoadAnimation extends Thread {
    private  LinkedList<byte[]> buffer = new LinkedList<byte[]>();
    private  LinkedList<DataAnimation>  dataAnimations;
    private static boolean[] runFlag = new boolean[1];
    private OnCompleteListener onCompleteListener;

    public static final int BUFFER_SIZE = 10;



    public ThreadLoadAnimation(LinkedList<DataAnimation> dataAnimations1) {

        setName("thread load animation");
        this.dataAnimations=dataAnimations1;
    }



    public byte[] getNextAnimation() {
        if (buffer == null)
            return null;
        try {
            if (!buffer.isEmpty()) {

                return buffer.removeFirst();
            }
        } catch (Exception ex) {
            return null;
        }
        return null;


    }


    @Override
    public void run() {

        DataManager dataManager = new DataManager();
        Iterator<DataAnimation> animationIterator = dataAnimations.iterator();
        runFlag[0]=true;

        while (runFlag[0]) {
            Log.i(this.getName(), "work");
            DataAnimation curAnim = null;

            if (animationIterator.hasNext())
                curAnim = animationIterator.next();
            else
                break;


            try {
                //  dataManager.fillAnimation(curAnim, buffer);
                dataManager.fillAnimationModify(curAnim, buffer, runFlag);
                Log.i(this.getName(), "Load anim " + curAnim.getName());
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(this.getName(), "Load anim fail " + e.getMessage());
            }


        }

        Log.i(getClass().getName(),"thread load finish");

    }

    public void clearBuffer() {
        //  buffer.clear();
        buffer = null;
    }

    public static void setRunning(boolean runFlag1) {
         runFlag[0] = runFlag1;
    }

    public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }

    public interface OnCompleteListener {
        public void onComplete();
    }
}
