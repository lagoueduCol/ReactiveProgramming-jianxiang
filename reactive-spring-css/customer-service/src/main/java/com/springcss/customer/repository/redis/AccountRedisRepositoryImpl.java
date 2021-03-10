package com.springcss.customer.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;

import com.springcss.customer.client.AccountMapper;

import reactor.core.publisher.Mono;

@Repository
public class AccountRedisRepositoryImpl implements AccountRedisRepository {

	@Autowired
	private ReactiveRedisTemplate<String, AccountMapper> reactiveRedisTemplate;

	private static final String HASH_NAME = "Account:";

	@Override
	public Mono<Boolean> saveAccount(AccountMapper account) {
		return reactiveRedisTemplate.opsForValue().set(HASH_NAME + account.getId(), account);
	}

	@Override
	public Mono<Boolean> updateAccount(AccountMapper account) {
		return reactiveRedisTemplate.opsForValue().set(HASH_NAME + account.getId(), account);
	}

	@Override
	public Mono<Boolean> deleteAccount(String accountId) {
		return reactiveRedisTemplate.opsForValue().delete(HASH_NAME + accountId);
	}

	@Override
	public Mono<AccountMapper> findAccountById(String accountId) {
		return reactiveRedisTemplate.opsForValue().get(HASH_NAME + accountId);
	}
}
