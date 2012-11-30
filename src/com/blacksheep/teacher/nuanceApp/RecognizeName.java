

package com.blacksheep.teacher.nuanceApp;




import android.content.Context;
import android.os.Handler;
import com.nuance.nmdp.speechkit.Recognition;
import com.nuance.nmdp.speechkit.Recognizer;
import com.nuance.nmdp.speechkit.SpeechError;
import com.nuance.nmdp.speechkit.SpeechKit;

public class RecognizeName implements Algorithm {
    private static SpeechKit _speechKit;
    private ReadyListener _readyListener;
    private Handler _handler = null;
    private  Recognizer.Listener _listener;
    private Recognizer _currentRecognizer;
    long _mills;
    private CheckResultName checkResult;
    @Override
    public void RecognizeText(String text,Context context,ReadyListener readyListener,long mills) {
        _handler=new Handler();
        _readyListener=readyListener;
        checkResult=new CheckResultName(context);
        _mills=mills;

        _listener=new Recognizer.Listener(){
            @Override
            public void onRecordingBegin(Recognizer recognizer) {

                 Thread thread=new Thread(new Runnable() {
                     @Override
                     public void run() {
                         try {
                             Thread.sleep(_mills);
                             StopRecognize();
                         } catch (InterruptedException e) {
                             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                         }
                     }
                 });
             thread.start();
            }
            @Override
            public void onRecordingDone(Recognizer recognizer) {
            }

            @Override
            public void onResults(Recognizer recognizer, Recognition recognition) {
                 checkResult.recognizeName(recognition);
                _readyListener.ready(checkResult.GetResponce());

            }
            @Override
            public void onError(Recognizer recognizer, SpeechError speechError) {
                 checkResult.CheckError(speechError.getErrorCode(),speechError.getErrorDetail());
                _readyListener.ready(checkResult.GetResponce());
            }
        };
        if(_speechKit==null)
        {
        _speechKit=SpeechKit.initialize(context, AppInfo.SpeechKitAppId, AppInfo.SpeechKitServer, AppInfo.SpeechKitPort, AppInfo.SpeechKitSsl, AppInfo.SpeechKitApplicationKey);
        _speechKit.connect();
        }
        _currentRecognizer=GetSpeechkit().createRecognizer(Recognizer.RecognizerType.Dictation, Recognizer.EndOfSpeechDetection.Short, "ru_RU", _listener, _handler);
        _currentRecognizer.start();
    }

    @Override
    public void RecognizeText(int text, Context context, ReadyListener readyListener, long mills) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void CancelRecognize() {
        if (_currentRecognizer!=null)
        {
            _currentRecognizer.cancel();
            _currentRecognizer=null;

        }
    }

    @Override
    public void StopRecognize() {
        if(_currentRecognizer!=null)
        {
            _currentRecognizer.stopRecording();
        }
    }

    @Override
    public float GetAudioLevel() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private static SpeechKit GetSpeechkit()
    {
       return _speechKit;
    }
}
