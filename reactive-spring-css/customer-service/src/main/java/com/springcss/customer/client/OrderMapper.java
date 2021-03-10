package com.springcss.customer.client;

import java.io.Serializable;

public class OrderMapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;	
	private String orderNumber;
	private String deliveryAddress;
	private String goods;		
	
	public OrderMapper() {
		super();
	}

	public OrderMapper(String id, String orderNumber, String deliveryAddress, String goods) {
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