package com.abc.dto;

import lombok.Data;

@Data
public class SearchRequestDto {

	private String column;
	private String value;
	private String joinTable;
	private Operation operation;
}
