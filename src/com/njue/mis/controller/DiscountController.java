package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.njue.mis.interfaces.DiscountControllerInterface;
import com.njue.mis.model.Discount;
import com.njue.mis.services.DiscountService;

public class DiscountController extends UnicastRemoteObject implements DiscountControllerInterface{
	
	private DiscountService discountService = new DiscountService();
	public DiscountController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String addDiscount(Discount discount) throws RemoteException {
		// TODO Auto-generated method stub
		return discountService.add(discount);
	}

	@Override
	public boolean deleteDiscount(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return discountService.delete(id);
	}

	@Override
	public boolean updateDiscount(Discount discount) throws RemoteException {
		// TODO Auto-generated method stub
		return discountService.update(discount);
	}

	@Override
	public Discount getDiscount(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return discountService.get(id);
	}

}
