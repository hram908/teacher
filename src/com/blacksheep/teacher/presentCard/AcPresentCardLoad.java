package com.blacksheep.teacher.presentCard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.LinearLayout;
import com.blacksheep.teacher.R;
import com.testsocial.social.DialogSelectSocial;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: vovi
 * Date: 16.05.12
 * Time: 2:46
 * To change this template use File | Settings | File Templates.
 */
public class AcPresentCardLoad extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_activity);
        final String path=String.valueOf(this.getIntent().getExtras().get(AcPresentCard.LOAD_ACTIVITY_BG));
        if(path!="")
        {
            LinearLayout llbg=(LinearLayout)findViewById(R.id.ac_load_bg);
            Bitmap bitmapImage = BitmapFactory.decodeFile(path);
            Drawable drawableImage = new BitmapDrawable(bitmapImage);
            llbg.setBackgroundDrawable(drawableImage);
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                finish();

                Intent picMessageIntent = new Intent(Intent.ACTION_SEND);
                picMessageIntent.setType("image/jpeg");
                File downloadedPic =  new File(path);
                picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(downloadedPic));
                startActivity(Intent.createChooser(picMessageIntent, "Отправьте вашу открытку используя:"));



            }
        });
        thread.start();
    }

}
