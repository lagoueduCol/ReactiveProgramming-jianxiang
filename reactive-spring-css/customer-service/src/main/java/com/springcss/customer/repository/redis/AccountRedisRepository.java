package com.springcss.customer.repository.redis;


import com.springcss.customer.client.AccountMapper;

import reactor.core.publisher.Mono;

public interface AccountRedisRepository {
    Mono<Boolean> saveAccount(AccountMapper account);
    
    Mono<Boolean> updateAccount(AccountMapper account);
    
    Mono<Boolean> deleteAccount(String accountId);
    
    Mono<AccountMapper> findAccountById(String accountId);
}


