package com.blacksheep.teacher.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.blacksheep.teacher.R;

/**
 * Created with IntelliJ IDEA.
 * User: vladimirmolodkin
 * Date: 17.05.12
 * Time: 0:14
 * To change this template use File | Settings | File Templates.
 */
public class DialogLoadResource extends Dialog{

    Context context;
    ListenerButton listenerButton;
    public DialogLoadResource(Context context,ListenerButton listenerButton) {
        super(context);
        this.context=context;
        this.listenerButton = listenerButton;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_load_resource);
        TextView message=(TextView)findViewById(R.id.dialog_DialogOfflineIternet_message);
        message.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/segoepr.ttf"));
        Button btOk=(Button)findViewById(R.id.dialog_DialogLoadResource_btOk);
        //getWindow().setLayout(400,200);
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listenerButton.ok();
                dismiss();

            }
        });

        Button btnCancel = (Button) findViewById(R.id.dialog_DialogLoadResource_btCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listenerButton.cancel();
               dismiss();
            }
        });
    }

    public interface ListenerButton{
        public void ok();
        public void cancel();

    }

}
