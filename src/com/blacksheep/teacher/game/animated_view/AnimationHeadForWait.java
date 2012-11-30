package com.blacksheep.teacher.game.animated_view;

import android.app.Activity;
import com.blacksheep.teacher.game.examtest.RecognizeRequest;
import com.blacksheep.teacher.nuanceApp.RecognizeTest;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: vladimirmolodkin
 * Date: 16.07.12
 * Time: 0:28
 * To change this template use File | Settings | File Templates.
 */
public class AnimationHeadForWait extends AnimationHead {
    public AnimationHeadForWait(LinkedList<AnimationSimple> animationSimples, Activity activity) {
        super(animationSimples, activity);
    }

    private boolean isWork;

    @Override
    public void start()
    {
        isWork=true;
        Iterator<AnimationSimple> animationSimpleIterator = animationSimples.iterator();
        AnimationSimple animationSimple = animationSimpleIterator.next();
        animationSimple.setOnCompletionListener(new AnimationEnd2(animationSimpleIterator));
        animationSimple.start();
    }

    public boolean isWork() {
        return isWork;
    }

    class AnimationEnd2 implements AnimationSimple.OnCompletionListener {

        Iterator<AnimationSimple> animationSimpleIterator;

        public AnimationEnd2(Iterator<AnimationSimple> animationSimpleIterator) {
            this.animationSimpleIterator = animationSimpleIterator;
        }

        @Override
        public void onCompletion(AnimationSimple animationHead) {
            animationHead.destroy();
            if (!animationSimpleIterator.hasNext()) {
                isWork=false;
                onCompleateAnimationHead();
            } else {

                AnimationSimple animationSimple = animationSimpleIterator.next();
                if(RecognizeRequest.isRecognize())
                {
                    while (true)
                    {
                        if(animationSimple.isForWaitStart())
                            break;
                        else
                            animationSimple =   animationSimpleIterator.next();
                    }
                }
                animationSimple.setOnCompletionListener(new AnimationEnd2(animationSimpleIterator));
                animationSimple.start();
            }
        }
    }
}
