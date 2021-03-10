package com.springcss.customer.client;

import java.io.Serializable;

public class AccountMapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
    private String accountCode;    
    private String accountName;    
        
	public AccountMapper() {
		super();
	}
	
	public AccountMapper(String id, String accountCode, String accountName) {
		super();
		this.id = id;
		this.accountCode = accountCode;
		this.accountName = accountName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}    
}