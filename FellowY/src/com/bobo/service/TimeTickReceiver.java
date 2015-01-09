package com.bobo.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeTickReceiver extends BroadcastReceiver {
	

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) { 
			  
			boolean isServiceRunning = false; 
			ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			for (RunningServiceInfo service :manager.getRunningServices(Integer.MAX_VALUE)) { 
			if("com.bobo.service.MainService".equals(service.service.getClassName())){ 
					isServiceRunning = true;
				}
			 } 
			if (!isServiceRunning) { 
		    	Intent i = new Intent(context, MainService.class); 
		    	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			    context.startService(i); 
			} 
			  
		} 

	}

}
