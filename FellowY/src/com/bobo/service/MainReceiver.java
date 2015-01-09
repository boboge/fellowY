package com.bobo.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.bobo.util.MessageEntity;
import com.bobo.util.MessageOrderManager;
import com.bobo.util.OnReceiveLocationCallBack;
import com.bobo.util.SettingManager;
import com.bobo.util.Util;
import com.bobo.view.MapActivity;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;

public class MainReceiver extends BroadcastReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(MainReceiver.class); 
	
	private String number;

	public void onReceive(final Context context, Intent intent) {
		Log.i("OUTPUT", "�㲥����������");
		Bundle bun = intent.getExtras();
		// ��ʼ��������
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
				MessageEntity receiveMessageEntity = MessageOrderManager.StringToObject(body);
				//�ж��ǲ���fellowY��
				if (receiveMessageEntity == null) {
					return;
				}
				Log.i("OUTPUT", "������Դ:" + number);
				Log.i("OUTPUT", "����:" + body);
				if (number.contains("+86")) {
					number = number.substring(3);
				}
				Log.i("OUTPUT", "������Դת��" + number);
				
				//���󷽷��ǣ�����fellwme λ��
				if (receiveMessageEntity.getFunction().equals(Util.SEND_LOCTION)) {
					if (receiveMessageEntity.getPassword().equals(SettingManager.getFellowMePasswd(context))) {
						Util.getMyLocation(context, new OnReceiveLocationCallBack() {
							@Override
							public void myLocation(String address) {
								MessageEntity messageEntity = new MessageEntity();
								messageEntity.setFunction(Util.RECEIVE_LOCATION);
								messageEntity.setPassword(SettingManager.getFellowMePasswd(context));
								messageEntity.setLocation(address);
								Util.sendSMS(context, number, MessageOrderManager.toStringOrder(messageEntity));
								logger.debug(new Date() + "____" + Util.SEND_LOCTION);
							}

							@Override
							public void myAddress(String address) {
								
							}
						});
					}else {
						MessageEntity messageEntity = new MessageEntity();
						messageEntity.setFunction(Util.ERROR_PASSWORD);
						Util.sendSMS(context, number, MessageOrderManager.toStringOrder(messageEntity));
					}
					abortBroadcast();
				}
				
				
				//���󷽷��ǣ�����fellwme ��ַ
				if (receiveMessageEntity.getFunction().equals(Util.SEND_ADDRESS)) {
					if (receiveMessageEntity.getPassword().equals(SettingManager.getFellowMePasswd(context))) {
						Util.getMyAddress(context, new OnReceiveLocationCallBack() {
							@Override
							public void myLocation(String address) {
							}

							@Override
							public void myAddress(String address) {
								MessageEntity messageEntity = new MessageEntity();
								messageEntity.setFunction(Util.RECEIVE_ADDRESS);
								messageEntity.setPassword(SettingManager.getFellowMePasswd(context));
								messageEntity.setLocation(address);
								Util.sendSMS(context, number, MessageOrderManager.toStringOrder(messageEntity));
							}
						});
					}else {
						MessageEntity messageEntity = new MessageEntity();
						messageEntity.setFunction(Util.ERROR_PASSWORD);
						Util.sendSMS(context, number, MessageOrderManager.toStringOrder(messageEntity));
					}
					abortBroadcast();
				}
				
				//���󷽷��ǣ���ȡfellwme ��ַ
				if ((receiveMessageEntity.getFunction().equals(Util.RECEIVE_ADDRESS))) {
					if (receiveMessageEntity.getLocation().equals("")) {
						Toast.makeText(context, "��ȡfellowMeλ��ʧ�ܴ������: " + receiveMessageEntity.getLocation(), Toast.LENGTH_SHORT).show();
					}else {
						Toast.makeText(context, "��ȡfellowMe��ַ: " + receiveMessageEntity.getLocation(), Toast.LENGTH_SHORT).show();
					}
				abortBroadcast();
			}				
				
				
				//���󷽷������fellowme λ�� 
				if ((receiveMessageEntity.getFunction().equals(Util.RECEIVE_LOCATION))) {
						Intent newIntent = new Intent(context,MapActivity.class);	
						if (receiveMessageEntity.getLocation().equals("") || !receiveMessageEntity.getLocation().contains(":")) {
							Toast.makeText(context, "��ȡfellowMeλ��ʧ�ܴ������: " + receiveMessageEntity.getLocation(), Toast.LENGTH_SHORT).show();
							logger.debug(new Date() + "____" + Util.RECEIVE_LOCATION+"ʧ��");

						}else {
							logger.debug(new Date() + "____" + Util.RECEIVE_LOCATION+"�ɹ�");
							newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							newIntent.putExtra("x", Double.parseDouble(MessageOrderManager.analyzeLocationString(receiveMessageEntity.getLocation())[1]));
							newIntent.putExtra("y",Double.parseDouble(MessageOrderManager.analyzeLocationString(receiveMessageEntity.getLocation())[0]));
						    context.startActivity(newIntent); 
						}
					abortBroadcast();
				}
				
				if ((receiveMessageEntity.getFunction().equals(Util.ERROR_PASSWORD))) {
					Toast.makeText(context, "������󣡣�", Toast.LENGTH_SHORT).show();
					abortBroadcast();
				}
				
				
			}
		}
	}
}
