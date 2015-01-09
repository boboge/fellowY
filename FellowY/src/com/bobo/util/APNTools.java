package com.bobo.util;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class APNTools {
	
	public static final String CMWAP = "cmwap";   //�й��ƶ�cmwap   
	public static final String CMNET = "cmnet";   //�й��ƶ�cmnet   
	public static final String GWAP_3 = "3gwap";  //�й���ͨ3gwap  
	public static final String GNET_3 = "3gnet";  //�й���ͨ3gnet  
	public static final String UNIWAP = "uniwap"; //�й���ͨuni wap  
	public static final String UNINET = "uninet"; //�й���ͨuni net  
    private static final Uri ALL_APN_URI = Uri.parse("content://telephony/carriers");
    
	public static String matchAPN(String currentName){  
      if(currentName == null || currentName.equals("")){  
          return "";  
      }  
      currentName = currentName.toLowerCase();//���Сд��ĸ  
      if(currentName.startsWith(APNTools.CMNET)){  
          return APNTools.CMNET;  
      }else if(currentName.startsWith(APNTools.CMWAP)){  
          return APNTools.CMWAP;  
      }else if(currentName.startsWith(APNTools.GNET_3)){  
          return APNTools.GNET_3;  
      }else if(currentName.startsWith(APNTools.GWAP_3)){  
          return APNTools.GWAP_3;  
      }else if(currentName.startsWith(APNTools.UNINET)){  
          return APNTools.UNINET;  
      }else if(currentName.startsWith(APNTools.UNIWAP)){  
          return APNTools.UNIWAP;  
      }else if(currentName.startsWith("default")){  
          return "default";  
      }else{  
          return "";  
      }  
        
  }
	
	
	  
	/** 
	 * �ر��ƶ�����,ͨ������һ�������APN���� 
	 * @param context 
	 */  
	public static void closeApn(Context context){  
	    ArrayList<APNInfos> apnList = new ArrayList<APNInfos>();  
	    apnList = getAPNList(context);  
	    for(APNInfos apnInfos : apnList){  
	    }  
	    for(APNInfos apnInfos : apnList){  
	        ContentValues values = new ContentValues();   
	        values.put("apn",APNTools.matchAPN(apnInfos.getApn()) + "mdev");  
	        values.put("type",APNTools.matchAPN(apnInfos.getType()) + "mdev");  
	        context.getContentResolver().update(APNTools.ALL_APN_URI, values, "_id=?", new String[]{apnInfos.getId()});  
	    }  
	} 
	
    /** 
     * ��ȡAPN�б� 
     * @param context 
     * @return 
     */  
	public static ArrayList<APNInfos> getAPNList(Context context){  
	ArrayList<APNInfos> apnList = new ArrayList<APNInfos>();  
	String[] projection = {"_id,apn,type,current"};   
	Cursor cr = context.getContentResolver().query(APNTools.ALL_APN_URI, projection, null, null, null);  
	while(cr !=null && cr.moveToNext()){   
	  if(cr.getString(cr.getColumnIndex("apn")).equals("")){  
	        
	  }else{  
	      APNInfos apnInfos = new APNInfos();  
	      apnInfos.setId(cr.getString(cr.getColumnIndex("_id")));  
	      apnInfos.setApn(cr.getString(cr.getColumnIndex("apn")));  
	      apnInfos.setType(cr.getString(cr.getColumnIndex("type")));  
	      apnList.add(apnInfos);  
	  }  
	}  
	if(cr != null){  
	  cr.close();  
	}  
	return apnList;  	
	}
	
	
    /** 
	* ���ƶ����磬ͨ������һ����ȷ��APN���� 
	* @param context 
	*/  
	public static void openApn(Context context){  
	 ArrayList<APNInfos> apnList = new ArrayList<APNInfos>();  
	 apnList = getAPNList(context);  
	 for(APNInfos apnInfos : apnList){  
	 }  
	 for(APNInfos apnInfos : apnList){  
	     ContentValues values = new ContentValues();   
	     values.put("apn",APNTools.matchAPN(apnInfos.getApn()));  
	     values.put("type",APNTools.matchAPN(apnInfos.getType()));  
	     context.getContentResolver().update(APNTools.ALL_APN_URI, values, "_id=?", new String[]{apnInfos.getId()});  
	 }  
	} 

}
