package com.pay.validateHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 *	反射工具
 */
public class ReflectUtil {
	
	/**
	 * 获取属性的参数化对象类型
	 * @param feild
	 * @return
	 */
	public static Class<?> getActualTypeArguments(Field feild){
		return getActualTypeArguments(feild, 0);
	}
	/**
	 * 获取参数化泛型的类型信息
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class<?> getActualTypeArguments(Field feild, final int index){
		Type gentype = feild.getGenericType();
				
		if(!(gentype instanceof ParameterizedType)){
			return Object.class;
		}
		Type [] params = ((ParameterizedType)gentype).getActualTypeArguments();
		if(params == null || params.length<index || index<0){
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class<?>) params[index];
	}
	
	/**
	 * 获取超类的泛型的类型信息
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class<?> getSuperClassGenricType(final Class<?> clazz, final int index){
		Type gentype = clazz.getGenericSuperclass();
		if(!(gentype instanceof ParameterizedType)){
			return Object.class;
		}
		Type [] params = ((ParameterizedType)gentype).getActualTypeArguments();
		if(params == null || params.length<index || index<0){
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class<?>) params[index];
	}
	
	/**
	 * 获取泛型的类型信息
	 * @param  
	 * @return Class<T> 
	 */
	public static Class<?> getSuperClassGenricType(final Class<?> clazz){
		return getSuperClassGenricType(clazz,0);
	}
	
	/**
	 * 获取obj对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}
	
	/**
	 * 获取obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getValueByFieldName(Object obj, String fieldName) throws Exception {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if(field!=null){
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}
	
	
	/**
	 * 获取对象属性值
	 * @param entity
	 * @param fieldName
	 * @return
	 */
	public static Object getValue(Object entity, String fieldName ){
		try {
			return ReflectUtil.getValueByFieldName(entity,  fieldName );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 设置obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @param value
	 */
	public static void setValueByFieldName(Object obj, String fieldName, Object value) throws  Exception {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
	
	/**
	 * 反射执行方
	 * @param object
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invoke(Object object,String methodName,Object ...args ) throws Exception {
		Method m = object.getClass().getMethod(methodName);
		return m.invoke(object, args);
	}
	
	/**
	 * 默认为 ""
	 * @param object
	 * @param propertyName
	 * @return
	 */
	public static Object initValue(Object object,String propertyName){
		Object value = "";
		
		try{
			Field f1 = object.getClass().getDeclaredField( propertyName );
			
			Class<?> type = f1.getType();
			
			//java基本类型对象
			if(type.isPrimitive()){
				return "";
			}
			else if(type == String.class){
				value = "";
			}
			else if(type.isArray()){
				value = new Object[0];
			}
			else if(type == List.class){
				value = Collections.EMPTY_LIST;
			}else if(type == Map.class ){
				value = Collections.EMPTY_MAP;
			}else if(type == Set.class){
				value = Collections.EMPTY_SET;
			}
			
		}catch(Exception e){
			//e.printStackTrace();
		}
		
		return value;
	}
}
