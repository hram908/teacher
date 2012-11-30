package com.testsocial.social;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import com.testsocial.R;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: vladimirmolodkin
 * Date: 01.06.12
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
public class DialogSelectSocial extends Dialog {

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        public void click();
    }


    Activity activity;
    String  picturePath;
    private ClickListener clickListener;

    public DialogSelectSocial(Context context,String pathPicture) {
        super(context);
        activity=(Activity) context;
        this.picturePath = pathPicture;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_social);

        Button btnVK = (Button) findViewById(R.id.dialog_select_social_vk);
        btnVK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogSelectVk dialogSelectVk = new DialogSelectVk(DialogSelectSocial.this.activity,picturePath);
                dialogSelectVk.show();
            }
        });

        Button btnFb = (Button) findViewById(R.id.dialog_select_social_fb);
        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogSelectFb dialogSelectFb = new DialogSelectFb(DialogSelectSocial.this.activity,picturePath);
                dialogSelectFb.show();
            }
        });

        Button btnOther = (Button) findViewById(R.id.dialog_select_social_other);
        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener!=null)
                    clickListener.click();
//                Intent picMessageIntent = new Intent(Intent.ACTION_SEND);
//                picMessageIntent.setType("image/jpeg");
//                File downloadedPic =  new File(picturePath);
//                picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(downloadedPic));
//                activity.startActivity(Intent.createChooser(picMessageIntent, "Отправьте вашу открытку используя:"));

            }
        });

    }



}
