package com.bobo.service;

import com.bobo.view.MainActivity;
import com.bobo.view.MapActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {
	static final String action_boot="android.intent.action.BOOT_COMPLETED";
	@Override
	public void onReceive(Context context, Intent intent) {
	      if (intent.getAction().equals(action_boot)){
	            Intent mainService=new Intent(context,MainService.class);
	            mainService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            context.startService(mainService);
	        }		
	}

}
