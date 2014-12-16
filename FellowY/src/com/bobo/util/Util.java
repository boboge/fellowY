
package com.bobo.util;

import java.util.List;

import android.R.string;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class Util {
	
	//������
	public final static String RECEIVE_LOCATION = "receive_location";
	public final static String SEND_LOCTION = "send_loction";
	
	//���Ͷ��ŷ���
	public static void sendSMS(Context context,String number,String message){
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        
        if(message.length() >= 70)
        {
            //������������70���Զ�����
            List<String> ms = smsManager.divideMessage(message);
            
            for(String str : ms )
            {
                //���ŷ���
                smsManager.sendTextMessage(number, null, str, sentIntent, null);
            }
        }
        else
        {
            smsManager.sendTextMessage(number, null, message, sentIntent, null);
        }
        Toast.makeText(context, "���ͳɹ���", Toast.LENGTH_SHORT).show();
	}

}
