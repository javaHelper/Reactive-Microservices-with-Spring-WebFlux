package com.example.demo.webtestclient;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.controller.ParamsController;
import com.example.demo.controller.ReactiveMathController;
import com.example.demo.dto.Response;
import com.example.demo.service.ReactiveMathService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = {ReactiveMathController.class, ParamsController.class})
class Lec02ControllerGetTest {
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	public ReactiveMathService reactiveMathService;
	
	@Test
	public void fluentAssertionTest() {
		when(reactiveMathService.findSquare(anyInt())).thenReturn(Mono.just(new Response(25)));
		
		this.webTestClient
			.get()
			.uri("/reactive-math/square/{number}", 5)
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBody(Response.class)
			.value(r -> Assertions.assertThat(r.getOutput()).isEqualTo(25));
	}
	
	
	@Test
	public void listResponseTest() {
		Flux<Response> flux = Flux.range(1, 3)
			.map(Response::new);
		when(reactiveMathService.multiplicationTable(anyInt())).thenReturn(flux);
		
		this.webTestClient
			.get()
			.uri("/reactive-math/table/{number}", 5)
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBodyList(Response.class)
			.hasSize(3);
	}
	
	@Test
	public void streamingResponseTest() {
		Flux<Response> flux = Flux.range(1, 3)
			.map(Response::new)
			.delayElements(Duration.ofMillis(100));
		
		when(reactiveMathService.multiplicationTable(anyInt())).thenReturn(flux);
		
		this.webTestClient
			.get()
			.uri("/reactive-math/table/{number}/stream", 5)
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectHeader().contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM_VALUE)
			.expectBodyList(Response.class)
			.hasSize(3);
	}
	
	
	@Test
	public void paramsTest() {
        Map<String, Integer> map = Map.of(
                "count", 10,
                "page", 20
        );

        this.webTestClient
                .get()
                 .uri(b -> b.path("/jobs/search").query("count={count}&page={page}").build(map))
                 .exchange()
                 .expectStatus().is2xxSuccessful()
                 .expectBodyList(Integer.class)
                 .hasSize(2).contains(10,10);
	}
}
