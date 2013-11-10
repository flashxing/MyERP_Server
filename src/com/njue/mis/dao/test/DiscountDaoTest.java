package com.njue.mis.dao.test;

import com.njue.mis.dao.DiscountDao;
import com.njue.mis.model.Discount;

import junit.framework.TestCase;

public class DiscountDaoTest extends TestCase{
	private Discount discount;
	private DiscountDao discountDao;
	public void setUp(){
		discountDao = new DiscountDao();
		discount = new Discount();
		discount.setCustomerId("123");
		discount.setDiscount(0.8);
		discount.getGoodsDiscount().put("abc", 0.75);
		discount.getGoodsDiscount().put("acb", 0.68);
	}
	public void tearDown(){
		
	}
	
	public void testVoid(){
		discountDao.delete(discount.getCustomerId());
		String id = discountDao.save(discount);
		assertEquals(id,"123");
		Discount dis = discountDao.get(id);
		assertNotNull(dis);
		assertEquals(dis.getGoodsDiscount().size(),2);
		assertEquals(dis.getGoodsDiscount().get("acb"),0.68);
		dis.getGoodsDiscount().put("acc", 0.9);
		dis.getGoodsDiscount().put("abb", 0.5);
		dis.getGoodsDiscount().remove("acb");
		assertEquals(dis.getGoodsDiscount().size(),3);
		assertTrue(discountDao.update(dis));
		dis = discountDao.get(dis.getCustomerId());
		assertEquals(dis.getGoodsDiscount().size(),3);
		assertEquals(discountDao.delete(id),true);
		dis = discountDao.get(id);
		assertNull(dis);
	}
}
