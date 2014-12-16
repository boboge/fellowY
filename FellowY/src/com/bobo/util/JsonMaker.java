package com.bobo.util;

import com.alibaba.fastjson.JSON;

import android.content.Context;

public class JsonMaker {

	//发送地理位置请求
	public static String sendLocationOrder(Context context,String location) {
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setFunction(Util.SEND_LOCTION);
		messageEntity.setLocation(location);
		messageEntity.setPassword(SettingManager.getFellowMePasswd(context));
		return JSON.toJSONString(messageEntity);
	}
	
	//接受地理位置请求
	public static MessageEntity  receiveLoctionOrder(Context context,String json) {
		return JSON.parseObject(json, MessageEntity.class);
	}

}
