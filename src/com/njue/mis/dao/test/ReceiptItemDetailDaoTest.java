package com.njue.mis.dao.test;

import junit.framework.TestCase;

import com.njue.mis.dao.ReceiptItemDao;
import com.njue.mis.model.ReceiptItem;

public class ReceiptItemDetailDaoTest extends TestCase{
	private ReceiptItemDao dao = new ReceiptItemDao();
	public void setUp(){
		
	}
	public void tearDown(){
		
	}
	public void testVoid(){
		ReceiptItem receiptItem = new ReceiptItem();
		dao.add(receiptItem);
	}
}
