package com.blacksheep.teacher.activites.setting;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.blacksheep.teacher.R;
import com.testsocial.AcGame;
import com.testsocial.social.fb.FacebookWorker;
import com.testsocial.social.vk.VkontakteError;
import com.testsocial.social.vk.VkontakteWorker;


/**
 * Created with IntelliJ IDEA.
 * User: default
 * Date: 4/23/12
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcSettingParent  extends Activity {
    public static String SETTINGS_PARENT_EMAIL="settings_parent_email";
    FacebookWorker facebookWorker;
    VkontakteWorker vkontakteWorker;
    //EditText emailEditText;

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        EditText emailEditText=(EditText)findViewById(R.id.ac_settings_parent_edittext_mail);
        String nameText = emailEditText.getText().toString().toLowerCase();
        String nameHint = emailEditText.getHint().toString().toLowerCase();

        SharedPreferences.Editor stats_editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        if (!nameText.equals(nameHint) && !nameText.equals("")&&isValidEmail(nameText)) {

            stats_editor.putString(SETTINGS_PARENT_EMAIL,nameText);
            stats_editor.commit();

        }


    }

    @Override
    public void onBackPressed() {
        EditText emailEditText=(EditText)findViewById(R.id.ac_settings_parent_edittext_mail);
        String nameText = emailEditText.getText().toString().toLowerCase();
       // if(!isValidEmail(nameText)&&!nameText.equals("")){emailEditText.setBackgroundResource(R.drawable.im_ac_settings_parent_not_valid_inet);Toast.makeText(this,"e-mail введен неправильно",3500).show();return;}
       // else finish();

    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        EditText emailEditText=(EditText)findViewById(R.id.ac_settings_parent_edittext_mail);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        emailEditText.setHint(prefs.getString(SETTINGS_PARENT_EMAIL,"e-mail"));
        emailEditText.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*EditText emailEditText=(EditText)findViewById(R.id.ac_settings_parent_edittext_mail);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        emailEditText.setHint(prefs.getString(SETTINGS_PARENT_EMAIL,"e-mail"));
        emailEditText.setText("");*/
    }

    @Override
    protected void onStop() {
        super.onStop();
/*
        EditText emailEditText=(EditText)findViewById(R.id.ac_settings_parent_edittext_mail);
        String nameText = emailEditText.getText().toString().toLowerCase();
        String nameHint = emailEditText.getHint().toString().toLowerCase();

        SharedPreferences.Editor stats_editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        if (!nameText.equals(nameHint) && !nameText.equals("")&&isValidEmail(nameText)) {

            stats_editor.putString(SETTINGS_PARENT_EMAIL,nameText);
            stats_editor.commit();

        }
*/

    }

    public void clickGame(View v)
    {
        startActivity(new Intent(this,AcGame.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_setting_parent);
        facebookWorker = new FacebookWorker();
        facebookWorker.logout(this);
        vkontakteWorker = new VkontakteWorker();
        TextView email1=(TextView) findViewById(R.id.ac_settings_parent_textview_email1);
        TextView email2=(TextView) findViewById(R.id.ac_settings_parent_textview_email2);
        TextView social1=(TextView) findViewById(R.id.ac_settings_parent_textview_social1);
        TextView social2=(TextView) findViewById(R.id.ac_settings_parent_textview_social2);
        EditText emailEditText=(EditText)findViewById(R.id.ac_settings_parent_edittext_mail);
        email1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        email2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        social1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        social2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        emailEditText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String emailFromPrefs = prefs.getString(SETTINGS_PARENT_EMAIL,"e-mail");
        if (emailFromPrefs.equals("e-mail")) {
            emailEditText.setHint("e-mail");
        } else emailEditText.setHint(emailFromPrefs);



    }



    public void clickEnterVk(View v)
    {
        //facebookWorker.postI(String.valueOf(System.currentTimeMillis()),this);
        vkontakteWorker.authorizeParent(this);

    }

    public void clickTestTEst(View v) throws VkontakteError {

        vkontakteWorker.postWallParent("test parent", this);
    }

    public void clickEnterFb(View v)
    {
         facebookWorker.authorizeParent(this);
    }

    public void clickBack(View v)
    {
        EditText emailEditText=(EditText)findViewById(R.id.ac_settings_parent_edittext_mail);
        String nameText = emailEditText.getText().toString().toLowerCase();
       // if(!isValidEmail(nameText)&&!nameText.equals("")){emailEditText.setBackgroundResource(R.drawable.im_ac_settings_parent_not_valid_inet);
       //     Toast.makeText(this,"e-mail введен неправильно",3500).show();return;}
       // else
        finish();
    }
    private boolean isValidEmail(CharSequence target) {
        try {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
        catch( NullPointerException exception ) {
            return false;
        }
    }

}
