package com.example.demo.webtestclient;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.dto.Response;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


@SpringBootTest
@AutoConfigureWebTestClient
class Lec01SimpleWebTestClientTest {
	@Autowired
	private WebTestClient webTestClient;


	@Test
	public void stepVerifierTest() {
		Flux<Response> responseBody = this.webTestClient
				.get()
				.uri("/reactive-math/square/{number}", 5)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.returnResult(Response.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
		.expectNextMatches(r -> r.getOutput() == 25)
		.verifyComplete();
	}

	@Test
	public void fluentAssertionTest() {
		this.webTestClient
			.get()
			.uri("/reactive-math/square/{number}", 5)
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBody(Response.class)
			.value(r -> Assertions.assertThat(r.getOutput()).isEqualTo(25));
	}
}
