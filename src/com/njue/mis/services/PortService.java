package com.njue.mis.services;

import java.util.List;
import java.util.Vector;

import com.njue.mis.common.CommonUtil;
import com.njue.mis.dao.PortDAO;
import com.njue.mis.model.CustomerMoney;
import com.njue.mis.model.Goods;
import com.njue.mis.model.GoodsItem;
import com.njue.mis.model.Port;
import com.njue.mis.model.PortBack;
import com.njue.mis.model.PortIn;
import com.njue.mis.model.Stock;
import com.njue.mis.server.Server;

public class PortService {
	private PortDAO portDao = new PortDAO();
	private StockService stockService = new StockService();
	private GoodsService goodsService = new GoodsService();
	private CustomerMoneyService customerMoneyService = new CustomerMoneyService();
	
	public String addPortBack(PortBack portBack){
		int time = CommonUtil.convertDateToInt(CommonUtil.getMyCurrentTime());
		if(time == 0){
			Server.logger.warn("Failed to save the Port for time is error"+portBack.getTime());
			return null;
		}
		String result = portDao.addPort(portBack);
		if(result == null){
			Server.logger.warn("Failed to save the PortIn:"+portBack);
			return null;
		}
		List<GoodsItem> list = portBack.getGoodsItemList();
		for(GoodsItem item:list){
			String goodsId = item.getGoodsId();
			int shId = portBack.getStoreHouseId();
			int number = item.getNumber();
			Stock stock = new Stock(goodsId,shId,number,time, item.getUnitPrice());
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
		}
		double totalMoney = portBack.getPrice();
		CustomerMoney customerMoney = new CustomerMoney(portBack.getCustomerId(), 0, totalMoney);
		if(customerMoneyService.add(customerMoney)== null){
			Server.logger.warn("Failed to add the customer money for port id:"+portBack.getId());
			return null;
		}
		return result;
	}
	public String addPortIn(PortIn portIn){		
		int time = CommonUtil.convertDateToInt(CommonUtil.getMyCurrentTime());
		if(time == 0){
			Server.logger.warn("Failed to save the Port for time is error"+portIn.getTime());
			return null;
		}
		String result = portDao.addPort(portIn);
		System.out.println(portIn);
		if(result == null){
			Server.logger.warn("Failed to save the PortIn:"+portIn);
			return null;
		}
		List<GoodsItem> list = portIn.getGoodsItemList();
		if(list == null || list.size()<=0){
			Server.logger.warn("Failed to get the item list");
			portDao.deletePort(portIn);
			return null;
		}
		for(GoodsItem item:list){
			String goodsId = item.getGoodsId();
			int shId = portIn.getStoreHouseId();
			int number = item.getNumber();
			Stock stock = new Stock(goodsId,shId,number,time,item.getUnitPrice());
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
			goods.setLastStockPrice(item.getUnitPrice());
			if(!goodsService.updateGoods(goods)){
				Server.logger.warn("Failed to update the goods after PortIn");
				stockService.deleteStock(stock);
				portDao.deletePort(portIn);
				return null;
			}
		}
		double totalMoney = portIn.getPrice();
		CustomerMoney customerMoney = new CustomerMoney(portIn.getCustomerId(), totalMoney,0);
		if(customerMoneyService.add(customerMoney)== null){
			Server.logger.warn("Failed to add the customer money for port id:"+portIn.getId());
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
	public List<List<PortIn>> searchPortInByTimeAndGoods(String beginTime,
			String endTime, String[] goodsIds){
//		List<List<PortIn>> resultList = new ArrayList<List<PortIn>>();
//		for(String goodsId:goodsIds){
//			List<PortIn> result = portDao.
//		}
		return null;
	}
	public boolean updatePort(Port port) {
		// TODO Auto-generated method stub
		return portDao.update(port);
	}
	public boolean deletePort(Port port) {			
		int time = CommonUtil.convertDateToInt(CommonUtil.getMyCurrentTime());
		if(time == 0){
			Server.logger.warn("Failed to save the Port for time is error"+port.getTime());
			return false;
		}
		List<GoodsItem> list = port.getGoodsItemList();
		if(port instanceof PortIn){
			for(GoodsItem item:list){
				String goodsId = item.getGoodsId();
				int shId = port.getStoreHouseId();
				int number = item.getNumber();
				Stock stock = new Stock(goodsId,shId,number,time, item.getUnitPrice());
				stock = stockService.getStock(shId, goodsId);
				if(stock.getNumber() < number){
					return false;
				}
			}
			double totalMoney = port.getPrice();
			CustomerMoney customerMoney = new CustomerMoney(port.getCustomerId(), -totalMoney, 0);
			if(customerMoneyService.add(customerMoney)== null){
				Server.logger.warn("Failed to add the customer money for port id:"+port.getId());
				return false;
			}
			boolean result = portDao.deletePort(port);
			if(!result){
				Server.logger.warn("Failed to save the PortIn:"+port);
				return false;
			}
			for(GoodsItem item:list){
				String goodsId = item.getGoodsId();
				int shId = port.getStoreHouseId();
				int number = item.getNumber();
				Stock stock = new Stock(goodsId,shId,number,time, item.getUnitPrice());
				if(!stockService.deleteStock(stock)){
					Server.logger.warn("Failed to delete stock:"+stock);
					portDao.deletePort(port);
					return false;
				}
				Goods goods = goodsService.getGoods(goodsId);
				if (null == goods){
					Server.logger.warn("Failed to get goods:"+goodsId);
					stockService.addStock(stock);
					portDao.deletePort(port);
					return false;
				}
				goods.setGoodsNum(goods.getGoodsNum()-number);
				if(!goodsService.updateGoods(goods)){
					Server.logger.warn("Failed to update the goods after PortBack");
					stockService.addStock(stock);
					portDao.deletePort(port);
					return false;
				}
			}
			return result;
		}else if(port instanceof PortBack){
			double totalMoney = port.getPrice();
			CustomerMoney customerMoney = new CustomerMoney(port.getCustomerId(), 0, -totalMoney);
			if(customerMoneyService.add(customerMoney)== null){
				Server.logger.warn("Failed to add the customer money for port id:"+port.getId());
				return false;
			}
			boolean result = portDao.deletePort(port);
			if(!result){
				Server.logger.warn("Failed to save the PortIn:"+port);
				return false;
			}
			for(GoodsItem item:list){
				String goodsId = item.getGoodsId();
				int shId = port.getStoreHouseId();
				int number = item.getNumber();
				Stock stock = new Stock(goodsId,shId,number,time, item.getUnitPrice());
				if(stockService.addStock(stock)<0){
					Server.logger.warn("Failed to delete stock:"+stock);
					portDao.addPort(port);
					return false;
				}
				Goods goods = goodsService.getGoods(goodsId);
				if (null == goods){
					Server.logger.warn("Failed to get goods:"+goodsId);
					stockService.deleteStock(stock);
					portDao.addPort(port);
					return false;
				}
				goods.setGoodsNum(goods.getGoodsNum()+number);
				if(!goodsService.updateGoods(goods)){
					Server.logger.warn("Failed to update the goods after PortBack");
					stockService.deleteStock(stock);
					portDao.addPort(port);
					return false;
				}
			}
		}
		return false;
	}
}
