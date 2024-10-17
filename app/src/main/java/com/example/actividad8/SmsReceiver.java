package com.example.actividad8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.actividad8.MainActivity;

import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = smsMessage.getDisplayOriginatingAddress();
                    String messageBody = smsMessage.getMessageBody();

                    // Obtener la lista de números
                    MainActivity mainActivity = new MainActivity();
                    ArrayList<String> phoneNumbers = mainActivity.getPhoneNumbers();

                    // Verifica si el número está en la lista
                    if (phoneNumbers.contains(sender)) {
                        Toast.makeText(context, "Nuevo mensaje de: " + sender, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
