package com.njue.mis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.metamodel.domain.Superclass;

import com.njue.mis.model.Sales;
import com.njue.mis.model.SalesBack;
import com.njue.mis.model.SalesIn;

public class SalesDao extends CommonObjectDao {
	public String save(Sales sales){
		Serializable id = super.save(sales);
		return null!=id ?(String) id : null;
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
		Object object = super.get(sales.getClass(), sales.getId());
		if(null == object){
			return false;
		}
		super.delete(object);
		return save(sales) != null;
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Sales> getAllSalesIn(){
		Vector<SalesIn> tmp = super.getAll("SalesIn where is_published=1 order by time ASC");
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
		Vector<SalesIn> tmp = super.getAll("SalesIn where is_published=1 and time>'"+begin+"' and time<'"+end+"' order by time ASC");
		Vector<Sales> vector = new Vector<>();
		for(SalesIn salesIn:tmp){
			vector.add(salesIn.clone());
		}
		return vector;
	}
	public List<Sales> searchSales(String begin, String end, 
									String customerId, String goodsId,
									String salesMan, int shId,
									String operator, String comment, int type)
	{
		List<Sales> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		if(type<=2){
			sql.append("SalesIn where is_published="+type);
		}
		if(null != begin){
			sql.append(" and time>'"+begin+"'");
		}
		if(null != end){
			sql.append(" and time<'"+end+"'");
		}
		if(customerId != null){
			sql.append(" and customerid='"+customerId+"'");
		}
		if(null != salesMan){
			sql.append(" and sales_man='"+salesMan+"'");
		}
		sql.append(" and storehouseid="+shId);
		if(null != operator){
			sql.append(" and operateperson likes '%"+operator+"%'");
		}
		if(null != comment){
			sql.append(" and comment likes '%"+comment+"%'");
		}
		sql.append(" order by time DESC");
		@SuppressWarnings("rawtypes")
		Iterator iterator = super.getAll(sql.toString()).iterator();
		while (iterator.hasNext()) {
			list.add(((Sales) iterator.next()).clone());
		}
		return list;
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
	
	@SuppressWarnings("unchecked")
	public List<SalesIn> getAllSalesDraft(){
		List<SalesIn> saleInList = new ArrayList<>();
		Iterator<SalesIn> iterator = super.getAll("SalesIn where is_published=0 order by time DESC").iterator();
		while(iterator.hasNext()){
			saleInList.add(iterator.next().clone());
		}
		return saleInList;
	}
	
	@SuppressWarnings("unchecked")
	public List<SalesIn> getAllSalesDraft(String begin, String end){
		List<SalesIn> salesIns = new ArrayList<>();
		Iterator<SalesIn> iterator = super.getAll("SalesIn where is_published=0 and time>='"+begin+"' and time<='"+end+"' order by time DESC").iterator();
		while(iterator.hasNext()){
			salesIns.add(iterator.next().clone());
		}
		return salesIns;
	}

}
