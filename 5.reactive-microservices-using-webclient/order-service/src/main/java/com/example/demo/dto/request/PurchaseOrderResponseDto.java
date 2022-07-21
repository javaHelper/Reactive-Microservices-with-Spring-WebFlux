package com.example.demo.dto.request;

import com.example.demo.dto.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PurchaseOrderResponseDto {
	private Integer orderId;
	private Integer userId;
	private String productId;
	private Integer amount;
	private OrderStatus status;
}