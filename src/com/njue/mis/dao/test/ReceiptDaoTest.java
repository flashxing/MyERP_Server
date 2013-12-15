package com.njue.mis.dao.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.njue.mis.dao.CommonObjectDao;
import com.njue.mis.dao.ReceiptDao;
import com.njue.mis.dao.ReceiptItemDao;
import com.njue.mis.model.Receipt;
import com.njue.mis.model.ReceiptIn;
import com.njue.mis.model.ReceiptItem;
import com.njue.mis.model.ReceiptItemDetail;

public class ReceiptDaoTest  extends TestCase{
	private ReceiptDao dao = new ReceiptDao();
	private CommonObjectDao objDao = new CommonObjectDao();
	public void setUp(){
		
	}
	public void tearDown(){
		
	}
	public void testVoid(){
		Receipt receipt = new ReceiptIn();
		receipt.setCustomerId("123");
		receipt.setId("receiptId2");
		receipt.setComment("");
		receipt.setMoney(123.0);
		receipt.setOperator("ope");
		receipt.setTime("2013-11-12 22:55:08");
		ReceiptItemDetail item = new ReceiptItemDetail("receiptId2", 0, "receiptItem", 11.0, "comment");
		List<ReceiptItemDetail> list = new ArrayList<ReceiptItemDetail>();
		list.add(item);
		receipt.setReceiptItemDetailList(list);
		dao.add(receipt);
	}
}
