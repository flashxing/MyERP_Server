package com.njue.mis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.njue.mis.model.MoneyItem;

public class MoneyItemDao extends CommonObjectDao{
	public String add(MoneyItem moneyItem){
		Serializable id = super.save(moneyItem);
		return id!=null?(String) id:null;
	}
	
	@SuppressWarnings("unchecked")
	public List<MoneyItem> getAll(){
		List<MoneyItem> list = new ArrayList<>();
		Iterator<MoneyItem> iterator = super.getAll("MoneyItem").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next());
		}
		return list;
	}
}
