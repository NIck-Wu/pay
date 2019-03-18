package com.pay.validateHelper;
import com.utils.ResponseResult;

/**
 * 
 * 参数校验辅助类
 * 
 * @version 1.0
 */
public class ValidateHelper {

	/**
	 * 通用非空参数校验
	 * @return 返回校验信息
	 */
	@SuppressWarnings("rawtypes")
	public static ResponseResult validateNull(Object bean, String[] propertyNames){
		StringBuffer errorMessage = new StringBuffer();
		
		if( bean == null){
			return new ResponseResult<>(ErrorCode.PARAMETER_ERROR.getCode(),"校验的对象不允许为空!");
		}
		
		//不需要校验参数
		if(propertyNames == null || propertyNames.length == 0) {
			return null;
		}
		
		for(String name : propertyNames){
			Object value = ReflectUtil.getValue(bean, name);
			
			if(DataUtil.isEmpty(value)){
				errorMessage.append("[").append(name).append("]不能为空;");
			}
		}
		
		if(errorMessage.length() > 0 ){
			return new ResponseResult<>(ErrorCode.PARAMETER_ERROR.getCode(),errorMessage.toString());
//			throw new BusinessException(ErrorCode.PARAMETER_ERROR.getCode(),errorMessage.toString());
		}
		return null;
	}
	
//	/**
//	 * 通用非空参数校验
//	 * @return 返回校验信息
//	 */
//	public static void validateNull(JSONObject bean, String[] propertyNames){
//		StringBuffer errorMessage = new StringBuffer();
//		
//		if( bean == null){
//			throw new BusinessException(ErrorCode.PARAMETER_ERROR.getCode(),"校验的对象不允许为空!");
//		}
//		
//		//不需要校验参数
//		if(propertyNames == null || propertyNames.length == 0) {
//			return;
//		}
//		
//		for(String name : propertyNames){
//			Object value = bean.get(name);
//			
//			if(DataUtil.isEmpty(value)){
//				errorMessage.append("[").append(name).append("]不能为空;");
//			}
//		}
//		
//		if(errorMessage.length() > 0 ){
//			throw new BusinessException(ErrorCode.PARAMETER_ERROR.getCode(),errorMessage.toString());
//		}
//	}
//	
//	/**
//	 * 校验列表
//	 * 
//	 * @author liaohongjian
//	 * @since 2017年8月14日
//	 * @param beanList
//	 * @param properties
//	 */
//	public static void validateNullList(List<?> beanList,String [] properties){
//		if(DataUtil.isEmpty(beanList)){
//			throw new BusinessException(ErrorCode.PARAMETER_ERROR.getCode(),"请求参列表象不能为空.");
//		}
//		
//		for(Object bean : beanList){
//			validateNull(bean,properties);
//		}
//	}
//	
//	/**
//	 * 校验列表
//	 * 
//	 * @author liaohongjian
//	 * @since 2017年8月14日
//	 * @param beanList
//	 * @param properties
//	 */
//	public static void validateNullList(JSONArray jsonArray,String [] properties){
//		if(DataUtil.isEmpty(jsonArray)){
//			throw new BusinessException(ErrorCode.PARAMETER_ERROR.getCode(),"请求参列表象不能为空.");
//		}
//		
//		for(int i =0;i < jsonArray.size();i++){
//			validateNull(jsonArray.getJSONObject(i),properties);
//		}
//	}
//	
//	/**
//	 * 校验身份证的合法性
//	 * 
//	 * @author liaohongjian
//	 * @since 2017年7月29日
//	 * @param idCard
//	 * 
//	 * @throws 如果证件号码校验失败,则抛出异常
//	 */
//	public static void validIDCard(String idcard){
//		IdCardCheckUtils.idcardCheck(idcard);
//	}
//	
	
}
