package com.testsocial.social.vk;

import android.content.Context;
import android.net.Uri;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.perm.kate.api.Api;
import com.perm.kate.api.KException;
import com.perm.kate.api.Photo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * FriendFFFF: default
 * Date: 4/1/12
 * Time: 11:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class VkApp {

    public static final String CALLBACK_URL = "http://api.vkontakte.ru/blank.html";
    private static final String OAUTH_AUTHORIZE_URL = "http://api.vkontakte.ru/oauth/authorize?client_id=2885772&redirect_uri=http://api.vkontakte.ru/blank.html &scope=friends,wall,photos&display=touch&response_type=token";

    private Context _context;
    private VkDialogListener _listener;
    private VkSession _vkSess;

    private String VK_API_URL = "https://api.vkontakte.ru/method/";
    private String VK_POST_TO_WALL_URL = VK_API_URL + "wall.post?";
    private String VK_PHOTOS_GET_WALL_UPLOAD_SERVER = VK_API_URL + "photos.getWallUploadServer?";
    private String VK_GET_AVATAR_URL = VK_API_URL + "users.get?";

    public VkApp() {
    }

    public VkApp(Context context) {
        _context = context;
        _vkSess = new VkSession(_context);
    }

    public void setListener(VkDialogListener listener) {
        _listener = listener;
    }

    public void showLoginDialog() {
        new DialogVk(_context, OAUTH_AUTHORIZE_URL, _listener).show();
    }

    //parse vkontakte JSON response
    private boolean parseResponse(String jsonStr) throws VkontakteError {
        boolean errorFlag = true;

        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(jsonStr);
            JSONObject errorObj = null;

            if (jsonObj.has("error")) {
                errorObj = jsonObj.getJSONObject("error");
                int errCode = errorObj.getInt("error_code");
                throw new VkontakteError("error", "url", errCode);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            //Log.d(Constants.DEBUG_TAG,"exception when creating json object");
        }

        return errorFlag;
    }

//    public String getUserPhoto()
//    {
//
//        boolean errorFlag = true;
//        String[] params = _vkSess.getAccessToken();
//
//        String accessToken = params[0];
//        String ownerId = params[2];
//        String requestStr = VK_GET_AVATAR_URL+"uids="+ownerId+"&fields=photo&name_case=nom"+"&access_token="+accessToken;;
//        String responseText="";
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet(requestStr);
//
//        try {
//            HttpResponse response = client.execute(request);
//            HttpEntity entity = response.getEntity();
//
//             responseText = EntityUtils.toString(entity);
//
//            //parse response for error code or not
//            errorFlag = parseResponse(responseText);
//
//            //Log.d(Constants.DEBUG_TAG,"response text="+responseText);
//        }
//        catch(ClientProtocolException cexc){
//            cexc.printStackTrace();
//        }
//        catch(IOException ioex){
//            ioex.printStackTrace();
//        }
//
//        return responseText;
//
//    }


    public boolean postToWallI(String message) throws VkontakteError {
        boolean errorFlag = true;

        String[] params = _vkSess.getAccessTokenI();

        String accessToken = params[0];
        String ownerId = params[2];

        //set request uri params
        VK_POST_TO_WALL_URL += "owner_id=" + ownerId + "&message=" + Uri.encode(message) + "&access_token=" + accessToken;

        //send request to vkontakte api
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(VK_POST_TO_WALL_URL);

        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            String responseText = EntityUtils.toString(entity);

            //parse response for error code or not
            errorFlag = parseResponse(responseText);

            //Log.d(Constants.DEBUG_TAG,"response text="+responseText);
        } catch (ClientProtocolException cexc) {
            cexc.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }

        return errorFlag;
    }

    public void postToWall(String accessToken, int userId, InputStream photo, String message) throws IOException, KException, JSONException, com.google.gson.JsonSyntaxException {

        Api api = new Api(accessToken, "");
        //List<FriendFFFF> users = api.getFriends(null,null,null);
        ArrayList<String> strings = null;
        if (photo != null) {
            String strrrrr = api.photosGetWallUploadServer(null, null);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(strrrrr);


            InputStreamBody inputStreamBody = new InputStreamBody(photo, "file.jpg");
            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("photo", inputStreamBody);
            httpPost.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(httpPost);
            String ss = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();

            Test testss = gson.fromJson(ss, new TypeToken<Test>() {
            }.getType());


            final Photo bbb = api.saveWallPhoto(testss.server, testss.photo, testss.hash, null, null).get(0);
            String localObject = "photo" + bbb.owner_id + "_" + bbb.pid;
            strings = new ArrayList<String>();
            strings.add(localObject);
        }
        api.createWallPost(userId, message, strings, null, false, false, false, null, null, null, null);
    }

    public class Test {
        String photo;
        String server;
        String hash;
    }

    public boolean postToWallParent(String message) throws VkontakteError {
        boolean errorFlag = true;

        String[] params = _vkSess.getAccessTokenParent();

        String accessToken = params[0];
        String ownerId = params[2];

        //set request uri params
        VK_POST_TO_WALL_URL += "owner_id=" + ownerId + "&message=" + Uri.encode(message) + "&access_token=" + accessToken;

        //send request to vkontakte api
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(VK_POST_TO_WALL_URL);

        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            String responseText = EntityUtils.toString(entity);

            //parse response for error code or not
            errorFlag = parseResponse(responseText);

            //Log.d(Constants.DEBUG_TAG,"response text="+responseText);
        } catch (ClientProtocolException cexc) {
            cexc.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }

        return errorFlag;
    }

    public String[] getAccessToken(String url) {
        String[] query = url.split("#");
        String[] params = query[1].split("&");
        //params[0] - access token=value, params[1] - expires_in=value,
        //params[2] - user_id=value
        return params;
    }

//    public boolean hasAccessToken() {
//        String[] params = _vkSess.getAccessToken();
//        if( params != null ) {
//            long accessTime = Long.parseLong(params[3]);
//            long currentTime = System.currentTimeMillis();
//            long expireTime = (currentTime - accessTime) / 1000;
//
//            //Log.d(Constants.DEBUG_TAG,"expires time="+expireTime);
//
//            if( params[0].equals("") & params[1].equals("") & params[2].equals("") & Long.parseLong(params[3]) ==0 ){
//                //Log.d(Constants.DEBUG_TAG,"access token empty");
//                return false;
//            }
//            else if( expireTime >= Long.parseLong(params[1]) ) {
//                //Log.d(Constants.DEBUG_TAG,"access token time expires out");
//                return false;
//            }
//            else {
//                //Log.d(Constants.DEBUG_TAG,"access token ok");
//                return true;
//            }
//        }
//        return false;
//    }

    public void saveAccessTokenI(String accessToken, String expires, String userId) {
        _vkSess.saveAccessTokenI(accessToken, expires, userId);
    }

    public void saveAccessTokenParent(String accessToken, String expires, String userId) {
        _vkSess.saveAccessTokenParent(accessToken, expires, userId);
    }

    public void resetAccessTokenI() {
        _vkSess.resetAccessTokenI();
    }

    public void resetAccessTokenParent() {
        _vkSess.resetAccessTokenParent();
    }

    public interface VkDialogListener {
        void onComplete(String url);

        void onError(String description);
    }

}
