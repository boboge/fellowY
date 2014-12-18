package com.bobo.util;

public class MessageOrderManager {
	
	public static String toStringOrder(MessageEntity messageEntity) {
		return messageEntity.getFunction() + ";" + messageEntity.getPassword() + ";" + messageEntity.getLocation();
	}
	
	public static  String[] analyzeStringOrder(String orderString) {
		return orderString.split(";");
	}
	
	public static MessageEntity StringToObject(String orderString) {
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setFunction(analyzeStringOrder(orderString)[0]);
		messageEntity.setPassword(analyzeStringOrder(orderString)[1]);
		messageEntity.setLocation(analyzeStringOrder(orderString)[2]);
		return messageEntity;
	}
	
	public static  String[] analyzeLocationString(String location) {
		return  location.split(":");
	}
	
	public static void main(String[] args) {
		String aa = "111;;";
//		MessageEntity messageEntity = MessageOrderManager.StringToObject(aa);
//		messageEntity.getLocation();
//
//		System.out.println(messageEntity.getLocation());
		System.out.println(analyzeStringOrder(aa).length);
	}
}
