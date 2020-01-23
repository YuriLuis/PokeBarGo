package com.example.pokebargo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.os.Bundle;

public class SmsActivity<notificationId> extends AppCompatActivity {

    private static final String CHANNEL_ID =  "7H1460";
    private int notificationId = 666;

    NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        NotifiManager notifiManager = new NotifiManager(this);

        notifiManager.enviarNotificacao(777,"Andre","TIM MAIA");

    }
}
