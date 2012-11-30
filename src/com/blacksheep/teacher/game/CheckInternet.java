package com.blacksheep.teacher.game;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.dialogs.DialogOfflineInternet;

/**
 * Created by IntelliJ IDEA.
 * User: vovi
 * Date: 03.04.12
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class CheckInternet  {
    Context mContext;
    DialogOfflineInternet dialog;
    public  CheckInternet(Context context)
    {
        mContext=context;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnected()) {
            Log.v("status", "ONLINE");
            return true;
        }
        else {
            Log.v("status", "OFFLINE");
            return false;
        }
    }
    public void dialogAppear()
    {
        dialog=new DialogOfflineInternet(mContext);
        dialog.show();
    }
    public DialogOfflineInternet getDialog()
    {
         return dialog;
    }
}
