/**
 * 供应商类
 */

package com.njue.mis.model;

public class Provider extends Person
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8287194707489704142L;

	public Provider()
	{
		super();
	}

	public Provider(String id, String providerName, String zip, String address,
			String telephone, String fax, String connectionPerson,
			String phone, String email, String bank, String account, CustomerMoney customerMoney)
	{
		super(id, providerName, zip, address, telephone, fax, connectionPerson, phone,
				email, bank, account, customerMoney);
	}

	public Provider(String id, String name, String zip, String address,
			String telephone, String fax, String connectionPerson,
			String phone, String email, String bank, String account,
			int available, CustomerMoney customerMoney)
	{
		super(id, name, zip, address, telephone, fax, connectionPerson, phone, email,
				bank, account, available,0, customerMoney);
		// TODO Auto-generated constructor stub
	}

}
