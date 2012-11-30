package com.blacksheep.teacher.model;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.game.animated_view.ThreadLoadAnimation;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 2/26/12
 * Time: 5:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataManager {

    public static final String PATH_LECTURE = "android/data/lecture";
    public static final String TEST_PATH_LECTURE = "/testteach/";
    public static final String PATH_HEAD_FOLDER = "/TeachData/";
    public static final String PATH_ANIMATION = "animations/";
    public static final String PATH_AUDIO = "audios/";
    public static final String PATH_LECTURES = "lectures/";
    public static final String PATH_PHRASES = "phrases/";
    public static final String PATH_NAMES = "names/";
    public static final String PATH_OPERATION = "op/";
    public static final String PATH_RESULT = "results/";

    public static final String exten = ".mp3";

    private static String absolutePath;

    public static void LoadDataForLecture() {

    }

    byte tr = 0;

    public LinkedList<byte[]> getAnimation(DataAnimation animation) throws IOException {
        String name = animation.getName();
        name += "/";
        //String nameatd = "at_d";
        String absolutePath = getAbsolutePathSD();
        LinkedList<byte[]> frameList = new LinkedList<byte[]>();
        for (int i = 1; i <= animation.getFrameCount(); i++) {
            //FileInputStream fileInputStreamatd = new FileInputStream(new File(absolutePath + TEST_PATH_LECTURE+name, nameatd + String.valueOf(i*2) + ".jpg"));
            FileInputStream fileInputStreamatd = new FileInputStream(new File(absolutePath + PATH_HEAD_FOLDER + PATH_ANIMATION + name, i + ".jpg"));

            byte[] matd = new byte[(int) fileInputStreamatd.getChannel().size()];
            fileInputStreamatd.read(matd);
            frameList.add(matd);
            fileInputStreamatd.close();
        }
        return frameList;
    }

    public void fillAnimation(DataAnimation animation, LinkedList<byte[]> buffer) throws IOException {
        String name = animation.getName();
        name += "/";
        // String nameatd = "at_d";
        String absolutePath = getAbsolutePathSD();
        for (int i = 1; i <= animation.getFrameCount(); i++) {
            //Todo Ошибка в тесте и экзамене но не вылетает
            FileInputStream fileInputStreamatd = new FileInputStream(new File(absolutePath + PATH_HEAD_FOLDER + PATH_ANIMATION + name, i + ".jpg"));
            byte[] matd = new byte[(int) fileInputStreamatd.getChannel().size()];
            fileInputStreamatd.read(matd);
            buffer.add(matd);
            fileInputStreamatd.close();
        }

    }

    public void fillAnimationModify(DataAnimation animation, LinkedList<byte[]> buffer, boolean[] runFlag) throws IOException {
        String name = animation.getFolderName();
        name += "/";
        //String absolutePath = getAbsolutePathSD();
        String absolutePath = getAbsolutePath();
        //    String[] list = new File("/data/data/com.blacksheep.teacher/files/TeachData/animations/10/").list();
        Log.i("load anim path", absolutePath + PATH_HEAD_FOLDER + PATH_ANIMATION + name);
        String[] list = new File(absolutePath + PATH_HEAD_FOLDER + PATH_ANIMATION + name).list();
        Arrays.sort(list);
        Log.i("load image all", Arrays.toString(list));
        int size = list.length;
        if (animation.getEndFrameAnim() != 0)
            size = animation.getEndFrameAnim();
        for (int i = animation.getStartFrameAnim(); i < size; i++) {
            //Todo Ошибка в тесте и экзамене но не вылетает
            if (!runFlag[0])
                return;
            if (buffer.size() > ThreadLoadAnimation.BUFFER_SIZE) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                i--;
                continue;
            }

            File file = new File(absolutePath + PATH_HEAD_FOLDER + PATH_ANIMATION + name, list[i]);
            //  Log.i("load image", absolutePath + PATH_HEAD_FOLDER + PATH_ANIMATION + name+"/"+list[i]);
            //  boolean v = file.exists();
            FileInputStream fileInputStreamatd = new FileInputStream(file);
            long s = file.length();
            if (s == 0) {
                Log.i("load image", "dont load");
                continue;
            }
            byte[] matd = new byte[(int) file.length()];
            //  byte[] matd = new byte[30000];
            fileInputStreamatd.read(matd);
            buffer.add(matd);
            fileInputStreamatd.close();
        }


    }

    public List<String> getImagesPathByAnimationId(String id) throws IOException {
        String name = id;
        name += "/";
        //String absolutePath = getAbsolutePathSD();
        String absolutePath = getAbsolutePath();
        //    String[] list = new File("/data/data/com.blacksheep.teacher/files/TeachData/animations/10/").list();
        Log.i("load anim path", absolutePath + PATH_HEAD_FOLDER + PATH_ANIMATION + name);
        String[] list = new File(absolutePath + PATH_HEAD_FOLDER + PATH_ANIMATION + name).list();
        Arrays.sort(list);
        Log.i("load image all", Arrays.toString(list));
        int size = list.length;

        LinkedList<String> strings = new LinkedList<String>();

        for (int i = 0; i < size; i++) {

            String path = absolutePath + PATH_HEAD_FOLDER + PATH_ANIMATION + name + list[i];
            strings.add(path);
        }
        return strings;


    }

    public static byte[] fillAnimationSimple(DataAnimation animation, int number) throws IOException {
        String name = animation.getName();
        name += "/";
        String nameatd = "at_d";
        String absolutePath = getAbsolutePathSD();

        FileInputStream fileInputStreamatd = new FileInputStream(new File(absolutePath + PATH_HEAD_FOLDER + PATH_ANIMATION + name, nameatd + String.valueOf(number * 2) + ".jpg"));
        byte[] matd = new byte[(int) fileInputStreamatd.getChannel().size()];
        fileInputStreamatd.read(matd);
        fileInputStreamatd.close();
        return matd;


    }

    public static String getLecturFilePath(int lectureID) {
        String path = getAbsolutePathForAudio();
        path += PATH_LECTURES;
        path += lectureID + "/";
        path += "l" + lectureID + exten;
        return path;
    }

    public static String getLecture(String audio) {
        StringBuilder lec = new StringBuilder(audio);
        String path = getAbsolutePathForAudio();
        path += PATH_LECTURES;
        path += Integer.parseInt(String.valueOf(audio.charAt(1))) + "/";
        path += audio;
        return path;
    }

    public static String getPhrases(String name) {
        String path = getAbsolutePathForAudio();
        path += PATH_PHRASES;
        path += name + exten;
        return path;
    }

    public static String getPhrasesNotExt(String name) {
        String path = getAbsolutePathForAudio();
        path += PATH_PHRASES;
        path += name;
        return path;
    }

    public static String getPhrasesDir() {
        String path = getAbsolutePathForAudio();
        path += PATH_PHRASES;

        path = path.substring(0, path.length() - 1);
        return path;
    }
/*    public static String getName() {
        String path = getAbsolutePathSD();
        path += PATH_HEAD_FOLDER;
        path += PATH_SOUNDS;
        path += PATH_NAMES;
        path += "_M_E_N_/s_volodya.wav";
        return path;
    }*/

    public static String getNameTest(String name) {
        String path = getAbsolutePathForAudio();
        path += PATH_NAMES;
        path += name + exten;
        return path;
    }

    public static String getCorrectResult(String result) {
        String path = getAbsolutePathForAudio();
        path += PATH_RESULT;
        path += "_result_" + result + exten;
        return path;
    }

    public static String getOperation(String content) {
        String path = getAbsolutePathForAudio();
        path += PATH_OPERATION;
        path += "_op_" + content + exten;
        return path;
    }

    private final static int MIN_SIZE = 1024 * 1024 * 500;

    public static boolean mountHeadFolders() {
        Context context = MyApplication.instance;

        if (isSdPresent()) {
            StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
            int bs = stat.getBlockSize();
            int ba = stat.getAvailableBlocks();
            long size = (long) stat.getBlockSize() * (long) stat.getAvailableBlocks();
            if (size > MIN_SIZE) {
                String pathHead = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(pathHead + PATH_HEAD_FOLDER + PATH_ANIMATION);
                return file.mkdirs();
            }
        } else {
            StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
            long size = (long) stat.getBlockSize() * (long) stat.getAvailableBlocks();
            if (size > MIN_SIZE) {
                String path = context.getFilesDir().getPath();
                File f = new File(path + PATH_HEAD_FOLDER + PATH_ANIMATION);
                return f.mkdirs();
            }

        }


        return false;

    }

//    private static boolean mountHeadFoldersTEST() {
//        Context context = MyApplication.instance;
//
//            StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
//            int size = stat.getBlockSize() * stat.getFreeBlocks();
//            if (size > MIN_SIZE) {
//                String path = context.getFilesDir().getPath();
//                File f = new File(path + PATH_HEAD_FOLDER + PATH_ANIMATION);
//                return f.mkdirs();
//            }
//        return false;
//    }
//
//    private static void mountAnimationFoldersTEST(Collection<DataAnimation> collection) {
//        Context context = MyApplication.instance;
//
//        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
//
//        for (DataAnimation dj:collection)
//        {
//            String path = context.getFilesDir().getPath();
//            File f = new File(path + PATH_HEAD_FOLDER + PATH_ANIMATION+dj.getId()+"/");
//            f.mkdirs();
//        }
//
//
//
//    }


    public static void mountAnimationFolders(Collection<DataAnimation> collection) {
        for (DataAnimation dj : collection) {
            File f = new File(absolutePath + PATH_HEAD_FOLDER + PATH_ANIMATION + dj.getId() + "/");
            if (!f.exists())
                f.mkdirs();
        }
    }

    public static void mountAudiosFolders()
    {
        File f = new File(absolutePath + PATH_HEAD_FOLDER + PATH_AUDIO + PATH_NAMES);
        if (!f.exists())
            f.mkdirs();
        File f1 = new File(absolutePath + PATH_HEAD_FOLDER + PATH_AUDIO + PATH_PHRASES);
        if (!f1.exists())
            f1.mkdirs();
        File f2= new File(absolutePath + PATH_HEAD_FOLDER + PATH_AUDIO + PATH_OPERATION);
        if (!f2.exists())
            f2.mkdirs();
        File f3 = new File(absolutePath + PATH_HEAD_FOLDER + PATH_AUDIO + PATH_RESULT);
        if (!f3.exists())
            f3.mkdirs();
    }

    private static boolean isSdPresent() {

        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

    }

    public static boolean init() {
        if (!checkMountHeadFolder())
            mountHeadFolders();
        return checkMountHeadFolder();
    }
//    public static boolean initTEST() {
//        if(!checkMountHeadFolderTEST())
//            mountHeadFoldersTEST();
//        return checkMountHeadFolderTEST();
//    }

    private static boolean checkMountHeadFolder() {
        Context context = MyApplication.instance;
        boolean k = false;

        if (isSdPresent()) {

            String pathHead = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(pathHead + PATH_HEAD_FOLDER);
            k = k | file.exists();
            if (file.exists())
                absolutePath = pathHead;
        }


        String path = context.getFilesDir().getPath();
        File f = new File(path + PATH_HEAD_FOLDER);
        if (f.exists())
            absolutePath = path;
        k = k | f.exists();
        return k;


    }


//    private static boolean checkMountHeadFolderTEST() {
//        Context context = MyApplication.instance;
//        boolean k = false;
//
//        String path = context.getFilesDir().getPath();
//        File f = new File(path + PATH_HEAD_FOLDER);
//        if(f.exists())
//            absolutePath = path;
//        k = k |  f.exists();
//        return k;
//    }


    private static String getAbsolutePathSD() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
        //Log.i(DataManager.getName(),absolutePath);
        //return absolutePath;
    }

    public static String getAbsolutePath() {
        //TODO:когда sd карта не воткнута может не правильно проинициализироваться
        return absolutePath;
        //Log.i(DataManager.getName(),absolutePath);
        //return absolutePath;
    }

    private static String getAbsolutePathForAudio() {
        return getAbsolutePath() + PATH_HEAD_FOLDER + PATH_AUDIO;
    }
}
