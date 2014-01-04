/**
 * ��Ʒ��
 */

package com.njue.mis.model;

import java.io.Serializable;

public class Goods implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6982905753217184200L;
	private String id;  //��Ʒ���
	private String goodsName;  //��Ʒ����
	private String producePlace;  //��Ʒ����
	private String size;  //���
	private String _package; //��װ
	private String productCode;  //��������
	private String promitCode;  //��ת�ĺ�
	private double price;  //�����۸�
	private double salesPrice; //���ۼ۸�
	private String description;  //����
	private String providerId;  //��Ӧ�̱��
	private int cateId;  //״̬ ��0�������
	private double lastSalePrice;	//���һ���ۼ�
	private double lastStockPrice; //���һ�ν���
	private int goodsNum;	//��Ʒ����
	public Goods()
	{
		super();
	}
	public Goods(Goods good){
		this.id = good.getId();
		this.goodsName = good.getGoodsName();
		this.producePlace = good.getProducePlace();
		this.size = good.getSize();
		this._package = good.get_package();
		this.productCode = good.getProductCode();
		this.promitCode = good.getPromitCode();
		this.price = good.getPrice();
		this.salesPrice = good.salesPrice;
		this.description = good.getDescription();
		this.providerId = good.getProviderId();
		this.cateId = good.getCateId();
		this.goodsNum = good.goodsNum;
		this.lastSalePrice = good.lastSalePrice;
		this.lastStockPrice = good.lastStockPrice;
	}
	public void update(Goods good){
		this.id = good.getId();
		this.goodsName = good.getGoodsName();
		this.producePlace = good.getProducePlace();
		this.size = good.getSize();
		this._package = good.get_package();
		this.productCode = good.getProductCode();
		this.promitCode = good.getPromitCode();
		this.price = good.getPrice();
		this.salesPrice = good.salesPrice;
		this.description = good.getDescription();
		this.providerId = good.getProviderId();
		this.cateId = good.getCateId();
		this.goodsNum = good.goodsNum;
		this.lastSalePrice = good.lastSalePrice;
		this.lastStockPrice = good.lastStockPrice;
	}
	public Goods(String id, String goodsName, String producePlace, String size,
			String _package, String productCode, String promitCode,
			String description,double price, double salesPrice, String providerId)
	{
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.producePlace = producePlace;
		this.size = size;
		this._package = _package;
		this.productCode = productCode;
		this.promitCode = promitCode;
		this.price = price;
		this.salesPrice = salesPrice;
		this.description = description;
		this.providerId = providerId;
	}
	
	public Goods(String id, String goodsName, String producePlace, String size,
			String _package, String productCode, String promitCode,
			String description,double price, double salesPrice, String providerId,
			int cateId)
	{
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.producePlace = producePlace;
		this.size = size;
		this._package = _package;
		this.productCode = productCode;
		this.promitCode = promitCode;
		this.price = price;
		this.salesPrice = salesPrice;
		this.description = description;
		this.providerId = providerId;
		this.cateId = cateId;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getGoodsName()
	{
		return goodsName;
	}
	public void setGoodsName(String goodsName)
	{
		this.goodsName = goodsName;
	}
	public String getProducePlace()
	{
		return producePlace;
	}
	public void setProducePlace(String producePlace)
	{
		this.producePlace = producePlace;
	}
	public String getSize()
	{
		return size;
	}
	public void setSize(String size)
	{
		this.size = size;
	}
	public String get_package()
	{
		return _package;
	}
	public void set_package(String _package)
	{
		this._package = _package;
	}
	public String getProductCode()
	{
		return productCode;
	}
	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}
	public String getPromitCode()
	{
		return promitCode;
	}
	public void setPromitCode(String promitCode)
	{
		this.promitCode = promitCode;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getProviderId()
	{
		return providerId;
	}
	public void setProviderId(String providerId)
	{
		this.providerId = providerId;
	}
	public int getCateId()
	{
		return cateId;
	}
	public void setCateId(int cateId)
	{
		this.cateId = cateId;
	}
	public double getLastSalePrice() {
		return lastSalePrice;
	}
	public void setLastSalePrice(double lastSalePrice) {
		this.lastSalePrice = lastSalePrice;
	}
	public double getLastStockPrice() {
		return lastStockPrice;
	}
	public void setLastStockPrice(double lastStockPrice) {
		this.lastStockPrice = lastStockPrice;
	}
	public int getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}
	public double getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}
	
}
