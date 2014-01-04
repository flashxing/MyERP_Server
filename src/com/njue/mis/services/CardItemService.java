package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.CardItemDao;
import com.njue.mis.model.CardItem;

public class CardItemService {
	private CardItemDao cardItemDao;
	public CardItemService() {
		cardItemDao = new CardItemDao();
	}
	
	public String addCardItem(CardItem cardItem){
		return cardItemDao.add(cardItem);
	}
	
	public boolean deleteCardItem(CardItem cardItem){
		return cardItemDao.deleteCardItem(cardItem);
	}
	
	public boolean updateCardItem(CardItem cardItem){
		return cardItemDao.update(cardItem);
	}
	
	public List<CardItem> getAllCardItems(){
		return cardItemDao.getAll();
	}
}
