package cl.mzapatae.mobileApp.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.BaseApplication;
import cl.mzapatae.mobileApp.utils.Constants;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 26-04-17
 * E-mail: miguel.zapatae@gmail.com
 */

//TODO: Improve on message received
public class FcmCallbackService extends FirebaseMessagingService {
    private static final String TAG = "FCM Callback Service";
    private static final int GCM_REQUEST_CODE = 6900;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Message Body: " + remoteMessage.getNotification().getBody());
        Log.d(TAG, "Received Data: " + remoteMessage.getData());

        String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();
        String clickAction = remoteMessage.getNotification().getClickAction();
        Map parameters = remoteMessage.getData();

        if (parameters == null || parameters.isEmpty() || clickAction == null || clickAction.equalsIgnoreCase(Constants.INTENT_ACTION_MAIN)) {
            if (BaseApplication.isActivityVisible()) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                sendBroadcast(intent);
            }
        } else {
            if (BaseApplication.isActivityVisible()) {
                Log.d(TAG, "Custom Data Detected");
                Intent intent = new Intent();

                if (clickAction.equalsIgnoreCase(Constants.INTENT_OPEN_SECTION)) {
                    intent.setAction(clickAction);
                    intent.putExtra("section", parameters.get("section").toString());
                    Log.d(TAG, "Click Action: INTENT_OPEN_SECTION Validate!");
                }

                if (clickAction.equalsIgnoreCase(Constants.INTENT_SHOW_MESSAGE)) {
                    intent.setAction(clickAction);
                    intent.putExtra("title", title);
                    intent.putExtra("message", message);
                    Log.d(TAG, "Click Action: INTENT_SHOW_MESSAGE Validate!");
                }

                sendBroadcast(intent);
            }
        }
    }

    private void sendNotification(String title, String message, Intent intent) {
        //TODO: Customize intent with custom action or extra
        PendingIntent pendingIntent = PendingIntent.getActivity(this, GCM_REQUEST_CODE, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_plus) //TODO: User Custom Notification Drawable
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
