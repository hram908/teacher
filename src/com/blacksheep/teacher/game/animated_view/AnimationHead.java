package com.blacksheep.teacher.game.animated_view;

import android.app.Activity;
import android.util.Log;
import com.blacksheep.teacher.game.examtest.AudioPhraseBuilder;
import com.blacksheep.teacher.game.examtest.ManagerPhrases;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;
import com.blacksheep.teacher.model.database.DBManager;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/10/12
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class AnimationHead {


    private LinkedList<String> dataForAudio;
    private LinkedList<DataAnimation> dataForAnimations;
    public LinkedList<AnimationSimple> animationSimples;

    private ThreadLoadAnimation threadLoadAnimation;
    private ThreadRendering threadRendering;
    private Activity context;

    private OnCompletionListener onCompletionListener;
    private AnimationSimple.OnCompletionAudioListener onCompletionAudioListener;
    private OnAnimationQuestStartListener onAnimationQuestStartListener;



    public AnimationHead(LinkedList<DataAnimation> dataForAnimations, LinkedList<String> dataForAudio, Activity activity, int a) {

        if (dataForAnimations.size() == 0)
            dataForAnimations.addAll(generateBlaBla());

        this.dataForAnimations = dataForAnimations;
        this.dataForAudio = dataForAudio;
        this.context = activity;

    }


    private LinkedList<DataAnimation> generateBlaBla() {
        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation = new DataAnimation("18", 1, 1);
//        dataAnimation.setEq_start(-100);
        dataAnimations.add(dataAnimation);
        //   dataAnimations.add(new DataAnimation("23",1,1));
//        dataAnimations.add(new DataAnimation("24",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("24",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("24",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("24",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("24",1,1));
//        dataAnimations.add(new DataAnimation("24",1,1));
//        dataAnimations.add(new DataAnimation("24",1,1));
//        dataAnimations.add(new DataAnimation("24",1,1));
//        dataAnimations.add(new DataAnimation("24",1,1));
//        dataAnimations.add(new DataAnimation("24",1,1));
        return dataAnimations;
    }

    public AnimationHead(LinkedList<AnimationSimple> animationSimples, Activity activity) {

        this.animationSimples = animationSimples;
        this.context = activity;

    }


    public AnimationHead(String s, LinkedList<String> dataForAudio, Activity context, int a) {
        LinkedList<DataAnimation> animlist = new LinkedList<DataAnimation>();
        DataAnimation daj = DBManager.getInstance().getAnimationByAudioFileName(s);
        if (daj != null)
            animlist.add(daj);
        this.dataForAnimations = animlist;
        this.dataForAudio = dataForAudio;
        this.context = context;
    }

    public void start() {

        Iterator<AnimationSimple> animationSimpleIterator = animationSimples.iterator();
        AnimationSimple animationSimple = animationSimpleIterator.next();
        if(animationSimple.isAudioCompleate())
            animationSimple.setOnCompletionAudioListener(onCompletionAudioListener);
        animationSimple.setOnCompletionListener(new AnimationEnd(animationSimpleIterator));
        animationSimple.start();
        threadLoadAnimation = animationSimple.getThreadLoadAnimation();
    }

    public void setOnAnimationQuestStartListener(OnAnimationQuestStartListener onAnimationQuestStartListener) {
        this.onAnimationQuestStartListener = onAnimationQuestStartListener;
    }

    class AnimationEnd implements AnimationSimple.OnCompletionListener {

        Iterator<AnimationSimple> animationSimpleIterator;

        public AnimationEnd(Iterator<AnimationSimple> animationSimpleIterator) {
            this.animationSimpleIterator = animationSimpleIterator;
        }

        @Override
        public void onCompletion(AnimationSimple animationHead) {
              animationHead.destroy();
            if (!animationSimpleIterator.hasNext()) {
                onCompleateAnimationHead();
            } else {
                AnimationSimple animationSimple = animationSimpleIterator.next();
                if(animationSimple.isQuestion()&&onAnimationQuestStartListener!=null)
                    onAnimationQuestStartListener.startQuest();

                animationSimple.setOnCompletionListener(new AnimationEnd(animationSimpleIterator));
                if(animationSimple.isAudioCompleate())
                     animationSimple.setOnCompletionAudioListener(onCompletionAudioListener);
                animationSimple.start();
                threadLoadAnimation = animationSimple.getThreadLoadAnimation();
            }
        }
    }

    protected void onCompleateAnimationHead() {
        Log.i(getClass().getName(), "onCompleate");
        if (onCompletionListener != null) {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onCompletionListener.onCompletion(AnimationHead.this);
                }
            });

        }

    }

    public void destroy() {

        DBManager.testAnim=false;
        AudioPhraseBuilder.mediaPlayerHead.release();

        //instance = null;
        if(threadLoadAnimation==null)
            return;
        threadLoadAnimation.setRunning(false);
        try {
            threadLoadAnimation.join();
            threadLoadAnimation.clearBuffer();
        } catch (InterruptedException e) {
            threadLoadAnimation.clearBuffer();
        }
    }

    public void pause() {
        DBManager.testAnim = false;
    }


    public byte[] getNextFrameForCode() {
        byte[] mas = threadLoadAnimation.getNextAnimation();
//        if(mas==null)
//            onCompleateAnimation();
        return mas;
    }


    public int[] getNextFrameForView() {
        return threadRendering.getImageForView();
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.onCompletionListener = onCompletionListener;
    }

    public void setOnCompletionAudioListener(AnimationSimple.OnCompletionAudioListener onCompletionAudioListener){
        this.onCompletionAudioListener = onCompletionAudioListener;
    }


    public interface OnCompletionListener {

        public void onCompletion(AnimationHead animationHead);
    }
    public interface OnAnimationQuestStartListener{
        public void startQuest();
    }
}
