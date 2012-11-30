package com.blacksheep.teacher.nuanceApp;

import com.nuance.nmdp.speechkit.Recognition;

public class CheckResult {
    public ResponceRecognize responceRecognize;
    private String[] BadFicha;
    private String[] GoodFicha;
    public Recognition result;
    public CheckResult() {
        responceRecognize = new ResponceRecognize();
        //CreateBadFicha();
        //CreateGoodFicha();
    }

    private void CreateBadFicha() {
        if(BadFicha==null)
        {
            BadFicha=new String[2];//из базы
            BadFicha[0]="дура";
            BadFicha[1]="овца";
        }

    }
    private void CreateGoodFicha()
    {
        if(GoodFicha==null)
        {
            GoodFicha=new String[2];//из базы
            GoodFicha[0]="забыл";
            GoodFicha[1]="точно";
        }
    }
    public void CheckError(int errorCode, String errorText) {
        responceRecognize.setText(errorText);
        switch (errorCode) {
            case 3:
                responceRecognize.setKey(ResponceRecognize.KEY_NOT_INTERNET_ACCESS);  //нет соединения с интернетом
                break;
            case 5:
                responceRecognize.setKey(ResponceRecognize.KEY_WRITING_HAS_STOP);  // запись и распознование прервано
                break;
            case 2:
                responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_SILENCE); //ответ -тишина
                break;
            default:
                responceRecognize.setKey(ResponceRecognize.KEY_UNKNOWN_ERROR); //неизвестная ошибка
                break;
        }
    }

    public ResponceRecognize GetResponce() {
        return responceRecognize;
    }


}
