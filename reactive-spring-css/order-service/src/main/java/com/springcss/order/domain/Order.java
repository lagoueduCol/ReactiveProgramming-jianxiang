package com.springcss.order.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order{

	@Id
	private String id;	
	private String orderNumber;
	private String deliveryAddress;
	private String goods;		
	
	public Order() {
		super();
	}

	public Order(String id, String orderNumber, String deliveryAddress, String goods) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.deliveryAddress = deliveryAddress;
		this.goods = goods;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

}
