package com.example.demo.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

	// > min & < max
	// Flux<Product> findByPriceBetween(int min, int max);
	Flux<Product> findByPriceBetween(Range<Integer> range);
}