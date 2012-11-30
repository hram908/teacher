package com.fedorvlasov.lazylist;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * FriendFFFF: Defafault
 * Date: 30.05.12
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class DialogFriends extends Dialog {

    ListView list;
    LazyAdapter adapter;


    private OnFriendsListener onFriendsListener;

    private List<FriendFFFF> friendFFFFs;
    private Activity activity;

    public DialogFriends(Activity activity, OnFriendsListener onFriendsListener, List<FriendFFFF> friendFFFFs) {
        super(activity);
        this.activity = activity;
        this.onFriendsListener = onFriendsListener;
        this.friendFFFFs = friendFFFFs;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.dialog_friends);
        list=(ListView)findViewById(R.id.list);
        adapter=new LazyAdapter(activity, friendFFFFs,onFriendsListener);
        list.setAdapter(adapter);
        Toast.makeText(this.getContext(),"test ets e",2000).show();



    }

    public interface OnFriendsListener
    {
        public void select(String id,String name);
    }


}
