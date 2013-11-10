package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import com.njue.mis.interfaces.OperatorControllerInterface;
import com.njue.mis.model.Operator;
import com.njue.mis.services.OperatorServices;

public class OperatorController extends UnicastRemoteObject implements OperatorControllerInterface 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1996024796836421977L;
	private OperatorServices operatorService = new OperatorServices();
	public OperatorController() throws RemoteException
	{
		super();
	}
	@Override
	public boolean addOperator(Operator operator)
	{
		return operatorService.addOperator(operator);
	}

	public boolean deleteOperator(String username)
	{
		return operatorService.deleteOperator(username);
	}

	public boolean loginCheck(String username, String password)
	{
		return operatorService.loginCheck(username, password);
	}

	public boolean modifyPassword(String username, String password)
	{
		return operatorService.modifyPassword(username, password);
	}

	public String getPower(String username) 
	{
		return operatorService.getPower(username);
	}

	
	public String getPassword(String username)
	{
		return operatorService.getPassword(username);
	}

	
	public boolean isExited(String username)
	{
		return operatorService.isExited(username);
	}

	
	public Vector<Operator> getOperator(String type)
	{
		return operatorService.getOperator(type);
	}

	
	public Operator getOperatorInfo(String userName)
	{
		return operatorService.getOperatorInfo(userName);
	}

}
