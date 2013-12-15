package com.njue.mis.dao;

import java.util.ArrayList;
import java.util.List;

import com.njue.mis.model.CustomerMoney;

public class CustomerMoneyDao extends CommonObjectDao{
	public String add(CustomerMoney customerMoney){
		Object obj = super.get(CustomerMoney.class, customerMoney.getCustomerId());
		if(obj == null){
			return (String) super.save(customerMoney);
		}else{
			CustomerMoney tmp = (CustomerMoney) obj;
			tmp.setGive(customerMoney.getGive()+tmp.getGive());
			tmp.setReceive(customerMoney.getReceive()+tmp.getReceive());
			if(super.update(tmp, tmp.getCustomerId())){
				return tmp.getCustomerId();
			}else{
				return null;
			}
		}
	}
	
	public boolean delete(CustomerMoney customerMoney){
		Object object = super.get(CustomerMoney.class, customerMoney.getCustomerId());
		if(object == null){
			return false;
		}else{
			CustomerMoney tmp = (CustomerMoney) object;
			tmp.setGive(tmp.getGive()-customerMoney.getGive());
			tmp.setReceive(tmp.getReceive()-customerMoney.getReceive());
			return super.update(tmp, tmp.getCustomerId());
		}
	}
	
	public CustomerMoney get(String customerId){
		Object obj = super.get(CustomerMoney.class, customerId);
		return obj == null?null:(CustomerMoney) obj;
	}
	
	public List<CustomerMoney> get(String[] customerIds){
		List<CustomerMoney> list = new ArrayList<>();
		for(String id: customerIds){
			CustomerMoney obj = get(id);
			list.add(obj);
		}
		return list;
	}
}
