package com.njue.mis.services;

import java.util.Vector;

import com.njue.mis.dao.GoodsHDao;
import com.njue.mis.model.Goods;
import com.njue.mis.model.StorageGoods;

public class GoodsService{
	public GoodsService() {
		// TODO Auto-generated constructor stub
	}

	private GoodsHDao goodsDao = new GoodsHDao();
	public String addGoods(Goods goods) {
		// TODO Auto-generated method stub
		return goodsDao.addGoods(goods);
	}

	public boolean deleteGoods(Goods goods) {
		// TODO Auto-generated method stub
		return goodsDao.deleteGoods(goods);
	}

	public Vector<Goods> searchGoods(String field, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateGoods(Goods goods) {
		// TODO Auto-generated method stub
		return goodsDao.updateGoods(goods);
	}

	public Vector<Goods> getAllGoods() {
		// TODO Auto-generated method stub
		return goodsDao.getAllGoods();
	}

	public boolean isExisted(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Goods getGoods(String id) {
		// TODO Auto-generated method stub
		return goodsDao.getGoodsInfo(id);
	}

	public Vector<StorageGoods> getAllStorageGoods() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<Goods> getAllGoodsByCateId(int cateId) {
		// TODO Auto-generated method stub
		return goodsDao.getAllGoodsByCategory(cateId);
	}

	public Vector<Goods> getAllGoodsByGoodsName(String goodsName) {
		// TODO Auto-generated method stub
		return goodsDao.getAllGoodsByGoodsName(goodsName);
	}
}
