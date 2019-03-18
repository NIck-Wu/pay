package com.pay.validateHelper;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 处理xml节点
 * @author qishangzy
 *
 */
public class DataUtil {
	
	/**
	 * 判断类型是否是基本类型
	 * @param clazz
	 * @return
	 */
	public static boolean isBaseType(Class<?> clazz ){
		
		//java基本类型对象
		if(clazz.isPrimitive()){
			return true;
		}
		
		//java.lang下对象不处理
		if(clazz.getName().startsWith("java.lang.") ){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断 对象是否为空
	 * @param object
	 * @return
	 */
	public static boolean isEmpty( Object object ){
		if(object == null) return true;
		//字符串
		if(object instanceof String ){
			return "".equals(object.toString());
		}
		//集合
		else if( object instanceof Collection<?>){
			return ((Collection<?>)object).isEmpty();
		}
		//数组
		else if( object instanceof Object[] ){
			return ((Object[])object).length == 0;
		}
		//Map
		else if( object instanceof Map<?, ?>){
			return ((Map<?,?>)object).isEmpty();
		}
		
		return false;
	}
	
	public static String toString(Object object){
		if(object == null) return null;
		String value = null;
		if(object instanceof java.util.Date){
			value = DateUtils.formatString( (java.util.Date)object,DateUtils.FULL_DATE_STR);
		}else if(object instanceof String[] ){
			value = toString( (String[])object );
		}else if(object instanceof java.lang.Throwable){
			value = toString((Throwable)object);
		} 
		else{
			value = object.toString();
		}

		return value;
	}
	
	public static String toString(String[] array) {
		if(array == null ) return null;
		
		if(array.length == 0) return "";
		
		String value = null;
		for(String s : array ){
			if( StringUtils.isEmpty(s))
				value = (value == null) ? s : value + "," + s;
		}
		
		return value;
	}
	
	public static String toString( Throwable e ){
		if(e == null ) return null;
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(os));
		String value = os.toString();
		
		return value;
	}
	
	/**
	 * 将字符串转为数值类型,非数值转为默认值
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static BigDecimal getBigDecimal(String value,Object defaultValue ){
		BigDecimal result = null;
		try{
			result = new BigDecimal(value);
		}catch(Exception e){
			result =  new BigDecimal(defaultValue.toString());
		}
		
		return result;
	}
}
