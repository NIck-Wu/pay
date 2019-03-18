package com.ext.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapUtils {
	/**
	 * 将支付宝回调的参数封装到Map中
	 * 
	 * @param requestParams
	 * @return
	 */
	public static Map<String, String> requestParamToHashMap(Map requestParams) {

		Map<String, String> params = new HashMap<String, String>();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}

		return params;
	}

}
