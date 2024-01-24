package com.abc.dto;

import java.util.List;

import lombok.Data;

@Data
public class RequestDto {

	private List<SearchRequestDto> searchRequestDto;
	
	private GlobalOperator operator;
}
