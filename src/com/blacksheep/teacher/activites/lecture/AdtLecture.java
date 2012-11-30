package com.blacksheep.teacher.activites.lecture;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.model.dataEntity.DataLecture;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 3/6/12
 * Time: 11:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdtLecture extends BaseAdapter {
    private final LayoutInflater inflater;
    private List<DataLecture> lectures;

    private IClickLecture clickLectureHandler;


    public AdtLecture(Activity activity, List<DataLecture> lectures, IClickLecture clickLectureHandler)
    {
        this.lectures = lectures;
        this.clickLectureHandler = clickLectureHandler;
        inflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return lectures.size();
    }

    @Override
    public Object getItem(int i) {
        return lectures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        final DataLecture dataLecture = lectures.get(i);

        TextView tvName;

        if(view==null)
        {
            view = inflater.inflate(R.layout.adt_activity_lecture,null);

        }
        tvName = (TextView) view.findViewById(R.id.tv_lecture_item_name);
        tvName.setText(dataLecture.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLectureHandler.click(dataLecture.getId());
            }
        });

        return view;
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//
//        super.onListItemClick(l, v, position, id);
//        // Do what you will need to w/ a List Row Click
//       // Intent h = new Intent(FindList.this,showmaplocation.class);
//       // startActivityForResult(h,SAVE_TABS);
//    }


    public interface IClickLecture{
        public void click(int lectureID);
    }
}
