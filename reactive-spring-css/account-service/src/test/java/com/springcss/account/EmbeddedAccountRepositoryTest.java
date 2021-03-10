package com.springcss.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.springcss.account.domain.Account;
import com.springcss.account.repository.ReactiveAccountRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@RunWith(SpringRunner.class)
@DataMongoTest
public class EmbeddedAccountRepositoryTest {

	@Autowired
	ReactiveAccountRepository repository;

	@Autowired
	ReactiveMongoOperations operations;
	
	@Before
	public void setUp() {
		operations.dropCollection(Account.class);

		operations.insert(new Account("Account1", "AccountCode1", "AccountName1"));
		operations.insert(new Account("Account2", "AccountCode2", "AccountName2"));
		
		operations.findAll(Account.class).subscribe(
				account -> {
					System.out.println(account.getId()
				);}
		);
	}
	
	@Test
	public void testGetAccountByAccountName() {
		Mono<Account> account = repository.findAccountByAccountName("AccountName1");

		StepVerifier.create(account)
			.expectNextMatches(results -> {
				assertThat(results.getAccountCode()).isEqualTo("AccountCode1");
				assertThat(results.getAccountName()).isEqualTo("AccountName1");
				return true;
		});
	}
}
