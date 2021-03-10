package com.springcss.customer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.springcss.customer.repository.redis.AccountRedisRepository;

import reactor.core.publisher.Mono;

@Component
public class ReactiveAccountClient {

    private static final Logger logger = LoggerFactory.getLogger(ReactiveAccountClient.class);
    
    @Autowired
    private AccountRedisRepository accountRedisRepository;
    
    private Mono<AccountMapper> getAccountFromCache(String accountId) {
    	logger.info("Get account from redis: {}", accountId);
    	
        return accountRedisRepository.findAccountById(accountId);
    }
    
    private Mono<AccountMapper> putAccountIntoCache(AccountMapper account) {    	
    	logger.info("Put account into redis: {}", account.getId());
		
    	return accountRedisRepository.saveAccount(account).flatMap( saved -> {
    		Mono<AccountMapper> savedAccount = accountRedisRepository.findAccountById(account.getId());
    		
    		return savedAccount;
    	});
    }

    public Mono<AccountMapper> findAccountById(String accountId){   
    	logger.debug("Get account from remote: {}", accountId); 	
    	
    	//先从Redis中获取目标Account对象
    	Mono<AccountMapper> accountMonoFromCache = getAccountFromCache(accountId); 
    	
    	//如果Redis中没有目标Account对象，则进行远程获取
    	Mono<AccountMapper> accountMono = accountMonoFromCache.switchIfEmpty(getAccountFromRemote(accountId));    	
        
        return accountMono;
    }
    
    public Mono<AccountMapper> getAccountFromRemote(String accountId) {    	
    	
    	//远程获取Account对象
    	Mono<AccountMapper> accountMonoFromRemote = WebClient.create()
    			.get()
    			.uri("http://127.0.0.1:8082/accounts/{accountId}", accountId) 
    			.retrieve()
    			.bodyToMono(AccountMapper.class).log("getAccountFromRemote");    	
    	
    	//将获取到的Account对象放入Redis中
    	return accountMonoFromRemote.flatMap(this::putAccountIntoCache);
    }    
}
