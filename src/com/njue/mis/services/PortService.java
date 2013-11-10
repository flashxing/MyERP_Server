package com.njue.mis.services;

import java.util.Vector;

import com.njue.mis.dao.PortDAO;
import com.njue.mis.model.Goods;
import com.njue.mis.model.PortBack;
import com.njue.mis.model.PortIn;
import com.njue.mis.model.Stock;
import com.njue.mis.server.Server;

public class PortService {
	private PortDAO portDao = new PortDAO();
	private StockService stockService = new StockService();
	private GoodsService goodsService = new GoodsService();
	
	public String addPortBack(PortBack portBack){
		String result = portDao.addPort(portBack);
		if(result == null){
			Server.logger.warn("Failed to save the PortIn:"+portBack);
			return null;
		}
		String goodsId = portBack.getGoodsId();
		int shId = portBack.getStoreHouseId();
		int number = portBack.getNumber();
		Stock stock = new Stock(goodsId,shId,number);
		if(!stockService.deleteStock(stock)){
			Server.logger.warn("Failed to delete stock:"+stock);
			portDao.deletePort(portBack);
			return null;
		}
		Goods goods = goodsService.getGoods(goodsId);
		if (goods == null){
			Server.logger.warn("Failed to get goods:"+goodsId);
			stockService.addStock(stock);
			portDao.deletePort(portBack);
			return null;
		}
		goods.setGoodsNum(goods.getGoodsNum()-number);
		if(!goodsService.updateGoods(goods)){
			Server.logger.warn("Failed to update the goods after PortBack");
			stockService.addStock(stock);
			portDao.deletePort(portBack);
			return null;
		}
		return result;
	}
	public String addPortIn(PortIn portIn){
		String result = portDao.addPort(portIn);
		if(result == null){
			Server.logger.warn("Failed to save the PortIn:"+portIn);
			return null;
		}
		String goodsId = portIn.getGoodsId();
		int shId = portIn.getStoreHouseId();
		int number = portIn.getNumber();
		Stock stock = new Stock(goodsId,shId,number);
		if(stockService.addStock(stock)<0){
			Server.logger.warn("Failed to add stock:"+stock);
			portDao.deletePort(portIn);
			return null;
		}
		Goods goods = goodsService.getGoods(goodsId);
		if (goods == null){
			Server.logger.warn("Failed to get goods:"+goodsId);
			stockService.deleteStock(stock);
			portDao.deletePort(portIn);
			return null;
		}
		goods.setGoodsNum(goods.getGoodsNum()+number);
		goods.setLastStockPrice(portIn.getPrice());
		if(!goodsService.updateGoods(goods)){
			Server.logger.warn("Failed to update the goods after PortIn");
			stockService.deleteStock(stock);
			portDao.deletePort(portIn);
			return null;
		}
		return result;
	}
	public Vector<PortIn> getAllPortIn(){
		return portDao.getAllPortIn();
	}
	public PortIn getPortIn(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Vector<PortIn> searchPortInByTime(String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return portDao.searchPortInByTime(beginTime, endTime);
	}
	public Vector<PortIn> searchPortIn(String field, String value) {
		// TODO Auto-generated method stub
		return null;
	}
	public Vector<PortBack> searchPortBackByTime(String begin,String end){
		return portDao.searchPortBackByTime(begin, end);
	}
	
	public Vector<PortBack> getAllPortBack(){
		return portDao.getAllPortBack();
	}
}
