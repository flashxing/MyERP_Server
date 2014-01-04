package com.njue.mis.common;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CommonUtil {
	private static SimpleDateFormat formate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat intFormat = new SimpleDateFormat("yyyyMMdd");
	public static String md5s(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			String result = buf.toString();
			return result;
		} catch (NoSuchAlgorithmException e) {
		  	// TODO Auto-generated catch block
		  	e.printStackTrace();
		  	return null;
		}
	}
	
	public static boolean updateObject(Object object, Object object1){
		if(object.getClass()!=object1.getClass()){
			return false;
		}
		Class<?> myClass = object.getClass();
		while(myClass!=Object.class){
			for(Field field:myClass.getDeclaredFields()){
				field.setAccessible(true);
				try {
					if(field.get(object1)!=null&&!"".equals(field.get(object1).toString())){
						field.set(object, field.get(object1));
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					//ignore it
					//e.printStackTrace();
				}
				System.out.println(field);
			}
			myClass = myClass.getSuperclass();
		}
		return true;
	}
	
	public static int convertDateToInt(String date){
		try {
			Date date2 = formate.parse(date);
			date = intFormat.format(date2);
			return Integer.parseInt(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
//	public static void main(String[] args){
//		CommonUtil util = new CommonUtil();
//		Goods goods1 = new Goods();
//		goods1.setId("a");
//		Goods goods2 = new Goods();
//		goods2.setId("b");
//		Customer c1 = new Customer();
//		c1.setCateId(5);
//		Customer c2 = new Customer();
//		c2.setCateId(4);
//		util.updateObject(goods1, goods2);
//		System.out.println(util.md5s("…Ã∆∑A"));
//		System.out.println(goods1.getId());
//		util.updateObject(c1, c2);
//		System.out.println(c1.getCateId());
//	}
}
