package com.example.demo.webtestclient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.config.RequestHandler;
import com.example.demo.config.RouterConfig;
import com.example.demo.dto.Response;

@WebFluxTest
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = RouterConfig.class)
class Lec05RouterFunctionTest {

	private WebTestClient webTestClient;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@MockBean
	private RequestHandler requestHandler;

	@BeforeAll
	void setClientTest() {
		this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext).build();
	}
	
	@Test
	public void test() {
		when(requestHandler.squareHandler(any())).thenReturn(ServerResponse.ok().bodyValue(new Response(225)));
		
		this.webTestClient
			.get()
			.uri("/router/square/{input}", 15)
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectBody(Response.class)
			.value(r -> Assertions.assertThat(r.getOutput()).isEqualTo(225));
	}
	
}
