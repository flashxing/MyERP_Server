package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.MoneyControllerInterface;
import com.njue.mis.model.Money;
import com.njue.mis.services.MoneyService;

public class MoneyController extends UnicastRemoteObject implements MoneyControllerInterface{
	private MoneyService moneyService;
	public MoneyController() throws RemoteException {
		super();
		moneyService = new MoneyService();
	}

	@Override
	public String addMoney(Money money) throws RemoteException {
		// TODO Auto-generated method stub
		return moneyService.addMoney(money);
	}

	@Override
	public boolean deleteMoney(Money money) throws RemoteException {
		// TODO Auto-generated method stub
		return moneyService.deleteMoney(money);
	}

	@Override
	public boolean updateMoney(Money money) throws RemoteException {
		// TODO Auto-generated method stub
		return moneyService.updateMoney(money);
	}

	@Override
	public Money getMoney(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return moneyService.getMoney(id);
	}

	@Override
	public List<Money> getMoneysByTime(String begin, String end)
			throws RemoteException {
		return moneyService.getMoneysByTime(begin, end);
	}

}
