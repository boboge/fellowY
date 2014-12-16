package com.bobo.service;

import java.io.UnsupportedEncodingException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import com.bobo.view.MapActivity;

public class MainReceiver extends BroadcastReceiver {
	
	private String number;

	public void onReceive(final Context context, Intent intent) {
		Log.i("OUTPUT", "广播接收器触发");
		Bundle bun = intent.getExtras();
		// 开始分析短信
		if (bun != null) {
			Object[] mypdus = (Object[]) bun.get("pdus");
			SmsMessage[] messages = new SmsMessage[mypdus.length];
			for (int i = 0; i < mypdus.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) mypdus[i]);
			}
			for (SmsMessage mess : messages) {

				number = mess.getDisplayOriginatingAddress();
				String body = "";
				try {
					body = new String(mess.getDisplayMessageBody().getBytes(),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				Log.i("OUTPUT", "号码来源:" + number);
				Log.i("OUTPUT", "内容:" + body);
				if (number.contains("+86")) {
					number = number.substring(3);
				}
				Log.i("OUTPUT", "号码来源转换" + number);
				if (body.indexOf("sms_remote_control")!=-1) {
//					if (Order.isPasswdPass(context, body)) {
//						String orderType=Order.getOrderTypeFromMessage(body);
//						if (orderType.equals(Order.ORDER_LOCATION)) {
//							Util.getMyLocation(context, new OnReceiveLocationCallBack() {
//								public void myLocation(String address) {
//									Util.sendSMS(context, number, "xxxx"+"1111");
//								}
//							});
//						}
//					}else {
//						if (SettingManager.isReplyOnPasswdError(context)) {
//							Util.sendSMS(context, number, "xxxx"+"1111");
//						}
//					}
//					abortBroadcast();
				}
				
				if (body.indexOf("xxxx") != -1) {
					Intent newIntent = new Intent(context,MapActivity.class);	
					newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					newIntent.putExtra("x", 120.24846);
					newIntent.putExtra("y", 30.222425);
				    context.startActivity(newIntent); 
					abortBroadcast();
				}
			}
		}
		
	}
}
