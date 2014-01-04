package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.SalesManControllerInterface;
import com.njue.mis.model.SalesMan;
import com.njue.mis.services.SalesManService;

public class SalesManController extends UnicastRemoteObject implements SalesManControllerInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = -526053466988627575L;
	private SalesManService salesManService;
	
	public SalesManController() throws RemoteException{
		salesManService = new SalesManService();
	}

	@Override
	public int addSalesMan(SalesMan salesMan) throws RemoteException {
		return salesManService.addSalesMan(salesMan);
	}

	@Override
	public boolean updateSalesMan(SalesMan salesMan) throws RemoteException {
		return salesManService.updateSalesMan(salesMan);
	}

	@Override
	public boolean deleteSalesMan(SalesMan salesMan) throws RemoteException {
		return salesManService.deleteSalesMan(salesMan);
	}

	@Override
	public SalesMan getSalesManById(int id) throws RemoteException {
		return salesManService.geSalesManById(id);
	}

	@Override
	public List<SalesMan> getAllSalesMans() throws RemoteException {
		return salesManService.getAllSalesMans();
	}

	@Override
	public List<SalesMan> getSalesMansByIds(List<Integer> idList)
			throws RemoteException {
		return salesManService.getSalesMansByIds(idList);
	}
}
