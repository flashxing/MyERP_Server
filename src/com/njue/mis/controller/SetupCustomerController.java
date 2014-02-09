package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.SetupCustomerControllerInterface;
import com.njue.mis.model.SetupCustomer;
import com.njue.mis.services.SetupCustomerService;

public class SetupCustomerController extends UnicastRemoteObject implements SetupCustomerControllerInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8728779080555157582L;
	private SetupCustomerService setupCustomerService;
	public SetupCustomerController() throws RemoteException {
		super();
		setupCustomerService = new SetupCustomerService();
	}
	@Override
	public boolean saveOrUpdateSetupCustomerList(
			List<SetupCustomer> setupCustomerList) throws RemoteException {
		// TODO Auto-generated method stub
		return setupCustomerService.update(setupCustomerList);
	}
	@Override
	public List<SetupCustomer> getAllSetupCustomer(List<String> customerIdList)
			throws RemoteException {
		// TODO Auto-generated method stub
		return setupCustomerService.getAll(customerIdList);
	}
	
	
}
