package com.testsocial.social;

import android.app.Activity;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: Defafault
 * Date: 02.06.12
 * Time: 15:30
 * To change this template use File | Settings | File Templates.
 */
 public abstract class SocialCallBack {

    WeakReference<Activity> activity;

    public SocialCallBack(Activity paramActivity)
    {
        if (paramActivity != null)
            this.activity = new WeakReference(paramActivity);
    }

    public void error(final Throwable paramThrowable)
    {
        paramThrowable.printStackTrace();

        if ((this.activity != null) && (this.activity.get() != null))
            ((Activity)this.activity.get()).runOnUiThread(new Runnable()
            {
                public void run()
                {
                    Toast.makeText(((Activity) SocialCallBack.this.activity.get()).getApplicationContext(), paramThrowable.getMessage(), 1).show();
                }
            });


    }

    public abstract void ready(Object paramObject);


}
