package com.testsocial.social.fb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.testsocial.social.SocialCallBack;
import com.testsocial.social.error.SocailErrorDontAutoriz;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * FriendFFFF: default
 * Date: 4/24/12
 * Time: 12:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class FacebookWorker {

    public static final String ACCESS_TOKEN_I = "access_token_i";
    public static final String ACCESS_EXPIRES_I = "access_expires_i";

    public static final String ACCESS_TOKEN_PARENT = "access_token_parent";
    public static final String ACCESS_EXPIRES_PARENT = "access_expires_parent";

    public static final String AppID = "295908833822676";

    // public boolean

    public void authorizeI(final Activity act, final SocialCallBack socialCallBack) {
        final Facebook facebook = new Facebook(AppID);
        facebook.authorize(act, new String[]{"email", "publish_stream"}, Facebook.FORCE_DIALOG_AUTH, new Facebook.DialogListener() {
            @Override
            public void onComplete(Bundle values) {

                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(act.getApplicationContext()).edit();
                editor.putString(ACCESS_TOKEN_I, facebook.getAccessToken());
                editor.putLong(ACCESS_EXPIRES_I, facebook.getAccessExpires());
                editor.commit();
                if(socialCallBack!=null)
                    socialCallBack.ready(null);
//                try {
//                    facebook.logout(act);
//                } catch (IOException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }

            }

            @Override
            public void onFacebookError(FacebookError error) {
            }

            @Override
            public void onError(DialogError e) {
            }

            @Override
            public void onCancel() {
            }
        });
    }

    public void logout(Activity act) {
        Facebook facebook = new Facebook(AppID);
        facebook.logoutMy(act);

    }

    public void authorizeParent(final Activity act) {
        final Facebook facebook = new Facebook(AppID);
        facebook.shouldExtendAccessToken();
        facebook.authorize(act, new String[]{"email", "publish_stream"}, Facebook.FORCE_DIALOG_AUTH, new Facebook.DialogListener() {
            @Override
            public void onComplete(Bundle values) {

                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(act.getApplicationContext()).edit();
                editor.putString(ACCESS_TOKEN_PARENT, facebook.getAccessToken());
                editor.putLong(ACCESS_EXPIRES_PARENT, facebook.getAccessExpires());
                editor.commit();
//                try {
//                    facebook.logout(act);
//                } catch (IOException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }

            }

            @Override
            public void onFacebookError(FacebookError error) {
            }

            @Override
            public void onError(DialogError e) {
            }

            @Override
            public void onCancel() {
            }
        });
    }

    public void post(boolean i, String id, String message, byte[] picture, Context c) throws SocailErrorDontAutoriz, FacebookError, IOException, JSONException {

        if (id == null)
            id = "me";
        final Facebook facebook = new Facebook(AppID);
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(c.getApplicationContext());
        if (i) {
            if (!prefs.getString(ACCESS_TOKEN_I, "").equals(""))
                facebook.setAccessToken(prefs.getString(ACCESS_TOKEN_I, ""));
            else {
                //facebook.setAccessToken(prefs.getString(ACCESS_TOKEN_I, "adsad"));
                throw new SocailErrorDontAutoriz();
            }
            if (prefs.getLong(ACCESS_EXPIRES_I, 0) != 0)
                facebook.setAccessExpires(prefs.getLong(ACCESS_EXPIRES_I, 0));
            else
                throw new SocailErrorDontAutoriz();
                //facebook.setAccessExpires(prefs.getLong(ACCESS_EXPIRES_I, 1231231232132112l));
        } else {
            if (!prefs.getString(ACCESS_TOKEN_PARENT, "").equals(""))
                facebook.setAccessToken(prefs.getString(ACCESS_TOKEN_PARENT, ""));
            else {
                throw new SocailErrorDontAutoriz();
            }
            if (prefs.getLong(ACCESS_EXPIRES_PARENT, 0) != 0)
                facebook.setAccessExpires(prefs.getLong(ACCESS_EXPIRES_PARENT, 0));
        }

        Bundle parameters = new Bundle();
        parameters.putByteArray("picture", picture);
        parameters.putString("message", message);

        parameters.putString("description", "");

        String response = null;
        response = facebook.request(id + "/photos", parameters, "POST");
        Util.parseJson(response);
        Log.i("fbresponse", response);


    }

    public Collection<User> getFriends(boolean i, Context c) throws JSONException, SocailErrorDontAutoriz,FacebookError, IOException,com.google.gson.JsonSyntaxException {
        final Facebook facebook = new Facebook(AppID);
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(c.getApplicationContext());
        if (i) {
            if (!prefs.getString(ACCESS_TOKEN_I, "").equals(""))
                facebook.setAccessToken(prefs.getString(ACCESS_TOKEN_I, ""));
            else {
                throw new SocailErrorDontAutoriz();
            }
            if (prefs.getLong(ACCESS_EXPIRES_I, 0) != 0)
                facebook.setAccessExpires(prefs.getLong(ACCESS_EXPIRES_I, 0));
            else {
                throw new SocailErrorDontAutoriz();
            }
        } else {
            if (!prefs.getString(ACCESS_TOKEN_PARENT, "").equals(""))
                facebook.setAccessToken(prefs.getString(ACCESS_TOKEN_PARENT, ""));
            else {
                throw new SocailErrorDontAutoriz();
            }
            if (prefs.getLong(ACCESS_EXPIRES_PARENT, 0) != 0)
                facebook.setAccessExpires(prefs.getLong(ACCESS_EXPIRES_PARENT, 0));
        }
        Bundle parameters = new Bundle();


        String response = null;

        response = facebook.request("me/friends", parameters, "GET");

        Log.i("fbresponse", response);
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject(response);
        String ss = jsonObject.getString("data");
        Collection<User> users = gson.fromJson(ss, new TypeToken<Collection<User>>() {
        }.getType());
        return users;


    }

    public void postI(String id, String message, byte[] picture, Activity c) {
        if (id == null)
            id = "me";
        final Facebook facebook = new Facebook(AppID);
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(c.getBaseContext());
        if (!prefs.getString(ACCESS_TOKEN_I, "").equals(""))
            facebook.setAccessToken(prefs.getString(ACCESS_TOKEN_I, ""));
        if (prefs.getLong(ACCESS_EXPIRES_I, 0) != 0)
            facebook.setAccessExpires(prefs.getLong(ACCESS_EXPIRES_I, 0));
        Bundle parameters = new Bundle();
        parameters.putByteArray("picture", picture);
        parameters.putString("message", message);

        parameters.putString("description", "");

        String response = null;
        try {
            //response = facebook.request("me/photos", parameters, "POST");
            response = facebook.request(id + "/photos", parameters, "POST");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Log.i("fbresponse", response);


    }


    public void postParent(String message, Activity c) {
        final Facebook facebook = new Facebook(AppID);
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(c.getBaseContext());
        if (!prefs.getString(ACCESS_TOKEN_PARENT, "").equals(""))
            facebook.setAccessToken(prefs.getString(ACCESS_TOKEN_PARENT, ""));
        if (prefs.getLong(ACCESS_EXPIRES_PARENT, 0) != 0)
            facebook.setAccessExpires(prefs.getLong(ACCESS_EXPIRES_PARENT, 0));
        Bundle parameters = new Bundle();
        //parameters.putString("picture", "http://lastpost2012.com/assets/to-face.png");
        parameters.putString("message", message);

        //parameters.putString("description", "");

        String response = null;
        try {
            response = facebook.request("me/feed", parameters, "POST");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Log.i("fbresponse", response);


    }


    public class User {
        public String name;
        public String id;

        public String getAvatar() {
            return "http://graph.facebook.com/" + id + "/picture";
        }
    }


}
