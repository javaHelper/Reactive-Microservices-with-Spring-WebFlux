package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.client.ProductClient;
import com.example.demo.client.UserClient;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.request.PurchaseOrderRequestDto;
import com.example.demo.service.OrderFulfillmentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class OrderServiceApplicationTests {
	@Autowired
	private UserClient userClient;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private OrderFulfillmentService fulfillmentService;
	
	@Test
	void contextLoads() {
		Flux.zip(userClient.getAllUsers(), productClient.getAllProducts())
			.map(t -> buildDto(t.getT1(), t.getT2()))
			.flatMap(dto -> this.fulfillmentService.processOrder(Mono.just(dto)))
			.doOnNext(e -> System.out.println(e));
	}

	private PurchaseOrderRequestDto buildDto(UserDto userDto, ProductDto productDto) {
		PurchaseOrderRequestDto dto = new PurchaseOrderRequestDto();
		dto.setUserId(userDto.getId());
		dto.setProductId(productDto.getId());
		return dto;
	}
}
