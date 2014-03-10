package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Vector;

import com.njue.mis.interfaces.CustomerControllerInterface;
import com.njue.mis.model.Customer;
import com.njue.mis.services.CustomerService;

public class CustomerController extends UnicastRemoteObject implements CustomerControllerInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8427498236673649256L;
	private CustomerService customerService = new CustomerService();
	public CustomerController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerService.addCustomer(customer);
	}

	@Override
	public boolean deleteCustomer(String id) {
		// TODO Auto-generated method stub
		return customerService.deleteCustomer(id);
	}

	@Override
	public Vector<Customer> searchCustomer(String field, String value) {
		// TODO Auto-generated method stub
		return customerService.searchCustomer(field, value);
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerService.updateCustomer(customer);
	}

	@Override
	public Vector<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerService.getAllCustomer();
	}

	@Override
	public boolean isExisted(String id) {
		// TODO Auto-generated method stub
		return customerService.isExisted(id);
	}

	@Override
	public Customer getCustomerInfo(String id) {
		// TODO Auto-generated method stub
		return customerService.getCustomerInfo(id);
	}

	@Override
	public Vector<Customer> getAllCustomerByCateId(int cateId)
			throws RemoteException {
		// TODO Auto-generated method stub
		return customerService.getAllCustomerByCategory(cateId);
	}

	@Override
	public Vector<Customer> searchCustomerByName(String name)
			throws RemoteException {
		// TODO Auto-generated method stub
		return customerService.searchCustomerByName(name);
	}

	@Override
	public List<Customer> getAllCustomersByCateId(List<Integer> cateIdList)
			throws RemoteException {
		// TODO Auto-generated method stub
		return customerService.getAllCustomerByCategory(cateIdList);
	}
}
