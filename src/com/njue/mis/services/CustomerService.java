package com.njue.mis.services;

import java.util.List;
import java.util.Vector;

import com.njue.mis.dao.CustomerDAO;
import com.njue.mis.model.Customer;
import com.njue.mis.model.SetupCustomer;

public class CustomerService {

	private CustomerDAO customerDao = new CustomerDAO();
	public String addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.addCustomer(customer);
	}

	public boolean deleteCustomer(String id) {
		// TODO Auto-generated method stub
		return customerDao.deleteCustomer(id);
	}

	public Vector<Customer> searchCustomer(String field, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.updateCustomer(customer);
	}

	public Vector<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerDao.getAllCustomer();
	}

	public boolean isExisted(String id) {
		// TODO Auto-generated method stub
		return customerDao.isExisted(id);
	}

	public Customer getCustomerInfo(String id) {
		// TODO Auto-generated method stub
		return customerDao.getCustomer(id);
	}
	
	public Vector<Customer> getAllCustomerByCategory(int cateId){
		return customerDao.getAllCustomerByCategory(cateId);
	}

	public Vector<Customer> searchCustomerByName(String name) {
		// TODO Auto-generated method stub
		return customerDao.searchCustomerByName(name);
	}

	public boolean setUp(List<SetupCustomer> setupCustomers) {
		// TODO Auto-generated method stub
		return customerDao.setUp(setupCustomers);
	}

}
