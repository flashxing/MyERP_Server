package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.CardItemControllerInterface;
import com.njue.mis.model.CardItem;
import com.njue.mis.services.CardItemService;

public class CardItemController extends UnicastRemoteObject implements CardItemControllerInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6963497733442792472L;
	private CardItemService cardItemService;
	
	public CardItemController() throws RemoteException{
		cardItemService = new CardItemService();
	}

	@Override
	public String addCardItem(CardItem cardItem) throws RemoteException {
		// TODO Auto-generated method stub
		return cardItemService.addCardItem(cardItem);
	}

	@Override
	public boolean deleteCardItem(CardItem cardItem) throws RemoteException {
		// TODO Auto-generated method stub
		return cardItemService.deleteCardItem(cardItem);
	}

	@Override
	public boolean updateCardItem(CardItem cardItem) throws RemoteException {
		// TODO Auto-generated method stub
		return cardItemService.updateCardItem(cardItem);
	}

	@Override
	public List<CardItem> getAllCardItems() throws RemoteException {
		// TODO Auto-generated method stub
		return cardItemService.getAllCardItems();
	}

	@Override
	public boolean saveOrUpdateCardItems(List<CardItem> cardItemList)
			throws RemoteException {
		// TODO Auto-generated method stub
		return cardItemService.saveOrUpdateCardItems(cardItemList);
	}
}
