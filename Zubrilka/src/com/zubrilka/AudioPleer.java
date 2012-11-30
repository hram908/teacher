package com.zubrilka;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: vovi
 * Date: 23.07.12
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class AudioPleer {
    public static MediaPlayer mediaPlayerHead;
    public static int currentScreen=0;
    private OnCompletionListener onCompletionListener;
    public static boolean isReleased;
    private LinkedList<LinearLayout> ll_lesson;
    private Context mContext;
    LinkedList<Integer> shuffle_list;
    public AudioPleer(LinkedList<LinearLayout> ll_lesson,Context context, int screen){
        this.ll_lesson=ll_lesson;
        mContext=context;
        currentScreen=screen;
    }
    public void play(LinkedList<String> paths,LinkedList<Integer> shuffle) {
        final Iterator<String> stringIterator = paths.iterator();
        this.shuffle_list=shuffle;
        isReleased=false;
        mediaPlayerHead = new MediaPlayer();
        int numberAudio=0;
        if(!stringIterator.hasNext()) { return;}
        try{
            try{

                String track = stringIterator.next();
                Log.i(this.getClass().getName(), "palay audio " + track);
                mediaPlayerHead.setDataSource(track);


            }
            catch (IOException e) {
            }
            mediaPlayerHead.setOnCompletionListener(new EndPlay(stringIterator,numberAudio));
            mediaPlayerHead.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    setSelected(ll_lesson,shuffle_list.get(0),shuffle_list.get(0));
                }
            });
            mediaPlayerHead.prepare();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

//

    public void setOnCompletionListener(OnCompletionListener onCompletionListener1)
    {
        this.onCompletionListener = onCompletionListener1;
    }


    private class EndPlay implements MediaPlayer.OnCompletionListener {
        private Iterator<String> iterator;
        private int numberAudio;
        public EndPlay(Iterator<String> iterator,int numberAudio) {
            this.iterator = iterator;
            this.numberAudio=numberAudio;
        }

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            isReleased=true;
            mediaPlayerHead.release();
            if (iterator.hasNext()) {
                numberAudio+=1;
                if(numberAudio%2==0)
                {
                    setSelected(ll_lesson,shuffle_list.get(numberAudio/2-1),shuffle_list.get(numberAudio/2));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }


                mediaPlayerHead = new MediaPlayer();
                try {
                    try {

                      mediaPlayerHead.setDataSource(iterator.next());
                    } catch (IOException e) {

                    }
                    mediaPlayerHead.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            isReleased=false;
                            mediaPlayer.start();
                        }
                    });
                    mediaPlayerHead.setOnCompletionListener(new EndPlay(iterator,numberAudio));
                    mediaPlayerHead.prepare();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            else
            {
                LinearLayout last_ll=ll_lesson.get(shuffle_list.get(shuffle_list.size()-1));
                last_ll.setBackgroundResource(R.drawable.lesson_bg);
                TextView tv1_last=(TextView)last_ll.getChildAt(0);
                tv1_last.setTextColor(mContext.getResources().getColor(R.color.color_numbers));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                if(onCompletionListener!=null)
                    onCompletionListener.onCompletion(AudioPleer.this);
            }


        }
    }
    private void setSelected(LinkedList<LinearLayout> ll_lessons,int lastView,int currentView)
    {
        LinearLayout current_ll=ll_lessons.get(currentView);
        current_ll.setBackgroundResource(R.drawable.lesson_bg_checked);
        TextView tv1_current=(TextView)current_ll.getChildAt(0);
        tv1_current.setTextColor(mContext.getResources().getColor(R.color.white));
        if(currentView!=lastView){
            LinearLayout last_ll=ll_lessons.get(lastView);
            last_ll.setBackgroundResource(R.drawable.lesson_bg);
            TextView tv1_last=(TextView)last_ll.getChildAt(0);
            tv1_last.setTextColor(mContext.getResources().getColor(R.color.color_numbers));
        }
    }


    public interface OnCompletionListener{

        public void onCompletion(AudioPleer audioPhraseBuilder);

    }

}
