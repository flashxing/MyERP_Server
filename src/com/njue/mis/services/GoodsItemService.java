package com.njue.mis.services;

import java.util.ArrayList;
import java.util.List;

import com.njue.mis.model.GoodsItem;
import com.njue.mis.model.PortIn;
import com.njue.mis.model.Sales;
import com.njue.mis.model.SalesGoodsItem;

public class GoodsItemService {
	private PortService portService;
	private SalesService salesService;
	public GoodsItemService(){
		portService = new PortService();
		salesService = new SalesService();
	}
	
	public List<List<SalesGoodsItem>> getSalesGoodsItemByTimeAndGoods(String beginTime,
			String endTime, String[] goodsIds){
		List<List<SalesGoodsItem>> resultList = new ArrayList<>();
		List<Sales> salesInList = salesService.getAllSalesInByTime(beginTime, endTime);
		for(String goodsId:goodsIds){
			List<SalesGoodsItem> list = new ArrayList<>();
			if(salesInList != null){
				for(Sales salesIn:salesInList){
					List<SalesGoodsItem> tmp = salesIn.getGoodsItemsList();
					if(tmp != null){
						for(SalesGoodsItem salesGoodsItem:tmp){
							if(salesGoodsItem.getGoodsId().equals(goodsId)){
								list.add(salesGoodsItem);
							}
						}
					}
				}
			}
			resultList.add(list);
		}
		return resultList;
	}
	
	public List<List<GoodsItem>> getPortInGoodsItemByTimeAndGoods(String beginTime,
			String endTime, String[] goodsIds){
		List<List<GoodsItem>> resultList = new ArrayList<>();
		List<PortIn> portInList = portService.searchPortInByTime(beginTime, endTime);
		for(String goodsId:goodsIds){
			List<GoodsItem> list = new ArrayList<>();
			if(portInList != null){
				for(PortIn portIn:portInList){
					List<GoodsItem> tmp = portIn.getGoodsItemList();
					if(tmp != null){
						for(GoodsItem goodsItem:tmp){
							if(goodsItem.getGoodsId().equals(goodsId)){
								list.add(goodsItem);
							}
						}
					}
				}
			}
			resultList.add(list);
		}
		return resultList;
	}
}
