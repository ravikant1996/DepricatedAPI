package com.example.imageholder.NetworkUtil;

import static com.example.imageholder.Constraints.CONNECTED;
import static com.example.imageholder.Constraints.NOT_CONNECTED;
import static com.example.imageholder.NetworkUtil.ApplicationClass.CHANNEL_ID;
import static com.example.imageholder.NetworkUtil.ApplicationClass.notificationID;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.imageholder.MainActivity;
import com.example.imageholder.R;

public class ShowNotification {


    public void showNotify(Context context) {
        try {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle(NOT_CONNECTED)
                    .setContentText("Please connect to the Internet")
                    .setAutoCancel(true)
                    .setVibrate(new long[]{-1})
                    .setContentInfo(context.getString(R.string.app_name))
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setSmallIcon(R.drawable.logo)
                    .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    .setLights(Color.GREEN, 1000, 300)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

            Intent resultIntent = new Intent(context, MainActivity.class);
            resultIntent.setAction(Intent.ACTION_MAIN);
            resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);

            // Adds the Intent that starts the Activity to the top of the stack
            notificationBuilder.setContentIntent(contentIntent);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.notify(notificationID, notificationBuilder.build());


        } catch (Exception e) {
            Log.e("Message", e.getMessage());
        }
    }
}
