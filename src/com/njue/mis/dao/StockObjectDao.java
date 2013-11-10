package com.njue.mis.dao;

import java.util.Vector;

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
			return (GiftIn) obj;
		}
		return null;
	}
	
	public GiftOut getGiftOut(String id){
		Object obj = super.get(GiftOut.class, id);
		if(obj != null){
			return (GiftOut) obj;
		}
		return null;
	}
	
	public InCome getInCome(String id){
		Object obj = super.get(InCome.class, id);
		if(obj != null){
			return (InCome) obj;
		}
		return null;
	}
	
	public OutCome getOutCome(String id){
		Object obj = super.get(OutCome.class, id);
		if(obj != null){
			return (OutCome) obj;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Vector<GiftIn> getAllGiftIn(){
		return super.getAll("GiftIn order by inporttime");
	}
	
	@SuppressWarnings("unchecked")
	public Vector<GiftOut> getAllGiftOut(){
		return super.getAll("GiftOut order by inporttime");
	}
	
	@SuppressWarnings("unchecked")
	public Vector<InCome> getAllInCome(){
		return super.getAll("InCome order by inporttime");
	}
	@SuppressWarnings("unchecked")
	public Vector<OutCome> getAllOutCome(){
		return super.getAll("OutCome oreder by inporttime");
	}
}
