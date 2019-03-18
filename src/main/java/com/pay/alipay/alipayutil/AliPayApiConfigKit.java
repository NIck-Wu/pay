package com.pay.alipay.alipayutil;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class AliPayApiConfigKit {

	private static final Map<String, AliPayApiConfig> CFG_MAP = new ConcurrentHashMap<String, AliPayApiConfig>();
	
	private static final Map<String, AliPayApiConfig> APPID_MAP = new ConcurrentHashMap<String, AliPayApiConfig>();

	/**
	 * 添加支付宝支付配置，每个accountCode只需添加一次，相同accountCode将被覆盖
	 * @param aliPayApiConfig 支付宝支付配置
	 * @return {AliPayApiConfig} 支付宝支付配置
	 */
	public static AliPayApiConfig putAliPayApiConfig(AliPayApiConfig aliPayApiConfig) {
        return CFG_MAP.put(aliPayApiConfig.getAccountCode(), aliPayApiConfig);
    }
	
	public static AliPayApiConfig getAliPayApiConfigByAppId(String appId) {
		AliPayApiConfig cfg = APPID_MAP.get(appId);
		if (cfg == null) {
			Iterator<AliPayApiConfig> iterator = CFG_MAP.values().iterator();
			while(iterator.hasNext()) {
				AliPayApiConfig ali = iterator.next();
				if(appId.equals(ali.getAppId())) {
					cfg  = ali;
					APPID_MAP.put(appId, cfg);
					break;
				}
			}
		}
		return cfg;
	}
   /**
    * 获取支付宝支付账号配置
    * @param accountCode
    * @return
    */
    public static AliPayApiConfig getAliPayApiConfig(String accountCode) {
    	AliPayApiConfig cfg = CFG_MAP.get(accountCode);
//        if (cfg == null)
//            throw new IllegalStateException("需事先调用 AliPayApiConfigKit.putAliPayApiConfig(aliPayApiConfig) 将 accountCode对应的 aliPayApiConfig 对象存入，" +
//                    "才可以使用 AliPayApiConfigKit.getAliPayApiConfig() 的系列方法");
        return cfg;
    }
}
