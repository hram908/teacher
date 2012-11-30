package com.zubrilka;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;


public class AdtZubrilka extends BaseAdapter implements TitleProvider {

    private static final int VIEW1 = 0;
    private static final int VIEW2 = 1;
    private static final int VIEW3 = 2;
    private static final int VIEW4 = 3;
    private static final int VIEW5 = 4;
    private static final int VIEW6 = 5;
    private static final int VIEW7 = 6;
    private static final int VIEW8 = 7;
    private static final int VIEW9 = 8;
    private static final int VIEW10 = 9;
    private static final int VIEW_MAX_COUNT = VIEW9 + 1;
    private final String[] names = {"View1","View2","View3","View4","View5","View6","View7","View8","View9","View10"};

    Context mContext;
    private LayoutInflater mInflater;
    private LinkedList<LinearLayout> listLayout;
    private LinkedList<LinkedList<LinearLayout>> listsLayout;
    ViewFlow mViewFlow;
    Button mPlay;
    public AdtZubrilka(Context context,ViewFlow viewFlow,Button play) {
    //public AdtZubrilka(Context context,ViewFlow viewFlow) {
        mViewFlow=viewFlow;
        mContext=context;
        mPlay=play;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listsLayout=new LinkedList<LinkedList<LinearLayout>>();
    }
    private LinkedList<LinearLayout> init(View view,int number_lesson)
    {
         LinearLayout main_ll=(LinearLayout)view;
         listLayout=new LinkedList<LinearLayout>();
         LinearLayout ll_1=(LinearLayout)main_ll.getChildAt(0);
         int count_11=ll_1.getChildCount()-1;
         int count_main_ll=main_ll.getChildCount();
        int count=0;
         for (int i=0;i<count_main_ll;i++)
         {
             LinearLayout current_main=(LinearLayout)main_ll.getChildAt(i);
             for (int j=0;j<count_11;j++)
             {
                 count+=1;
                 LinearLayout current=(LinearLayout)current_main.getChildAt(j);
                 current.setOnClickListener(new  ExampleListener());
                 setTypeFace(current,number_lesson+1,count);
                 listLayout.add(current);

             }
         }
        return listLayout;
    }
    private void setTypeFace(LinearLayout layout,int number_lesson, int count)
    {
        TextView tv1=(TextView)layout.getChildAt(0);
        tv1.setTypeface(Typeface.createFromAsset(mContext.getAssets(), AcZubrilka.FONT_SEGOEPR));
        tv1.setText(number_lesson + "X" + count + "=");
        //tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        //tv1.setTextColor(mContext.getResources().getColor(R.color.color_numbers));
        TextView tv2=(TextView)layout.getChildAt(1);
        tv2.setTypeface(Typeface.createFromAsset(mContext.getAssets(), AcZubrilka.FONT_SEGOEPR));
        tv2.setText(String.valueOf(count*number_lesson));
        //tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        //tv2.setTextColor(mContext.getResources().getColor(R.color.red_numbers));
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
        return 10;
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
                case VIEW1: {
                    View view1=mInflater.inflate(R.layout.adt_zubrilka_1,null);
                    listsLayout.add(init(view1,VIEW1));
                    convertView = view1;
                    break;  }
                case VIEW2: {
                    View view2=mInflater.inflate(R.layout.adt_zubrilka_1,null);
                    listsLayout.add(init(view2,VIEW2));
                    convertView = view2;
                    break;    }
                case VIEW3:{
                    View view3=mInflater.inflate(R.layout.adt_zubrilka_1,null);
                    listsLayout.add(init(view3,VIEW3));
                    convertView = view3;
                    break;        }
                case VIEW4: {
                    View view4=mInflater.inflate(R.layout.adt_zubrilka_1,null);
                    listsLayout.add(init(view4,VIEW4));
                    convertView = view4;
                    break;   }
                case VIEW5:    {
                    View view5=mInflater.inflate(R.layout.adt_zubrilka_1,null);
                    listsLayout.add(init(view5,VIEW5));
                    convertView = view5;
                    break;     }
                case VIEW6: {
                    View view6=mInflater.inflate(R.layout.adt_zubrilka_1,null);
                    listsLayout.add(init(view6,VIEW6));
                    convertView = view6;
                    break;    }
                case VIEW7:    {
                    View view7=mInflater.inflate(R.layout.adt_zubrilka_1,null);
                    listsLayout.add(init(view7,VIEW7));
                    convertView = view7;
                    break;  }
                case VIEW8:  {
                    View view8=mInflater.inflate(R.layout.adt_zubrilka_1,null);
                    listsLayout.add(init(view8,VIEW8));
                    convertView = view8;
                    break;      }
                case VIEW9:   {
                    View view9=mInflater.inflate(R.layout.adt_zubrilka_1,null);
                    listsLayout.add(init(view9,VIEW9));
                    convertView = view9;
                    break;       }
                case VIEW10:   {
                    View view10=mInflater.inflate(R.layout.adt_zubrilka_1,null);
                    listsLayout.add(init(view10,VIEW10));
                    convertView = view10;
                    break;      }
            }
        }
        return convertView;
    }



    /* (non-Javadoc)
	 * @see org.taptwo.android.widget.TitleProvider#getTitle(int)
	 */
    public String getTitle(int position) {
        return names[position];
    }

    public LinkedList<LinkedList<LinearLayout>> getListsLayout()
    {
        return listsLayout;
    }
    class ExampleListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            try{
                AudioPleer.mediaPlayerHead.stop();
                AudioPleer.mediaPlayerHead.release();
                cleanField(AudioPleer.currentScreen);
            }catch (NullPointerException ex) {}
            catch (IllegalStateException ex2){}
             /*if(AcZubrilka.isPlay)
             {
                 AudioPleer.mediaPlayerHead.stop();
                 AudioPleer.mediaPlayerHead.release();
             }
            AcZubrilka.isPlay=true;*/
            /*View view1=mInflater.inflate(R.layout.ac_zubrilka,null);
            final Button play=(Button)view1.findViewById(R.id.ac_zubrilka_play);*/
            AcZubrilka.isPlay=false;
             final int currentScreen=mViewFlow.getCurrentScreen();
             Integer tag=Integer.valueOf((String)view.getTag());
             cleanField(currentScreen);
             AudioPleer pleer=new AudioPleer(listsLayout.get(currentScreen),mContext,mViewFlow.getCurrentScreen());
            LinkedList<String> audio=new LinkedList<String>();
            String path= Environment.getExternalStorageDirectory().getAbsolutePath();
            audio.add(path+AcZubrilka.PATH_OPERATION+(currentScreen+1)+"x"+tag+AcZubrilka.exten);
            audio.add(path+AcZubrilka.PATH_RESULT+(currentScreen+1)*tag+AcZubrilka.exten);
            LinkedList<Integer> shuffle=new LinkedList<Integer>();
            shuffle.add(tag-1);

            pleer.play(audio, shuffle);

            //mPlay.setBackgroundResource(R.drawable.im_ac_zubrilka_button_stop);
            mPlay.setBackgroundResource(R.drawable.btn_play);

            pleer.setOnCompletionListener(new AudioPleer.OnCompletionListener() {
                @Override
                public void onCompletion(AudioPleer audioPhraseBuilder) {
                           //AcZubrilka.isPlay=false;
                           //cleanField(currentScreen);
                           //mPlay.setBackgroundResource(R.drawable.im_ac_zubrilka_button_play);

                }
            });
        }
    }
    private void cleanField(int screen)
    {
        for (int i=0;i<10;i++)
        {
            LinearLayout linearLayout=listsLayout.get(screen).get(i);
            linearLayout.setBackgroundResource(R.drawable.lesson_bg);
            TextView tv1=(TextView)linearLayout.getChildAt(0);
            tv1.setTextColor(mContext.getResources().getColor(R.color.color_numbers));

        }
    }


}
