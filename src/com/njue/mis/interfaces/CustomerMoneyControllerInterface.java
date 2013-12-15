package com.njue.mis.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.njue.mis.model.CustomerMoney;

public interface CustomerMoneyControllerInterface extends Remote{
	public String addCustomerMoney(CustomerMoney customerMoney) throws RemoteException;
	public CustomerMoney getCustomerMoney(String customerId) throws RemoteException;
	public List<CustomerMoney> getCustomerMoneys(String[] customerIds) throws RemoteException;
}
