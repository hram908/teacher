package com.testsocial.social;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import com.fedorvlasov.lazylist.DialogFriends;
import com.fedorvlasov.lazylist.FriendFFFF;
import com.perm.kate.api.Api;
import com.perm.kate.api.KException;
import com.perm.kate.api.User;
import com.testsocial.R;
import com.testsocial.social.vk.VkApp;
import com.testsocial.social.vk.VkSession;
import com.testsocial.social.vk.VkontakteWorker;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimirmolodkin
 * Date: 01.06.12
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class DialogSelectVk extends Dialog {
    Activity act;
    String picturePath;
    public DialogSelectVk(Context context, String picturePath) {
        super(context);
        act = (Activity) context;
        this.picturePath = picturePath;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_vk);
        Button btnWall = (Button) findViewById(R.id.dialog_select_vk_wall);
        btnWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                execute(new SocialCommand(new SocialCallBack(act) {
                    @Override
                    public void ready(Object paramObject) {
                        Toast.makeText(DialogSelectVk.this.act, "Сообщение отпрвлено", 1000).show();
                    }
                }, act) {
                    @Override
                    public void run() throws Exception {

                        VkSession _vkSess = new VkSession(DialogSelectVk.this.getContext());
                        String[] params = _vkSess.getAccessTokenI();

                        final String accessToken = params[0];
                        final String ownerId = params[2];
                        final VkApp vkApp = new VkApp(DialogSelectVk.this.getContext());
                        Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        final byte[] bitmapdata = stream.toByteArray();

                        String id;
                        if (ownerId == "")
                            id = "1";
                        else
                            id = ownerId;
                        vkApp.postToWall(accessToken, Integer.parseInt(id), new ByteArrayInputStream(bitmapdata, 0, bitmapdata.length), "teste teste");
                    }
                });
            }
        });


        Button btnFriends = (Button) findViewById(R.id.dialog_select_vk_friends);
        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFriends();
            }
        });

    }



    public void sendFriends()
    {
        execute(new SocialCommand(new SocialCallBack(this.act) {
            @Override
            public void ready(Object paramObject) {
                List<User> users = (ArrayList<User>) paramObject;
                final List<FriendFFFF> users1 = new ArrayList<FriendFFFF>();
                for (User user:users)
                {
                    FriendFFFF user1 = new FriendFFFF(user.photo_medium,user.first_name+" "+user.last_name,String.valueOf(user.uid));
                    users1.add(user1);
                }

                act.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DialogFriends dialogFriends = new DialogFriends(act,new DialogFriends.OnFriendsListener() {
                            @Override
                            public void select(final String id,final String name) {

                                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(act);
                                myAlertDialog.setTitle("Отправка подарка");
                                myAlertDialog.setMessage("Отправить подарок - "+name+" ?");
                                myAlertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface arg0, int arg1) {

                                        final VkApp vkApp = new VkApp(DialogSelectVk.this.getContext());
                                        Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                        final byte[] bitmapdata = stream.toByteArray();
                                        execute(new SocialCommand(new SocialCallBack(DialogSelectVk.this.act) {
                                            @Override
                                            public void ready(Object paramObject) {
                                                Toast.makeText(DialogSelectVk.this.act, "Сообщение отпрвлено", 1000).show();
                                            }
                                        }, DialogSelectVk.this.act) {
                                            @Override
                                            public void run() throws Exception {
                                                final VkSession _vkSess = new VkSession(DialogSelectVk.this.getContext());
                                                String[] params = _vkSess.getAccessTokenI();

                                                final String accessToken = params[0];
                                                String ownerId = params[2];


                                                final Api api = new Api(accessToken,"asd");
                                                vkApp.postToWall(accessToken,Integer.parseInt(id),new  ByteArrayInputStream(bitmapdata,0,bitmapdata.length),"teste teste");
                                            }
                                        });

                                    }});
                                myAlertDialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface arg0, int arg1) {

                                    }});
                                myAlertDialog.show();





                            }
                        }, users1);
                        dialogFriends.show();
                    }
                });



            }
        }, this.act) {
            @Override
            public void run() throws Exception {
                final VkSession _vkSess = new VkSession(DialogSelectVk.this.getContext());
                String[] params = _vkSess.getAccessTokenI();

                final String accessToken = params[0];
                String ownerId = params[2];


                final Api api = new Api(accessToken,"asd");
                this.res = api.getFriends(null,null,null);
            }
        });



    }


    private void executeInner(final SocialCommand paramCommand)
    {
        try
        {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog =  ProgressDialog.show(act,"","Идет отправка поздравления на стену",true);
                }
            });
            paramCommand.recursion_count = (1 + paramCommand.recursion_count);
            paramCommand.run();
            paramCommand.reportSuccess(paramCommand.res);
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });

            return;
        }
        catch (KException localKException)
        {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
            Log.i("localKException","exceptiom ="+localKException.error_code);
            //while (true)
            //{
                if ((localKException.error_code == 3) || (localKException.error_code == 4) || (localKException.error_code == 5)|| (localKException.error_code == 113))
                {

                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            VkontakteWorker vkontakteWorker = new VkontakteWorker();
                            vkontakteWorker.authorizeI(DialogSelectVk.this.act,new SocialCallBack(DialogSelectVk.this.act) {
                                @Override
                                public void ready(Object paramObject) {
                                   new Thread(new Runnable() {
                                       @Override
                                       public void run() {
                                           executeInner(paramCommand);
                                       }
                                   }).start();

                                }
                            });
                        }
                    });

                   // break;
                }
                if (localKException.error_code == 6)

                    try
                    {
                        Thread.sleep(1000L);
                        executeInner(paramCommand);
                    }
                    catch (InterruptedException localInterruptedException)
                    {
                       // while (true)
                            localInterruptedException.printStackTrace();
                    }
                if (localKException.error_code == 14)
                {
                   //  break;
                }
//                if (localKException.error_code == 113)
//                {
//                    VkontakteWorker vkontakteWorker = new VkontakteWorker();
//                    vkontakteWorker.authorizeI(DialogSelectVk.this.act,new SocialCallBack(DialogSelectVk.this.act) {
//                        @Override
//                        public void ready(Object paramObject) {
//                            executeInner(paramCommand);
//                        }
//                    });
//                 //   break;
//                }
              //  break;
//                if ((localKException.error_code != 7) && (localKException.error_code != 170) && (localKException.error_code != 200) && (localKException.error_code != 212) && (localKException.error_code != 210) && (localKException.error_code != 180) && (localKException.error_code != 15) && (localKException.error_code != 214))
//                    Helper.reportError(localKException);
//                paramCommand.reportError(localKException);
        //    }
        } catch (Exception localThrowable)
        {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
            paramCommand.reportError(localThrowable);
        } catch (Throwable localThrowable)
        {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
            paramCommand.reportError(localThrowable);
        }
    }

    private void execute2(final SocialCommand paramCommand)
    {
        ProgressDialog progressDialog = new ProgressDialog(act);
        try
        {

            progressDialog.show(act,"","Идет отправка поздравления на стену",true);
            paramCommand.recursion_count = (1 + paramCommand.recursion_count);
            paramCommand.run();
            paramCommand.reportSuccess(paramCommand.res);
            progressDialog.hide();
            return;
        }
        catch (KException localKException)
        {
            progressDialog.hide();
            while (true)
            {
                if ((localKException.error_code == 3) || (localKException.error_code == 4) || (localKException.error_code == 5))
                {
                    VkontakteWorker vkontakteWorker = new VkontakteWorker();
                    vkontakteWorker.authorizeI(DialogSelectVk.this.act,new SocialCallBack(DialogSelectVk.this.act) {
                        @Override
                        public void ready(Object paramObject) {
                            execute(paramCommand);
                        }
                    });
                    break;
                }
                if (localKException.error_code == 6)
                    try
                    {
                        Thread.sleep(1000L);
                        execute(paramCommand);
                    }
                    catch (InterruptedException localInterruptedException)
                    {
                        while (true)
                            localInterruptedException.printStackTrace();
                    }
                if (localKException.error_code == 14)
                {
                    break;
                }
                if (localKException.error_code == 113)
                {
                    VkontakteWorker vkontakteWorker = new VkontakteWorker();
                    vkontakteWorker.authorizeI(DialogSelectVk.this.act,new SocialCallBack(DialogSelectVk.this.act) {
                        @Override
                        public void ready(Object paramObject) {
                            execute(paramCommand);
                        }
                    });
                    break;
                }
                break;
//                if ((localKException.error_code != 7) && (localKException.error_code != 170) && (localKException.error_code != 200) && (localKException.error_code != 212) && (localKException.error_code != 210) && (localKException.error_code != 180) && (localKException.error_code != 15) && (localKException.error_code != 214))
//                    Helper.reportError(localKException);
//                paramCommand.reportError(localKException);
            }
        } catch (Exception localThrowable)
        {
            progressDialog.hide();
            paramCommand.reportError(localThrowable);
        } catch (Throwable localThrowable)
        {
            progressDialog.hide();
            paramCommand.reportError(localThrowable);
        }
    }

     ProgressDialog progressDialog;
    private void execute(final SocialCommand paramCommand)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeInner(paramCommand);

            }
        }).start();

    }
}
