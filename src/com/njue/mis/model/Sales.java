/**
 * ������
 */

package com.njue.mis.model;

import java.io.Serializable;

public abstract class Sales implements Serializable
{
	public String id;  //���
	public String customerId;  //�ͻ����
	public String goodsId;  //��Ʒ���
	public int number;  //����
	public double price;  //�۸�
	public String time;  //ʱ��
	public String operatePerson; //����Ա  
	public String comment;  //ע��
	public double discount;
	public double totalPrice;
	public int shId;
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getShId() {
		return shId;
	}

	public void setShId(int shId) {
		this.shId = shId;
	}

	public Sales()
	{
		super();
	}

	public Sales(String id, String customerId, String goodsId, 
			int number, double price, String time, String operatePerson,
			String comment,double discount, double totalPrice, int shId)
	{
		super();
		this.id = id;
		this.customerId = customerId;
		this.goodsId = goodsId;
		this.number = number;
		this.price = price;
		this.time = time;
		this.operatePerson = operatePerson;
		this.comment = comment;
		this.discount = discount;
		this.totalPrice = totalPrice;
		this.shId = shId;
	}

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getCustomerId()
	{
		return customerId;
	}
	public void setCustomerId(String customerId)
	{
		this.customerId = customerId;
	}
	public String getGoodsId()
	{
		return goodsId;
	}
	public void setGoodsId(String goodsId)
	{
		this.goodsId = goodsId;
	}

	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public String getOperatePerson()
	{
		return operatePerson;
	}
	public void setOperatePerson(String operatePerson)
	{
		this.operatePerson = operatePerson;
	}
	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
}
