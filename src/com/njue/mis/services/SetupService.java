package com.njue.mis.services;

import java.util.List;

import com.njue.mis.model.SetupCustomer;
import com.njue.mis.model.SetupGoods;

public class SetupService {
	private SetupGoodsService setupGoodsService;
	private SetupCustomerService setupCustomerService;
	private GoodsService goodsService;
	private CustomerService customerService;
	private StockService stockService;
	
	public SetupService(){
		setupCustomerService = new SetupCustomerService();
		setupGoodsService = new SetupGoodsService();
		goodsService = new GoodsService();
		customerService = new CustomerService();
		stockService = new StockService();
	}
	public boolean setUp(){
		List<SetupCustomer> setupCustomers = setupCustomerService.getAll();
		List<SetupGoods> setupGoods = setupGoodsService.getAll();
		return goodsService.setUp(setupGoods) && stockService.setUp(setupGoods) && customerService.setUp(setupCustomers);
	}
	public SetupGoodsService getSetupGoodsService() {
		return setupGoodsService;
	}
	public void setSetupGoodsService(SetupGoodsService setupGoodsService) {
		this.setupGoodsService = setupGoodsService;
	}
	public SetupCustomerService getSetupCustomerService() {
		return setupCustomerService;
	}
	public void setSetupCustomerService(SetupCustomerService setupCustomerService) {
		this.setupCustomerService = setupCustomerService;
	}
	
}
