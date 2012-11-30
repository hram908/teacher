package com.blacksheep.teacher.synchronization;

import android.os.Environment;
import android.os.StatFs;

/**
 * Created with IntelliJ IDEA.
 * User: default
 * Date: 4/19/12
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CheckerData {

    public boolean existSD()
    {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))
            return true;
        return false;
    }

    public int getFreeSdDataByte()
    {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        return statFs.getBlockSize()* statFs.getFreeBlocks();
        //kjjk
    }

    public int getFreeDeviceDataByte()
    {
        StatFs statFs = new StatFs("/");
        return statFs.getBlockSize()* statFs.getFreeBlocks();
    }

}
