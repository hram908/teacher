package com.blacksheep.teacher.nuanceApp;

import com.nuance.nmdp.speechkit.Recognition;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.                      8a8d597464
 * User: vovi
 * Date: 19.04.12
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class CheckResultTestExam extends CheckResult{

    private final static String[] charList=new String[]{"ноль","один","два","три","четыре","пять","шесть","семь","восемь","девять","20:03","20:04","20:05","20:06","20:07","20:08","20:09"};
    private final static String[] charList2=new String[]{"восемь десятых","восемь десет","8:10"};
    private final static String[] charList3=new String[]{"восемь десет один"};
    private final static int [] numberList=new int[]{0,1,2,3,4,5,6,7,8,9,23,24,25,26,27,28,29};
    private final static String [] numberList2=new String[]{"80"};
    private final static String [] numberList3=new String[]{"81"};
    public CheckResultTestExam() {
        super();
    }
    public void recognizeTestExam(ArrayList<String[]> recognition, int value)
    {

        int count=recognition.size();
        String recText ="";
        for(int i=0;i<count;i++)
        {
            for(int j=0;j<recognition.get(i).length;j++)
                recText+=" "+recognition.get(i)[j];
        }
        boolean isWrong=false;
        boolean isRight=false;
        for (int i=0;i<count;i++)
        {
            for (int j=0;j<recognition.get(i).length;j++)
            {
                try
                {
                    if(Integer.valueOf(recognition.get(i)[j])==value)
                    {
                        responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_CORRECTLY); //распознал верно
                        responceRecognize.setText(recognition.get(i)[j]);
                        isRight=true;
                    }
                    else isWrong=true;
                }
                catch (Exception ex){}
            }
        }
        if(isWrong)
        {
          //  responceRecognize.setText("Ответ неверный");
            responceRecognize.setText(recText);
            responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_WRONG);//неверный ответ
        }

        else if(!isRight&&!isWrong)
        {
            responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_INCORRECTLY);
            responceRecognize.setText(recText);
           // responceRecognize.setText("Ответ некорректный");
        }
    }
    public ArrayList<String[]> ConvertToArrayList(Recognition recognition)
    {
        ArrayList<String> result_list=new ArrayList<String>();
        int count=recognition.getResultCount();
        for (int i=0;i<count;i++)
        {
            result_list.add(recognition.getResult(i).getText().toLowerCase());
        }
        return ConvertBagRecognize(result_list);

    }
    private ArrayList<String[]> ConvertBagRecognize(ArrayList<String> result_list)
    {
        ArrayList<String[]> arrayList=new ArrayList<String[]>();
        int count=result_list.size();
        for (int i=0;i<count;i++)
        {
            if(result_list.get(i).equals(charList3[0]))
            {
                arrayList.add(numberList3);
            }
            else if(result_list.get(i).equals(charList2[0])|result_list.get(i).equals(charList2[1]))
            {
                arrayList.add(numberList2);
            }
            else {
            String[] splitResult=result_list.get(i).split(" ");
            arrayList.add(splitResult);
            for (int k=0;k<splitResult.length;k++)
            {
                for (int j=0;j<charList.length;j++)
                {
                    if(splitResult[k].equals(charList[j]))
                    {

                        arrayList.get(i)[k]=String.valueOf(numberList[j]);
                    }
                }
            }
            }

        }
        return arrayList;
    }
    /*    public void RecognizeTest(Recognition recognition,String word){
        result=recognition;
        if(CheckValue(word)){responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_CORRECTLY);return;}// распознал верно
        //if(CheckValues(BadFicha)){responceRecognize.setKey(7);return;}// плохая фишка
        //if(CheckValues(GoodFicha)){responceRecognize.setKey(8);return;} //хорошая фишка
        if(CheckValues(word)){responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_INCORRECTLY);return;}//неполный ответ
        responceRecognize.setText("Ответ неверный");
        responceRecognize.setKey(ResponceRecognize.KEY_RESPONSE_WRONG);//неверный ответ

    }*/
    /* private boolean CheckValue(String[] value) {
        int count=result.getResultCount();
        int count2=value.length;
        for (int i=0;i<count;i++)
        {
            for (int j=0;j<count2;j++)
            {
                if(result.getResult(i).getContent().toLowerCase().equals(value[j])){responceRecognize.setText(value[j]); return true;}
            }
        }
        return false;
    }
    private boolean CheckValue(String value) {
        int count=result.getResultCount();
        for (int i=0;i<count;i++)
        {
                if(result.getResult(i).getContent().toLowerCase().equals(value)){responceRecognize.setText(value); return true;}
        }
        return false;
    }
    private boolean CheckValues(String[] mass)
    {
        ArrayList<String> list_result=new ArrayList<String>();
        int count=result.getResultCount();
        int count2=mass.length;
        for (int i=0;i<count;i++)
        {
            for (int j=0;j<count2;j++)
            {
                String[] relult_split=result.getResult(i).getContent().split(" ");
                int count3=relult_split.length;
                for (int k=0;k<count3;k++)
                {
                    if(relult_split[k].toLowerCase().equals(mass[j]))list_result.add(mass[j]);
                }
            }
        }
        if(list_result.size()!=0)
        {
            Random r1=new Random();
            responceRecognize.setText(list_result.get(r1.nextInt(list_result.size())));
            return true;
        }
        return false;
    }
    private boolean CheckValues(String mass)
    {
        int count=result.getResultCount();
        for (int i=0;i<count;i++)
        {
                String[] relult_split=result.getResult(i).getContent().split(" ");
                int count3=relult_split.length;
                for (int k=0;k<count3;k++)
                {
                    if(relult_split[k].toLowerCase().equals(mass)){responceRecognize.setText("Ответ неполный "+mass);return true;}
                }
        }
        return false;
    }*/
}
