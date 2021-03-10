package com.springcss.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.springcss.account.domain.Account;
import com.springcss.account.event.ReactiveAccountChangedSource;
import com.springcss.account.repository.ReactiveAccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
	
    @Autowired
    private ReactiveAccountRepository accountRepository;
    
    @Autowired
    private ReactiveAccountChangedSource accountChangedSource;
        
    public Mono<Account> getAccountById(String accountId) {
        
        return accountRepository.findById(accountId).log("getAccountById");
    }
    
    public Mono<Account> getAccountByAccountName(String accountName) {
        
        return accountRepository.findAccountByAccountName(accountName).log("getAccountByAccountName");
    }

    public Mono<Void> addAccount(Mono<Account> account){
    	
    	Mono<Account> saveAccount = account.flatMap(accountRepository::save);
    	
    	return saveAccount.flatMap(accountChangedSource::publishAccountUpdatedEvent);
    }

    public Mono<Void> updateAccount(Mono<Account> account){
    	
    	Mono<Account> saveAccount = account.flatMap(accountRepository::save);
    	
    	return saveAccount.flatMap(accountChangedSource::publishAccountUpdatedEvent);
    }
    
    public Flux<Account> getAccountsByAccountName(String accountName) {
    	Account account = new Account();
    	account.setAccountName(accountName);
 
    	ExampleMatcher matcher = ExampleMatcher.matching()
    		.withIgnoreCase()
    		.withMatcher(accountName, GenericPropertyMatcher.of(StringMatcher.STARTING))
    		.withIncludeNullValues();
 
    	Example<Account> example = Example.of(account, matcher);
    	
    	Flux<Account> accounts = accountRepository.findAll(example).log("getAccountsByAccountName");
    	
    	return accounts;
    }   

}



//return Mono.when(saveAccount, saveEvent); 
