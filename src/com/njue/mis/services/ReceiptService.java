package com.njue.mis.services;

import java.util.List;

import com.njue.mis.dao.ReceiptDao;
import com.njue.mis.model.CardItem;
import com.njue.mis.model.CustomerMoney;
import com.njue.mis.model.Receipt;
import com.njue.mis.model.ReceiptIn;
import com.njue.mis.model.ReceiptOut;
import com.njue.mis.server.Server;

public class ReceiptService {
	private ReceiptDao receiptDao;
	private CustomerMoneyService customerMoneyService;
	private CardItemService cardItemService;
	public ReceiptService(){
		receiptDao = new ReceiptDao();
		customerMoneyService = new CustomerMoneyService();
		cardItemService = new CardItemService();
	}
	
	public String addReceipt(Receipt receipt){
		String result = receiptDao.add(receipt);
		//roll back
		if(result == null){
			Server.logger.warn("Failed to add the receipt into dababase "+receipt.getId());
			return null;
		}
		CustomerMoney customerMoney =null;
		if(receipt instanceof ReceiptIn){
			customerMoney = new CustomerMoney(receipt.getCustomerId(), 0, -receipt.getMoney());
			if(customerMoneyService.add(customerMoney)==null){
				Server.logger.warn("Failed to delete the money of the customer "+receipt.getCustomerId()+" to give "+receipt.getMoney());
				receiptDao.delete(receipt);
				return null;
			}
			int number = 0;
			for(number=0; number<receipt.getReceiptItemDetailList().size(); ++number){
				if(!cardItemService.updateCardItem(receipt.getReceiptItemDetailList().get(number).getCardName(), receipt.getReceiptItemDetailList().get(number).getMoney())){
					Server.logger.warn("Faild to update the money for receipt "+number+" card name is"+receipt.getReceiptItemDetailList().get(number).getCardName());
					break;
				}
			}
			if(number<receipt.getReceiptItemDetailList().size()){
				Server.logger.warn("Failed to update the money for receipt "+receipt.getId());	
				customerMoneyService.delete(customerMoney);
				for(int i=0; i<number; ++i){
					cardItemService.updateCardItem(receipt.getReceiptItemDetailList().get(number).getCardName(), -receipt.getReceiptItemDetailList().get(number).getMoney());
				}
				return null;
			}
		}else {
			customerMoney = new CustomerMoney(receipt.getCustomerId(), -receipt.getMoney(), 0);
			if(customerMoneyService.add(customerMoney)==null){
				Server.logger.warn("Failed to delete the money of the customer "+receipt.getCustomerId()+" to receive "+receipt.getMoney());
				receiptDao.delete(receipt);
				return null;
			}
			int number = 0;
			for(number=0; number<receipt.getReceiptItemDetailList().size(); ++number){
				if(!cardItemService.updateCardItem(receipt.getReceiptItemDetailList().get(number).getCardName(), -receipt.getReceiptItemDetailList().get(number).getMoney())){
					Server.logger.warn("Faild to update the money for receipt "+number+" card name is"+receipt.getReceiptItemDetailList().get(number).getCardName());
					break;
				}
			}
			if(number<receipt.getReceiptItemDetailList().size()){
				Server.logger.warn("Failed to update the money for receipt "+receipt.getId());	
				customerMoneyService.delete(customerMoney);
				for(int i=0; i<number; ++i){
					cardItemService.updateCardItem(receipt.getReceiptItemDetailList().get(number).getCardName(), receipt.getReceiptItemDetailList().get(number).getMoney());
				}
				receiptDao.delete(receipt);
				return null;
			}
		}
		return result;
	}
	
	public boolean deleteReceipt(Receipt receipt){
		return receiptDao.delete(receipt);
	}
	
	public boolean updateReceipt(Receipt receipt){
		return receiptDao.update(receipt);
	}
	
	public ReceiptIn getReceiptIn(String id){
		return receiptDao.getReceiptIn(id);
	}
	
	public ReceiptOut getReceiptOut(String id){
		return receiptDao.getReceiptOut(id);
	}
	
	public List<ReceiptIn> getAllReceiptIn(){
		return receiptDao.getAllReceiptIn();
	}
	
	public List<ReceiptOut> getAllReceiptOut(){
		return receiptDao.getAllReceiptOut();
	}
	
	public List<ReceiptIn> getAllReceiptInByTime(String begin, String end){
		return receiptDao.getReceiptInByTime(begin, end);
	}
	
	public List<ReceiptOut> getAllReceiptOutByTime(String begin, String end){
		return receiptDao.getReceiptOutByTime(begin, end);
	}
}
