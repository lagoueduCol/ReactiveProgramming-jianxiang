package com.springcss.r2dbc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcss.r2dbc.demo.domain.Account;
import com.springcss.r2dbc.demo.repository.ReactiveAccountRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "accounts")
public class AccountController {

	@Autowired
	private ReactiveAccountRepository reactiveAccountRepository;

	@GetMapping(value = "/{accountId}")
	public Mono<Account> getAccountById(@PathVariable("accountId") Long accountId) {
		
		Mono<Account> account = reactiveAccountRepository.getAccountById(accountId);
		return account;
	}

	@PostMapping(value = "/")
	public Mono<Boolean> addAccount(@RequestBody Account account) {
		
		return reactiveAccountRepository.addAccount(account.getAccountCode(), account.getAccountName());
	}
}
