package com.springcss.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.springcss.account.controller.AccountController;
import com.springcss.account.domain.Account;
import com.springcss.account.service.AccountService;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = AccountController.class)
public class AccountControllerTest {

	@Autowired
	WebTestClient webClient;

	@MockBean
	AccountService service;

	@Test
	public void testGetAccountById() {
		Account mockAccount = new Account("Account1", "AccountCode1", "AccountName1");		

		given(service.getAccountById("Account1")).willReturn(Mono.just(mockAccount));

		EntityExchangeResult<Account> result = webClient.get()
				.uri("http://localhost:8082/accounts/{accountId}", "Account1").exchange().expectStatus()
				.isOk().expectBody(Account.class).returnResult();

		verify(service).getAccountById("Account1");
		verifyNoMoreInteractions(service);
		
		assertThat(result.getResponseBody().getId()).isEqualTo("Account1");
		assertThat(result.getResponseBody().getAccountCode()).isEqualTo("AccountCode1");
	}
	
//	
//	@Test
//	public void testDeleteMedicine() {
//		given(service.deleteMedicineById("001")).willReturn(Mono.empty());
//
//		webClient.delete()
//				.uri("http://localhost:8081/v1/medicines/{medicineId}", "001").exchange().expectStatus()
//				.isOk().expectBody(Void.class).returnResult();
//
//		verify(service).deleteMedicineById("001");
//		verifyNoMoreInteractions(service);
//	}

}

