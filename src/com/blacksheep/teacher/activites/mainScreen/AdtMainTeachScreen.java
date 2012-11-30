package com.blacksheep.teacher.activites.mainScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.blacksheep.teacher.R;

/**
 * Created by IntelliJ IDEA.
 * User: default
 * Date: 4/5/12
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdtMainTeachScreen  extends BaseAdapter implements TitleProvider {

    private static final int VIEW1 = 0;
    private static final int VIEW2 = 1;
    private static final int VIEW3 = 2;
    private static final int VIEW_MAX_COUNT = VIEW2 + 1;
    private final String[] names = {"View1","View2","View3"};

    private LayoutInflater mInflater;

    public AdtMainTeachScreen(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int view = getItemViewType(position);
        if (convertView == null) {
            switch (view) {
                case VIEW1:
                    convertView = mInflater.inflate(R.layout.ac_main_teach_screen_basic, null);
                    break;
                case VIEW2:
                    convertView = mInflater.inflate(R.layout.ac_main_teach_screen_main, null);
                    break;
                case VIEW3:
                    convertView = mInflater.inflate(R.layout.ac_main_teach_screen_setting, null);
                    break;
            }
        }
        return convertView;
    }

    public String getTitle(int position) {
        return names[position];
    }

}

