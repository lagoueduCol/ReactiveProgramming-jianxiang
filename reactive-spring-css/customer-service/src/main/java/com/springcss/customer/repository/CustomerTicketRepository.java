package com.springcss.customer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.springcss.customer.domain.CustomerTicket;

@Repository
public interface CustomerTicketRepository extends ReactiveMongoRepository<CustomerTicket, String> {
	
	List<CustomerTicket> getCustomerTicketByOrderNumber(String orderNumber);
}
