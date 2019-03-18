package com.utils;

import com.pay.validateHelper.ErrorCode;

/**
 * 业务异常
 * @author qishangzy
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String code = ErrorCode.ERROR.getCode();
	
	private String message;
	
	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		super(message);
		this.message = message;
	}

	public BusinessException(String errorCode,String message ) {
		super(message);
		this.code = errorCode;
		this.message = message;
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}	
