package com.njue.mis.services;

import java.util.Vector;

import com.njue.mis.dao.SalesDao;
import com.njue.mis.model.Goods;
import com.njue.mis.model.Sales;
import com.njue.mis.model.SalesBack;
import com.njue.mis.model.SalesIn;
import com.njue.mis.model.Stock;
import com.njue.mis.server.Server;

public class SalesService {

	private SalesDao salesDao = new SalesDao();
	private StockService stockService = new StockService();
	private GoodsService goodsService = new GoodsService();
	public String addSalesBack(SalesBack sales) {
		// TODO Auto-generated method stub
		String result = salesDao.save(sales);
		if(result == null){
			Server.logger.warn("Failed to save the salesback:"+sales);
			return null;
		}
		String goodsId = sales.getGoodsId();
		int shId = sales.getShId();
		int number = sales.getNumber();
		Stock stock = new Stock(goodsId,shId,number);
		if(stockService.addStock(stock)<0){
			Server.logger.warn("Failed to add stock:"+stock);
			salesDao.delete(sales);
			return null;
		}
		Goods goods = goodsService.getGoods(goodsId);
		if (goods == null){
			Server.logger.warn("Failed to get goods:"+goodsId);
			stockService.deleteStock(stock);
			salesDao.delete(sales);
			return null;
		}
		goods.setGoodsNum(goods.getGoodsNum()+number);
		if(!goodsService.updateGoods(goods)){
			Server.logger.warn("Failed to update the goods after Sales");
			stockService.deleteStock(stock);
			salesDao.delete(sales);
			return null;
		}
		return result;
	}
	public String addSalesIn(SalesIn sales) {
		// TODO Auto-generated method stub
		String result = salesDao.save(sales);
		if(result == null){
			Server.logger.warn("Failed to save the sales:"+sales);
			return null;
		}
		String goodsId = sales.getGoodsId();
		int shId = sales.getShId();
		int number = sales.getNumber();
		Stock stock = new Stock(goodsId,shId,number);
		if(!stockService.deleteStock(stock)){
			Server.logger.warn("Failed to delete stock:"+stock);
			salesDao.delete(sales);
			return null;
		}
		Goods goods = goodsService.getGoods(goodsId);
		if (goods == null){
			Server.logger.warn("Failed to get goods:"+goodsId);
			stockService.addStock(stock);
			salesDao.delete(sales);
			return null;
		}
		goods.setGoodsNum(goods.getGoodsNum()-number);
		goods.setLastSalePrice(sales.getPrice());
		if(!goodsService.updateGoods(goods)){
			Server.logger.warn("Failed to update the goods after Sales");
			stockService.addStock(stock);
			salesDao.delete(sales);
			return null;
		}
		return result;
	}
	
	public Sales getSalesIn(String id) {
		// TODO Auto-generated method stub
		return salesDao.getSalesIn(id);
	}

	public Sales getSalesBack(String id) {
		// TODO Auto-generated method stub
		return salesDao.getSalesBack(id);
	}

	public Vector<Sales> getAllSalesIn() {
		// TODO Auto-generated method stub
		return salesDao.getAllSalesIn();
	}

	public Vector<Sales> getAllSalesBack() {
		// TODO Auto-generated method stub
		return salesDao.getAllSalesBack();
	}

	public Vector<Sales> getAllSalesInByTime(String begin, String end) {
		// TODO Auto-generated method stub
		return salesDao.getAllSalesInByTime(begin, end);
	}

	public Vector<Sales> getAllSalesBackByTime(String begin, String end) {
		// TODO Auto-generated method stub
		return salesDao.getAllSalesBackByTime(begin, end);
	}

	

}
