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
		return ((SalesIn) super.get(SalesIn.class, id)).clone(); 
	}
	
	public Sales getSalesBack(String id){
		return((SalesBack) super.get(SalesBack.class, id)).clone();
	}
	
	public boolean update(Sales sales){
		return super.update(sales, sales.getId());
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Sales> getAllSalesIn(){
		Vector<SalesIn> tmp = super.getAll("SalesIn order by time ASC");
		Vector<Sales> vector = new Vector<>();
		for(SalesIn salesIn:tmp){
			vector.add(salesIn.clone());
		}
		return vector;
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Sales> getAllSalesBack(){
		Vector<SalesBack> tmp = super.getAll("SalesBack order by time ASC");
		Vector<Sales> vector = new Vector<>();
		for(SalesBack salesIn:tmp){
			vector.add(salesIn.clone());
		}
		return vector;
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Sales> getAllSalesInByTime(String begin, String end){
		Vector<SalesIn> tmp = super.getAll("SalesIn where time>'"+begin+"' and time<'"+end+"' order by time ASC");
		Vector<Sales> vector = new Vector<>();
		for(SalesIn salesIn:tmp){
			vector.add(salesIn.clone());
		}
		return vector;
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Sales> getAllSalesBackByTime(String begin, String end){
		Vector<SalesBack> tmp = super.getAll("SalesBack where time>'"+begin+"' and time<'"+end+"' order by time ASC");
		Vector<Sales> vector = new Vector<>();
		for(SalesBack salesIn:tmp){
			vector.add(salesIn.clone());
		}
		return vector;
	}

}
