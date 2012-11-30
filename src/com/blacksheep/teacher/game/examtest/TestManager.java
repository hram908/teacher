package com.blacksheep.teacher.game.examtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.activites.setting.AcSetting;
import com.blacksheep.teacher.dialogs.DialogOfflineInternet;
import com.blacksheep.teacher.game.CheckInternet;
import com.blacksheep.teacher.game.animated_view.AnimationHead;
import com.blacksheep.teacher.game.animated_view.AnimationSimple;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.LogTeach;
import com.blacksheep.teacher.model.dataEntity.DataQuest;
import com.blacksheep.teacher.model.dataEntity.DataTest;
import com.blacksheep.teacher.model.database.DBManager;
import com.blacksheep.teacher.nuanceApp.RecognizeTest;
import com.blacksheep.teacher.nuanceApp.ResponceRecognize;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/9/12
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestManager {

    private Activity activity;
    private LinearLayout ll_board;
    LinearLayout curLinearLayout;
    TextView curTextView;
    int max_component = 5;
    int countExaple = 0;
    private int testNumber;

    DataTest dataTest;
    ManagerPhrases managerPhrases;
    private Activity mContext;

    private int countI;
    private int countX;

    private int testSessionID;
    private int countOKAnswer;

    private long startTime;
    private OnNuanceResponse onNuanceResponse;

    private OnCompletionListener onCompletionListener;

    int width;
    int height;

    ManagerActions managerActions;

    //  public ResponseListener responseListener;
    public boolean isWork;
    public boolean isShownQuestHelp;
    OnQuestHelpAction onQuestHelpAction;

    public TestManager(int testNumber, Activity context,OnQuestHelpAction onQuestHelpAction) {
        this.testNumber = testNumber;
        this.mContext = context;
        this.onQuestHelpAction=onQuestHelpAction;
        Display d = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        width = d.getWidth();
        height = d.getHeight();
        Log.i(getClass().getName(), "display width " + width);
        Log.i(getClass().getName(), "display height " + height);
        managerPhrases = new ManagerPhrases("", context);
        managerActions = new ManagerActions(context);


    }
    private DataTest generateTest() {
        DataTest test = new DataTest(-1, -1, testNumber, "", "");
        LinkedList<DataQuest> dataQuests = new LinkedList<DataQuest>(DBManager.getInstance().getDataQuestsByTestID(testNumber));
        Collections.shuffle(dataQuests);
        test.setListQuests(dataQuests);
        return test;
    }


    public void start() {


        DBManager.testAnim = true;
        dataTest = generateTest();
        startTime = System.currentTimeMillis();
        managerPhrases = new ManagerPhrases(DataManager.getPhrasesDir(), activity);
        countI = 0;
        countX = 0;
        //responseListener=new ResponseListener();
        isWork = true;
        isShownQuestHelp=!MyApplication.getInstance().getPrefs().getBoolean(AcSetting.SETTING_QUEST_HELP,true);
        firstBlock();

    }

    private boolean repeatQuestion = false;

    public void resume() {

        if (countI == -1)
            return;

        final CheckInternet checkInternet = new CheckInternet(mContext);
        if (checkInternet.isOnline()) {
            isWork = true;
            DBManager.testAnim = true;
            if (repeatQuestion) {
                repeatQuestion = false;
                doQuestionF4(countI);

            } else {
                try {
                    AudioPhraseBuilder.mediaPlayerHead.start();
                } catch (Exception e) {
                    return;
                }
            }

        } else {
            managerActions.generateAnimationOfflineInternet(checkInternet, new ManagerActions.IActionComplete() {
                @Override
                public void onComplete() {
                    checkInternet.dialogAppear();
                }
            });
            //generateAnimationOfflineInternet(checkInternet);
        }

    }

    public void pause() {
        isWork = false;
        DBManager.testAnim = false;
        if (RecognizeRequest.listenResponse) {
            stopRecognize();
            repeatQuestion = true;
        } else {
            try {
                AudioPhraseBuilder.mediaPlayerHead.pause();
            } catch (Exception e) {
                return;
            }
        }
    }

    private void firstBlock() {
        //final CheckInternet checkInternet = new CheckInternet(mContext);
        //if (checkInternet.isOnline()) {
            addContentToBoard(dataTest.getListQuests().get(countI).getContent());
            doQuestionF4(countI);
        /*} else {
            managerActions.generateAnimationOfflineInternet(checkInternet, new ManagerActions.IActionComplete() {
                @Override
                public void onComplete() {
                    checkInternet.dialogAppear();
                }
            });
        }*/
    }

    RecognizeRequest recognizeRequest;

    private void doQuestionF4(final int numberQuestion) {

        final DataQuest curQuest = dataTest.getListQuests().get(numberQuestion);
        managerActions.doQuestionF4(curQuest, new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {


                // responseListener.setListenResponse(Integer.valueOf(curQuest.getAnswer()));
//                recognizeRequest = new RecognizeRequest();
//                recognizeRequest.startRecognize(Integer.valueOf(curQuest.getAnswer()), new RecognizeTest(), activity);
//                recognizeRequest.setCompleteRecognize(new RecognizeRequest.CompleteRecognize() {
//                    @Override
//                    public void onRecognizeComplete(ResponceRecognize responceRecognize) {
//                        if (onNuanceResponse != null)
//                            onNuanceResponse.response(responceRecognize.getText());
//                        doResponseByQuestion(responceRecognize.getKey());
//                    }
//                });
//                LogTeach.startTime = new Timestamp(System.currentTimeMillis());
                final ResponceRecognize resp = RecognizeRequest.getRespRecognize();
                if (resp != null) {
                    if(recognizeRequest!=null)
                        recognizeRequest.stopRecognize();
                    doResponseByQuestion(resp.getKey(),resp.getText());
                } else  {
                    doWaitShort();
                }

            }
        }, new AnimationSimple.OnCompletionAudioListener() {
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
                   //     LogTeach.startTime = new Timestamp(System.currentTimeMillis());
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
    private void doWaitShort() {
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
                            isReadyListenerFirst=true;
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
                            if(isReadyListenerFirst)
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
//                                            doResponseByQuestion(resp2.getKey());
//                                            }
//                                            else
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

//    private void doWaitShort() {
//        isReadyListenerFirst=false;
//        managerActions.doWaitShortBegin(new ManagerActions.IActionComplete() {
//            @Override
//            public void onComplete() {
//
//                final ResponceRecognize resp = RecognizeRequest.getRespRecognize();
//                if (resp != null) {
//                    managerActions.doWaitShortEnd(new ManagerActions.IActionComplete() {
//                        @Override
//                        public void onComplete() {
//                            doResponseByQuestion(resp.getKey());
//                        }
//                    });
//
//                } else {
//
//                    RecognizeRequest.setResponseReadyListener(new RecognizeRequest.ResponseReadyListener() {
//                        @Override
//                        public void ready() {
//                            isReadyListenerFirst=true;
//                            managerActions.doWaitShortEnd(new ManagerActions.IActionComplete() {
//                                @Override
//                                public void onComplete() {
//                                    final ResponceRecognize resp2 = RecognizeRequest.getRespRecognize();
//                                    doResponseByQuestion(resp2.getKey());
//
//                                }
//                            });
//                        }
//                    });
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Thread.sleep(2000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                            }
//                            if(isReadyListenerFirst)
//                                return;
//                            else {
//                                RecognizeRequest.setResponseReadyListener(null);
//                            }
//                            activity.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    managerActions.doWaitShortEnd(new ManagerActions.IActionComplete() {
//                                        @Override
//                                        public void onComplete() {
//                                            final ResponceRecognize resp2 = RecognizeRequest.getRespRecognize();
//                                            if (resp2 != null) {
//                                                doResponseByQuestion(resp2.getKey());
//                                            }
//                                            else
//                                                doWaitLong();
//                                        }
//                                    });
//                                }
//                            });
//                        }
//                    }).start();
//                }
//            }
//        });
//    }


    private void doWaitLong() {
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


    private void someResponseHandler(int result) {
        if (countX == 2) {
            countX = 0;
            doCorrectlyResultF33(dataTest.getListQuests().get(countI).getAnswer());

        } else {
            countX++;
            if (result == ResponceRecognize.KEY_RESPONSE_INCORRECTLY || result == ResponceRecognize.KEY_RESPONSE_SILENCE)
                doInformationСlearlyOneNumber();
            else
                doRepeatQuestionF32();
        }
    }

    private void doResponseByQuestion(int responseCode,String text) {
        RecognizeRequest.setNullRespRecognizeHard();
        RecognizeRequest.setResponseReadyListener(null);
        onQuestHelpAction.hide();
         if(onNuanceResponse!=null)
             onNuanceResponse.response(text);
      //  LogTeach.addAnswer(testSessionID, dataTest.getListQuests().get(countI).getId(), responseCode);
        switch (responseCode) {
            case ResponceRecognize.KEY_RESPONSE_CORRECTLY: {

                managerActions.doResultQuestionOK(countX, new ManagerActions.IActionComplete() {
                    @Override
                    public void onComplete() {
                        countX = 0;
                        countOKAnswer++;
                        checkLastQuestion();
                    }
                });
                break;
            }

            case ResponceRecognize.KEY_RESPONSE_SILENCE: {
                //f-5-NN-2

                managerActions.doResultQuestionSilence(new ManagerActions.IActionComplete() {
                    @Override
                    public void onComplete() {
                        someResponseHandler(ResponceRecognize.KEY_RESPONSE_SILENCE);
                    }
                });
                break;
            }

            case ResponceRecognize.KEY_RESPONSE_INCORRECTLY: {

                //f-21-S-1
                managerActions.doResultQuestionInaccurately(new ManagerActions.IActionComplete() {
                    @Override
                    public void onComplete() {
                        someResponseHandler(ResponceRecognize.KEY_RESPONSE_INCORRECTLY);
                    }
                });
                break;
            }

            case ResponceRecognize.KEY_RESPONSE_WRONG: {

                //f-7-N-1
                managerActions.doResultQuestionWrong(countX, new ManagerActions.IActionComplete() {
                    @Override
                    public void onComplete() {
                        someResponseHandler(ResponceRecognize.KEY_RESPONSE_WRONG);
                    }
                });

                break;
            }
            case ResponceRecognize.KEY_RESPONSE_NOT_STANDARD_BAD: {
                doRepeatQuestionF32();
                break;
            }

            case ResponceRecognize.KEY_UNKNOWN_ERROR: {
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

            default: {
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

    private void addContentToBoard(String text) {
        curTextView = new TextView(activity);


        curTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, MyApplication.instance.getTextSize());

        curTextView.setTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/segoepr.ttf"));
        curTextView.setPadding(0, 0, 45, 0);
        curTextView.setText(text+"?");
        curTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        if (countExaple >= max_component) {
            curLinearLayout = layoutBuilder();
            ll_board.addView(curLinearLayout);
            countExaple = 0;
        }
        curLinearLayout.addView(curTextView);
        countExaple++;

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

    private void doTestResult(int count) {
        managerActions.doTestResult(countOKAnswer, new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                if (onCompletionListener != null)
                    onCompletionListener.onCompletion();
            }
        });
    }

    private void checkLastQuestion() {
        setTextBoxResult();

        if (countI == dataTest.getListQuests().size() - 1) {
            float result = (float) countOKAnswer / dataTest.getListQuests().size();
            // LogTeach.updateTest(testSessionID,result);
          //  LogTeach.addTest(dataTest, -1, new Timestamp(startTime), result);
            doTestResult(countOKAnswer);
            countI = -1;
            if (onCompletionListener != null)
                onCompletionListener.onCompletionForShowResult(countOKAnswer);
            return;
        }

        countI++;
      //  isShownQuestHelp=false;
        firstBlock();
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

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.onCompletionListener = onCompletionListener;
    }

    public void setOnNuanceResponse(OnNuanceResponse onNuanceResponse) {
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


    public interface OnCompletionListener {
        public void onCompletion();

        public void onCompletionForShowResult(int countOkAnswer);
    }

    public interface OnNuanceResponse{
        public void response(String str);
    }
    public interface OnQuestHelpAction{
        public void show(String content);
        public void hide();
        public void setBg(View view);
    }
}
