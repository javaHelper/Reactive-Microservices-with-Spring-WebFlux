package com.example.demo.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.request.TransactionRequestDto;
import com.example.demo.dto.request.TransactionResponseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserClient {

	private final WebClient webClient;

	public UserClient(@Value("${user.service.url}") String url) {
		this.webClient = WebClient.builder()
				.baseUrl(url)
				.build();
	}

	public Flux<UserDto> getAllUsers(){
		return this.webClient
				.get()
				.uri("all")
				.retrieve()
				.bodyToFlux(UserDto.class);
	}

	public Mono<TransactionResponseDto> authorizedTransaction(TransactionRequestDto transactionRequestDto) {
		return this.webClient
				.post()
				.uri("transaction")
				.bodyValue(transactionRequestDto)
				.retrieve()
				.bodyToMono(TransactionResponseDto.class);
	}
}
