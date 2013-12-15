package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.MoneyItemDao;
import com.njue.mis.model.MoneyItem;

public class MoneyItemService {
	private MoneyItemDao moneyItemDao;
	public MoneyItemService(){
		moneyItemDao = new MoneyItemDao();
	}
	
	public String addMoneyItem(MoneyItem moneyItem){
		return moneyItemDao.add(moneyItem);
	}
	
	public List<MoneyItem> getAll(){
		return moneyItemDao.getAll();
	}
}
