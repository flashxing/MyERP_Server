package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.njue.mis.interfaces.SetupControllerInterface;
import com.njue.mis.services.SetupService;

public class SetupController extends UnicastRemoteObject implements SetupControllerInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SetupService setupService;
	public SetupController() throws RemoteException {
		super();
		setupService = new SetupService();
	}

	@Override
	public boolean setUp() throws RemoteException {
		// TODO Auto-generated method stub
		return setupService.setUp();
	}

}
