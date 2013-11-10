package com.njue.mis.dao;

import java.util.Vector;

import com.njue.mis.model.Sales;
import com.njue.mis.model.SalesBack;
import com.njue.mis.model.SalesIn;

public class SalesDao extends CommonObjectDao {
	public String save(Sales sales){
		return (String) super.save(sales);
	}
	
	public boolean delete(Sales sales){
		return super.delete(sales);
	}
	
	public Sales getSalesIn(String id){
		return (SalesIn) super.get(SalesIn.class, id); 
	}
	
	public Sales getSalesBack(String id){
		return(SalesBack) super.get(SalesBack.class, id);
	}
	
	public boolean update(Sales sales){
		return super.update(sales, sales.getId());
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Sales> getAllSalesIn(){
		return super.getAll("SalesIn order by time ASC");
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Sales> getAllSalesBack(){
		return super.getAll("SalesBack order by time ASC");
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Sales> getAllSalesInByTime(String begin, String end){
		return super.getAll("SalesIn where time>'"+begin+"' and time<'"+end+"' order by time ASC");
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Sales> getAllSalesBackByTime(String begin, String end){
		return super.getAll("SalesBack where time>'"+begin+"' and time<'"+end+"' order by time ASC");
	}

}
