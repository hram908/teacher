package com.blacksheep.teacher.game.examtest;


import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.dataEntity.DataWord;
import com.blacksheep.teacher.model.database.DBManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/9/12
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagerPhrases {


    //public static final String exten = ".wav";
    String[] listPaths;
    //String headdir;
    Activity activity;
    public ManagerPhrases(String headDir,Activity activity)
    {
        this.activity=activity;

       listPaths =  getPhrasesMass();
    }
    private String[] getPhrasesMass()
    {
        String[] listPaths = new String[190];
        listPaths[0]="f-7-S-5.mp3";
        listPaths[1]="f-33-2.mp3";
        listPaths[2]="f-22-17.mp3";
        listPaths[3]="f-5-NN-1.mp3";
        listPaths[4]="f-3-3.mp3";
        listPaths[5]="f-222-5.mp3";
        listPaths[6]="f-32-3.mp3";
        listPaths[7]="f-22-7.mp3";
        listPaths[8]="f-1.1-2-a.mp3";
        listPaths[9]="f-5-N-2-a.mp3";
        listPaths[10]="f-22-9.mp3";
        listPaths[11]="f-1.55.mp3";
        listPaths[12]="f-5-N-3.mp3";
        listPaths[13]="f-1.1-2-b.mp3";
        listPaths[14]="f-15-A100.mp3";
        listPaths[15]="f-144-2.mp3";
        listPaths[16]="f-22-14.mp3";
        listPaths[17]="f-1.1-6.mp3";
        listPaths[18]="f-8-A7-1.mp3";
        listPaths[19]="f-8-A4-1.mp3";
        listPaths[20]="f-8-A1-1.mp3";
        listPaths[21]="f-5-NN-4.mp3";
        listPaths[22]="f-6-S-4.mp3";
        listPaths[23]="f-7-N-4.mp3";
        listPaths[24]="f-83.2.mp3";
        listPaths[25]="f-8-A6-1.mp3";
        listPaths[26]="f-1.1-4.mp3";
        listPaths[27]="f-5-S-1.mp3";
        listPaths[28]="f-32-2.mp3";
        listPaths[29]="f-1.1-5-a.mp3";
        listPaths[30]="f-12.2.mp3";
        listPaths[31]="f-22-16.mp3";
        listPaths[32]="f-8-A0-2.mp3";
        listPaths[33]="f-22-10.mp3";
        listPaths[34]="f-1.1-5.mp3";
        listPaths[35]="f-3-1.mp3";
        listPaths[36]="f-22-12.mp3";
        listPaths[37]="f-6-P-2.mp3";
        listPaths[38]="f-1.1-7.mp3";
        listPaths[39]="f-5-N-2.mp3";
        listPaths[40]="f-3-4.mp3";
        listPaths[41]="f-15-A50-b.mp3";
        listPaths[42]="f-33-3.mp3";
        listPaths[43]="f-1.1-3-a.mp3";
        listPaths[44]="f-32-1.mp3";
        listPaths[45]="f-12-A100.mp3";
        listPaths[46]="f-7-S-12.mp3";
        listPaths[47]="f-6-P-3.mp3";
        listPaths[48]="f-13.mp3";
        listPaths[49]="f-6-PP-6.mp3";
        listPaths[50]="f-6-PP-4.mp3";
        listPaths[51]="f-12-1.mp3";
        listPaths[52]="f-6-S-9.mp3";
        listPaths[53]="f-23-1.mp3";
        listPaths[54]="f-14-4.mp3";
        listPaths[55]="f-15-A100-b.mp3";
        listPaths[56]="f-7-S-4.mp3";
        listPaths[57]="f-5-N-1-b.mp3";
        listPaths[58]="f-6-S-7.mp3";
        listPaths[59]="f-5-NN-3.mp3";
        listPaths[60]="f-8-A0-3.mp3";
        listPaths[61]="f-23-2.mp3";
        listPaths[62]="f-5-NN-2.mp3";
        listPaths[63]="f-7-S-3.mp3";
        listPaths[64]="f-7-N-3.mp3";
        listPaths[65]="f-45.mp3";
        listPaths[66]="f-8-A2-2.mp3";
        listPaths[67]="f-15-A100-a.mp3";
        listPaths[68]="f-6-P-1.mp3";
        listPaths[69]="f-8-A1-2.mp3";
        listPaths[70]="f-5-N-4.mp3";
        listPaths[71]="f-21-NN-1.mp3";
        listPaths[72]="f-8-A3-1.mp3";
        listPaths[73]="f-16.mp3";
        listPaths[74]="f-14-3-a.mp3";
        listPaths[75]="f-12-A25-2.mp3";
        listPaths[76]="f-7-N-7.mp3";
        listPaths[77]="f-6-P-4.mp3";
        listPaths[78]="f-758-b.mp3";
        listPaths[79]="f-777-2.mp3";
        listPaths[80]="f-222-4.mp3";
        listPaths[81]="f-7-S-1.mp3";
        listPaths[82]="f-5-N-2-b.mp3";
        listPaths[83]="f-15-A50-a.mp3";
        listPaths[84]="f-6-P-3-b.mp3";
        listPaths[85]="f-21-S-1.mp3";
        listPaths[86]="f-758-a.mp3";
        listPaths[87]="f-33-4.mp3";
        listPaths[88]="f-756.mp3";
        listPaths[89]="f-6-S-1.mp3";
        listPaths[90]="f-8-A0-1.mp3";
        listPaths[91]="f-144-1.mp3";
        listPaths[92]="f-1.2-1-b.mp3";
        listPaths[93]="f-6-S-5.mp3";
        listPaths[94]="f-1.2-1-a.mp3";
        listPaths[95]="f-11-b.mp3";
        listPaths[96]="f-21-N-1.mp3";
        listPaths[97]="f-5-NN-6.mp3";
        listPaths[98]="f-11.mp3";
        listPaths[99]="f-222-3.mp3";
        listPaths[100]="f-22-8.mp3";
        listPaths[101]="f-6-P-3-a.mp3";
        listPaths[102]="f-4-1.mp3";
        listPaths[103]="f-5-S-2.mp3";
        listPaths[104]="f-83.4.mp3";
        listPaths[105]="f-22-13.mp3";
        listPaths[106]="f-5-S-3.mp3";
        listPaths[107]="f-14-3-b.mp3";
        listPaths[108]="f-5-N-1.mp3";
        listPaths[109]="f-7-S-10.mp3";
        listPaths[110]="f-15-A25-a.mp3";
        listPaths[111]="f-7-N-2.mp3";
        listPaths[112]="f-6-PP-5.mp3";
        listPaths[113]="f-4-4.mp3";
        listPaths[114]="f-4-2-b.mp3";
        listPaths[115]="f-22-6.mp3";
        listPaths[116]="f-6-P-1-b.mp3";
        listPaths[117]="f-33-1.mp3";
        listPaths[118]="f-8-A3-2.mp3";
        listPaths[119]="f-8-A3-3.mp3";
        listPaths[120]="f-1.1-5-b.mp3";
        listPaths[121]="f-6-PP-3.mp3";
        listPaths[122]="f-7-S-2.mp3";
        listPaths[123]="f-7-N-6.mp3";
        listPaths[124]="f-8-A4-2.mp3";
        listPaths[125]="f-15-A50.mp3";
        listPaths[126]="f-1.2-2-a.mp3";
        listPaths[127]="f-7-S-11.mp3";
        listPaths[128]="f-5-N-1-a.mp3";
        listPaths[129]="f-7-N-5.mp3";
        listPaths[130]="f-4-3.mp3";
        listPaths[131]="f-757.mp3";
        listPaths[132]="f-5-NN-5.mp3";
        listPaths[133]="f-4-5.mp3";
        listPaths[134]="f-1.2-2-b.mp3";
        listPaths[135]="f-222-1.mp3";
        listPaths[136]="f-1.1-4-b.mp3";
        listPaths[137]="f-7-NN-2.mp3";
        listPaths[138]="f-777-1.mp3";
        listPaths[139]="f-7-S-10-a.mp3";
        listPaths[140]="f-1.mp3";
        listPaths[141]="f-11-a.mp3";
        listPaths[142]="f-222-2.mp3";
        listPaths[143]="f-6-P-1-a.mp3";
        listPaths[144]="f-1.1-3-b.mp3";
        listPaths[145]="f-22-4.mp3";
        listPaths[146]="f-7-S-10-b.mp3";
        listPaths[147]="f-6-S-3.mp3";
        listPaths[148]="f-6-S-8.mp3";
        listPaths[149]="f-7-N-1.mp3";
        listPaths[150]="f-1.1-2.mp3";
        listPaths[151]="f-44-2.mp3";
        listPaths[152]="f-759.mp3";
        listPaths[153]="f-1.2-1.mp3";
        listPaths[154]="f-14-2.mp3";
        listPaths[155]="f-12-A25-1.mp3";
        listPaths[156]="f-22-5.mp3";
        listPaths[157]="f-6-S-6.mp3";
        listPaths[158]="f-22-15.mp3";
        listPaths[159]="f-3-2.mp3";
        listPaths[160]="f-7-NN-1.mp3";
        listPaths[161]="f-15-A25-b.mp3";
        listPaths[162]="f-22-3.mp3";
        listPaths[163]="f-1.1-1.mp3";
        listPaths[164]="f-14-3.mp3";
        listPaths[165]="f-22-11.mp3";
        listPaths[166]="f-22-2.mp3";
        listPaths[167]="f-1.2-2.mp3";
        listPaths[168]="f-8-A8-1.mp3";
        listPaths[169]="f-1.1-4-a.mp3";
        listPaths[170]="f-83.3.mp3";
        listPaths[171]="f-6-S-2.mp3";
        listPaths[172]="f-83.1.mp3";
        listPaths[173]="f-6-PP-2.mp3";
        listPaths[174]="f-6-PP-1.mp3";
        listPaths[175]="f-8-A2-1.mp3";
        listPaths[176]="f-14-1.mp3";
        listPaths[177]="f-12.3.mp3";
        listPaths[178]="f-8-A5-1.mp3";
        listPaths[179]="f-44-1.mp3";
        listPaths[180]="f-8-A4-3.mp3";
        listPaths[181]="f-4-2-a.mp3";
        listPaths[182]="f-12-A50.mp3";
        listPaths[183]="f-22-1.mp3";
        listPaths[184]="f-8-A9-1.mp3";
        listPaths[185]="f-1.1-3.mp3";
        listPaths[186]="f-15-A25.mp3";
        listPaths[187]="f-4-2.mp3";
        listPaths[188]="f-1-a.mp3";
        listPaths[189]="f-1-b.mp3";
        return listPaths;
    }
    public List<String> questBuilder(int option_code,String operation)
    {
        //int number = new Random().nextInt(6);
        option_code++;
        String operatio1 = operation.replace(" ","");
        operatio1 = operatio1.substring(0,operatio1.length()-1);
        LinkedList<String> list = new LinkedList<String>();
        switch(option_code)
        {
            case 0:
            {
                list.add(DataManager.getOperation(operatio1));
                break;
            }
            case 1:
            {
                list.add(DataManager.getPhrases("f-4-1"));
                list.add(DataManager.getOperation(operatio1));
                break;
            }
            case 2:
            {
                // TODO: пока нет разрезанных фраз  попавил
                list.add(DataManager.getPhrases("f-4-2-a"));
                list.add(getNameNegative(0));
                list.add(DataManager.getPhrases("f-4-2-b"));
                list.add(DataManager.getOperation(operatio1));
                break;
            }
            case 3:
            {
                list.add(DataManager.getPhrases("f-4-3"));
                list.add(DataManager.getOperation(operatio1));
                break;

            }
            case 4:
            {
                list.add(getNameNegative(0));
                list.add(DataManager.getPhrases("f-4-4"));
                list.add(DataManager.getOperation(operatio1));
                break;
            }
            case 5:
            {
                list.add(DataManager.getPhrases("f-4-5"));
                list.add(DataManager.getOperation(operatio1));
                break;
            }
        }
        return list;
    }


//    public List<String> responseWrongBuilder(int x)
//    {
//        //TODO:Переделать
//        String head = "f7";
//        LinkedList<String> list;
//
//        List<DataWord> dataWords = DBManager.getInstance().getDataWords(head,returnNegative(x));
//        list = (LinkedList<String>) generateStrings(dataWords,x,false);
//        return list;
//    }
    
    private  List<String> generateStrings(List<DataWord> dataWords,int x,boolean positive)
    {
        LinkedList<String> list = new LinkedList<String>();
        for(DataWord dw:dataWords)
        {
            switch (dw.getType())
            {
                case DataWord.TYPE_NAME:
                {
                    if(positive)
                    list.add(getNamePositive(x));
                    else
                        list.add(getNameNegative(x));
                    break;
                }
                case DataWord.TYPE_PHRASE:
                {
                    list.add(DataManager.getPhrases(dw.getFileName()));
                }
            }
        }
        return list;
    }

    private String addNegativePositive(String str,int x)
    {
        switch (x)
        {
            case 0:
            {
                str+="-S";
                break;
            }
            case 1:
            {
                str+="-N";
                break;
            }
            case 2:
            {
                str+="-NN";
                break;
            }
            default:
                break;
        }
        return str;
    }

    private String returnNegative(int x)
    {
        switch (x)
        {
            case 0:
            {
                return "S";
            }
            case 1:
            {
                return "N";
            }
            case 2:
            {
                return "NN";
            }
            default:
                return "";
        }

    }

    private String returnPositive(int x)
    {
        switch (x)
        {
            case 0:
            {
                return "S";
            }
            case 1:
            {
                return "P";
            }
            case 2:
            {
                return "PP";
            }
            default:
                return "";
        }

    }

    private String addNegativePositiveOK(String str,int x)
    {
        switch (x)
        {
            case 0:
            {
                str+="-S";
                break;
            }
            case 1:
            {
                str+="-P";
                break;
            }
            case 2:
            {
                str+="-PP";
                break;
            }
            default:
                break;
        }
        return str;
    }

//    public List<String> responceInaccuratelyBuilder(int x)
//    {
//        //TODO:Переделать
//        String head = "f21";
//
//        LinkedList<String> list;
//
//        List<DataWord> dataWords = DBManager.getInstance().getDataWords(head,returnNegative(x));
//        list = (LinkedList<String>) generateStrings(dataWords,x,false);
//        return list;
//    }

//    public List<String> responceSilenceBuilder(int x)
//    {
//        //TODO:Переделать
//        String head = "f5";
//        LinkedList<String> list;
//        List<DataWord> dataWords = DBManager.getInstance().getDataWords(head,returnNegative(x));
//        list = (LinkedList<String>) generateStrings(dataWords,x,false);
//        return list;
//    }
//
//    public List<String> responceOkBuilder(int x,int animationID)
//    {
//        LinkedList<String> list;
//        List<DataWord> dataWords = DBManager.getInstance().getPhrasByAnimationId(animationID);
//        list = (LinkedList<String>) generateStrings(dataWords,x,true);
//        return list;
//    }

//    public List<String> responceInaccuratelyBuilder(int x)
//    {
//        //TODO:Переделать
//        String head = "f21";
//
//        LinkedList<String> list;
//
//        List<DataWord> dataWords = DBManager.getInstance().getDataWords(head,returnNegative(x));
//        list = (LinkedList<String>) generateStrings(dataWords,x,false);
//        return list;
//    }

//    public List<String> responceSilenceBuilder(int x)
//    {
//        //TODO:Переделать
//        String head = "f5";
//        LinkedList<String> list;
//        List<DataWord> dataWords = DBManager.getInstance().getDataWords(head,returnNegative(x));
//        list = (LinkedList<String>) generateStrings(dataWords,x,false);
//        return list;
//    }

//    public List<String> responceOkBuilder(int x)
//    {
//        //TODO:Переделать
//        String head = "f6";
//
//        LinkedList<String> list;
//
//        List<DataWord> dataWords = DBManager.getInstance().getDataWords(head,returnPositive(x));
//        list = (LinkedList<String>) generateStrings(dataWords,x,true);
//        return list;
//    }

    public List<String> testResult(int countOKResponses)
    {
        String head="";
        if(countOKResponses==10)head+="f-144";
        else head+= "f-8-A"+countOKResponses;

        LinkedList<String> listTemp = new LinkedList<String>();
        LinkedList<String> list = new LinkedList<String>();

        for(String phras:listPaths)
        {
            if(phras.indexOf(head)!=-1)
                listTemp.add(phras);
        }
        int pos = new Random().nextInt(listTemp.size());
        list.add(DataManager.getPhrasesNotExt(listTemp.get(pos)));
        return list;
    }


    public List<String> informationСlearlyOneNumberBuilder()
    {
        //TODO:Переделать
        String head = "f-3";

        LinkedList<String> listTemp = new LinkedList<String>();
        LinkedList<String> list = new LinkedList<String>();

        for(String phras:listPaths)
        {
            if(phras.indexOf(head)!=-1&&phras.indexOf("f-33")==-1&&phras.indexOf("f-32")==-1)
                listTemp.add(phras);
        }
        int pos = new Random().nextInt(listTemp.size());
        list.add(DataManager.getPhrasesNotExt(listTemp.get(pos)));
        return list;
    }

    public List<String> repeatQuestBuilder()
    {
        //TODO:Переделать
        String head = "f-32";

        LinkedList<String> listTemp = new LinkedList<String>();
        LinkedList<String> list = new LinkedList<String>();

        for(String phras:listPaths)
        {
            if(phras.indexOf(head)!=-1)
                listTemp.add(phras);
        }
        int pos = new Random().nextInt(listTemp.size());
        list.add(DataManager.getPhrasesNotExt(listTemp.get(pos)));
        return list;
    }

    public List<String> correctlyResult(String name,String result)
    {
        //TODO:Переделать
        String head = "f-33";

        LinkedList<String> listTemp = new LinkedList<String>();
        LinkedList<String> list = new LinkedList<String>();

        for(String phras:listPaths)
        {
            if(phras.indexOf(head)!=-1)
                listTemp.add(phras);
        }
        int pos = new Random().nextInt(listTemp.size());
        list.add(getNameNegative(0));
        list.add(DataManager.getPhrasesNotExt(listTemp.get(pos)));
        list.add(DataManager.getCorrectResult(result));
        return list;
    }

    public LinkedList<String> standartGreeting(int number)
    {
        LinkedList<String> list=new LinkedList<String>();
        switch (number)
        {
            case 0:{
                list.add(DataManager.getPhrases("f-1.1-1"));
                break;}
            case 1:{
                list.add(DataManager.getPhrases("f-1.1-2-a"));
                list.add(getNamePositive(0));
                list.add(DataManager.getPhrases("f-1.1-2-b"));
                break;}
            case 2:{
                list.add(DataManager.getPhrases("f-1.1-3-a"));
                list.add(getNamePositive(0));
                list.add(DataManager.getPhrases("f-1.1-3-b"));
                break;}
            case 3:{
                list.add(DataManager.getPhrases("f-1.1-4-a"));
                list.add(getNamePositive(0));
                list.add(DataManager.getPhrases("f-1.1-4-b"));
                break;}
            case 4:{
                list.add(DataManager.getPhrases("f-1.1-5-a"));
                list.add(getNamePositive(0));
                list.add(DataManager.getPhrases("f-1.1-5-b"));
                break;}
            case 5:{
                list.add(DataManager.getPhrases("f-1.1-6"));
                break;}
            case 6:{
                list.add(DataManager.getPhrases("f-1.1-7"));
                break;}
            default:break;
        }
        return list;
    }

    public int getConstructorGreetingByAudio(String audio)
    {
        String [] audios = {"f-1.1-1","f-1.1-2-a","f-1.1-3-a","f-1.1-4-a","f-1.1-5-a","f-1.1-6","f-1.1-7"};
        for(int i=0;i<audios.length;i++)
        {
            if(audio.contains(audios[i]))
                return i;
        }
        return -1;
    }
    public String getNameNegative(int x)
    {
        SharedPreferences saved_names= PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
        switch (x)
        {
            case 0:
            {
                return DataManager.getNameTest(saved_names.getString("pathStandart","-"));
            }
            case 1:
            {
                return DataManager.getNameTest(saved_names.getString("pathStrogoe","-"));
            }
            case 2:
            {
                return DataManager.getNameTest(saved_names.getString("pathStrogoe","-"));
            }
            default:
                return "";
        }
    }

    public String getName(int x)
    {
        SharedPreferences saved_names= PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
        switch (x)
        {
            case ManagerActions.NAME_S:
            {
                return DataManager.getNameTest(saved_names.getString("pathStandart","-"));
            }
            case ManagerActions.NAME_N:
            {
                return DataManager.getNameTest(saved_names.getString("pathStrogoe","-"));
            }
            case ManagerActions.NAME_P:
            {
                return DataManager.getNameTest(saved_names.getString("pathLascatelnoe","-"));
            }
            default:
                return "";
        }
    }
    public String getNamePositive(int x)
    {
        SharedPreferences saved_names= PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
        switch (x)
        {
            case 0:
            {
                return DataManager.getNameTest(saved_names.getString("pathStandart","-"));
            }
            case 1:
            {
                return DataManager.getNameTest(saved_names.getString("pathLascatelnoe","-"));
            }
            case 2:
            {
                return DataManager.getNameTest(saved_names.getString("pathLascatelnoe","-"));
            }
            default:
                return "";
        }
    }


}
