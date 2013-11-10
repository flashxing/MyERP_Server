package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import com.njue.mis.interfaces.SalesControllerInterface;
import com.njue.mis.model.Sales;
import com.njue.mis.model.SalesBack;
import com.njue.mis.model.SalesIn;
import com.njue.mis.services.SalesService;

public class SalesController extends UnicastRemoteObject implements
		SalesControllerInterface {
	private SalesService salesService;
	public SalesController() throws RemoteException {
		super();
		salesService = new SalesService();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String addSalesBack(SalesBack sales) throws RemoteException{
		return salesService.addSalesBack(sales);
	}

	@Override
	public String addSalesIn(SalesIn sales) throws Exception {
		// TODO Auto-generated method stub
		return salesService.addSalesIn(sales);
	}

	@Override
	public Sales getSalesIn(String id) throws Exception {
		// TODO Auto-generated method stub
		return salesService.getSalesIn(id);
	}

	@Override
	public Sales getSalesBack(String id) throws Exception {
		// TODO Auto-generated method stub
		return salesService.getSalesBack(id);
	}

	@Override
	public Vector<Sales> getAllSalesIn() throws Exception {
		// TODO Auto-generated method stub
		return salesService.getAllSalesIn();
	}

	@Override
	public Vector<Sales> getAllSalesBack() throws Exception {
		// TODO Auto-generated method stub
		return salesService.getAllSalesBack();
	}

	@Override
	public Vector<Sales> getAllSalesInByTime(String begin, String end)
			throws Exception {
		// TODO Auto-generated method stub
		return salesService.getAllSalesInByTime(begin,end);
	}

	@Override
	public Vector<Sales> getAllSalesBackByTime(String begin, String end)
			throws Exception {
		// TODO Auto-generated method stub
		return salesService.getAllSalesBackByTime(begin, end);
	}

}
