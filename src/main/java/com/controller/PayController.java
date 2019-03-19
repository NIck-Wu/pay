package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.pay.wechatpay.service.WeChatPayService;
import com.utils.ResponseResult;

@RestController
@RequestMapping("/api/pay/pay/")
public class PayController {
	@Autowired
	private WeChatPayService wxChatPayService;

	/**
	 * 微信jsapi支付
	 * 
	 * @param jsonObject
	 * @return
	 */
	@RequestMapping(value = "wechatJsApiPay", method = RequestMethod.POST)
	public ResponseResult<JSONObject> wechatJsApiPay(@RequestBody JSONObject jsonObject) {

		return wxChatPayService.jsAPIPushOrder(jsonObject);

	}
	
	/**
	 * 微信APP支付
	 * 
	 * @param jsonObject
	 * @return
	 */
	@RequestMapping(value = "wechatAppPay", method = RequestMethod.POST)
	public ResponseResult<JSONObject> wechatAppPay(@RequestBody JSONObject jsonObject) {

		return wxChatPayService.jsAPIPushOrder(jsonObject);

	}
	
	/**
	 * 微信H5支付
	 * 
	 * @param jsonObject
	 * @return
	 */
	@RequestMapping(value = "wechatH5Pay", method = RequestMethod.POST)
	public ResponseResult<JSONObject> wechatH5Pay(@RequestBody JSONObject jsonObject) {

		return wxChatPayService.jsAPIPushOrder(jsonObject);

	}
	/**
	 * 微信订单查询
	 * 
	 * @param jsonObject
	 * @return
	 */
	@RequestMapping(value = "wechatOrderQuery", method = RequestMethod.POST)
	public ResponseResult<JSONObject> wechatOrderQuery(@RequestBody JSONObject jsonObject) {

		return wxChatPayService.jsAPIPushOrder(jsonObject);

	}
	/**
	 * 微信订单查询
	 * 
	 * @param jsonObject
	 * @return
	 */
	@RequestMapping(value = "wechatOrderRefund", method = RequestMethod.POST)
	public ResponseResult<JSONObject> wechatOrderRefund(@RequestBody JSONObject jsonObject) {

		return wxChatPayService.jsAPIPushOrder(jsonObject);

	}
	
}
