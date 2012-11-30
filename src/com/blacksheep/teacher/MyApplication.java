package com.blacksheep.teacher;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.database.DBManager;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/6/12
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyApplication extends Application{

    public static int EXTA_LARGE_DISPLAY=0;
    public static int LARGE_DISPLAY=1;
    public static int NORMAL_DISPLAY=2;
    public static int SMALL_DISPLAY=3;
    private static float COEFFICIENT_EXTA_LARGE_DISPLAY=1.4f;
    private static float COEFFICIENT_LARGE_DISPLAY=1.2f;
    private static float COEFFICIENT_NORMAL_DISPLAY=1.0f;
    private static float COEFFICIENT_SMALL_DISPLAY=0.8f;

    private static int LOW_DENSITY=0;
    private static int MEDIUM_DENSITY=1;
    private static int HIGHT_DENSITY=2;
    private static int EXTRA_HIGHT_DENSITY=3;

    public static boolean startedApp;
    public static MyApplication instance;
    public static MyApplication getInstance(){

        return instance;

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(int typeDisplay) {
        this.typeDisplay = typeDisplay;
    }

    public float getCoefficientTension() {
        return coefficientTension;
    }

    public void setCoefficientTension(float coefficientTension) {
        this.coefficientTension = coefficientTension;
    }

    private int width;
    private int height;
    private int typeDisplay;
    private float coefficientTension;
    private int widthTeach;
    private int heightTeach;
    private int density;

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public int getWidthTeach() {
        return widthTeach;
    }

    public void setWidthTeach(int widthTeach) {
        this.widthTeach = widthTeach;
    }

    public int getHeightTeach() {
        return heightTeach;
    }

    public void setHeightTeach(int heightTeach) {
        this.heightTeach = heightTeach;
    }

    private SharedPreferences prefs;

    public SharedPreferences getPrefs()
    {
        return prefs;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.init(this);
        instance = this;
        DataManager.init();
        setWidthTeach(640);
        setHeightTeach(480);
        Display d=((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        setHeight(d.getHeight());
        setWidth(d.getWidth());
        if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {

            setTypeDisplay(LARGE_DISPLAY);
            setCoefficientTension(COEFFICIENT_LARGE_DISPLAY);
        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {

            setTypeDisplay(NORMAL_DISPLAY);
            setCoefficientTension(COEFFICIENT_NORMAL_DISPLAY);
        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            setTypeDisplay(SMALL_DISPLAY);
            setCoefficientTension(COEFFICIENT_SMALL_DISPLAY);
        }
        else {
            setTypeDisplay(EXTA_LARGE_DISPLAY);
            setCoefficientTension(COEFFICIENT_EXTA_LARGE_DISPLAY);
        }
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        switch(metrics.densityDpi){
            case DisplayMetrics.DENSITY_LOW:
            {
                setDensity(LOW_DENSITY);
                break;
            }
            case DisplayMetrics.DENSITY_MEDIUM:
            {
                setDensity(MEDIUM_DENSITY);
                break;
            }
            case DisplayMetrics.DENSITY_HIGH:
            {
                setDensity(HIGHT_DENSITY);
                break;
            }
            default:
            {
               setDensity(EXTRA_HIGHT_DENSITY);
                break;
            }
        }

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

    }
    public int getTextSize()
    {
        int textSize=22;
        DisplayMetrics metrics1 = this.getResources().getDisplayMetrics();
        int width = metrics1.widthPixels;
        switch (typeDisplay)
        {
            case 0:
            {
                if(width<=1024)textSize=45;
                else if (width>=1280)textSize=50;
                break;
            }
            case 1:
            {
                if(density==LOW_DENSITY) textSize=48;
                else
                {
                    if(width==1024)textSize=30;
                    else textSize=31;
                }
                break;
            }
            case 2:
            {
                if(density==MEDIUM_DENSITY|density==EXTRA_HIGHT_DENSITY)textSize=18;
                else
                {
                    if(width==1024)textSize=29;
                    else textSize=22;
                }
                break;
            }
            case 3:
            {
                break;
            }
        }
        return textSize;
    }
}
