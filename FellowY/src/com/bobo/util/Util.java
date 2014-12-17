
package com.bobo.util;

import java.util.List;

import android.R.string;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.bobo.service.LocationApplication;

public class Util {
	
	//方法名
	public final static String RECEIVE_LOCATION = "receive_location";
	public final static String SEND_LOCTION = "send_loction";
	public final static	String ERROR_PASSWORD = "error_password";
	
	/**
	 * 获得地理位置方法
	 * @param context
	 * @param callBack
	 */
	public static void getMyLocation(Context context,final OnReceiveLocationCallBack callBack) {
		final LocationClient mLocationClient = new LocationClient(context);
			LocationClientOption option = new LocationClientOption();
			option.setOpenGps(true);
			option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
			option.setCoorType("bd09ll");//返回的定位结果是百度经纬度，默认值gcj02
			option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
			option.setIsNeedAddress(true);
			mLocationClient.setLocOption(option);
			mLocationClient.registerLocationListener(new BDLocationListener() {
				
				public void onReceiveLocation(BDLocation location) {
					mLocationClient.stop();
					String address;
					if (location.getLocType()!=61 && location.getLocType()!=66) {
						address = String.valueOf(location.getLocType());
					}else {
						address = location.getLatitude() + ":" + location.getLongitude();
					}
					callBack.myLocation(address);
				}
			});
			mLocationClient.start();
	}
	
	/**
	 * 发送短信方法
	 * @param context
	 * @param number
	 * @param message
	 */
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
