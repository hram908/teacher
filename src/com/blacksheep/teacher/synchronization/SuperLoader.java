package com.blacksheep.teacher.synchronization;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.model.DataManager;
import com.blacksheep.teacher.model.dataEntity.DataAnimationToAudio;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;
import com.blacksheep.teacher.model.dataEntity.DataContentForLecture;
import com.blacksheep.teacher.model.dataEntity.DataImageAnimationJson;
import com.blacksheep.teacher.model.database.DBManager;
import com.blacksheep.teacher.model.database.TeachDB;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: default
 * Date: 4/27/12
 * Time: 5:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class SuperLoader {

    private onLoadListener onLoadListener;
    private static final int hw320x420 = 3;
    private static final int hw480x640 = 4;

    private static final int COUNT_FAIL_LOAD=5;

    //private static final String domain = "http://uchilka.pro";
    private static final String domain = "http://resources.uchilka.pro";

    public void loadAnimationInfoForDbAndMountAnimAndAudioFolders() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(domain + "/animations.json");
        Log.i(this.getClass().getName(),domain +  "/animations.json");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        String response = EntityUtils.toString(httpResponse.getEntity());
        Gson gson = new Gson();
        Collection<DataAnimation> data = gson.fromJson(response, new TypeToken<Collection<DataAnimation>>() {
        }.getType());
        DBManager.getInstance().fillAnimations(data);
        DataManager.mountAnimationFolders(data);
        DataManager.mountAudiosFolders();
    }

    public static int  getHwForLoad()
    {
        int h = MyApplication.getInstance().getWidth();
        if(h>=480)
            return hw480x640;
        return hw320x420;
    }

    public void loadImagesForDb(boolean [] downloader) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        int targetLoad = getHwForLoad();

        Collection<DataAnimation> data = DBManager.getInstance().getAnimations();
        for (DataAnimation da : data) {
            //http://talking-teacher.black-sheep.ru/images_list/1/1.json
            if(!downloader[0])
                return;
            HttpGet httpGet = new HttpGet(domain + "/images_list/" + da.getId() + "/"+targetLoad+".json");
            Log.i(this.getClass().getName(), domain + "/images_list/" + da.getId() + "/"+targetLoad+".json");
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity());
            Gson gson = new Gson();
            Collection<DataImageAnimationJson> datal = gson.fromJson(response, new TypeToken<Collection<DataImageAnimationJson>>() {
            }.getType());
            DBManager.getInstance().fillImageAnimation(datal);
        }
    }

    public void loadAudioForLectureForDb(boolean[] downloader) throws IOException {
        DBManager.getInstance().sqLiteDatabase.delete(TeachDB.TABLE_NAME_AUDIO_TO_ANIMATION,null,null);
        HttpClient httpClient = new DefaultHttpClient();
        Collection<DataAnimation> data = DBManager.getInstance().getAnimations();
        for (DataAnimation da : data) {
            if(!downloader[0])
                return;
            //http://talking-teacher.black-sheep.ru/images_list/1/1.json
            HttpGet httpGet = new HttpGet(domain + "/audios_list/" + da.getId() + ".json");
            Log.i(this.getClass().getName(),domain +  "/audios_list/" + da.getId() + ".json");
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity());
            Gson gson = new Gson();
            Collection<DataAnimationToAudio> datal = gson.fromJson(response, new TypeToken<Collection<DataAnimationToAudio>>() {
            }.getType());
            DBManager.getInstance().fillAnimationToAudio(datal);
        }
    }

    public void loadContentToLectureForDB(boolean[] downloader) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        DBManager.getInstance().sqLiteDatabase.delete(TeachDB.TABLE_NAME_CONTENT_TO_LECTURE,null,null);
        for (int i = 1; i <= 11; i++) {
            if(!downloader[0])
                return;
            HttpGet httpGet = new HttpGet(domain + "/lecture/" + i + "/content_to_lectures.json");
            Log.i(this.getClass().getName(),domain +  "/lecture/" + i + "/content_to_lectures.json");
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity());
            Gson gson = new Gson();
            Collection<DataContentForLecture> datal = gson.fromJson(response, new TypeToken<Collection<DataContentForLecture>>() {
            }.getType());
            DBManager.getInstance().fillContent(datal);
        }
    }

    public boolean loadImagesAndAudio(boolean[] downloader) throws IOException {

        Collection<DataAnimation> data= DBManager.getInstance().getAnimations();

        if(onLoadListener!=null)
            onLoadListener.load(data.size(),true,"");

        int i=0;
        for(DataAnimation daj:data)
        {
            if(onLoadListener!=null)
                onLoadListener.load(i,false,"");
            i++;
            if(daj.isSync())
               continue;
            Collection<DataImageAnimationJson> dataImageAnimationJsons = DBManager.getInstance().getImagesAnimationsByAnimationId(daj.getId());
            Collection<DataAnimationToAudio> dataAnimationToAudios = DBManager.getInstance().getAudiosByAnimationID(daj.getId());
            if (loadAudiosToAnimation(downloader[0], daj, dataAnimationToAudios)) return false;
            // DataManager.initTEST();
            if (loadImagesToAnimation(downloader[0], daj, dataImageAnimationJsons)) return false;
        }
        return true;

//        Collection<DataImageAnimationJson> dataImageAnimationJsons = DBManager.getInstance().getImagesAnimations();
//        DataManager.initTEST();
//        for (DataImageAnimationJson di : dataImageAnimationJsons) {
//            loadImage(di.getId(),"http://talking-teacher.black-sheep.ru" + di.getFile_url(), DataManager.getAbsolutePath() + DataManager.PATH_HEAD_FOLDER + DataManager.PATH_ANIMATION + di.getAnimation_id() + "/" + di.getImage_file_name());


    }

    private boolean loadImagesToAnimation(boolean b, DataAnimation daj, Collection<DataImageAnimationJson> dataImageAnimationJsons) throws IOException {
        boolean animSync = true;
        for (DataImageAnimationJson di : dataImageAnimationJsons)
        {
            if(b)
            {
                int countFail=0;
                while(true)
                {
                    try{
                        animSync = loadImage(di.getId(),domain + di.getFile_url(), DataManager.getAbsolutePath() + DataManager.PATH_HEAD_FOLDER + DataManager.PATH_ANIMATION + di.getAnimation_id() + "/" + di.getImage_file_name());
                        break;
                    }
                    catch (IOException exc)
                    {
                        if(countFail>COUNT_FAIL_LOAD)
                        {
                            throw new IOException();
                        }
                        else
                            countFail++;
                    }


                }
              //  onLoadListener.load(i,false,di.getImage_file_name());
            }
            else
            {
                animSync=false;
                return true;
            }
        }
        DBManager.getInstance().setAnimationSync(daj.getId(),animSync);
        return false;
    }

    private boolean loadAudiosToAnimation(boolean b, DataAnimation daj, Collection<DataAnimationToAudio> dataAnimationToAudios) throws IOException {
        for(DataAnimationToAudio audio:dataAnimationToAudios)
        {
            if(b)
            {
                int countFail=0;
                while(true)
                {
                    try{
                        String path = DataManager.getAbsolutePath() + DataManager.PATH_HEAD_FOLDER + DataManager.PATH_AUDIO;
                        if(daj.getAnimation_type()==DataAnimation.ANIMATION_TYPE_AUDIO_RESOURCES)
                        {
                             switch (daj.getOption_code())
                             {
                                 case DataAnimation.OPTION_CODE_AUDIO_RESOURCES_QUEST:
                                 {
                                     path+=DataManager.PATH_OPERATION;
                                     break;
                                 }
                                 case DataAnimation.OPTION_CODE_AUDIO_RESOURCES_RESPONSE:
                                 {
                                     path+=DataManager.PATH_RESULT;
                                     break;
                                 }
                                 case DataAnimation.OPTION_CODE_AUDIO_RESOURCES_NAME:
                                 {
                                     path+=DataManager.PATH_NAMES;
                                     break;
                                 }
                             }
                        }
                        else
                        {
                            path+=DataManager.PATH_PHRASES;
                        }
                        loadAudio(domain + audio.getFile_url(), path + audio.getAudio_file_name());
                        break;
                    }
                    catch (IOException exc)
                    {
                        if(countFail>COUNT_FAIL_LOAD)
                        {
                            throw new IOException();
                        }
                        else
                            countFail++;
                    }


                }
                //  onLoadListener.load(i,false,di.getImage_file_name());
            }
            else
            {
                return true;
            }
        }
        return false;
    }

    public boolean loadImage(int id,String urlStr, String fileName) throws IOException {



        Log.i(this.getClass().getName(), fileName);
        Log.i(this.getClass().getName(), urlStr);

        File file = new File(fileName);
        if(file.exists())
        {
            DBManager.getInstance().setImagesSync(id,true);
            return true;
        }

            URL url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            connection.connect();
            //System.out.println(fileName);
            // this will be useful so that you can show a typical 0-100% progress bar
            int fileLength = connection.getContentLength();
            // download the file
            InputStream input = new BufferedInputStream(url.openStream());

            file.createNewFile();
            OutputStream output = new FileOutputStream(file);

            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                //publishProgress((int) (total * 100 / fileLength));
                //knnkn
               // Log.i(this.getClass().getName(), String.valueOf((int) (total * 100 / fileLength)));
                //System.out.println((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
            DBManager.getInstance().setImagesSync(id,true);
            return true;

    }

    public boolean loadAudio(String urlStr, String fileName) throws IOException {

        Log.i(this.getClass().getName(), fileName);
        Log.i(this.getClass().getName(), urlStr);

        File file = new File(fileName);
        if(file.exists())
        {

            return true;
        }

        URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        connection.connect();
        //System.out.println(fileName);
        // this will be useful so that you can show a typical 0-100% progress bar
        int fileLength = connection.getContentLength();
        // download the file
        InputStream input = new BufferedInputStream(url.openStream());

        file.createNewFile();
        OutputStream output = new FileOutputStream(file);

        byte data[] = new byte[1024];
        long total = 0;
        int count;
        while ((count = input.read(data)) != -1) {
            total += count;
            // publishing the progress....
            //publishProgress((int) (total * 100 / fileLength));
            //knnkn
            // Log.i(this.getClass().getName(), String.valueOf((int) (total * 100 / fileLength)));
            //System.out.println((int) (total * 100 / fileLength));
            output.write(data, 0, count);
        }

        output.flush();
        output.close();
        input.close();
        return true;

    }

    public void setOnLoadListener(SuperLoader.onLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }



    public interface onLoadListener
    {
        public void load(int i,boolean firs,String text);
    }


}
