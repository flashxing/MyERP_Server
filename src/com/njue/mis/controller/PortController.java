package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Vector;

import com.njue.mis.interfaces.PortControllerInterface;
import com.njue.mis.model.PortBack;
import com.njue.mis.model.PortIn;
import com.njue.mis.services.PortService;

public class PortController extends UnicastRemoteObject implements PortControllerInterface{

	private PortService portService = new PortService(); 
	public PortController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String addPortIn(PortIn portIn) {
		// TODO Auto-generated method stub
		return portService.addPortIn(portIn);
	}

	@Override
	public Vector<PortIn> getAllPortIn() {
		// TODO Auto-generated method stub
		return portService.getAllPortIn();
	}

	@Override
	public Vector<PortIn> searchPortIn(String field, String value) {
		// TODO Auto-generated method stub
		return portService.searchPortIn(field,value);
	}

	@Override
	public Vector<PortIn> searchPortInByTime(String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return portService.searchPortInByTime(beginTime,endTime);
	}

	@Override
	public PortIn getPortIn(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return portService.getPortIn(id);
	}

	@Override
	public String addPortBack(PortBack portBack) throws RemoteException {
		// TODO Auto-generated method stub
		return portService.addPortBack(portBack);
	}

	@Override
	public Vector<PortBack> getAllPortBack() throws RemoteException {
		// TODO Auto-generated method stub
		return portService.getAllPortBack();
	}

	@Override
	public Vector<PortBack> searchPortBackByTime(String beginTime,
			String endTime) throws RemoteException {
		// TODO Auto-generated method stub
		return portService.searchPortBackByTime(beginTime, endTime);
	}
	@Override
	public List<List<PortIn>> searchPortInByTimeAndGoods(String beginTime,
			String endTime, String[] goodsIds) throws RemoteException{
		return portService.searchPortInByTimeAndGoods(beginTime, endTime, goodsIds);
	}

}
