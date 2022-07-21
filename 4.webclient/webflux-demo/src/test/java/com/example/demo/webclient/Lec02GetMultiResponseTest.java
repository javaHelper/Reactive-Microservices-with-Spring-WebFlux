package com.example.demo.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.Response;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class Lec02GetMultiResponseTest extends BaseTest{
	@Autowired
    private WebClient webClient;
	
	@Test
	public void fluxTest() {
		Flux<Response> fluxResponse = this.webClient
			.get()
			.uri("reactive-math/table/{number}", 5)
			.retrieve()
			.bodyToFlux(Response.class)
			.doOnNext(s -> System.out.println(s));
		
		StepVerifier.create(fluxResponse)
			.expectNextCount(10)
			.verifyComplete();
	}
	
	
	@Test
    public void fluxStreamTest() {
		Flux<Response> doOnNext = this.webClient
			.get()
			.uri("reactive-math/table/{number}/stream", 5)
			.retrieve()
			.bodyToFlux(Response.class)
			.doOnNext(s -> System.out.println(s));
		
		StepVerifier.create(doOnNext)
			.expectNextCount(10)
			.verifyComplete();
	}
}
