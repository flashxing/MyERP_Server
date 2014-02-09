package com.njue.mis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisMounting implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4073532079496302729L;
	private String id;
	private String time;
	private String operator;
	private int shId;
	private List<StockItem> outGoodsList;
	private List<StockItem> inGoodsList;
	
	public DisMounting(){
		this.id = "";
		this.time = "";
		this.operator = "";
		this.outGoodsList = new ArrayList<>();
		this.inGoodsList = new ArrayList<>();
	}
	
	public DisMounting(String id, String time, String operator, int shId,
			List<StockItem> outGoodList, List<StockItem> inGoodsList){
		this.id = id;
		this.time = time;
		this.operator = operator;
		this.shId = shId;
		this.outGoodsList = outGoodList;
		this.inGoodsList = inGoodsList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<StockItem> getOutGoodsList() {
		return outGoodsList;
	}

	public void setOutGoodsList(List<StockItem> outGoodsList) {
		this.outGoodsList = outGoodsList;
	}

	public List<StockItem> getInGoodsList() {
		return inGoodsList;
	}

	public void setInGoodsList(List<StockItem> inGoodsList) {
		this.inGoodsList = inGoodsList;
	}
	
	public int getShId() {
		return shId;
	}

	public void setShId(int shId) {
		this.shId = shId;
	}

	public DisMounting clone(){
		DisMounting disMounting = new DisMounting(id, time, operator, shId, outGoodsList, inGoodsList);		
		disMounting.outGoodsList = new ArrayList<>();
		for(StockItem stockItem: this.outGoodsList){
			disMounting.outGoodsList.add(stockItem);
		}
		disMounting.inGoodsList = new ArrayList<>();
		for(StockItem stockItem: this.inGoodsList){
			disMounting.inGoodsList.add(stockItem);
		}
		return disMounting;
	}
	
	
	
}
