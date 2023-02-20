package com.phonebook.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralResponse<T> {

	private int status;

	private String message;

	private T data;

	public GeneralResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}
}
