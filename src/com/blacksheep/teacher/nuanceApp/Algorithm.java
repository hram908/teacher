package com.blacksheep.teacher.nuanceApp;

import android.content.Context;
import com.blacksheep.teacher.nuanceApp.ReadyListener;

public interface Algorithm
{
    void RecognizeText(String text, Context context,ReadyListener readyListener, long mills);
    void RecognizeText(int text, Context context, ReadyListener readyListener, long mills);
    void CancelRecognize();
    void StopRecognize();
    float GetAudioLevel();
}