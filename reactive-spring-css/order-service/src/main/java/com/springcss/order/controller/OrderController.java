//package com.springcss.order.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.springcss.order.domain.Order;
//import com.springcss.order.service.OrderService;
//
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping(value="orders")
//public class OrderController {
//  
//	@Autowired
//	private OrderService orderService;    	
//	
//	@GetMapping(value = "/{orderNumber}")
//    public Mono<Order> getOrderByOrderNumber(@PathVariable String orderNumber) {	
//		
//		Mono<Order> order = orderService.getOrderByOrderNumber(orderNumber);
//		
//    	return order;
//    }
//}
