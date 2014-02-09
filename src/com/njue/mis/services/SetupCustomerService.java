package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.SetupCustomerDao;
import com.njue.mis.model.SetupCustomer;

public class SetupCustomerService {
	private SetupCustomerDao setupCustomerDao;
	public SetupCustomerService(){
		setupCustomerDao = new SetupCustomerDao();
	}
	
	public boolean update(List<SetupCustomer> setupCustomerList){
		return setupCustomerDao.update(setupCustomerList);
	}
	
	public List<SetupCustomer> getAll(List<String> customerIdList){
		return setupCustomerDao.getAll(customerIdList);
	}

	public List<SetupCustomer> getAll() {
		// TODO Auto-generated method stub
		return setupCustomerDao.getAll();
	}
}
