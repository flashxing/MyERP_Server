package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.CustomerMoneyControllerInterface;
import com.njue.mis.model.CustomerMoney;
import com.njue.mis.services.CustomerMoneyService;

public class CustomerMoneyController extends UnicastRemoteObject implements CustomerMoneyControllerInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 80653392550682619L;
	private CustomerMoneyService customerMoneyService;
	public CustomerMoneyController() throws RemoteException {
		super();
		customerMoneyService = new CustomerMoneyService();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String addCustomerMoney(CustomerMoney customerMoney)
			throws RemoteException {
		// TODO Auto-generated method stub
		return customerMoneyService.add(customerMoney);
	}

	@Override
	public CustomerMoney getCustomerMoney(String customerId)
			throws RemoteException {
		// TODO Auto-generated method stub
		return customerMoneyService.get(customerId);
	}

	@Override
	public List<CustomerMoney> getCustomerMoneys(String[] customerIds)
			throws RemoteException {
		// TODO Auto-generated method stub
		return customerMoneyService.get(customerIds);
	}

}
