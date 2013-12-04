package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.ReceiptDao;
import com.njue.mis.model.Receipt;
import com.njue.mis.model.ReceiptIn;
import com.njue.mis.model.ReceiptOut;

public class ReceiptService {
	private ReceiptDao receiptDao;
	public ReceiptService(){
		receiptDao = new ReceiptDao();
	}
	
	public String addReceipt(Receipt receipt){
		return receiptDao.add(receipt);
	}
	
	public boolean deleteReceipt(Receipt receipt){
		return receiptDao.delete(receipt);
	}
	
	public boolean updateReceipt(Receipt receipt){
		return receiptDao.update(receipt);
	}
	
	public ReceiptIn getReceiptIn(String id){
		return receiptDao.getReceiptIn(id);
	}
	
	public ReceiptOut getReceiptOut(String id){
		return receiptDao.getReceiptOut(id);
	}
	
	public List<ReceiptIn> getAllReceiptIn(){
		return receiptDao.getAllReceiptIn();
	}
	
	public List<ReceiptOut> getAllReceiptOut(){
		return receiptDao.getAllReceiptOut();
	}
	
	public List<ReceiptIn> getAllReceiptInByTime(String begin, String end){
		return receiptDao.getReceiptInByTime(begin, end);
	}
	
	public List<ReceiptOut> getAllReceiptOutByTime(String begin, String end){
		return receiptDao.getReceiptOutByTime(begin, end);
	}
}
