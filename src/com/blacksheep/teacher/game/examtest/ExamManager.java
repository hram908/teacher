package com.blacksheep.teacher.game.examtest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.activites.setting.AcSetting;
import com.blacksheep.teacher.dialogs.DialogOfflineInternet;
import com.blacksheep.teacher.dialogs.DialogRequestName;
import com.blacksheep.teacher.game.CheckInternet;
import com.blacksheep.teacher.game.animated_view.AnimationHead;
import com.blacksheep.teacher.game.animated_view.AnimationSimple;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.LogTeach;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;
import com.blacksheep.teacher.model.dataEntity.DataQuest;
import com.blacksheep.teacher.model.dataEntity.DataTest;
import com.blacksheep.teacher.model.database.DBManager;
import com.blacksheep.teacher.nuanceApp.RecognizeTest;
import com.blacksheep.teacher.nuanceApp.RecognizeWord;
import com.blacksheep.teacher.nuanceApp.ResponceRecognize;
import com.testsocial.AcGame;

import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/13/12
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExamManager {

    public static String IS_FINISH_EXAM_1="IsFinishExam1";
    public static String IS_FINISH_EXAM_2="IsFinishExam2";
    public static String IS_FINISH_EXAM_3="IsFinishExam3";
    private TestManager.OnCompletionListener onCompletionListener;
    private TestManager.OnNuanceResponse onNuanceResponse;

    private static final String FIRST_GREETING = "FIRST_GREETING";

    private Activity activity;
    private LinearLayout ll_board;
    LinearLayout curLinearLayout;
    TextView curTextView;
    int max_component = 5;
    int countExaple = 0;
    private int coutQuestion;

    DataTest dataTest;
    ManagerPhrases managerPhrases;
    private Activity mContext;
    private boolean examFail;


    private int countI;



    private int level;
    public boolean isWork;
    private ManagerActions managerActions;
    public boolean isShownQuestHelp;
    TestManager.OnQuestHelpAction onQuestHelpAction;

    public ExamManager(int coutQuestion, int level, Activity context,TestManager.OnQuestHelpAction onQuestHelpAction) {
        this.coutQuestion = coutQuestion;
        this.level = level;
        this.mContext = context;
        this.onQuestHelpAction=onQuestHelpAction;
        managerActions = new ManagerActions(context);
    }

    private DataTest generateTest() {
        DataTest test = new DataTest();
        LinkedList<DataQuest> dataQuests = new LinkedList<DataQuest>(DBManager.getInstance().getQuestionByLevel(coutQuestion, level));
        test.setListQuests(dataQuests);
        return test;
    }


    public void start() {
        isShownQuestHelp=!MyApplication.getInstance().getPrefs().getBoolean(AcSetting.SETTING_QUEST_HELP,true);
        DBManager.testAnim = true;
        dataTest = generateTest();
        managerPhrases = new ManagerPhrases(DataManager.getPhrasesDir(), activity);
        countI = 0;
      //  responseListener = new ResponseListener();
        isWork = true;

        //firstBlock();
        examGreeting(level);

    }

    private boolean checkFirstGreeting(int level) {
        SharedPreferences sPreferences = MyApplication.getInstance().getPrefs();
        if (sPreferences.getInt(FIRST_GREETING, -1) != level) {
            return true;
        }
        return false;

    }

    private void examGreeting(int level) {
        int option = -1;
        if (checkFirstGreeting(level)) {
            switch (level) {
                case 0: {
                    option = DataAnimation.OPTION_CODE_START_EXAM_FIRST_1;
                    break;
                }
                case 1: {
                    option = DataAnimation.OPTION_CODE_START_EXAM_FIRST_2;
                    break;
                }
                case 2: {
                    option = DataAnimation.OPTION_CODE_START_EXAM_FIRST_3;
                    break;
                }
            }
        } else {
            option = DataAnimation.OPTION_CODE_START_EXAM_SECOND;
        }

        managerActions.doStartExam(option, new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                firstBlock();
            }
        });
    }

    private boolean repeatQuestion=false;

    public void resume() {

        if(countI==-1)
            return;

        final CheckInternet checkInternet=new CheckInternet(mContext);
        if(checkInternet.isOnline()){
            isWork=true;
            DBManager.testAnim=true;
            if(repeatQuestion)
            {
                repeatQuestion=false;
                doQuestionF4(countI);

            }
            else
            {
                try{
                    AudioPhraseBuilder.mediaPlayerHead.start();
                }
                catch (Exception e)
                {
                    return;
                }
            }

        }
        else
        {
            managerActions.generateAnimationOfflineInternet(checkInternet,new ManagerActions.IActionComplete() {
                @Override
                public void onComplete() {
                    checkInternet.dialogAppear();
                }
            });

        }
    }

    public void pause() {

        isWork=false;
        DBManager.testAnim=false;
        if(RecognizeRequest.listenResponse)
        {
            stopRecognize();
            repeatQuestion=true;
        }
        else
        {
            try{
                AudioPhraseBuilder.mediaPlayerHead.pause();
            }
            catch (Exception e)
            {
                return;
            }
        }

    }


    private void firstBlock() {
        //CheckInternet checkInternet = new CheckInternet(mContext);
       // if (checkInternet.isOnline()) {
            addContentToBoard(dataTest.getListQuests().get(countI).getContent());
            doQuestionF4(countI);
       /* } else {
            generateAnimationOfflineInternet(checkInternet);
        }*/
    }


    private void generateAnimationOfflineInternet(final CheckInternet checkInternet) {
        managerActions.generateAnimationOfflineInternet(checkInternet, new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                checkInternet.dialogAppear();
            }
        });
    }

    RecognizeRequest recognizeRequest;

    private void doQuestionF4(final int numberQuestion) {
        final DataQuest curQuest = dataTest.getListQuests().get(numberQuestion);
        managerActions.doQuestionF4(curQuest, new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
//                recognizeRequest = new RecognizeRequest();
//                recognizeRequest.startRecognize(Integer.valueOf(curQuest.getAnswer()),new RecognizeTest(),activity);
//                recognizeRequest.setCompleteRecognize(new RecognizeRequest.CompleteRecognize() {
//                    @Override
//                    public void onRecognizeComplete(ResponceRecognize responceRecognize) {
//                        if(onNuanceResponse!=null)
//                            onNuanceResponse.response(responceRecognize.getText());
//                        doResponseByQuestion(responceRecognize.getKey());
//                    }
//                });
                final ResponceRecognize resp = RecognizeRequest.getRespRecognize();
                if (resp != null) {
                    if(recognizeRequest!=null)
                        recognizeRequest.stopRecognize();
                    doResponseByQuestion(resp.getKey(),resp.getText());
                } else  {
                    doWaitShort();
                }
            }
        },new AnimationSimple.OnCompletionAudioListener() {
                    @Override
                    public void onCompletionAudio() {
                        if(! RecognizeRequest.isResponseRecognizeNull())
                            return;

                        Log.i(getClass().getName(),"microfon start");
                        recognizeRequest = new RecognizeRequest();
                        recognizeRequest.startRecognize(Integer.valueOf(curQuest.getAnswer()), new RecognizeTest(), activity);
//                        recognizeRequest.setCompleteRecognize(new RecognizeRequest.CompleteRecognize() {
//                            @Override
//                            public void onRecognizeComplete(ResponceRecognize responceRecognize) {
//                                if (onNuanceResponse != null)
//                                    onNuanceResponse.response(responceRecognize.getText());
//                                doResponseByQuestion(responceRecognize.getKey());
//                            }
//                        });
                        LogTeach.startTime = new Timestamp(System.currentTimeMillis());
                    }
                },new AnimationHead.OnAnimationQuestStartListener() {
                    @Override
                    public void startQuest() {
                        if (!isShownQuestHelp)
                        {
                            onQuestHelpAction.show(dataTest.getListQuests().get(countI).getContent());
                            RecognizeRequest.startListenResponse();
                        }
                    }
                });
    }

    boolean isReadyListenerFirst=false;
    private void doWaitShort()
    {
        isReadyListenerFirst=false;
        managerActions.doWaitShortBegin(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {

                final ResponceRecognize resp = RecognizeRequest.getRespRecognize();
                if (resp != null) {
                    managerActions.doWaitShortEnd(new ManagerActions.IActionComplete() {
                        @Override
                        public void onComplete() {
                            doResponseByQuestion(resp.getKey(),resp.getText());
                        }
                    });

                } else {

                    RecognizeRequest.setResponseReadyListener(new RecognizeRequest.ResponseReadyListener() {
                        @Override
                        public void ready() {
                            isReadyListenerFirst = true;
                            managerActions.doWaitShortEnd(new ManagerActions.IActionComplete() {
                                @Override
                                public void onComplete() {
                                    final ResponceRecognize resp2 = RecognizeRequest.getRespRecognize();
                                    doResponseByQuestion(resp2.getKey(),resp2.getText());

                                }
                            });
                        }
                    });
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                            if (isReadyListenerFirst)
                                return;
                            else {
                                RecognizeRequest.setResponseReadyListener(null);
                            }
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    doWaitLong();
//                                    managerActions.doWaitShortEnd(new ManagerActions.IActionComplete() {
//                                        @Override
//                                        public void onComplete() {
//                                            final ResponceRecognize resp2 = RecognizeRequest.getRespRecognize();
//                                            if (resp2 != null) {
//                                                doResponseByQuestion(resp2.getKey());
//                                            } else
//                                                doWaitLong();
//                                        }
//                                    });
                                }
                            });
                        }
                    }).start();
                }
            }
        });
    }
    private void doWaitLong()
    {
        managerActions.doWaitLong(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                ResponceRecognize resp = RecognizeRequest.getRespRecognize();
                if (resp != null) {
                    doResponseByQuestion(resp.getKey(),resp.getText());
                } else {
                    doResponseByQuestion(ResponceRecognize.KEY_RESPONSE_INCORRECTLY,"");
                }
            }
        });
    }


    private void doResponseByQuestion(int responseCode,String text) {
        RecognizeRequest.setNullRespRecognizeHard();
        RecognizeRequest.setResponseReadyListener(null);
        onQuestHelpAction.hide();
        if(onNuanceResponse!=null)
            onNuanceResponse.response(text);
        switch (responseCode) {
            case ResponceRecognize.KEY_RESPONSE_CORRECTLY: {

                managerActions.doResultQuestionOK(countI, new ManagerActions.IActionComplete() {
                    @Override
                    public void onComplete() {
                        checkLastQuestion();
                    }
                });
                break;
            }

            case ResponceRecognize.KEY_RESPONSE_SILENCE: {
                managerActions.doResultQuestionSilence(new ManagerActions.IActionComplete() {
                    @Override
                    public void onComplete() {
                        doInformationСlearlyOneNumber();
                    }
                });
                break;
            }

            case ResponceRecognize.KEY_RESPONSE_INCORRECTLY: {

                managerActions.doResultQuestionInaccurately(new ManagerActions.IActionComplete() {
                    @Override
                    public void onComplete() {
                        doInformationСlearlyOneNumber();
                    }
                });
                break;
            }

            case ResponceRecognize.KEY_RESPONSE_WRONG: {

                examFail = true;
                managerActions.doResultQuestionWrong(countI, new ManagerActions.IActionComplete() {
                    @Override
                    public void onComplete() {
                        checkLastQuestion();
                    }
                });
                break;
            }
            case ResponceRecognize.KEY_RESPONSE_NOT_STANDARD_BAD: {
                doRepeatQuestionF32();
                break;
            }
            case ResponceRecognize.KEY_NOT_INTERNET_ACCESS: {
                final CheckInternet checkInternet=new CheckInternet(activity);

                ManagerActions managerActions = new ManagerActions(activity);
                managerActions.generateAnimationOfflineInternet(checkInternet,new ManagerActions.IActionComplete() {
                    @Override
                    public void onComplete() {
                        checkInternet.dialogAppear();
                        checkInternet.getDialog().setiButtonOk(new DialogOfflineInternet.IButtonOk() {
                            @Override
                            public void ok() {
                                onCompletionListener.onCompletion();
                            }
                        });
                    }
                });
                break;
            }
            default:
            {
                doRepeatQuestionF32();
                break;
            }
        }
    }

    private void doRepeatQuestionF32() {

        managerActions.doRepeatQuestion(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                doQuestionF4(countI);
            }
        });
    }

    private void doCorrectlyResultF33(String correctResult) {

        managerActions.doCorrectlyResult(correctResult, new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                checkLastQuestion();
            }
        });
    }

    private void doInformationСlearlyOneNumber() {
        managerActions.doInformationClearlyOneNumber(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                doRepeatQuestionF32();
            }
        });
    }


    public void addContentToBoard(String text) {
        curTextView = new TextView(activity);
        curTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, MyApplication.instance.getTextSize());
        curTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/segoepr.ttf"));
        curTextView.setPadding(0, 0, 45, 0);
        curTextView.setText(text + "?");
        curTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        if (countExaple >= max_component) {
            //if (countI > max_component + 1)
            if(ll_board.getChildCount()==2)
                clearBoard();
            curLinearLayout = layoutBuilder();
            ll_board.addView(curLinearLayout);
            countExaple = 0;
        }
        curLinearLayout.addView(curTextView);
        countExaple++;

    }

    private void clearBoard() {
        ll_board.removeViewAt(0);
    }

    private LinearLayout layoutBuilder() {
        LinearLayout linearLayout1 = new LinearLayout(activity);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 4;
        layoutParams.rightMargin = 4;
        linearLayout1.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout1.setLayoutParams(layoutParams);
        return linearLayout1;
    }

    private void checkLastQuestion() {
        setTextBoxResult();
        //if (countI == dataTest.getListQuests().size() - 1) {
        if (countI == 0) {
            endExam();
            return;
        }

        setTextBoxResult();
        countI++;
        firstBlock();
    }

    private void endExam() {
        countI = -1;
        SharedPreferences.Editor sE = MyApplication.getInstance().getPrefs().edit();
        sE.putInt(FIRST_GREETING, level);
        sE.commit();
        if (examFail) {
            managerActions.doEndExam(DataAnimation.OPTION_CODE_END_EXAM_FAIL, new ManagerActions.IActionComplete() {
                @Override
                public void onComplete() {
                        activity.finish();
                }
            });
        } else {
            int option = -1;

            SharedPreferences.Editor stats_editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
            switch (level) {
                case 0: {
                    option = DataAnimation.OPTION_CODE_END_EXAM_1;
                    stats_editor.putBoolean(IS_FINISH_EXAM_1,true);
                    stats_editor.commit();
                    break;
                }
                case 1: {
                    option = DataAnimation.OPTION_CODE_END_EXAM_2;
                    stats_editor.putBoolean(IS_FINISH_EXAM_2,true);
                    stats_editor.commit();
                    break;
                }
                case 2: {
                    option = DataAnimation.OPTION_CODE_END_EXAM_3;
                    stats_editor.putBoolean(IS_FINISH_EXAM_3,true);
                    stats_editor.commit();
                    break;
                }
            }

            managerActions.doEndExam(option, new ManagerActions.IActionComplete() {
                @Override
                public void onComplete() {
                    if(onCompletionListener!=null)
                        onCompletionListener.onCompletion();

                    switch (level) {
                        case 0: {
                            activity.finish();
                            activity.startActivity(new Intent(mContext, AcGame.class));
                            break;
                        }
                        case 1: {
                           SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
                            final String word=AcGame.getListWords().get(preferences.getInt(AcGame.COUNT_WORD,0));
                            RecognizeRequest recognizeRequest = new RecognizeRequest();
                            recognizeRequest.startRecognize(word,new RecognizeWord(),activity);
                            recognizeRequest.setCompleteRecognize(new RecognizeRequest.CompleteRecognize() {
                                @Override
                                public void onRecognizeComplete(ResponceRecognize responceRecognize) {
                                    doResponseByWord(responceRecognize.getKey(), word);
                                }
                            });
                            //doWaitShort(word);
                          //  responseListener.setListenResponseWord(word);
                            break;
                        }
                        case 2: {
                             //отправка диплома
                            break;
                        }
                    }
                }
            });
        }
    }

//    private void doWaitShort(final String exp)
//    {
//        managerActions.doWaitShort(new ManagerActions.IActionComplete() {
//            @Override
//            public void onComplete() {
//                ResponceRecognize resp = RecognizeRequest.getRespRecognize();
//                if(resp!=null)
//                {
//                   doResponseByWord(resp.getKey(),exp);
//                }
//                else
//                {
//                    doWaitLong(exp);
//                }
//            }
//        });
//    }
    private void doWaitLong(final String exp)
    {
        managerActions.doWaitLong(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                ResponceRecognize resp = RecognizeRequest.getRespRecognize();
                if(resp!=null)
                {
                    doResponseByWord(resp.getKey(), exp);
                }
                else
                {
                    doResponseByWord(500,"");
                }
            }
        });
    }


    private void doResponseByWord(int code, final String expecktedValue)
    {
         if(code==ResponceRecognize.KEY_WORD_FOUND) {
             setCountWord();
             doBonus();

         }
        else {
             managerActions.doGreetingFirstPartRecognizeFail(new ManagerActions.IActionComplete() {
                 @Override
                 public void onComplete() {
                     DialogRequestName dialogRequestName=new DialogRequestName(mContext,new DialogRequestName.ResponceListener() {
                         @Override
                         public void onClick(String result) {
                             if(result.equals(expecktedValue)){
                                 setCountWord();
                                 doBonus();
                             }
                             else activity.finish();
                         }
                     },activity);
                     //
                     dialogRequestName.show();
                     dialogRequestName.setHint("Введите кодовое слово");
                 }
             });

         }
    }

    private void doBonus()
    {
        managerActions.doBonus(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                activity.finish();
            }
        });
    }

    private void setTextBoxResult() {
        String text = curTextView.getText().toString();
        text = text.replace("?", dataTest.getListQuests().get(countI).getAnswer());
        curTextView.setText(text);
    }

    public void setLl_board(LinearLayout ll_board) {
        this.ll_board = ll_board;
        curLinearLayout = layoutBuilder();
        this.ll_board.addView(curLinearLayout);
    }


    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void stopRecognize() {
        if (recognizeRequest != null)
            recognizeRequest.stopRecognize();
    }

   /* public void testWord()
    {   SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
        responseListener=new ResponseListener();
        String word=AcGame.getListWords().get(preferences.getInt(AcGame.COUNT_WORD,0));
        responseListener.setListenResponseWord(word);
    }*/
    private void setCountWord(){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
        SharedPreferences.Editor stats_editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
        int currentWord=preferences.getInt(AcGame.COUNT_WORD,0);
        if(currentWord<AcGame.COUNT_WORDS-1){
            stats_editor.putInt(AcGame.COUNT_WORD,currentWord+1);
        }
        else {
            stats_editor.putInt(AcGame.COUNT_WORD,0);
        }
        stats_editor.commit();
    }

    public void setOnCompletionListener(TestManager.OnCompletionListener onCompletionListener) {
        this.onCompletionListener = onCompletionListener;
    }

    public void setOnNuanceResponse(TestManager.OnNuanceResponse onNuanceResponse) {
        this.onNuanceResponse = onNuanceResponse;
    }


    public void clickHelpQuestion(View v) {
        TextView currTextView=(TextView)v;
        int value=Integer.valueOf(currTextView.getText().toString());
        if (value==Integer.valueOf(dataTest.getListQuests().get(countI).getAnswer())){
            onQuestHelpAction.setBg(v);
            ResponceRecognize responceRecognize = new ResponceRecognize();
            responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_CORRECTLY);
            RecognizeRequest.setRespRecognize(responceRecognize);
        }
        else {
            onQuestHelpAction.setBg(v);
            ResponceRecognize responceRecognize = new ResponceRecognize();
            responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_WRONG);
            RecognizeRequest.setRespRecognize(responceRecognize);
        }


    }
}
