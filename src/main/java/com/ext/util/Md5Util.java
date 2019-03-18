package com.ext.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 基于MessageDigest消息摘要加密工具类,提供 Md5/SHA加密方式
 * 
 * @author liaohongjian
 *
 * @since 2017年6月21日
 *
 * @version 1.0
 */
public class Md5Util {
	
	private static final String ENCODING = "UTF-8";
	
	private enum MessageDigestType{
		
		MD5("MD5"),
		
		SHA1("SHA-1");
		
		private String code;

		public String getCode() {
			return code;
		}

		private MessageDigestType(String code) {
			this.code = code;
		}
	}
	
	/**
	 * md5签名摘要
	 * @param source
	 * @param charset
	 * @return
	 */
	public static String md5(String source){
		return signature(source, MessageDigestType.MD5.getCode(), ENCODING);
	} 
	
	/**
	 * md5签名摘要
	 * @param source
	 * @param charset
	 * @return
	 */
	public static String md5(String source,String charset){
		return signature(source, MessageDigestType.MD5.getCode(), charset);
	}
	
	/**
	 * sha1签名摘要
	 * @param source
	 * @param charset
	 * @return
	 */
	public static String sha1(String source,String charset){
		return signature(source, MessageDigestType.SHA1.getCode(), charset);
	}
	
	/**
	 * sha1签名摘要
	 * @param source
	 * @param charset
	 * @return
	 */
	public static String sha1(String source){
		return signature(source, MessageDigestType.SHA1.getCode(), ENCODING);
	}
	
	/**
	 * 验证签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */

	private static String signature(String source,String algorithm,String charset) {
		
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance(algorithm);
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(source.getBytes(charset));
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return tmpStr.toLowerCase();

	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */

	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;

	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
	
}
