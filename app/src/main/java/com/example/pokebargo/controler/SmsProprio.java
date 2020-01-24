package com.example.pokebargo.controler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.pokebargo.NotifiManager;


public class SmsProprio extends BroadcastReceiver {


    private String TAG = "Sms";

    @Override
        public void onReceive(Context context, Intent intent) {


            if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
                Log.d(TAG, "Sms: ");
                SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                for (SmsMessage message : smsMessages) {
                    Log.d(TAG, "Recebido: ");
                    NotifiManager notifiManager = new NotifiManager(context);

                    notifiManager.enviarNotificacao(26860, "Andre quer uma Cadeira Gamer", message.getDisplayMessageBody());
                }
            }
        }
}


