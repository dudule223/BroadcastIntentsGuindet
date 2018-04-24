package org.diiage.guindet.broadcastintents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class GlobalBroadcastReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";
    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Test : " + intent.getAction(), Toast.LENGTH_SHORT).show();

        if(intent.getAction() == BATTERY_CHANGED)
        {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float battery = (level / (float)scale) * 100;

            if(battery < 20)
            {
                Toast toast = Toast.makeText(context, "Batterie : " + Math.round(battery) + "%", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if(intent.getAction() == SMS_RECEIVED)
        {
            Bundle bundle = intent.getExtras();
            if (bundle != null)
            {
                final Object[] tmpObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < tmpObj.length; i++)
                {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) tmpObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Toast.makeText(context,"Sender : "+ senderNum + ", msg : " + message, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
