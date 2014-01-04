package com.njue.mis.services;

import java.awt.event.ItemEvent;
import java.util.List;

import com.njue.mis.common.CommonUtil;
import com.njue.mis.dao.StockObjectDao;
import com.njue.mis.model.GiftIn;
import com.njue.mis.model.GiftOut;
import com.njue.mis.model.Goods;
import com.njue.mis.model.InCome;
import com.njue.mis.model.OutCome;
import com.njue.mis.model.Stock;
import com.njue.mis.model.StockInObject;
import com.njue.mis.model.StockItem;
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
		int time = CommonUtil.convertDateToInt(stockOut.getTime());
		if(time == 0){
			Server.logger.warn("Failed to save the StockOutObject for time is error"+stockOut.getTime());
			return null;
		}	
		String id = stockObjectDao.add(stockOut);
		if(id == null){
			Server.logger.warn("Failed to save the StockOut:"+stockOut);
			return null;
		}
		for(StockItem stockItem:stockOut.getStockItems()){
			String goodsId = stockItem.getGoodsId();
			int shId = stockOut.getStoreHouseId();
			int number = stockItem.getNumber();
			Stock stock = new Stock(goodsId,shId,number,time,0);
			if(!stockService.deleteStock(stock)){
				Server.logger.warn("Failed to delete stock after StockOut:"+stock+" "+stockOut.getId());
				stockObjectDao.delete(stockOut);
				return null;
			}
			Goods goods = goodsService.getGoods(goodsId);
			if (goods == null){
				Server.logger.warn("Failed to get goods after StockOut:"+goodsId+" "+stockOut.getId());
				stockService.addStock(stock);
				stockObjectDao.delete(stockOut);
				return null;
			}
			goods.setGoodsNum(goods.getGoodsNum()-number);
			if(!goodsService.updateGoods(goods)){
				Server.logger.warn("Failed to update the goods after StockOut"+stockOut.getId());
				stockService.addStock(stock);
				stockObjectDao.delete(stockOut);
				return null;
			}
		}
		
		return id;
	}
	
	public String addStockInObject(StockInObject stockIn){
		int time = CommonUtil.convertDateToInt(stockIn.getTime());
		if(time == 0){
			Server.logger.warn("Failed to save the StockInObject for time is error"+stockIn.getTime());
			return null;
		}	
		String id = stockObjectDao.add(stockIn);
		if(id == null){
			Server.logger.warn("Failed to save the stockIn:"+stockIn.getId());
			return null;
		}
		for(StockItem stockItem:stockIn.getStockItems()){
			String goodsId = stockItem.getGoodsId();
			int shId = stockIn.getStoreHouseId();
			int number = stockItem.getNumber();
			Stock stock = new Stock(goodsId,shId,number,time,0);
			if(stockService.addStock(stock)<0){
				Server.logger.warn("Failed to add stock:"+stock+" after stockIn"+stockIn.getId());
				stockObjectDao.delete(stockIn);
				return null;
			}
			Goods goods = goodsService.getGoods(goodsId);
			if (goods == null){
				Server.logger.warn("Failed to get goods:"+goodsId+" after stockIn"+stockIn.getId());
				stockService.deleteStock(stock);
				stockObjectDao.delete(stockIn);
				return null;
			}
			goods.setGoodsNum(goods.getGoodsNum()+number);
			if(!goodsService.updateGoods(goods)){
				Server.logger.warn("Failed to update the goods after StockIn"+stockIn.getId());
				stockService.deleteStock(stock);
				stockObjectDao.delete(stockIn);
				return null;
			}
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
	
	public List<GiftIn> getAllGiftIn(){
		return stockObjectDao.getAllGiftIn();
	}
	
	public List<GiftOut> getAllGiftOut(){
		return stockObjectDao.getAllGiftOut();
	}
	
	public List<InCome> getAllInCome(){
		return stockObjectDao.getAllInCome();
	}
	
	public List<OutCome> getAllOutCome(){
		return stockObjectDao.getAllOutCome();
	}
}
