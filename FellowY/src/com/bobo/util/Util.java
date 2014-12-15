package com.bobo.util;

import java.util.List;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class Util {
	
	//发送短信方法
	public static void sendSMS(Context context,String number,String message){
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        
        if(message.length() >= 70)
        {
            //短信字数大于70，自动分条
            List<String> ms = smsManager.divideMessage(message);
            
            for(String str : ms )
            {
                //短信发送
                smsManager.sendTextMessage(number, null, str, sentIntent, null);
            }
        }
        else
        {
            smsManager.sendTextMessage(number, null, message, sentIntent, null);
        }
        Toast.makeText(context, "发送成功！", Toast.LENGTH_SHORT).show();
	}

}
