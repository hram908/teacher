package com.blacksheep.teacher.game.greeting;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blacksheep.teacher.activites.greeting.AcGreetingLoad;
import com.blacksheep.teacher.dialogs.DialogRequestName;
import com.blacksheep.teacher.game.CheckInternet;
import com.blacksheep.teacher.game.animated_view.AnimationHead;
import com.blacksheep.teacher.game.animated_view.AnimationSimple;
import com.blacksheep.teacher.game.animated_view.AnimationSimpleBlaBla;
import com.blacksheep.teacher.game.animated_view.SurfaceViewTeach;
import com.blacksheep.teacher.game.examtest.ManagerActions;
import com.blacksheep.teacher.game.examtest.ManagerPhrases;
import com.blacksheep.teacher.game.examtest.RecognizeRequest;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;
import com.blacksheep.teacher.model.dataEntity.DataNames;
import com.blacksheep.teacher.model.dataEntity.DataTest;
import com.blacksheep.teacher.model.database.DBManager;
import com.blacksheep.teacher.nuanceApp.*;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: vovi
 * Date: 16.04.12
 * Time: 17:10
 * To change this template use File | Settings | File Templates.
 */
public class GreetingManager {
    private Activity activity;
    private LinearLayout ll_board;
    LinearLayout curLinearLayout;
    TextView curTextView;
    int max_component = 5;
    int countExaple = 0;
    private int testNumber;
    public AnimationHead animationHead;
    DataTest dataTest;
    ManagerPhrases managerPhrases;
    private Activity context_ac;
    RecognizerAlgoritm recognizerAlgoritm;
    LinkedList<DataNames> dataNames;
    SharedPreferences.Editor stats_editor;
    public SurfaceViewTeach.IFrameListener iFrameListener;
    public boolean isListenResponce;
    private TextView textViewFrameCount;
    private int frameCount;
    ManagerActions managerActions;

    public GreetingManager(Activity context) {
        context_ac = context;
        implementListener();
        stats_editor = PreferenceManager.getDefaultSharedPreferences(context_ac).edit();
        managerPhrases = new ManagerPhrases("", context_ac);
        managerActions = new ManagerActions(context_ac);
    }

    private void implementListener() {
        iFrameListener = new SurfaceViewTeach.IFrameListener() {
            @Override
            public void frameNumber(final int frame) {
                frameCount = frame;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (textViewFrameCount != null)
                            textViewFrameCount.setText(String.valueOf(frame));
                    }
                });
            }
        };
    }

    public void startFirstGreeting() {


        managerActions.doGreetingFirstPart1(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {

                RecognizeRequest recognizeRequest = new RecognizeRequest();
                recognizeRequest.startRecognize("", new RecognizeName(), activity);
                recognizeRequest.setCompleteRecognize(new RecognizeRequest.CompleteRecognize() {
                    @Override
                    public void onRecognizeComplete(ResponceRecognize responceRecognize) {
                        handlerRecognize(responceRecognize);
                    }
                });
                //doWaitShort();
            }
        });


    }


//    private void doWaitShort() {
//        managerActions.doWaitShort(new ManagerActions.IActionComplete() {
//            @Override
//            public void onComplete() {
//                ResponceRecognize resp = RecognizeRequest.getRespRecognize();
//                if (resp != null) {
//                    handlerRecognize(resp);
//                } else {
//                    doWaitLong();
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
                    handlerRecognize(resp);
                } else {
                    showDialogNames(resp);
                }
            }
        });
    }


    //    private void listenResponse() {
//        isListenResponce = true;
//        recognizerAlgoritm = new RecognizerAlgoritm(new RecognizeName());
//        recognizerAlgoritm.RecognizeText("", activity, new ReadyListener() {
//            @Override
//            public void ready(ResponceRecognize responceRecognize) {
//                Log.i(this.getClass() + "recognize text", responceRecognize.getText());
//                isListenResponce = true;
//                switch (responceRecognize.getKey()) {
//                    case ResponceRecognize.KEY_NAME_FOUND: {
//                        responseOk(responceRecognize);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_RESPONSE_SILENCE: {
//
//                        showDialogNames(responceRecognize);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_NAME_NOT_FOUND: {
//                        showDialogNames(responceRecognize);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_NOT_INTERNET_ACCESS: {
//                        generateAnimationOfflineInternet(new CheckInternet(context_ac));
//                        break;
//                    }
//                    case ResponceRecognize.KEY_UNKNOWN_ERROR: {
//                        showDialogNames(responceRecognize);
//                        break;
//                    }
//                    default:
//                        break;
//                }
//
//            }
//        }, 13000);
//    }
    private void handlerRecognize(ResponceRecognize responceRecognize) {

        Log.i(this.getClass() + "recognize text", responceRecognize.getText());
        isListenResponce = true;
        switch (responceRecognize.getKey()) {
            case ResponceRecognize.KEY_NAME_FOUND: {
                responseOk(responceRecognize);
                break;
            }
            case ResponceRecognize.KEY_RESPONSE_SILENCE: {

                showDialogNames(responceRecognize);
                break;
            }
            case ResponceRecognize.KEY_NAME_NOT_FOUND: {
                showDialogNames(responceRecognize);
                break;
            }
            case ResponceRecognize.KEY_NOT_INTERNET_ACCESS: {

                break;
            }
            case ResponceRecognize.KEY_UNKNOWN_ERROR: {
                showDialogNames(responceRecognize);
                break;
            }
            default:
                break;
        }


    }


    public void startGreeting() {
//        LinkedList<DataAnimation>  dataAnimations1 = new LinkedList<DataAnimation>();
//        LinkedList<DataAnimation>  dataAnimations3 = new LinkedList<DataAnimation>();
//        DataAnimation dataAnimation = new DataAnimation("112",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200);
//        dataAnimation.setEndFrameAnim(96);
//        dataAnimation.setStartAudioFrame(1);
//        dataAnimations1.add(dataAnimation);
//        LinkedList<String> audio1 = new LinkedList<String>();
//        audio1.add(DataManager.getPhrases("f-1.1-5-a"));
//      //  audio1.add(DataManager.getPhrases("f-1.1-5-b"));
//        LinkedList<String> audio2 = new LinkedList<String>();
//        audio2.add(managerPhrases.getNameNegative(0));
//
//        DataAnimation dataAnimation3 = new DataAnimation("112",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200);
//        dataAnimation3.setStartFrameAnim(97);
//        dataAnimations3.add(dataAnimation);
//        LinkedList<String> audio3 = new LinkedList<String>();
//        audio3.add(DataManager.getPhrases("f-1.1-5-b"));
//
//        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();
//        animationSimples.add(new AnimationSimple(dataAnimations1,audio1,context_ac));
//      //  animationSimples.add(new AnimationSimpleBlaBla(audio2,context_ac));
//        animationSimples.add(new AnimationSimple(dataAnimations3,audio3,context_ac));
//
//        animationHead = new AnimationHead(animationSimples,context_ac);
//        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
//            @Override
//            public void onCompletion(AnimationHead animationHead) {
//                startMainAc();
//            }
//        });
//        animationHead.start();
        managerActions.doGreetingSecond(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                context_ac.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startMainAc();
                    }
                });
            }
        });
    }

//    public void startGreeting() {
//        int r1 = new Random().nextInt(7);
//        managerPhrases = new ManagerPhrases(DataManager.getPhrasesDir(), activity);
//
//        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
//        DataAnimation dataAnimation = DBManager.getInstance().getAnimationByTypeAndOptionCode(DataAnimation.ANIMATION_TYPE_GREETING, 1);
//        String audio;
//        int consructorNumber = r1;
//        if (dataAnimation != null) {
//            audio = DBManager.getInstance().getAudioByAnimationID(dataAnimation.getId()).getAudio_file_name();
//            consructorNumber = managerPhrases.getConstructorGreetingByAudio(audio);
//        }
//
//        LinkedList<String> phrs = managerPhrases.standartGreeting(consructorNumber);
//        if (dataAnimation != null)
//            dataAnimations.add(dataAnimation);
//
//        animationHead = new AnimationHead(dataAnimations, phrs, context_ac, 0);
//        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
//            @Override
//            public void onCompletion(AnimationHead animationHead) {
//                animationHead.destroy();
//                animationHead = null;
//                startMainAc();
//            }
//        });
//        animationHead.start();
//    }

    private LinkedList<DataAnimation> animationStandartGreeting(int number) {
        //ToDo привязать сюда анимацию приветствия
        LinkedList<DataAnimation> list = new LinkedList<DataAnimation>();
        switch (number) {
            case 0: {
                list.add(new DataAnimation("packed", 1, 85));
                break;
            }
            case 1: {
                list.add(new DataAnimation("packed", 1, 85));
                break;
            }
            case 2: {
                list.add(new DataAnimation("packed", 1, 85));
                break;
            }
            case 3: {
                list.add(new DataAnimation("packed", 1, 85));
                break;
            }
            case 4: {
                list.add(new DataAnimation("packed", 1, 85));
                break;
            }
            case 5: {
                list.add(new DataAnimation("packed", 1, 85));
                break;
            }
            case 6: {
                list.add(new DataAnimation("packed", 1, 85));
                break;
            }
            default:
                break;
        }
        return list;
    }

//    private void generateAnimationOfflineInternet(final CheckInternet checkInternet) {
//        //Todo привязать анимацию и звук при отсутствии интернет соединения
//        String phrase = "f-757";
//
//        LinkedList<String> soundlist = new LinkedList<String>();
//        soundlist.add(DataManager.getPhrases(phrase));
//        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
//        LinkedList<String> phrs = soundlist;
//        for (String s : phrs) {
//            String[] prts = s.split("/");
//            s = prts[prts.length - 1];
//            DataAnimation daj = DBManager.getInstance().getAnimationByAudioFileName(s);
//            if (daj != null)
//                dataAnimations.add(daj);
//        }
//        animationHead = new AnimationHead(dataAnimations, soundlist, context_ac, 0);
//        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
//            @Override
//            public void onCompletion(AnimationHead animationHead) {
//                animationHead.destroy();
//                animationHead = null;
//                checkInternet.dialogAppear();
//            }
//        });
//        animationHead.start();
//    }

    private void showDialogNames(final ResponceRecognize responceRecognize) {

//        LinkedList<String> audio1 = new LinkedList<String>();
//        audio1.add(DataManager.getPhrases("f-1.55"));
//
//
//        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();
//        animationSimples.add(new AnimationSimpleBlaBla(audio1,context_ac));
//
//        //animationHead = new AnimationHead(dataLecture.getListAnimations(), strings, context_ac, 0);
//        animationHead = new AnimationHead(animationSimples,context_ac);
//        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
//            @Override
//            public void onCompletion(AnimationHead animationHead) {
//                animationHead.destroy();
//                animationHead = null;
//                DialogRequestName dialogRequestName = new DialogRequestName(context_ac, new NameDialogListener(responceRecognize), activity);
//                dialogRequestName.show();
//            }
//    });
//        animationHead.start();

        managerActions.doGreetingFirstPartRecognizeFail(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                DialogRequestName dialogRequestName = new DialogRequestName(context_ac, new NameDialogListener(responceRecognize), activity);
                dialogRequestName.show();
            }
        });

    }

//    private void showDialogNames(final ResponceRecognize responceRecognize) {
//        String phrase = "f-1.55";
//
//        LinkedList<String> soundlist = new LinkedList<String>();
//        soundlist.add(DataManager.getPhrases(phrase));
//        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
//        LinkedList<String> phrs = soundlist;
//        for (String s : phrs) {
//            String[] prts = s.split("/");
//            s = prts[prts.length - 1];
//            DataAnimation daj = DBManager.getInstance().getAnimationByAudioFileName(s);
//            if (daj != null)
//                dataAnimations.add(daj);
//        }
//        animationHead = new AnimationHead(dataAnimations, soundlist, context_ac, 0);
//        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
//            @Override
//            public void onCompletion(AnimationHead animationHead) {
//                animationHead.destroy();
//                animationHead = null;
//                DialogRequestName dialogRequestName = new DialogRequestName(context_ac, new NameDialogListener(responceRecognize), activity);
//                dialogRequestName.show();
//            }
//        });
//        animationHead.start();
//
//    }


    public class NameDialogListener implements DialogRequestName.ResponceListener {
        ResponceRecognize responceRecognize;

        public NameDialogListener(ResponceRecognize responceRecognize1) {
            responceRecognize = responceRecognize1;
        }

        @Override
        public void onClick(String result) {
            int numberName = checkName(responceRecognize.getDatanames(), result);
            if (numberName >= 0) {
                String nameStandart = responceRecognize.getDatanames().get(numberName).getDataName(DataNames.keyNameStandart);
                String pathStandart = responceRecognize.getDatanames().get(numberName).getDataName(DataNames.keyPathStandart);
                String pathStrogoe = responceRecognize.getDatanames().get(numberName).getDataName(DataNames.keyPathStrogoe);
                String pathLascatelnoe = responceRecognize.getDatanames().get(numberName).getDataName(DataNames.keyPathLascatelnoe);
                stats_editor.putString("nameStandart", nameStandart);
                stats_editor.putString("pathStandart", pathStandart);
                stats_editor.putString("pathStrogoe", pathStrogoe);
                stats_editor.putString("pathLascatelnoe", pathLascatelnoe);


            } else {
                String nameStandart = result;
                stats_editor.putString("nameStandart", nameStandart);
                stats_editor.putString("pathStandart", "-");
                stats_editor.putString("pathStrogoe", "-");
                stats_editor.putString("pathLascatelnoe", "-");
            }
            stats_editor.putBoolean("isFirstEnter", false);
            stats_editor.commit();
            managerActions.doGreetingSecondPartRecognizeFail(new ManagerActions.IActionComplete() {
                @Override
                public void onComplete() {
                    context_ac.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startMainAc();
                        }
                    });
                }
            });

        }
    }

    private void startMainAc() {


        activity.finish();
        Intent intent = new Intent(context_ac, AcGreetingLoad.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context_ac.startActivity(intent);


    }

    private int checkName(LinkedList<DataNames> dataNames, String name) {
        int count = dataNames.size();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 5; j++) {
                if (dataNames.get(i).getDataName(j).toLowerCase().equals(name.toLowerCase())) return i;
            }
        }
        return -1;
    }

    private void responseOk(final ResponceRecognize responceRecognize) {
//        String phrase = "f-1-b";
//        LinkedList<DataAnimation> dataAnimations = new LinkedList<DataAnimation>();
//
//        DataAnimation daj = DBManager.getInstance().getAnimationByAudioFileName(phrase + ".mp3");
//        if (daj != null)
//            dataAnimations.add(daj);
//        LinkedList<String> soundlist = new LinkedList<String>();
//        soundlist.add(DataManager.getPhrases(phrase));
//        animationHead = new AnimationHead(dataAnimations, soundlist, context_ac, 0);
//        LinkedList<DataAnimation>  dataAnimations1 = new LinkedList<DataAnimation>();
//        DataAnimation dataAnimation = new DataAnimation("22",DataAnimation.ANIMATION_TYPE_QUESTION_REQUEST,200);
//        dataAnimation.setStartFrameAnim(335);
//        dataAnimations1.add(dataAnimation);
//        LinkedList<String> audio1 = new LinkedList<String>();
//        audio1.add(DataManager.getPhrases("f-1-b"));
//
//
//        LinkedList<AnimationSimple> animationSimples = new LinkedList<AnimationSimple>();
//        animationSimples.add(new AnimationSimple(dataAnimations1,audio1,context_ac));
//
//        //animationHead = new AnimationHead(dataLecture.getListAnimations(), strings, context_ac, 0);
//        animationHead = new AnimationHead(animationSimples,context_ac);
//        animationHead.setOnCompletionListener(new AnimationHead.OnCompletionListener() {
//            @Override
//            public void onCompletion(AnimationHead animationHead) {
//
//                String nameStandart = responceRecognize.getDatanames().get(Integer.valueOf(responceRecognize.getText())).getDataName(DataNames.keyNameStandart);
//                String pathStandart = responceRecognize.getDatanames().get(Integer.valueOf(responceRecognize.getText())).getDataName(DataNames.keyPathStandart);
//                String pathStrogoe = responceRecognize.getDatanames().get(Integer.valueOf(responceRecognize.getText())).getDataName(DataNames.keyPathStrogoe);
//                String pathLascatelnoe = responceRecognize.getDatanames().get(Integer.valueOf(responceRecognize.getText())).getDataName(DataNames.keyPathLascatelnoe);
//                stats_editor.putString("nameStandart", nameStandart);
//                stats_editor.putString("pathStandart", pathStandart);
//                stats_editor.putString("pathStrogoe", pathStrogoe);
//                stats_editor.putString("pathLascatelnoe", pathLascatelnoe);
//                stats_editor.putBoolean("isFirstEnter", false);
//                stats_editor.commit();
//                startMainAc();
//            }
//        });
//        animationHead.start();

        managerActions.doGreetingFirstPartRecognizeSuccess(new ManagerActions.IActionComplete() {
            @Override
            public void onComplete() {
                String nameStandart = responceRecognize.getDatanames().get(Integer.valueOf(responceRecognize.getText())).getDataName(DataNames.keyNameStandart);
                String pathStandart = responceRecognize.getDatanames().get(Integer.valueOf(responceRecognize.getText())).getDataName(DataNames.keyPathStandart);
                String pathStrogoe = responceRecognize.getDatanames().get(Integer.valueOf(responceRecognize.getText())).getDataName(DataNames.keyPathStrogoe);
                String pathLascatelnoe = responceRecognize.getDatanames().get(Integer.valueOf(responceRecognize.getText())).getDataName(DataNames.keyPathLascatelnoe);
                stats_editor.putString("nameStandart", nameStandart);
                stats_editor.putString("pathStandart", pathStandart);
                stats_editor.putString("pathStrogoe", pathStrogoe);
                stats_editor.putString("pathLascatelnoe", pathLascatelnoe);
                stats_editor.putBoolean("isFirstEnter", false);
                stats_editor.commit();
                startMainAc();
            }
        });
    }

    /*private Bitmap doScreenshot(View v1)
    {
        //String mPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/1.jpeg";
        Bitmap bitmap;
        v1.setDrawingCacheEnabled(true);
        bitmap = Bitmap.createBitmap(v1.getDrawingCache());
        v1.setDrawingCacheEnabled(false);
        return bitmap;
    }*/
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setTextViewFrameCount(TextView textViewFrameCount) {
        this.textViewFrameCount = textViewFrameCount;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void resume() {
        isListenResponce = false;
        startFirstGreeting();
    }

    public void stop() {
        stopRecognize();

    }

    public void stopRecognize() {
        if (recognizerAlgoritm != null)
            recognizerAlgoritm.CancelRecognize();
    }

}
