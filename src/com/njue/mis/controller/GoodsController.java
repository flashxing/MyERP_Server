package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Vector;

import com.njue.mis.interfaces.GoodsControllerInterface;
import com.njue.mis.model.Goods;
import com.njue.mis.model.StorageGoods;
import com.njue.mis.services.GoodsService;

public class GoodsController extends UnicastRemoteObject implements GoodsControllerInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1104391533913785543L;
	public GoodsController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private GoodsService goodsService = new GoodsService();
	@Override
	public String addGoods(Goods goods) {
		// TODO Auto-generated method stub
		return goodsService.addGoods(goods);
	}

	@Override
	public boolean deleteGoods(Goods goods) {
		// TODO Auto-generated method stub
		return goodsService.deleteGoods(goods);
	}

	@Override
	public Vector<Goods> searchGoods(String field, String value) {
		// TODO Auto-generated method stub
		return goodsService.searchGoods(field, value);
	}

	@Override
	public boolean updateGoods(Goods goods) {
		// TODO Auto-generated method stub
		return goodsService.updateGoods(goods);
	}

	@Override
	public Vector<Goods> getAllGoods() {
		// TODO Auto-generated method stub
		return goodsService.getAllGoods();
	}

	@Override
	public boolean isExisted(String id) {
		// TODO Auto-generated method stub
		return goodsService.isExisted(id);
	}

	@Override
	public Goods getGoods(String id) {
		// TODO Auto-generated method stub
		return goodsService.getGoods(id);
	}

	@Override
	public Vector<StorageGoods> getAllStorageGoods() {
		// TODO Auto-generated method stub
		return goodsService.getAllStorageGoods();
	}

	@Override
	public Vector<Goods> getAllGoodsByCateId(int cateId) {
		// TODO Auto-generated method stub
		return goodsService.getAllGoodsByCateId(cateId);
	}
	
	@Override
	public List<Goods> getAllGoodsByCateId(List<Integer> cateIds){
		return goodsService.getAllGoodsByCateId(cateIds);
	}

	@Override
	public Vector<Goods> getAllGoodsByGoodsName(String goodsName)
			throws RemoteException {
		// TODO Auto-generated method stub
		return goodsService.getAllGoodsByGoodsName(goodsName);
	}

	@Override
	public List<Goods> getGoods(List<String> goodsIdList)
			throws RemoteException {
		// TODO Auto-generated method stub
		return goodsService.getGoods(goodsIdList);
	}
	
}
