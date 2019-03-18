package com.constants;

/**
 * 错误代码枚举类
 * 
 * @author NIck
 */
public enum ErrorCodeEnum {
	SUCCESS("0","成功"),

	;

	private String code;

	private String desc;

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	private ErrorCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}
