package com.testsocial.social.vk;

import android.content.Context;
import com.perm.kate.api.KException;
import com.testsocial.social.SocialCallBack;
import com.testsocial.social.SocialCommand;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created with IntelliJ IDEA.
 * FriendFFFF: default
 * Date: 4/24/12
 * Time: 6:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class VkontakteWorker {

    public void authorizeI(Context c, final SocialCallBack sc)
    {

        final VkApp vkApp = new  VkApp(c);
        vkApp.setListener(new VkApp.VkDialogListener() {
            @Override
            public void onComplete(String url) {
                String [] param = vkApp.getAccessToken(url);
                vkApp.saveAccessTokenI(param[0].split("=")[1],param[1].split("=")[1],param[2].split("=")[1]);
                String s =url;
                int d =0;
                if(sc!=null)
                    sc.ready(null);
            }

            @Override
            public void onError(String description) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        vkApp.showLoginDialog();
    }

    public void authorizeParent(Context c)
    {
        final VkApp vkApp = new  VkApp(c);
        vkApp.setListener(new VkApp.VkDialogListener() {
            @Override
            public void onComplete(String url) {
                String [] param = vkApp.getAccessToken(url);
                vkApp.saveAccessTokenParent(param[0].split("=")[1],param[1].split("=")[1],param[2].split("=")[1]);
                String s =url;
            }

            @Override
            public void onError(String description) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        vkApp.showLoginDialog();
    }

    public void postWallI(String message,Context c) throws VkontakteError {
        final VkApp vkApp = new  VkApp(c);
        vkApp.postToWallI(message);
    }

    public void postWallI(String message,byte [] picture,Context c) throws VkontakteError {
        final VkApp vkApp = new  VkApp(c);
        vkApp.postToWallI(message);

        HttpClient  httpClient = new DefaultHttpClient();
    }

    public void postWallParent(String message,Context c) throws VkontakteError {
        final VkApp vkApp = new  VkApp(c);
        vkApp.postToWallParent(message);
    }






}
