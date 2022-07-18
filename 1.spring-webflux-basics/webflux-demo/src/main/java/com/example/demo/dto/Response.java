package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
	private LocalDate localDate = LocalDate.now();
	private int output;

	public Response(int output) {
		this.output = output;
	}
}