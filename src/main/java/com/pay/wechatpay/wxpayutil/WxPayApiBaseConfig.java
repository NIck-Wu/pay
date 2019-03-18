package com.pay.wechatpay.wxpayutil;


import java.io.Serializable;

import com.ext.kit.StrKit;

/**
 * @author Javen 2017年5月22日
 */
public class WxPayApiBaseConfig implements Serializable {
	
	private static final long serialVersionUID = 5455485868658034954L;
	//账号代码
	private String accountCode;
	//是否沙箱环境
    private boolean isSandbox;
    //appId
	private String appId;
	//商户号
	private String mchId;
	//服务商模式appId
	private String subAppId;
	//服务商模式商户号
	private String subMchId;
	//商户密钥
	private String paternerKey;

	private String openId;
	//
	private String subOpenId;
	//微信商户证书路径
	private String centPath;
	
	private PayModel payModel;

	/**
	 * 分别对应商户模式、服务商模式
	 */
	public static enum PayModel {
		BUSINESSMODEL, SERVICEMODE
	}


	public WxPayApiBaseConfig() {
	}

//	public static WxPayApiBaseConfig New() {
//		return new WxPayApiBaseConfig();
//	}

	public String getAccountCode() {
		if (StrKit.isBlank(accountCode))
			throw new IllegalStateException("accountCode 未被赋值");
		return accountCode;
	}

	public WxPayApiBaseConfig setAccountCode(String accountCode) {
		if (StrKit.isBlank(accountCode))
			throw new IllegalArgumentException("accountCode 值不能为 null");
		this.accountCode = accountCode;
		return this;
	}

	
	public boolean isSandbox() {
		return isSandbox;
	}

	public WxPayApiBaseConfig setSandbox(boolean isSandbox) {
		this.isSandbox = isSandbox;
		return this;
	}

	public String getAppId() {
		return appId;
	}

	public WxPayApiBaseConfig setAppId(String appId) {
		this.appId = appId;
		return this;
	}

	public String getMchId() {
		return mchId;
	}

	public WxPayApiBaseConfig setMchId(String mchId) {
		this.mchId = mchId;
		return this;
	}

	public String getSubAppId() {
		return subAppId;
	}

	public WxPayApiBaseConfig setSubAppId(String subAppId) {
		this.subAppId = subAppId;
		return this;
	}

	public String getSubMchId() {
		return subMchId;
	}

	public WxPayApiBaseConfig setSubMchId(String subMchId) {
		this.subMchId = subMchId;
		return this;
	}

	public String getOpenId() {
		return openId;
	}

	public WxPayApiBaseConfig setOpenId(String openId) {
		this.openId = openId;
		return this;
	}

	public String getSubOpenId() {
		return subOpenId;
	}

	public WxPayApiBaseConfig setSubOpenId(String subOpenId) {
		this.subOpenId = subOpenId;
		return this;
	}

	public String getPaternerKey() {
		return paternerKey;
	}

	public WxPayApiBaseConfig setPaternerKey(String paternerKey) {
		this.paternerKey = paternerKey;
		return this;
	}
	
	public String getCentPath() {
		return centPath;
	}

	public WxPayApiBaseConfig setCentPath(String centPath) {
		this.centPath = centPath;
		return this;
	}

	public PayModel getPayModel() {
		if (payModel == null)
			payModel = PayModel.BUSINESSMODEL;
		return payModel;
	}

	public WxPayApiBaseConfig setPayModel(PayModel payModel) {
		if (payModel == null)
			payModel = PayModel.BUSINESSMODEL;
		this.payModel = payModel;
		return this;
	}
}
