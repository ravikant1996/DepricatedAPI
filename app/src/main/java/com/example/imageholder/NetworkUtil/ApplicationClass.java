package com.example.imageholder.NetworkUtil;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

public class ApplicationClass extends Application {
    public static final int notificationID = 1;
    public static final String CHANNEL_ID = "Network Issue";
    public static final String CHANNEL_DESC = "Network Issue";


    @Override
    public void onCreate() {
        super.onCreate();
        registerChannel();
    }


    private void registerChannel() {
        // Add as notification
        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_DESC, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            channel.setShowBadge(true);
            channel.canShowBadge();
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});

            assert manager != null;
            manager.createNotificationChannel(channel);
        }
    }
}
