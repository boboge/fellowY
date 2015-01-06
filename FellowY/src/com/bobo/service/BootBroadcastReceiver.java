package com.bobo.service;

import java.util.Date;

import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {
	private static final Logger logger = LoggerFactory.getLogger(BootBroadcastReceiver.class); 
	static final String action_boot="android.intent.action.BOOT_COMPLETED";
	@Override
	public void onReceive(Context context, Intent intent) {
	      if (intent.getAction().equals(action_boot)){
	            Intent mainService=new Intent(context,MainService.class);
	            logger.debug(new Date() + "¿ª»úÆô¶¯");
	            mainService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            context.startService(mainService);
	        }		
		
	}
}
