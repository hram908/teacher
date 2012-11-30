package com.blacksheep.teacher.game.animated_view;

import android.app.Activity;
import android.media.MediaPlayer;
import android.util.Log;
import com.blacksheep.teacher.game.examtest.AudioPhraseBuilder;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;
import com.blacksheep.teacher.model.database.DBManager;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Defafault
 * Date: 07.07.12
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */
public class AnimationSimple {

    private LinkedList<String> dataForAudio;
    private LinkedList<DataAnimation> dataForAnimations;
    private boolean forWaitStart;

    private ThreadLoadAnimation threadLoadAnimation;
    private ThreadRendering threadRendering;
    protected Activity context;

    protected OnCompletionListener onCompletionListener;
    protected OnCompletionAudioListener onCompletionAudioListener;
    private boolean isAudioCompleate;
    private boolean isQuestion;

    public AnimationSimple(LinkedList<DataAnimation> dataForAnimations, LinkedList<String> dataForAudio, Activity activity) {

        this.dataForAudio = dataForAudio;
        if(dataForAnimations.size()==0)
            dataForAnimations.addAll(generateBlaBla());

        this.dataForAnimations = dataForAnimations;

        this.context=activity;

    }


    public ThreadLoadAnimation getThreadLoadAnimation()
    {
        return threadLoadAnimation;
    }

//    private static AnimationHead instance;
//
//    public static AnimationHead getInstance()
//    {
//        return instance;
//    }

    private   LinkedList<DataAnimation> generateBlaBla()
    {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(dataForAudio.get(0));
        } catch (IOException e) {
            return new LinkedList<DataAnimation>();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            return new LinkedList<DataAnimation>();
        }


        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
        int durationAudio = mediaPlayer.getDuration();
        DataAnimation dataAnimation = new DataAnimation("18",1,1);
        dataAnimation.setFrameCount(8);
        dataAnimation.setEndFrameAnim(8);

        int countRepats = Math.round((float)(durationAudio/((float)dataAnimation.getFrameCount()*(1000.0/12))));
        for (int i=0;i<countRepats;i++)
            dataAnimations.add(dataAnimation);

       // dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
//        dataAnimations.add(new DataAnimation("18",1,1));
        return  dataAnimations;
    }

    public AnimationSimple(String s, LinkedList<String> dataForAudio,Activity context,int a) {
        LinkedList<DataAnimation> animlist =new LinkedList<DataAnimation>();
        DataAnimation daj = DBManager.getInstance().getAnimationByAudioFileName(s);
        if(daj!=null)
            animlist.add(daj);
        this.dataForAnimations = animlist;
        this.dataForAudio = dataForAudio;
        this.context=context;
    }

    public void start() {

        if(checkEmpptyAndComplete())
            return;
        DBManager.testAnim=true;
        threadLoadAnimation = new ThreadLoadAnimation(dataForAnimations);
        threadLoadAnimation.setRunning(true);
        threadLoadAnimation.setOnCompleteListener(new ThreadLoadAnimation.OnCompleteListener() {
            @Override
            public void onComplete() {
                AnimationSimple.this.onCompleateAnimation();
            }
        });
        threadLoadAnimation.start();

        final AudioPhraseBuilder audioPhraseBuilder = new AudioPhraseBuilder(context,0);
        audioPhraseBuilder.setOnCompletionListener(new AudioPhraseBuilder.OnCompletionListener() {
            @Override
            public void onCompletion(AudioPhraseBuilder audioPhraseBuilder) {
                onCompleateAudio();
//                if (onCompletionListener != null)
//                    onCompletionListener.onCompletion(AnimationHead.this);
            }
        });
        if(dataForAnimations.get(0).getStartAudioFrame()>0)
        {
            final int  msStop = Math.abs(dataForAnimations.get(0).getStartAudioFrame())*(1000/12);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(msStop);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            audioPhraseBuilder.play(dataForAudio);
                        }
                    });

                }
            }).start();

        }
        else
        {
            audioPhraseBuilder.play(dataForAudio);
        }

        ThreadAnimation.animationSimple = this;
        //instance = this;

    }

    private boolean checkEmpptyAndComplete()
    {
         if(dataForAnimations.size()==0)
         {
             onCompleateAnimationHeadHard();
             return true;
         }
        return false;
    }

    protected boolean animCompleate = false;
    protected boolean audioCompleate = false;

    public boolean canCompletion=false;

    protected void onCompleateAnimation()
    {
        if(!canCompletion)
            return;
        Log.i(getClass().getName(), "onCompleate anim");
        animCompleate = true;
        onCompleateAnimationHead();
    }

    protected void onCompleateAudio()
    {
        audioCompleate = true;
        if(onCompletionAudioListener!=null)
            onCompletionAudioListener.onCompletionAudio();

        onCompleateAnimationHead();
    }

    protected void onCompleateAnimationHead()
    {
        Log.i(getClass().getName(),"onCompleate");
        if(animCompleate&&audioCompleate)
        {
            if (onCompletionListener != null)
            {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onCompletionListener.onCompletion(AnimationSimple.this);
                    }
                });

            }
        }
    }

    private void onCompleateAnimationHeadHard()
    {
        Log.i(getClass().getName(),"onCompleate");

            if (onCompletionListener != null)
            {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onCompletionListener.onCompletion(AnimationSimple.this);
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
//        threadRendering.setRun(false);
//        try {
//            threadRendering.join();
//            threadRendering.clearBuffer();
//        } catch (InterruptedException e) {
//            threadRendering.clearBuffer();
//        }
    }

    public void pause()
    {
        DBManager.testAnim=false;
    }






    public byte[] getNextFrameForCode() {
        byte[] mas= threadLoadAnimation.getNextAnimation();
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

    public void setOnCompletionAudioListener(OnCompletionAudioListener onCompletionAudioListener){
       this.onCompletionAudioListener = onCompletionAudioListener;
    }

    public boolean isForWaitStart() {
        return forWaitStart;
    }

    public void setForWaitStart(boolean forWaitStart) {
        this.forWaitStart = forWaitStart;
    }

    public boolean isAudioCompleate() {
        return isAudioCompleate;
    }

    public void setAudioCompleate(boolean audioCompleate) {
        isAudioCompleate = audioCompleate;
    }

    public boolean isQuestion() {
        return isQuestion;
    }

    public void setQuestion(boolean question) {
        isQuestion = question;
    }


    public interface OnCompletionListener {

        public void onCompletion(AnimationSimple animationHead);


    }

    public interface OnCompletionAudioListener {

        public void onCompletionAudio();


    }

}
