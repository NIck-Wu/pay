package com.pay.alipay.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.ext.util.StringUtils;
import com.pay.alipay.service.AliPayService;
import com.pay.validateHelper.ValidateHelper;
import com.utils.ResponseResult;



@RestController
@RequestMapping("/alipay/alipay")
public class AliPayController {
	private static Logger logger = Logger.getLogger(AliPayController.class);
	
	@Autowired
	private AliPayService aliPayService;
//	/**
//	 * 支付宝支付回调
//	 * 
//	 * @param
//	 * @return
//	 */
//	@RequestMapping(value = "/aliPayAsynNotify", method = RequestMethod.POST)
//	public String aliPayAsynNotify(HttpServletRequest request) {
//		logger.info("=======支付宝支付回调======");
//		//logger.info("request==="+JSONObject.toJSONString(request));
//		return aliPayService.aliPayAsynNotify(request);
//	}
	
	/**
	 * app支付
	 * @param request
	 * @return
	 * @throws AlipayApiException
	 */
	@RequestMapping(value = "/appPay", method = RequestMethod.POST)
	public ResponseResult<JSONObject> appPay(@RequestBody JSONObject request) throws AlipayApiException{
		logger.info("request==="+request.toJSONString());
		//参数校验
		ValidateHelper.validateNull(request, new String[]{"accountCode","outTradeNo","totalFee","notifyURL"});
		//body subject
		return aliPayService.appPay(request);
	}
	/**
	 * wap支付(未测试)
	 * @param response
	 * @param request
	 * @return
	 * @throws AlipayApiException
	 * @throws IOException
	 */
//	@RequestMapping(value = "/wapPay", method = RequestMethod.POST)
//	public String wapPay(@RequestBody JSONObject request,HttpServletResponse response) throws AlipayApiException,IOException{
//		logger.info("request==="+request.toJSONString());
//		//参数校验
//		ValidateHelper.validateNull(request, new String[]{"accountCode","outTradeNo","totalFee","notifyURL","quitUrl"});
//		
//		return aliPayService.wapPay(request,response);
//	}
//	@RequestMapping(value = "/wapPayGet", method = RequestMethod.GET)
//	public String wapPayGet(HttpServletRequest request,HttpServletResponse response) throws AlipayApiException,IOException{
//	
//		JSONObject requestJson = new JSONObject();
//		requestJson.put("accountCode", request.getParameter("accountCode"));
//		requestJson.put("outTradeNo", request.getParameter("outTradeNo"));
//		requestJson.put("totalFee", request.getParameter("totalFee"));
//		requestJson.put("notifyURL", request.getParameter("notifyURL"));
//		requestJson.put("quitUrl", request.getParameter("quitUrl"));
//		logger.info("requestJson==="+requestJson.toJSONString());
//		//参数校验
//		ValidateHelper.validateNull(requestJson, new String[]{"accountCode","outTradeNo","totalFee","notifyURL","quitUrl"});
//		return aliPayService.wapPay(requestJson,response);
//	}
	/**
	 * 交易查询
	 * @param request
	 * @return
	 * @throws AlipayApiException
	 */
//	@RequestMapping(value = "/tradeQuery", method = RequestMethod.POST)
//	public ResponseResult<JSONObject> tradeQuery(@RequestBody JSONObject request) throws AlipayApiException{
//		logger.info("request==="+request.toJSONString());
//		//参数校验
//		ValidateHelper.validateNull(request, new String[]{"outTradeNo"});
//		return aliPayService.tradeQuery(request);
//	}
	/**
	 * 退款接口
	 * @param request
	 * @return
	 * @throws AlipayApiException
	 */
//	@RequestMapping(value = "/tradeRefund", method = RequestMethod.POST)
//	public ResponseResult<JSONObject> tradeRefund(@RequestBody JSONObject request) throws AlipayApiException{
//		logger.info("request==="+request.toJSONString());
/**
{
	"accountCode": "wsh_aliPay_01",
	"outTradeNo": "aea011790e7d43a0aaefa6861d557306",
	"tradeNo": "2018072421001004090591247920",
	"refundAmount": "0.01",
	"outRequestNo": "123456789007",
	"refundNotifyURL": "http://CoreBusinessServer/order/payvoucher/refundOrderNotify"
}
*/
//		//参数校验 //outRequestNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
//		ValidateHelper.validateNull(request, new String[]{"outTradeNo","outRequestNo","refundFee","refundNotifyURL"});
//		if(StringUtils.isEmptyStr(request.getString("refundReason"))) {
//			request.put("refundReason", "正常退款");
//		}
//		//body subject
//		return aliPayService.tradeRefund(request);
//	}

	/**
	 * 退款查询
	 * @param request
	 * @return
	 * @throws AlipayApiException
	 */
//	@RequestMapping(value = "/tradeRefundQuery", method = RequestMethod.POST)
//	public ResponseResult<JSONObject> tradeRefundQuery(@RequestBody JSONObject request) throws AlipayApiException{
//		logger.info("request==="+request.toJSONString());
//		//参数校验 //outRequestNo 标识一次退款请求，则此参数必传
//		ValidateHelper.validateNull(request, new String[]{"outTradeNo","outRequestNo"});
//		return aliPayService.tradeRefundQuery(request);
//	}

	/**
	 * 下载对账单
	 */
//	@RequestMapping(value = "/dataDataServiceBill", method = RequestMethod.POST)
//	public ResponseResult<JSONObject> dataDataServiceBill(@RequestBody JSONObject request) {
//		//@RequestParam("billDate") String billDate
//		logger.info("request==="+request.toJSONString());
//		//参数校验 //outRequestNo 标识一次退款请求，则此参数必传
//		ValidateHelper.validateNull(request, new String[]{"accountCode","billDate"});
//		
//		return aliPayService.dataDataServiceBill(request);
//	}
}
