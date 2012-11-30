package com.blacksheep.teacher.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import com.blacksheep.teacher.R;

/**
 * Created by IntelliJ IDEA.
 * User: vovi
 * Date: 19.04.12
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
public class DialogRequestName extends Dialog {
    ResponceListener responceListener;
    Activity mActivity;
    EditText name;
    public DialogRequestName(Context context,ResponceListener responceListener1,Activity activity) {
        super(context);
        mActivity=activity;
        responceListener=responceListener1;

    }
    public interface ResponceListener
    {
        public void onClick(String result);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_request_name);
        name=(EditText)findViewById(R.id.dialog_DialogRequestName_name);
        name.setTypeface(Typeface.createFromAsset(mActivity.getAssets(),"fonts/segoepr.ttf"));
        Button btOk=(Button)findViewById(R.id.dialog_DialogRequestName_btOk);
        //getWindow().setLayout(400,200);
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                if(!name.getText().toString().equals(""))
                {
                    responceListener.onClick(name.getText().toString());
                    DialogRequestName.this.dismiss();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
         mActivity.finish();
    }
    public void setHint(final String value)
    {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                name.setHint(value);
            }
        });

    }
}
