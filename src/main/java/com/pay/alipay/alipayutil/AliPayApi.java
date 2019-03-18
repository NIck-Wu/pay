package com.pay.alipay.alipayutil;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;




public class AliPayApi {
	private static Logger logger = Logger.getLogger(AliPayApi.class);
	/**
	 * app支付
	 * @param aliPayApiConfig
	 * @param model
	 * @param notifyUrl
	 * @return
	 * @throws AlipayApiException
	 */
	public static AlipayTradeAppPayResponse appPay(AliPayApiConfig aliPayApiConfig, AlipayTradeAppPayModel model, String notifyUrl) throws AlipayApiException{
		if(aliPayApiConfig==null) {
			throw new AlipayApiException(" aliPayApiConfig is null ");
		}
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		//这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeAppPayResponse response = aliPayApiConfig.getAlipayClient().sdkExecute(request);
		return response;
	}
	
	/**
	 * WAP支付
	 * @param aliPayApiConfig
	 * @param response
	 * @param model
	 * @param returnUrl
	 * @param notifyUrl
	 * @return String
	 * @throws AlipayApiException
	 * @throws IOException
	 */
	public static String wapPay(AliPayApiConfig aliPayApiConfig , HttpServletResponse response,AlipayTradeWapPayModel model,String returnUrl,String notifyUrl) throws AlipayApiException, IOException {
		if(aliPayApiConfig==null) {
			throw new AlipayApiException(" aliPayApiConfig is null ");
		}
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();// 创建API对应的request
		//alipayRequest.setReturnUrl(returnUrl);
		alipayRequest.setNotifyUrl(notifyUrl);// 在公共参数中设置回跳和通知地址
		alipayRequest.setBizModel(model);// 填充业务参数
		return aliPayApiConfig.getAlipayClient().pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
	}
	public static void wapPay1(AliPayApiConfig aliPayApiConfig,AlipayTradeWapPayModel model,String notifyUrl)  throws AlipayApiException{
		AlipayTradeWapPayRequest wapPayRequest = new AlipayTradeWapPayRequest();
		//String returnUrl = "";
		//wapPayRequest.setReturnUrl(returnUrl);
		wapPayRequest.setNotifyUrl(notifyUrl);// 在公共参数中设置回跳和通知地址
		wapPayRequest.setBizModel(model);// 填充业务参数
		/**
		wapPayRequest.setBizContent("{" +
		"\"body\":\"对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。\"," +
		"\"subject\":\"大乐透\"," +
		"\"out_trade_no\":\"70501111111S001111119\"," +
		"\"timeout_express\":\"90m\"," +
		"\"time_expire\":\"2016-12-31 10:05\"," +
		"\"total_amount\":9.00," +
		"\"seller_id\":\"2088102147948060\"," +
		"\"auth_token\":\"appopenBb64d181d0146481ab6a762c00714cC27\"," +
		"\"goods_type\":\"0\"," +
		"\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
		"\"quit_url\":\"http://www.taobao.com/product/113714.html\"," +
		"\"product_code\":\"QUICK_WAP_WAY\"," +
		"\"promo_params\":\"{\\\"storeIdType\\\":\\\"1\\\"}\"," +
		"\"royalty_info\":{" +
		"\"royalty_type\":\"ROYALTY\"," +
		"        \"royalty_detail_infos\":[{" +
		"          \"serial_no\":1," +
		"\"trans_in_type\":\"userId\"," +
		"\"batch_no\":\"123\"," +
		"\"out_relation_id\":\"20131124001\"," +
		"\"trans_out_type\":\"userId\"," +
		"\"trans_out\":\"2088101126765726\"," +
		"\"trans_in\":\"2088101126708402\"," +
		"\"amount\":0.1," +
		"\"desc\":\"分账测试1\"," +
		"\"amount_percentage\":\"100\"" +
		"          }]" +
		"    }," +
		"\"extend_params\":{" +
		"\"sys_service_provider_id\":\"2088511833207846\"," +
		"\"hb_fq_num\":\"3\"," +
		"\"hb_fq_seller_percent\":\"100\"," +
		"\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
		"\"card_type\":\"S0JP0000\"" +
		"    }," +
		"\"sub_merchant\":{" +
		"\"merchant_id\":\"19023454\"," +
		"\"merchant_type\":\"alipay: 支付宝分配的间连商户编号, merchant: 商户端的间连商户编号\"" +
		"    }," +
		"\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
		"\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
		"\"store_id\":\"NJ_001\"," +
		"\"settle_info\":{" +
		"        \"settle_detail_infos\":[{" +
		"          \"trans_in_type\":\"cardSerialNo\"," +
		"\"trans_in\":\"A0001\"," +
		"\"summary_dimension\":\"A0001\"," +
		"\"amount\":0.1" +
		"          }]" +
		"    }," +
		"\"invoice_info\":{" +
		"\"key_info\":{" +
		"\"is_support_invoice\":true," +
		"\"invoice_merchant_name\":\"ABC|003\"," +
		"\"tax_num\":\"1464888883494\"" +
		"      }," +
		"\"details\":\"[{\\\"code\\\":\\\"100294400\\\",\\\"name\\\":\\\"服饰\\\",\\\"num\\\":\\\"2\\\",\\\"sumPrice\\\":\\\"200.00\\\",\\\"taxRate\\\":\\\"6%\\\"}]\"" +
		"    }," +
		"\"specified_channel\":\"pcredit\"," +
		"\"business_params\":\"{\\\"data\\\":\\\"123\\\"}\"," +
		"\"ext_user_info\":{" +
		"\"name\":\"李明\"," +
		"\"mobile\":\"16587658765\"," +
		"\"cert_type\":\"IDENTITY_CARD\"," +
		"\"cert_no\":\"362334768769238881\"," +
		"\"min_age\":\"18\"," +
		"\"fix_buyer\":\"F\"," +
		"\"need_check_info\":\"F\"" +
		"    }" +
		"  }");
		*/
		AlipayTradeWapPayResponse response = aliPayApiConfig.getAlipayClient().pageExecute(wapPayRequest);
		logger.info("AlipayTradeWapPayResponse==="+JSONObject.toJSONString(response));
		if(response.isSuccess()){
			logger.info("调用成功");
		} else {
			logger.info("调用失败");
		}
	}

	/**
	 * 交易查询接口
	 * @param aliPayApiConfig
	 * @param model
	 * @return
	 * @throws AlipayApiException
	 */
	public static boolean isTradeQuery(AliPayApiConfig aliPayApiConfig,AlipayTradeQueryModel model) throws AlipayApiException{
		AlipayTradeQueryResponse response = tradeQuery(aliPayApiConfig,model);
		if(response.isSuccess()){
			return true;
		}
		return false;
	}

	/**
	 * 交易查询接口
	 * @param aliPayApiConfig
	 * @param model
	 * @return
	 * @throws AlipayApiException
	 */
	public static AlipayTradeQueryResponse  tradeQuery(AliPayApiConfig aliPayApiConfig,AlipayTradeQueryModel model) throws AlipayApiException{
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizModel(model);
		return aliPayApiConfig.getAlipayClient().execute(request);
	}
	/**
	 * 退款
	 * @param aliPayApiConfig
	 * @param model
	 * @return AlipayTradeRefundResponse
	 * @throws AlipayApiException
	 */
	public static AlipayTradeRefundResponse tradeRefund(AliPayApiConfig aliPayApiConfig, AlipayTradeRefundModel model) throws AlipayApiException{
		if(aliPayApiConfig==null) {
			throw new AlipayApiException(" aliPayApiConfig is null ");
		}
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizModel(model);
		return aliPayApiConfig.getAlipayClient().execute(request);
	}
	
	/**
	 * 退款查询
	 * @param aliPayApiConfig
	 * @param model
	 * @return
	 * @throws AlipayApiException
	 */
	public static AlipayTradeFastpayRefundQueryResponse tradeRefundQuery(AliPayApiConfig aliPayApiConfig, AlipayTradeFastpayRefundQueryModel model) throws AlipayApiException{
		if(aliPayApiConfig==null) {
			throw new AlipayApiException(" aliPayApiConfig is null ");
		}
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		request.setBizModel(model);
		return aliPayApiConfig.getAlipayClient().execute(request);
	}
	
	
	/**
	 * 将异步通知的参数转化为Map
	 * @param request
	 * @return {Map<String, String>}
	 */
	public static Map<String, String> toMap(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		return params;
	}

	/**
	 * 查询对账单下载地址
	 * @param aliPayApiConfig
	 * @param model
	 * @return
	 * @throws AlipayApiException
	 */
	public static AlipayDataDataserviceBillDownloadurlQueryResponse  billDownloadurlQuery(AliPayApiConfig aliPayApiConfig,AlipayDataDataserviceBillDownloadurlQueryModel model) throws AlipayApiException{
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		request.setBizModel(model);
		return aliPayApiConfig.getAlipayClient().execute(request);
	}
}
