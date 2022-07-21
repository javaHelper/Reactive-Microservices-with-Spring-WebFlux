package com.example.demo.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.ProductClient;
import com.example.demo.client.UserClient;
import com.example.demo.dto.RequestContext;
import com.example.demo.dto.request.PurchaseOrderRequestDto;
import com.example.demo.dto.request.PurchaseOrderResponseDto;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.util.EntityDtoUtil;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

@Service
public class OrderFulfillmentService {
	@Autowired
    private PurchaseOrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    
    public Mono<PurchaseOrderResponseDto> processOrder(Mono<PurchaseOrderRequestDto> requestDtoMono){
        return requestDtoMono.map(dto -> new RequestContext(dto))
                .flatMap(this::productRequestResponse)
                .doOnNext(EntityDtoUtil::setTransactionRequestDto)
                .flatMap(this::userRequestResponse)
                .map(EntityDtoUtil::getPurchaseOrder)
                .map(this.orderRepository::save) // blocking
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic()); // Use when u have blocking operation
    }

    private Mono<RequestContext> productRequestResponse(RequestContext rc){
    	System.out.println("Call Product-Service");
        return this.productClient.getProductById(rc.getPurchaseOrderRequestDto().getProductId())
                .doOnNext(rc::setProductDto)
                .retryWhen(Retry.fixedDelay(5, Duration.ofSeconds(1)))
                .thenReturn(rc);
    }

    private Mono<RequestContext> userRequestResponse(RequestContext rc){
    	System.out.println("Call User-service");
        return this.userClient.authorizedTransaction(rc.getTransactionRequestDto())
                .doOnNext(rc::setTransactionResponseDto)
                .thenReturn(rc);
    }
}
