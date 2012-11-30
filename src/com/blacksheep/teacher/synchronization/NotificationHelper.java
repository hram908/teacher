package com.blacksheep.teacher.synchronization;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.blacksheep.teacher.R;
import com.blacksheep.teacher.activites.loadApp.AcLoadResources;
import com.blacksheep.teacher.activites.mainScreen.AcMainTeachScreen;

/**
 * Created with IntelliJ IDEA.
 * User: default
 * Date: 5/4/12
 * Time: 12:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationHelper {
    private Context mContext;
    private int NOTIFICATION_ID = 1;
    private int NOTIFICATION_ID_COMPLETAET = 12;
    private int NOTIFICATION_ERROR = 123;
    private Notification mNotification;
    private NotificationManager mNotificationManager;
    private PendingIntent mContentIntent;
    private CharSequence mContentTitle;
    public NotificationHelper(Context context)
    {
        mContext = context;
    }

    /**        asdsa
     * Put the notification into the status bar
     */

    public void createNotificationCompleate()
    {
        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE); // Создаем экземпляр менеджера уведомлений
        int icon = R.drawable.icon; // Иконка для уведомления, я решил воспользоваться стандартной иконкой для Email
        CharSequence tickerText = "Teacher"; // Подробнее под кодом
        long when = System.currentTimeMillis(); // Выясним системное время
        Notification notification = new Notification(icon, tickerText, when); // Создаем экземпляр уведомления, и передаем ему наши параметры
        Context context = mContext.getApplicationContext();
        CharSequence contentTitle = "Teacher"; // Текст заголовка уведомления при развернутой строке статуса
        CharSequence contentText = "Загрузка ресурсов успешна завершена"; //Текст под заголовком уведомления при развернутой строке статуса
        Intent notificationIntent = new Intent(mContext,AcMainTeachScreen.class); // Создаем экземпляр Intent
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.setLatestEventInfo(mContext,  "TEst test", "mess mess", contentIntent);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent); // Передаем в наше уведомление параметры вида при развернутой строке состояния
        mNotificationManager.notify(NOTIFICATION_ID_COMPLETAET, notification); // И наконец показываем наше уведомление через менеджер передав его ID
    }

    public void createNotificationError()
    {
        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE); // Создаем экземпляр менеджера уведомлений
        int icon = R.drawable.icon; // Иконка для уведомления, я решил воспользоваться стандартной иконкой для Email
        CharSequence tickerText = "Teacher"; // Подробнее под кодом
        long when = System.currentTimeMillis(); // Выясним системное время
        Notification notification = new Notification(icon, tickerText, when); // Создаем экземпляр уведомления, и передаем ему наши параметры
        Context context = mContext.getApplicationContext();
        CharSequence contentTitle = "Teacher"; // Текст заголовка уведомления при развернутой строке статуса
        CharSequence contentText = "Ошибка сервера, попробуйте загрузить еще раз"; //Текст под заголовком уведомления при развернутой строке статуса
        Intent notificationIntent = new Intent(mContext,AcLoadResources.class); // Создаем экземпляр Intent
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.setLatestEventInfo(mContext,  "TEst test", "mess mess", contentIntent);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent); // Передаем в наше уведомление параметры вида при развернутой строке состояния
        mNotificationManager.notify(NOTIFICATION_ID_COMPLETAET, notification); // И наконец показываем наше уведомление через менеджер передав его ID
    }

    public void createNotification() {
        //get the notification manager
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        //create the notification
       // int icon = android.R.drawable.stat_sys_download;
        int icon = R.drawable.icon;
        CharSequence tickerText ="trolol";// mContext.getString("trolol"); //Initial text that appears in the status bar
        long when = System.currentTimeMillis();
        mNotification = new Notification(icon, tickerText, when);

        //create the content which is shown in the notification pulldown
        mContentTitle ="trolol";// mContext.getString("trolol"); //Full title of the notification in the pull down
        CharSequence contentText = "0% complete"; //Text of the notification in the pull down

        //you have to set a PendingIntent on a notification to tell the system what you want it to do when the notification is selected
        //I don't want to use this here so I'm just creating a blank one
        Intent notificationIntent = new Intent(this.mContext, AcLoadResources.class);
        mContentIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);


        mNotification.contentView = new RemoteViews(mContext.getApplicationContext().getPackageName(), R.layout.notification_download);
        //add the additional content and intent to the notification
        //mNotification.setLatestEventInfo(mContext, mContentTitle, contentText, mContentIntent);

        //make this notification appear in the 'Ongoing events' section
        mNotification.contentIntent = mContentIntent;
        mNotification.contentView.setProgressBar(R.id.status_progress, 100, 0, false);
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;

        //show the notification
        mNotificationManager.notify(NOTIFICATION_ID, mNotification);
        //yyt
    }

    /**
     * Receives progress updates from the background task and updates the status bar notification appropriately
     * @param percentageComplete
     */
    public void progressUpdate(int percentageComplete,String text) {

        // Изменяем текущую позицию на i
        mNotification.contentView.setProgressBar(R.id.status_progress, 100, percentageComplete, false);
// Задаем текст
        mNotification.contentView.setTextViewText(R.id.text_progress, text);

        //build up the new status message
      //  CharSequence contentText = percentageComplete + "% "+text;
        //publish it to the status bar
       // mNotification.setLatestEventInfo(mContext, mContentTitle, contentText, mContentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mNotification);
    }

    /**
     * called when the background task is complete, this removes the notification from the status bar.
     * We could also use this to add a new ‘task complete’ notification
     */
    public void completed()    {
        //remove the notification from the status bar
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
}
