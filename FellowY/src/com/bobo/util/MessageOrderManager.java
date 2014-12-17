package com.bobo.util;

public class MessageOrderManager {
	
	public static String toStringOrder(MessageEntity messageEntity) {
		return messageEntity.getFunction() + ";" + messageEntity.getPassword() + ";" + messageEntity.getLocation() + ";";
	}
	
	public static  String[] analyzeStringOrder(String orderString) {
		return orderString.split(";");
	}
	
	public static MessageEntity StringToObject(String orderString) {
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setFunction(analyzeStringOrder(orderString)[0]);
		messageEntity.setLocation(analyzeStringOrder(orderString)[1]);
		messageEntity.setLocation(analyzeStringOrder(orderString)[2]);
		return messageEntity;
	}
	
	public static  String[] analyzeLocationString(String location) {
		return  location.split(":");
	}
	
	public static void main(String[] args) {
		String aa = "123123.11:123213";
		System.out.println(analyzeLocationString(aa)[1]);
	}
}
