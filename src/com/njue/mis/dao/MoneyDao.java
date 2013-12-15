package com.njue.mis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.njue.mis.model.Money;

public class MoneyDao extends CommonObjectDao{
	public String add(Money money){
		Serializable id = super.save(money);
		return id!=null?(String) id:null;
	}
	
	public boolean delete(Money money){
		return super.delete(money);
	}
	
	public boolean update(Money money){
		return super.update(money, money.getId());
	}
	
	public Money getMoney(String id){
		Object object = super.get(Money.class, id);
		return object!=null?(Money) object:null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Money> getMoneysByTime(String begin, String end){
		List<Money> list = new ArrayList<>();
		Iterator<Money> iterator = super.getAll("Money where time>'"+begin+"' and time<'"+end+"' order by time ASC").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next().clone());
		}
		return list;
	}
}
