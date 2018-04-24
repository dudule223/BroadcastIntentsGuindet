package org.diiage.guindet.broadcastintents;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GlobalBroadcastReceiver globalBroadcastReceiver = new GlobalBroadcastReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        this.registerReceiver(globalBroadcastReceiver, filter);
    }
}
