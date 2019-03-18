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
		 * 查询
		 * 
		 * @param user
		 * @return
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "wechatpay", method = RequestMethod.POST)
		public ResponseResult<JSONObject> wechatpay(@RequestBody JSONObject jsonObject){
			return wxChatPayService.appPushOrder(jsonObject);
		}


}
