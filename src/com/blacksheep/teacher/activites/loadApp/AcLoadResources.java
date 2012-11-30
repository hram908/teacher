package com.blacksheep.teacher.activites.loadApp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.activites.greeting.AcGreeting;
import com.blacksheep.teacher.activites.greeting.AcGreetingLoad;
import com.blacksheep.teacher.activites.mainScreen.AcMainTeachScreen;
import com.blacksheep.teacher.dialogs.DialogLoadResource;
import com.blacksheep.teacher.dialogs.DialogOfflineInternet;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.synchronization.DownloadService;
import com.blacksheep.teacher.synchronization.SuperLoader;
import com.testsocial.social.SocialCallBack;
import com.testsocial.social.SocialCommand;
import com.testsocial.social.fb.FacebookWorker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Defafault
 * Date: 16.05.12
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class AcLoadResources extends Activity {


    TextView textView;

    ServiceConnection serviceConnection  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(this.getClass().getName(), "MainActivity onServiceConnected");
            bound = true;
            DownloadService downloadService=  ((DownloadService.LocalBinder) iBinder).getService();
            downloadService.setOnLoadListener(new SuperLoader.onLoadListener() {
                @Override
                public void load(int i, boolean firs, String text) {
                    progressBar.setProgress(i);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(this.getClass().getName(), "MainActivity onServiceDisconnected");
            bound = false;
        }
    };


    boolean bound=false;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_tests);
        textView= (TextView) findViewById(R.id.ac_test_tv_load);
        progressBar= (ProgressBar) findViewById(R.id.ac_test_status_progress);
        progressBar.setMax(100);
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean b = false;
        try {
            b=DownloadService.mustLoadRes();
        } catch (IOException e) {
            DialogOfflineInternet dialogOfflineInternet = new DialogOfflineInternet(this);
            dialogOfflineInternet.setiButtonOk(new DialogOfflineInternet.IButtonOk() {
                @Override
                public void ok() {
                    finish();

                }
            });
            dialogOfflineInternet.show();
        }

        boolean serviceIsActive = isMyServiceRunning();
        if(serviceIsActive)
        {
            AcLoadResources.this.bindService(new Intent(AcLoadResources.this,DownloadService.class),serviceConnection,0);
            return;
        }

        if(b&&!serviceIsActive)
        {
            DialogLoadResource dialogLoadResource = new DialogLoadResource(this,new DialogLoadResource.ListenerButton() {
                @Override
                public void ok() {
                    if(DataManager.init())
                    {
                        AcLoadResources.this.startService(new Intent(AcLoadResources.this,DownloadService.class));
                        AcLoadResources.this.bindService(new Intent(AcLoadResources.this,DownloadService.class),serviceConnection,0);

                        //finish();

                    }
                    else
                    {
                        Toast.makeText(AcLoadResources.this, "Недостаточно места для ресурсов", 2000).show();
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    Thread.sleep(3000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                                }
//                                finish();
//
//                            }
//                        }).start();
                    }

                }

                @Override
                public void cancel() {
                    finish();

                }
            })  ;
            dialogLoadResource.show();



        }
        else
        {   Toast.makeText(AcLoadResources.this, "Все загружено", 2000).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.


                    }

                    AcLoadResources.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            Intent intent = new Intent(AcLoadResources.this, AcGreeting.class);
                            startActivity(intent);
                        }
                    });
                }
            }).start();

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private boolean isMyServiceRunning() {

        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.blacksheep.teacher.synchronization.DownloadService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void clickStopLoad(View v)
    {

        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
        myAlertDialog.setTitle("Предупреждение");
        myAlertDialog.setMessage("Отменить загрузку ресурсов?");
        myAlertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {

                unbindService(serviceConnection);
                stopService(new Intent(AcLoadResources.this,DownloadService.class));

            }});
        myAlertDialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {

            }});
        myAlertDialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isMyServiceRunning())
            unbindService(serviceConnection);
    }
}
