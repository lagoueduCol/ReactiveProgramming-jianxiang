package com.springcss.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcss.customer.domain.CustomerTicket;
import com.springcss.customer.service.CustomerTicketService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="customers")
public class CustomerController {
    
    @Autowired
    private CustomerTicketService customerTicketService; 
	
	@PostMapping(value = "/{accountId}/{orderNumber}")
	public Mono<CustomerTicket> generateCustomerTicket( @PathVariable("accountId") String accountId,
            @PathVariable("orderNumber") String orderNumber) {
		
		Mono<CustomerTicket> customerTicket = customerTicketService.generateCustomerTicket(accountId, orderNumber);		
		
		return customerTicket;
	}
	
	@GetMapping(value = "/{id}")
    public Mono<CustomerTicket> getCustomerTicketById(@PathVariable String id) {
		
		Mono<CustomerTicket> customerTicket = customerTicketService.getCustomerTicketById(id);
		
//		CustomerTicket customerTicket = new CustomerTicket();
//		customerTicket.setId(1L);
//		customerTicket.setAccountId(100L);
//		customerTicket.setOrderNumber("Order00001");
//		customerTicket.setDescription("DemoOrder");
//		customerTicket.setCreateTime(new Date());
//		
    	return customerTicket;
    }
	
	@GetMapping(value = "/")
	public Flux<CustomerTicket> getCustomerTicketList( ) {
		Flux<CustomerTicket> customerTickets = customerTicketService.getCustomerTickets();
		
		return customerTickets;
	}	
	
}
