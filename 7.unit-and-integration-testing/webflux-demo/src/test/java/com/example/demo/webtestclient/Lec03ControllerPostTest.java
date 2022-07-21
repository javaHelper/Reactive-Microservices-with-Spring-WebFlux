package com.example.demo.webtestclient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.controller.ParamsController;
import com.example.demo.controller.ReactiveMathController;
import com.example.demo.dto.MultiplyRequestDto;
import com.example.demo.dto.Response;
import com.example.demo.service.ReactiveMathService;

import reactor.core.publisher.Mono;

@WebFluxTest(controllers = {ReactiveMathController.class, ParamsController.class})
class Lec03ControllerPostTest {
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	public ReactiveMathService reactiveMathService;

	@Test
	void postTest() {
		when(reactiveMathService.multiply(any())).thenReturn(Mono.just(new Response(1)));
		
		this.webTestClient
			.post()
			.uri("/reactive-math/multiply")
			.accept(MediaType.APPLICATION_JSON)
			.headers(h -> h.setBasicAuth("username", "password"))
			.headers(h -> h.set("some-key", "some-value"))
			.bodyValue(new MultiplyRequestDto())
			.exchange()
			.expectStatus().is2xxSuccessful();
	}
}
