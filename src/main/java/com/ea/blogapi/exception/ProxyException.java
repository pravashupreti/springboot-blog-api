package com.ea.blogapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProxyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ProxyException(String message) {
		super(message);
	}

	public ProxyException(String message, Throwable cause) {
		super(message, cause);
	}
}
