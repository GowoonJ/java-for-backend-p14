package kr.co.ordermanagement.presentation.dto;

import lombok.Getter;

@Getter
public class ErrorMessageDto {

	private String message;

	public ErrorMessageDto(String message) {
		this.message = message;
	}

}
