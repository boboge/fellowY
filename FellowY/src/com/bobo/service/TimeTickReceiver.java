package com.bobo.service;

import java.util.Date;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;

public class TimeTickReceiver extends BroadcastReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(TimeTickReceiver.class); 

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) { 
			  
			boolean isServiceRunning = false; 
			ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			for (RunningServiceInfo service :manager.getRunningServices(Integer.MAX_VALUE)) { 
			if("com.bobo.service.MainService".equals(service.service.getClassName())){ 
			    	logger.debug(new Date()+"service存在");
					isServiceRunning = true;
				}
			 } 
			if (!isServiceRunning) { 
		    	logger.debug(new Date()+"service不存在重新开启");
			Intent i = new Intent(context, MainService.class); 
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			       context.startService(i); 
			} 
			  
			} 

	}

}
