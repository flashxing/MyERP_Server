package com.njue.mis.dao;

import java.util.List;

import com.njue.mis.model.ReceiptItem;

public class ReceiptItemDao extends CommonObjectDao{
	public String add(ReceiptItem receiptItem){
		return (String) super.save(receiptItem);
	}
	
	public boolean delete(ReceiptItem receipteItem){
		return super.delete(receipteItem);
	}
	
	public boolean update(ReceiptItem receipteItem){
		return super.update(ReceiptItem.class, receipteItem.getItem());
	}
	
	public ReceiptItem get(String item){
		Object obj = super.get(ReceiptItem.class, item);
		return obj != null?(ReceiptItem) obj:null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReceiptItem> getAll(){
		return super.getAll("ReceiptItem");
	}
}
