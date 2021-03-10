package com.springcss.account.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account {
	@Id
	private String id;
    private String accountCode;    
    private String accountName;    
        
	public Account() {
		super();
	}
	
	public Account(String id, String accountCode, String accountName) {
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
