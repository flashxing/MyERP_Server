package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.StoreHouseControllerInterface;
import com.njue.mis.model.StoreHouse;
import com.njue.mis.services.StoreHouseService;

public class StoreHouseController  extends UnicastRemoteObject implements StoreHouseControllerInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4455317193073726846L;
	private StoreHouseService storeHouseService = new StoreHouseService();
	public StoreHouseController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int saveStoreHouse(StoreHouse storeHouse) throws RemoteException {
		// TODO Auto-generated method stub
		return storeHouseService.saveStoreHouse(storeHouse);
	}

	@Override
	public boolean deleteStoreHouse(StoreHouse storeHouse)
			throws RemoteException {
		// TODO Auto-generated method stub
		return storeHouseService.delStoreHouse(storeHouse);
	}

	@Override
	public boolean updateStoreHouse(StoreHouse storeHouse)
			throws RemoteException {
		// TODO Auto-generated method stub
		return storeHouseService.updateStoreHouse(storeHouse);
	}

	@Override
	public StoreHouse getStoreHouse(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return storeHouseService.getStoreHouse(id);
	}

	@Override
	public List<StoreHouse> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		return storeHouseService.getAll();
	}
	
}
