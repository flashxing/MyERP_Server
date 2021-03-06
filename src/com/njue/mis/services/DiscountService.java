package com.njue.mis.services;

import com.njue.mis.dao.DiscountDao;
import com.njue.mis.model.Discount;

public class DiscountService {
	private DiscountDao discountDao = new DiscountDao();
	public String add(Discount discount){
		return discountDao.save(discount);
	}
	
	public Boolean delete(String id){
		return discountDao.delete(id);
	}
	
	public Discount get(String id){
		Discount discount = discountDao.get(id);
		if(discount == null){
			return new Discount();
		}
		return discount;
	}
	
	public boolean update(Discount discount){
		return discountDao.update(discount);
	}
}
