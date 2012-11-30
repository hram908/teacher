package com.blacksheep.teacher.nuanceApp;

import com.nuance.nmdp.speechkit.Recognition;

/**
 * Created with IntelliJ IDEA.
 * User: vovi
 * Date: 30.07.12
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
public class CheckResultWord extends CheckResult {

    String mValue;
    public void recognizeWord(Recognition recognition,String value) {
        result=recognition;
        mValue=value;
        if(search())
        {
            responceRecognize.setKey(ResponceRecognize.KEY_WORD_FOUND);
            responceRecognize.setText(value);
        }
        else
        {
            responceRecognize.setKey(ResponceRecognize.KEY_WORD_NOT_FOUND);
            responceRecognize.setText("Слово не совпадает");

        }

    }
    private boolean search()
    {
        int count=result.getResultCount();
        for (int i=0;i<count;i++)
        {
            String[] splited=result.getResult(i).getText().split(" ");
            for (int j=0;j<splited.length;j++)
            {
                if(splited[j].toLowerCase().equals(mValue.toLowerCase()))
                {
                    return true;
                }
            }

        }
        return false;
    }
}
