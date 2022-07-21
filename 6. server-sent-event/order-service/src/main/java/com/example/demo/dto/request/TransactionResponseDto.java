package com.example.demo.dto.request;

import com.example.demo.dto.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionResponseDto {
	private Integer userId;
	private Integer amount;
	private TransactionStatus status;
}