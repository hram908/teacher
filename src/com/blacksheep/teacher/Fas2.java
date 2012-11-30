package com.blacksheep.teacher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/12/12
 * Time: 2:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class Fas2 extends Activity{

    EditText editText;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.dbtest);
        TestView testView = new TestView(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lltest);
        linearLayout.addView(testView);

        long start = System.currentTimeMillis();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.anim);

        long end = System.currentTimeMillis() - start;
        int d = 0;

//        DBManager.testAnim = false;
//
//        LinkedList<DataLecture> lectures = new LinkedList<DataLecture>(DBManager.getInstance().getLectures());
//        AdtLecture lectureAdapter = new AdtLecture(this,lectures,new AdtLecture.IClickLecture() {
//            @Override
//            public void click(int lectureID) {
//                lectureID--;
//                Intent intent = new Intent(Fas2.this, AcStartLecture.class);
//                intent.putExtra(AcStartLecture.LECTURE_ID,lectureID);
//                startActivity(intent);
//            }
//        });
//        listView.setAdapter(lectureAdapter);

    }

    public void clickAcTest(View v)
    {
        Intent intent = new Intent(this, FasActiv.class);
        startActivity(intent);
    }
}
