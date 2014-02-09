package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.CardItemDao;
import com.njue.mis.model.CardItem;
import com.njue.mis.server.Server;

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
	
	public CardItem getCardItem(String cardName){
		return cardItemDao.getCardItem(cardName);
	}
	
	public boolean updateCardItem(String cardName, double money){
		CardItem cardItem = getCardItem(cardName);
		if (cardItem == null) {
			Server.logger.warn("Failed to get the card "+cardName);
		}
		cardItem.setMoney(cardItem.getMoney()+money);
		return updateCardItem(cardItem);
	}
	
	public boolean saveOrUpdateCardItems(List<CardItem> cardItemList){
		return cardItemDao.saveOrUpdate(cardItemList);
	}
}
