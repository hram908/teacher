package com.blacksheep.teacher.activites.adminka;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.game.animated_view.AnimationHead;
import com.blacksheep.teacher.game.animated_view.AnimationSimple;
import com.blacksheep.teacher.game.animated_view.SurfaceViewTeach;
import com.blacksheep.teacher.game.animated_view.ThreadAnimation;
import com.blacksheep.teacher.game.examtest.ManagerActions;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;
import com.blacksheep.teacher.model.dataEntity.DataAnimationToAudio;
import com.blacksheep.teacher.model.database.DBManager;
import com.perm.kate.api.Newsfeed;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Defafault
 * Date: 23.07.12
 * Time: 1:37
 * To change this template use File | Settings | File Templates.
 */
public class AcAnimation extends Activity {



    public static final String EXT_ANIMATION_ID = "animation id";
    EditText editTextFps;
    EditText editTextstartFrame;
    EditText editTextendFrame;
    EditText editTextCrono;
    View v;
    SurfaceHolder surfaceHolder;
    DataManager dataManager = new DataManager();
    List<String> list = null;
    int currentFrame;
    int fps = 6;
    int fpsMusic=12;
    int sleep;
    int sleepMusic;
    String audio;
    TextView tvCurFrame;
    SoundPool soundPool;

    String animationID;
    int id;

    int idForStop;
    List<DataAnimationToAudio> dataAnimationToAudios;
    DataAnimation dataAnimation;

    MediaPlayer mediaPlayer;
    SurfaceViewTeach surfaceViewTeach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.ac_animation);
        sleep = 1000 / fps;
        sleepMusic = 1000 / fpsMusic;
       // mediaPlayer = new MediaPlayer();
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.ac_animation_rl);
        tvCurFrame = (TextView) findViewById(R.id.ac_animation_tv_cur_frame);
        editTextFps = (EditText) findViewById(R.id.ac_animation_et_fps);
        editTextstartFrame = (EditText) findViewById(R.id.ac_animation_et_startFrame);
        editTextendFrame = (EditText) findViewById(R.id.ac_animation_et_endFrameFrame);
        editTextendFrame.setText("jkk");
        editTextCrono = (EditText) findViewById(R.id.ac_animation_et_crono);
        editTextCrono.setText("-1");
        SurfaceViewTeach1 surfaceView = new SurfaceViewTeach1(this);
       surfaceViewTeach = new SurfaceViewTeach(this,null);

        int widthTeach = 640;
        int heightTeach = 480;
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(widthTeach, heightTeach);
        relativeLayout.addView(surfaceView, lp);
        relativeLayout.addView(surfaceViewTeach,lp);
        currentFrame = 0;
        //animationID = "7";
        animationID = String.valueOf(this.getIntent().getExtras().getInt(EXT_ANIMATION_ID));
        dataAnimation  = DBManager.getInstance().getAnimationById(Integer.parseInt(animationID));

      //  soundPool=new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        dataAnimationToAudios = DBManager.getInstance().getAudiosByAnimationID(Integer.parseInt(animationID));
        editTextendFrame.setText(String.valueOf(dataAnimation.getFrameCount()));

        try{
            Log.i(this.getClass().getName(),"audio name "+dataAnimationToAudios.get(0).getAudio_file_name());
            if(dataAnimationToAudios.get(0).getAudio_file_name().contains("l"))
            {
                    audio =DataManager.getLecture(dataAnimationToAudios.get(0).getAudio_file_name());
            }
            else
            {
                    audio = DataManager.getPhrasesNotExt(dataAnimationToAudios.get(0).getAudio_file_name());
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"нет аудио",1000).show();
        }
       //  id = soundPool.load(DataManager.getPhrases("f-757"),1);



//        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//            @Override
//            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
//                Toast.makeText(AcAnimation.this,"ready",100).show();
//            }
//        });
         idForStop = id;
        try {
            list = dataManager.getImagesPathByAnimationId(animationID);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if(list.size()==0)
            Toast.makeText(this,"нет анимации",1000).show();



    }

    @Override
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
        //stopAnim();
        stopAnimAnim();
    }


    boolean stoping = false;

    public void clickStart(View v) {
        stoping = false;
        try{
            fps = Integer.valueOf(editTextFps.getText().toString());
            sleep = 1000 / fps;
        }
        catch (Exception e)
        {
            Toast.makeText(this,"fps неверен",1000).show();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = currentFrame; i < list.size(); i++) {
                    if (stoping)
                        return;
                    draw(i,sleep);
                }
                currentFrame = 0;
            }
        }).start();

    }

    public void clear(View v)
    {
        Canvas canvas = surfaceHolder.lockCanvas(null);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        surfaceHolder.unlockCanvasAndPost(canvas);
        Canvas canvas1 = surfaceViewTeach.getSurfaceHolder().lockCanvas();
        canvas1.drawColor(0, PorterDuff.Mode.CLEAR);
        surfaceViewTeach.getSurfaceHolder().unlockCanvasAndPost(canvas1);
    }

    private void draw(final int frame,long sleepm) {

        long start = System.currentTimeMillis();
        Bitmap bmp = BitmapFactory.decodeFile(list.get(frame));
        Canvas canvas = surfaceHolder.lockCanvas(null);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bmp, 0, 0, null);
        surfaceHolder.unlockCanvasAndPost(canvas);
        currentFrame = frame;
        AcAnimation.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvCurFrame.setText("Cur frame: " + frame);
            }
        });

        long sleepCur = sleepm - System.currentTimeMillis() + start;
        if (sleepCur > 0) {
            try {
                Thread.sleep(sleepCur);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }


    }

    public void clickStop(View v) {
        stoping = true;
    }

    public void clickPrev(View v) {
        stoping = true;
        currentFrame--;
        draw(currentFrame,sleep);
    }

    public void clickNext(View v) {
        currentFrame++;
        draw(currentFrame,sleep);
    }

    boolean animWithMusicWork = false;

    public void clickStartAudio(View v)
    {
       //startAudioWithAnim();
        startAnimAnimHead();
    }

    private void startAudioWithAnim()
    {
        if(animWithMusicWork)
        {
            stopAnim();
            return;
        }
        animWithMusicWork=true;
        stoping=false;

        DataAnimationToAudio dataAnimationToAudio = DBManager.getInstance().getAudioByAnimationID(Integer.valueOf(animationID));
        int stratFrame = 0;
        try{
            stratFrame = Integer.parseInt(editTextstartFrame.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(this,"fps stratFrame invalid",1000).show();
        }

        //   final int id = soundPool.load(DataManager.getPhrases("f-757"),1);
        final int finalStratFrame = stratFrame;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sleepMusic* finalStratFrame);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                // idForStop = soundPool.play(id,0.5f,0.5f,1,0,1f);
                if(dataAnimationToAudios.get(0).getAudio_file_name().contains("l"))
                {
                    //  id = soundPool.load(DataManager.getLecture(dataAnimationToAudios.get(0).getAudio_file_name()),1);
                    try {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(DataManager.getLecture(dataAnimationToAudios.get(0).getAudio_file_name()));
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                else
                {
                    //   id = soundPool.load(DataManager.getPhrasesNotExt(dataAnimationToAudios.get(0).getAudio_file_name()),1);
                    try {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(DataManager.getPhrasesNotExt(dataAnimationToAudios.get(0).getAudio_file_name()));
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                mediaPlayer.start();
            }
        }).start();

        final int endFrame = Integer.parseInt(editTextendFrame.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < endFrame; i++) {
                    if (stoping)
                        return;
                    draw(i,sleepMusic);
                }
                currentFrame = 0;
            }
        }).start();
    }

    AnimationHead animationHead;

    private void startAnimAnimHead()
    {
//        ManagerActions managerActions = new ManagerActions(this);
//        managerActions.doRepeatQuestion(new ManagerActions.IActionComplete() {
//            @Override
//            public void onComplete() {
//
//            }
//        });
//        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
//        dataAnimations.add(dataAnimation);
//        LinkedList<String> strings = new LinkedList<String>();
//        strings.add(audio);
//        AnimationSimple animationSimple = new AnimationSimple(dataAnimations,strings,this);
//
//        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();
//        animationSimples.add(animationSimple);

     //   animationHead = new AnimationHead(animationSimples,this);
        //animationHead = DataAnimation.generateAnimationHead(dataAnimation,this,Integer.parseInt(editTextCrono.getText().toString()),Integer.parseInt(editTextstartFrame.getText().toString()));
        animationHead = ManagerActions.generateAnimationHeadTest(dataAnimation, 0, "36", "5x5", this, editTextCrono.getText().toString(), Integer.parseInt(editTextstartFrame.getText().toString()));
        animationHead.start();
    }

    public void clickStopAudio(View v)
    {
       //  stopAnim();
        stopAnimAnim();
    }

    private void  stopAnim()
    {
        //soundPool.pause(idForStop);
        try{

            mediaPlayer.stop();
            mediaPlayer.release();
            stoping = true;
            animWithMusicWork=false;
        }
        catch (Exception e)
        {

        }
    }

    private void  stopAnimAnim()
    {
        //soundPool.pause(idForStop);
       animationHead.destroy();
    }



    public class SurfaceViewTeach1 extends SurfaceView implements SurfaceHolder.Callback {


        public SurfaceViewTeach1(Context context) {
            super(context);


            this.setZOrderOnTop(true);
            getHolder().addCallback(this);
            getHolder().setFormat(PixelFormat.TRANSPARENT);
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {

            AcAnimation.this.surfaceHolder = surfaceHolder;
        }

//    public void startView(LectureManager lectureManager)
//    {
//        threadAnimation.setRunning(true,lectureManager);
//        threadAnimation.start();
//    }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {


        }
    }


}
