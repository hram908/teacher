package com.blacksheep.teacher.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.blacksheep.teacher.model.dataEntity.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/5/12
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBManager {

    public SQLiteDatabase sqLiteDatabase;

    public static boolean testAnim = true;


    private static DBManager instance;
    private static Context context;

    public static void init(Context context1) {
        context = context1;
    }

    public static DBManager getInstance() {
        if (instance == null)
            instance = new DBManager(context);
        return instance;
    }


    public DBManager(Context context) {
        sqLiteDatabase = (new TeachDB(context)).getWritableDatabase();
    }


    public List<DataLecture> getLectures() {
        List<DataLecture> dataLectures = new LinkedList<DataLecture>();
        SQLiteDatabase sqLiteDatabase = instance.sqLiteDatabase;
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_LECTURE + ";", null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            DataLecture lecture = cursorToLecture(c);
            dataLectures.add(lecture);
            c.moveToNext();
        }
        c.close();
        return dataLectures;

    }

    public List<DataQuest> getQuestionByLevel(int limit, int level) {
        List<DataQuest> dataQuests = new LinkedList<DataQuest>();
        String query = "SELECT * FROM "
                + TeachDB.TABLE_NAME_QUEST + " JOIN " + TeachDB.TABLE_NAME_LEVEL_QUESTION
                + " ON " + TeachDB.TABLE_NAME_QUEST + "._id=" + TeachDB.RAW_LEVEL_QUESTION_QUESTION_ID
                + " WHERE " + TeachDB.RAW_LEVEL_QUESTION_LEVEL + "=" + level
                + " ORDER BY RANDOM() LIMIT " + limit + ";";
        Cursor c = instance.sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        String[] ss = c.getColumnNames();
        while (!c.isAfterLast()) {
            DataQuest quest = cursorToQuest(c);
            dataQuests.add(quest);
            c.moveToNext();
        }
        c.close();
        return dataQuests;

    }

//    public List<DataWord> getDataWords(String type, String mood) {
//        List<DataWord> dataWords = new LinkedList<DataWord>();
//        //Todo при тесте и экзамене ошибка но прилага не падает
//       Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_PHRASES + " WHERE " + TeachDB.RAW_PHRASES_TYPE + "=\"" + type + "\" AND " + TeachDB.RAW_PHRASES_MOOD_TYPE + "=\"" + mood + "\" ORDER BY RANDOM() LIMIT 1;", null);
//        c.moveToFirst();
//
//
//        int phraseID = c.getInt(0);
//        c.close();
//        Log.i("id phrase", String.valueOf(phraseID));
//        Cursor c2 = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_WORD_FOR_PHRASES + " WHERE " + TeachDB.RAW_WORD_FOR_PHRASES_PHRASE_ID + "=" + phraseID + " ORDER BY " + TeachDB.RAW_WORD_FOR_PHRASES_PHRASE_NUMBER + " ;", null);
//        c2.moveToFirst();
//        String[] ss = c2.getColumnNames();
//        //    int cs=c2.getInt(0);
//        while (!c2.isAfterLast()) {
//
//            DataWord dataWord = cursorToWord(c2);
//            dataWords.add(dataWord);
//            c2.moveToNext();
//        }
//        c2.close();
//        return dataWords;
//
//    }

    public List<DataWord> getDataWordsByPhraseID(int id) {
        List<DataWord> dataWords = new LinkedList<DataWord>();

        Log.i("id phrase", String.valueOf(id));
        Cursor c2 = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_WORD_FOR_PHRASES + " WHERE " + TeachDB.RAW_WORD_FOR_PHRASES_PHRASE_ID + "=" + id + " ORDER BY " + TeachDB.RAW_WORD_FOR_PHRASES_PHRASE_NUMBER + " ;", null);
        c2.moveToFirst();
        String[] ss = c2.getColumnNames();
        while (!c2.isAfterLast()) {

            DataWord dataWord = cursorToWord(c2);
            dataWords.add(dataWord);
            c2.moveToNext();
        }
        c2.close();
        return dataWords;

    }

    private DataWord cursorToWord(Cursor c) {
        return new DataWord(c.getInt(0), c.getInt(1), c.getString(2), c.getInt(3), c.getInt(4));
    }

    public List<DataTestSession> getTestSessions() {
        List<DataTestSession> dataLectures = new LinkedList<DataTestSession>();
        SQLiteDatabase sqLiteDatabase = instance.sqLiteDatabase;
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_TEST_SESSION + ";", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            DataTestSession test = cursorToDataTestSession(c);
            dataLectures.add(test);
            c.moveToNext();
        }
        c.close();
        return dataLectures;
    }

    public List<Integer> getTips(String quest){

        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM "+ TeachDB.TABLE_NAME_QUEST_FOR_TIPS+
                " JOIN "+TeachDB.TABLE_NAME_QUEST_TIPS+" ON "+
                TeachDB.RAW_QUEST_TIPS_QUEST_ID+"=_id WHERE "+TeachDB.RAW_QUEST_FOR_TIPS_QUEST+" = '"+quest+"';",null);
        c.moveToFirst();
        Random rnd = new Random();
        int tip1=0;
        int tip2=0;
        while (tip1==tip2)
        {
            tip1 = rnd.nextInt(6);
            tip2 = rnd.nextInt(6);
        }
        int numberTip=0;
        List<Integer> tips = new LinkedList<Integer>();
        if(!c.isAfterLast())
            tips.add(Integer.valueOf(c.getString(2)));
        else
           return null;
        while (!c.isAfterLast()){
            Log.d("testssttsts",c.getString(0));
            if(numberTip==tip1||numberTip==tip2)
            {
                tips.add(Integer.valueOf(c.getString(3)));
            }
            numberTip++;
            c.moveToNext();

        }
        c.close();
        Collections.shuffle(tips);
        return tips;
    }



    public List<DataAnswerLog> getAnswerLog() {
        List<DataAnswerLog> dataLectures = new LinkedList<DataAnswerLog>();
        SQLiteDatabase sqLiteDatabase = instance.sqLiteDatabase;
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_ANSWER_LOG + ";", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            DataAnswerLog test = cursorToDataAnswerLog(c);
            dataLectures.add(test);
            c.moveToNext();
        }
        c.close();
        return dataLectures;
    }

    private DataAnswerLog cursorToDataAnswerLog(Cursor c) {
        return new DataAnswerLog(c.getInt(0), c.getInt(1), c.getInt(2), Timestamp.valueOf(c.getString(3)), Timestamp.valueOf(c.getString(4)), c.getInt(5));
    }

    private DataTestSession cursorToDataTestSession(Cursor c) {

        return new DataTestSession(c.getInt(0), c.getInt(1), c.getInt(2), Timestamp.valueOf(c.getString(3)), Timestamp.valueOf(c.getString(4)), c.getFloat(5));
    }

    public List<DataTest> getTests() {
        List<DataTest> dataTests = new LinkedList<DataTest>();
        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_TEST + ";", null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            DataTest test = cursorToTest(c);
            dataTests.add(test);
            c.moveToNext();
        }
        c.close();
        return dataTests;
    }

    public List<DataQuest> getDataQuestsByTestID(int testID) {
        List<DataQuest> dataQuests = new LinkedList<DataQuest>();
        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_QUEST + " WHERE " + TeachDB.RAW_QUEST_TEST_ID + "=" + testID + ";", null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            DataQuest quest = cursorToQuest(c);
            dataQuests.add(quest);
            c.moveToNext();
        }
        c.close();
        return dataQuests;
    }

    public HashMap<Integer, Float> getStarForTest() {
        HashMap<Integer, Float> integerIntegerHashMap = new HashMap<Integer, Float>();
        for (int i = 0; i < 10; i++) {
            Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_TEST_SESSION + " WHERE " + TeachDB.RAW_TEST_SESSION_TEST_ID + " = " + (i + 1) + " ORDER BY " + "_id" + " DESC", null);
            c.moveToFirst();
            if (!c.isAfterLast())
                integerIntegerHashMap.put(i + 1, c.getFloat(5));
            c.close();
        }
        return integerIntegerHashMap;

    }

    public List<DataQuest> getDataQuests() {
        List<DataQuest> dataQuests = new LinkedList<DataQuest>();
        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_QUEST + ";", null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            DataQuest quest = cursorToQuest(c);
            dataQuests.add(quest);
            c.moveToNext();
        }
        c.close();
        return dataQuests;
    }

    public void addAnswer(int testSessionID, int questID, Timestamp startTime, Timestamp endTime, int result) {


        ContentValues values = new ContentValues();
        values.put(TeachDB.RAW_ANSWER_LOG_TEST_SESSION_ID, testSessionID);
        values.put(TeachDB.RAW_ANSWER_LOG_QUEST_ID, questID);
        values.put(TeachDB.RAW_ANSWER_LOG_TIME_START, startTime.toString());
        values.put(TeachDB.RAW_ANSWER_LOG_TIME_END, endTime.toString());
        values.put(TeachDB.RAW_ANSWER_LOG_RESULT, result);
        instance.sqLiteDatabase.insert(TeachDB.TABLE_NAME_ANSWER_LOG, TeachDB.RAW_ANSWER_LOG_TEST_SESSION_ID, values);

    }


    public void fillAnimations(Collection<DataAnimation> data) {

        for (DataAnimation daj : data) {

            if (getAnimationById(daj.getId()) != null)
                continue;

            ContentValues values = new ContentValues();
            values.put("_id", daj.getId());
            values.put(TeachDB.RAW_ANIMATION_NAME, daj.getName());
            values.put(TeachDB.RAW_ANIMATION_TYPE, daj.getAnimation_type());
            values.put(TeachDB.RAW_ANIMATION_FPS, daj.getFps());
            values.put(TeachDB.RAW_ANIMATION_FRAME_COUNT, daj.getFrameCount());
            values.put(TeachDB.RAW_ANIMATION_OPTION_CODE, daj.getOption_code());
            values.put(TeachDB.RAW_ANIMATION_EQ_START, daj.getEq_start());
            values.put(TeachDB.RAW_ANIMATION_EQ_END, daj.getEq_end());
            values.put(TeachDB.RAW_ANIMATION_START_FRAME, daj.getStartAudioFrame());
            values.put(TeachDB.RAW_ANIMATION_CHRONOMETRY, daj.getChronometry());
            values.put(TeachDB.RAW_ANIMATION_PATTERN, daj.getPattern());
            values.put(TeachDB.RAW_ANIMATION_IS_SYNC, 0);
            instance.sqLiteDatabase.insert(TeachDB.TABLE_NAME_ANIMATION, TeachDB.RAW_ANIMATION_NAME, values);
        }
    }

    public void updateTestSessionEndTime(int id, Timestamp time, float result) {
        String strFilter = "_id=" + id;
        ContentValues values = new ContentValues();
        values.put(TeachDB.RAW_TEST_SESSION_TIME_END, time.toString());
        values.put(TeachDB.RAW_TEST_SESSION_RESULT, result);
        instance.sqLiteDatabase.update(TeachDB.TABLE_NAME_TEST_SESSION, values, strFilter, null);
    }

    public long addTestSession(int testID, int userID, Timestamp startTime, Timestamp endTime, float result) {
        ContentValues values = new ContentValues();
        values.put(TeachDB.RAW_TEST_SESSION_TEST_ID, testID);
        values.put(TeachDB.RAW_TEST_SESSION_USER_ID, userID);
        values.put(TeachDB.RAW_TEST_SESSION_TIME_START, startTime.toString());
        values.put(TeachDB.RAW_TEST_SESSION_TIME_END, endTime.toString());
        values.put(TeachDB.RAW_TEST_SESSION_RESULT, result);
        return instance.sqLiteDatabase.insert(TeachDB.TABLE_NAME_TEST_SESSION, TeachDB.RAW_TEST_SESSION_TEST_ID, values);
    }

    private DataQuest cursorToQuest(Cursor c) {
        //DataQuest quest = new DataQuest(c.getInt(1),c.getString(2),c.getString(3));
        return new DataQuest(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3));
    }

    private DataTest cursorToTest(Cursor c) {
        //DataTest test = new DataTest(c.getInt(0), DataTest.TYPE_LECTURE,c.getInt(2) , c.getString(3), c.getString(4));
        return new DataTest(c.getInt(0), DataTest.TYPE_LECTURE, c.getInt(2), c.getString(3), c.getString(4));
    }

    public List<DataContentForLecture> getContentForLecture(int lectureID) {
        List<DataContentForLecture> dataLectures = new LinkedList<DataContentForLecture>();
        SQLiteDatabase sqLiteDatabase = instance.sqLiteDatabase;
        //Todo ошибка какая то но прилага не падает
        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_CONTENT_TO_LECTURE + " WHERE " + TeachDB.RAW_CONTENT_TO_LECTURE_LECTURE_ID + "=" + lectureID + ";", null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            DataContentForLecture lecture = cursorToContentToLecture(c);
            dataLectures.add(lecture);
            c.moveToNext();
        }
        c.close();
        return dataLectures;
    }

    private DataContentForLecture cursorToContentToLecture(Cursor cursor) {
        //String [] ss = cursor.getColumnNames();
        // DataContentForLecture contentForLecture = new DataContentForLecture(cursor.getInt(2),cursor.getInt(3),cursor.getString(4), cursor.getInt(0));
        return new DataContentForLecture(cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(0));
    }

    private DataLecture cursorToLecture(Cursor cursor) {
        DataLecture lecture = new DataLecture();
        lecture.setName(cursor.getString(1));
        lecture.setId(cursor.getInt(0));
        return lecture;
    }


//    public List<DataQuest> getDataQuestsRandom(int coutQuestion) {
//        List<DataQuest> dataQuests = new LinkedList<DataQuest>();
//        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_QUEST + " ORDER BY RANDOM() LIMIT " + coutQuestion + ";", null);
//        c.moveToFirst();
//
//        while (!c.isAfterLast()) {
//            DataQuest quest = cursorToQuest(c);
//            dataQuests.add(quest);
//            c.moveToNext();
//        }
//        c.close();
//        return dataQuests;
//    }

    public int getPhraseIdByWordName(String name) {
        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_WORD_FOR_PHRASES + " WHERE " + TeachDB.RAW_WORD_FOR_PHRASES_FILE_NAME + " = '" + name + "';", null);
        c.moveToFirst();
        int id = -1;
        if (!c.isAfterLast())
            id = c.getInt(1);
        c.close();
        return id;
    }

    public LinkedList<DataAnimation> getAnimations() {

        LinkedList<DataAnimation> DataAnimations = new LinkedList<DataAnimation>();

        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_ANIMATION + ";", null);
        //  Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_ANIMATION + " WHERE 1=" + 1 + ";", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            DataAnimation DataAnimation = cursorToDataAnimation(c);
            DataAnimations.add(DataAnimation);
            c.moveToNext();
        }
        c.close();
//        for(int i=0;i<100;i++)
//        {
//            DataAnimation DataAnimation = getAnimationById(i);
//            if(DataAnimation!=null)
//                DataAnimations.add(DataAnimation);
//        }
        return DataAnimations;
    }

    public DataAnimation getAnimationByTypeAndOptionCode(int type, int option_code) {
        String query = "SELECT * FROM " + TeachDB.TABLE_NAME_ANIMATION;
        if (type != -1)
            query += " WHERE " + TeachDB.RAW_ANIMATION_TYPE + " = " + type;
        if (option_code != -1)
            query += " AND " + TeachDB.RAW_ANIMATION_OPTION_CODE + " = " + option_code;
        //  query+= " ORDER BY RANDOM() LIMIT 1;";
        query += " ;";


        Cursor c = instance.sqLiteDatabase.rawQuery(query, null);
        DataAnimation dataAnimation = null;
        c.moveToFirst();
        Random random = new Random();
        int select = random.nextInt(c.getCount());
        for (int i = 0; i < select; i++) {
            c.moveToNext();
        }
        dataAnimation = cursorToDataAnimation(c);
        c.close();
        if (dataAnimation != null)
            Log.i(this.getClass().getName(), "animaion id " + dataAnimation.getId() + " name " + dataAnimation.getName());
        else {
            Log.i(this.getClass().getName(), "bugggg");
        }
        return dataAnimation;
    }

    public DataAnimation getAnimationById(int id) {

        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_ANIMATION + " WHERE _id=" + id + ";", null);
        DataAnimation DataAnimation = null;
        c.moveToFirst();
        if (!c.isAfterLast())
            DataAnimation = cursorToDataAnimation(c);

        c.close();
        return DataAnimation;


    }

    private DataAnimation cursorToDataAnimation(Cursor c) {
        DataAnimation dataAnimation = new DataAnimation(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getInt(7), c.getInt(8), c.getString(9), c.getString(10), c.getInt(11));
        return dataAnimation;
    }

    public void fillImageAnimation(Collection<DataImageAnimationJson> datal) {


        for (DataImageAnimationJson dia : datal) {

            if (getImageById(dia.getId()) != null)
                continue;

            ContentValues values = new ContentValues();
            values.put("_id", dia.getId());
            values.put(TeachDB.RAW_IMAGES_ANIMATION_ANIMATION_ID, dia.getAnimation_id());
            values.put(TeachDB.RAW_IMAGES_ANIMATION_FILE_NAME, dia.getImage_file_name());
            values.put(TeachDB.RAW_IMAGES_ANIMATION_FILE_URL, dia.getFile_url());
            values.put(TeachDB.RAW_IMAGES_ANIMATION_FILE_SIZE, dia.getImage_file_size());
            values.put(TeachDB.RAW_IMAGES_ANIMATION_IS_SYNC, 0);
            instance.sqLiteDatabase.insert(TeachDB.TABLE_NAME_IMAGES_ANIMATION, TeachDB.RAW_IMAGES_ANIMATION_ANIMATION_ID, values);
        }
    }

    private DataImageAnimationJson getImageById(int id) {

        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_IMAGES_ANIMATION + " WHERE _id=" + id + ";", null);
        DataImageAnimationJson dataImageAnimationJson = null;
        c.moveToFirst();
        if (!c.isAfterLast())
            dataImageAnimationJson = new DataImageAnimationJson(c.getInt(0), c.getInt(1), c.getString(3), c.getInt(4), c.getString(2), c.getInt(5));

        c.close();
        return dataImageAnimationJson;

    }

    public void fillAnimationToAudio(Collection<DataAnimationToAudio> datal) {

        for (DataAnimationToAudio dia : datal) {
            ContentValues values = new ContentValues();
            values.put(TeachDB.RAW_AUDIO_TO_ANIMATION_AUDIO_FILE_NAME, dia.getAudio_file_name());
            values.put(TeachDB.RAW_AUDIO_TO_ANIMATION_FILE_URL, dia.getFile_url());
            values.put(TeachDB.RAW_AUDIO_TO_ANIMATION_ANIMATION_ID, dia.getAnimation_id());
            instance.sqLiteDatabase.insert(TeachDB.TABLE_NAME_AUDIO_TO_ANIMATION, TeachDB.RAW_AUDIO_TO_ANIMATION_AUDIO_FILE_NAME, values);
        }
    }

    public void fillAnimationToAudioOne(DataAnimationToAudio dia) {
        ContentValues values = new ContentValues();
        values.put(TeachDB.RAW_AUDIO_TO_ANIMATION_AUDIO_FILE_NAME, dia.getAudio_file_name());
        values.put(TeachDB.RAW_AUDIO_TO_ANIMATION_ANIMATION_ID, dia.getAnimation_id());
        instance.sqLiteDatabase.insert(TeachDB.TABLE_NAME_AUDIO_TO_ANIMATION, TeachDB.RAW_AUDIO_TO_ANIMATION_AUDIO_FILE_NAME, values);
    }

    public void fillContent(Collection<DataContentForLecture> datal) {


        for (DataContentForLecture dc : datal) {
            ContentValues values = new ContentValues();
            values.put(TeachDB.RAW_CONTENT_TO_LECTURE_LECTURE_ID, dc.getLecture_id());
            values.put(TeachDB.RAW_CONTENT_TO_LECTURE_START_FRAME, dc.getStart_frame());
            values.put(TeachDB.RAW_CONTENT_TO_LECTURE_END_FRAME, dc.getEnd_frame());
            values.put(TeachDB.RAW_CONTENT_TO_LECTURE_CONTENT, dc.getContent());
            instance.sqLiteDatabase.insert(TeachDB.TABLE_NAME_CONTENT_TO_LECTURE, TeachDB.RAW_CONTENT_TO_LECTURE_LECTURE_ID, values);
        }
    }

    public Collection<DataImageAnimationJson> getImagesAnimations() {

        Collection<DataImageAnimationJson> dataImageAnimationJsons = new LinkedList<DataImageAnimationJson>();
        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_IMAGES_ANIMATION + ";", null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            DataImageAnimationJson dataImageAnimationJson = new DataImageAnimationJson(c.getInt(0), c.getInt(1), c.getString(3), c.getInt(4), c.getString(2), c.getInt(5));
            dataImageAnimationJsons.add(dataImageAnimationJson);
            c.moveToNext();
        }
        c.close();
        return dataImageAnimationJsons;

    }

    public Collection<DataImageAnimationJson> getImagesAnimationsByAnimationId(int animationId) {

        Collection<DataImageAnimationJson> dataImageAnimationJsons = new LinkedList<DataImageAnimationJson>();
        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_IMAGES_ANIMATION + " WHERE " + TeachDB.RAW_IMAGES_ANIMATION_ANIMATION_ID + " = " + animationId + " AND " + TeachDB.RAW_IMAGES_ANIMATION_IS_SYNC + "=0;", null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            DataImageAnimationJson dataImageAnimationJson = new DataImageAnimationJson(c.getInt(0), c.getInt(1), c.getString(2), c.getInt(3), c.getString(4), c.getInt(5));
            dataImageAnimationJsons.add(dataImageAnimationJson);
            c.moveToNext();
        }
        c.close();
        return dataImageAnimationJsons;

    }

    public void setImagesSync(int id, boolean isSync) {
        try {
            String strFilter = "_id=" + id;
            ContentValues values = new ContentValues();
            values.put(TeachDB.RAW_IMAGES_ANIMATION_IS_SYNC, isSync ? 1 : 0);
            instance.sqLiteDatabase.update(TeachDB.TABLE_NAME_IMAGES_ANIMATION, values, strFilter, null);
        } catch (Exception e) {

        }
    }

    public void setAnimationSync(int id, boolean isSync) {
        String strFilter = "_id=" + id;
        ContentValues values = new ContentValues();
        values.put(TeachDB.RAW_ANIMATION_IS_SYNC, isSync ? 1 : 0);
        instance.sqLiteDatabase.update(TeachDB.TABLE_NAME_ANIMATION, values, strFilter, null);
    }

    public Collection<DataAnimationToAudio> getDataAnimationToAudio() {
        Collection<DataAnimationToAudio> dataAnimationToAudios = new LinkedList<DataAnimationToAudio>();

        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_AUDIO_TO_ANIMATION + ";", null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            DataAnimationToAudio dataAnimationToAudio = new DataAnimationToAudio(c.getInt(0), c.getString(1), c.getInt(2));
            dataAnimationToAudios.add(dataAnimationToAudio);
            c.moveToNext();
        }
        c.close();
        return dataAnimationToAudios;

    }

    public DataAnimation getAnimationByAudioFileName(String name) {

        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_AUDIO_TO_ANIMATION + " WHERE " + TeachDB.RAW_AUDIO_TO_ANIMATION_AUDIO_FILE_NAME + " = '" + name + "';", null);

        c.moveToFirst();
        DataAnimationToAudio dataAnimationToAudio = null;
        if (!c.isAfterLast()) {
            dataAnimationToAudio = new DataAnimationToAudio(c.getInt(0), c.getString(1), c.getInt(2));
        }
        c.close();
        if (dataAnimationToAudio != null)
            return getAnimationById(dataAnimationToAudio.getAnimation_id());
        return null;

    }

    public DataAnimationToAudio getAudioByAnimationID(int id) {

        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_AUDIO_TO_ANIMATION + " WHERE " + TeachDB.RAW_AUDIO_TO_ANIMATION_ANIMATION_ID + " = " + id + ";", null);

        c.moveToFirst();
        DataAnimationToAudio dataAnimationToAudio = null;
        if (!c.isAfterLast()) {
            dataAnimationToAudio = new DataAnimationToAudio(c.getInt(0), c.getString(1), c.getInt(2));
        }
        c.close();
        return dataAnimationToAudio;
    }

    public List<DataAnimationToAudio> getAudiosByAnimationID(int id) {

        Cursor c = instance.sqLiteDatabase.rawQuery("SELECT * FROM " + TeachDB.TABLE_NAME_AUDIO_TO_ANIMATION + " WHERE " + TeachDB.RAW_AUDIO_TO_ANIMATION_ANIMATION_ID + " = " + id + ";", null);
        List<DataAnimationToAudio> audios = new ArrayList<DataAnimationToAudio>();
        c.moveToFirst();

        while (!c.isAfterLast()) {
            DataAnimationToAudio dataAnimationToAudio = new DataAnimationToAudio(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3));
            audios.add(dataAnimationToAudio);
            c.moveToNext();
        }
        c.close();
        return audios;
    }

    public List<DataWord> getPhrasByAnimationId(int id) {
        DataAnimationToAudio dataAnimationToAudio = getAudioByAnimationID(id);
        int idPhrase = getPhraseIdByWordName(dataAnimationToAudio.getAudio_file_name().replace(".mp3", ""));
        List<DataWord> dataWords = getDataWordsByPhraseID(idPhrase);
        if (!dataWords.isEmpty())
            Log.i(getClass().getName(), dataWords.get(0).getFileName());
        return dataWords;

    }

    public void clearTestSessions() {
        instance.sqLiteDatabase.delete(TeachDB.TABLE_NAME_TEST_SESSION, null, null);
    }

}
