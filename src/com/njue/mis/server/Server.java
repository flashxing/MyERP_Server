package com.njue.mis.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.njue.mis.controller.CategoryController;
import com.njue.mis.controller.CustomerController;
import com.njue.mis.controller.CustomerMoneyController;
import com.njue.mis.controller.DiscountController;
import com.njue.mis.controller.GoodsController;
import com.njue.mis.controller.GoodsItemController;
import com.njue.mis.controller.MoneyController;
import com.njue.mis.controller.MoneyItemController;
import com.njue.mis.controller.OperatorController;
import com.njue.mis.controller.PortController;
import com.njue.mis.controller.ReceiptController;
import com.njue.mis.controller.ReceiptItemController;
import com.njue.mis.controller.RoleController;
import com.njue.mis.controller.SalesController;
import com.njue.mis.controller.StockController;
import com.njue.mis.controller.StockObjectController;
import com.njue.mis.controller.StoreHouseController;
import com.njue.mis.controller.UserController;
import com.njue.mis.controller.UserRoleController;
import com.njue.mis.interfaces.CategoryControllerInterface;
import com.njue.mis.interfaces.CustomerControllerInterface;
import com.njue.mis.interfaces.CustomerMoneyControllerInterface;
import com.njue.mis.interfaces.DiscountControllerInterface;
import com.njue.mis.interfaces.GoodsControllerInterface;
import com.njue.mis.interfaces.GoodsItemControllerInterface;
import com.njue.mis.interfaces.OperatorControllerInterface;
import com.njue.mis.interfaces.PortControllerInterface;
import com.njue.mis.interfaces.ReceiptControllerInterface;
import com.njue.mis.interfaces.ReceiptItemControllerInterface;
import com.njue.mis.interfaces.RoleControllerInterface;
import com.njue.mis.interfaces.SalesControllerInterface;
import com.njue.mis.interfaces.StockControllerInterface;
import com.njue.mis.interfaces.StockObjectControllerInterface;
import com.njue.mis.interfaces.StoreHouseControllerInterface;
import com.njue.mis.interfaces.UserControllerInterface;
import com.njue.mis.interfaces.UserRoleControllerInterface;
import com.njue.mis.services.CategoryService;

public class Server {
	public static Logger logger = Logger.getLogger(CategoryService.class.getName());
	public static void main(String[] args)
	{
		// BasicConfigurator replaced with PropertyConfigurator.
		PropertyConfigurator.configure("src/log4j.property");
		logger.info("Starting application.");
		try {
			Configure.init();
			System.setProperty("java.rmi.server.hostname",Configure.getValue("OUTER"));
			LocateRegistry.createRegistry((Integer.parseInt(Configure.getValue("PORT"))));
			
			logger.info("binding portInservice");
			PortControllerInterface portInService = new PortController();
			Naming.bind(Configure.PortInController,portInService);
			portInService.getAllPortIn();
			logger.info("bing portInservice success");
			
			logger.info("binding categoryservice");
			CategoryControllerInterface categoryService = new CategoryController();
			Naming.bind(Configure.CategoryController, categoryService);
			logger.info("bind categoryservice success");
			
			logger.info("binding operatorservice");
			OperatorControllerInterface operatorService = new OperatorController();
			Naming.bind(Configure.OperatorController, operatorService);
			logger.info("bind operatorservice success");
			
			logger.info("binding goodsservice");
			GoodsControllerInterface goodsService = new GoodsController();
			Naming.bind(Configure.GoodsController, goodsService);
			logger.info("bing goodsservice success");
			
			logger.info("binding storeHouseservice");
			StoreHouseControllerInterface storeHouseService = new StoreHouseController();
			Naming.bind(Configure.StoreHouseController,storeHouseService);
			logger.info("bing storeHouseservice success");
			
			logger.info("binding customerservice");
			CustomerControllerInterface customerService = new CustomerController();
			Naming.bind(Configure.CustomerController,customerService);
			logger.info("bing Customerservice success");
			
			logger.info("binding discountservice");
			DiscountControllerInterface discountService = new DiscountController();
			Naming.bind(Configure.DiscountController, discountService);
			logger.info("binding discountservice success");
			
			logger.info("binding salesservice");
			SalesControllerInterface salesService = new SalesController();
			Naming.bind(Configure.SalesController, salesService);
			logger.info("binding salesservice success");
			
			logger.info("binding roleservice");
			RoleControllerInterface roleService = new RoleController();
			Naming.bind(Configure.RoleController, roleService);
			logger.info("binding roleservice success");
			
			logger.info("binding userservice");
			UserControllerInterface userService = new UserController();
			Naming.bind(Configure.UserController, userService);
			logger.info("binding userservice success");
			
			logger.info("binding userroleservice");
			UserRoleControllerInterface userRoleService = new UserRoleController();
			Naming.bind(Configure.UserRoleController, userRoleService);
			logger.info("binding userroleservice success");
			
			logger.info("binding stockObjectservice");
			StockObjectControllerInterface stockObjectService = new StockObjectController();
			Naming.bind(Configure.StockObjectController, stockObjectService);
			logger.info("binding stockobjectservice success");
			
			logger.info("binding stockservice");
			StockControllerInterface stockService = new StockController();
			Naming.bind(Configure.StockController, stockService);
			logger.info("binding stockservice success");
			
			logger.info("binding receiptservice");
			ReceiptControllerInterface receiptService = new ReceiptController();
			Naming.bind(Configure.ReceiptController, receiptService);
			logger.info("binding receiptservice success");
		
			ReceiptItemControllerInterface receiptItemService = new ReceiptItemController();
			Naming.bind(Configure.ReceiptItemController, receiptItemService);

			CustomerMoneyControllerInterface customerMoneyService = new CustomerMoneyController();
			Naming.bind(Configure.CustomerMoneyController, customerMoneyService);
			
			GoodsItemControllerInterface goodsItemService = new GoodsItemController();
			Naming.bind(Configure.GoodsItemController, goodsItemService);
			
			Naming.bind(Configure.MoneyItemController, new MoneyItemController());

			Naming.bind(Configure.MoneyController, new MoneyController());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
