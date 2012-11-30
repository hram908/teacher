package com.blacksheep.teacher.model.dataEntity;

import android.app.Activity;
import com.blacksheep.teacher.game.animated_view.AnimationHead;
import com.blacksheep.teacher.game.animated_view.AnimationSimple;
import com.blacksheep.teacher.game.animated_view.AnimationSimpleBlaBla;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.database.DBManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 2/25/12
 * Time: 10:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataAnimation implements Cloneable {

    public static final int ANIMATION_TYPE_GREETING=0;
    public static final int ANIMATION_TYPE_LECTURE=2;
    public static final int ANIMATION_TYPE_QUESTION_REQUEST=3;
    public static final int ANIMATION_TYPE_LIPS_SPEAK_SHORT_ANIMATION=4;
    public static final int ANIMATION_TYPE_MOVING_SHORT_ANIMATION=5;
    public static final int ANIMATION_TYPE_RESPONSE=6;
    public static final int ANIMATION_TYPE_LESSON_RESULT=8;
    public static final int ANIMATION_TYPE_LESSON_END=9;
    public static final int ANIMATION_TYPE_START_EXAM=10;
    public static final int ANIMATION_TYPE_END_EXAM=11;
    public static final int ANIMATION_TYPE_APPLICATION_EVENT=12;
    public static final int ANIMATION_TYPE_WAIT_RESPONSE=13;
    public static final int ANIMATION_TYPE_AUDIO_RESOURCES = 14;

    //public static final int ANIMATION_TYPE_INFORMATION=7;
    public static final int OPTION_CODE_GREETING_FIRST=0;
    public static final int OPTION_CODE_GREETING_OTHER=1;


    public static final int OPTION_CODE_RESPONSE_TRUE_S=0;
    public static final int OPTION_CODE_RESPONSE_TRUE_P=1;
    public static final int OPTION_CODE_RESPONSE_FALSE_S=2;
    public static final int OPTION_CODE_RESPONSE_FALSE_N=3;
    public static final int OPTION_CODE_RESPONSE_INCORRECTLY=4;
    public static final int OPTION_CODE_RESPONSE_SILENCE=5;

    public static final int OPTION_CODE_LESSON_END_SIMPLE_END=0;
    public static final int OPTION_CODE_LESSON_END_WAS_LAST_LESSON=1;
    public static final int OPTION_CODE_LESSON_END_ALL_LESSON_COMPLETE=2;

    public static final int OPTION_CODE_START_EXAM_FIRST_1=0;
    public static final int OPTION_CODE_START_EXAM_FIRST_2=1;
    public static final int OPTION_CODE_START_EXAM_FIRST_3=2;
    public static final int OPTION_CODE_START_EXAM_SECOND=3;

    public static final int OPTION_CODE_END_EXAM_1=0;
    public static final int OPTION_CODE_END_EXAM_2=1;
    public static final int OPTION_CODE_END_EXAM_3=2;
    public static final int OPTION_CODE_END_EXAM_FAIL=3;
    public static final int OPTION_CODE_END_EXAM_GREETING=4;


    public static final int OPTION_CODE_APPLICATION_EVENT_BREAK=0;
    public static final int OPTION_CODE_APPLICATION_EVENT_CALL_PARENT=1;
    public static final int OPTION_CODE_APPLICATION_EVENT_ALL_LESSON_COMPLETE=2;
    public static final int OPTION_CODE_APPLICATION_EVENT_NAME_DIDNT_LISTEN_ON_FIRST_GREETING=3;
    public static final int OPTION_CODE_APPLICATION_EVENT_REPEAT_QUESTION=4;
    public static final int OPTION_CODE_APPLICATION_EVENT_BONUS=6;
    public static final int OPTION_CODE_APPLICATION_EVENT_SPEAK_CORRECT_ANSWER=7;
    public static final int OPTION_CODE_APPLICATION_EVENT_NO_INTERNET=8;
    public static final int OPTION_CODE_APPLICATION_EVENT_ANSWER_CLEARLY=9;


    public static final int OPTION_CODE_AUDIO_RESOURCES_QUEST=0;
    public static final int OPTION_CODE_AUDIO_RESOURCES_RESPONSE=1;
    public static final int OPTION_CODE_AUDIO_RESOURCES_NAME=2;

    public static final int OPTION_CODE_WAIT_RESPONSE_SHORT =0 ;
    public static final int OPTION_CODE_WAIT_RESPONSE_LONG = 1;

    public static final String PATTERN_ANIMATION="animation";
    public static final String PATTERN_NAME="name";
    public static final String PATTERN_QUEST="quest";
    public static final String PATTERN_ANSWER="answer";
    public static final String PATTERN_BEGIN_ACTION="action_begin";
    public static final String PATTERN_ACTION="action";
    public static final String PATTERN_END_ACTION="action_end";


    private int type;
    private int id;
    private int frame_count;
    private  int animation_type;
    private  int eq_start;
    private  int eq_end;
    private  int fps;
    private String name;
    private int option_code;
    private boolean isSync;

    private int endFrameAnim;
    private int startFrameAnim;
    private String chronometry;
    private String pattern;
    private int start_frame;



    public void setStartAudioFrame(int start_frame) {
        this.start_frame = start_frame;
    }



    public void setEndFrameAnim(int endFrameAnim) {
        this.endFrameAnim = endFrameAnim;
    }


//    RAW_ANIMATION_NAME        +" STRING, "+
//    RAW_ANIMATION_TYPE        +" INTEGER, "+
//    RAW_ANIMATION_FPS         +" INTEGER, "+
//    RAW_ANIMATION_FRAME_COUNT +" INTEGER, "+
//    RAW_ANIMATION_OPTION_CODE +" INTEGER, "+
//    RAW_ANIMATION_EQ_START    +" INTEGER, "+
//    RAW_ANIMATION_EQ_END      +" INTEGER, "+
//    RAW_ANIMATION_START_FRAME +" INTEGER, "+
//    RAW_ANIMATION_CHRONOMETRY +" STRING, "+
//    RAW_ANIMATION_PATTERN     +" STRING, "+
//    RAW_ANIMATION_IS_SYNC+" BOOLEAN);");


    public DataAnimation(int id, String name, int animation_type, int fps, int frame_count, int option_code, int eq_start, int eq_end,int start_frame,String chronometry,String pattern, int isSync) {
        this.id = id;
        this.frame_count = frame_count;
        this.animation_type = animation_type;
        this.eq_start = eq_start;
        this.eq_end = eq_end;
        this.fps = fps;
        this.name = name;
        this.option_code = option_code;
        this.isSync = isSync==0?false:true;
        this.start_frame = start_frame;
        this.chronometry = chronometry;
        this.pattern = pattern;
        this.endFrameAnim = 0;
    }

    public DataAnimation(int id, String name, int animation_type, int fps, int frame_count, int eq_start, int eq_end, int option_code, int isSync, int start_frame, int endFrameAnim) {
        this.id = id;
        this.frame_count = frame_count;
        this.animation_type = animation_type;
        this.eq_start = eq_start;
        this.eq_end = eq_end;
        this.fps = fps;
        this.name = name;
        this.option_code = option_code;
        this.start_frame = start_frame;
        this.endFrameAnim = endFrameAnim;
        this.isSync = isSync==0?false:true;
    }

    public DataAnimation(int id, String name, int animation_type, int fps, int frame_count, int eq_start, int eq_end, int option_code, int isSync, int start_frame, int endFrameAnim, int startFrameAnim, String chronometry, String pattern) {
        this.id = id;
        this.frame_count = frame_count;
        this.animation_type = animation_type;
        this.eq_start = eq_start;
        this.eq_end = eq_end;
        this.fps = fps;
        this.name = name;
        this.option_code = option_code;
        this.start_frame = start_frame;
        this.endFrameAnim = endFrameAnim;
        this.startFrameAnim = startFrameAnim;
        this.chronometry = chronometry;
        this.pattern = pattern;
        this.isSync = isSync==0?false:true;
    }



    public DataAnimation()
    {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();    //To change body of overridden methods use File | Settings | File Templates.
    }


    public DataAnimation(String id,int type,int frameCount)
    {
        this.id= Integer.valueOf(id);
        this.type = type;
        this.frame_count = frameCount;
        startFrameAnim=0;
    }

    public String getFolderName()
    {
        return String.valueOf((id));
    }


    public int getId() {
        return id;
    }



    public int getAnimation_type() {
        return animation_type;
    }

    public void setEq_start(int eq_start) {
        this.eq_start = eq_start;
    }


    public int getEq_start() {
        return eq_start;
    }

    public int getEq_end() {
        return eq_end;
    }

    public int getFps() {
        return fps;
    }



    public int getOption_code() {
        return option_code;
    }

    public boolean isSync() {
        return isSync;
    }

    
    public int getType()
    {
        return this.type;
    }

    public void setType(int type)
    {
        this.type = type;
    }


    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getFrameCount() {
        return frame_count;
    }

    public void setFrameCount(int frameCount) {
        this.frame_count = frameCount;
    }

//    public LinkedList<byte[]> getData() {
//        return data;
//    }
//
//    public void setData(LinkedList<byte[]> data) {
//        this.data = data;
//    }

    public int getStartAudioFrame() {
        return start_frame;
    }

    public int getEndFrameAnim() {
        return endFrameAnim;
    }

    public String getChronometry() {
        return chronometry;
    }

    public String getPattern() {
        return pattern;
    }

    public int getStartFrameAnim() {
        return startFrameAnim;
    }

    public void setStartFrameAnim(int startFrameAnim) {
        this.startFrameAnim = startFrameAnim;
    }

    public String [] parsePattern()
    {
        if(pattern.equals("null"))
            return null;
        return pattern.split(";");
    }

    public static AnimationHead generateAnimationHead(DataAnimation dataAnimation,Activity act,int cronometry,int startAudio)
    {


         LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();

        List<DataAnimationToAudio> audios =  DBManager.getInstance().getAudiosByAnimationID(dataAnimation.getId());


         String [] animArray = dataAnimation.getPattern().split(";");

        boolean chronometryIsUse = false;

        for (String i:animArray)
        {
            if(i.equals(DataAnimation.PATTERN_ANIMATION))
            {
                LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
                LinkedList<String> audiosCur = new LinkedList<String>();
                DataAnimation dataAnimation1;
                try {
                    dataAnimation1 = (DataAnimation)dataAnimation.clone();
                } catch (CloneNotSupportedException e) {
                    dataAnimation1 = dataAnimation;
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                if(!dataAnimation.getChronometry().equals(""))
                       if(!chronometryIsUse)
                       {
                           int crono;
                           if(cronometry!=-1)
                               crono =cronometry;
                           else
                              crono = Integer.parseInt(dataAnimation.getChronometry());
                           if(crono==0)
                               crono=10;
                          dataAnimation1.setEndFrameAnim(crono);

                           dataAnimation1.setStartAudioFrame(startAudio);
                           audiosCur.add(DataManager.getPhrasesNotExt(audios.get(0).getAudio_file_name()));
                           chronometryIsUse=true;
                       }
                       else
                       {
                           int crono;
                           if(cronometry!=-1)
                               crono =cronometry;
                           else
                               crono = Integer.parseInt(dataAnimation.getChronometry());
                           if(crono==0)
                               crono=10;
                           dataAnimation1.setStartFrameAnim(crono);
                           audiosCur.add(DataManager.getPhrasesNotExt(audios.get(1).getAudio_file_name()));
                       }
                else {
                    dataAnimation1.setStartAudioFrame(startAudio);
                    audiosCur.add(DataManager.getPhrasesNotExt(audios.get(0).getAudio_file_name()));
                }
                dataAnimations.add(dataAnimation1);

                AnimationSimple animationSimple = new AnimationSimple(dataAnimations,audiosCur,act);
                animationSimples.add(animationSimple);
            }
            else if(i.equals(DataAnimation.PATTERN_NAME))
            {
                LinkedList<String> audiosCur = new LinkedList<String>();
                audiosCur.add(DataManager.getNameTest("1p_tarasik"));
                AnimationSimpleBlaBla animationSimpleBlaBla = new AnimationSimpleBlaBla(audiosCur,act);
                animationSimples.add(animationSimpleBlaBla);
            }
            else if(i.equals(DataAnimation.PATTERN_ANSWER))
            {
                LinkedList<String> audiosCur = new LinkedList<String>();
                audiosCur.add(DataManager.getCorrectResult("36"));
                AnimationSimpleBlaBla animationSimpleBlaBla = new AnimationSimpleBlaBla(audiosCur,act);
                animationSimples.add(animationSimpleBlaBla);
            }
            else if((i.equals(DataAnimation.PATTERN_QUEST)))
            {
                LinkedList<String> audiosCur = new LinkedList<String>();
                audiosCur.add(DataManager.getOperation("5x5"));
                AnimationSimpleBlaBla animationSimpleBlaBla = new AnimationSimpleBlaBla(audiosCur,act);
                animationSimples.add(animationSimpleBlaBla);
            }

        }

         AnimationHead animationHead = new AnimationHead(animationSimples,act);
        return  animationHead;
    }

    private AnimationSimple generateNameAnim(int number,Activity act)
    {
        LinkedList<String> audiosCur = new LinkedList<String>();
        audiosCur.add(DataManager.getNameTest("1p_tarasik"));
        AnimationSimpleBlaBla animationSimpleBlaBla = new AnimationSimpleBlaBla(audiosCur,act);
        return animationSimpleBlaBla;
    }

    private AnimationSimple generateAnswerAnim(Activity act,String answer)
    {
        LinkedList<String> audiosCur = new LinkedList<String>();
        audiosCur.add(DataManager.getCorrectResult(answer));
        AnimationSimpleBlaBla animationSimpleBlaBla = new AnimationSimpleBlaBla(audiosCur,act);
        return animationSimpleBlaBla;
    }

    private AnimationSimple generateQuestAnim(Activity act,String quest)
    {
        LinkedList<String> audiosCur = new LinkedList<String>();
        audiosCur.add(DataManager.getOperation(quest));
        AnimationSimpleBlaBla animationSimpleBlaBla = new AnimationSimpleBlaBla(audiosCur,act);
        return animationSimpleBlaBla;
    }

    private  AnimationSimple generateAnimAnim(DataAnimation dataAnimation,int startFrame,int endFrame,Activity act)
    {
        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
        LinkedList<String> audiosCur = new LinkedList<String>();
        DataAnimation dataAnimation1;
        try {
            dataAnimation1 = (DataAnimation)dataAnimation.clone();
        } catch (CloneNotSupportedException e) {
            dataAnimation1 = dataAnimation;
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        dataAnimation1.setStartFrameAnim(startFrame);
        dataAnimation1.setEndFrameAnim(endFrame);

        dataAnimations.add(dataAnimation1);
        AnimationSimple animationSimple = new AnimationSimple(dataAnimations,audiosCur,act);
        return animationSimple;

    }

}
