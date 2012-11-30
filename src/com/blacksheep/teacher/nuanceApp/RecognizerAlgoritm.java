package com.blacksheep.teacher.nuanceApp;

import android.content.Context;
import com.blacksheep.teacher.nuanceApp.Algorithm;
import com.blacksheep.teacher.nuanceApp.ReadyListener;

public class RecognizerAlgoritm {
    private Algorithm algorithm;
    public RecognizerAlgoritm(Algorithm algorithm){
        this.algorithm=algorithm;
    }
    public void SetAlgorithm(Algorithm algorithm){
        this.algorithm=algorithm;
    }
    public void RecognizeText(String text,Context context,ReadyListener readyListener,long mills){
        algorithm.RecognizeText(text,context,readyListener,mills);
    }
    public void RecognizeText(int text,Context context,ReadyListener readyListener,long mills){
        algorithm.RecognizeText(text,context,readyListener,mills);
    }
    public void CancelRecognize(){
        algorithm.CancelRecognize();
    }
    public void StopRecognize(){
        algorithm.StopRecognize();
    }
    public float GetAudioLevel()
    {
       return algorithm.GetAudioLevel();
    }
}
