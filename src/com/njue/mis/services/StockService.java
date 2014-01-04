package com.njue.mis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.njue.mis.common.CommonUtil;
import com.njue.mis.dao.StockDao;
import com.njue.mis.model.Stock;
import com.njue.mis.model.StoreHouse;

public class StockService {
	private StockDao stockDao = new StockDao();
	private StoreHouseService storeHouseService = new StoreHouseService();
	public int addStock(Stock stock){
		return stockDao.saveStock(stock);
	}
	
	public boolean deleteStock(Stock stock){
		return stockDao.delStock(stock);
	}
	
	public Stock getStock(int id){
		return stockDao.getStock(id);
	}
	
	public int getStockId(Stock stock){
		return stockDao.getStockId(stock);
	}
	public boolean updateStock(Stock stock){
		return stockDao.updateStock(stock);
	}
	public Vector<Stock> getAllStock(){
		return new Vector<Stock>(stockDao.getAll());
	}

	public Stock getStock(int shId, String goodsId) {
		// TODO Auto-generated method stub
		return stockDao.getStock(shId, goodsId);
	}
	
	public List<Stock> getLastStocks(String[] goodsIds, String time){
		List<Stock> stocks = new ArrayList<>();
		for(String goodsId:goodsIds){
			stocks.add(getLastStock(goodsId, CommonUtil.convertDateToInt(time)));
		}
		return stocks;
	}
	
	public Stock getLastStock(String goodsId, int time){
		List<StoreHouse> shs = storeHouseService.getAll();
		List<Stock> stocks = new ArrayList<>();
		for(StoreHouse storeHouse:shs){
			Stock stock = stockDao.getStockByGoodsAndTime(goodsId, time, storeHouse.getId());
			if(stock != null){
				stocks.add(stock);
			}
		}
		Stock stock = new Stock();
		stock.setNumber(0);
		stock.setPrice(0);
		for(Stock stock2:stocks){
			stockDao.joinStock(stock, stock2);
		}
		System.out.println("stock id:"+stock.getGoodsId()+" "+stock.getNumber()+" "+stock.getPrice());
		return stock;
	}
}
