package com.example.imageholder.NetworkUtil;

import static com.example.imageholder.Constraints.CONNECTED;
import static com.example.imageholder.Constraints.NOT_CONNECTED;
import static com.example.imageholder.NetworkUtil.ApplicationClass.notificationID;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.imageholder.MainActivity;

public class BroadcastListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectionUtils.isConnected(context)) {
            Toast.makeText(context, CONNECTED, Toast.LENGTH_SHORT).show();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(notificationID);

        } else {
            ShowNotification showNotification = new ShowNotification();
            showNotification.showNotify(context);
            Toast.makeText(context, NOT_CONNECTED, Toast.LENGTH_SHORT).show();
        }
    }
}
