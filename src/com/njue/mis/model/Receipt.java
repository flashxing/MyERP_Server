package com.njue.mis.model;

import java.io.Serializable;

public class Receipt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6002084433560002158L;
	public String id;
	public String customerId;
	public Double money;
	public String time;
	public String operator;
	public String comment;
	public Receipt(){
		
	}
	public Receipt(String id, String customerId, Double money, String time, String operator, String comment){
		this.id = id;
		this.customerId = customerId;
		this.money = money;
		this.time = time;
		this.operator = operator;
		this.comment = comment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}