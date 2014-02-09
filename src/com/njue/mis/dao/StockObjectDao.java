package com.njue.mis.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.njue.mis.model.GiftIn;
import com.njue.mis.model.GiftOut;
import com.njue.mis.model.InCome;
import com.njue.mis.model.OutCome;
import com.njue.mis.model.StockObject;

public class StockObjectDao extends CommonObjectDao{
	public String add(StockObject stockObject){
		return (String) super.save(stockObject);
	}
	
	public boolean delete(StockObject stockObject){
		return super.delete(stockObject);
	}
	
	public boolean update(StockObject stockObject){
		String id = stockObject.getId();
		return super.update(stockObject.getClass(), id);
	}
	
	public GiftIn getGiftIn(String id){
		Object obj = super.get(GiftIn.class, id);
		if(obj != null){
			return ((GiftIn) obj).clone();
		}
		return null;
	}
	
	public GiftOut getGiftOut(String id){
		Object obj = super.get(GiftOut.class, id);
		if(obj != null){
			return ((GiftOut) obj).clone();
		}
		return null;
	}
	
	public InCome getInCome(String id){
		Object obj = super.get(InCome.class, id);
		if(obj != null){
			return ((InCome) obj).clone();
		}
		return null;
	}
	
	public OutCome getOutCome(String id){
		Object obj = super.get(OutCome.class, id);
		if(obj != null){
			return ((OutCome) obj).clone();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<GiftIn> getAllGiftIn(){
		List<GiftIn> list = new ArrayList<>();
		Iterator<GiftIn> iterator = super.getAll("GiftIn order by inporttime").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next().clone());
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<GiftOut> getAllGiftOut(){
		List<GiftOut> list = new ArrayList<>();
		Iterator<GiftOut> iterator = super.getAll("GiftOut order by inporttime").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next().clone());
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<InCome> getAllInCome(){
		List<InCome> list = new ArrayList<>();
		Iterator<InCome> iterator = super.getAll("InCome order by inporttime").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next().clone());
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<OutCome> getAllOutCome(){
		List<OutCome> list = new ArrayList<>();
		Iterator<OutCome> iterator = super.getAll("OutCome oreder by inporttime").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next().clone());
		}
		return list;
	}
}
