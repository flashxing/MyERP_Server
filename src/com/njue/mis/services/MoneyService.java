package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.MoneyDao;
import com.njue.mis.model.Money;

public class MoneyService {
	private MoneyDao moneyDao;
	public MoneyService(){
		moneyDao = new MoneyDao();
	}
	
	public String addMoney(Money money){
		return moneyDao.add(money);
	}
	
	public boolean updateMoney(Money money){
		return moneyDao.update(money);
	}
	
	public boolean deleteMoney(Money money){
		return moneyDao.delete(money);
	}
	
	public Money getMoney(String id){
		return moneyDao.getMoney(id);
	}
	public List<Money> getMoneysByTime(String begin, String end){
		return moneyDao.getMoneysByTime(begin, end);
	}
}
