package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.ReceiptDao;
import com.njue.mis.model.CustomerMoney;
import com.njue.mis.model.Receipt;
import com.njue.mis.model.ReceiptIn;
import com.njue.mis.model.ReceiptOut;
import com.njue.mis.server.Server;

public class ReceiptService {
	private ReceiptDao receiptDao;
	private CustomerMoneyService customerMoneyService;
	public ReceiptService(){
		receiptDao = new ReceiptDao();
		customerMoneyService = new CustomerMoneyService();
	}
	
	public String addReceipt(Receipt receipt){
		CustomerMoney customerMoney =null;
		if(receipt instanceof ReceiptIn){
			customerMoney = new CustomerMoney(receipt.getCustomerId(), 0, -receipt.getMoney());
			if(customerMoneyService.add(customerMoney)==null){
				Server.logger.warn("Failed to delete the money of the customer "+receipt.getCustomerId()+" to give "+receipt.getMoney());
			}
		}else {
			customerMoney = new CustomerMoney(receipt.getCustomerId(), -receipt.getMoney(), 0);
			if(customerMoneyService.add(customerMoney)==null){
				Server.logger.warn("Failed to delete the money of the customer "+receipt.getCustomerId()+" to receive "+receipt.getMoney());
			}
		}
		String result = receiptDao.add(receipt);
		if(result == null){
			customerMoneyService.delete(customerMoney);
		}
		return result;
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
