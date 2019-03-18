package com.pay.wechatpay.wxpayutil;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



/**
 * @author Javen
 * 2017年5月22日
 */
public class WxPayApiConfigKit {
	
	private static final Map<String, WxPayApiBaseConfig> CFG_MAP = new ConcurrentHashMap<String, WxPayApiBaseConfig>();
	
	private static final Map<String, WxPayApiBaseConfig> APPID_MAP = new ConcurrentHashMap<String, WxPayApiBaseConfig>();

	/**
	 * 添加微信支付配置，每个appId只需添加一次，相同appId将被覆盖
	 * @param wxPayApiConfig 微信支付配置
	 * @return {WxPayApiConfig} 微信支付配置
	 */
	public static WxPayApiBaseConfig putWxPayApiBaseConfig(WxPayApiBaseConfig wxPayApiBaseConfig) {
        return CFG_MAP.put(wxPayApiBaseConfig.getAccountCode(), wxPayApiBaseConfig);
    }
	public static WxPayApiBaseConfig getWxPayApiBaseConfigByAppId(String appId) {
		WxPayApiBaseConfig cfg = APPID_MAP.get(appId);
		if (cfg == null) {
			Iterator<WxPayApiBaseConfig> iterator = CFG_MAP.values().iterator();
			while(iterator.hasNext()) {
				WxPayApiBaseConfig wb = iterator.next();
				if(appId.equals(wb.getAppId())) {
					cfg  = wb;
					APPID_MAP.put(appId, cfg);
					break;
				}
			}
		}
		return cfg;
	}
    public static WxPayApiBaseConfig getWxPayApiBaseConfig(String accountCode) {
    	WxPayApiBaseConfig cfg = CFG_MAP.get(accountCode);
//        if (cfg == null)
//        	throw new IllegalStateException("Can not find WxPayApiBaseConfig  by accountCode , accountCode == " + accountCode);
//            throw new IllegalStateException("需事先调用 WxPayApiConfigKit.putWxPayApiBaseConfig(wxPayApiBaseConfig) 将 accountCode对应的 WxPayApiBaseConfig 对象存入，" +
//                    "才可以使用 WxPayApiConfigKit.getWxPayApiBaseConfig() 的系列方法");
        return cfg;
    }
}
