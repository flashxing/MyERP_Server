package com.njue.mis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.njue.mis.model.CardItem;

public class CardItemDao extends CommonObjectDao{
	public String add(CardItem cardItem){
		Serializable id = super.save(cardItem);
		return id!=null?(String) id:null;
	}
	
	public boolean update(CardItem cardItem){
		return super.update(cardItem);
	}
	
	public boolean deleteCardItem(CardItem cardItem){
		return super.delete(cardItem);
	}
	
	@SuppressWarnings("unchecked")
	public List<CardItem> getAll(){
		List<CardItem> list = new ArrayList<>();
		Iterator<CardItem> iterator = super.getAll("CardItem").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next());
		}
		return list;
	}
}
