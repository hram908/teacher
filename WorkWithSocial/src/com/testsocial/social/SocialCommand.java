package com.testsocial.social;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: Defafault
 * Date: 02.06.12
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
public abstract class SocialCommand {

    public WeakReference<Activity> activity;
    private SocialCallBack callback;
    public String captcha_key;
    public String captcha_sid;
    public int recursion_count = 0;
    public Object res;

    public SocialCommand(SocialCallBack paramCallback, Activity paramActivity)
    {
        this.callback = paramCallback;
        if (paramActivity != null)
            this.activity = new WeakReference(paramActivity);
    }

    public void reportError(Throwable paramThrowable)
    {
        if (this.callback != null)
        {
            this.callback.error(paramThrowable);
            this.callback = null;
        }
    }

    public void reportSuccess(Object paramObject)
    {
        try
        {
            if (this.callback != null)
            {
                this.callback.ready(paramObject);
                this.callback = null;
            }
        }
        catch (Throwable localThrowable)
        {
            //Helper.reportError(localThrowable);
            localThrowable.printStackTrace();
        }
    }

    public abstract void run()
            throws Exception;

}
