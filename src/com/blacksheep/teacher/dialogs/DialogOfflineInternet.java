package com.blacksheep.teacher.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Button;
import com.blacksheep.teacher.R;

/**
 * Created by IntelliJ IDEA.
 * User: vovi
 * Date: 03.04.12
 * Time: 23:44
 * To change this template use File | Settings | File Templates.
 */
public class DialogOfflineInternet extends Dialog{
    Context context;
    private IButtonOk iButtonOk;
    public DialogOfflineInternet(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_offline_internet);
        TextView message=(TextView)findViewById(R.id.dialog_DialogOfflineIternet_message);
        message.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/segoepr.ttf"));
        Button btOk=(Button)findViewById(R.id.dialog_DialogOfflineInternet_btOk);
        //getWindow().setLayout(400,200);
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                if(iButtonOk!=null)
                    iButtonOk.ok();
                DialogOfflineInternet.this.dismiss();
            }
        });
    }

    public void setiButtonOk(IButtonOk iButtonOk) {
        this.iButtonOk = iButtonOk;
    }

    public interface IButtonOk{
        public void ok();
    }
}
