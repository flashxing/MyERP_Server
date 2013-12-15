package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.MoneyItemControllerInterface;
import com.njue.mis.model.MoneyItem;
import com.njue.mis.services.MoneyItemService;

public class MoneyItemController extends UnicastRemoteObject implements MoneyItemControllerInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1854895243546542655L;
	private MoneyItemService moneyItemService;
	public MoneyItemController() throws RemoteException {
		super();
		moneyItemService = new MoneyItemService();
	}
	@Override
	public String addMoneyItem(MoneyItem moneyItem) throws RemoteException {
		return moneyItemService.addMoneyItem(moneyItem);
	}
	@Override
	public List<MoneyItem> getAll() throws RemoteException {
		return moneyItemService.getAll();
	}
	

}
