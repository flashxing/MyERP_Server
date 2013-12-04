package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.ReceiptControllerInterface;
import com.njue.mis.model.Receipt;
import com.njue.mis.model.ReceiptIn;
import com.njue.mis.model.ReceiptOut;
import com.njue.mis.services.ReceiptService;

public class ReceiptController extends UnicastRemoteObject implements ReceiptControllerInterface{
	private ReceiptService receiptService;
	public ReceiptController() throws RemoteException {
		super();
		receiptService = new ReceiptService();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String addReceipt(Receipt receipt) throws RemoteException {
		// TODO Auto-generated method stub
		return receiptService.addReceipt(receipt);
	}

	@Override
	public boolean updateReceipt(Receipt receipt) throws RemoteException {
		// TODO Auto-generated method stub
		return receiptService.updateReceipt(receipt);
	}

	@Override
	public boolean deleteReceipt(Receipt receipt) throws RemoteException {
		// TODO Auto-generated method stub
		return receiptService.deleteReceipt(receipt);
	}

	@Override
	public ReceiptIn getReceiptIn(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return receiptService.getReceiptIn(id);
	}

	@Override
	public ReceiptOut getReceiptOut(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return receiptService.getReceiptOut(id);
	}

	@Override
	public List<ReceiptIn> getAllReceiptIn() throws RemoteException {
		// TODO Auto-generated method stub
		return receiptService.getAllReceiptIn();
	}

	@Override
	public List<ReceiptOut> getAllReceiptOut() throws RemoteException {
		// TODO Auto-generated method stub
		return receiptService.getAllReceiptOut();
	}

	@Override
	public List<ReceiptIn> getAllReceiptInByTime(String begin, String end)
			throws RemoteException {
		// TODO Auto-generated method stub
		return receiptService.getAllReceiptInByTime(begin, end);
	}

	@Override
	public List<ReceiptOut> getAllReceiptOutByTime(String begin, String end)
			throws RemoteException {
		// TODO Auto-generated method stub
		return receiptService.getAllReceiptOutByTime(begin, end);
	}

}
