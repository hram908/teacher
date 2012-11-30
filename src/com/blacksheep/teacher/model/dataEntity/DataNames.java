package com.blacksheep.teacher.model.dataEntity;

/**
 * Created by IntelliJ IDEA.
 * User: vovi
 * Date: 18.04.12
 * Time: 1:52
 * To change this template use File | Settings | File Templates.
 */
public class DataNames {

    public static int keyNameStrogoe=0;
    public static int keyNameNeitral=1;
    public static int keyNameStandart=2;
    public static int keyNameLascatelnoee=3;
    public static int keyNameDobroe=4;
    public static int keyPathStandart=5;
    public static int keyPathStrogoe=6;
    public static int keyPathLascatelnoe=7;

    private int id;
    private String nameStrogoe;
    private String nameNeitral;
    private String nameStandart;
    private String nameLascatelnoee;
    private String nameDobroe;
    private String pathStandart;
    private String pathStrogoe;
    private String pathLascatelnoe;




    public DataNames(int id, String nameStrogoe, String nameNeitral, String nameStandart, String nameLascatelnoee, String nameDobroe, String pathStandart,String pathStrogoe, String pathLascatelnoe) {
        this.id = id;
        this.nameStrogoe = nameStrogoe;
        this.nameNeitral = nameNeitral;
        this.nameStandart = nameStandart;
        this.nameLascatelnoee = nameLascatelnoee;
        this.nameDobroe = nameDobroe;
        this.pathStandart = pathStandart;
        this.pathStrogoe=pathStrogoe;
        this.pathLascatelnoe=pathLascatelnoe;

    }
    public String getDataName(int value)
    {
        String name="";
        switch (value)
        {
            case 0:{name= nameStrogoe;break;}
            case 1:{name= nameNeitral;break;}
            case 2:{name= nameStandart;break;}
            case 3:{name= nameLascatelnoee;break;}
            case 4:{name= nameDobroe;break;}
            case 5:{name= pathStandart;break;}
            case 6:{name= pathStrogoe;break;}
            case 7:{name= pathLascatelnoe;break;}
            default:break;

        }
        return name;
    }

}
