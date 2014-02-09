package com.njue.mis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.njue.mis.model.CardItem;
import com.njue.mis.server.Server;

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
	
	public CardItem getCardItem(String cardName){
		Object object = super.get(CardItem.class, cardName);
		return object == null?null:(CardItem) object;
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

	@SuppressWarnings("finally")
	public boolean saveOrUpdate(List<CardItem> cardItemList) {
		Session session = null;
		Boolean result = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			for(CardItem cardItem: cardItemList){
				session.saveOrUpdate(cardItem);
			}
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("update "+cardItemList+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
			result = false;
		}finally{
			if(session != null){
				session.close();
			}
			return result;
		}
	}
}
