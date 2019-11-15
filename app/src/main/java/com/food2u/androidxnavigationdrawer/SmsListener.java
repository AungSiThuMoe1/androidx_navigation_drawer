package com.food2u.androidxnavigationdrawer;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsListener extends BroadcastReceiver {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("messageBody", intent.getAction());
        System.out.println("SOE" + intent.getAction() + " " + Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            try {
                System.out.println("body" + intent.getAction() + " " + Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
                String messageBody = "";
                String messageNumber = "";
                for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    messageBody += smsMessage.getMessageBody();
                    messageNumber = smsMessage.getDisplayOriginatingAddress();

                }

                Bundle bundle = intent.getExtras();
                Object[] data = (Object[]) bundle.get("pdus");
                for (Object pdu : data) {
                    //Log.d(tag, "legacy SMS implementation (before KitKat)");
                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
                    if (message == null) {
                        // Log.e(tag, "SMS message is null -- ABORT");
                        break;
                    }
                    messageBody = message.getDisplayMessageBody();
                    messageNumber = message.getDisplayOriginatingAddress();
                }
                Intent messageReceived = new Intent("otp");
                messageReceived.putExtra("smsnumber", messageNumber);
                messageReceived.putExtra("sms", messageBody);
                context.sendBroadcast(messageReceived);// when receiving it somewhere in your app, subString the additional text and leave only the code, then place it in the editText and do your verification
                System.out.println("Send Message" + messageBody);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("" + ex.getMessage() + "ERROR");
            }

        }
    }
}
