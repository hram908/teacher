package com.blacksheep.teacher.activites.adminka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.model.dataEntity.DataAnimation;
import com.blacksheep.teacher.model.database.DBManager;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Defafault
 * Date: 22.07.12
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class AcChooseAnimation extends Activity implements AdapterView.OnItemClickListener {
    ListView listView;
    LinkedList<DataAnimation> dataAnimations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.ac_chooce_animation);
        listView = (ListView) findViewById(R.id.ac_choose_animation_list);
        fillListAnimations();
    }

    private void fillListAnimations()
    {
        dataAnimations =  DBManager.getInstance().getAnimations();
        Collections.sort(dataAnimations,new Comparator<DataAnimation>() {
            @Override
            public int compare(DataAnimation dataAnimation, DataAnimation dataAnimation1) {

                if(dataAnimation.getAnimation_type()>dataAnimation1.getAnimation_type())
                    return 1;
                if(dataAnimation.getAnimation_type()==dataAnimation1.getAnimation_type())
                {
                    if(dataAnimation.getOption_code()>dataAnimation1.getOption_code())
                        return 1;
                    if(dataAnimation.getOption_code()==dataAnimation1.getOption_code())
                        return 0;
                }
                return -1;
            }
        });

        List<String> list = new ArrayList<String>();
        for (DataAnimation da:dataAnimations)
        {
            list.add(da.getId()+" "+da.getName());
        }
          ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
          listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
         listView.setOnItemClickListener(this);
          listView.setAdapter(stringArrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent =new Intent(this, AcAnimation.class);
        intent.putExtra(AcAnimation.EXT_ANIMATION_ID,dataAnimations.get(i).getId());
        startActivity(intent);
        Log.i("Lisc select","select "+i);
    }
}
