package com.njue.mis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.njue.mis.model.MoneyChange;

public class MoneyChangeDao extends CommonObjectDao{
	public String add(MoneyChange moneyChange){
		Serializable id = super.save(moneyChange);
		return id!=null?(String) id:null;
	}
	
	public boolean delete(MoneyChange moneyChange){
		return super.delete(moneyChange);
	}
	
	public boolean update(MoneyChange moneyChange){
		return super.update(moneyChange);
	}
	
	public MoneyChange getMoney(String id){
		Object object = super.get(MoneyChange.class, id);
		return object!=null?(MoneyChange) object:null;
	}
	
	@SuppressWarnings("unchecked")
	public List<MoneyChange> getMoneyChangesByTime(String begin, String end, String operator, 
												int type, int isPublished, String comment){
		List<MoneyChange> list = new ArrayList<>();
		String sqlString = "Money where time>'"+begin+"' and time<'"+end+"'";
		if(operator != null){
			sqlString += " and operator='"+operator+"'";
		}
		if(type >= 0){
			sqlString += " and type="+type;
		}
		if(isPublished>=0){
			sqlString += " and is_published="+isPublished;
		}
		if(null != comment){
			sqlString += " and comment likes '%"+comment+"%'";
		}
		sqlString += " order by time ASC";
		Iterator<MoneyChange> iterator = super.getAll(sqlString).iterator();
		while(iterator.hasNext()){
			list.add(iterator.next().clone());
		}
		return list;
	}
}
