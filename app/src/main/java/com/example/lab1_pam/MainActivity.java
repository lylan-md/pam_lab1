package com.example.lab1_pam;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Button bEx1;
    public Button bEx2;
    public EditText etEx2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bEx1 = findViewById(R.id.button_ex1);
        bEx2 = findViewById(R.id.button_search);
        etEx2 = findViewById(R.id.text_box_ex1);

        bEx1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                NotificationChannel channel = new NotificationChannel("CHANNEL_ID_1", "Ex1Channel", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                nm.createNotificationChannel(channel);
                Notification notify = new Notification.Builder(getApplicationContext())
                        .setContentTitle("Some title Ex1")
                        .setContentText("Some content Ex1")
                        .setSmallIcon(R.drawable.notif_icon)
                        .setAutoCancel(true)
                        .setChannelId("CHANNEL_ID_1")
                        .build();

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        nm.notify(1, notify);
                    }
                }, 10000);
            }
        });

        bEx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, etEx2.getText().toString());
                startActivity(intent);
            }
        });
    }
}