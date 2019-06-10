package com.twcam.uv.cloudingair.controllers;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

	private String errorCode;
	private String errorMessage;
	private String devErrorMessage;
	private Map<String, Object> additionalData = new HashMap<>();

}
