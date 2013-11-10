package com.njue.mis.services;

import java.util.Vector;

import com.njue.mis.dao.StockObjectDao;
import com.njue.mis.model.Gift;
import com.njue.mis.model.GiftIn;
import com.njue.mis.model.GiftOut;
import com.njue.mis.model.Goods;
import com.njue.mis.model.InCome;
import com.njue.mis.model.OutCome;
import com.njue.mis.model.Stock;
import com.njue.mis.model.StockInObject;
import com.njue.mis.model.StockObject;
import com.njue.mis.model.StockOutObject;
import com.njue.mis.server.Server;

public class StockObjectService {
	private StockObjectDao stockObjectDao;
	private StockService stockService;
	private GoodsService goodsService;
	public StockObjectService(){
		stockObjectDao = new StockObjectDao();
		stockService = new StockService();
		goodsService = new GoodsService();
	}
	
	public String addStockOutObject(StockOutObject stockOut){
		String id = stockObjectDao.add(stockOut);
		if(id == null){
			Server.logger.warn("Failed to save the StockOut:"+stockOut);
			return null;
		}
		String goodsId = stockOut.getGoodsId();
		int shId = stockOut.getStoreHouseId();
		int number = stockOut.getNumber();
		Stock stock = new Stock(goodsId,shId,number);
		if(!stockService.deleteStock(stock)){
			Server.logger.warn("Failed to delete stock after StockOut:"+stock);
			stockObjectDao.delete(stockOut);
			return null;
		}
		Goods goods = goodsService.getGoods(goodsId);
		if (goods == null){
			Server.logger.warn("Failed to get goods after StockOut:"+goodsId);
			stockService.addStock(stock);
			stockObjectDao.delete(stockOut);
			return null;
		}
		goods.setGoodsNum(goods.getGoodsNum()-number);
		if(!goodsService.updateGoods(goods)){
			Server.logger.warn("Failed to update the goods after StockOut");
			stockService.addStock(stock);
			stockObjectDao.delete(stockOut);
			return null;
		}
		return id;
	}
	
	public String addStockInObject(StockInObject stockIn){
		String id = stockObjectDao.add(stockIn);
		if(id == null){
			Server.logger.warn("Failed to save the stockIn:"+stockIn);
			return null;
		}
		String goodsId = stockIn.getGoodsId();
		int shId = stockIn.getStoreHouseId();
		int number = stockIn.getNumber();
		Stock stock = new Stock(goodsId,shId,number);
		if(stockService.addStock(stock)<0){
			Server.logger.warn("Failed to add stock:"+stock);
			stockObjectDao.delete(stockIn);
			return null;
		}
		Goods goods = goodsService.getGoods(goodsId);
		if (goods == null){
			Server.logger.warn("Failed to get goods:"+goodsId);
			stockService.deleteStock(stock);
			stockObjectDao.delete(stockIn);
			return null;
		}
		goods.setGoodsNum(goods.getGoodsNum()+number);
		if(!goodsService.updateGoods(goods)){
			Server.logger.warn("Failed to update the goods after StockIn");
			stockService.deleteStock(stock);
			stockObjectDao.delete(stockIn);
			return null;
		}
		return id;
	}
	
	public boolean deleteStockObject(StockObject stockObject){
		return stockObjectDao.delete(stockObject);
	}
	
	public boolean updateStockObject(StockObject stockObject){
		return stockObjectDao.update(stockObject);
	}
	
	public GiftIn getGiftIn(String id){
		return stockObjectDao.getGiftIn(id);
	}
	
	public GiftOut getGiftOut(String id){
		return stockObjectDao.getGiftOut(id);
	}
	
	public InCome getInCome(String id){
		return stockObjectDao.getInCome(id);
	}
	
	public OutCome getOutCome(String id){
		return stockObjectDao.getOutCome(id);
	}
	
	public Vector<GiftIn> getAllGiftIn(){
		return stockObjectDao.getAllGiftIn();
	}
	
	public Vector<GiftOut> getAllGiftOut(){
		return stockObjectDao.getAllGiftOut();
	}
	
	public Vector<InCome> getAllInCome(){
		return stockObjectDao.getAllInCome();
	}
	
	public Vector<OutCome> getAllOutCome(){
		return stockObjectDao.getAllOutCome();
	}
}
