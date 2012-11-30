package com.blacksheep.teacher.game.examtest;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/8/12
 * Time: 9:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class AudioPhraseBuilder {

    public static MediaPlayer mediaPlayerHead;
    private OnCompletionListener onCompletionListener;
    public static boolean isReleased;
    private Activity activity;
    public static int duration;
    private Context context;
    public AudioPhraseBuilder(Context context1,int a) {
        this.context=context1;
        //this.activity = activity;
    }

    public void play(LinkedList<String> paths) {
        final Iterator<String> stringIterator = paths.iterator();
        //final Iterator<String> stringIterator = convertList(paths).iterator();

        isReleased=false;
        mediaPlayerHead = new MediaPlayer();
        if(!stringIterator.hasNext()) {
            if(onCompletionListener!=null)
                onCompletionListener.onCompletion(AudioPhraseBuilder.this);
            return;
        }
        //AssetFileDescriptor afd=null;
        try{
            try{
                //afd= context.getAssets().openFd(stringIterator.next());
               // mediaPlayerHead.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                String track = stringIterator.next();
                Log.i(this.getClass().getName(),"palay audio "+track);
                mediaPlayerHead.setDataSource(track);


            }
            catch (IOException e) {

               /* mediaPlayerHead=new MediaPlayer();
                AssetFileDescriptor afd2=null;
                try{
                    afd2= context.getAssets().openFd(stringIterator.next());
                    mediaPlayerHead.setDataSource(afd2.getFileDescriptor(), afd2.getStartOffset(), afd2.getLength());
                }
                catch (IOException n)
                {
                    mediaPlayerHead.setDataSource("-");
                }
*/
            }
            mediaPlayerHead.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if(onCompletionListener!=null)
                        onCompletionListener.onCompletion(AudioPhraseBuilder.this);
                }
            });
            mediaPlayerHead.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    duration = mediaPlayer.getDuration();
                    Log.i(this.getClass().getName(),"audio duration "+duration);
                    mediaPlayer.start();
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

    public int getDuration() {
        return duration;
    }

    private class EndPlay implements MediaPlayer.OnCompletionListener {
        private Iterator<String> iterator;

        public EndPlay(Iterator<String> iterator) {
            this.iterator = iterator;
        }

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            isReleased=true;
            mediaPlayerHead.release();
            if (iterator.hasNext()) {
               /* try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }*/
                mediaPlayerHead = new MediaPlayer();
                AssetFileDescriptor afd=null;
                try {
                    try {
                        afd=context.getAssets().openFd(iterator.next());
                        mediaPlayerHead.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    } catch (IOException e) {

                            /*mediaPlayerHead=new MediaPlayer();
                            AssetFileDescriptor afd2=null;
                            try{
                                afd2= context.getAssets().openFd(iterator.next());
                                mediaPlayerHead.setDataSource(afd2.getFileDescriptor(), afd2.getStartOffset(), afd2.getLength());
                            }
                            catch (IOException n)
                            {
                                mediaPlayerHead.setDataSource("-");
                            }*/
                    }
                    mediaPlayerHead.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            isReleased=false;
                            mediaPlayer.start();
                        }
                    });
                    mediaPlayerHead.setOnCompletionListener(new EndPlay(iterator));
                    mediaPlayerHead.prepare();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            else
            {
                if(onCompletionListener!=null)
                    onCompletionListener.onCompletion(AudioPhraseBuilder.this);
            }


        }
    }

    private LinkedList<String> convertList(LinkedList<String> list) {
        LinkedList<String> newList = new LinkedList<String>();
        for (String path : list)
        {
               MediaPlayer mp=new MediaPlayer();
            try {
                AssetFileDescriptor assetFileDescriptor=context.getAssets().openFd(path);
                mp.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                newList.add(path);
            }
            catch (IOException e) {


            }


        }
        return newList;
    }

    public interface OnCompletionListener{

        public void onCompletion(AudioPhraseBuilder audioPhraseBuilder);

    }


}
