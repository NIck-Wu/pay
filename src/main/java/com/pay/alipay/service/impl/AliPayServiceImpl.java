package com.pay.alipay.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.config.Constants;
import com.ext.util.StringUtils;
import com.pay.alipay.alipayutil.AliPayApi;
import com.pay.alipay.alipayutil.AliPayApiConfig;
import com.pay.alipay.alipayutil.AliPayApiConfigKit;
import com.pay.alipay.service.AliPayService;
import com.pay.validateHelper.DateUtils;
import com.utils.ResponseResult;

@Service
@Transactional
public class AliPayServiceImpl implements AliPayService {
	private static Logger logger = Logger.getLogger(AliPayServiceImpl.class);

//	@Override
//	public String aliPayAsynNotify(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		String response = "failure";
//		try {
//			// 获取支付宝POST过来反馈信息
//			Map<String, String> params = AliPayApi.toMap(request);
//			for (Map.Entry<String, String> entry : params.entrySet()) {
//				logger.info(entry.getKey() + " = " + entry.getValue());
//			}
//			//支付宝分配给开发者的应用Id
//			String app_id = params.get("app_id");
//			AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfigByAppId(app_id);
//			if(aliPayApiConfig!=null) {
//				boolean verify_result = AlipaySignature.rsaCheckV1(params, aliPayApiConfig.getAlipayPublicKey(), aliPayApiConfig.getCharset(),
//						aliPayApiConfig.getSignType());
//				if (verify_result) {// 验证成功
//					// TODO 请在这里加上商户的业务逻辑程序代码 异步通知可能出现订单重复通知 需要做去重处理
//					logger.info("notify_url 验证成功succcess");
//					//交易状态
//					String trade_status = StringUtils.cancelNull(params.get("trade_status"));
////					WAIT_BUYER_PAY	交易创建，等待买家付款
////					TRADE_CLOSED	未付款交易超时关闭，或支付完成后全额退款
////					TRADE_SUCCESS	交易支付成功
////					TRADE_FINISHED	交易结束，不可退款
//					switch(trade_status) {
//					case "WAIT_BUYER_PAY":response = "success";break;
//					//超时或全额退款
//					case "TRADE_CLOSED":response = notifyByClosed(params);break;
//					//支付成功或部分退款
//					case "TRADE_SUCCESS": response = notifyBySuccess(params);break;
//					case "TRADE_FINISHED":response = "success";break;
//					default: break;
//					}
//				} else {
//					logger.info("notify_url 验证失败");
//					// TODO
//				}
//			}
//		} catch (AlipayApiException e) {
//			e.printStackTrace();
//		}	
//		return response;
//	}
//	public String notifyBySuccess(Map<String, String> params) {
//		String response = "failure";
//		//out_biz_no 商户业务号   商户业务ID，主要是退款通知中返回退款申请的流水号
//		String out_biz_no = params.get("out_biz_no");
//		//退款流水号为空时为支付成功回调
//		if(StringUtils.isEmptyStr(out_biz_no)) {
//			response = payNotifyRecord(params);
//		}else {
//          //退款回调
//			response = tradeRefundNotifyRecord(params);
//		}
//		return response;
//	}
//	public String notifyByClosed(Map<String, String> params) {
//		//交易创建时间
//		//gmt_create = 2018-07-23 10:49:26
//		//charset = utf-8
//		//seller_email = cw@wsh.net.cn
//		//subject = 广明灯购课缴费
//		//sign = Spf0Fax7kHZ3jce82esqI+uR+jYHmEhcQxf2hFhtsgfXTl0JQhwiIanEhLcXp/WS+nY75Y4CaxdtygMukV4Yi2uCd7A223qZSokss5fncPTdwtGG+HAoakucxGHcZePrf1FsCutukq4fDlETgM4eSSEim6BONGWDwH5poVs7GgzLQ9vsbfgevuSav7QJWEEfG0AhPfhXxR5CziqxlBBgQpx+xOtzHkG5ASaVVog0K3fxoO3n0wgWE+AIUlYeMx8HQu9vsr3WtuWvn7ofxN/AzYw9/1CMuqsuyX6fu+b3HnuMkNreIFpOlbEThgiTaqyr6Q25JWOm/wam30FiLCORNg==
//		//body = 广明灯
//		//buyer_id = 2088102431996098
//		//notify_id = b35dcdcee6b87a008c65dcec95da56bgp5
//		//notify_type = trade_status_sync
//		//trade_status = TRADE_CLOSED
//		//app_id = 2017112000054779
//		//sign_type = RSA2
//		//seller_id = 2088421918109951
//		//gmt_payment = 2018-07-23 10:49:27
//		//notify_time = 2018-07-23 17:46:10
//		//gmt_refund = 2018-07-23 17:42:17.965
//		//out_biz_no = 123456789001
//		//version = 1.0
//		//out_trade_no = 6858ea06f43d4225aa9cc1c21e6b1140
//		//total_amount = 0.01
//		//refund_fee = 0.01
//		//trade_no = 2018072321001004090590353157
//		//auth_app_id = 2017112000054779
//		//buyer_logon_id = xzq***@sina.com
//		//gmt_close = 2018-07-23 17:42:17
//		return tradeRefundNotifyRecord(params);
//	}
	/**
	 *  支付成功回调信息记录
	 * @param params
	 * @return
	 */
//	public String payNotifyRecord(Map<String, String> params) {
//		String response = "failure";
//		//String out_biz_no = params.get("out_biz_no");
//		//商户订单号
//		String out_trade_no = params.get("out_trade_no");
//		//订单金额
//		String total_amount = params.get("total_amount");
//		//修改支付记录表 TODO
//		PayRecordDto payRecordQuery = new PayRecordDto();
//		payRecordQuery.setPayOrderNO(out_trade_no);
//		PayRecordDto payRecord = payRecordService.find(payRecordQuery);
//		if(payRecord!=null) {
//			//支付宝交易号
//			String trade_no = params.get("trade_no");
//			payRecord.setTradeNO(trade_no);
//			//买家支付宝账号
//			String buyer_logon_id = params.get("buyer_logon_id");
//			//用户ID可通过订单找到，此处存储买家支付宝账号
//			payRecord.setBuyerID(buyer_logon_id);
//			payRecord.setPayStatus(PayStatusEnum.SUCCESS.getCode());
//			//状态
//			String trade_status = StringUtils.cancelNull(params.get("trade_status"));
//			payRecord.setTradeStatus(trade_status);
//			//交易付款时间
//			String gmt_payment = params.get("gmt_payment");
//			if(StringUtils.isNotEmpty(gmt_payment)) {
//				payRecord.setTradeTime(DateUtils.formatDate(gmt_payment, DateUtils.FULL_DATE_STR));
//			}
//			payRecord.setLastUpdate(new Date());
//			payRecordService.update(payRecord);
//			//业务回调地址
//			String notifyURL = payRecord.getNotifyURL();
//			//存储回调记录表 TODO
//			NotifyRecordDto notifyRecord = new NotifyRecordDto();
//			notifyRecord.setNotifyUrl(notifyURL);
//			notifyRecord.setBusinessOrderNo(out_trade_no);
//			JSONObject requestParamsJson = new JSONObject();
//			requestParamsJson.put("businessNo", out_trade_no);
//			requestParamsJson.put("tradeNO", trade_no);
//			requestParamsJson.put("totalFee", total_amount);
//			requestParamsJson.put("channel", Constants.ALI_APPPAY_PAYEE);
//
//			String requestParams = requestParamsJson.toJSONString();
//			notifyRecord.setRequestParams(requestParams);
//			notifyRecord.setNotifyType(NotifyTypeEnum.PAYNOTIFY.getCode());
//			notifyRecord.setCreateTime(new Date());
//			notifyRecordService.save(notifyRecord);
//			//推送异步任务
//			JSONObject requestJson = new JSONObject();
//			requestJson.put("moduleCode", Constants.MODULE_CODE);
//			requestJson.put("taskName", NotifyTypeEnum.PAYNOTIFY.getDesc());
//			requestJson.put("businessNo", out_trade_no);
//			requestJson.put("notifyUrl", notifyURL);
//			requestJson.put("requestParams", requestParams);
//			if(createTask(requestJson)) {
//				response = "success";
//			}
//		}
//		return response;
//	}
	
	/**
	 * 退款回调记录信息
	 * @param params
	 * @return
	 */
//	public String tradeRefundNotifyRecord(Map<String, String> params) {
//		String response = "failure";
//		String out_biz_no = params.get("out_biz_no");
//		//yyyy-MM-dd HH:mm:ss.S
//		String gmt_refund = params.get("gmt_refund");
//		//商户订单号
//		String out_trade_no = params.get("out_trade_no");
//		//流水号
//		String trade_no = params.get("trade_no");
//		//退款总金额
//		String refund_fee = params.get("refund_fee");
//		//状态
//		String trade_status = StringUtils.cancelNull(params.get("trade_status"));
//		//修改支付记录表退款内容
//		PayRecordDto payRecordQuery = new PayRecordDto();
//		payRecordQuery.setPayOrderNO(out_trade_no);
//		PayRecordDto payRecord = payRecordService.find(payRecordQuery);
//		if(payRecord!=null) {
//			payRecord.setIsRefund(IsRefundEnum.REfUND_YES.getCode());
//			payRecord.setTotalRefundFee(new BigDecimal(refund_fee));
//			payRecord.setTradeStatus(trade_status);
//			payRecordService.update(payRecord);
//
//			RefundRecordDto refundRecordQuery = new RefundRecordDto();
//			refundRecordQuery.setRefundTrxNo(out_biz_no);
//			RefundRecordDto refundRecord = refundRecordService.find(refundRecordQuery);
//			if(refundRecord!=null){
//				//受理成功
//				refundRecord.setRefundStatus(RefundStatusEnum.SUCCESS.getCode());
//				//退款完成时间
//				refundRecord.setRefundCompleteTime(DateUtils.formatDate(gmt_refund, DateUtils.ALL_FULL_DATE_STR));
//				refundRecordService.update(refundRecord);
//				String notifyURL = refundRecord.getResultNotifyUrl();
//
//				//存储回调记录表 TODO
//				NotifyRecordDto notifyRecord = new NotifyRecordDto();
//				notifyRecord.setNotifyUrl(notifyURL);
//				notifyRecord.setBusinessOrderNo(out_biz_no);
//				JSONObject requestParamsJson = new JSONObject();
//				requestParamsJson.put("businessNo", out_biz_no);
//				requestParamsJson.put("tradeNO", trade_no);
//				requestParamsJson.put("refundStatus",RefundStatusEnum.SUCCESS.getCode());
//				requestParamsJson.put("refundFee", refundRecord.getRefundFee());
//				requestParamsJson.put("channel", Constants.ALI_APPPAY_PAYEE);
//
//				String requestParams = requestParamsJson.toJSONString();
//				notifyRecord.setRequestParams(requestParams);
//				notifyRecord.setNotifyType(NotifyTypeEnum.REFUNDNOTIFY.getCode());
//				notifyRecord.setCreateTime(new Date());
//				notifyRecordService.save(notifyRecord);
//				//推送异步任务
//				JSONObject requestJson = new JSONObject();
//				requestJson.put("moduleCode", Constants.MODULE_CODE);
//				requestJson.put("taskName", NotifyTypeEnum.REFUNDNOTIFY.getDesc());
//				requestJson.put("businessNo", out_biz_no);
//				requestJson.put("notifyUrl", notifyURL);
//				requestJson.put("requestParams", requestParams);
//				if(createTask(requestJson)) {
//					response = "success";
//				}
//			}
//		}
//		return response;
//	}
	
	/**
	 * 推送异步任务
	 * @param requestJson
	 * @return
	 */
//	public boolean createTask(JSONObject requestJson) {
//		boolean successInd = false;
//		ResponseResult<JSONObject> result = asynTaskApiClient.createTask(requestJson);
//		if(result!=null && ErrorCode.SUCCESS.getCode().equals(result.getErrorCode())){
//			JSONObject data = result.getData();
//			if(data!=null) {
//				String returnCode = data.getString("returnCode");
//				String returnMsg = data.getString("returnMsg");
//				if(Constants.RETURE_CODE_SUCCESS.equals(returnCode)&&Constants.RETURE_MSG_SUCCESS.equals(returnMsg)) {
//					successInd = true;
//				}
//			}
//		}
//		return successInd;
//	}
	
	@Override
	public ResponseResult<JSONObject> appPay(JSONObject request) throws AlipayApiException{
		// TODO Auto-generated method stub
		String accountCode = request.getString("accountCode");
		AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfig(accountCode);
		String totalFee = request.getString("totalFee");
		String outTradeNo = request.getString("outTradeNo");
		String body = (StringUtils.isEmpty(request.getString("body"))?Constants.ALI_APPPAY_BODY:request.getString("body"));
		String subject = (StringUtils.isEmpty(request.getString("subject"))?Constants.ALI_APPPAY_SUBJECT:request.getString("subject"));
		String timeoutExpress = "";	// 设置失效时间
		String expireTime = request.getString("expireTime");
		Date now = new Date();
		Date time_expire = null;
		boolean flag = true;
		if (StringUtils.isNotEmpty(expireTime)) {
			time_expire = DateUtils.formatDate(expireTime, DateUtils.FULL_DATE_STR);
			if (time_expire != null && time_expire.getTime() > now.getTime()) {
				//支付宝小于1分钟设置为1分钟 大于15天设置为15天
				long timeTemp = time_expire.getTime() - now.getTime();
				if (timeTemp < 60 * 1000) {
					timeoutExpress = "1m";
					time_expire = DateUtils.addMinute(now, 1);
				}else if(timeTemp>=60 * 1000 && timeTemp < 15*24*3600*1000) {
					Long timeOut = timeTemp/(60*1000);
					timeoutExpress = timeOut+"m" ;
					//time_expire = DateUtils.addMinute(now, timeOut.intValue());
				}else {
					timeoutExpress = "15d";
					time_expire = DateUtils.addDate(now, 15);
				}
				flag = false;
			}
		}
		if (flag) {
			timeoutExpress = Constants.ALI_APPPAY_TIMEOUT_EXPRESS;
			time_expire = DateUtils.addMinute(now, 30);
		}

		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		//对一笔交易的具体描述信息
		model.setBody(body);
		//商品的标题/交易标题/订单标题/订单关键字
		model.setSubject(subject);
		//该笔订单允许的最晚付款时间 m代表分钟
		//logger.info("timeoutExpress:"+timeoutExpress);
		model.setTimeoutExpress(timeoutExpress);
		//坑，实际失效使用的是timeExpire 格式为yyyy-MM-dd HH:mm
		model.setTimeExpire(DateUtils.formatString(time_expire, DateUtils.PART_DATE_STR));
		//销售产品码，商家和支付宝签约的产品码
		model.setProductCode(Constants.ALI_APPPAY_PRODUCT_CODE);
		//商户网站唯一订单号 我们传入的为支付凭证号
		model.setOutTradeNo(outTradeNo);
		//支付总金额
		model.setTotalAmount(totalFee);
		//支付平台回调地址
		String payNotifyUrl = request.getString("notifyURL");//支付回调地址
	    //存储支付记录表 TODO  start========================================
		//存储支付记录表  end========================================
		
		AlipayTradeAppPayResponse response = AliPayApi.appPay(aliPayApiConfig,model,payNotifyUrl);
		JSONObject result = new JSONObject();
		result.put("signData", response.getBody());
		return new ResponseResult<JSONObject>(result);
	}
	/**
	 *  wap支付
	 * @param request
	 * @param response
	 * @return
	 * @throws AlipayApiException
	 * @throws IOException
	 */
//	public String wapPay(JSONObject request,HttpServletResponse response) throws AlipayApiException,IOException{
//		String accountCode = request.getString("accountCode");
//		AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfig(accountCode);
//		if(aliPayApiConfig==null) {
//			throw new BusinessException("没有找到accountCode对应的支付配置信息!");
//		}
//		String outTradeNo = request.getString("outTradeNo");
//		String totalFee = request.getString("totalFee");
//		String buNotifyURL = request.getString("notifyURL");
//		String quitUrl = request.getString("quitUrl");
//		String body = (StringUtils.isEmptyStr(request.getString("body"))?Constants.ALI_APPPAY_BODY:request.getString("body"));
//		String subject = (StringUtils.isEmptyStr(request.getString("subject"))?Constants.ALI_APPPAY_SUBJECT:request.getString("subject"));
//		// 设置失效时间
//		String timeoutExpress = "";
//		String expireTime = request.getString("expireTime");
//		Date now = new Date();
//		Date time_expire = null;
//		boolean flag = true;
//		if (StringUtils.isNotEmpty(expireTime)) {
//			time_expire = DateUtils.formatDate(expireTime, DateUtils.FULL_DATE_STR);
//			if (time_expire != null && time_expire.getTime() > now.getTime()) {
//				// 支付宝小于1分钟设置为1分钟 大于15天设置为15天
//				long timeTemp = time_expire.getTime() - now.getTime();
//				if (timeTemp < 60 * 1000) {
//					timeoutExpress = "1m";
//					time_expire = DateUtils.addMinute(now, 1);
//				} else if (timeTemp >= 60 * 1000 && timeTemp < 15 * 24 * 3600 * 1000) {
//					Long timeOut = timeTemp / (60 * 1000);
//					timeoutExpress = timeOut + "m";
//					//time_expire = DateUtils.addMinute(now, timeOut.intValue());
//				} else {
//					timeoutExpress = "15d";
//					time_expire = DateUtils.addDate(now, 15);
//				}
//				flag = false;
//			}
//		}
//		if (flag) {
//			timeoutExpress = Constants.ALI_APPPAY_TIMEOUT_EXPRESS;
//			time_expire = DateUtils.addMinute(now, 30);
//		}
//		
//		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
//		model.setBody(body);
//		model.setSubject(subject);
//		//logger.info("timeoutExpress:"+timeoutExpress);
//		//该笔订单允许的最晚付款时间 m代表分钟
//		model.setTimeoutExpress(timeoutExpress);
//		//坑，实际失效使用的是timeExpire 格式为yyyy-MM-dd HH:mm
//		model.setTimeExpire(DateUtils.formatString(time_expire, DateUtils.PART_DATE_STR));
//		model.setOutTradeNo(outTradeNo);
//		model.setTotalAmount(totalFee);
//		//收款支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
//		model.setSellerId("");
//		//用户付款中途退出返回商户网站的地址
//		model.setQuitUrl(quitUrl);
//		model.setProductCode(Constants.ALI_WAPPAY_PRODUCT_CODE);
//		//支付平台回调地址
//		String payNotifyUrl = payConfiguration.getAliNotifyUrl();
//		//存储支付记录表 TODO  start========================================
//		PayRecordDto payRecordDto = buildPayRecord(outTradeNo,accountCode,totalFee,buNotifyURL,PayMethodEnum.WAP.getCode(),time_expire);
////		PayRecordDto payRecordDto = new PayRecordDto();
////		//支付凭证号
////		payRecordDto.setPayOrderNO(outTradeNo);
////		payRecordDto.setAccountCode(accountCode);
////		//收款方
////		payRecordDto.setPayee(Constants.ALI_APPPAY_PAYEE);
////		//支付方法
////		payRecordDto.setPayMethod(PayMethodEnum.WAP.getCode());
////		//用户在商户app下的唯一标识
////		//payRcordDto.setBuyerID(buyerID);
////		//订单金额
////		payRecordDto.setTotalFee(new BigDecimal(totalFee));
////		//支付状态 
////		payRecordDto.setPayStatus(PayStatusEnum.PAYING.getCode());
////		//是否有退款操作
////		payRecordDto.setIsRefund(IsRefundEnum.REfUND_NO.getCode());
////		//已退款总金额
////		payRecordDto.setRefundFee(new BigDecimal(0));
////		//订单失效时间
////		payRecordDto.setTimeExpire(DateUtils.addMinute(new Date(), Constants.ALI_APPPAY_TIMEOUT_EXPRESS_INT));
////		//异步回调地址
////		payRecordDto.setNotifyURL(buNotifyURL);
////		payRecordDto.setCreateTime(new Date());
//		payRecordService.save(payRecordDto);
//		//存储支付记录表  end========================================
//
//		String result = AliPayApi.wapPay(aliPayApiConfig,response,model,"",payNotifyUrl);
//		return result;
//	}
//	public PayRecordDto buildPayRecord(String outTradeNo,String accountCode,String totalFee,String buNotifyURL,String payMethod,Date time_expire) {
//		PayRecordDto payRecordDto = new PayRecordDto();
//		//支付凭证号
//		payRecordDto.setPayOrderNO(outTradeNo);
//		payRecordDto.setAccountCode(accountCode);
//		//收款方
//		payRecordDto.setPayee(Constants.ALI_APPPAY_PAYEE);
//		//支付方法
//		payRecordDto.setPayMethod(payMethod);
//		//用户在商户app下的唯一标识
//		//payRcordDto.setBuyerID(buyerID);
//		//订单金额
//		payRecordDto.setTotalFee(new BigDecimal(totalFee));
//		//支付状态 
//		payRecordDto.setPayStatus(PayStatusEnum.PAYING.getCode());
//		//是否有退款操作
//		payRecordDto.setIsRefund(IsRefundEnum.REfUND_NO.getCode());
//		//已退款总金额
//		payRecordDto.setTotalRefundFee(new BigDecimal(0));
//		//订单失效时间
//		payRecordDto.setTimeExpire(time_expire);
//		//异步回调地址
//		payRecordDto.setNotifyURL(buNotifyURL);
//		payRecordDto.setCreateTime(new Date());
//		return payRecordDto;
//	}
//	@Override
//	public ResponseResult<JSONObject> tradeQuery(JSONObject request) throws AlipayApiException {
//		// TODO Auto-generated method stub
//		JSONObject response = new JSONObject();
////		String accountCode = request.getString("accountCode");
////		AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfig(accountCode);
////		if(aliPayApiConfig==null) {
////			throw new BusinessException("没有找到accountCode对应的支付配置信息!");
////		}
//		String outTradeNo = request.getString("outTradeNo");
//		
//		PayRecordDto payRecordQuery = new PayRecordDto();
//		payRecordQuery.setPayOrderNO(outTradeNo);
//		PayRecordDto payRecord = payRecordService.find(payRecordQuery);
//		if(payRecord==null) {
//			throw new BusinessException("未找到当前支付凭证号!");
//		}
//		String accountCode = payRecord.getAccountCode();
//		AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfig(accountCode);
//		if(aliPayApiConfig==null) {
//			throw new BusinessException("没有找到accountCode对应的支付配置信息!");
//		}
//		String payStatus = payRecord.getPayStatus();
//		//当前状态为支付中去查询支付宝接口
//		if(PayStatusEnum.PAYING.getCode().equals(payStatus)) {
//			String tradeNo = payRecord.getTradeNO();
//			AlipayTradeQueryModel model = new AlipayTradeQueryModel();
//			model.setOutTradeNo(outTradeNo);
//			if(StringUtils.isNotEmpty(tradeNo)) {
//				model.setTradeNo(tradeNo);
//			}
//			
//			AlipayTradeQueryResponse result = AliPayApi.tradeQuery(aliPayApiConfig,model);
//			if(result.isSuccess()) {
//				String tradeStatus = result.getTradeStatus();
//
//				switch(tradeStatus) {
//				//交易创建，等待买家付款
//				case "WAIT_BUYER_PAY":payStatus = PayStatusEnum.PAYING.getCode();break;
//				//超时或全额退款
//				case "TRADE_CLOSED":payStatus = PayStatusEnum.FAIL.getCode();break;
//				//支付成功或部分退款
//				case "TRADE_SUCCESS": payStatus = PayStatusEnum.SUCCESS.getCode();break;
//				//交易结束，不可退款
//				case "TRADE_FINISHED":payStatus = PayStatusEnum.SUCCESS.getCode();break;
//				default: break;
//				}
//				//支付成功或者失败，此处不做处理，由回调来处理，这里只做数据返回
//			}
//		}
//		response.put("payStatus", payStatus);
//		if(PayStatusEnum.SUCCESS.getCode().equals(payStatus)) {
//			response.put("businessNo", payRecord.getPayOrderNO());
//			response.put("tradeNO", payRecord.getTradeNO());
//			response.put("totalFee", payRecord.getTotalFee());
//			response.put("channel", Constants.ALI_APPPAY_PAYEE);
//		}
//		return new ResponseResult<JSONObject>(response);
//	}
	
//	@Override
//	public ResponseResult<JSONObject> tradeRefund(JSONObject request) throws AlipayApiException {
//		// TODO Auto-generated method stub
////		String accountCode = request.getString("accountCode");
////		AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfig(accountCode);
////		if(aliPayApiConfig==null) {
////			throw new BusinessException("没有找到accountCode对应的支付配置信息!");
////		}
//		JSONObject result = new JSONObject();
//		
//		String outTradeNo = request.getString("outTradeNo");
//		String outRequestNo = request.getString("outRequestNo");
//		String refundFee = request.getString("refundFee");
//		String refundNotifyURL = request.getString("refundNotifyURL");
//		String refundReason = request.getString("refundReason");
//	    Date now = new Date();
//		PayRecordDto payRecordQuery = new PayRecordDto();
//		payRecordQuery.setPayOrderNO(outTradeNo);
//		PayRecordDto payRecord = payRecordService.find(payRecordQuery);
//		if(payRecord==null) {
//			throw new BusinessException("未找到当前支付凭证号!");
//		}
//
//		String accountCode = payRecord.getAccountCode();
//		AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfig(accountCode);
//		if(aliPayApiConfig==null) {
//			throw new BusinessException("没有找到accountCode对应的支付配置信息!");
//		}
//
//        //防止重复提交退款
//		RefundRecordDto refundRecordQuery = new RefundRecordDto();
//		refundRecordQuery.setRefundTrxNo(outRequestNo);
//		RefundRecordDto refundRecordResult = refundRecordService.find(refundRecordQuery);
//		if(refundRecordResult!=null){
//			throw new BusinessException("当前退款凭证号已经发起退款，不能重复退款!");
//		}
//		
//		if("TRADE_SUCCESS".equals(payRecord.getTradeStatus())) {
//			String tradeNo = request.getString("tradeNo");
//			if(StringUtils.isEmptyStr(tradeNo)) {
//				tradeNo =payRecord.getTradeNO();
//			}
//			JSONObject tradeRefundModelJson =  new JSONObject();
//			tradeRefundModelJson.put("outTradeNo", outTradeNo);
//			tradeRefundModelJson.put("tradeNo", tradeNo);
//			tradeRefundModelJson.put("outRequestNo", outRequestNo);
//			tradeRefundModelJson.put("refundAmount", refundFee);
//			tradeRefundModelJson.put("refundReason", refundReason);
//
//			AlipayTradeRefundModel model = JSONObject.parseObject(tradeRefundModelJson.toJSONString(), AlipayTradeRefundModel.class);
//			AlipayTradeRefundResponse response = AliPayApi.tradeRefund(aliPayApiConfig, model);
//			logger.debug("tradeRefund response =="+JSONObject.toJSONString(response));
//			if(response.isSuccess()){
//				logger.debug("退款调用成功");
//				//TODO
//				payRecord.setIsRefund(IsRefundEnum.REfUND_YES.getCode());
//				//汇总金额
//				payRecord.setTotalRefundFee(new BigDecimal(response.getRefundFee()));
//				payRecordService.update(payRecord);
//				RefundRecordDto refundRecord = new RefundRecordDto();
//				//退款凭证号
//				refundRecord.setRefundTrxNo(outRequestNo);
//				//原支付凭证号
//				refundRecord.setPayOrderNO(payRecord.getPayOrderNO());
//				//原平台交易号
//				refundRecord.setTradeNO(payRecord.getTradeNO());
//				//原支付账号代码
//				refundRecord.setAccountCode(payRecord.getAccountCode());
//				//原收款方
//				refundRecord.setPayee(payRecord.getPayee());
//				//原支付方法
//				refundRecord.setPayMethod(payRecord.getPayMethod());
//				//退款金额
//				refundRecord.setRefundFee(new BigDecimal(refundFee));
//				//平台退款单号
//				refundRecord.setRefundPlatNo(response.getTradeNo());
//				//退款结果通知url TODO
//				refundRecord.setResultNotifyUrl(refundNotifyURL);
//				//退款状态
//				refundRecord.setRefundStatus(RefundStatusEnum.COMMIT_SUCCESS.getCode());
//				//退款接收账号
//				refundRecord.setRefundRecvAccout(payRecord.getBuyerID());
//				//退款请求时间
//				refundRecord.setRefundRequestTime(now);
//				//退款成功时间
//				refundRecord.setRefundSuccessTime(new Date());
//				//退款完成时间
//				//refundRecord.setRefundCompleteTime(refundCompleteTime);
//				//退款原因
//				refundRecord.setRefundReason(refundReason);
//				refundRecord.setCreateTime(new Date());
//				refundRecordService.save(refundRecord);
//			} else {
//				logger.debug("退款调用失败");
//				result.put("reason", "code="+response.getCode()+"  msg="+response.getMsg());
//			}
//			result.put("isSuccess", response.isSuccess());
//		}else {
//			result.put("isSuccess", false);
//			result.put("reason", "当前支付未成功付款或已全额退款,不能发起退款");
//		}
//
//		return new ResponseResult<JSONObject>(result);
//	}

//	@Override
//	public ResponseResult<JSONObject> tradeRefundQuery(JSONObject request) throws AlipayApiException {
//		// TODO Auto-generated method stub
//		JSONObject response = new JSONObject();
////		String accountCode = request.getString("accountCode");
////		AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfig(accountCode);
////		if(aliPayApiConfig==null) {
////			throw new BusinessException("没有找到accountCode对应的支付配置信息!");
////		}
//		//String outTradeNo = request.getString("outTradeNo");
//		String outRequestNo = request.getString("outRequestNo");
//
//		RefundRecordDto refundRecordQuery = new RefundRecordDto();
//		refundRecordQuery.setRefundTrxNo(outRequestNo);
//		RefundRecordDto refundRecord = refundRecordService.find(refundRecordQuery);
//		if(refundRecord==null){
//			throw new BusinessException("未找到当前退款凭证号!");
//		}
//
//		String accountCode = refundRecord.getAccountCode();
//		AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfig(accountCode);
//		if(aliPayApiConfig==null) {
//			throw new BusinessException("没有找到accountCode对应的支付配置信息!");
//		}
//
//		response.put("businessNo", refundRecord.getRefundTrxNo());
//		response.put("tradeNO", refundRecord.getTradeNO());
//		response.put("refundFee", refundRecord.getRefundFee());
//		response.put("channel", Constants.ALI_APPPAY_PAYEE);
//		response.put("refundStatus", refundRecord.getRefundStatus());
//		/**
//		//已返回退款状态
//		if(refundRecord.getRefundCompleteTime()!=null) {
//			response.put("businessNo", refundRecord.getRefundTrxNo());
//			response.put("tradeNO", refundRecord.getTradeNO());
//			response.put("refundFee", refundRecord.getRefundAmount());
//			response.put("channel", Constants.ALI_APPPAY_PAYEE);
//			response.put("refundStatus", refundRecord.getRefundStatus());
//		}else {
//			//支付宝未返回当前退款状态 查询无意义
//			PayRecordDto payRecordQuery = new PayRecordDto();
//			payRecordQuery.setPayOrderNO(outTradeNo);
//			PayRecordDto payRecord = payRecordService.find(payRecordQuery);
//			if(payRecord==null) {
//				throw new ParameterException("参数校验失败!未找到当前支付凭证号!");
//			}
//			
//			AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
//			model.setOutRequestNo(outRequestNo);
//			model.setOutTradeNo(outTradeNo);
//			String tradeNo = payRecordQuery.getTradeNO();
//			model.setTradeNo(tradeNo);
//			AlipayTradeFastpayRefundQueryResponse result =  AliPayApi.tradeRefundQuery(aliPayApiConfig, model);
//			logger.debug("tradeRefundQuery result =="+JSONObject.toJSONString(result));
//			if(result.isSuccess()){
//				logger.debug("调用成功");
//			} else {
//				logger.debug("调用失败");
//			}
//			//result.getRefundStatus()
//			response.put("businessNo", refundRecord.getRefundTrxNo());
//			response.put("tradeNO", refundRecord.getTradeNO());
//			response.put("refundFee", refundRecord.getRefundAmount());
//			response.put("channel", Constants.ALI_APPPAY_PAYEE);
//			response.put("refundStatus", result.getRefundStatus());
//		}
//		*/
//		return new ResponseResult<JSONObject>(response);
//	}
	/**
	 * 下载对账单
	 * @param request
	 * @return
	 */
//	public ResponseResult<JSONObject> dataDataServiceBill(@RequestBody JSONObject request) {
//		String accountCode = request.getString("accountCode");
//		JSONObject response = new JSONObject();
//		String billDownloadUrl = "";
//		AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfig(accountCode);
//		if(aliPayApiConfig==null) {
//			throw new BusinessException("没有找到accountCode对应的支付配置信息!");
//		}
//		String billDate = request.getString("billDate");
//		try {
//			AlipayDataDataserviceBillDownloadurlQueryModel model = new AlipayDataDataserviceBillDownloadurlQueryModel();
//			//bill_type	String	必选	10	账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
//			//trade指商户基于支付宝交易收单的业务账单；signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单；	trade
//			model.setBillType("trade");
//			//bill_date	String	必选	15	账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。	2016-04-05
//			model.setBillDate(billDate);
//			AlipayDataDataserviceBillDownloadurlQueryResponse result = AliPayApi.billDownloadurlQuery(aliPayApiConfig, model);
//			if(result!=null) {
//				billDownloadUrl = result.getBillDownloadUrl();
//			}
//		} catch (AlipayApiException e) {
//			e.printStackTrace();
//		}
//		response.put("billDownloadUrl", billDownloadUrl);
//		return new ResponseResult<JSONObject>(response);
//	}
}
