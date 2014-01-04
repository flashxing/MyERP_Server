/**
 * �˻���
 */

package com.njue.mis.model;

import java.util.ArrayList;
import java.util.List;


public class SalesBack extends Sales
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8666688041430343913L;
	public SalesBack()
	{
		super();
	}

	public SalesBack(String id, String customerId, String goodsId,
			int number, double price, String time,
			String operatePerson, String comment,double discount, double totalPrice,
			Double decreasePrice, int shId, int isPublished, String salesMan,
			List<SalesGoodsItem> goodsItemsList)
	{
		super(id, customerId, goodsId, number, price, time, operatePerson,
				comment, discount, totalPrice, decreasePrice, shId, isPublished, salesMan, goodsItemsList);
	}
	public SalesBack clone(){
		List<SalesGoodsItem> list = new ArrayList<SalesGoodsItem>();
		for(SalesGoodsItem item: goodsItemsList){
			list.add(item);
		}
		return new SalesBack(id, customerId, goodsId,
			number, price, time, operatePerson,
			comment, discount, totalPrice, decreasePrice, shId, isPublished, salesMan, list);
	}

}
