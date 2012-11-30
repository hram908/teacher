package com.blacksheep.teacher.synchronization;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.*;
import android.util.Log;
import com.blacksheep.teacher.MyApplication;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: default
 * Date: 5/4/12
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadService extends Service {


    private static final String RESOURCES_IS_LOAD = "RESOURCES_IS_LOAD";
    private NotificationHelper mNotificationHelper;
    private static final String DOCUMENT_VIEW_STATE_PREFERENCES = "DjvuDocumentViewState";

    private Looper mServiceLooper;
   // private ServiceHandler mServiceHandler;
    String downloadUrl;
    public static boolean serviceState = false;
    private int count;
    private SuperLoader.onLoadListener onLoadListener;


    public static boolean mustLoadRes() throws IOException {

      //  SuperLoader superLoader = new SuperLoader();
      //  superLoader.loadAnimationInfoForDbAndMountAnimAndAudioFolders();


       return !MyApplication.getInstance().getPrefs().getBoolean(RESOURCES_IS_LOAD,false);
//           return ;
//
//
//       Collection<DataAnimation> animations=  DBManager.getInstance().getAnimations();
//        if(animations.size()==0)
//            return true;
//        for(DataAnimation da:animations)
//        {
//            if(!da.isSync())
//                return true;
//        }
//        return false;
    }


    private final IBinder myBinder = new LocalBinder();

    public void setOnLoadListener(SuperLoader.onLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    public class LocalBinder extends Binder {
        public DownloadService getService() {
            return DownloadService.this;
        }
    }




    @Override
    public void onCreate() {
       // android.os.Debug.waitForDebugger();
        super.onCreate();
        serviceState = true;
      //  HandlerThread thread = new HandlerThread("ServiceStartArguments", 1);
      //  thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
      //  mServiceLooper = thread.getLooper();
        //mServiceHandler = new ServiceHandler(mServiceLooper);

        Log.d(getClass().getName(), "onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(getClass().getName(), "onStartCommand");

//        Bundle extra = intent.getExtras();
//        if (extra != null) {
//            String downloadUrl = extra.getString("downloadUrl");
//            Log.d("URL", downloadUrl);
//
//            this.downloadUrl = downloadUrl;
//        }
//
//        Message msg = mServiceHandler.obtainMessage();
//        msg.arg1 = startId;
//        mServiceHandler.sendMessage(msg);

        work();

        // If we get killed, after returning from here, restart
        //return START_STICKY;
        Log.d(getClass().getName(), "START_REDELIVER_INTENT");
        return START_NOT_STICKY;
    }
    Thread working;

    private void work()
    {
        mNotificationHelper = new NotificationHelper(this);
        mNotificationHelper.createNotification();
       working =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean success = downloadFile();
                    onLoadListener.load(100,false,"ok");
                   // boolean success =  downloadFileTest();
                    if(success)
                    {
                        addFlagLoadResources();
                        mNotificationHelper.createNotificationCompleate();
                    }
                 //   DownloadService.this.stopService()

                } catch (IOException e) {

                    Log.d(getClass().getName(), "server fail or internet");
                    mNotificationHelper.createNotificationError();
                  //  Toast.makeText(DownloadService.this, "Ошибка сети", 700).show();
                }
                catch (Exception e)
                {
                    mNotificationHelper.createNotificationError();
                    Log.d(getClass().getName(), "DESTORY Thread");
                    //Toast.makeText(DownloadService.this, "Ошибка", 700).show();
                }
                finally {
                    mNotificationHelper.completed();
                    DownloadService.this.stopService(new Intent(DownloadService.this,DownloadService.class));
                }


            }
        });
        working.start();
    }

    public void addFlagLoadResources()
    {
       SharedPreferences.Editor edit= MyApplication.getInstance().getPrefs().edit();
       edit.putBoolean(RESOURCES_IS_LOAD,true);
       edit.commit();
    }


    @Override
    public void onDestroy() {
       Downloader[0]=false;
        Log.d(getClass().getName(), "DESTORY");
        serviceState = false;
        mNotificationHelper.completed();
         super.onDestroy();

        //Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        Log.d(getClass().getName(), "Bind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(getClass().getName(), "Unbind");
        onLoadListener=null;
        return super.onUnbind(intent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public  boolean downloadFile() throws IOException {


        Downloader[0] = true;
        SuperLoader superLoader = new SuperLoader();


       superLoader.loadContentToLectureForDB(Downloader);
//
        if(mNotificationHelper!=null)
            mNotificationHelper.progressUpdate(1, "1");
//
        if(!Downloader[0])
            return false;
        superLoader.loadAnimationInfoForDbAndMountAnimAndAudioFolders();
//
  //     mNotificationHelper.progressUpdate(2, "2");
//
//
     //   mNotificationHelper.progressUpdate(1, "1");
        if(!Downloader[0])
            return false;
        superLoader.loadAudioForLectureForDb(Downloader);
//
//        if(!Downloader[0])
//            return false;
        superLoader.loadImagesForDb(Downloader);

        mNotificationHelper.progressUpdate(2, "2");

        if(!Downloader[0])
            return false;
        superLoader.setOnLoadListener(new SuperLoader.onLoadListener() {
            @Override
            public void load(int i, boolean firs, String txt) {
                if (firs)
                    count = i;
                else {
                    if(mNotificationHelper!=null)
                         mNotificationHelper.progressUpdate((int) ((float) i / count * 100), txt);
                    if(onLoadListener!=null)
                        onLoadListener.load(i,firs,txt);
                }
            }
        });
        return superLoader.loadImagesAndAudio(Downloader);



    }

    public  boolean downloadFileTest() throws IOException {
        Downloader[0]=true;
                 for (int i =0;i<101;i++)
                 {
                    if(!Downloader[0])
                        return false;
                    if(mNotificationHelper!=null)
                        mNotificationHelper.progressUpdate(i,"test");
                    if(onLoadListener!=null)
                        onLoadListener.load(i,true,"txt");
                     Log.i(this.getClass().getName(),"Servic work "+i);
                     try {
                         Thread.sleep(2000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                     }

                 }
        return true;

    }



    public boolean[] Downloader = new boolean[1];


}