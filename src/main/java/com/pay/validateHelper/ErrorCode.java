package com.pay.validateHelper;

public enum ErrorCode {

	//通用异常
	SUCCESS("0","成功"),
	ERROR("11","服务端程序执行异常，请检查"),
	TOKEN_CHECKFALSE("12","token无效或已过期,需重新获取"),
	PARAMETER_ERROR("13","请求参数校验错误，请检查入参是否正确"),
	SERVER_NOT_AVAILABLE("14","服务连接失败，请检查服务提供是否正常"),
	SERVER_NOT_FOUND("15","服务不存在"),
	DATA_NOT_FOUND("16","数据不存在"),
	DATA_FOUND_DUPLICATE("17","找到多条数据"),
	DATA_SAVE_DUPLICATE("18","重复提交数据"),
	RESOURCE_NOT_FOUND("19","资源不存在"),
	;
	
	private String code;
	
	private String message;
	
	private ErrorCode(String message) {
		this.message = message;
	}

	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code == null ? this.name().substring(1) : code ;
	}

	public String getMessage() {
		return message;
	}
	
	public boolean equals(String errorCode){
		return this.code.equals(errorCode);
	}
}
