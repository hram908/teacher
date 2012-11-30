package com.blacksheep.teacher.game.examtest;

import android.app.Activity;
import com.blacksheep.teacher.game.CheckInternet;
import com.blacksheep.teacher.game.animated_view.AnimationHead;
import com.blacksheep.teacher.game.animated_view.AnimationHeadForWait;
import com.blacksheep.teacher.game.animated_view.AnimationSimple;
import com.blacksheep.teacher.game.animated_view.AnimationSimpleBlaBla;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;
import com.blacksheep.teacher.model.dataEntity.DataAnimationToAudio;
import com.blacksheep.teacher.model.dataEntity.DataQuest;
import com.blacksheep.teacher.model.database.DBManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: vladimirmolodkin
 * Date: 17.07.12
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */
public class ManagerActions {

    private Activity activity;
    private AnimationHead animationHead;

    public void doLecture(int optionCode, final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_LECTURE,optionCode);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }

    public void doGreetingFirstPart1(final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_GREETING,DataAnimation.OPTION_CODE_GREETING_FIRST);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.animationSimples.remove(1);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }

    public void doGreetingFirstPartRecognizeSuccess(final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_GREETING,DataAnimation.OPTION_CODE_GREETING_FIRST);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.animationSimples.remove(0);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }


    public void doGreetingFirstPartRecognizeFail(final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_APPLICATION_EVENT,DataAnimation.OPTION_CODE_APPLICATION_EVENT_NAME_DIDNT_LISTEN_ON_FIRST_GREETING);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.animationSimples.remove(2);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }

    public void doGreetingSecond(final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_GREETING,DataAnimation.OPTION_CODE_GREETING_OTHER);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }

    public void doGreetingSecondPartRecognizeFail(final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_APPLICATION_EVENT,DataAnimation.OPTION_CODE_APPLICATION_EVENT_NAME_DIDNT_LISTEN_ON_FIRST_GREETING);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.animationSimples.remove(0);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }


    private ManagerPhrases managerPhrases;
    public static final int NAME_S=1;
    public static final int NAME_N=2;
    public static final int NAME_P=3;

    public ManagerActions(Activity activity) {
        this.activity = activity;
        managerPhrases = new ManagerPhrases("", activity);
    }

    public void generateAnimationOfflineInternet(final CheckInternet checkInternet, final IActionComplete iActionComplete) {

        DataAnimation dataAnimation =DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_APPLICATION_EVENT,DataAnimation.OPTION_CODE_APPLICATION_EVENT_NO_INTERNET);

        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
               // checkInternet.dialogAppear();
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }

    public interface IActionComplete {
        // public void onComplete(Object obj);
        public void onComplete();
    }

    public void doQuestionF4(final DataQuest curQuest, final IActionComplete iActionComplete,final AnimationSimple.OnCompletionAudioListener onCompletionAudioListener,final AnimationHead.OnAnimationQuestStartListener onAnimationQuestStartListener) {

       String op=DataManager.getOperation(curQuest.getContent().replace(" ", "").replace("=", ""));
        Random random= new Random();
        if(random.nextInt(100)<40)
        {
            DataAnimation dataAnimation=DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,-1);
            animationHead=generateAnimationHead(dataAnimation,NAME_S,"",op,activity);
            animationHead.setOnAnimationQuestStartListener(onAnimationQuestStartListener);
        }
        else
        {
            LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();
            AnimationSimple animationSimple = generateQuestAnim(activity,op);
            animationSimple.setAudioCompleate(true);
            animationSimples.add(animationSimple);
            animationHead = new AnimationHead(animationSimples,activity);
            onAnimationQuestStartListener.startQuest();
        }

        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.setOnCompletionAudioListener(onCompletionAudioListener);
         animationHead.start();
    }

    public void doResultQuestionOK(int count,final IActionComplete iActionComplete) {
        /*LinkedList<DataAnimation> dataAnimations1 = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation1 = new DataAnimation("13", DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST, 200);
        dataAnimations1.add(dataAnimation1);
        LinkedList<String> audio1 = new LinkedList<String>();
        audio1.add(DataManager.getPhrases("f-6-PP-1"));

        LinkedList<DataAnimation> dataAnimations2 = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation2 = new DataAnimation("18", DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST, 200);
        dataAnimations2.add(dataAnimation2);
        dataAnimations2.add(dataAnimation2);
        LinkedList<String> audio2 = new LinkedList<String>();
        audio2.add(managerPhrases.getNameNegative(0));

        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();

        if (!managerPhrases.getNameNegative(0).contains("-"))
            animationSimples.add(new AnimationSimpleBlaBla(audio2, activity));
        animationSimples.add(new AnimationSimple(dataAnimations1, audio1, activity));*/
        DataAnimation dataAnimation=null;
        if(count>0){
            dataAnimation=DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_RESPONSE,DataAnimation.OPTION_CODE_RESPONSE_TRUE_P);
            animationHead=generateAnimationHead(dataAnimation,NAME_P,"","",activity);
        }
        else {
            dataAnimation=DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_RESPONSE,DataAnimation.OPTION_CODE_RESPONSE_TRUE_S);
            animationHead=generateAnimationHead(dataAnimation,NAME_S,"","",activity);
        }

        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {

                iActionComplete.onComplete();

            }
        });
        animationHead.start();
    }

    public void doResultQuestionSilence(final IActionComplete iActionComplete) {
        /*LinkedList<DataAnimation> dataAnimations1 = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation1 = new DataAnimation("8", DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST, 200);
        dataAnimations1.add(dataAnimation1);
        LinkedList<String> audio1 = new LinkedList<String>();
        audio1.add(DataManager.getPhrases("f-5-NN-2"));

        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();


        animationSimples.add(new AnimationSimple(dataAnimations1, audio1, activity));*/
        DataAnimation dataAnimation=DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_RESPONSE,DataAnimation.OPTION_CODE_RESPONSE_SILENCE);
        animationHead = generateAnimationHead(dataAnimation, 0, "", "", activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {

                iActionComplete.onComplete();
            }
        });
        animationHead.start();

    }

    public void doResultQuestionInaccurately(final IActionComplete iActionComplete) {
        /*LinkedList<DataAnimation> dataAnimations1 = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation1 = new DataAnimation("10", DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST, 200);
        dataAnimations1.add(dataAnimation1);
        LinkedList<String> audio1 = new LinkedList<String>();
        audio1.add(DataManager.getPhrases("f-21-S-1"));
        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();
        animationSimples.add(new AnimationSimple(dataAnimations1, audio1, activity));*/
        DataAnimation dataAnimation=DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_RESPONSE,DataAnimation.OPTION_CODE_RESPONSE_INCORRECTLY);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {

                iActionComplete.onComplete();
            }
        });
        animationHead.start();

    }

    public void doResultQuestionWrong(int count,final IActionComplete iActionComplete) {
        /*LinkedList<DataAnimation> dataAnimations1 = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation1 = new DataAnimation("53", DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST, 200);
        dataAnimations1.add(dataAnimation1);
        LinkedList<String> audio1 = new LinkedList<String>();
        audio1.add(DataManager.getPhrases("f-7-N-1"));


        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();*/

        // if(!managerPhrases.getNameNegative(0).equals("-"))
        //animationSimples.add(new AnimationSimple(dataAnimations2,audio2,activity));
        /*animationSimples.add(new AnimationSimple(dataAnimations1, audio1, activity));*/
        DataAnimation dataAnimation=null;
        if(count>0){
            dataAnimation=DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_RESPONSE,DataAnimation.OPTION_CODE_RESPONSE_FALSE_N);
            animationHead = generateAnimationHead(dataAnimation,NAME_N,"","",activity) ;
        }
        else {
            dataAnimation=DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_RESPONSE,DataAnimation.OPTION_CODE_RESPONSE_FALSE_S);
            animationHead = generateAnimationHead(dataAnimation,NAME_P,"","",activity) ;
        }

        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {

                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }

    public void doRepeatQuestion(final IActionComplete iActionComplete) {
      /*  LinkedList<DataAnimation> dataAnimations1 = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation1 = new DataAnimation("18", DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST, 200);
        dataAnimations1.add(dataAnimation1);
        dataAnimations1.add(dataAnimation1);
        LinkedList<String> audio1 = new LinkedList<String>();
        audio1.add(DataManager.getPhrases("f-32-1"));


        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();

        // if(!managerPhrases.getNameNegative(0).equals("-"))
        //animationSimples.add(new AnimationSimple(dataAnimations2,audio2,activity));
        animationSimples.add(new AnimationSimple(dataAnimations1, audio1, activity));*/
        DataAnimation dataAnimation=DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_APPLICATION_EVENT,DataAnimation.OPTION_CODE_APPLICATION_EVENT_REPEAT_QUESTION);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();

    }

    public void doCorrectlyResult(String correctResult, final IActionComplete iActionComplete) {
//        LinkedList<DataAnimation> dataAnimations1 = new LinkedList<DataAnimation>();
//        DataAnimation dataAnimation1 = new DataAnimation("18", DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST, 200);
//        dataAnimations1.add(dataAnimation1);
//        dataAnimations1.add(dataAnimation1);
//        dataAnimations1.add(dataAnimation1);
//        LinkedList<String> audio1 = new LinkedList<String>();
//        audio1.add(DataManager.getPhrases("f-33-1"));
//
//        LinkedList<DataAnimation> dataAnimations2 = new LinkedList<DataAnimation>();
//        DataAnimation dataAnimation2 = new DataAnimation("18", DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST, 200);
//        dataAnimations2.add(dataAnimation2);
//        dataAnimations2.add(dataAnimation2);
//        LinkedList<String> audio2 = new LinkedList<String>();
//        audio2.add(DataManager.getCorrectResult(correctResult));
//
//        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();
//
//
//        animationSimples.add(new AnimationSimple(dataAnimations1, audio1, activity));
//        animationSimples.add(new AnimationSimple(dataAnimations2, audio2, activity));
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_APPLICATION_EVENT,DataAnimation.OPTION_CODE_APPLICATION_EVENT_SPEAK_CORRECT_ANSWER);
        animationHead = generateAnimationHead(dataAnimation,0,correctResult,"",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }

    public void doEndLessonSimple( final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_LESSON_END,DataAnimation.OPTION_CODE_LESSON_END_SIMPLE_END);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }


    public void doBonus( final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_APPLICATION_EVENT,DataAnimation.OPTION_CODE_APPLICATION_EVENT_BONUS);

        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }

    public void doStartExam(int optionCode,final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_START_EXAM,optionCode);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }

    public void doEndExam(int optionCode,final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_END_EXAM,optionCode);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }


    public void doInformationClearlyOneNumber(final IActionComplete iActionComplete) {
        /*LinkedList<DataAnimation> dataAnimations1 = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation1 = new DataAnimation("18", DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST, 200);
        dataAnimations1.add(dataAnimation1);
        dataAnimations1.add(dataAnimation1);
        dataAnimations1.add(dataAnimation1);
        LinkedList<String> audio1 = new LinkedList<String>();
        audio1.add(DataManager.getPhrases("f-3-1"));


        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();


        animationSimples.add(new AnimationSimple(dataAnimations1, audio1, activity));*/
        DataAnimation dataAnimation=DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_APPLICATION_EVENT,DataAnimation.OPTION_CODE_APPLICATION_EVENT_ANSWER_CLEARLY);
        animationHead=generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }
    public void doTestResult(int count,final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation=DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_LESSON_RESULT,count);
        animationHead=generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();

    }

    public void doWaitShortBegin(final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_WAIT_RESPONSE,DataAnimation.OPTION_CODE_WAIT_RESPONSE_SHORT);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.animationSimples.remove(1);
        animationHead.animationSimples.remove(1);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }
    public void doWaitShortEnd(final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_WAIT_RESPONSE,DataAnimation.OPTION_CODE_WAIT_RESPONSE_SHORT);
        animationHead = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead.animationSimples.remove(0);
        animationHead.animationSimples.remove(0);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }

    public void doWaitLong(final IActionComplete iActionComplete)
    {
        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_WAIT_RESPONSE,DataAnimation.OPTION_CODE_WAIT_RESPONSE_LONG);

        AnimationHead animationHead1 = generateAnimationHead(dataAnimation,0,"","",activity);
        animationHead  = new AnimationHeadForWait(animationHead1.animationSimples,activity);
        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
            @Override
            public void onCompletion(AnimationHead animationHead) {
                iActionComplete.onComplete();
            }
        });
        animationHead.start();
    }

    public static AnimationHead generateAnimationHead(DataAnimation dataAnimation, int count, String answer, String quest, Activity act) {


        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();

        List<DataAnimationToAudio> audios = DBManager.getInstance().getAudiosByAnimationID(dataAnimation.getId());

        String[] animArray = dataAnimation.getPattern().split(";");
        String[] chronometryArray = dataAnimation.getChronometry().split(";");
        boolean isChrono = chronometryArray[0].equals("");
        int [] countAnim = new int[]{0};
        int countAudio =0;



        for (String i : animArray) {
            if (i.equals(DataAnimation.PATTERN_ANIMATION)) {

                LinkedList<String> audiosCur = new LinkedList<String>();

                AnimationSimple animationSimple = null;
                if (!isChrono) {
                    int numberUseParamChrono = countAnim[0] / 2;
                    int start = 0;
                    int end = dataAnimation.getFrameCount();
                    if (countAnim[0] % 2 == 0) {
                        if (numberUseParamChrono != 0) {
                            start = Integer.parseInt(chronometryArray[numberUseParamChrono-1]);
                        }
                        end = Integer.parseInt(chronometryArray[numberUseParamChrono]);
                    } else {
                        if (numberUseParamChrono + 1 != chronometryArray.length) {
                            end = Integer.parseInt(chronometryArray[numberUseParamChrono + 1]);
                        }
                        start = Integer.parseInt(chronometryArray[numberUseParamChrono]);
                        countAnim[0]++;
                    }

                    if(audios.size()!=0)
                        audiosCur.add(DataManager.getPhrasesNotExt(audios.get(countAudio).getAudio_file_name()));

                    animationSimple = generateAnimAnim(dataAnimation, start, end, act, audiosCur);

                } else {
                    if(audios.size()!=0)
                        audiosCur.add(DataManager.getPhrasesNotExt(audios.get(0).getAudio_file_name()));
                    animationSimple = generateAnimAnim(dataAnimation, 0, dataAnimation.getFrameCount(), act, audiosCur);
                }
                animationSimples.add(animationSimple);
                countAudio++;
                countAnim[0]++;
            } else if (i.equals(DataAnimation.PATTERN_NAME)) {
                AnimationSimple animationSimple = generateNameAnim(count, act);
                animationSimples.add(animationSimple);
            } else if (i.equals(DataAnimation.PATTERN_ANSWER)) {
                AnimationSimple animationSimple = generateAnswerAnim(act, answer);
                animationSimples.add(animationSimple);
            } else if ((i.equals(DataAnimation.PATTERN_QUEST))) {
                AnimationSimple animationSimple = generateQuestAnim(act, quest);
                animationSimple.setQuestion(true);
                animationSimples.add(animationSimple);
            } else if ((i.equals(DataAnimation.PATTERN_BEGIN_ACTION))) {
                AnimationSimple animationSimple = null;
                GenerateStartAndEndFrameForAnimWaut generateStartAndEndFrameForAnimWaut = new GenerateStartAndEndFrameForAnimWaut(dataAnimation, chronometryArray, countAnim).invoke();
                int start = generateStartAndEndFrameForAnimWaut.getStart();
                int end = generateStartAndEndFrameForAnimWaut.getEnd();
                animationSimple = generateBeginAction(dataAnimation, start, end, act);
                animationSimples.add(animationSimple);
                countAnim[0]++;
                countAudio++;
            }else if ((i.equals(DataAnimation.PATTERN_ACTION))) {
                AnimationSimple animationSimple = null;
                GenerateStartAndEndFrameForAnimWaut generateStartAndEndFrameForAnimWaut = new GenerateStartAndEndFrameForAnimWaut(dataAnimation, chronometryArray, countAnim).invoke();
                int start = generateStartAndEndFrameForAnimWaut.getStart();
                int end = generateStartAndEndFrameForAnimWaut.getEnd();
                animationSimple = generateAction(dataAnimation, start, end, act);
                animationSimples.add(animationSimple);
                countAnim[0]++;
                countAudio++;
            }else if ((i.equals(DataAnimation.PATTERN_END_ACTION))) {
                AnimationSimple animationSimple = null;
                GenerateStartAndEndFrameForAnimWaut generateStartAndEndFrameForAnimWaut = new GenerateStartAndEndFrameForAnimWaut(dataAnimation, chronometryArray, countAnim).invoke();
                int start = generateStartAndEndFrameForAnimWaut.getStart();
                int end = generateStartAndEndFrameForAnimWaut.getEnd();
                animationSimple = generateEndAction(dataAnimation, start, end, act);
                animationSimples.add(animationSimple);
                countAnim[0]++;
                countAudio++;
            }

        }

        AnimationHead animationHead = new AnimationHead(animationSimples, act);
        return animationHead;
    }
    public static AnimationHead generateAnimationHeadTest(DataAnimation dataAnimation, int count, String answer, String quest, Activity act,String chrono,int startFrame) {


        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();

        List<DataAnimationToAudio> audios = DBManager.getInstance().getAudiosByAnimationID(dataAnimation.getId());

        String[] animArray = dataAnimation.getPattern().split(";");
        String[] chronometryArray = dataAnimation.getChronometry().split(";");
        if(!chrono.equals("-1"))
            chronometryArray[0]=chrono;
        if(startFrame!=-1)
            dataAnimation.setStartAudioFrame(startFrame);
        boolean isChrono = chronometryArray[0].equals("");
        int [] countAnim = new int[]{0};
        int countAudio =0;



        for (String i : animArray) {
            if (i.equals(DataAnimation.PATTERN_ANIMATION)) {

                LinkedList<String> audiosCur = new LinkedList<String>();

                AnimationSimple animationSimple = null;
                if (!isChrono) {
                    int numberUseParamChrono = countAnim[0] / 2;
                    int start = 0;
                    int end = dataAnimation.getFrameCount();
                    if (countAnim[0] % 2 == 0) {
                        if (numberUseParamChrono != 0) {
                            start = Integer.parseInt(chronometryArray[numberUseParamChrono-1]);
                        }
                        end = Integer.parseInt(chronometryArray[numberUseParamChrono]);
                    } else {
                        if (numberUseParamChrono + 1 != chronometryArray.length) {
                            end = Integer.parseInt(chronometryArray[numberUseParamChrono + 1]);
                        }
                        start = Integer.parseInt(chronometryArray[numberUseParamChrono]);
                        countAnim[0]++;
                    }

                    if(audios.size()!=0)
                        audiosCur.add(DataManager.getPhrasesNotExt(audios.get(countAudio).getAudio_file_name()));

                    animationSimple = generateAnimAnim(dataAnimation, start, end, act, audiosCur);

                } else {
                    if(audios.size()!=0)
                        audiosCur.add(DataManager.getPhrasesNotExt(audios.get(0).getAudio_file_name()));
                    animationSimple = generateAnimAnim(dataAnimation, 0, dataAnimation.getFrameCount(), act, audiosCur);
                }
                animationSimples.add(animationSimple);
                countAudio++;
                countAnim[0]++;
            } else if (i.equals(DataAnimation.PATTERN_NAME)) {
                AnimationSimple animationSimple = generateNameAnim(count, act);
                animationSimples.add(animationSimple);
            } else if (i.equals(DataAnimation.PATTERN_ANSWER)) {
                AnimationSimple animationSimple = generateAnswerAnim(act, answer);
                animationSimples.add(animationSimple);
            } else if ((i.equals(DataAnimation.PATTERN_QUEST))) {
                AnimationSimple animationSimple = generateQuestAnim(act, quest);
                animationSimples.add(animationSimple);
            } else if ((i.equals(DataAnimation.PATTERN_BEGIN_ACTION))) {
                AnimationSimple animationSimple = null;
                GenerateStartAndEndFrameForAnimWaut generateStartAndEndFrameForAnimWaut = new GenerateStartAndEndFrameForAnimWaut(dataAnimation, chronometryArray, countAnim).invoke();
                int start = generateStartAndEndFrameForAnimWaut.getStart();
                int end = generateStartAndEndFrameForAnimWaut.getEnd();
                animationSimple = generateBeginAction(dataAnimation, start, end, act);
                animationSimples.add(animationSimple);
                countAnim[0]++;
                countAudio++;
            }else if ((i.equals(DataAnimation.PATTERN_ACTION))) {
                AnimationSimple animationSimple = null;
                GenerateStartAndEndFrameForAnimWaut generateStartAndEndFrameForAnimWaut = new GenerateStartAndEndFrameForAnimWaut(dataAnimation, chronometryArray, countAnim).invoke();
                int start = generateStartAndEndFrameForAnimWaut.getStart();
                int end = generateStartAndEndFrameForAnimWaut.getEnd();
                animationSimple = generateAction(dataAnimation, start, end, act);
                animationSimples.add(animationSimple);
                countAnim[0]++;
                countAudio++;
            }else if ((i.equals(DataAnimation.PATTERN_END_ACTION))) {
                AnimationSimple animationSimple = null;
                GenerateStartAndEndFrameForAnimWaut generateStartAndEndFrameForAnimWaut = new GenerateStartAndEndFrameForAnimWaut(dataAnimation, chronometryArray, countAnim).invoke();
                int start = generateStartAndEndFrameForAnimWaut.getStart();
                int end = generateStartAndEndFrameForAnimWaut.getEnd();
                animationSimple = generateEndAction(dataAnimation, start, end, act);
                animationSimples.add(animationSimple);
                countAnim[0]++;
                countAudio++;
            }

        }

        AnimationHead animationHead = new AnimationHead(animationSimples, act);
        return animationHead;
    }

    private static AnimationSimple generateNameAnim(int number, Activity act) {
        ManagerPhrases  managerPhrases1 = new ManagerPhrases("",act);
        String name= managerPhrases1.getName(number);
        LinkedList<String> audiosCur = new LinkedList<String>();
        //audiosCur.add(DataManager.getNameTest("1p_tarasik"));
        audiosCur.add(name);
        AnimationSimpleBlaBla animationSimpleBlaBla = new AnimationSimpleBlaBla(audiosCur, act);
        return animationSimpleBlaBla;
    }

    private static AnimationSimple generateAnswerAnim(Activity act, String answer) {
        LinkedList<String> audiosCur = new LinkedList<String>();
        audiosCur.add(DataManager.getCorrectResult(answer));
        AnimationSimpleBlaBla animationSimpleBlaBla = new AnimationSimpleBlaBla(audiosCur, act);
        return animationSimpleBlaBla;
    }

    private static AnimationSimple generateQuestAnim(Activity act, String quest) {
        LinkedList<String> audiosCur = new LinkedList<String>();
        audiosCur.add(quest);
        AnimationSimpleBlaBla animationSimpleBlaBla = new AnimationSimpleBlaBla(audiosCur, act);
        animationSimpleBlaBla.setAudioCompleate(true);
        return animationSimpleBlaBla;
    }

    private static AnimationSimple generateAnimAnim(DataAnimation dataAnimation, int startFrame, int endFrame, Activity act, LinkedList<String> audiosCur) {
        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation1;
        try {
            dataAnimation1 = (DataAnimation) dataAnimation.clone();
            if(startFrame!=0)
                dataAnimation1.setStartAudioFrame(0);
        } catch (CloneNotSupportedException e) {
            dataAnimation1 = dataAnimation;
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        dataAnimation1.setStartFrameAnim(startFrame);
        dataAnimation1.setEndFrameAnim(endFrame);

        dataAnimations.add(dataAnimation1);
        AnimationSimple animationSimple = new AnimationSimple(dataAnimations, audiosCur, act);
        return animationSimple;
    }

    private static AnimationSimple generateBeginAction(DataAnimation dataAnimation, int startFrame, int endFrame, Activity act) {
        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation1;
        try {
            dataAnimation1 = (DataAnimation) dataAnimation.clone();
        } catch (CloneNotSupportedException e) {
            dataAnimation1 = dataAnimation;
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        dataAnimation1.setStartFrameAnim(startFrame);
        dataAnimation1.setEndFrameAnim(endFrame);

        dataAnimations.add(dataAnimation1);
        AnimationSimple animationSimple = new AnimationSimple(dataAnimations, new LinkedList<String>(), act);
        return animationSimple;
    }

    private static AnimationSimple generateAction(DataAnimation dataAnimation, int startFrame, int endFrame, Activity act) {
        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation1;
        try {
            dataAnimation1 = (DataAnimation) dataAnimation.clone();
        } catch (CloneNotSupportedException e) {
            dataAnimation1 = dataAnimation;
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        dataAnimation1.setStartFrameAnim(startFrame);
        dataAnimation1.setEndFrameAnim(endFrame);

        dataAnimations.add(dataAnimation1);
        AnimationSimple animationSimple = new AnimationSimple(dataAnimations, new LinkedList<String>(), act);
        return animationSimple;
    }

    private static AnimationSimple generateEndAction(DataAnimation dataAnimation, int startFrame, int endFrame, Activity act) {
        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
        DataAnimation dataAnimation1;
        try {
            dataAnimation1 = (DataAnimation) dataAnimation.clone();
        } catch (CloneNotSupportedException e) {
            dataAnimation1 = dataAnimation;
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        dataAnimation1.setStartFrameAnim(startFrame);
        dataAnimation1.setEndFrameAnim(endFrame);

        dataAnimations.add(dataAnimation1);
        AnimationSimple animationSimple = new AnimationSimple(dataAnimations, new LinkedList<String>(), act);
        animationSimple.setForWaitStart(true);
        return animationSimple;
    }

    private static class GenerateStartAndEndFrameForAnimWaut {
        private DataAnimation dataAnimation;
        private String[] chronometryArray;
        private int [] countAnim;
        private int start;
        private int end;

        public GenerateStartAndEndFrameForAnimWaut(DataAnimation dataAnimation, String[] chronometryArray, int [] countAnim) {
            this.dataAnimation = dataAnimation;
            this.chronometryArray = chronometryArray;
            this.countAnim = countAnim;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public GenerateStartAndEndFrameForAnimWaut invoke() {
            int numberUseParamChrono = countAnim[0] / 2;
            start = 0;
            end = dataAnimation.getFrameCount();
            if (countAnim[0] % 2 == 0) {
                if (numberUseParamChrono != 0) {
                    start = Integer.parseInt(chronometryArray[numberUseParamChrono-1]);
                }
                end = Integer.parseInt(chronometryArray[numberUseParamChrono]);
            } else {
                if (numberUseParamChrono + 1 != chronometryArray.length) {
                    end = Integer.parseInt(chronometryArray[numberUseParamChrono + 1]);
                }
                start = Integer.parseInt(chronometryArray[numberUseParamChrono]);
                countAnim[0]++;

            }
            return this;
        }
    }
}
