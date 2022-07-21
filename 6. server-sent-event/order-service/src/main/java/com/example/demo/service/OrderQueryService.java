package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.PurchaseOrderResponseDto;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.util.EntityDtoUtil;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class OrderQueryService {

	@Autowired
    private PurchaseOrderRepository orderRepository;
	
	public Flux<PurchaseOrderResponseDto> getProductsByUserId(int userId) {
		
		return Flux.fromStream(() -> this.orderRepository.findByUserId(userId).stream()) // blocking
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
	}
}
