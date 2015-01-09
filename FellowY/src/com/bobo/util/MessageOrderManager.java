package com.bobo.util;

public class MessageOrderManager {
	
	public static String toStringOrder(MessageEntity messageEntity) {
		return Util.APP_TAG+";"+ messageEntity.getFunction() + ";" + messageEntity.getPassword() + ";" + messageEntity.getLocation();
	}
	
	public static  String[] analyzeStringOrder(String orderString) {
		return orderString.split(";");
	}
	
	public static MessageEntity StringToObject(String orderString) {
		if (orderString.startsWith(Util.APP_TAG)) {
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setFunction(analyzeStringOrder(orderString)[1]);
		messageEntity.setPassword(analyzeStringOrder(orderString)[2]);
		messageEntity.setLocation(analyzeStringOrder(orderString)[3]);
		return messageEntity;
		}else {
			return null;
		}
	}
	
	public static  String[] analyzeLocationString(String location) {
		return  location.split(":");
	}
	
	public static void main(String[] args) {
		String aa = "111;dsad;asds;";
		MessageEntity messageEntity = MessageOrderManager.StringToObject(aa);
		messageEntity.getLocation();

		System.out.println(messageEntity.getLocation());
		System.out.println(analyzeStringOrder(aa).length);
	}
}
