package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.GoodsItemControllerInterface;
import com.njue.mis.model.GoodsItem;
import com.njue.mis.model.SalesGoodsItem;
import com.njue.mis.services.GoodsItemService;

public class GoodsItemController extends UnicastRemoteObject implements
		GoodsItemControllerInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3036489067851033267L;
	private GoodsItemService goodsItemService;
	public GoodsItemController() throws RemoteException {
		super();
		goodsItemService = new GoodsItemService();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<List<GoodsItem>> getPortGoodsItemByTimeAndGoods(
			String beginTime, String endTime, String[] goodsIds)
			throws RemoteException {
		// TODO Auto-generated method stub
		return goodsItemService.getPortInGoodsItemByTimeAndGoods(beginTime, endTime, goodsIds);
	}

	@Override
	public List<List<SalesGoodsItem>> getSalesGoodsItemByTimeAndGoods(
			String beginTime, String endTime, String[] goodsIds)
			throws RemoteException {
		// TODO Auto-generated method stub
		return goodsItemService.getSalesGoodsItemByTimeAndGoods(beginTime, endTime, goodsIds);
	}

}
