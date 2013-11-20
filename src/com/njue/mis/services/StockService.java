package com.njue.mis.services;

import java.util.Vector;

import com.njue.mis.dao.StockDao;
import com.njue.mis.model.Stock;

public class StockService {
	private StockDao stockDao = new StockDao();
	public int addStock(Stock stock){
		int id = stockDao.getStockId(stock);
		if(id < 0){
			return stockDao.saveStock(stock);	
		}{
			Stock tmp_stock = stockDao.getStock(id);
			tmp_stock.setNumber(stock.getNumber()+tmp_stock.getNumber());
			if(stockDao.updateStock(tmp_stock)){
				return tmp_stock.getId();
			}else{
				return -1;
			}
		}
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
	
	
}
