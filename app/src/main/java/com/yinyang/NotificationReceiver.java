package com.yinyang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String messageRecu = intent.getStringExtra("message");
        Toast.makeText(context,messageRecu, Toast.LENGTH_LONG).show();

    }
}
