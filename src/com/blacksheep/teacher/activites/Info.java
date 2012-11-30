package com.blacksheep.teacher.activites;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.blacksheep.teacher.R;

/**
 * Created with IntelliJ IDEA.
 * User: vovi
 * Date: 08.09.12
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
public class Info extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.ac_info);
        TextView text=(TextView)findViewById(R.id.ac_info_text);
        text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
    }
    public void clickBack(View view){
        finish();
    }
    public void clickNuance(View view){
        String url = "http://www.nuance.com/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
