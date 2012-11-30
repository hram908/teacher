package com.testsocial;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import com.fedorvlasov.lazylist.DialogFriends;

import com.fedorvlasov.lazylist.FileCache;
import com.fedorvlasov.lazylist.Friend;
import com.fedorvlasov.lazylist.FriendFFFF;
import com.perm.kate.api.Api;
import com.perm.kate.api.KException;
import com.perm.kate.api.User;
import com.testsocial.social.DialogSelectSocial;
import com.testsocial.social.SocialCallBack;
import com.testsocial.social.fb.FacebookWorker;
import com.testsocial.social.vk.VkApp;
import com.testsocial.social.vk.VkSession;
import com.testsocial.social.vk.VkontakteWorker;
import org.json.JSONException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * FriendFFFF: Defafault
 * Date: 30.05.12
 * Time: 10:29
 * To change this template use File | Settings | File Templates.
 */
public class AcSocial extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.ac_social);
      // FileCache fileCache = new FileCache(this);
    }

    public void clickloginVk(View v)
    {
        VkontakteWorker vkontakteWorker = new VkontakteWorker();
        vkontakteWorker.authorizeI(this,new SocialCallBack(this) {
            @Override
            public void ready(Object paramObject) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void clickloginFb(View v)
    {
//        FacebookWorker facebookWorker = new FacebookWorker();
//        facebookWorker.authorizeI(this);
    }

    public void clickpostMyWallFb(View v)
    {
        Drawable d = getResources().getDrawable(R.drawable.ac_game_back_table);
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();
        FacebookWorker facebookWorker = new FacebookWorker();
        facebookWorker.postI(null,"testets",bitmapdata,this);
    }


    public void clickpostMyWallVk(View v)
    {
        VkSession _vkSess = new VkSession(this);
        String[] params = _vkSess.getAccessTokenI();

        String accessToken = params[0];
        String ownerId = params[2];
        VkApp vkApp = new VkApp(this);
        Drawable d = getResources().getDrawable(R.drawable.ac_game_back_table);
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();


        try {
            vkApp.postToWall(accessToken,Integer.parseInt(ownerId),new  ByteArrayInputStream(bitmapdata,0,bitmapdata.length),"teste teste");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (KException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void clickpostFriendsWallFb(View v) throws JSONException {


//        FacebookWorker facebookWorker = new FacebookWorker();
//        Collection<FacebookWorker.User> users = facebookWorker.getFriendsI(this);
//        List<FriendFFFF> users1 = new ArrayList<FriendFFFF>();
//        for (FacebookWorker.User user:users)
//        {
//            FriendFFFF user1 = new FriendFFFF(user.getAvatar(),user.name,user.id);
//            users1.add(user1);
//        }
//
//        DialogFriends dialogFriends = new DialogFriends(this,new DialogFriends.OnFriendsListener() {
//            @Override
//            public void select(String id) {
//                Drawable d = getResources().getDrawable(R.drawable.b4);
//                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] bitmapdata = stream.toByteArray();
//                FacebookWorker facebookWorker = new FacebookWorker();
//                facebookWorker.postI(id,"testets",bitmapdata,AcSocial.this);
//            }
//        }, users1);
//        dialogFriends.show();



    }

    public void clickpostFriendsWallVk(View v)
    {
        final VkSession _vkSess = new VkSession(AcSocial.this);
        String[] params = _vkSess.getAccessTokenI();

        final String accessToken = params[0];
        String ownerId = params[2];


        Api api = new Api(accessToken,"asd");
        List<User> users = new ArrayList<User>();
        try {
           users = api.getFriends(null,null,null);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (KException e) {

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        List<FriendFFFF> users1 = new ArrayList<FriendFFFF>();
        for (User user:users)
        {
            FriendFFFF user1 = new FriendFFFF(user.photo_medium,user.first_name+" "+user.last_name,String.valueOf(user.uid));
            users1.add(user1);
        }

        DialogFriends dialogFriends = new DialogFriends(this,new DialogFriends.OnFriendsListener() {
            @Override
            public void select(String id,String name) {
                VkApp vkApp = new VkApp(AcSocial.this);
                Drawable d = getResources().getDrawable(R.drawable.ac_game_back_table);
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bitmapdata = stream.toByteArray();


                try {
                    vkApp.postToWall(accessToken,Integer.parseInt(id),new  ByteArrayInputStream(bitmapdata,0,bitmapdata.length),"teste teste");
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (KException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (JSONException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }, users1);
        dialogFriends.show();

//        Drawable d = getResources().getDrawable(R.drawable.b4);
//        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte[] bitmapdata = stream.toByteArray();
//
//
//        try {
//            vkApp.postToWall("defb82af92cfd5e7dd531dc66bdd24d39eddd08dd08fb14124b5202b607e9e8",66279869,new  ByteArrayInputStream(bitmapdata,0,bitmapdata.length),"teste teste");
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (KException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (JSONException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }

    public void clickPost(View v)
    {
        String  mPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/TeachData/teachPresent.jpeg";
        DialogSelectSocial dialogSelectSocial = new DialogSelectSocial(this,mPath);
        dialogSelectSocial.show();
    }
}
