package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.ReceiptItemControllerInterface;
import com.njue.mis.model.ReceiptItem;
import com.njue.mis.services.ReceiptItemService;

public class ReceiptItemController extends UnicastRemoteObject implements
		ReceiptItemControllerInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8591090665938002330L;
	private ReceiptItemService receiptItemService;
	public ReceiptItemController() throws RemoteException {
		super();
		receiptItemService = new ReceiptItemService();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String addReceiptItem(ReceiptItem receiptItem)
			throws RemoteException {
		// TODO Auto-generated method stub
		return receiptItemService.add(receiptItem);
	}

	@Override
	public boolean updateReceiptItem(ReceiptItem receiptItem)
			throws RemoteException {
		// TODO Auto-generated method stub
		return receiptItemService.update(receiptItem);
	}

	@Override
	public boolean deleteReceiptItem(ReceiptItem receiptItem)
			throws RemoteException {
		// TODO Auto-generated method stub
		return receiptItemService.delete(receiptItem);
	}

	@Override
	public ReceiptItem getReceiptItem(String item) throws RemoteException {
		// TODO Auto-generated method stub
		return receiptItemService.getReceiptItem(item);
	}

	@Override
	public List<ReceiptItem> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		return receiptItemService.getAll();
	}

}
