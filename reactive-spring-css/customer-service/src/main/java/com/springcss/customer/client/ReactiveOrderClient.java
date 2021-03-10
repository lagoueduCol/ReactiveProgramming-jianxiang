package com.springcss.customer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class ReactiveOrderClient {
  
    private static final Logger logger = LoggerFactory.getLogger(ReactiveOrderClient.class);    
    
    public Mono<OrderMapper> getOrderByOrderNumber(String orderNumber) {    	
    	logger.debug("Get order from remote: {}", orderNumber);
    	
    	Mono<OrderMapper> orderMono = WebClient.create()
                .get()
                .uri("localhost:8081/orders/{orderNumber}", orderNumber)
                .retrieve()
                .bodyToMono(OrderMapper.class).log("getOrderFromRemote");
    	
        return orderMono;
    }
}
