package com.pay.wechatpay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.config.Constants;
import com.ext.kit.PaymentKit;
import com.ext.util.StringUtils;
import com.pay.validateHelper.BigDecimalUtils;
import com.pay.validateHelper.DateUtils;
import com.pay.wechatpay.service.WeChatPayService;
import com.pay.wechatpay.wxpayutil.WxPayApi;
import com.pay.wechatpay.wxpayutil.WxPayApi.TradeType;
import com.pay.wechatpay.wxpayutil.WxPayApiBaseConfig;
import com.pay.wechatpay.wxpayutil.WxPayApiConfig;
import com.utils.ResponseResult;

@Service
@Transactional
public class WeChatPayServiceImpl implements WeChatPayService {
	private static Logger logger = Logger.getLogger(WeChatPayServiceImpl.class);

//	@Override
//	public String wechatPayAsynNotify(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		// 发送通知等
//		Map<String, String> responseXml = new HashMap<String, String>();
//		String xmlMsg = HttpKit.readData(request);
//		logger.info("支付通知=" + xmlMsg);
//		Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);
//		String appid = params.get("appid");
//		// //商户号
//		// String mch_id = params.get("mch_id");
//		String result_code = params.get("result_code");
//		String openId = params.get("openid");
//		// //交易类型
//		// String trade_type = params.get("trade_type");
//		// //付款银行
//		// String bank_type = params.get("bank_type");
//		// // 总金额
//		// String total_fee = params.get("total_fee");
//		// //现金支付金额
//		// String cash_fee = params.get("cash_fee");
//		// 微信支付订单号
//		String transaction_id = params.get("transaction_id");
//		// 商户订单号
//		String out_trade_no = params.get("out_trade_no");
//
//		// 支付完成时间，格式为yyyyMMddHHmmss
//		String time_end = params.get("time_end");
//
//		// ///////////////////////////以下是附加参数///////////////////////////////////
//
//		// String attach = params.get("attach");
//		// String fee_type = params.get("fee_type");
//		// String is_subscribe = params.get("is_subscribe");
//		// String err_code = params.get("err_code");
//		// String err_code_des = params.get("err_code_des");
//
//		if (PaymentKit.verifyNotify(params, WxPayApiConfigKit
//				.getWxPayApiBaseConfigByAppId(appid).getPaternerKey())) {
//			if (("SUCCESS").equals(result_code)) {
//				// 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
//				// 避免已经成功、关闭、退款的订单被再次更新
//				PayRecordDto payRecordQuery = new PayRecordDto();
//				payRecordQuery.setPayOrderNO(out_trade_no);
//				PayRecordDto payRecord = payRecordService
//						.findByPayorderNo(payRecordQuery);
//				if (payRecord != null
//						&& PayStatusEnum.PAYING.getCode().equals(
//								payRecord.getPayStatus())) {
//
//					// 修改支付记录表 TODO
//					payRecord.setTradeNO(transaction_id);
//					// 用户ID可通过订单找到，此处存储买家openid
//					payRecord.setBuyerID(openId);
//					payRecord.setPayStatus(PayStatusEnum.SUCCESS.getCode());
//					// 状态
//					payRecord.setTradeStatus(result_code);
//					// 支付完成时间
//					if (StringUtils.isNotEmpty(time_end)) {
//						payRecord.setTradeTime(DateUtils.formatDate(time_end,
//								DateUtils.DATE_FORMAT_YYYYMMDDHHMMSS));
//					}
//
//					payRecord.setLastUpdate(new Date());
//					payRecordService.update(payRecord);
//
//					// 业务回调地址
//					String notifyURL = payRecord.getNotifyURL();
//					// 存储回调记录表 TODO
//					NotifyRecordDto notifyRecord = new NotifyRecordDto();
//					notifyRecord.setNotifyUrl(notifyURL);
//					notifyRecord.setBusinessOrderNo(out_trade_no);
//					JSONObject requestParamsJson = new JSONObject();
//					requestParamsJson.put("businessNo", out_trade_no);
//					requestParamsJson.put("tradeNO", transaction_id);
//					requestParamsJson.put("totalFee", payRecord.getTotalFee());
//					requestParamsJson.put("channel", Constants.WX_APPPAY_PAYEE);
//
//					String requestParams = requestParamsJson.toJSONString();
//					notifyRecord.setRequestParams(requestParams);
//					notifyRecord.setNotifyType(NotifyTypeEnum.PAYNOTIFY
//							.getCode());
//					notifyRecord.setCreateTime(new Date());
//					notifyRecordService.save(notifyRecord);
//
//					// 推送异步任务
//					JSONObject requestJson = new JSONObject();
//					requestJson.put("moduleCode", Constants.MODULE_CODE);
//					requestJson.put("taskName",
//							NotifyTypeEnum.PAYNOTIFY.getDesc());
//					requestJson.put("businessNo", out_trade_no);
//					requestJson.put("notifyUrl", notifyURL);
//					requestJson.put("requestParams", requestParams);
//					if (createTask(requestJson)) {
//						responseXml.put("return_code", "SUCCESS");
//						responseXml.put("return_msg", "OK");
//					}
//				}
//			}
//		}
//		return PaymentKit.toXml(responseXml);
//	}

	/**
	 * 推送异步任务
	 * 
	 * @param requestJson
	 * @return
	 */
//	public boolean createTask(JSONObject requestJson) {
//		boolean successInd = false;
//		ResponseResult<JSONObject> result = asynTaskApiClient
//				.createTask(requestJson);
//		if (result != null
//				&& ErrorCode.SUCCESS.getCode().equals(result.getErrorCode())) {
//			JSONObject data = result.getData();
//			if (data != null) {
//				String returnCode = data.getString("returnCode");
//				String returnMsg = data.getString("returnMsg");
//				if (Constants.RETURE_CODE_SUCCESS.equals(returnCode)
//						&& Constants.RETURE_MSG_SUCCESS.equals(returnMsg)) {
//					successInd = true;
//				}
//			}
//		}
//		return successInd;
//	}

	/**
	 * app下单
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public ResponseResult<JSONObject> appPushOrder(JSONObject request) {
		System.out.println(request.toJSONString());
		WxPayApiConfig wxPayParm = buildWxPayApiConfig(request);
		boolean isSandbox = wxPayParm.isSandbox();
		wxPayParm.setTradeType(TradeType.APP);
		Map<String, String> params = wxPayParm.build(); /** 参数转换为Map格式 */
		logger.info("调用微信统一下单接口最终Map参数：" + params.toString());

		String xmlResult = WxPayApi.pushOrder(isSandbox, params); /** 调用微信统一下单接口 */
		Map<String, String> resultMap = PaymentKit.xmlToMap(xmlResult);

		ResponseResult<JSONObject> response = new ResponseResult<JSONObject>();

		String return_code = resultMap.get("return_code");
		String return_msg = resultMap.get("return_msg");
		if (!PaymentKit.codeIsOK(return_code)) {
			response.setErrorCode(return_code);
			response.setErrorMsg(return_msg);
			return response;
		}

		String result_code = resultMap.get("result_code");
		if (!PaymentKit.codeIsOK(result_code)) {
			String err_code_des = resultMap.get("err_code_des");
			String err_code = resultMap.get("err_code");
			response.setErrorCode(result_code);
			response.setErrorMsg("err_code:" + err_code + " err_code_des:" + err_code_des);
			return response;
		}

		/** 以下字段在return_code 和result_code都为SUCCESS的时候有返回 */
		String prepay_id = resultMap.get("prepay_id");
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appid", wxPayParm.getAppId());
		packageParams.put("partnerid", wxPayParm.getMchId());
		packageParams.put("prepayid", prepay_id);
		packageParams.put("package", "Sign=WXPay");
		packageParams.put("noncestr", System.currentTimeMillis() + "");
		packageParams.put("timestamp", System.currentTimeMillis() / 1000 + "");
		String packageSign = PaymentKit.createSign(packageParams, wxPayParm.getPaternerKey()); // 二次签名
		packageParams.put("sign", packageSign);

		String jsonStr = JSON.toJSONString(packageParams);
		response.setData(JSONObject.parseObject(jsonStr));
		return response;
	}

	/**
	 * h5下单
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public ResponseResult<JSONObject> h5PushOrder(JSONObject request) {
		WxPayApiConfig wxPayApiConfig = buildWxPayApiConfig(request);
		String outTradeNo = request.getString("outTradeNo");
		String totalFee = request.getString("totalFee");
		String buNotifyURL = request.getString("notifyURL");
		boolean isSandbox = wxPayApiConfig.isSandbox();
		String sceneInfo = request.getString("sceneInfo");
		String timeExpire = wxPayApiConfig.getTimeExpire();
		wxPayApiConfig.setTradeType(TradeType.MWEB);
		wxPayApiConfig.setSceneInfo(sceneInfo);
		Map<String, String> params = wxPayApiConfig.build();

		String xmlResult = WxPayApi.pushOrder(isSandbox, params);

		Map<String, String> resultMap = PaymentKit.xmlToMap(xmlResult);
		ResponseResult<JSONObject> response = new ResponseResult<JSONObject>();

		String return_code = resultMap.get("return_code");
		String return_msg = resultMap.get("return_msg");
		if (!PaymentKit.codeIsOK(return_code)) {
			logger.info(xmlResult);
			response.setErrorCode(return_code);
			response.setErrorMsg(return_msg);
			return response;
		}
		String result_code = resultMap.get("result_code");
		if (!PaymentKit.codeIsOK(result_code)) {
			logger.info(xmlResult);
			response.setErrorCode(result_code);
			return response;
		}
		String prepay_id = resultMap.get("prepay_id");
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appId", wxPayApiConfig.getAppId());
		packageParams.put("partnerId", wxPayApiConfig.getMchId());
		packageParams.put("prepayId", prepay_id);
		packageParams.put("package", "Sign=WXPay");
		packageParams.put("nonceStr", System.currentTimeMillis() + "");
		packageParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
		String packageSign = PaymentKit.createSign(packageParams, wxPayApiConfig.getPaternerKey());
		packageParams.put("sign", packageSign);

		String jsonStr = JSON.toJSONString(packageParams);
		JSONObject result = JSONObject.parseObject(jsonStr);
		result.put("mwebUrl", resultMap.get("mweb_url"));
		response.setData(result);

		// 存储支付记录表 TODO start========================================
		// 存储支付记录表 end========================================
		return response;
	}

	/**
	 * 微信公众号下单
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public ResponseResult<JSONObject> jsAPIPushOrder(JSONObject request) {
		WxPayApiConfig wxPayApiConfig = buildWxPayApiConfig(request);
		boolean isSandbox = wxPayApiConfig.isSandbox();
		wxPayApiConfig.setTradeType(TradeType.JSAPI);
		String openId = request.getString("openId");
		wxPayApiConfig.setOpenId(openId);// 公众号openID
		Map<String, String> params = wxPayApiConfig.build();
		String xmlResult = WxPayApi.pushOrder(isSandbox, params);

		Map<String, String> resultMap = PaymentKit.xmlToMap(xmlResult);
		ResponseResult<JSONObject> response = new ResponseResult<JSONObject>();

		String return_code = resultMap.get("return_code");
		String return_msg = resultMap.get("return_msg");
		if (!PaymentKit.codeIsOK(return_code)) {
			logger.info(xmlResult);
			response.setErrorCode(return_code);
			response.setErrorMsg(return_msg);
			return response;
		}
		String result_code = resultMap.get("result_code");
		if (!PaymentKit.codeIsOK(result_code)) {
			logger.info(xmlResult);
			response.setErrorCode(result_code);
			return response;
		}
		String prepay_id = resultMap.get("prepay_id");
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);

		SortedMap<String, String> configMap = new TreeMap<>();
		String nonceStr = String.valueOf(new Date().getTime());
		String appId = request.getString("appId");// "wxafe7f0fac1c27836";
		configMap.put("appId", appId);
		configMap.put("timeStamp", timestamp);
//		configMap.put("openid", openId);
		configMap.put("nonceStr", nonceStr);
		configMap.put("signType", "MD5");
		String package1 = "prepay_id=" + prepay_id;
		configMap.put("package", package1);
		String paySign = PaymentKit.createSign(configMap, wxPayApiConfig.getPaternerKey());
		configMap.put("sign", paySign);

		String jsonStr = JSON.toJSONString(configMap);
		response.setData(JSONObject.parseObject(jsonStr));
		// 存储支付记录表 TODO start========================================
		return response;
	}

//	public PayRecordDto buildPayRecord(String outTradeNo, String accountCode,
//			String totalFee, String buNotifyURL, String payMethod,
//			String prepayId, String buyerID, String timeExpire) {
//		PayRecordDto payRecordDto = new PayRecordDto();
//		// 支付凭证号
//		payRecordDto.setPayOrderNO(outTradeNo);
//		payRecordDto.setAccountCode(accountCode);
//		// 收款方
//		payRecordDto.setPayee(Constants.WX_APPPAY_PAYEE);
//		// 支付方法
//		payRecordDto.setPayMethod(payMethod);
//		// 用户在商户app下的唯一标识
//		payRecordDto.setBuyerID(buyerID);
//		// 订单金额
//		payRecordDto.setTotalFee(new BigDecimal(totalFee));
//		// 微信预支付ID
//		payRecordDto.setPrepayID(prepayId);
//		// 支付状态
//		payRecordDto.setPayStatus(PayStatusEnum.PAYING.getCode());
//		// 是否有退款操作
//		payRecordDto.setIsRefund(IsRefundEnum.REfUND_NO.getCode());
//		// 已退款总金额
//		payRecordDto.setTotalRefundFee(new BigDecimal(0));
//		// 订单失效时间
//		if (StringUtils.isNotEmpty(timeExpire)) {
//			payRecordDto.setTimeExpire(DateUtils.formatDate(timeExpire,
//					DateUtils.DATE_FORMAT_YYYYMMDDHHMMSS));
//		}
//		// 异步回调地址
//		payRecordDto.setNotifyURL(buNotifyURL);
//		payRecordDto.setCreateTime(new Date());
//		return payRecordDto;
//	}

//	@Override
//	public ResponseResult<JSONObject> orderQuery(JSONObject request) {
//		// TODO Auto-generated method stub
//		ResponseResult<JSONObject> response = new ResponseResult<JSONObject>();
//		// String accountCode = request.getString("accountCode");
//		// WxPayApiConfig wxPayApiConfig = getWxPayApiConfigByCode(accountCode);
//
//		String outTradeNo = request.getString("outTradeNo");
//		PayRecordDto payRecordQuery = new PayRecordDto();
//		payRecordQuery.setPayOrderNO(outTradeNo);
//		PayRecordDto payRecord = payRecordService.find(payRecordQuery);
//		if (payRecord == null) {
//			throw new BusinessException("未找到当前支付凭证号!");
//		}
//
//		String accountCode = payRecord.getAccountCode();
//		WxPayApiConfig wxPayApiConfig = getWxPayApiConfigByCode(accountCode);
//		boolean isSandbox = wxPayApiConfig.isSandbox();
//
//		String payStatus = payRecord.getPayStatus();
//		// 当前状态为支付中去查询微信接口
//		if (PayStatusEnum.PAYING.getCode().equals(payStatus)) {
//			wxPayApiConfig.setOutTradeNo(outTradeNo);
//			Map<String, String> params = wxPayApiConfig.orderQueryBuild();
//			String xmlResult = WxPayApi.orderQuery(isSandbox, params);
//			logger.info("orderQuery return xmlResult===" + xmlResult);
//			Map<String, String> resultMap = PaymentKit.xmlToMap(xmlResult);
//
//			String return_code = resultMap.get("return_code");
//			String return_msg = resultMap.get("return_msg");
//			if (!PaymentKit.codeIsOK(return_code)) {
//				response.setErrorCode(return_code);
//				response.setErrorMsg(return_msg);
//				return response;
//			}
//			String result_code = resultMap.get("result_code");
//			if (!PaymentKit.codeIsOK(result_code)) {
//				String err_code_des = resultMap.get("err_code_des");
//				String err_code = resultMap.get("err_code");
//				response.setErrorCode(result_code);
//				response.setErrorMsg("err_code:" + err_code + " err_code_des:"
//						+ err_code_des);
//				return response;
//			}
//
//			// 支付状态
//			String trade_state = resultMap.get("trade_state");
//			switch (trade_state) {
//			// 支付成功
//			case "SUCCESS":
//				payStatus = PayStatusEnum.SUCCESS.getCode();
//				break;
//			// 转入退款
//			case "REFUND":
//				payStatus = PayStatusEnum.FAIL.getCode();
//				break;
//			// 未支付
//			case "NOTPAY":
//				payStatus = PayStatusEnum.PAYING.getCode();
//				break;
//			// 已关闭
//			case "CLOSED":
//				payStatus = PayStatusEnum.FAIL.getCode();
//				break;
//			// 已撤销（刷卡支付）
//			case "REVOKED":
//				payStatus = PayStatusEnum.FAIL.getCode();
//				break;
//			// 用户支付中
//			case "USERPAYING":
//				payStatus = PayStatusEnum.PAYING.getCode();
//				break;
//			// 支付失败(其他原因，如银行返回失败)
//			case "PAYERROR":
//				payStatus = PayStatusEnum.FAIL.getCode();
//				break;
//			default:
//				break;
//			}
//			// 支付成功或者失败，此处不做处理，由回调来处理，这里只做数据返回
//		}
//		JSONObject data = new JSONObject();
//		data.put("payStatus", payStatus);
//		if (PayStatusEnum.SUCCESS.getCode().equals(payStatus)) {
//			data.put("businessNo", payRecord.getPayOrderNO());
//			data.put("tradeNO", payRecord.getTradeNO());
//			data.put("totalFee", payRecord.getTotalFee());
//			data.put("channel", Constants.WX_APPPAY_PAYEE);
//		}
//		response.setData(data);
//		return response;
//	}

//	@Override
//	public ResponseResult<JSONObject> closeOrder(JSONObject request) {
//		// TODO Auto-generated method stub
//		ResponseResult<JSONObject> response = new ResponseResult<JSONObject>();
//		boolean isSuccess = false;
//		String reason = "";
//		// String accountCode = request.getString("accountCode");
//		// WxPayApiConfig wxPayApiConfig = getWxPayApiConfigByCode(accountCode);
//		String outTradeNo = request.getString("outTradeNo");
//		PayRecordDto payRecordQuery = new PayRecordDto();
//		payRecordQuery.setPayOrderNO(outTradeNo);
//		PayRecordDto payRecord = payRecordService.find(payRecordQuery);
//		if (payRecord == null) {
//			throw new BusinessException("未找到当前支付凭证号!");
//		}
//		String accountCode = payRecord.getAccountCode();
//		WxPayApiConfig wxPayApiConfig = getWxPayApiConfigByCode(accountCode);
//
//		String payStatus = payRecord.getPayStatus();
//		// 当前状态为支付中去查询微信接口
//		if (!PayStatusEnum.CLOSED.getCode().equals(payStatus)) {
//			wxPayApiConfig.setOutTradeNo(outTradeNo);
//			Map<String, String> params = wxPayApiConfig.closeOrderBuild();
//			String xmlResult = WxPayApi.closeOrder(params);
//			logger.info("closeOrder return xmlResult===" + xmlResult);
//
//			Map<String, String> resultMap = PaymentKit.xmlToMap(xmlResult);
//			String return_code = resultMap.get("return_code");
//			String return_msg = resultMap.get("return_msg");
//			if (!PaymentKit.codeIsOK(return_code)) {
//				response.setErrorCode(return_code);
//				response.setErrorMsg(return_msg);
//				return response;
//			}
//			String result_code = resultMap.get("result_code");
//			if (!PaymentKit.codeIsOK(result_code)) {
//				String err_code_des = resultMap.get("err_code_des");
//				String err_code = resultMap.get("err_code");
//				response.setErrorCode(result_code);
//				response.setErrorMsg("err_code:" + err_code + " err_code_des:"
//						+ err_code_des);
//				return response;
//			}
//			isSuccess = true;
//			payRecord.setPayStatus(PayStatusEnum.CLOSED.getCode());
//			payRecordService.update(payRecord);
//		} else {
//			reason = "订单已处于关闭状态";
//		}
//		JSONObject data = new JSONObject();
//		data.put("isSuccess", isSuccess);
//		if (StringUtils.isNotEmpty(reason)) {
//			data.put("reason", reason);
//		}
//		response.setData(data);
//		return response;
//	}

//	@Override
//	public ResponseResult<JSONObject> orderRefund(JSONObject request) {
//		// TODO Auto-generated method stub
//		// String accountCode = request.getString("accountCode");
//		// WxPayApiConfig wxPayApiConfig = getWxPayApiConfigByCode(accountCode);
//
//		String transactionId = request.getString("tradeNo");
//		String outTradeNo = request.getString("outTradeNo");
//		String buRefundNotifyURL = request.getString("refundNotifyURL");
//		String refundReason = request.getString("refundReason");
//		String refundFee = request.getString("refundFee");
//		// 退款凭证号
//		String outRequestNo = request.getString("outRequestNo");
//
//		PayRecordDto payRecordQuery = new PayRecordDto();
//		payRecordQuery.setPayOrderNO(outTradeNo);
//		PayRecordDto payRecord = payRecordService.find(payRecordQuery);
//		if (payRecord == null) {
//			throw new BusinessException("未找到当前支付凭证号!");
//		}
//		String accountCode = payRecord.getAccountCode();
//		WxPayApiConfig wxPayApiConfig = getWxPayApiConfigByCode(accountCode);
//		boolean isSandbox = wxPayApiConfig.isSandbox();
//
//		// 防止重复提交退款
//		RefundRecordDto refundRecordQuery = new RefundRecordDto();
//		refundRecordQuery.setRefundTrxNo(outRequestNo);
//		RefundRecordDto refundRecordResult = refundRecordService
//				.find(refundRecordQuery);
//		if (refundRecordResult != null) {
//			throw new BusinessException("当前退款凭证号已经发起退款，不能重复退款!");
//		}
//
//		ResponseResult<JSONObject> response = new ResponseResult<JSONObject>();
//		boolean isSuccess = false;
//		String reason = "";
//		JSONObject data = new JSONObject();
//		if ("SUCCESS".equals(payRecord.getTradeStatus())) {
//			Date now = new Date();
//			if (StringUtils.isEmpty(transactionId)) {
//				transactionId = payRecord.getTradeNO();
//			}
//			String totalFee = BigDecimalUtils.roundBigDecimal(
//					BigDecimalUtils.mul(payRecord.getTotalFee(),
//							BigDecimalUtils.BigDecimal_100), 0).toString();
//			String refundFeeTo = BigDecimalUtils.roundBigDecimal(
//					BigDecimalUtils.mul(
//							BigDecimalUtils.transNullStringToZERO(refundFee),
//							BigDecimalUtils.BigDecimal_100), 0).toString();
//
//			Map<String, String> params = new HashMap<String, String>();
//			params.put("appid", wxPayApiConfig.getAppId());
//			params.put("mch_id", wxPayApiConfig.getMchId());
//			params.put("nonce_str", System.currentTimeMillis() + "");
//			params.put("out_trade_no", outTradeNo);
//			if (StrKit.notBlank(transactionId)) {
//				params.put("transaction_id", transactionId);
//			}
//			// 商户退款单号
//			params.put("out_refund_no", outRequestNo);
//			// 订单总金额 单位分
//			params.put("total_fee", totalFee);
//			// 退款金额 单位分
//			params.put("refund_fee", refundFeeTo);
//			String refundAsynNotifyUrl = payConfiguration
//					.getWxRefundNotifyUrl();
//			// "http://a8-domain.yitianxian.com:11111/wechatpay/wechatpay/refundAsynNotify";
//			params.put("notify_url", refundAsynNotifyUrl);
//
//			params.put(
//					"sign",
//					PaymentKit.createSign(params,
//							wxPayApiConfig.getPaternerKey()));
//			String xmlResult = WxPayApi.orderRefund(isSandbox, params,
//					wxPayApiConfig.getCentPath(), wxPayApiConfig.getMchId());
//			logger.info("orderRefund return xmlResult===" + xmlResult);
//			Map<String, String> resultMap = PaymentKit.xmlToMap(xmlResult);
//
//			String return_code = resultMap.get("return_code");
//			String return_msg = resultMap.get("return_msg");
//			if (!PaymentKit.codeIsOK(return_code)) {
//				response.setErrorCode(return_code);
//				response.setErrorMsg(return_msg);
//				return response;
//			}
//			String result_code = resultMap.get("result_code");
//			if (!PaymentKit.codeIsOK(result_code)) {
//				String err_code_des = resultMap.get("err_code_des");
//				String err_code = resultMap.get("err_code");
//				response.setErrorCode(result_code);
//				response.setErrorMsg("err_code:" + err_code + " err_code_des:"
//						+ err_code_des);
//				return response;
//			}
//			String refund_fee = resultMap.get("refund_fee");
//			// 微信平台退款单号
//			String refund_id = resultMap.get("refund_id");
//			// //TODO 修改支付记录表 微信在回调中处理退款状态和退款总金额
//			// payRecord.setIsRefund(IsRefundEnum.REfUND_YES.getCode());
//			// //汇总金额
//			// payRecord.setRefundFee(BigDecimalUtils.div(BigDecimalUtils.transNullStringToZERO(refund_fee),
//			// BigDecimalUtils.BigDecimal_100));
//			// payRecordService.update(payRecord);
//			// 新增退款记录
//			RefundRecordDto refundRecord = new RefundRecordDto();
//			// 退款凭证号
//			refundRecord.setRefundTrxNo(outRequestNo);
//			// 原支付凭证号
//			refundRecord.setPayOrderNO(payRecord.getPayOrderNO());
//			// 原平台交易号
//			refundRecord.setTradeNO(payRecord.getTradeNO());
//			// 原支付账号代码
//			refundRecord.setAccountCode(payRecord.getAccountCode());
//			// 原收款方
//			refundRecord.setPayee(payRecord.getPayee());
//			// 原支付方法
//			refundRecord.setPayMethod(payRecord.getPayMethod());
//			// 退款金额
//			refundRecord.setRefundFee(new BigDecimal(refundFee));
//			// 平台退款单号
//			refundRecord.setRefundPlatNo(refund_id);
//			// 退款结果通知url TODO
//			refundRecord.setResultNotifyUrl(buRefundNotifyURL);
//			// 退款状态
//			refundRecord.setRefundStatus(RefundStatusEnum.COMMIT_SUCCESS
//					.getCode());
//			// 退款接收账号
//			refundRecord.setRefundRecvAccout(payRecord.getBuyerID());
//			// 退款请求时间
//			refundRecord.setRefundRequestTime(now);
//			// 退款成功时间
//			refundRecord.setRefundSuccessTime(new Date());
//			// 退款完成时间
//			// refundRecord.setRefundCompleteTime(refundCompleteTime);
//			// 退款原因
//			refundRecord.setRefundReason(refundReason);
//			refundRecord.setCreateTime(new Date());
//			refundRecordService.save(refundRecord);
//			isSuccess = true;
//		} else {
//			reason = "当前支付未成功付款或已全额退款,不能发起退款";
//		}
//
//		data.put("isSuccess", isSuccess);
//		if (StringUtils.isNotEmpty(reason)) {
//			data.put("reason", reason);
//		}
//		response.setData(data);
//		return response;
//	}

//	@Override
//	public ResponseResult<JSONObject> refundQuery(JSONObject request) {
//		// TODO Auto-generated method stub
//		// String accountCode = request.getString("accountCode");
//		// WxPayApiConfig wxPayApiConfig = getWxPayApiConfigByCode(accountCode);
//		// 微信订单号
//		// String transactionId = request.getString("transactionId");
//		// 商户订单号
//		String outTradeNo = request.getString("outTradeNo");
//		// 商户退款单号
//		String outRequestNo = request.getString("outRequestNo");
//
//		RefundRecordDto refundRecordQuery = new RefundRecordDto();
//		refundRecordQuery.setRefundTrxNo(outRequestNo);
//		RefundRecordDto refundRecord = refundRecordService
//				.find(refundRecordQuery);
//		if (refundRecord == null) {
//			throw new BusinessException("未找到当前退款凭证号!");
//		}
//		String accountCode = refundRecord.getAccountCode();
//		WxPayApiConfig wxPayApiConfig = getWxPayApiConfigByCode(accountCode);
//
//		ResponseResult<JSONObject> response = new ResponseResult<JSONObject>();
//		JSONObject data = new JSONObject();
//		String refundStatus = refundRecord.getRefundStatus();
//		// 当前状态为已提交时去微信查询
//		if (RefundStatusEnum.COMMIT_SUCCESS.getCode().equals(refundStatus)) {
//			// 微信退款单号
//			// String refundId = request.getString("refundId");
//			// wxPayApiConfig.setOutTradeNo(outTradeNo);
//			Map<String, String> params = new HashMap<String, String>();
//			params.put("appid", wxPayApiConfig.getAppId());
//			params.put("mch_id", wxPayApiConfig.getMchId());
//			params.put("nonce_str", System.currentTimeMillis() + "");
//			// 微信订单号查询的优先级是： refund_id > out_refund_no > transaction_id >
//			// out_trade_no
//			// if(StrKit.notBlank(refundId)) {
//			// params.put("refund_id", refundId);
//			// }else if(StrKit.notBlank(outRefundNo)) {
//			// params.put("out_refund_no", outRefundNo);
//			// }else if(StrKit.notBlank(outRefundNo)) {
//			// params.put("transaction_id", transactionId);
//			// }else if(StrKit.notBlank(outRefundNo)) {
//			// params.put("out_trade_no", outTradeNo);
//			// }else {
//			// throw new
//			// BusinessException(ErrorCode.PARAMETER_ERROR.getCode(),"refund_id , out_refund_no , transaction_id , out_trade_no不能都为空");
//			// }
//			params.put("out_trade_no", outTradeNo);
//			params.put("out_refund_no", outRequestNo);
//			params.put(
//					"sign",
//					PaymentKit.createSign(params,
//							wxPayApiConfig.getPaternerKey()));
//			String xmlResult = WxPayApi.orderRefundQuery(false, params);
//			Map<String, String> resultMap = PaymentKit.xmlToMap(xmlResult);
//
//			String return_code = resultMap.get("return_code");
//			String return_msg = resultMap.get("return_msg");
//			if (!PaymentKit.codeIsOK(return_code)) {
//				response.setErrorCode(return_code);
//				response.setErrorMsg(return_msg);
//				return response;
//			}
//			String result_code = resultMap.get("result_code");
//			if (!PaymentKit.codeIsOK(result_code)) {
//				String err_code_des = resultMap.get("err_code_des");
//				String err_code = resultMap.get("err_code");
//				response.setErrorCode(result_code);
//				response.setErrorMsg("err_code:" + err_code + " err_code_des:"
//						+ err_code_des);
//				return response;
//			}
//
//			/**
//			 * 退款状态：
//			 * 
//			 * SUCCESS—退款成功
//			 * 
//			 * REFUNDCLOSE—退款关闭。
//			 * 
//			 * PROCESSING—退款处理中
//			 * 
//			 * CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，
//			 * 
//			 * 可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。
//			 */
//
//			String refund_status = resultMap.get("refund_status_0");
//			switch (refund_status) {
//			// 退款成功
//			case "SUCCESS":
//				refundStatus = RefundStatusEnum.SUCCESS.getCode();
//				break;
//			// 退款关闭
//			case "REFUNDCLOSE":
//				refundStatus = RefundStatusEnum.FAIL.getCode();
//				break;
//			// 退款处理中
//			case "PROCESSING":
//				refundStatus = RefundStatusEnum.COMMIT_SUCCESS.getCode();
//				break;
//			// 退款异常
//			case "CHANGE":
//				refundStatus = RefundStatusEnum.FAIL.getCode();
//				break;
//			default:
//				break;
//			}
//			// 退款成功或者失败，此处不做处理，由退款回调来处理，这里只做数据返回
//		}
//
//		data.put("businessNo", refundRecord.getRefundTrxNo());
//		data.put("tradeNO", refundRecord.getTradeNO());
//		data.put("refundFee", refundRecord.getRefundFee());
//		data.put("channel", Constants.WX_APPPAY_PAYEE);
//		data.put("refundStatus", refundStatus);
//		response.setData(data);
//		return response;
//	}

//	@Override
//	public String refundAsynNotify(HttpServletRequest request) throws Exception {
//		// TODO Auto-generated method stub
//		String xmlMsg = HttpKit.readData(request);
//		logger.info("退款结果通知==" + xmlMsg);
//		Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);
//		boolean isSuccess = false;
//		// 商户号
//		String return_code = params.get("return_code");
//		String return_message = params.get("return_message");
//		if (("SUCCESS").equals(return_code)) {
//			String appid = params.get("appid");
//			String mch_id = params.get("mch_id");
//			String nonce_str = params.get("nonce_str");
//			String req_info = params.get("req_info");
//			String req_xml = WechatAESUtil.decryptData(req_info, WechatAESUtil
//					.generateKey(WxPayApiConfigKit
//							.getWxPayApiBaseConfigByAppId(appid)
//							.getPaternerKey()));
//			/**
//			 * <root> <out_refund_no><![CDATA[1530262569938]]></out_refund_no>
//			 * <out_trade_no
//			 * ><![CDATA[x3xhx1oqasqft4akcdv38o2rdtk8iusi]]></out_trade_no>
//			 * <refund_account
//			 * ><![CDATA[REFUND_SOURCE_RECHARGE_FUNDS]]></refund_account>
//			 * <refund_fee><![CDATA[1]]></refund_fee>
//			 * <refund_id><![CDATA[50000507062018062905301192985]]></refund_id>
//			 * <refund_recv_accout><![CDATA[平安银行信用卡7966]]></refund_recv_accout>
//			 * <refund_request_source><![CDATA[API]]></refund_request_source>
//			 * <refund_status><![CDATA[SUCCESS]]></refund_status>
//			 * <settlement_refund_fee><![CDATA[1]]></settlement_refund_fee>
//			 * <settlement_total_fee><![CDATA[1]]></settlement_total_fee>
//			 * <success_time><![CDATA[2018-06-29 16:57:04]]></success_time>
//			 * <total_fee><![CDATA[1]]></total_fee>
//			 * <transaction_id><![CDATA[4200000150201806295932708821
//			 * ]]></transaction_id> </root>
//			 */
//			Map<String, String> resultMap = PaymentKit.xmlToMap(req_xml);
//			logger.info("resultMap============"
//					+ JSONObject.toJSONString(resultMap));
//			// 商户退款单号
//			String out_refund_no = resultMap.get("out_refund_no");
//			// 商户订单号
//			String out_trade_no = resultMap.get("out_trade_no");
//			// 退款资金来源
//			String refund_account = resultMap.get("refund_account");
//			// 申请退款金额
//			String refund_fee = resultMap.get("refund_fee");
//			// 微信退款单号
//			String refund_id = resultMap.get("refund_id");
//			// 退款入账账户
//			String refund_recv_accout = resultMap.get("refund_recv_accout");
//			// 退款发起来源
//			String refund_request_source = resultMap
//					.get("refund_request_source");
//			// 退款状态 SUCCESS-退款成功 CHANGE-退款异常 REFUNDCLOSE—退款关闭
//			String refund_status = resultMap.get("refund_status");
//			// 退款金额
//			String settlement_refund_fee = resultMap
//					.get("settlement_refund_fee");
//			// 应结订单金额
//			String settlement_total_fee = resultMap.get("settlement_total_fee");
//			// 退款成功时间
//			String success_time = resultMap.get("success_time");
//			// 订单金额
//			String total_fee = resultMap.get("total_fee");
//			// 微信订单号
//			String transaction_id = resultMap.get("transaction_id");
//
//			// 修改支付记录表退款内容
//			PayRecordDto payRecordQuery = new PayRecordDto();
//			payRecordQuery.setPayOrderNO(out_trade_no);
//			PayRecordDto payRecord = payRecordService.find(payRecordQuery);
//			if (payRecord != null) {
//				RefundRecordDto refundRecordQuery = new RefundRecordDto();
//				refundRecordQuery.setRefundTrxNo(out_refund_no);
//				RefundRecordDto refundRecord = refundRecordService
//						.find(refundRecordQuery);
//				if (refundRecord != null) {
//					// SUCCESS-退款成功 CHANGE-退款异常 REFUNDCLOSE—退款关闭
//					String refundStatus = "";
//					// 执行业务逻辑
//					if ("SUCCESS".equals(refund_status)) {
//						// 受理成功
//						refundStatus = RefundStatusEnum.SUCCESS.getCode();
//						// 退款完成时间
//						refundRecord.setRefundCompleteTime(DateUtils
//								.formatDate(success_time,
//										DateUtils.FULL_DATE_STR));
//						// 退款入账账户
//						if (StringUtils.isNotEmpty(refund_recv_accout)) {
//							refundRecord
//									.setRefundRecvAccout(refund_recv_accout);
//						}
//						refundRecord.setRefundStatus(refundStatus);
//						refundRecordService.update(refundRecord);
//
//						payRecord
//								.setIsRefund(IsRefundEnum.REfUND_YES.getCode());
//						BigDecimal totalRefundFee = refundRecordService
//								.countRefundFeeByPayOrder(out_trade_no);
//						logger.info("totalRefundFee:" + totalRefundFee);
//						// BigDecimal refundFee = payRecord.getTotalRefundFee();
//						// BigDecimal refundAmount =
//						// refundRecord.getRefundFee();
//						// logger.info("refundFee:"+refundFee);
//						// logger.info("refundAmount:"+refundAmount);
//						// //查询
//						// BigDecimal totalRefundFee =
//						// BigDecimalUtils.add(refundFee, refundAmount);
//						payRecord.setTotalRefundFee(totalRefundFee);
//						if (BigDecimalUtils.isEqual(totalRefundFee,
//								payRecord.getTotalFee())) {
//							payRecord.setTradeStatus("TRADE_CLOSED");
//						}
//						payRecordService.update(payRecord);
//					} else {
//						refundStatus = RefundStatusEnum.FAIL.getCode();
//						refundRecord.setRefundStatus(refundStatus);
//						refundRecordService.update(refundRecord);
//						isSuccess = true;
//					}
//					String notifyURL = refundRecord.getResultNotifyUrl();
//					// 存储回调记录表 TODO
//					NotifyRecordDto notifyRecord = new NotifyRecordDto();
//					notifyRecord.setNotifyUrl(notifyURL);
//					notifyRecord.setBusinessOrderNo(out_refund_no);
//					JSONObject requestParamsJson = new JSONObject();
//					requestParamsJson.put("businessNo", out_refund_no);
//					requestParamsJson.put("tradeNO", refund_id);
//					requestParamsJson.put("refundStatus", refundStatus);
//					requestParamsJson.put("refundFee",
//							refundRecord.getRefundFee());
//					requestParamsJson.put("channel", Constants.WX_APPPAY_PAYEE);
//
//					String requestParams = requestParamsJson.toJSONString();
//					notifyRecord.setRequestParams(requestParams);
//					notifyRecord.setNotifyType(NotifyTypeEnum.REFUNDNOTIFY
//							.getCode());
//					notifyRecord.setCreateTime(new Date());
//					notifyRecordService.save(notifyRecord);
//
//					// 推送异步任务
//					JSONObject requestJson = new JSONObject();
//					requestJson.put("moduleCode", Constants.MODULE_CODE);
//					requestJson.put("taskName",
//							NotifyTypeEnum.REFUNDNOTIFY.getDesc());
//					requestJson.put("businessNo", out_refund_no);
//					requestJson.put("notifyUrl", notifyURL);
//					requestJson.put("requestParams", requestParams);
//					isSuccess = createTask(requestJson);
//
//				}
//			}
//		}
//		Map<String, String> xml = new HashMap<String, String>();
//		if (isSuccess) {
//			xml.put("return_code", "SUCCESS");
//			xml.put("return_msg", "OK");
//		}
//		return PaymentKit.toXml(xml);
//	}

//	@Override
//	public ResponseResult<JSONObject> downloadBill(
//			@RequestBody JSONObject request) {
//		ResponseResult<JSONObject> response = new ResponseResult<JSONObject>();
//		String accountCode = request.getString("accountCode");
//		WxPayApiConfig wxPayApiConfig = getWxPayApiConfigByCode(accountCode);
//		String billDateTemp = request.getString("billDate");
//		String billDate = DateUtils.format(
//				DateUtils.formatDate(billDateTemp, DateUtils.SIMPLE_DATE_STR),
//				DateUtils.DATE_FORMAT_YYYYMMDD);
//		Map<String, String> params = new HashMap<String, String>();
//		/**
//		 * 公众账号ID appid 是 String(32) wx8888888888888888
//		 * 微信分配的公众账号ID（企业号corpid即为此appId） 商户号 mch_id 是 String(32) 1900000109
//		 * 微信支付分配的商户号 随机字符串 nonce_str 是 String(32)
//		 * 5K8264ILTKCH16CQ2502SI8ZNMTM67VS 随机字符串，不长于32位。推荐随机数生成算法 签名 sign 是
//		 * String(32) C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名生成算法 签名类型
//		 * sign_type 否 String(32) HMAC-SHA256 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
//		 * 对账单日期 bill_date 是 String(8) 20140603 下载对账单的日期，格式：20140603 账单类型
//		 * bill_type 是 String(8) ALL ALL，返回当日所有订单信息，默认值
//		 * 
//		 * SUCCESS，返回当日成功支付的订单
//		 * 
//		 * REFUND，返回当日退款订单
//		 * 
//		 * RECHARGE_REFUND，返回当日充值退款订单
//		 * 
//		 * 压缩账单 tar_type 否 String(8) GZIP
//		 * 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
//		 */
//		params.put("appid", wxPayApiConfig.getAppId());
//		params.put("mch_id", wxPayApiConfig.getMchId());
//		params.put("nonce_str", System.currentTimeMillis() + "");
//		params.put("sign_type", "MD5");
//		params.put("bill_date", billDate);
//		params.put("bill_type", "ALL");
//		// params.put("tar_type", "GZIP");
//		params.put("sign",
//				PaymentKit.createSign(params, wxPayApiConfig.getPaternerKey()));
//		String result = WxPayApi.downloadBill(wxPayApiConfig.isSandbox(),
//				params);
//		logger.info("result:" + result);
//		// 解析数据可参照文章 https://www.cnblogs.com/mgqy/articles/7657483.html
//		// 把第一行表头去掉
//		String tradeMsg = result.substring(result.indexOf("`"));
//		// 去掉汇总数据，并且去掉"`"这个符号
//		String tradeInfo = tradeMsg.substring(0, tradeMsg.indexOf("总"))
//				.replace("`", "");
//		// 根据\r\n来区分
//		String[] tradeArray = tradeInfo.split("\r\n");
//		List<PtWxTradeDetail> billData = new ArrayList<PtWxTradeDetail>();
//		for (String tradeDetailInfo : tradeArray) {
//			logger.info("tradeDetailInfo:" + tradeDetailInfo);
//			String[] tradeDetailArray = tradeDetailInfo.split(",");
//			// 防错误数据
//			if (tradeDetailArray.length < 24) {
//				continue;
//			}
//			PtWxTradeDetail entity = new PtWxTradeDetail();
//			entity.setTransDate(tradeDetailArray[0]);// 交易时间
//			entity.setCommonId(tradeDetailArray[1]);// 公众账号ID
//			entity.setBusinessNo(tradeDetailArray[2]);// 商户号
//			entity.setChildBusinessNo(tradeDetailArray[3]);// 子商户号
//			entity.setEquipmentNo(tradeDetailArray[4]);// 设备号
//			entity.setWxOrderNo(tradeDetailArray[5]);// 微信订单号
//			entity.setBusinessOrderNo(tradeDetailArray[6]);// 商户订单号
//			entity.setUserIdentity(tradeDetailArray[7]);// 用户标识
//			entity.setTransType(tradeDetailArray[8]);// 交易类型
//			entity.setTransStatus(tradeDetailArray[9]);// 交易状态
//			entity.setPaymentBank(tradeDetailArray[10]);// 付款银行
//			entity.setCurrency(tradeDetailArray[11]);// 货币种类
//			entity.setTotalAmount(tradeDetailArray[12]);// 总金额
//			entity.setRedEnvelopesAmount(tradeDetailArray[13]);// 企业红包金额
//			entity.setWxRefundNo(tradeDetailArray[14]);// 微信退款单号
//			entity.setBusinessRefundNo(tradeDetailArray[15]);// 商户退款单号
//			entity.setRefundAmount(tradeDetailArray[16]);// 退款金额
//			entity.setRedEnvelopesRefundAmount(tradeDetailArray[17]);// 企业红包退款金额
//			entity.setRefundType(tradeDetailArray[18]);// 退款类型
//			entity.setRefundStatus(tradeDetailArray[19]);// 退款状态
//			entity.setBusinessName(tradeDetailArray[20]);// 商品名称
//			entity.setBusinessData(tradeDetailArray[21]);// 商户数据包
//			entity.setFee(tradeDetailArray[22]);// 手续费
//			entity.setRate(tradeDetailArray[23]);// 费率
//			entity.setBillDate(billDateTemp);// 账单日期
//			billData.add(entity);
//		}
//		JSONObject data = new JSONObject();
//		data.put("billData", billData);
//		response.setData(data);
//		return response;
//	}

	/**
	 * 组装支付参数
	 * 
	 * @param request
	 * @return
	 */
	private WxPayApiConfig buildWxPayApiConfig(JSONObject request) {
		WxPayApiConfig wxPayApiConfig = new WxPayApiConfig();
		String appid = request.getString("appId");
		String mch_id = request.getString("mchId");
		String notify_url = request.getString("notifyUrl");
		String outTradeNo = request.getString("outTradeNo");// 商户订单号
		String totalFee = BigDecimalUtils.roundBigDecimal(
				BigDecimalUtils.mul(BigDecimalUtils.transNullStringToZERO(request.getString("totalFee")),
						BigDecimalUtils.BigDecimal_100),
				0).toString();
		logger.info("totalFee===" + totalFee);

		String body = (StringUtils.isEmpty(request.getString("body")) ? Constants.WX_APPPAY_BODY
				: request.getString("body"));
		String spbillCreateIp = request.getString("spbillCreateIp");
		String expireTime = request.getString("expireTime"); // 设置失效时间

		Date now = new Date();
		Date time_expire = null;
		boolean flag = true;
		if (StringUtils.isNotEmpty(expireTime)) {
			time_expire = DateUtils.formatDate(expireTime, DateUtils.FULL_DATE_STR);
			if (time_expire != null && time_expire.getTime() > now.getTime()) {
				// 微信最长两小时 超两小时设置成两个小时
				if (time_expire.getTime() - now.getTime() > 2 * 3600 * 1000) {
					time_expire = DateUtils.addHour(now, 2);
				}
				flag = false;
			}
		}
		if (flag) {
			time_expire = DateUtils.addMinute(now, 30);
		}
		if (time_expire != null) {
			String timeStart = DateUtils.formatString(now, DateUtils.DATE_FORMAT_YYYYMMDDHHMMSS);
			String timeExpire = DateUtils.formatString(time_expire, DateUtils.DATE_FORMAT_YYYYMMDDHHMMSS);
			wxPayApiConfig.setTimeStart(timeStart);
			wxPayApiConfig.setTimeExpire(timeExpire);
		}

		wxPayApiConfig.setPaternerKey("s5vFlSQbm7qL7688jBPcoF0Ewrh0ePnN");// TODO
		wxPayApiConfig.setAppId(appid);
		wxPayApiConfig.setMchId(mch_id);
		wxPayApiConfig.setNotifyUrl(notify_url);
		wxPayApiConfig.setBody(body);
		wxPayApiConfig.setOutTradeNo(outTradeNo);
		wxPayApiConfig.setTotalFee(totalFee);
		wxPayApiConfig
				.setSpbillCreateIp(StringUtils.isEmpty(spbillCreateIp) ? Constants.WX_DEFAULT_IP : spbillCreateIp);

		return wxPayApiConfig;
	}
}
