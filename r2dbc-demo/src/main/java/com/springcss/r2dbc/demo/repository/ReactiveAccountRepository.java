package com.springcss.r2dbc.demo.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.springcss.r2dbc.demo.domain.Account;

import reactor.core.publisher.Mono;

public interface ReactiveAccountRepository extends R2dbcRepository<Account, Long> {

	@Query("insert into ACCOUNT (ACCOUNT_CODE, ACCOUNT_NAME) values (:accountCode,:accountName)")
	Mono<Boolean> addAccount(String accountCode, String accountName);
	
	@Query("SELECT * FROM account WHERE id =:id")
    Mono<Account> getAccountById(Long id);
}
