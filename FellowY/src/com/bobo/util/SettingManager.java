package com.bobo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SettingManager {

	final static String PRE_NAME="settings";
	final static String FELLOWYOU_PASSWORD="fellowyou_password";
	final static String FELLOWME_PASSWORD="fellowme_password";
	final static String PHONE_NUMBER="phone_number";
	final static boolean IS_BOOT = false;
	
	public static String getFellowYouPasswd(Context context){
		SharedPreferences preferences=context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
		return preferences.getString(FELLOWYOU_PASSWORD, "");
	}
	
	public static String getFellowMePasswd(Context context){
		SharedPreferences preferences=context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
		return preferences.getString(FELLOWME_PASSWORD, "");
	}
	
	public static String getPhoneNumber(Context context){
		SharedPreferences preferences=context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
		return preferences.getString(PHONE_NUMBER, "");
	}
	
	public static void setFellowYouPasswd(Context context,String newPasswd){
		SharedPreferences preferences=context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
		Editor editor=preferences.edit();
		editor.putString(FELLOWYOU_PASSWORD, newPasswd);
		editor.commit();
	}
	
	public static void setFellowMePasswd(Context context,String newPasswd){
		SharedPreferences preferences=context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
		Editor editor=preferences.edit();
		editor.putString(FELLOWME_PASSWORD, newPasswd);
		editor.commit();
	}
	
	public static void setPhoneNumber(Context context,String number){
		SharedPreferences preferences=context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
		Editor editor=preferences.edit();
		editor.putString(PHONE_NUMBER, number);
		editor.commit();
	}	

}
