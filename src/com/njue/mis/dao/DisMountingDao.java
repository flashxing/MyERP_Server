package com.njue.mis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.njue.mis.model.DisMounting;

public class DisMountingDao extends CommonObjectDao{
	public String add(DisMounting disMounting){
		Serializable id = super.save(disMounting);
		return id!=null?(String) id:null;
	}
	
	public boolean update(DisMounting disMounting){
		return super.update(disMounting);
	}
	
	public boolean delete(DisMounting disMounting){
		return super.delete(disMounting);
	}
	
	@SuppressWarnings("unchecked")
	public List<DisMounting> getAll(){
		List<DisMounting> list = new ArrayList<>();
		Iterator<DisMounting> iterator = super.getAll("DisMounting").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next().clone());
		}
		return list;
	}
	
	public List<DisMounting> getAllByTime(String beginTime, String endTime){
		List<DisMounting> list = new ArrayList<>();
		@SuppressWarnings("unchecked")
		Iterator<DisMounting> iterator = super.getAll("DisMounting where time>'"+beginTime+"' and time<'"+endTime+"' order by time ASC").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next().clone());
		}
		return list;
	}
}
