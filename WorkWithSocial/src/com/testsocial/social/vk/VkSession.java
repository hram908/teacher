package com.testsocial.social.vk;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by IntelliJ IDEA.
 * FriendFFFF: default
 * Date: 4/1/12
 * Time: 11:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class VkSession {

    private SharedPreferences _prefs;
    private final String PREFS_NAME = "Vk:Settings";
    private Context _context;
    private SharedPreferences.Editor _editor;

    public VkSession(){}

    public VkSession(Context context){
        _context = context;
        _prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        _editor = _prefs.edit();
    }

    public void saveAccessTokenI(String accessToken, String expires, String userId){
        _editor.putString("VkAccessToken", accessToken);
        _editor.putString("VkExpiresIn", expires);
        _editor.putString("VkUserId", userId);
        _editor.putLong("VkAccessTime", System.currentTimeMillis());
        _editor.commit();
    }

    public String[] getAccessTokenI(){
        String[] params = new String[4];
        params[0] = _prefs.getString("VkAccessToken", "");
        params[1] = _prefs.getString("VkExpiresIn", "");
        params[2] = _prefs.getString("VkUserId", "");
        params[3] =  String.valueOf(_prefs.getLong("VkAccessTime",0));
        return params;
    }

    public void resetAccessTokenI(){
        _editor.putString("VkAccessToken", "");
        _editor.putString("VkExpiresIn", "");
        _editor.putString("VkUserId", "");
        _editor.putLong("VkAccessTime", 0);
        _editor.commit();
    }

    public void saveAccessTokenParent(String accessToken, String expires, String userId){
        _editor.putString("VkAccessTokenP", accessToken);
        _editor.putString("VkExpiresInP", expires);
        _editor.putString("VkUserIdP", userId);
        _editor.putLong("VkAccessTimeP", System.currentTimeMillis());
        _editor.commit();
    }

    public String[] getAccessTokenParent(){
        String[] params = new String[4];
        params[0] = _prefs.getString("VkAccessTokenP", "");
        params[1] = _prefs.getString("VkExpiresInP", "");
        params[2] = _prefs.getString("VkUserIdP", "");
        params[3] =  String.valueOf(_prefs.getLong("VkAccessTimeP",0));
        return params;
    }

    public void resetAccessTokenParent(){
        _editor.putString("VkAccessTokenP", "");
        _editor.putString("VkExpiresInP", "");
        _editor.putString("VkUserIdP", "");
        _editor.putLong("VkAccessTimeP", 0);
        _editor.commit();
    }




}
