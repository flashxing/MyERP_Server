package com.njue.mis.services;

import java.util.ArrayList;
import java.util.List;

import com.njue.mis.dao.SalesManDao;
import com.njue.mis.model.SalesMan;

public class SalesManService {
	private SalesManDao salesManDao;
	public SalesManService(){
		salesManDao = new SalesManDao();
	}
	
	public int addSalesMan(SalesMan salesMan){
		return salesManDao.addSalesMan(salesMan);
	}
	
	public boolean updateSalesMan(SalesMan salesMan){
		return salesManDao.update(salesMan);
	}
	
	public boolean deleteSalesMan(SalesMan salesMan){
		return salesManDao.deleteSalesMan(salesMan);
	}
	
	public SalesMan geSalesManById(int id){
		return salesManDao.getSalesManById(id);
	}
	
	public List<SalesMan> getAllSalesMans(){
		return salesManDao.getAllSalesMans();
	}
	
	public List<SalesMan> getSalesMansByIds(List<Integer> idList){
		List<SalesMan> list = new ArrayList<>();
		for(Integer id : idList){
			list.add(salesManDao.getSalesManById(id));
		}
		return list;
	}
}
