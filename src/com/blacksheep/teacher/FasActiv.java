package com.blacksheep.teacher;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import com.blacksheep.teacher.activites.test.AcStartExam;
import com.blacksheep.teacher.activites.test.AcStartTest;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/6/12
 * Time: 12:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class FasActiv extends Activity{


    ///mnt/sdcard/TeachData/sounds/results/_result_1.wav
    ///mnt/sdcard/TeachData/sounds/phrases/f-33-2.wav
    ///mnt/sdcard/TeachData/sounds/names/_M_E_N_/s_volodya.wav

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.fas);
        editText = (EditText) findViewById(R.id.etTestNumber);



    }

    public void clickStartTest(View v)
    {
        Intent intent = new Intent(FasActiv.this,AcStartTest.class);
        intent.putExtra(AcStartTest.TEST_ID,Integer.valueOf(editText.getText().toString()));
        startActivity(intent);
    }
    public void clickStartExam13(View v)
    {
        Intent intent = new Intent(FasActiv.this,AcStartExam.class);
        intent.putExtra(AcStartExam.EXAM_QUESTION_COUNT,13);
        intent.putExtra(AcStartExam.EXAM_QUESTION_LEVEL,0);
        startActivity(intent);
    }
    public void clickStartExam21(View v)
    {
        Intent intent = new Intent(FasActiv.this,AcStartExam.class);
        intent.putExtra(AcStartExam.EXAM_QUESTION_COUNT,21);
        intent.putExtra(AcStartExam.EXAM_QUESTION_LEVEL,1);
        startActivity(intent);
    }
    public void clickStartExam34(View v)
    {
        Intent intent = new Intent(FasActiv.this,AcStartExam.class);
        intent.putExtra(AcStartExam.EXAM_QUESTION_COUNT,34);
        intent.putExtra(AcStartExam.EXAM_QUESTION_LEVEL,2);
        startActivity(intent);
    }
    public void clickSend(View v){
        Intent picMessageIntent = new Intent(android.content.Intent.ACTION_SEND);
        picMessageIntent.setType("image/jpeg");

        File downloadedPic =  new File(
                Environment.getExternalStorageDirectory().getAbsolutePath()+"/TeachData/animations/test/1.jpg");

        picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(downloadedPic));
        startActivity(Intent.createChooser(picMessageIntent, "Отправьте вашу открытку используя:"));
    }
}
