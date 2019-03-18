package com.pay.wechatpay.wxpayutil;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.ext.util.Md5Util;
 /**
  * 微信报文加解密用
  * @author xiaozhiqiang
  *
  */
public class WechatAESUtil {
 
	/**
	 * 密钥算法
	 */
	private static final String ALGORITHM = "AES";
	/**
	 * 加解密算法/工作模式/填充方式
	 */
	private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS5Padding";

	/**
	 * AES加密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptData(String data,Key key) throws Exception {
		// 创建密码器
		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
		// 初始化
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return new String(Base64.getEncoder().encode(cipher.doFinal(data.getBytes())),"UTF-8");
	}
	/**
	 * AES解密
	 * @param base64Data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptData(String base64Data,Key key) throws Exception {
		
		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64.getDecoder().decode(base64Data)));
	}
	/**
	 * md5加密后再全小写生成 AES
	 * @param encodeAesKey
	 * @return
	 * @throws Exception
	 */
	public static Key generateKey(String encodeAesKey)throws Exception{
		SecretKeySpec key = new SecretKeySpec(Md5Util.md5(encodeAesKey, "UTF-8").toLowerCase().getBytes(), ALGORITHM);
		return key;
	}
	public static void main(String[] args) throws Exception {
		String A = "我是测试";
		Key key = generateKey("123456");
		String SecretData = WechatAESUtil.encryptData(A, key);
		System.out.println("加密=="+SecretData);
		String B = WechatAESUtil.decryptData(SecretData,key);
		System.out.println("解密=="+B);
	}
 
}