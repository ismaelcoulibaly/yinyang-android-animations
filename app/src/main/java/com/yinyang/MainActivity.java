package com.yinyang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button animateBtn, stopBtn;
    private ImageView yinyang;
    private ImageButton notifications_btn;
    public static final String CHANNEL_ID = "CHANNELYINYANG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animateBtn = findViewById(R.id.animate_btn);
        stopBtn = findViewById(R.id.stop_btn);
        notifications_btn = findViewById(R.id.notifications_btn);
        yinyang = findViewById(R.id.yinyang);

        createNotificationChannel();
        animateBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        notifications_btn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.animate_btn:
                RotateAnimation rotateAnimation =new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF,
                        .5f, RotateAnimation.RELATIVE_TO_SELF
                        ,.5f);
                rotateAnimation.setDuration(500);
                rotateAnimation.setRepeatCount(Animation.INFINITE);
                yinyang.startAnimation(rotateAnimation);

                break;
            case R.id.stop_btn:
                yinyang.clearAnimation();
                break;

            case R.id.notifications_btn:

                Intent intent = new Intent(this, MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = getActivity(this, 0 ,intent, 0);//creation pending intent

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("ombre et lumière")
                        .setContentText("yinyang")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)//on set le content intent
                        .setAutoCancel(true);//autocancel

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(1, builder.build());
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "CHANNELYINYANG", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Bienvenue dans le CHANNELYINYANG");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }
}