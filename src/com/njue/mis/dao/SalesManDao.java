package com.njue.mis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.njue.mis.model.SalesMan;

public class SalesManDao extends CommonObjectDao{
	public int addSalesMan(SalesMan salesMan){
		Serializable id = super.save(salesMan);
		return id!=null?(Integer) id:0;
	}
	
	public boolean updateSalesMan(SalesMan salesMan){
		return super.update(salesMan);
	}
	
	public boolean deleteSalesMan(SalesMan salesMan){
		return super.delete(salesMan);
	}
	
	public SalesMan getSalesManById(int id){
		Object object = super.get(SalesMan.class, id);
		return object!=null?(SalesMan) object:new SalesMan();
	}
	
	@SuppressWarnings("unchecked")
	public List<SalesMan> getAllSalesMans(){
		List<SalesMan> list = new ArrayList<>();
		Iterator<SalesMan> iterator = super.getAll("SalesMan order by id").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next().clone());
		}
		return list;
	}
	
}
