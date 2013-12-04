package com.njue.mis.dao;

import java.util.List;

import com.njue.mis.model.Receipt;
import com.njue.mis.model.ReceiptIn;
import com.njue.mis.model.ReceiptOut;

public class ReceiptDao extends CommonObjectDao {
	public String add(Receipt receipt){
		return (String) super.save(receipt);
	}
	
	public boolean delete(Receipt receipt){
		return super.delete(receipt);
	}
	
	public boolean update(Receipt receipt){
		return super.update(receipt.getClass(), receipt.getId());
	}
	
	public ReceiptIn getReceiptIn(String id){
		Object obj = super.get(ReceiptIn.class, id);
		if(obj != null){
			return (ReceiptIn) obj;
		}
		return null;
	}
	
	public ReceiptOut getReceiptOut(String id){
		Object obj = super.get(ReceiptOut.class, id);
		return obj != null?(ReceiptOut) obj:null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReceiptOut> getAllReceiptOut(){
		return super.getAll("ReceiptOut order by time ASC");
	}
	
	@SuppressWarnings("unchecked")
	public List<ReceiptIn> getAllReceiptIn(){
		return super.getAll("ReceiptIn order by time ASC");
	}
	
	@SuppressWarnings("unchecked")
	public List<ReceiptOut> getReceiptOutByTime(String begin, String end){
		return super.getAll("ReceiptOut where time>'"+begin+"' and time<'"+end+"' order by time ASC");
	}
	
	@SuppressWarnings("unchecked")
	public List<ReceiptIn> getReceiptInByTime(String begin, String end){
		return super.getAll("ReceiptIn where time>'"+begin+"' and time<'"+end+"' order by time ASC");
	}
	
}
