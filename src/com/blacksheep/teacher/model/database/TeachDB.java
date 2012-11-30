package com.blacksheep.teacher.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import com.blacksheep.teacher.MyApplication;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.model.dataEntity.DataWord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/4/12
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class TeachDB extends SQLiteOpenHelper implements BaseColumns{

    //public static final String RAW_ID="ID";

    public static final String TABLE_NAME_LECTURE = "LECTURE";
    //public static final String RAW_LECTURE_ID="LECTURE_ID";
    public static final String RAW_LECTURE_TITLE="LECTURE_TITLE";

    public static final String TABLE_NAME_CONTENT_TO_LECTURE = "CONTENT_TO_LECTURE";
    public static final String RAW_CONTENT_TO_LECTURE_LECTURE_ID="LECTURE_ID";
    public static final String RAW_CONTENT_TO_LECTURE_START_FRAME="START_FRAME";
    public static final String RAW_CONTENT_TO_LECTURE_END_FRAME="END_FRAME";
    public static final String RAW_CONTENT_TO_LECTURE_CONTENT="CONTENT";

    public static final String TABLE_NAME_TEST = "TEST";
    public static final String RAW_TEST_TARGET_TYPE="TARGET_TYPE";
    public static final String RAW_TEST_TARGET_ID="TARGET_ID";
    public static final String RAW_TEST_TITLE="TITLE";
    public static final String RAW_TEST_DESCRIPTION="DESCRIPTION";

    public static final String TABLE_NAME_QUEST = "QUEST";
    public static final String RAW_QUEST_TEST_ID="TEST_ID";
    public static final String RAW_QUEST_CONTENT="CONTENT";
    public static final String RAW_QUEST_ANSWER="ANSWER";


    public static final String TABLE_NAME_ANIMATION = "ANIMATION";
    public static final String RAW_ANIMATION_NAME="NAME";
    public static final String RAW_ANIMATION_TYPE="TYPE";
    public static final String RAW_ANIMATION_FPS="FPS";
    public static final String RAW_ANIMATION_FRAME_COUNT="FRAME_COUNT";
    public static final String RAW_ANIMATION_EQ_START="EQ_START";
    public static final String RAW_ANIMATION_EQ_END="EQ_END";
    public static final String RAW_ANIMATION_START_FRAME="START_FRAME";
    public static final String RAW_ANIMATION_CHRONOMETRY="CHRONOMETRY";
    public static final String RAW_ANIMATION_PATTERN="PATTERN";
    public static final String RAW_ANIMATION_OPTION_CODE="OPTION_CODE";
    public static final String RAW_ANIMATION_IS_SYNC="IS_SYNC";

    public static final String TABLE_NAME_IMAGES_ANIMATION="IMAGES_ANIMATION";
    public static final String RAW_IMAGES_ANIMATION_ANIMATION_ID="ANIMATION_ID";
    public static final String RAW_IMAGES_ANIMATION_FILE_URL="FILE_URL";
    public static final String RAW_IMAGES_ANIMATION_FILE_NAME="FILE_NAME";
    public static final String RAW_IMAGES_ANIMATION_FILE_SIZE="FILE_SIZE";
    public static final String RAW_IMAGES_ANIMATION_IS_SYNC="IS_SYNC";



    public static final String TABLE_NAME_ANIMATION_TO_LECTURE = "ANIMATION_TO_LECTURE";
    public static final String RAW_ANIMATION_TO_LECTURE_ANIMATION_ID ="ANIMATION_ID";
    public static final String RAW_ANIMATION_TO_LECTURE_LECTURE_ID ="LECTURE_ID";
    public static final String RAW_ANIMATION_TO_LECTURE_START_FRAME ="START_FRAME";

    public static final String TABLE_NAME_AUDIO_FILE = "AUDIO_FILE";
    public static final String RAW_AUDIO_FILE_NAME="NAME";
    public static final String RAW_AUDIO_FILE_LECTURE_ID="LECTURE_ID";
    public static final String RAW_AUDIO_FILE_QUEST_ID="QUEST_ID";
    public static final String RAW_AUDIO_FILE_QUEST_CONSTRUCTOR_ID="QUEST_CONSTRUCTOR";
   // public static final String RAW_AUDIO_FILE_REACTION="REACTION";


    public static final String TABLE_NAME_TEST_SESSION = "TEST_SESSION";
    public static final String RAW_TEST_SESSION_TEST_ID="TEST_ID";
    public static final String RAW_TEST_SESSION_USER_ID="USER_ID";
    public static final String RAW_TEST_SESSION_TIME_START="TIME_START";
    public static final String RAW_TEST_SESSION_TIME_END="TEST_END";
    public static final String RAW_TEST_SESSION_RESULT="RESULT";

    public static final String TABLE_NAME_ANSWER_LOG = "ANSWER_LOG";
    public static final String RAW_ANSWER_LOG_TEST_SESSION_ID="TEST_SESSION_ID";
    public static final String RAW_ANSWER_LOG_QUEST_ID="QUEST_ID";
    public static final String RAW_ANSWER_LOG_TIME_START="TIME_START";
    public static final String RAW_ANSWER_LOG_TIME_END="TIME_END";
    public static final String RAW_ANSWER_LOG_RESULT="RESULT";

    public static final String TABLE_NAME_PHRASES="PHRASES";
    public static final String RAW_PHRASES_TYPE="PHRASES_TYPE";
    public static final String RAW_PHRASES_MOOD_TYPE="PHRASES_MOOD_TYPE";

    public static final String TABLE_NAME_WORD_FOR_PHRASES="WORD_FOR_PHRASES";
    public static final String RAW_WORD_FOR_PHRASES_PHRASE_ID="WORD_FOR_PHRASES_TYPE";
    public static final String RAW_WORD_FOR_PHRASES_FILE_NAME="WORD_FOR_PHRASES_FILE_NAME";
    public static final String RAW_WORD_FOR_PHRASES_TYPE_WORD="WORD_FOR_PHRASES_TYPE_WORD";
    public static final String RAW_WORD_FOR_PHRASES_PHRASE_NUMBER = "PHRASE_NUMBER";

    public static final String TABLE_NAME_LEVEL_QUESTION="LEVEL_QUESTION";
    public static final String RAW_LEVEL_QUESTION_QUESTION_ID="LEVEL_QUESTION_QUESTION_ID";
    public static final String RAW_LEVEL_QUESTION_LEVEL="LEVEL_QUESTION_LEVEL";

    public static final String TABLE_NAME_AUDIO_TO_ANIMATION ="TABLE_NAME_AUDIO_TO_ANIMATION";
    public static final String RAW_AUDIO_TO_ANIMATION_AUDIO_FILE_NAME ="AUDIO_TO_LECTURE_AUDIO_FILE_NAME";
    public static final String RAW_AUDIO_TO_ANIMATION_ANIMATION_ID ="AUDIO_TO_LECTURE_ANIMATION_ID";
    public static final String RAW_AUDIO_TO_ANIMATION_FILE_URL ="AUDIO_TO_LECTURE_FILE_URL";

    public static final String TABLE_NAME_QUEST_TIPS = "QUEST_TIPS";
    public static final String RAW_QUEST_TIPS_QUEST_ID="QUEST_ID";
    public static final String RAW_QUEST_TIPS_TIPS="TIPS";

    public static final String TABLE_NAME_QUEST_FOR_TIPS = "QUEST_FOR_TIPS";
    public static final String RAW_QUEST_FOR_TIPS_QUEST="QUEST";
    public static final String RAW_QUEST_FOR_TIPS_ANSWER="ANSWER";



    public static final String DB_NAME = "TEACHDB";


    public TeachDB(Context context) {
        super(context, TeachDB.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_LECTURE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+RAW_LECTURE_TITLE+" STRING);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_CONTENT_TO_LECTURE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +RAW_CONTENT_TO_LECTURE_LECTURE_ID +" INTEGER, "
                +RAW_CONTENT_TO_LECTURE_START_FRAME+" INTEGER, "
                +RAW_CONTENT_TO_LECTURE_END_FRAME  +" INTEGER, "
                +RAW_CONTENT_TO_LECTURE_CONTENT    +" STRING);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_TEST+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_TEST_TARGET_TYPE+" STRING, "+
                RAW_TEST_TARGET_ID  +" INTEGER, "+
                RAW_TEST_TITLE      +" STRING, "+
                RAW_TEST_DESCRIPTION+" STRING);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_QUEST+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_QUEST_TEST_ID+" INTEGER, "+
                RAW_QUEST_ANSWER +" STRING, "+
                RAW_QUEST_CONTENT+" STRING);");




        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_ANIMATION+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_ANIMATION_NAME        +" STRING, "+
                RAW_ANIMATION_TYPE        +" INTEGER, "+
                RAW_ANIMATION_FPS         +" INTEGER, "+
                RAW_ANIMATION_FRAME_COUNT +" INTEGER, "+
                RAW_ANIMATION_OPTION_CODE +" INTEGER, "+
                RAW_ANIMATION_EQ_START    +" INTEGER, "+
                RAW_ANIMATION_EQ_END      +" INTEGER, "+
                RAW_ANIMATION_START_FRAME +" INTEGER, "+
                RAW_ANIMATION_CHRONOMETRY +" STRING, "+
                RAW_ANIMATION_PATTERN     +" STRING, "+
                RAW_ANIMATION_IS_SYNC+" BOOLEAN);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_IMAGES_ANIMATION+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_IMAGES_ANIMATION_ANIMATION_ID     +" INTEGER, "+
                RAW_IMAGES_ANIMATION_FILE_NAME       +" STRING, "+
                RAW_IMAGES_ANIMATION_FILE_SIZE        +" INTEGER, "+
                RAW_IMAGES_ANIMATION_FILE_URL+" STRING, "+
                RAW_IMAGES_ANIMATION_IS_SYNC +" BOOLEAN);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_ANIMATION_TO_LECTURE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_ANIMATION_TO_LECTURE_ANIMATION_ID+" INTEGER, "+
                RAW_ANIMATION_TO_LECTURE_LECTURE_ID  +" INTEGER, "+
                RAW_ANIMATION_TO_LECTURE_START_FRAME +" INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_AUDIO_FILE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_AUDIO_FILE_NAME                +" STRING, "+
                RAW_AUDIO_FILE_LECTURE_ID          +" INTEGER, "+
                RAW_AUDIO_FILE_QUEST_ID            +" INTEGER, "+
                RAW_AUDIO_FILE_QUEST_CONSTRUCTOR_ID+" INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_TEST_SESSION+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_TEST_SESSION_TEST_ID    +" INTEGER, "+
                RAW_TEST_SESSION_USER_ID    +" INTEGER, "+
                RAW_TEST_SESSION_TIME_START +" DATETIME, "+
                RAW_TEST_SESSION_TIME_END   +" DATETIME, "+
                RAW_TEST_SESSION_RESULT     +" FLOAT);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_ANSWER_LOG+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_ANSWER_LOG_TEST_SESSION_ID+" INTEGER, "+
                RAW_ANSWER_LOG_QUEST_ID       +" INTEGER, "+
                RAW_ANSWER_LOG_TIME_START     +" DATETIME, "+
                RAW_ANSWER_LOG_TIME_END       +" DATETIME, "+
                RAW_ANSWER_LOG_RESULT         +" INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_PHRASES+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_PHRASES_MOOD_TYPE+" INTEGER, "+
                RAW_PHRASES_TYPE+" INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_WORD_FOR_PHRASES+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_WORD_FOR_PHRASES_PHRASE_ID    +" INTEGER, "+
                RAW_WORD_FOR_PHRASES_FILE_NAME+" STRING, "+
                RAW_WORD_FOR_PHRASES_PHRASE_NUMBER    +" INTEGER, "+
                RAW_WORD_FOR_PHRASES_TYPE_WORD+" INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_LEVEL_QUESTION+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_LEVEL_QUESTION_QUESTION_ID    +" INTEGER, "+
                RAW_LEVEL_QUESTION_LEVEL+" INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME_AUDIO_TO_ANIMATION +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RAW_AUDIO_TO_ANIMATION_AUDIO_FILE_NAME +" STRING, "+
                RAW_AUDIO_TO_ANIMATION_FILE_URL +" STRING, "+
                RAW_AUDIO_TO_ANIMATION_ANIMATION_ID +" INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME_QUEST_TIPS+ " ("+
                RAW_QUEST_TIPS_TIPS + " STRING, "+
                RAW_QUEST_TIPS_QUEST_ID+" INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME_QUEST_FOR_TIPS+ " (_id INTEGER, "+
                RAW_QUEST_FOR_TIPS_QUEST + " STRING, "+
                RAW_QUEST_FOR_TIPS_ANSWER+" STRING);");


        fillPhrasesF5(sqLiteDatabase);
        fillPhrasesF6(sqLiteDatabase);
        fillPhrasesF7(sqLiteDatabase);
        fillPhrasesF21(sqLiteDatabase);
        fillPhrasesF3(sqLiteDatabase);

        fillLecture(sqLiteDatabase);
        
        //fillContent(sqLiteDatabase);

        fillAudioForLecture(sqLiteDatabase);

        fillTest(sqLiteDatabase);

        fillQuests(sqLiteDatabase);
        fillQuestsLevel(sqLiteDatabase);

        fillQuestTips(sqLiteDatabase);
    }


    private void fillQuestTips(SQLiteDatabase sqLiteDatabase)
    {
       // sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(id,'quest','answer')");
       // sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('tips',questid)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(1,'1x1','1')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('0',1)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('2',1)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('3',1)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',1)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',1)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('100',1)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(2,'1x2','2')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',2)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('5',2)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('4',2)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('3',2)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',2)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',2)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(3,'2x1','2')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',3)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('5',3)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('4',3)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('3',3)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',3)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',3)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(4,'1x3','3')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',4)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',4)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',4)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',4)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',4)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',4)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(5,'3x1','3')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',5)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',5)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',5)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',5)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',5)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',5)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(6,'1x4','4')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',6)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('5',6)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',6)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',6)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',6)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',6)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(7,'4x1','4')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',7)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('5',7)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',7)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',7)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',7)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',7)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(8,'2x2','4')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('2',8)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('5',8)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',8)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',8)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',8)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',8)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(9,'1x5','5')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',9)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('0',9)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',9)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',9)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',9)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',9)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(10,'5x1','5')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',10)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('0',10)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',10)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',10)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',10)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',10)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(11,'1x6','6')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('3',11)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('5',11)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',11)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('7',11)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',11)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',11)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(12,'6x1','6')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('3',12)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('5',12)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',12)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('7',12)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',12)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',12)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(13,'2x3','6')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('3',13)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',13)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',13)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('5',13)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',13)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',13)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(14,'3x2','6')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('3',14)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',14)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',14)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('5',14)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',14)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',14)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(15,'1x7','7')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',15)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('17',15)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',15)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',15)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',15)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',15)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(16,'7x1','7')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',16)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('17',16)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',16)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',16)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',16)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',16)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(17,'2x4','8')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',17)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',17)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',17)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',17)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',17)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',17)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(18,'4x2','8')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',18)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',18)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',18)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',18)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',18)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',18)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(19,'1x8','8')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',19)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('81',19)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',19)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',19)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',19)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',19)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(20,'8x1','8')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',20)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('81',20)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',20)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',20)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',20)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',20)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(21,'1x9','9')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',21)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('19',21)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',21)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',21)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('90',21)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',21)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(22,'9x1','9')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',22)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('19',22)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',22)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',22)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('90',22)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',22)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(23,'3x3','9')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('3',23)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('33',23)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',23)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',23)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',23)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',23)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(24,'2x5','10')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('5',24)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('2',24)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',24)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('7',24)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',24)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',24)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(25,'5x2','10')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('5',25)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('2',25)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',25)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('7',25)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',25)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',25)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(26,'1x10','10')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',26)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',26)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',26)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',26)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',26)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',26)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(27,'10x1','10')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('1',27)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',27)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',27)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',27)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',27)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',27)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(28,'3x4','12')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',28)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('34',28)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',28)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('7',28)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',28)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',28)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(29,'4x3','12')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',29)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('34',29)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',29)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('7',29)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',29)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',29)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(30,'2x6','12')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',30)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('26',30)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',30)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',30)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',30)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',30)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(31,'6x2','12')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('6',31)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('26',31)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',31)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',31)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',31)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',31)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(32,'2x7','14')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',32)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('72',32)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',32)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',32)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',32)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',32)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(33,'7x2','14')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',33)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('72',33)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',33)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',33)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',33)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',33)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(34,'3x5','15')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',34)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('53',34)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',34)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',34)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('35',34)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',34)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(35,'5x3','15')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',35)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('53',35)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',35)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',35)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('35',35)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',35)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(36,'4x4','16')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',36)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',36)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('44',36)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',36)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',36)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',36)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(37,'2x8','16')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('2',37)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',37)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('28',37)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',37)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',37)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',37)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(38,'8x2','16')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('2',38)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('8',38)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('28',38)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',38)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',38)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',38)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(39,'3x6','18')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',39)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',39)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',39)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',39)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',39)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('36',39)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(40,'6x3','18')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',40)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',40)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',40)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',40)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',40)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('36',40)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(41,'2x9','18')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',41)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('28',41)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',41)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',41)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',41)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',41)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(42,'9x2','18')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',42)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('28',42)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',42)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',42)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',42)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',42)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(43,'4x5','20')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',43)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',43)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',43)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',43)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',43)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',43)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(44,'5x4','20')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',44)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',44)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',44)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('9',44)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',44)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',44)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(45,'2x10','20')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',45)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',45)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',45)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',45)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',45)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',45)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(46,'10x2','20')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',46)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',46)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',46)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',46)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',46)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',46)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(47,'3x7','21')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',47)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('37',47)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',47)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',47)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',47)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',47)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(48,'7x3','21')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',48)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('37',48)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',48)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',48)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',48)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',48)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(49,'4x6','24')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',49)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('22',49)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',49)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',49)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',49)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',49)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(50,'6x4','24')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',50)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('22',50)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',50)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',50)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',50)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',50)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(51,'3x8','24')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',51)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',51)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('26',51)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',51)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',51)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',51)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(52,'8x3','24')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',52)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',52)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('26',52)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',52)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',52)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',52)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(53,'5x5','25')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',53)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('55',53)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('35',53)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('10',53)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',53)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',53)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(54,'3x9','27')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',54)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('39',54)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('28',54)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',54)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',54)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',54)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(55,'9x3','27')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',55)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('39',55)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('28',55)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',55)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',55)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',55)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(56,'4x7','28')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',56)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',56)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('47',56)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',56)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',56)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',56)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(57,'7x4','28')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('24',57)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',57)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('47',57)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',57)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('21',57)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',57)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(58,'3x10','30')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',58)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('31',58)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('33',58)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',58)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',58)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('35',58)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(59,'10x3','30')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('27',59)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('31',59)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('33',59)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',59)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',59)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('35',59)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(60,'5x6','30')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',60)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('56',60)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('35',60)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',60)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',60)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',60)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(61,'6x5','30')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',61)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('56',61)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('35',61)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('11',61)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',61)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',61)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(62,'4x8','32')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',62)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',62)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('34',62)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',62)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',62)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('36',62)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(63,'8x4','32')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',63)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',63)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('34',63)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',63)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',63)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('36',63)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(64,'5x7','35')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('57',64)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('36',64)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',64)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',64)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',64)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',64)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(65,'7x5','35')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('57',65)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('36',65)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',65)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',65)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',65)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',65)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(66,'6x6','36')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',66)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('35',66)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('66',66)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('12',66)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',66)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',66)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(67,'4x9','36')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('49',67)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',67)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',67)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',67)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('45',67)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('35',67)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(68,'9x4','36')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('49',68)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',68)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',68)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',68)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('45',68)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('35',68)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(69,'4x10','40')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('36',69)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('41',69)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('44',69)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',69)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',69)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',69)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(70,'10x4','40')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('36',70)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('41',70)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('44',70)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',70)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('30',70)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',70)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(71,'5x8','40')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('45',71)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('44',71)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',71)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',71)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',71)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',71)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(72,'8x5','40')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('45',72)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('44',72)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',72)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',72)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('25',72)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',72)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(73,'6x7','42')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('49',73)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('46',73)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('47',73)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',73)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',73)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',73)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(74,'7x6','42')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('49',74)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('46',74)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('47',74)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',74)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('13',74)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',74)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(75,'5x9','45')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',75)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('46',75)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('49',75)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',75)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',75)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',75)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(76,'9x5','45')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',76)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('46',76)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('49',76)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',76)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',76)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',76)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(77,'6x8','48')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('68',77)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',77)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',77)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',77)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('42',77)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',77)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(78,'8x6','48')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('68',78)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',78)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',78)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',78)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('42',78)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',78)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(79,'7x7','49')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('45',79)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('47',79)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('77',79)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('14',79)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('42',79)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',79)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(80,'5x10','50')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('51',80)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('55',80)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',80)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',80)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('45',80)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',80)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(81,'10x5','50')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('51',81)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('55',81)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',81)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',81)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('45',81)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('40',81)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(82,'6x9','54')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',82)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('55',82)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('56',82)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',82)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',82)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('64',82)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(83,'9x6','54')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',83)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('55',83)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('56',83)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',83)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',83)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('64',83)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(84,'7x8','56')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',84)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('78',84)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('58',84)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',84)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',84)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',84)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(85,'8x7','56')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('48',85)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('78',85)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('58',85)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('15',85)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',85)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',85)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(86,'6x10','60')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('56',86)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('61',86)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('66',86)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',86)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',86)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',86)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(87,'10x6','60')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('56',87)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('61',87)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('66',87)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',87)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('50',87)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',87)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(88,'7x9','63')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('62',88)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('36',88)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('67',88)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',88)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',88)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',88)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(89,'9x7','63')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('62',89)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('36',89)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('67',89)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',89)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',89)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',89)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(90,'8x8','64')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('58',90)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('62',90)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('68',90)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('16',90)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',90)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('80',90)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(91,'7x10','70')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',91)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('71',91)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('80',91)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('17',91)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('77',91)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('63',91)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(92,'10x7','70')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('60',92)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('71',92)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('80',92)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('17',92)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('77',92)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('63',92)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(93,'8x9','72')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',93)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('74',93)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('78',93)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('17',93)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('80',93)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('90',93)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(94,'9x8','72')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',94)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('74',94)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('78',94)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('17',94)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('80',94)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('90',94)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(95,'8x10','80')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',95)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('78',95)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('88',95)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',95)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('90',95)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('81',95)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(96,'10x8','80')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',96)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('78',96)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('88',96)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',96)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('90',96)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('81',96)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(97,'9x9','81')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('79',97)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('82',97)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('83',97)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('18',97)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('80',97)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('90',97)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(98,'9x10','90')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',98)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('80',98)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('92',98)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('19',98)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('91',98)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('100',98)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(99,'10x9','90')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('70',99)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('80',99)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('92',99)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('19',99)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('91',99)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('100',99)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_FOR_TIPS VALUES(100,'10x10','100')");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('80',100)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('90',100)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('99',100)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('20',100)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('95',100)");
        sqLiteDatabase.execSQL("INSERT INTO QUEST_TIPS VALUES('85',100)");
    }

    private void fillQuestsLevel(SQLiteDatabase sqLiteDatabase) {

        ContentValues values = new ContentValues();
        int [] level0 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,30,31,32,33,34,35,40,41,42,43,
                44,45,46,50,51,52,53,55,56,60,61,62,70,71,72,80,81,82,89,90,91,92,93,94,95,96,97,98,99,100};
        int [] level1 = {24,26,27,28,29,33,35,36,37,38,39,44,46,47,48,49,53,54,55,56,57,58,59,63,64,65,66,67,68,69,73,74,75,76,77,78,79,83,84,85,86,87,88,89};

        int [] level2 = {27,28,29,36,37,38,39,47,48,49,54,57,58,59,63,64,65,66,67,68,69,73,74,75,76,77,78,79,83,84,85,86,87,88,89};
        Random level = new Random();
        for(int i=0;i<level0.length;i++)
        {
            values.put(RAW_LEVEL_QUESTION_QUESTION_ID,level0[i]);
            values.put(RAW_LEVEL_QUESTION_LEVEL,0);
            sqLiteDatabase.insert(TABLE_NAME_LEVEL_QUESTION,RAW_LEVEL_QUESTION_QUESTION_ID,values);
        }
        for(int i=0;i<level1.length;i++)
        {
            values.put(RAW_LEVEL_QUESTION_QUESTION_ID,level1[i]);
            values.put(RAW_LEVEL_QUESTION_LEVEL,1);
            sqLiteDatabase.insert(TABLE_NAME_LEVEL_QUESTION,RAW_LEVEL_QUESTION_QUESTION_ID,values);
        }
        for(int i=0;i<level2.length;i++)
        {
            values.put(RAW_LEVEL_QUESTION_QUESTION_ID,level2[i]);
            values.put(RAW_LEVEL_QUESTION_LEVEL,2);
            sqLiteDatabase.insert(TABLE_NAME_LEVEL_QUESTION,RAW_LEVEL_QUESTION_QUESTION_ID,values);
        }
    }

    private void fillPhrasesF5(SQLiteDatabase sqLiteDatabase) {

        long id;
        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();
        values.put(RAW_PHRASES_TYPE,"f5");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-S-1");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);


        //TODO:имя в середине когда нарежут фразы переделать
        values.put(RAW_PHRASES_TYPE,"f5");
        values.put(RAW_PHRASES_MOOD_TYPE,"N");
        id = sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-N-1-a");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-N-1-b");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,2);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);


        //TODO:имя в середине когда нарежут фразы переделать
        values.put(RAW_PHRASES_TYPE,"f5");
        values.put(RAW_PHRASES_MOOD_TYPE,"N");
        id = sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-N-2-a");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-N-2-b");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,2);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);


        values.put(RAW_PHRASES_TYPE,"f5");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id = sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID, (int) id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME, "f-5-S-2");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER, 0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);


        values.put(RAW_PHRASES_TYPE,"f5");
        values.put(RAW_PHRASES_MOOD_TYPE,"NN");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-NN-1");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);



        values.put(RAW_PHRASES_TYPE,"f5");
        values.put(RAW_PHRASES_MOOD_TYPE,"NN");
        id =sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-NN-2");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);


        values.put(RAW_PHRASES_TYPE,"f5");
        values.put(RAW_PHRASES_MOOD_TYPE,"NN");
        id=sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER, 0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-NN-3");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f5");
        values.put(RAW_PHRASES_MOOD_TYPE,"NN");
        id = sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-NN-4");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);


        values.put(RAW_PHRASES_TYPE,"f5");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id = sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-S-3");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);


        values.put(RAW_PHRASES_TYPE,"f5");
        values.put(RAW_PHRASES_MOOD_TYPE,"NN");
        id = sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-NN-5");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);


        values.put(RAW_PHRASES_TYPE,"f5");
        values.put(RAW_PHRASES_MOOD_TYPE,"NN");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-5-NN-6");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);





    }

    private void fillPhrasesF6(SQLiteDatabase sqLiteDatabase)
    {
       long id;
        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();
        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-S-1");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-S-2");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-S-3");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-S-4");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-S-5");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-S-6");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-S-7");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-S-8");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-S-9");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        //TODO:имя в середине когда нарежут фразы переделать
        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"P");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME, "f-6-P-1-a");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME, "");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME, "f-6-P-1-b");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,2);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"P");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-P-2");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        //TODO:имя в середине когда нарежут фразы переделать
        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"P");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-P-3-a");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-P-3-b");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,2);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"P");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-P-4");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"PP");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-PP-1");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"PP");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-PP-2");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"PP");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-PP-3");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"PP");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-PP-4");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"PP");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-PP-5");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f6");
        values.put(RAW_PHRASES_MOOD_TYPE,"PP");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-6-PP-6");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

    }

    private void fillPhrasesF7(SQLiteDatabase sqLiteDatabase)
    {
        long id;
        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();

        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-S-1");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);


        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-S-2");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-S-3");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-S-4");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        //TODO:имя в середине когда нарежут фразы переделать
        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-S-5");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        //TODO:имя в середине когда нарежут фразы переделать
        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-S-10-a");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-S-10-b");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,2);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        //TODO:имя в середине когда нарежут фразы переделать  дорежут
        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"N");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        /*values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);*/
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-N-1");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"N");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-N-2");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"N");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-N-3");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"N");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-N-4");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"N");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-N-5");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"N");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID, (int) id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-N-6");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"N");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-N-7");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"NN");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-NN-1");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f7");
        values.put(RAW_PHRASES_MOOD_TYPE,"NN");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-7-NN-2");
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

    }

    private void fillPhrasesF21(SQLiteDatabase sqLiteDatabase)
    {
        long id;
        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();

        values.put(RAW_PHRASES_TYPE,"f21");
        values.put(RAW_PHRASES_MOOD_TYPE,"NN");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_NAME);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-21-NN-1");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,0);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f21");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-21-S-1");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

        values.put(RAW_PHRASES_TYPE,"f21");
        values.put(RAW_PHRASES_MOOD_TYPE,"N");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME, "f-21-N-1");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);

    }


    private void fillPhrasesF3(SQLiteDatabase sqLiteDatabase)
    {
        long id;
        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();


        values.put(RAW_PHRASES_TYPE,"f3");
        values.put(RAW_PHRASES_MOOD_TYPE,"S");
        id= sqLiteDatabase.insert(TABLE_NAME_PHRASES,RAW_PHRASES_TYPE,values);
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_ID,(int)id);
        values2.put(RAW_WORD_FOR_PHRASES_TYPE_WORD, DataWord.TYPE_PHRASE);
        values2.put(RAW_WORD_FOR_PHRASES_FILE_NAME,"f-3-1");
        values2.put(RAW_WORD_FOR_PHRASES_PHRASE_NUMBER,1);
        sqLiteDatabase.insert(TABLE_NAME_WORD_FOR_PHRASES,RAW_WORD_FOR_PHRASES_PHRASE_ID,values2);



    }

    private void fillLecture(SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();

        for(int i=0;i<=10;i++)
        {
            values.put(RAW_LECTURE_TITLE,"Лекция "+i);
            db.insert(TABLE_NAME_LECTURE,RAW_LECTURE_TITLE,values);
        }
    }

    private void fillContent(SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();
        
        for(int i=1;i<=10;i++)
            for(int j=i;j<=10;j++)
            {
                values.put(RAW_CONTENT_TO_LECTURE_LECTURE_ID,i);
                values.put(RAW_CONTENT_TO_LECTURE_START_FRAME,j*10);
                values.put(RAW_CONTENT_TO_LECTURE_END_FRAME,-1);
                values.put(RAW_CONTENT_TO_LECTURE_CONTENT,i+" x "+j+" = "+i*j);
                db.insert(TABLE_NAME_CONTENT_TO_LECTURE,RAW_CONTENT_TO_LECTURE_LECTURE_ID,values);
            }

        for(int i=1;i<=10;i++)
            for(int j=1;j<i;j++)
            {
                values.put(RAW_CONTENT_TO_LECTURE_LECTURE_ID,i);
                values.put(RAW_CONTENT_TO_LECTURE_START_FRAME,j*10);
                values.put(RAW_CONTENT_TO_LECTURE_END_FRAME,i*10-2);
                values.put(RAW_CONTENT_TO_LECTURE_CONTENT,j+" x "+i+" = "+i*j);
                db.insert(TABLE_NAME_CONTENT_TO_LECTURE,RAW_CONTENT_TO_LECTURE_LECTURE_ID,values);

                values.put(RAW_CONTENT_TO_LECTURE_LECTURE_ID,i);
                values.put(RAW_CONTENT_TO_LECTURE_START_FRAME,i*10-1);
                values.put(RAW_CONTENT_TO_LECTURE_END_FRAME,-1);
                values.put(RAW_CONTENT_TO_LECTURE_CONTENT,i+" x "+j+" = "+i*j);
                db.insert(TABLE_NAME_CONTENT_TO_LECTURE,RAW_CONTENT_TO_LECTURE_LECTURE_ID,values);

            }
    }
    
    private void fillAudioForLecture(SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();
        
        for(int i=0;i<=10;i++)
        {
            values.put(RAW_AUDIO_FILE_LECTURE_ID,i);
            values.put(RAW_AUDIO_FILE_NAME,String.valueOf(i));
            db.insert(TABLE_NAME_AUDIO_FILE,RAW_AUDIO_FILE_LECTURE_ID,values) ;
        }
    }

    private void fillTest(SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();

        for(int i=1;i<=10;i++)
        {
            values.put(RAW_TEST_TARGET_TYPE,"lecture");
            values.put(RAW_TEST_TARGET_ID,i);
            values.put(RAW_TEST_TITLE,"Тест к лекции "+String.valueOf(i));
            db.insert(TABLE_NAME_TEST, RAW_TEST_TARGET_ID, values) ;
        }
    }

    private void fillQuests(SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();

        for (int i=1;i<=10;i++)
            for (int j=1;j<=10;j++)
            {
                values.put(RAW_QUEST_TEST_ID,i);
                values.put(RAW_QUEST_CONTENT,i+" x "+j+" = ");
                values.put(RAW_QUEST_ANSWER,String.valueOf(i*j));
                db.insert(TABLE_NAME_QUEST,RAW_QUEST_TEST_ID,values);


            }
    }

    private LinkedList<Integer[]> createListHelp(){
        BufferedReader in = null;
        LinkedList<Integer[]> values = new LinkedList<Integer[]>();
        try {
            in = new BufferedReader(new InputStreamReader(MyApplication.getInstance().getResources().openRawResource(R.raw.quest_help), "utf8"));
            while (in.ready()) {
                for (int i = 0; i < 100; i++) {
                    String a = in.readLine();
                    String[] aa = a.split("\t");
                    Integer[] bb=new Integer[3];
                    for (int j=0;j<bb.length;j++){
                        bb[j]=Integer.valueOf(aa[j]);
                    }
                    values.add(bb);

                }

            }
            in.close();
        } catch (Exception ex) {
        }
        return values;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
