package com.example;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.util.TypedValue;
import android.view.View;
import android.widget.*;

import java.io.*;


/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 4/5/12
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcPresentCard extends Activity{

    private ViewFlow viewFlow;
    private String mPath;
    private  float minSize;
    private float maxSize;

    @Override
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ac_present_card);

        viewFlow = (ViewFlow) findViewById(R.id.viewflow);
        ImageView imageView=(ImageView)findViewById(R.id.ac_present_card_agnessa);
        AdtPesentCard adapter = new AdtPesentCard(this,imageView);
        viewFlow.setAdapter(adapter,0);
        setRadioButtonsDesc();
        setTypeface();
        //mPath=getFilesDir().getPath()+"/test.jpeg";
        mPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/teachPresent.jpeg";
        EditText message=(EditText) findViewById(R.id.ac_present_card_message);
        minSize=message.getTextSize()-7;
        maxSize=message.getTextSize()+7;







    }
    private  void setTypeface()
    {

        EditText message=(EditText) findViewById(R.id.ac_present_card_message);
        Button left_slide=(Button)findViewById(R.id.ac_present_card_left_slide);
        Button right_slide=(Button)findViewById(R.id.ac_present_card_right_slide);
        Button send=(Button)findViewById(R.id.ac_present_card_send);
        Button menu=(Button)findViewById(R.id.ac_present_card_main_menu);
        Button zoomIn=(Button)findViewById(R.id.ac_present_card_zoom_in);
        Button zoomOut=(Button)findViewById(R.id.ac_present_card_zoom_out);

        message.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        left_slide.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        right_slide.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        send.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        menu.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        zoomIn.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        zoomOut.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/segoepr.ttf"));
        left_slide.setText("<");
    }
    private void setRadioButtonsDesc()
    {
        RadioButton rb1=(RadioButton)findViewById(R.id.ac_present_card_desk1);
        RadioButton rb2=(RadioButton)findViewById(R.id.ac_present_card_desk2);
        RadioButton rb3=(RadioButton)findViewById(R.id.ac_present_card_desk3);
        RadioButton rb4=(RadioButton)findViewById(R.id.ac_present_card_desk4);
        RadioButton rb5=(RadioButton)findViewById(R.id.ac_present_card_desk5);

        rb2.setChecked(true);
        rb1.setOnCheckedChangeListener(new RadioListener(AcSetting.SETTINGS_DESK_YELLOW));
        rb2.setOnCheckedChangeListener(new RadioListener(AcSetting.SETTINGS_DESK_GREEN));
        rb3.setOnCheckedChangeListener(new RadioListener(AcSetting.SETTINGS_DESK_RED));
        rb4.setOnCheckedChangeListener(new RadioListener(AcSetting.SETTINGS_DESK_BLUE));
        rb5.setOnCheckedChangeListener(new RadioListener(AcSetting.SETTINGS_DESK_VIOLET));
    }
    private class RadioListener implements CompoundButton.OnCheckedChangeListener
    {
        int numberDesc;

        private RadioListener(int numberDesc) {
            this.numberDesc = numberDesc;
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b)
            {
                  setBackground(numberDesc);
            }
        }
    }
    private void setBackground(int number)
    {
        RelativeLayout rl=(RelativeLayout) findViewById(R.id.ac_present_card_background);
        switch (number)
        {
            case 0:{
                rl.setBackgroundResource(R.drawable.back_yellow);
                break;
            }
            case 1:{
                rl.setBackgroundResource(R.drawable.back_green);
                break;
            }
            case 2:{
                rl.setBackgroundResource(R.drawable.back_red);
                break;
            }
            case 3:{
                rl.setBackgroundResource(R.drawable.back_blue);
                break;
            }
            case 4:{
                rl.setBackgroundResource(R.drawable.back_voilet);
                break;
            }
            default:break;

        }

    }
    private void doScreenshot(View v1)
    {
        setVisibleView(false);
        //String mPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/1.jpeg";
        Bitmap bitmap;
        v1.setDrawingCacheEnabled(true);
        bitmap = Bitmap.createBitmap(v1.getDrawingCache());
        v1.setDrawingCacheEnabled(false);

        setVisibleView(true);

        OutputStream fout = null;
        File imageFile = new File(mPath);

        try {
            fout = new FileOutputStream(imageFile);
            //fout=openFileOutput("test.jpeg",Context.MODE_WORLD_READABLE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fout);
            fout.flush();
            fout.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void setVisibleView(boolean visible)
    {
        LinearLayout llTop=(LinearLayout)findViewById(R.id.ac_present_card_top_layout);
        LinearLayout llBottom=(LinearLayout)findViewById(R.id.ac_present_card_bottom_layout);
        LinearLayout llZoom=(LinearLayout)findViewById(R.id.ac_present_card_zoom_layout);
        EditText message=(EditText)findViewById(R.id.ac_present_card_message);
        if (visible)
        {
            llTop.setVisibility(View.VISIBLE);
            llBottom.setVisibility(View.VISIBLE);
            llZoom.setVisibility(View.VISIBLE);
            message.setCursorVisible(true);
        }
        else
        {
            llTop.setVisibility(View.INVISIBLE);
            llBottom.setVisibility(View.INVISIBLE);
            llZoom.setVisibility(View.INVISIBLE);
            message.setCursorVisible(false);
        }

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        viewFlow.onConfigurationChanged(newConfig);
    }
    public void clickNext(View v)
    {
          viewFlow.slideRight();
    }
    public void clickPrevious(View v)
    {
          viewFlow.slideLeft();
    }
    public  void clickMainMenu(View v)
    {

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public  void clickSend(View v)
    {
        RelativeLayout rl=(RelativeLayout) findViewById(R.id.ac_present_card_background) ;
        View v1=rl.getRootView();
        doScreenshot(v1);
        sendPresent();

    }
    private void sendPresent()
    {
            /*Intent picMessageIntent = new Intent(android.content.Intent.ACTION_SEND);
            picMessageIntent.setType("image/jpeg");

            File downloadedPic =  new File(mPath);
            picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(downloadedPic));
            startActivity(Intent.createChooser(picMessageIntent, "Отправьте вашу открытку используя:"));*/
        Intent intent = new Intent(AcPresentCard.this, AcPresentCardLoad.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);

    }
    public  void clickZoomIn(View v)
    {
        final EditText message=(EditText)findViewById(R.id.ac_present_card_message);
        LinearLayout llmessage;
        final float size=message.getTextSize();
        if(size<maxSize){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                float newSize= (float) (size+1);
                message.setTextSize(TypedValue.COMPLEX_UNIT_PX,newSize);
            }
        });
        }


    }
    public  void clickZoomOut(View v)
    {
        final EditText message=(EditText)findViewById(R.id.ac_present_card_message);
        final float size=message.getTextSize();
        if(size>minSize){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                float newSize= (float) (size-1);
                message.setTextSize(TypedValue.COMPLEX_UNIT_PX,newSize);
            }
        });
        }
    }

}