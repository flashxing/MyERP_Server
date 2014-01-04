package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Vector;

import com.njue.mis.interfaces.StockControllerInterface;
import com.njue.mis.model.Stock;
import com.njue.mis.services.StockService;

public class StockController extends UnicastRemoteObject implements StockControllerInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4136992892354977490L;
	private StockService stockService = new StockService();
	public StockController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addStock(Stock stock) throws RemoteException {
		// TODO Auto-generated method stub
		return stockService.addStock(stock);
	}

	@Override
	public boolean deleteStock(Stock stock) throws RemoteException {
		// TODO Auto-generated method stub
		return stockService.deleteStock(stock);
	}

	@Override
	public boolean updateStock(Stock stock) throws RemoteException {
		// TODO Auto-generated method stub
		return stockService.updateStock(stock);
	}

	@Override
	public Vector<Stock> getAllStock() throws RemoteException {
		// TODO Auto-generated method stub
		return stockService.getAllStock();
	}

	@Override
	public int getStockId(Stock stock) throws RemoteException {
		// TODO Auto-generated method stub
		return stockService.getStockId(stock);
	}

	@Override
	public Stock getStock(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return stockService.getStock(id);
	}

	@Override
	public Stock getStock(int shId, String goodsId) throws RemoteException {
		// TODO Auto-generated method stub
		return stockService.getStock(shId,goodsId);
	}

	@Override
	public List<Stock> searchStocksByTime(String[] goodsIds, String time)
			throws RemoteException {
		// TODO Auto-generated method stub
		return stockService.getLastStocks(goodsIds, time);
	}

}
