package com.example.demo.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.MultiplyRequestDto;
import com.example.demo.dto.Response;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class Lec03PostRequestTest extends BaseTest{

	@Autowired
    private WebClient webClient;
	
	private MultiplyRequestDto buildRequestDto(int a, int b){
        MultiplyRequestDto dto = new MultiplyRequestDto();
        dto.setFirst(a);
        dto.setSecond(b);
        return dto;
    }
	
	@Test
    public void postTest(){
		Mono<Response> doOnNext = this.webClient
			.post()
			.uri("reactive-math/multiply")
			.bodyValue(buildRequestDto(5, 2))
			.retrieve()
			.bodyToMono(Response.class)
			.doOnNext(s -> System.out.println(s));
		
		StepVerifier.create(doOnNext)
			.expectNextCount(1)
			.verifyComplete();
	}
}
