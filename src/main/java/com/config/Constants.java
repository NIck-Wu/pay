package com.config;
public class Constants {
	//支付宝常量
	//销售产品码，商家和支付宝签约的产品码
	public static final String ALI_APPPAY_PRODUCT_CODE = "QUICK_MSECURITY_PAY";
	
	public static final String ALI_WAPPAY_PRODUCT_CODE = "QUICK_WAP_WAY";
	//该笔订单允许的最晚付款时间 m代表分钟 
	public static final String ALI_APPPAY_TIMEOUT_EXPRESS = "30m";
	public static final Integer ALI_APPPAY_TIMEOUT_EXPRESS_INT = 30;
	//对一笔交易的具体描述信息
	public static final String ALI_APPPAY_BODY = "";
	//商品的标题/交易标题/订单标题/订单关键字
	public static final String ALI_APPPAY_SUBJECT = "";
	
	public static final String ALI_APPPAY_PAYEE = "aliPay";
	
	//模块代码
	public static final String MODULE_CODE = "pay";
	
	//返回结果
	//成功
	public static final String RETURE_CODE_SUCCESS = "0";
	//成功
	public static final String RETURE_MSG_SUCCESS = "success";
	
	//微信常量
	//对一笔交易的具体描述信息
	public static final String WX_APPPAY_BODY = "微信支付";
	
	public static final String WX_APPPAY_PAYEE = "wxPay";
	
	public static final String WX_DEFAULT_IP = "123.12.12.123";
}