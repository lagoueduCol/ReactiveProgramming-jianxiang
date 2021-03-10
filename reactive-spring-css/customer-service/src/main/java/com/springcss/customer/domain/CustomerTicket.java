package com.springcss.customer.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;


@Document
public class CustomerTicket {
	@Id
	private String id;
    private String accountId;    
    private String orderNumber;
    private String description;
    private Date createTime;
    
        
	public CustomerTicket() {
		super();
	}

	public CustomerTicket(String accountId, String orderNumber) {
		super();
		
		Assert.notNull(accountId, "Account Id must not be null");
		Assert.notNull(orderNumber, "Order Number must not be null");

		this.accountId = accountId;
		this.orderNumber = orderNumber;
	}
	
	public CustomerTicket(String accountId, String orderNumber, String description, Date createTime) {
		
		this(accountId, orderNumber);
		
		this.description = description;
		this.createTime = createTime;
	}
	
	public CustomerTicket(String id, String accountId, String orderNumber, String description, Date createTime) {
				
		this(accountId, orderNumber);
		
		this.id = id;
		this.description = description;
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}
