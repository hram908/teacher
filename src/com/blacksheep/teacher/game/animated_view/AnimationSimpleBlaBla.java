package com.blacksheep.teacher.game.animated_view;

import android.app.Activity;
import android.util.Log;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Defafault
 * Date: 08.07.12
 * Time: 18:40
 * To change this template use File | Settings | File Templates.
 */
public class AnimationSimpleBlaBla extends AnimationSimple {


    public AnimationSimpleBlaBla( LinkedList<String> dataForAudio, Activity activity) {

        super(new LinkedList<DataAnimation>(), dataForAudio, activity);
    }



//    @Override
//    protected void onCompleateAnimationHead() {
//        Log.i(getClass().getName(), "onCompleate");
//        if(audioCompleate)
//        {
//            audioCompleate=false;
//            if (onCompletionListener != null)
//            {
//                context.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        onCompletionListener.onCompletion(AnimationSimpleBlaBla.this);
//                    }
//                });
//
//            }
//        }
//    }


}
