package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.CustomerMoneyDao;
import com.njue.mis.model.CustomerMoney;

public class CustomerMoneyService {
	private CustomerMoneyDao customerMoneyDao;
	public CustomerMoneyService(){
		customerMoneyDao = new CustomerMoneyDao();
	}
	public String add(CustomerMoney customerMoney){
		return customerMoneyDao.add(customerMoney);
	}
	
	public boolean delete(CustomerMoney customerMoney){
		return customerMoneyDao.delete(customerMoney);
	}
	
	public CustomerMoney get(String customerId){
		return customerMoneyDao.get(customerId);
	}
	
	public List<CustomerMoney> get(String[] customerIds){
		return customerMoneyDao.get(customerIds);
	}
}	
