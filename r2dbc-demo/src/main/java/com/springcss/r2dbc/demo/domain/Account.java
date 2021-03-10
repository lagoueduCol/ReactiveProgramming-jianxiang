package com.springcss.r2dbc.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("account")
public class Account {
	@Id
	private Long id;
    private String accountCode;    
    private String accountName;    
        
	public Account() {
		super();
	}
	
	public Account(Long id, String accountCode, String accountName) {
		super();
		this.id = id;
		this.accountCode = accountCode;
		this.accountName = accountName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
