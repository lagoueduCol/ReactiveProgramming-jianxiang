package com.springcss.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcss.order.domain.Order;
import com.springcss.order.repository.ReactiveOrderRepository;

import reactor.core.publisher.Mono;

@Service
public class OrderService {

	@Autowired
	private ReactiveOrderRepository orderRepository;	
	
	public Mono<Order> getOrderByOrderNumber(String orderNumber) {
		return orderRepository.getOrderByOrderNumber(orderNumber);
	}
}

