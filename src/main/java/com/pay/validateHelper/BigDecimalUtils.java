package com.pay.validateHelper;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.ext.util.StringUtils;
/**
 * @desc BigDecimalUtils
 * @author xiaozhiqiang
 * @date 2017年4月19日 上午11:50:47
 */
public class BigDecimalUtils {
	
	
	/**
	 * 小数位默认取2位
	 */
	private static final Integer DEFAULT_SCALE = 2;
	
	public static final BigDecimal BigDecimal_100 = new BigDecimal("100");
	
	/**
	 * 获取数值对象
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static BigDecimal getBigDecimal(Object value){
		
		BigDecimal result = null;
		
		try{
			result = new BigDecimal(value.toString());
		}catch(Exception e){
			
		}
		
		return result;
	}
	
	/**
	 * 获取数值对象
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static BigDecimal getBigDecimal(Object value,Number defaultValue){
		
		BigDecimal result = null;
		
		try{
			result = new BigDecimal(value.toString());
		}catch(Exception e){
			result = new BigDecimal(defaultValue.toString());
		}
		
		return result;
	}
	
	
	/**
	 * 提供精确的加法运算。
	 * @param value1 被加数
	 * @param value2 加数
	 * @return 两个参数的和
	 */
	public static BigDecimal add(Number value1, Number value2) {
		if(value1==null) value1 = 0;
		if(value2==null) value2 = 0;
		BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
		return b1.add(b2);
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param value1 被减数
	 * @param value2 减数
	 * @return 两个参数的差
	 */
	public static BigDecimal sub(Number value1, Number value2) {
		if(value1==null) value1 = 0;
		if(value2==null) value2 = 0;
		BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
		return b1.subtract(b2);
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param value1 被乘数
	 * @param value2  乘数
	 * @return 两个参数的积
	 */
	public static BigDecimal mul(Number value1, Number value2) {
		if(value1==null) value1 = 0;
		if(value2==null) value2 = 0;
		BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
		return b1.multiply(b2);
	}

	/**
	 * 提供（相对）精确的除法运算 
	 * @param dividend 被除数
	 * @param divisor  除数
	 * @return 两个参数的商
	 * 
	 * @see {@link #DEFAULT_SCALE}
	 */
	public static BigDecimal div(Number dividend, Number divisor) {
		return div(dividend, divisor, DEFAULT_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。 
	 * 
	 * @param dividend 被除数
	 * @param divisor 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static BigDecimal div(Number dividend, Number divisor, Integer scale) {
		if(dividend==null) return  null;
		
		if(divisor ==null || divisor.doubleValue() == 0){
			throw new IllegalArgumentException(
					"The divisor cannot be zero or null !");
		}
		
		if(scale == null) scale = DEFAULT_SCALE;
		
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(dividend.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(divisor.doubleValue()));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param value 需要四舍五入的数字
	 * @return 四舍五入后的结果
	 * 
	 * @see {@link #DEFAULT_SCALE}
	 */
	public static BigDecimal round(Number value){
		return round(value,DEFAULT_SCALE);
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param value 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static BigDecimal round(Number value, Integer scale) {
		if(value == null) return null;
		
		if(scale == null) scale = DEFAULT_SCALE;
		
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(value.toString());
		
		return b.divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 格式化输出 (默认保留2位小数)
	 * 
	 * @author liaohongjian
	 * @since 2017年8月17日
	 * @param value
	 * @return
	 */
	public static String format(BigDecimal value){
		
		if(value == null){
			throw new IllegalArgumentException("The value must be a valid value.");
		}
		
		DecimalFormat  df = new DecimalFormat(".00");
		
		return df.format(value);
	}
	
	/**
	 * 比较返回小的
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal min(Object a, Object b) {
		BigDecimal i = new BigDecimal(String.valueOf(a));
		BigDecimal j = new BigDecimal(String.valueOf(b));
		return i.compareTo(j) < 0 ? i : j;
	}
	/**
	 * 比较返回小的
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal min(BigDecimal a, BigDecimal b) {
		return a.compareTo(b) < 0 ? a : b;
	}
	/**
	 * 比较返回大的
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal max(BigDecimal a, BigDecimal b) {
		return a.compareTo(b) > 0 ? a : b;
	}


	/**
	 * 返回百分比
	 * @param a
	 * @return
	 */
	public static BigDecimal toPercent(BigDecimal a) {
		return a.divide(new BigDecimal("100"));
	}
	/**
	 * 对象为null时转换为零
	 * @param a
	 * @return
	 */
	public static BigDecimal transNullToZERO(BigDecimal a) {
		return a == null ? BigDecimal.ZERO : a;
	}
	/**
	 * 将null或空字符转换为零
	 * @param a
	 * @return
	 */ 
	public static BigDecimal transNullStringToZERO(String a) {
		if(StringUtils.isEmpty(a)){
			return BigDecimal.ZERO;
		}
		return new BigDecimal(a.trim());
	}

	/**
	 * 
	 * 功能描述：负数转0
	 *@author xiaozhiqiang
	 *@date 2014-3-10
	 *@param a
	 *@return
	 */
	public static BigDecimal transMinusToZERO(BigDecimal a) {
		return a.compareTo(BigDecimal.ZERO)<0?BigDecimal.ZERO: a;
	}

	/**
	 * 由于java语言中对浮点数的二进制转换会产生问题，因此在将浮点数转换为BigDecimal类型时需要先将浮点数转换为String型
	 * @author xiaozhiqiang
	 * @date 2013-12-20
	 * @param num
	 * @return BigDecimal
	 */
	public static BigDecimal getExactBigDicimal(double num) {
		String numStr = num + "";
		return new BigDecimal(numStr);
	}

	/**
	 * 校验是否为小于等于零的数字
	 * @author xiaozhiqiang
	 * @date 2014-01-13
	 * @param num
	 * @return boolean
	 */
	public static boolean isNotPlus(BigDecimal number) {
		if (number == null) {
			return true;
		}
		if (number.compareTo(new BigDecimal(0)) <= 0) {
			return true;
		}
		return false;
	}


	/**
	 * 
	 * 功能描述：校验是否大于零的数字
	 *@author xiaozhiqiang
	 *@date 2014-10-10
	 *@param number
	 *@return
	 */
	public static boolean isGreaterZero(BigDecimal number) {
		if (number == null) {
			return false;
		}
		if (number.compareTo(new BigDecimal(0)) <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * 功能描述：将可能为空的BigDecimal转为BigDecimal
	 * @date 2014年1月23日
	 * @return BigDecimal
	 */
	public static BigDecimal convertBigDecimal(BigDecimal num) {
		if (num == null) {
			num = new BigDecimal(0);
		}
		return num;
	}

	/**
	 * 功能描述:将可能为空的Integer转为0
	 * @param integer
	 * @return
	 */
	public static Integer convertInteger(Integer integer){
		if(integer == null){
			integer = new Integer(0);
		}
		return integer;
	}

	/**
	 * 功能描述：将BigDecimal转为BigDecimal,允许自己定义异常时的返回值
	 * @date 2014年1月23日
	 * @return BigDecimal
	 */
	public static BigDecimal convertBigDecimal(BigDecimal num, BigDecimal bd) {
		if (num == null) {
			return bd;
		}
		return num;
	}

	/**
	 * 功能描述：将BigDecimal转为String
	 * @date 2014年1月24日
	 * @return String
	 */
	public static String covertBigDecimalToString(BigDecimal num) {
		if (num == null) {
			return "";
		}
		return num.toString();
	}

	/**
	 * 功能描述：将BigDecimal转为double
	 * 
	 * @date 2014年2月14日
	 * @return String
	 */
	public static double toDouble(BigDecimal num){
		if(num == null){
			return 0;
		}
		return num.doubleValue();
	}
	/**
	 * 判断bd1是否小于bd2
	 * @author xiaozhiqiang
	 * @date 2013-12-18
	 * @param bd1
	 * @param bd2
	 * @return boolean
	 * 
	 */
	public static boolean compareBigDecimalMinus(BigDecimal bd1, BigDecimal bd2) {
		boolean returnFlag = false;
		int i1 = bd1.compareTo(bd2);
		if (i1 < 0) {
			returnFlag = true;
		}
		return returnFlag;
	}

	/**
	 * 判断bd1是否小于等于bd2
	 * @author xiaozhiqiang
	 * @date 2013-12-18
	 * @param bd1
	 * @param bd2
	 * @return boolean
	 * 
	 */
	public static boolean compareBigDecimalMinusEq(BigDecimal bd1, BigDecimal bd2) {
		boolean returnFlag = false;
		int i1 = bd1.compareTo(bd2);
		if (i1 < 0 || i1==0) {
			returnFlag = true;
		}
		return returnFlag;
	}

	/**
	 * 功能描述：判断bd1是否大于bd2
	 * @author xiaozhiqiang
	 * @date 2014-5-15
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static boolean compareBigDecimalPlus(BigDecimal bd1,BigDecimal bd2){
		boolean returnFlag = false;
		int i1 = bd1.compareTo(bd2);
		if (i1 > 0) {
			returnFlag = true;
		}
		return returnFlag;
	}

	/**
	 * 
	 * 功能描述：三目运算
	 *@author xiaozhiqiang
	 *@date 2014-3-2
	 *@param flag
	 *@param amount
	 *@return
	 */
	/**
	public static BigDecimal ternaryConditionalCalc (boolean flag,BigDecimal amount) {
		return flag?amount:BigDecimal.ZERO;
	}
	 */
	/**
	 * 功能描述：判断BigDecimal是否为null
	 * @author xiaozhiqiang
	 * @date 2014-3-13
	 * @param bigDecimal
	 * @return
	 */
	public static boolean isNullBigDecimal(BigDecimal bigDecimal){
		boolean result = false;

		if(bigDecimal == null){
			result = true; 
		}

		return result;
	}

	/**
	 * 功能描述：判断BigDecimal是否大于或等于零，为null时返回false
	 * @author xiaozhiqiang
	 * @date 2014-3-13
	 * @param bigDecimal
	 * @return
	 */
	public static boolean isGreaterOrEqualToZero(BigDecimal bigDecimal){
		boolean result = false;
		if(BigDecimalUtils.isNullBigDecimal(bigDecimal)){
			return result;
		}

		if(bigDecimal.compareTo(BigDecimal.ZERO) >= 0) {
			result = true;
		}

		return result;

	}

	/**
	 * 
	 * 功能描述：将String类型转换为BigDecimal类型
	 *          如果String为空或null则返回BigDecimal.ZERO
	 * @author xiaozhiqiang
	 * @date 2013-10-26
	 * @param bigDecimalStr
	 * @return
	 */
	public static BigDecimal getBigDecimal(String bigDecimalStr){
		BigDecimal result = BigDecimal.ZERO;

		if(!StringUtils.isEmpty(bigDecimalStr)){
			result = new BigDecimal(bigDecimalStr);
		}

		return result;
	}

	/**
	 * 
	 * 功能描述：如果bigDecimal等于0则返回1
	 *          否则返回bigDecimal
	 * @author xiaozhiqiang
	 * @date 2014-04-15
	 * @param bigDecimal
	 * @return
	 */
	public static BigDecimal transZEROToONE(BigDecimal bigDecimal) {

		if(BigDecimal.ZERO.compareTo(bigDecimal) == 0){
			return BigDecimal.ONE;
		}else{
			return bigDecimal;
		}
	}

	/**
	 * 
	 * 功能描述：判断两个BigDecimal 是否相等
	 * @author xiaozhiqiang
	 * @date 2014-07-09
	 * @param BigDecimal one, BigDecimal two
	 * @return
	 */
	public static boolean isEqual(BigDecimal one, BigDecimal two){

		return one.compareTo(two) == 0 ? true : false; 
	}

	/**
	 * @Desc 对BigDecimal类型进行四舍五入并返回BigDecimal
	 * @param one
	 * @param size
	 * @return 
	 * @author xiaozhiqiang
	 * @Date 2015-11-27
	 */
	public static BigDecimal roundBigDecimal(BigDecimal one, int size){
		return one.setScale(size, BigDecimal.ROUND_HALF_UP);
	}
}