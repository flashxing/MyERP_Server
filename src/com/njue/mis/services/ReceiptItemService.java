package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.ReceiptItemDao;
import com.njue.mis.model.ReceiptItem;

public class ReceiptItemService {
	private ReceiptItemDao receiptDao;
	public ReceiptItemService(){
		receiptDao = new ReceiptItemDao();
	}
	
	public String add(ReceiptItem receiptItem){
		return receiptDao.add(receiptItem);
	}
	
	public boolean delete(ReceiptItem receiptItem){
		return receiptDao.delete(receiptItem);
	}
	
	public boolean update(ReceiptItem receiptItem){
		return receiptDao.update(receiptItem);
	}
	
	public ReceiptItem getReceiptItem(String item){
		return receiptDao.get(item);
	}
	
	public List<ReceiptItem> getAll(){
		return receiptDao.getAll();
	}
}
