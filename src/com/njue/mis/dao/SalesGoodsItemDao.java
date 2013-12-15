package com.njue.mis.dao;

import java.util.List;

import com.njue.mis.model.SalesGoodsItem;

public class SalesGoodsItemDao extends CommonObjectDao{
	@SuppressWarnings("unchecked")
	public List<SalesGoodsItem> searchPortInByTimeAndGoods(String portId, String goodsId){
		List<SalesGoodsItem> list = super.getAll("from SaleGoodsItem where goods_id='"+goodsId+"' and port_id='"+portId+"'");
		return list;
	}
}
