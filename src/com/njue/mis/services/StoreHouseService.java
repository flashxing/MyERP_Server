package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.StoreHouseDao;
import com.njue.mis.model.StoreHouse;

public class StoreHouseService {
	private StoreHouseDao storeHouseDao = new StoreHouseDao();
	public int saveStoreHouse(StoreHouse storeHouse){
		return storeHouseDao.saveStoreHouse(storeHouse);
	}
	
	public boolean delStoreHouse(StoreHouse storeHouse){
		return storeHouseDao.deleteStoreHouse(storeHouse);
	}
	
	public boolean updateStoreHouse(StoreHouse storeHouse){
		return storeHouseDao.updateStoreHouse(storeHouse);
	}
	
	public StoreHouse getStoreHouse(int id){
		return storeHouseDao.getStoreHouse(id);
	}
	
	public List<StoreHouse> getAll(){
		return storeHouseDao.getAll();
	}
}
