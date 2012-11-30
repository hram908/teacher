package com.blacksheep.teacher.nuanceApp;

import android.content.Context;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.model.dataEntity.DataNames;
import com.nuance.nmdp.speechkit.Recognition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: vovi
 * Date: 19.04.12
 * Time: 13:02
 * To change this template use File | Settings | File Templates.
 */
public class CheckResultName extends CheckResult{
    Context context_ac;
    LinkedList<DataNames> dataNames;
    public CheckResultName(Context context) {
        super();
        context_ac=context;
        dataNames= createNameList();

    }
    private LinkedList<DataNames> createNameList() {
        BufferedReader in = null;
        LinkedList<DataNames> names = new LinkedList<DataNames>();
        try {
            in = new BufferedReader(new InputStreamReader(context_ac.getResources().openRawResource(R.raw.names), "utf8"));
            while (in.ready()) {
                for (int i = 0; i < 121; i++) {
                    String a = in.readLine();
                    String[] aa = a.split("\t");
                    names.add(new DataNames(i, aa[1], aa[2], aa[3], aa[4], aa[5], aa[6],aa[7],aa[8]));

                }

            }
            in.close();
        } catch (Exception ex) {
        }
        return names;

    }
    public void recognizeName(Recognition recognition)
    {
        result=recognition;
        if(search())
        {
            responceRecognize.setKey(ResponceRecognize.KEY_NAME_FOUND);
            responceRecognize.setDatanames(dataNames);
        }
        else
        {
            responceRecognize.setKey(ResponceRecognize.KEY_NAME_NOT_FOUND);
            responceRecognize.setText("Имя не найдено");
            responceRecognize.setDatanames(dataNames);

        }
        
    }
    private boolean search()
    {
        int count=result.getResultCount();
          for (int i=0;i<count;i++)
          {
              for (int j=0;j<dataNames.size();j++)
              {
                  for (int k=0;k<5;k++)
                  {

                      if(result.getResult(i).getText().toLowerCase().equals(dataNames.get(j).getDataName(k).toLowerCase()))
                      {
                          responceRecognize.setText(String.valueOf(j));
                          return true;
                      }
                  }
              }
          }
          return false;
    }

    @Override
    public void CheckError(int errorCode, String errorText) {
        super.CheckError(errorCode, errorText);
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
                responceRecognize.setDatanames(dataNames);
                break;
            default:
                responceRecognize.setKey(ResponceRecognize.KEY_UNKNOWN_ERROR); //неизвестная ошибка
                break;
        }
    }
}
