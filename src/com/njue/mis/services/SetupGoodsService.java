package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.SetupGoodsDao;
import com.njue.mis.model.SetupGoods;

public class SetupGoodsService {
	private SetupGoodsDao setupGoodsDao;
	public SetupGoodsService(){
		setupGoodsDao = new SetupGoodsDao();
	}
	
	public boolean update(List<SetupGoods> setupGoodsList){
		return setupGoodsDao.update(setupGoodsList);
	}
	
	public List<SetupGoods> getAll(List<String> goodsIdList, int shId){
		return setupGoodsDao.getAll(goodsIdList, shId);
	}

	public List<SetupGoods> getAll() {
		// TODO Auto-generated method stub
		return setupGoodsDao.getAll();
	}
}
