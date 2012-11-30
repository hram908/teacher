package com.fedorvlasov.lazylist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private List<FriendFFFF> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    DialogFriends.OnFriendsListener  onFriendsListener;
    
    public LazyAdapter(Activity a, List<FriendFFFF> d, DialogFriends.OnFriendsListener onFriendsListener) {
        activity = a;
        data=d;
        this.onFriendsListener = onFriendsListener;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
        {
            vi = inflater.inflate(R.layout.item_list, null);
        }
        TextView text=(TextView)vi.findViewById(R.id.text);
        ImageView image=(ImageView)vi.findViewById(R.id.image);
        text.setText(data.get(position).getName());
        imageLoader.DisplayImage(data.get(position).getImage(), image);
        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(activity,"sasadsa "+data.get(position).getId(),200).show();
                onFriendsListener.select(data.get(position).getId(),data.get(position).getName());
            }
        });
        return vi;
    }
}