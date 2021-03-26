
package com.springcss.order;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.springcss.order.domain.Order;

@Component
public class InitDatabase {
	@Bean
	CommandLineRunner init(MongoOperations operations) {
		return args -> {
			operations.dropCollection(Order.class);

			operations.insert(new Order("O_" + UUID.randomUUID().toString(), "Order001", "deliveryAddress1", ""));
			operations.insert(new Order("O_" + UUID.randomUUID().toString(), "Order002", "deliveryAddress2", ""));

			operations.findAll(Order.class).forEach(
					order -> {
						System.out.println(order.getId()
					);}
			);
		};
	}
}