package com.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


public class AdtPesentCard extends BaseAdapter implements TitleProvider {

    private static final int VIEW1 = 0;
    private static final int VIEW2 = 1;
    private static final int VIEW3 = 2;
    private static final int VIEW4 = 3;
    private static final int VIEW5 = 4;
    private static final int VIEW6 = 5;
   /* private static final int VIEW7 = 6;
    private static final int VIEW8 = 7;
    private static final int VIEW9 = 8;
    private static final int VIEW10 = 9;
    private static final int VIEW11 = 10;
    private static final int VIEW12 = 11;
    private static final int VIEW13 = 12;
    private static final int VIEW14 = 13;
    private static final int VIEW15 = 14;
    private static final int VIEW16 = 15;
    private static final int VIEW17 = 16;
    private static final int VIEW18 = 17;
    private static final int VIEW19 = 18;
    private static final int VIEW20 = 19;
    private static final int VIEW21 = 20;
    private static final int VIEW22 = 21;
    private static final int VIEW23 = 22;*/
    private static final int VIEW_MAX_COUNT = VIEW5 + 1;
    //private final String[] names = {"View1","View2","View3","View4","View5","View6","View7","View8","View9","View10","View11","View12","View13","View14","View15","View16","View17","View18","View19","View20","View21","View22","View23"};
    private final String[] names = {"View1","View2","View3","View4","View5","View6"};
    //private final String[] names = {"View1","View2","View3","View4","View5","View6","View7","View8","View9","View10"};

    private LayoutInflater mInflater;
    private ImageView mImageView;
    public AdtPesentCard(Context context, ImageView imageView1) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImageView=imageView1;
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
        return 6;
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
                    View view1=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv1=(ImageView)view1.findViewById(R.id.ac_present_card_agnessa);
                    imv1.setImageResource(R.drawable.im_ac_present_card_agnessa_1);
                    convertView = imv1;
                    /*mImageView.setImageResource(R.drawable.im_ac_present_card_agnessa_1);
                    convertView=mImageView;*/
                    break;
                case VIEW2:
//                    ImageView iv2=mImageView;
//                    iv2.setImageResource(R.drawable.im_ac_present_card_agnessa_3);
//                    convertView=iv2;
                    View view2=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv2=(ImageView)view2.findViewById(R.id.ac_present_card_agnessa);
                    imv2.setImageResource(R.drawable.im_ac_present_card_agnessa_2);
                    convertView = imv2;
                    /*mImageView.setImageResource(R.drawable.im_ac_present_card_agnessa_2);
                    convertView=mImageView; */
                    break;
                case VIEW3:
                    View view3=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv3=(ImageView)view3.findViewById(R.id.ac_present_card_agnessa);
                    imv3.setImageResource(R.drawable.im_ac_present_card_agnessa_3);
                    convertView = imv3;
                    /*mImageView.setImageResource(R.drawable.im_ac_present_card_agnessa_3);
                    convertView=mImageView;*/
                    break;
                case VIEW4:
                    View view4=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv4=(ImageView)view4.findViewById(R.id.ac_present_card_agnessa);
                    imv4.setImageResource(R.drawable.im_ac_present_card_agnessa_4);
                    convertView = imv4;
                    break;
                case VIEW5:
                    View view5=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv5=(ImageView)view5.findViewById(R.id.ac_present_card_agnessa);
                    imv5.setImageResource(R.drawable.im_ac_present_card_agnessa_5);
                    convertView = imv5;
                    break;
                case VIEW6:
                    View view6=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv6=(ImageView)view6.findViewById(R.id.ac_present_card_agnessa);
                    imv6.setImageResource(R.drawable.im_ac_present_card_agnessa_6);
                    convertView = imv6;
                    break;
                /*case VIEW7:
                    View view7=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv7=(ImageView)view7.findViewById(R.id.ac_present_card_agnessa);
                    imv7.setImageResource(R.drawable.im_ac_present_card_agnessa_7);
                    convertView = imv7;
                    break;
                case VIEW8:
                    View view8=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv8=(ImageView)view8.findViewById(R.id.ac_present_card_agnessa);
                    imv8.setImageResource(R.drawable.im_ac_present_card_agnessa_8);
                    convertView = imv8;
                    break;
                case VIEW9:
                    View view9=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv9=(ImageView)view9.findViewById(R.id.ac_present_card_agnessa);
                    imv9.setImageResource(R.drawable.im_ac_present_card_agnessa_9);
                    convertView = imv9;
                    break;
                case VIEW10:
                    View view10=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv10=(ImageView)view10.findViewById(R.id.ac_present_card_agnessa);
                    imv10.setImageResource(R.drawable.im_ac_present_card_agnessa_10);
                    convertView = imv10;
                    break;
                case VIEW11:
                    View view11=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv11=(ImageView)view11.findViewById(R.id.ac_present_card_agnessa);
                    imv11.setImageResource(R.drawable.im_ac_present_card_agnessa_11);
                    convertView = imv11;
                    break;
                case VIEW12:
                    View view12=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv12=(ImageView)view12.findViewById(R.id.ac_present_card_agnessa);
                    imv12.setImageResource(R.drawable.im_ac_present_card_agnessa_12);
                    convertView = imv12;
                    break;
                case VIEW13:
                    View view13=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv13=(ImageView)view13.findViewById(R.id.ac_present_card_agnessa);
                    imv13.setImageResource(R.drawable.im_ac_present_card_agnessa_13);
                    convertView = imv13;
                    break;
                case VIEW14:
                    View view14=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv14=(ImageView)view14.findViewById(R.id.ac_present_card_agnessa);
                    imv14.setImageResource(R.drawable.im_ac_present_card_agnessa_14);
                    convertView = imv14;
                    break;
                case VIEW15:
                    View view15=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv15=(ImageView)view15.findViewById(R.id.ac_present_card_agnessa);
                    imv15.setImageResource(R.drawable.im_ac_present_card_agnessa_15);
                    convertView = imv15;
                    break;
                case VIEW16:
                    View view16=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv16=(ImageView)view16.findViewById(R.id.ac_present_card_agnessa);
                    imv16.setImageResource(R.drawable.im_ac_present_card_agnessa_16);
                    convertView = imv16;
                    break;
                case VIEW17:
                    View view17=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv17=(ImageView)view17.findViewById(R.id.ac_present_card_agnessa);
                    imv17.setImageResource(R.drawable.im_ac_present_card_agnessa_17);
                    convertView = imv17;
                    break;
                case VIEW18:
                    View view18=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv18=(ImageView)view18.findViewById(R.id.ac_present_card_agnessa);
                    imv18.setImageResource(R.drawable.im_ac_present_card_agnessa_18);
                    convertView = imv18;
                    break;
                case VIEW19:
                    View view19=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv19=(ImageView)view19.findViewById(R.id.ac_present_card_agnessa);
                    imv19.setImageResource(R.drawable.im_ac_present_card_agnessa_19);
                    convertView = imv19;
                    break;
                case VIEW20:
                    View view20=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv20=(ImageView)view20.findViewById(R.id.ac_present_card_agnessa);
                    imv20.setImageResource(R.drawable.im_ac_present_card_agnessa_20);
                    convertView = imv20;
                    break;
                case VIEW21:
                    View view21=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv21=(ImageView)view21.findViewById(R.id.ac_present_card_agnessa);
                    imv21.setImageResource(R.drawable.im_ac_present_card_agnessa_21);
                    convertView = imv21;
                    break;
                case VIEW22:
                    View view22=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv22=(ImageView)view22.findViewById(R.id.ac_present_card_agnessa);
                    imv22.setImageResource(R.drawable.im_ac_present_card_agnessa_22);
                    convertView = imv22;
                    break;
                case VIEW23:
                    View view23=mInflater.inflate(R.layout.ac_present_card,null);
                    ImageView imv23=(ImageView)view23.findViewById(R.id.ac_present_card_agnessa);
                    imv23.setImageResource(R.drawable.im_ac_present_card_agnessa_23);
                    convertView = imv23;
                    break;*/
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

}
