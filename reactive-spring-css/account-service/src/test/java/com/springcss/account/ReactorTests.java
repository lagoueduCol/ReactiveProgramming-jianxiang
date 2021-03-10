package com.springcss.account;

import org.junit.Test;

import com.springcss.account.domain.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ReactorTests {
	Flux<String> helloWorld = Flux.just("Hello", "World");

	@Test
	public void testStepVerifier() {
		StepVerifier.create(helloWorld).expectNext("Hello").expectNext("World").expectComplete().verify();
	}

	@Test
	public void testExpectNextMatches() {
		StepVerifier.create(Flux.just("jian-hu", "xiang-hu")).expectSubscription()
				.expectNextMatches(e -> e.startsWith("jian")).expectNextMatches(e -> e.startsWith("xiang"))
				.expectComplete().verify();

	}

	@Test
	public void testAssertNext() {
		Account testAccount = new Account("1", "accountCode1", "accountName1");

		StepVerifier
			.create(Flux.just(testAccount))
			.expectSubscription()
			.assertNext(
				account -> account.getAccountCode().equals("accountCode1")
			)
			.expectComplete()
			.verify();
	}

	@Test
	public void testStepVerifierWithError() {
		Flux<String> helloWorldWithException = helloWorld
				.concatWith(Mono.error(new IllegalArgumentException("exception")));

		StepVerifier.create(helloWorldWithException).expectNext("Hello").expectNext("World")
				.expectErrorMessage("exception").verify();
	}

}
