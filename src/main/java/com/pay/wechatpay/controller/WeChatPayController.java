package com.pay.wechatpay.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.pay.validateHelper.ValidateHelper;
import com.pay.wechatpay.service.WeChatPayService;
import com.utils.ResponseResult;



@RestController
@RequestMapping("/wechatpay/wechatpay")
public class WeChatPayController {
	private static Logger logger = Logger.getLogger(WeChatPayController.class);
	@Autowired
	private WeChatPayService weChatPayService;
//	/**
//	 * 微信支付回调
//	 * 
//	 * @param
//	 * @return
//	 */
//	@RequestMapping(value = "/wechatPayAsynNotify", method = RequestMethod.POST)
//	public String wechatPayAsynNotify(HttpServletRequest request) {
//		logger.info("=======微信支付回调======");
//		return weChatPayService.wechatPayAsynNotify(request);
//	}
	
	/**
	 * app下单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/appPushOrder", method = RequestMethod.POST)
	public ResponseResult<JSONObject> appPushOrder(@RequestBody JSONObject request) {
		logger.info("request==="+request.toJSONString());
		//参数校验
		//notifyURL 为支付模块回调业务模块的地址，微信回调地址由模块服务自身提供  app下单
		ValidateHelper.validateNull(request, new String[]{"accountCode","outTradeNo","totalFee","notifyURL"});
		return weChatPayService.appPushOrder(request);
	}
	/**
	 * h5下单
	 * @param request
	 * @return
	 */
//	@RequestMapping(value = "/h5PushOrder", method = RequestMethod.POST)
//	public ResponseResult<JSONObject> h5PushOrder(@RequestBody JSONObject request) {
//		logger.info("request==="+request.toJSONString());
//		//参数校验
//		//notifyURL 为支付模块回调业务模块的地址，微信回调地址由模块服务自身提供  app下单
//		ValidateHelper.validateNull(request, new String[]{"accountCode","outTradeNo","totalFee","notifyURL","spbillCreateIp","sceneInfo"});
//		return weChatPayService.h5PushOrder(request);
//	}
	/**
	 * 微信公众号下单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/jsAPIPushOrder", method = RequestMethod.POST)
	public ResponseResult<JSONObject> jsAPIPushOrder(@RequestBody JSONObject request) {
		logger.info("request==="+request.toJSONString());
		
		ValidateHelper.validateNull(request, new String[]{"appid","outTradeNo","totalFee","notifyURL","openId","mchId"});
		
		return weChatPayService.jsAPIPushOrder(request);
	}
	
	/**
	 * 微信订单查询
	 */
//	@RequestMapping(value = "/orderQuery", method = RequestMethod.POST)
//	public ResponseResult<JSONObject> orderQuery(@RequestBody JSONObject request) {
//		logger.info("request==="+request.toJSONString());
//		//参数校验
//		ValidateHelper.validateNull(request, new String[]{"outTradeNo"});
//		return weChatPayService.orderQuery(request);
//	}
	/**
	 * 微信关闭订单
	 */
//	@RequestMapping(value = "/closeOrder", method = RequestMethod.POST)
//	public ResponseResult<JSONObject> closeOrder(@RequestBody JSONObject request) {
//		logger.info("request==="+request.toJSONString());
//		//参数校验
//		ValidateHelper.validateNull(request, new String[]{"outTradeNo"});
//		return weChatPayService.closeOrder(request);
//	}
	/**
	 * 微信退款
	 */
//	@RequestMapping(value = "/orderRefund", method = RequestMethod.POST)
//	public ResponseResult<JSONObject> orderRefund(@RequestBody JSONObject request) {
//		logger.info("request==="+request.toJSONString());
//		ValidateHelper.validateNull(request, new String[]{"outTradeNo","refundFee","outRequestNo","refundNotifyURL"});
//		return weChatPayService.orderRefund(request);
//	}
	
	/**
	 * 微信退款查询
	 */
//	@RequestMapping(value = "/refundQuery", method = RequestMethod.POST)
//	public ResponseResult<JSONObject> refundQuery(@RequestBody JSONObject request) {
//		logger.info("request==="+request.toJSONString());
//		//参数校验
//		ValidateHelper.validateNull(request, new String[]{"outTradeNo","outRequestNo"});
//		return weChatPayService.refundQuery(request);
//	}
//	/**
//	 * 微信退款结果通知回调
//	 * 
//	 * @param
//	 * @return
//	 */
//	@RequestMapping(value = "/refundAsynNotify", method = RequestMethod.POST)
//	public String refundAsynNotify(HttpServletRequest request) throws Exception{
//		logger.info("=======微信退款结果通知======");
//		return weChatPayService.refundAsynNotify(request);
//	}
	/**
	 * 下载对账单
	 * @param request
	 * @return
	 */
//	@RequestMapping(value = "/downloadBill", method = RequestMethod.POST)
//	public ResponseResult<JSONObject> downloadBill(@RequestBody JSONObject request) {
//		logger.info("request==="+request.toJSONString());
//		//参数校验
//		ValidateHelper.validateNull(request, new String[]{"accountCode","billDate"});
//		return weChatPayService.downloadBill(request);
//	}
	/**
	 * 下载资金账单
	 * @param request
	 * @return
	 */
//	@RequestMapping(value = "/downloadFundFlow", method = RequestMethod.POST)
//	public ResponseResult<JSONObject> downloadFundFlow(@RequestBody JSONObject request) {
//		//暂不实现
//		return null;
//	}
}
