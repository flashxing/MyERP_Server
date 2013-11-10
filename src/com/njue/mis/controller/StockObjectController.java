package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import com.njue.mis.interfaces.StockObjectControllerInterface;
import com.njue.mis.model.Gift;
import com.njue.mis.model.GiftIn;
import com.njue.mis.model.GiftOut;
import com.njue.mis.model.InCome;
import com.njue.mis.model.OutCome;
import com.njue.mis.model.StockInObject;
import com.njue.mis.model.StockOutObject;
import com.njue.mis.services.StockObjectService;

public class StockObjectController extends UnicastRemoteObject implements
		StockObjectControllerInterface {
	private StockObjectService stockObjectService;
	public StockObjectController() throws RemoteException {
		super();
		stockObjectService = new StockObjectService();
	}

	@Override
	public GiftIn getGiftIn(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.getGiftIn(id);
	}

	@Override
	public GiftOut getGiftOut(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.getGiftOut(id);
	}

	@Override
	public Vector<GiftIn> getAllGiftIn() throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.getAllGiftIn();
	}

	@Override
	public Vector<GiftOut> getAllGiftOut() throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.getAllGiftOut();
	}

	@Override
	public String addStockIn(StockInObject stockIn) throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.addStockInObject(stockIn);
	}

	@Override
	public String addStockOut(StockOutObject stockOut) throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.addStockOutObject(stockOut);
	}

	@Override
	public boolean deleteStockObject(StockOutObject stockObject)
			throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.deleteStockObject(stockObject);
	}

	@Override
	public boolean updateStockObject(StockOutObject stockObject)
			throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.updateStockObject(stockObject);
	}

	@Override
	public InCome getInCome(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.getInCome(id);
	}

	@Override
	public OutCome getOutCome(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.getOutCome(id);
	}

	@Override
	public Vector<InCome> getAllInCome() throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.getAllInCome();
	}

	@Override
	public Vector<OutCome> getAllOutCome() throws RemoteException {
		// TODO Auto-generated method stub
		return stockObjectService.getAllOutCome();
	}

}
