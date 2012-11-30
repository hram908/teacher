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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import com.facebook.android.FacebookError;
import com.fedorvlasov.lazylist.DialogFriends;
import com.fedorvlasov.lazylist.FriendFFFF;
import com.perm.kate.api.Api;
import com.perm.kate.api.KException;
import com.testsocial.R;
import com.testsocial.social.error.SocailErrorDontAutoriz;
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
 * User: vladimirmolodkin
 * Date: 01.06.12
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class DialogSelectFb extends Dialog {
    Activity act;
    String picturePath;

    public DialogSelectFb(Context context, String picturePath) {
        super(context);
        act = (Activity) context;
        this.picturePath = picturePath;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_fb);
        Button button = (Button) findViewById(R.id.dialog_select_fb_wall);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                execute(new SocialCommand(new SocialCallBack(DialogSelectFb.this.act ) {
                    @Override
                    public void ready(Object paramObject) {
                        Toast.makeText(DialogSelectFb.this.act, "Сообщение отпрвлено", 1000).show();
                    }
                },DialogSelectFb.this.act ) {
                    @Override
                    public void run() throws Exception {


                        Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] bitmapdata = stream.toByteArray();

                        FacebookWorker facebookWorker = new FacebookWorker();
                        facebookWorker.post(true, null, "test test", bitmapdata, DialogSelectFb.this.getContext());

                    }
                });
            }
        });


        Button btnFriends = (Button) findViewById(R.id.dialog_select_fb_friends);
        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                execute(new SocialCommand(new SocialCallBack(DialogSelectFb.this.act) {
                    @Override
                    public void ready(Object paramObject) {
                        Collection<FacebookWorker.User> users = (Collection<FacebookWorker.User>)  paramObject;
                        final List<FriendFFFF> users1 = new ArrayList<FriendFFFF>();
                        for (FacebookWorker.User user : users) {
                            FriendFFFF user1 = new FriendFFFF(user.getAvatar(), user.name, user.id);
                            users1.add(user1);
                        }

                        act.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                DialogFriends dialogFriends = new DialogFriends(act, new DialogFriends.OnFriendsListener() {
                                    @Override
                                    public void select(final String id,String name) {


                                        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(act);
                                        myAlertDialog.setTitle("Отправка подарка");
                                        myAlertDialog.setMessage("Отправить подарок - "+name+" ?");
                                        myAlertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface arg0, int arg1) {

                                                execute(new SocialCommand(new SocialCallBack(DialogSelectFb.this.act) {
                                                    @Override
                                                    public void ready(Object paramObject) {
                                                        Toast.makeText(DialogSelectFb.this.act, "Сообщение отпрвлено", 1000).show();
                                                    }
                                                }, DialogSelectFb.this.act) {
                                                    @Override
                                                    public void run() throws Exception {
                                                        Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                                                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                                        byte[] bitmapdata = stream.toByteArray();
                                                        FacebookWorker facebookWorker = new FacebookWorker();
                                                        facebookWorker.post(true, id, "testets", bitmapdata, DialogSelectFb.this.getContext());
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
                }, DialogSelectFb.this.act) {
                    @Override
                    public void run() throws Exception {
                        FacebookWorker facebookWorker = new FacebookWorker();
                        Collection<FacebookWorker.User> users = null;
                        users = facebookWorker.getFriends(true, DialogSelectFb.this.getContext());
                        this.res=users;
                    }
                });

//                FacebookWorker facebookWorker = new FacebookWorker();
//                Collection<FacebookWorker.User> users = null;
//                try {
//                    users = facebookWorker.getFriends(true, DialogSelectFb.this.getContext());
//                } catch (JSONException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                    Toast.makeText(DialogSelectFb.this.getContext(), "JSONException ", 1000).show();
//                } catch (SocailErrorDontAutoriz socailErrorDontAutoriz) {
//                    socailErrorDontAutoriz.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                    Toast.makeText(DialogSelectFb.this.getContext(), "SocailErrorDontAutoriz", 1000).show();
//                } catch (IOException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                    Toast.makeText(DialogSelectFb.this.getContext(), "IOException", 1000).show();
//                } catch (FacebookError facebookError) {
//                    facebookError.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                    Toast.makeText(DialogSelectFb.this.getContext(), "FacebookError", 1000).show();
//                }
//                List<FriendFFFF> users1 = new ArrayList<FriendFFFF>();
//                for (FacebookWorker.User user : users) {
//                    FriendFFFF user1 = new FriendFFFF(user.getAvatar(), user.name, user.id);
//                    users1.add(user1);
//                }
//
//                DialogFriends dialogFriends = new DialogFriends(DialogSelectFb.this.getContext(), new DialogFriends.OnFriendsListener() {
//                    @Override
//                    public void select(String id) {
//                        Drawable d = DialogSelectFb.this.getContext().getResources().getDrawable(R.drawable.b4);
//                        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                        byte[] bitmapdata = stream.toByteArray();
//                        FacebookWorker facebookWorker = new FacebookWorker();
//                        try {
//                            facebookWorker.post(true, id, "testets", bitmapdata, DialogSelectFb.this.getContext());
//                        } catch (SocailErrorDontAutoriz socailErrorDontAutoriz) {
//                            socailErrorDontAutoriz.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                        } catch (FacebookError facebookError) {
//                            facebookError.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                        } catch (IOException e) {
//                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                        } catch (JSONException e) {
//                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                        }
//                    }
//                }, users1);
//                dialogFriends.show();
            }
        });

    }

    private ProgressDialog progressDialog;
    private void innerExecute(final SocialCommand paramCommand)
    {
        try {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog =  ProgressDialog.show(act, "", "Идет отправка поздравления на стену", true);
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
        } catch (FacebookError localKException) {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
            int s = localKException.getErrorCode();
            if(localKException.getErrorType().contains("OAuthException"))
            {

                act.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FacebookWorker facebookWorker = new FacebookWorker();
                        facebookWorker.authorizeI(DialogSelectFb.this.act,new SocialCallBack(DialogSelectFb.this.act) {
                            @Override
                            public void ready(Object paramObject) {
                               new Thread(new Runnable() {
                                   @Override
                                   public void run() {
                                       execute(paramCommand);
                                   }
                               }).start();
                            }
                        });
                    }
                });

            }

        } catch (SocailErrorDontAutoriz socailErrorDontAutoriz) {
            socailErrorDontAutoriz.printStackTrace();
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    FacebookWorker facebookWorker = new FacebookWorker();
                    facebookWorker.authorizeI(DialogSelectFb.this.act,new SocialCallBack(DialogSelectFb.this.act) {
                        @Override
                        public void ready(Object paramObject) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    execute(paramCommand);
                                }
                            }).start();
                        }
                    });
                }
            });

        } catch (IOException e) {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            paramCommand.reportError(e);
        }
        catch (Exception localThrowable)
        {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
            paramCommand.reportError(localThrowable);
        }
        catch (Throwable localThrowable) {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
            paramCommand.reportError(localThrowable);
        }
    }

    private void execute(final SocialCommand paramCommand) {

        new Thread(new Runnable() {
            @Override
            public void run() {
               innerExecute(paramCommand);
            }
        }).start();

    }


}
