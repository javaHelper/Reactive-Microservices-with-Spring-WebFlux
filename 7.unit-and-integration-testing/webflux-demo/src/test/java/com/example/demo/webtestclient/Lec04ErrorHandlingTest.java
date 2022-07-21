package com.example.demo.webtestclient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.controller.ReactiveMathValidationController;
import com.example.demo.dto.MultiplyRequestDto;
import com.example.demo.dto.Response;
import com.example.demo.service.ReactiveMathService;

import reactor.core.publisher.Mono;

@WebFluxTest(controllers = {ReactiveMathValidationController.class})
class Lec04ErrorHandlingTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	public ReactiveMathService reactiveMathService;

	@Test
	void errorHandlingTest() {
		when(reactiveMathService.multiply(any())).thenReturn(Mono.just(new Response(1)));
		
		// allowed range 10-20
		
		this.webTestClient
			.get()
			.uri("/reactive-math/square/{input}/throw", 5)
			.exchange()
			.expectStatus().isBadRequest()
			.expectBody()
			.jsonPath("$.message").isEqualTo("allowed range is 10 - 20")
			.jsonPath("$.errorCode").isEqualTo(100)
			.jsonPath("$.input").isEqualTo(5);
	}
}
