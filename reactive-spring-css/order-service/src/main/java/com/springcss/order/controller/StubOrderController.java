//package com.springcss.order.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.springcss.order.domain.Order;
//import com.springcss.order.service.StubOrderService;
//
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/orders")
//public class StubOrderController {
//
//	@Autowired
//	private StubOrderService orderService;
//
//	@GetMapping("")
//	public Flux<Order> getOrders() {
//		return this.orderService.getOrders();
//	}
//
//	@GetMapping("/{id}")
//	public Mono<Order> getOrderById(@PathVariable("id") final String id) {
//		return this.orderService.getOrderById(id);
//	}
//
//	@PostMapping("")
//	public Mono<Void> createOrder(@RequestBody final Mono<Order> order) {
//		return this.orderService.createOrUpdateOrder(order);
//	}
//
//	@DeleteMapping("/{id}")
//	public Mono<Order> delete(@PathVariable("id") final String id) {
//		return this.orderService.deleteOrder(id);
//	}
//}
