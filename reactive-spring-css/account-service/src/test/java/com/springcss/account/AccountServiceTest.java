package com.springcss.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springcss.account.domain.Account;
import com.springcss.account.repository.ReactiveAccountRepository;
import com.springcss.account.service.AccountService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@Autowired
	AccountService service;
	
	@MockBean
	ReactiveAccountRepository repository;

	@Test
	public void testGetAccountByAccountName() {
		Account mockAccount = new Account("Account1", "AccountCode1", "AccountName1");
		
//		Mockito.when(repository.findAccountByAccountName("AccountName1")).thenReturn(Mono.just(mockAccount));
		
		given(repository.findAccountByAccountName("AccountName1")).willReturn(Mono.just(mockAccount));
		
		Mono<Account> account = service.getAccountByAccountName("AccountName1");
		
		StepVerifier.create(account).expectNextMatches(results -> {
			assertThat(results.getAccountCode()).isEqualTo("AccountCode1");
			assertThat(results.getAccountName()).isEqualTo("AccountName1");
			return true;
		}).verifyComplete();
		
	}	
}


