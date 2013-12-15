package com.njue.mis.server;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.njue.mis.common.CommonUtil;


public class Configure {
	private static Properties propertie;
	private static InputStream inputFile;
	public static String CategoryController =null;
	public static String OperatorController =null;
	public static String GoodsController =null;
	public static String StoreHouseController =null;
	public static String PortInController =null;
	public static String CustomerController =null;
	public static String DiscountController =null;
	public static String SalesController =null;
	public static String RoleController =null;
	public static String UserController =null;
	public static String UserRoleController =null;
	public static String StockObjectController =null;
	public static String StockController =null;
	public static String ReceiptController =null;
	public static String ReceiptItemController = null;
	public static String CustomerMoneyController = null;
	public static String GoodsItemController = null;
	public static String MoneyItemController = null;
	public static String MoneyController = null;
//	public final static String IP = "localhost:3333";
	public final static int init(){
		propertie = new Properties();
		try{
			inputFile = Configure.class.getClassLoader().getResourceAsStream("erp.property");
			propertie.load(inputFile);
			ReceiptController = "rmi://"+getValue("IP")+"/ReceiptController";
			StockController = "rmi://"+getValue("IP")+"/StockController";
			StockObjectController = "rmi://"+getValue("IP")+"/StockObjectController";
			UserController = "rmi://"+getValue("IP")+"/UserController";
			UserRoleController = "rmi://"+getValue("IP")+"/UserRoleController";
			RoleController = "rmi://"+getValue("IP")+"/RoleController";
			SalesController = "rmi://"+getValue("IP")+"/SalesController";
			DiscountController = "rmi://"+getValue("IP")+"/DiscountController";
			CustomerController = "rmi://"+getValue("IP")+"/CustomerController";
			PortInController = "rmi://"+getValue("IP")+"/PortInController";
			StoreHouseController = "rmi://"+getValue("IP")+"/StoreHouseController";
			GoodsController= "rmi://"+getValue("IP")+"/GoodsController";
			OperatorController = "rmi://"+getValue("IP")+"/OperatorController";
			CategoryController = "rmi://"+getValue("IP")+"/CategoryController";
			ReceiptItemController = "rmi://"+getValue("IP")+"/ReceiptItemController";
			CustomerMoneyController = "rmi://"+getValue("IP")+"/CustomerMoneyController";
			GoodsItemController = "rmi://"+getValue("IP")+"/GoodsItemController";
			MoneyItemController = "rmi://"+getValue("IP")+"/MoneyItemController";
			MoneyController = "rmi://"+getValue("IP")+"/MoneyController";
			inputFile.close();
			return 1;
		}catch(Exception ex){
			ex.printStackTrace();
			Server.logger.warn("Fail to get the configure file");
			return 0;
		}
	}
	public static String getValue(String key){
		if(propertie.containsKey(key)){
			String value = propertie.getProperty(key);
			return value;
		}else
			return "";
	}
//	public final static String IP = "192.168.1.108:3333";
//	public final static int PORT = 3333;

}
