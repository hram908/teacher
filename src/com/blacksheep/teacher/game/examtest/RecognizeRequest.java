package com.blacksheep.teacher.game.examtest;

import android.app.Activity;
import android.media.AudioFormat;
import android.util.Log;
import com.blacksheep.teacher.game.CheckInternet;
import com.blacksheep.teacher.nuanceApp.*;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Defafault
 * Date: 30.07.12
 * Time: 3:46
 * To change this template use File | Settings | File Templates.
 */
public class RecognizeRequest {

    public static boolean listenResponse;
    private int waitRecognize =13000;
    private int waitRecognizeSilence =3000;
    private static   ResponceRecognize respRecognize;
    private CompleteRecognize completeRecognize;
    private static ResponseReadyListener responseReadyListener;


    public static boolean isRecognize()
    {
        if(respRecognize==null)
            return false;
        return true;
    }



    private boolean detectVoice(RecognizerAlgoritm recognizerAlgoritm)
    {

        float minLevel=70f;
        float level=0;
        long startRecognize = System.currentTimeMillis();
        Random random = new Random();
        int thId = random.nextInt(1000);
        while (true)        {
           // long time =  System.currentTimeMillis()-startRecognize;
        //    Log.i("recognize silence time", "t= "+time);
            if(System.currentTimeMillis()-startRecognize>=waitRecognizeSilence)
            {
             //   Log.i("recognize silence", "pause");
                break;
            }
            float curLevel = recognizerAlgoritm.GetAudioLevel();
            if(level<curLevel)
                level=curLevel;
            Log.i("volume level "+thId, "level =" + level);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if(level>minLevel)
            return true;
        return false;
    }

    RecognizerAlgoritm  recognizerAlgoritm;

    public void startRecognize(int expectedResult,Algorithm algorithm,final Activity activity) {

        this.listenResponse = true;

         recognizerAlgoritm = new RecognizerAlgoritm(algorithm);

        final Thread  silence = new Thread(new Runnable() {
            @Override
            public void run() {

                if(!detectVoice(recognizerAlgoritm))
                    recognizerAlgoritm.StopRecognize();
            }
        });
        silence.start();


        recognizerAlgoritm.RecognizeText(expectedResult,activity ,new ReadyListener() {
            @Override
            public void ready(ResponceRecognize responceRecognize) {

                if(!setRespRecognize(responceRecognize))
                    return;

                Log.i(this.getClass()+ "recognize text", responceRecognize.getText());
                if(completeRecognize!=null)
                    completeRecognize.onRecognizeComplete(responceRecognize);
//                switch (responceRecognize.getKey())
//                {
//                    case ResponceRecognize.KEY_RESPONSE_CORRECTLY:
//                    {
//                        //response(RESULT_QUESTION_OK);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_RESPONSE_INCORRECTLY:
//                    {
//                        //response(RESULT_QUESTION_INACCURATELY);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_RESPONSE_SILENCE:
//                    {
//                        //response(RESULT_QUESTION_SILENCE);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_RESPONSE_WRONG:
//                    {
//                        //response(RESULT_QUESTION_WRONG);
//                        break;
//                    }
//                    case  ResponceRecognize.KEY_NOT_INTERNET_ACCESS:
//                    {
//
//                        break;
//                    }
//                    case ResponceRecognize.KEY_UNKNOWN_ERROR:
//                    {
//                        //response(RESULT_REPEAT_QUESTION);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_NAME_NOT_FOUND:
//                    {
//                        //response(ResponceRecognize.KEY_NAME_NOT_FOUND);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_NAME_FOUND:
//                    {
//                        //response(ResponceRecognize.KEY_NAME_FOUND);
//                        break;
//                    }
//                    default:
//                        break;
//                }

            }
        },waitRecognize);


    }
    public void startRecognize(String text,Algorithm algorithm,final Activity activity) {

        this.listenResponse = true;
       recognizerAlgoritm = new RecognizerAlgoritm(algorithm);


        final Thread  silence = new Thread(new Runnable() {
            @Override
            public void run() {

                if(!detectVoice(recognizerAlgoritm))
                    recognizerAlgoritm.StopRecognize();
            }
        });
        silence.start();

        recognizerAlgoritm.RecognizeText(text,activity ,new ReadyListener() {
            @Override
            public void ready(ResponceRecognize responceRecognize) {

                if(!setRespRecognize(responceRecognize))
                    return;

                Log.i(this.getClass()+ "recognize text", responceRecognize.getText());
                if(completeRecognize!=null)
                    completeRecognize.onRecognizeComplete(responceRecognize);
//                switch (responceRecognize.getKey())
//                {
//                    case ResponceRecognize.KEY_RESPONSE_CORRECTLY:
//                    {
//                        //response(RESULT_QUESTION_OK);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_RESPONSE_INCORRECTLY:
//                    {
//                        //response(RESULT_QUESTION_INACCURATELY);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_RESPONSE_SILENCE:
//                    {
//                        //response(RESULT_QUESTION_SILENCE);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_RESPONSE_WRONG:
//                    {
//                        //response(RESULT_QUESTION_WRONG);
//                        break;
//                    }
//                    case  ResponceRecognize.KEY_NOT_INTERNET_ACCESS:
//                    {
//                        final CheckInternet checkInternet=new CheckInternet(activity);
//                        ManagerActions managerActions = new ManagerActions(activity);
//                        managerActions.generateAnimationOfflineInternet(checkInternet,new ManagerActions.IActionComplete() {
//                            @Override
//                            public void onComplete() {
//                                checkInternet.dialogAppear();
//                            }
//                        });
//                        break;
//                    }
//                    case ResponceRecognize.KEY_UNKNOWN_ERROR:
//                    {
//                        //response(RESULT_REPEAT_QUESTION);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_NAME_NOT_FOUND:
//                    {
//                        //response(ResponceRecognize.KEY_NAME_NOT_FOUND);
//                        break;
//                    }
//                    case ResponceRecognize.KEY_NAME_FOUND:
//                    {
//                        //response(ResponceRecognize.KEY_NAME_FOUND);
//                        break;
//                    }
//                    default:
//                        break;
//                }

            }
        },waitRecognize);


    }


    public static ResponceRecognize getRespRecognize() {

        if(respRecognize==null)
            return null;
        ResponceRecognize recognizeRequest=null;
        try {
            recognizeRequest =(ResponceRecognize) respRecognize.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        respRecognize=null;
        if(recognizeRequest!=null)
            Log.i(RecognizeRequest.class.getName(),"code = "+recognizeRequest.getKey());
        return recognizeRequest;
    }

    public static void startListenResponse()
    {
        listenResponse=true;
    }

    public static boolean isResponseRecognizeNull(){
        if(respRecognize==null)
            return true;
        return false;
    }

    public static boolean setRespRecognize(ResponceRecognize respRecognize1)
    {
        if (!listenResponse)
            return false;
        listenResponse = false;
        respRecognize = respRecognize1;
        if(responseReadyListener!=null)
            responseReadyListener.ready();
        return true;
    }
    public static void setNullRespRecognizeHard()
    {
        respRecognize = null;
    }

    public void stopRecognize(){
        listenResponse = false;
        recognizerAlgoritm.StopRecognize();
    }

    public void setCompleteRecognize(CompleteRecognize completeRecognize) {
        this.completeRecognize = completeRecognize;
    }

    public static void setResponseReadyListener(ResponseReadyListener responseReadyListener1) {
        responseReadyListener = responseReadyListener1;
    }

    public interface CompleteRecognize{
        public void onRecognizeComplete(ResponceRecognize responceRecognize);
    }

    public interface ResponseReadyListener{
        public void ready();
    }
}
