package com.example.template_db_v4;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TemplateNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TemplateNotFoundException(String message) {
		super(message);
	}
}
