package com.example.demo.util;

import org.springframework.beans.BeanUtils;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;

public class EntityDtoUtil {

	public static ProductDto toDto(Product product) {
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(product, dto);
		return dto;
	}

	public static Product toEntity(ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		return product;
	}

}