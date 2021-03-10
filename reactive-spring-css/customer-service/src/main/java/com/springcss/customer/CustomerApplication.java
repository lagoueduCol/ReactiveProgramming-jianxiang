package com.springcss.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.springcss.customer.client.AccountMapper;

@SpringBootApplication
@EnableBinding(Sink.class)
public class CustomerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
	
	@Bean
	ReactiveRedisTemplate<String, String> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
		return new ReactiveRedisTemplate<>(factory, RedisSerializationContext.string());
	}
	
	@Bean
	ReactiveRedisTemplate<String, AccountMapper> redisOperations(ReactiveRedisConnectionFactory factory) {
		Jackson2JsonRedisSerializer<AccountMapper> serializer = new Jackson2JsonRedisSerializer<>(AccountMapper.class);

		RedisSerializationContext.RedisSerializationContextBuilder<String, AccountMapper> builder = RedisSerializationContext
				.newSerializationContext(new StringRedisSerializer());

		RedisSerializationContext<String, AccountMapper> context = builder.value(serializer).build();

		return new ReactiveRedisTemplate<>(factory, context);
	}
}
