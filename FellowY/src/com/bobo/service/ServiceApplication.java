package com.bobo.service;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

public class ServiceApplication extends Application {

	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
        IntentFilter filter=new IntentFilter();
        TimeTickReceiver tickReceiver = new TimeTickReceiver();
        filter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(tickReceiver,filter);
		super.onCreate();
	}

}
