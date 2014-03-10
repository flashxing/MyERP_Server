package com.njue.mis.services;

import java.util.List;
import java.util.Vector;

import com.njue.mis.common.CommonUtil;
import com.njue.mis.dao.SalesDao;
import com.njue.mis.model.CustomerMoney;
import com.njue.mis.model.Goods;
import com.njue.mis.model.Sales;
import com.njue.mis.model.SalesBack;
import com.njue.mis.model.SalesGoodsItem;
import com.njue.mis.model.SalesIn;
import com.njue.mis.model.Stock;
import com.njue.mis.server.Server;
import com.sun.istack.internal.FinalArrayList;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

public class SalesService {

	private SalesDao salesDao = new SalesDao();
	private StockService stockService = new StockService();
	private GoodsService goodsService = new GoodsService();
	private CustomerMoneyService customerMoneyService = new CustomerMoneyService();
	public String addSalesBack(SalesBack sales) {
		int time = CommonUtil.convertDateToInt(CommonUtil.getMyCurrentTime());
		if(time == 0){
			Server.logger.warn("Failed to save the Sales for time is error"+sales.getTime());
			return null;
		}		
		String result = salesDao.save(sales);
		if(result == null){
			Server.logger.warn("Failed to save the salesback:"+sales);
			return null;
		}
		List<SalesGoodsItem> list = sales.getGoodsItemsList();
		for(SalesGoodsItem item:list){
			String goodsId = item.getGoodsId();
			int shId = sales.getShId();
			int number = item.getNumber();
			Stock stock = new Stock(goodsId,shId,number,time,item.getUnitPrice());
			if(stockService.addStock(stock)<0){
				Server.logger.warn("Failed to add stock:"+stock+"for sales:"+sales.getId());
				salesDao.delete(sales);
				return null;
			}
			Goods goods = goodsService.getGoods(goodsId);
			if (goods == null){
				Server.logger.warn("Failed to get goods:"+goodsId+"for sales:"+sales.getId());
				stockService.deleteStock(stock);
				salesDao.delete(sales);
				return null;
			}
			goods.setGoodsNum(goods.getGoodsNum()+number);
			if(!goodsService.updateGoods(goods)){
				Server.logger.warn("Failed to update the goods after Sales"+"for sales:"+sales.getId());
				stockService.deleteStock(stock);
				salesDao.delete(sales);
				return null;
			}
		}
		double totalMoney = sales.getPrice();
		CustomerMoney customerMoney = new CustomerMoney(sales.getCustomerId(), totalMoney, 0);
		if(customerMoneyService.add(customerMoney)== null){
			Server.logger.warn("Failed to add the customer money for sales id:"+sales.getId());
			return null;
		}
		return result;
	}
	public String addSalesIn(SalesIn sales) {
		int time = CommonUtil.convertDateToInt(CommonUtil.getMyCurrentTime());
		if(time == 0){
			Server.logger.warn("Failed to save the Sales for time is error"+sales.getTime());
			return null;
		}
		String result = salesDao.save(sales);
		if(result == null){
			Server.logger.warn("Failed to save the sales:"+sales.getId());
			return null;
		}
		if(sales.getIsPublished() == 0){
			return result;
		}
		List<SalesGoodsItem> list = sales.getGoodsItemsList();
		for(SalesGoodsItem item:list){
			String goodsId = item.getGoodsId();
			int shId = sales.getShId();
			int number = item.getNumber();
			Stock stock = new Stock(goodsId,shId,number,time,item.getUnitPrice());
			if(!stockService.deleteStock(stock)){
				Server.logger.warn("Failed to delete stock:"+stock+"for sales:"+sales.getId());
				salesDao.delete(sales);
				return null;
			}
			Goods goods = goodsService.getGoods(goodsId);
			if (goods == null){
				Server.logger.warn("Failed to get goods:"+goodsId+"for sales:"+sales.getId());
				stockService.addStock(stock);
				salesDao.delete(sales);
				return null;
			}
			goods.setGoodsNum(goods.getGoodsNum()-number);
			goods.setLastSalePrice(item.getUnitPrice());
			if(!goodsService.updateGoods(goods)){
				Server.logger.warn("Failed to update the goods after Sales"+"for sales:"+sales.getId());
				stockService.addStock(stock);
				salesDao.delete(sales);
				return null;
			}
		}
		double totalMoney = sales.getPrice();
		CustomerMoney customerMoney = new CustomerMoney(sales.getCustomerId(), 0,totalMoney);
		if(customerMoneyService.add(customerMoney)== null){
			Server.logger.warn("Failed to add the customer money for sales id:"+sales.getId());
			return null;
		}
		return result;
	}
	
	public boolean setSalesToPublish(SalesIn sales){
		sales.setIsPublished(1);
		int time = CommonUtil.convertDateToInt(CommonUtil.getMyCurrentTime());
		if(time == 0){
			Server.logger.warn("Failed to save the Sales for time is error"+sales.getTime());
			return false;
		}
		List<SalesGoodsItem> list = sales.getGoodsItemsList();
		int i = 0;
		for(i=0; i<list.size(); ++i){
			SalesGoodsItem item = list.get(i);
			String goodsId = item.getGoodsId();
			int shId = sales.getShId();
			int number = item.getNumber();
			Stock stock = new Stock(goodsId,shId,number,time,item.getUnitPrice());
			if(!stockService.deleteStock(stock)){
				Server.logger.warn("Failed to delete stock:"+stock+"for sales:"+sales.getId());
				break;
			}
			Goods goods = goodsService.getGoods(goodsId);
			if (goods == null){
				Server.logger.warn("Failed to get goods:"+goodsId+"for sales:"+sales.getId());
				stockService.addStock(stock);
				break;
			}
			goods.setGoodsNum(goods.getGoodsNum()-number);
			goods.setLastSalePrice(item.getUnitPrice());
			if(!goodsService.updateGoods(goods)){
				Server.logger.warn("Failed to update the goods after Sales"+"for sales:"+sales.getId());
				stockService.addStock(stock);
				break;
			}
		}
		if(i < sales.getGoodsItemsList().size()){
			for(int j=0; j<i; ++j){
				SalesGoodsItem item = sales.getGoodsItemsList().get(j);
				String goodsId = item.getGoodsId();
				int shId = sales.getShId();
				int number = item.getNumber();
				Stock stock = new Stock(goodsId,shId,number,time,item.getUnitPrice());
				stockService.addStock(stock);
				Goods goods = goodsService.getGoods(goodsId);
				if (goods == null){
					Server.logger.warn("Failed to get goods:"+goodsId+"for sales:"+sales.getId());
				}
				goods.setGoodsNum(goods.getGoodsNum()+number);
				goods.setLastSalePrice(item.getUnitPrice());
				if(!goodsService.updateGoods(goods)){
					Server.logger.warn("Failed to update the goods after Sales"+"for sales:"+sales.getId());
				}	
			}
			return false;
		}
		double totalMoney = sales.getPrice();
		CustomerMoney customerMoney = new CustomerMoney(sales.getCustomerId(), 0,totalMoney);
		if(customerMoneyService.add(customerMoney)== null){
			Server.logger.warn("Failed to add the customer money for sales id:"+sales.getId());
			return false;
		}
		if(!salesDao.update(sales)){
			Server.logger.warn("Failed to save the sales:"+sales.getId());
			return false;
		}
		return true;
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
	public List<SalesIn> getAllSalesDraft(String begin, String end) {
		// TODO Auto-generated method stub
		return salesDao.getAllSalesDraft(begin, end);
	}
	public List<SalesIn> getAllSalesDraft() {
		// TODO Auto-generated method stub
		return salesDao.getAllSalesDraft();
	}
	public boolean updateSales(Sales sales) {
		// TODO Auto-generated method stub
		return salesDao.update(sales);
	}
	public boolean deleteSales(Sales sales) {
		if(sales.getIsPublished() ==0){
			return salesDao.delete(sales);
		}
		sales.setIsPublished(2);
		boolean result = salesDao.update(sales);
		if(!result){
			Server.logger.warn("Failed to save the salesback:"+sales);
			return false;
		}
		if(sales instanceof SalesIn){
			int time = CommonUtil.convertDateToInt(CommonUtil.getMyCurrentTime());
			if(time == 0){
				Server.logger.warn("Failed to save the Sales for time is error"+sales.getTime());
				return false;
			}		
			List<SalesGoodsItem> list = sales.getGoodsItemsList();
			for(SalesGoodsItem item:list){
				String goodsId = item.getGoodsId();
				int shId = sales.getShId();
				int number = item.getNumber();
				Stock stock = new Stock(goodsId,shId,number,time,item.getUnitPrice());
				if(stockService.addStock(stock)<0){
					Server.logger.warn("Failed to add stock:"+stock+"for sales:"+sales.getId());
					return false;
				}
				Goods goods = goodsService.getGoods(goodsId);
				if (goods == null){
					Server.logger.warn("Failed to get goods:"+goodsId+"for sales:"+sales.getId());
					stockService.deleteStock(stock);
					return false;
				}
				goods.setGoodsNum(goods.getGoodsNum()+number);
				if(!goodsService.updateGoods(goods)){
					Server.logger.warn("Failed to update the goods after Sales"+"for sales:"+sales.getId());
					stockService.deleteStock(stock);
					return false;
				}
			}
			double totalMoney = sales.getPrice();
			CustomerMoney customerMoney = new CustomerMoney(sales.getCustomerId(), 0, -totalMoney);
			if(customerMoneyService.add(customerMoney)== null){
				Server.logger.warn("Failed to add the customer money for sales id:"+sales.getId());
				return false;
			}
		}else if(sales instanceof SalesBack){
			
		}
		return true;
	}
	public List<Sales> searchSales(String begin, String end, 
			String customerId, String goodsId,
			String salesMan, int shId,
			String operator, String comment, int type){
		return salesDao.searchSales(begin, end, customerId, goodsId, salesMan, shId, operator, comment, type);
	}
}
