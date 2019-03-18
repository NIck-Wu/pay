package com.pay.alipay.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.utils.ResponseResult;

import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AliPayService {
	/**
	 * app支付
	 * @param request
	 * @return
	 * @throws AlipayApiException
	 */
	public ResponseResult<JSONObject> appPay(@RequestBody JSONObject request) throws AlipayApiException;
	
	/**
	 *  wap支付
	 * @param request
	 * @param response
	 * @return
	 * @throws AlipayApiException
	 * @throws IOException
	 */
//	public String wapPay(JSONObject request,HttpServletResponse response) throws AlipayApiException,IOException;
	
	/**
	 * 交易查询
	 * @param request
	 * @return
	 */
//	public ResponseResult<JSONObject> tradeQuery(@RequestBody JSONObject request) throws AlipayApiException;
	/**
	 * 支付回调
	 * @param request
	 * @return
	 */
//	public String aliPayAsynNotify(HttpServletRequest request);
	/**
	 *  退款接口
	 * @param request
	 * @return
	 * @throws AlipayApiException
	 */
//	public ResponseResult<JSONObject> tradeRefund(@RequestBody JSONObject request) throws AlipayApiException;
	/**
	 *  退款查询
	 * @param request
	 * @return
	 * @throws AlipayApiException
	 */
//	public ResponseResult<JSONObject> tradeRefundQuery(@RequestBody JSONObject request) throws AlipayApiException;
	/**
	 * 下载对账单
	 * @param request
	 * @return
	 */
//	public ResponseResult<JSONObject> dataDataServiceBill(@RequestBody JSONObject request) ;
}
