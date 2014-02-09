package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.SetupGoodsControllerInterface;
import com.njue.mis.model.SetupGoods;
import com.njue.mis.services.SetupGoodsService;

public class SetupGoodsController extends UnicastRemoteObject implements SetupGoodsControllerInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7257116737334114773L;
	private SetupGoodsService setupGoodsService;
	public SetupGoodsController() throws RemoteException{
		setupGoodsService = new SetupGoodsService();
	}
	@Override
	public boolean saveOrUpdateSetupGoodsList(List<SetupGoods> setupGoodsList)
			throws RemoteException {
		// TODO Auto-generated method stub
		return setupGoodsService.update(setupGoodsList);
	}
	@Override
	public List<SetupGoods> getAllSetupGoods(List<String> goodsIdList, int shId)
			throws RemoteException {
		// TODO Auto-generated method stub
		return setupGoodsService.getAll(goodsIdList, shId);
	}
}
