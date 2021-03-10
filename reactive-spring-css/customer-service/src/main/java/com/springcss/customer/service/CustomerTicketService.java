package com.springcss.customer.service;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcss.customer.client.AccountMapper;
import com.springcss.customer.client.OrderMapper;
import com.springcss.customer.client.ReactiveAccountClient;
import com.springcss.customer.client.ReactiveOrderClient;
import com.springcss.customer.domain.CustomerTicket;
import com.springcss.customer.repository.CustomerTicketRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerTicketService {

	@Autowired
	private CustomerTicketRepository customerTicketRepository;

	@Autowired
	private ReactiveOrderClient orderClient;
	
	@Autowired
	private ReactiveAccountClient accountClient;

	private static final Logger logger = LoggerFactory.getLogger(CustomerTicketService.class);

	private Mono<OrderMapper> getRemoteOrderByOrderNumber(String orderNumber) {

		return orderClient.getOrderByOrderNumber(orderNumber);
	}
	
	private Mono<AccountMapper> getRemoteAccountByAccountId(String accountId) {

		return accountClient.findAccountById(accountId);
		
//		return accountClient.getAccountFromRemote(accountId);
	}

	public Mono<CustomerTicket> generateCustomerTicket(String accountId, String orderNumber) {

		logger.debug("Generate customer ticket record with account: {} and order: {}", accountId, orderNumber);

		CustomerTicket customerTicket = new CustomerTicket();
		customerTicket.setId("C_" + UUID.randomUUID().toString());

		// 从远程account-service获取Account信息
		Mono<AccountMapper> accountMapper = getRemoteAccountByAccountId(accountId);
		
		// 从远程order-service中获取Order信息
		Mono<OrderMapper> orderMapper = getRemoteOrderByOrderNumber(orderNumber);
		
		Mono<CustomerTicket> monoCustomerTicket = 
				Mono.zip(accountMapper, orderMapper).flatMap(tuple -> {
			AccountMapper account = tuple.getT1();
			OrderMapper order = tuple.getT2();
			
			if(account == null || order == null) {
				return Mono.just(customerTicket);
			}
			
			customerTicket.setAccountId(account.getId());
			customerTicket.setOrderNumber(order.getOrderNumber());
			customerTicket.setCreateTime(new Date());
			customerTicket.setDescription("TestCustomerTicket");
			
			return Mono.just(customerTicket);
		});
		
		return monoCustomerTicket.flatMap(customerTicketRepository::save);
	}

	public Flux<CustomerTicket> getCustomerTickets() {

		return customerTicketRepository.findAll();
	}

	public Mono<CustomerTicket> getCustomerTicketById(String id) {
		
		return customerTicketRepository.findById(id);
	}
}
