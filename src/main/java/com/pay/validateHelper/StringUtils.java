package com.pay.validateHelper;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串相关方法
 *
 */
public class StringUtils {
   
    /**
	 * 判断 对象是否为空  支持 字符串 集合 数组 Map 类型的判断
	 * @param object
	 * @return
	 */
	public static boolean isEmptyObject( Object object ){
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
    /**
     * 功能描述:判断字符串是否非空
     * @author xiaozhiqiang
     * @date 2013-6-17
     * @param str 待判断字符串
     * @return 判断结果
     */
    public static boolean isNotEmpty(String str) {
        return !isEmptyStr(str);
    }
    
    
    /**
     * 功能描述:判断字符串是否为空或者为空白串
     * @author xiaozhiqiang
     * @date 2013-6-17
     * @param str 待判断字符串
     * @return 判断结果
     */
    public static boolean isEmptyStr(String str) {
        if (null == str || "".equals(str.trim())) {
            return true;
        } else
            return false;
    }

    /**
     * 
     * 功能描述：将null转化为空字符串
     *@author xiaozhiqiang
     *@date 2013-8-29
     *@param str 源字符串
     *@return
     */
    public static String cancelNull(String str) {
        return str == null ? "" : str;
    }

    /**
     * 
     * 功能描述:如果传入串为空或者undefined，那么用defaultStr初始化，否则返回截取空白后的字符串
     * @author xiaozhiqiang
     * @date 2013-6-19
     * @param str 待处理的字符串
     * @param defaultStr 缺省的初始化串
     * @return 处理结果
     */
    public static String getTrimStr(String str, String defaultStr) {
        if (isEmptyStr(str) || "undefined".equals(str)) {
            str = defaultStr;
        }
        return str.trim();
    }
    
    /**
     * 
     * 功能描述:根据身份证号码获取生日的日期，可以处理15位或18位身份证号码
     * @author xiaozhiqiang
     * @date 2013-6-19
     * @param idCardNo 身份证号码
     * @return 生日的日期
     */
    public static Date getBirthdayFromIdCard(String idCardNo) {
        if (isEmptyStr(idCardNo)
                || !(idCardNo.matches(RegexStrings.REG_IDCARD_15) || idCardNo.matches(RegexStrings.REG_IDCARD_18))) {
            throw new IllegalArgumentException("idCardNo is not right");
        }
        String birdayStr = "";
        Date birday = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        try {
            if (!isEmptyStr(idCardNo) && 15 == idCardNo.length()) {
                birdayStr = "19" + idCardNo.substring(6, 12);
                birday = dateFormat.parse(birdayStr);
            } else if (!isEmptyStr(idCardNo) && 18 == idCardNo.length()) {
                birdayStr = idCardNo.substring(6, 14);
                birday = dateFormat.parse(birdayStr);
            }
        } catch (Exception e) {
            System.out.println("转换身份证号码的生日出错"+ e.getMessage());
        }
        return birday;
    }

    /**
     * 
     * 功能描述:根据身份证号码获取性别，可以处理15位或18位身份证号码
     * @author xiaozhiqiang
     * @date 2013-6-19
     * @param idCardNo 身份证号码
     * @return 性别（M表示男，F表示女）
     */
    public static String getGenderFromIdCard(String idCardNo) {
        String result = "";
        int genderNo = 0;
        if (isEmptyStr(idCardNo)
                || !(idCardNo.matches(RegexStrings.REG_IDCARD_15) || idCardNo.matches(RegexStrings.REG_IDCARD_18))) {
            throw new IllegalArgumentException("idCardNo is not right");
        }
        if (15 == idCardNo.length()) {
            genderNo = Integer.parseInt(idCardNo.substring(12));
        } else if (18 == idCardNo.length()) {
            genderNo = Integer.parseInt(idCardNo.substring(14, 17));
        }
        result = (genderNo % 2) > 0 ? "M" : "F";
        return result;
    }

    /**
     * 
     * 功能描述:将15位身份证转换成为18位，如果已经是18位，那么直接返回。
     * @author xiaozhiqiang
     * @date 2013-6-19
     * @param idCardNo 身份证号码
     * @return 转换后的身份证号码
     */
    public static String change15IdCardTo18(String idCardNo) {
        if (isEmptyStr(idCardNo)
                || !(idCardNo.matches(RegexStrings.REG_IDCARD_15) || idCardNo.matches(RegexStrings.REG_IDCARD_18))) {
            throw new IllegalArgumentException("idCardNo is not right");
        }
        if (15 == idCardNo.length()) {
            final int[] W = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
            final String[] A = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
            int i, j, s = 0;
            String newid;
            newid = idCardNo;
            newid = newid.substring(0, 6) + "19" + newid.substring(6, idCardNo.length());
            for (i = 0; i < newid.length(); i++) {
                j = Integer.parseInt(newid.substring(i, i + 1)) * W[i];
                s = s + j;
            }
            s = s % 11;
            newid = newid + A[s];
            return newid;
        } else
            return idCardNo;
    }

    /**
     * 
     * 功能描述:从身份证中获取周岁
     * @author xiaozhiqiang
     * @date 2013-6-19
     * @param idCardNo 身份证
     * @return 周岁
     */
    public static int getAgeFromIdCard(String idCardNo) {
        Date birthday = getBirthdayFromIdCard(idCardNo);
        Calendar calBirthday = Calendar.getInstance();
        calBirthday.setTime(birthday);
        calBirthday.get(Calendar.YEAR);

        Calendar calToday = Calendar.getInstance();
        int diffYear = calToday.get(Calendar.YEAR) - calBirthday.get(Calendar.YEAR);

        calBirthday.add(Calendar.YEAR, diffYear);
        if (calBirthday.after(calToday))//如果今天的日期还没到达今年的生日，说明周岁还需要减1
        {
            diffYear = diffYear - 1;
        }
        return diffYear;
    }

    /**
     * 
     * 功能描述:将字符串转成小写，可避免直接调用String.toLowerCase引起空指针的情况
     * @author xiaozhiqiang
     * @date 2013-8-17
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
        if (str == null)
            return "";
        else {
            return str.toLowerCase();
        }

    }

    /**
     * 
     * 功能描述:将字符串转成大写，可避免直接调用String.toUpperCase引起空指针的情况
     * @author xiaozhiqiang
     * @date 2013-8-17
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        if (str == null)
            return "";
        else {
            return str.toUpperCase();
        }
    }
    
    /**
     * 
     * 功能描述:将字符串的首字母转成大写
     * @author xiaozhiqiang
     * @date 2014-1-6
     * @param str
     * @return
     */
    public static String toUpper1stLetter(String str) {
        if (str == null)
            return "";
        else {
            return str.substring(0,1).toUpperCase()+str.substring(1,str.length());
        }
    }
    
    
	/**
	 * 用delimeter连接个字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String getMergeString(List<String> list, String delimeter) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		if (list.size() == 1) {
			return (String) list.get(0);
		}
		StringBuffer sb = new StringBuffer();

		Iterator<String> iter = list.iterator();
		String str;
		while (iter.hasNext()) {
			str = iter.next();
			sb.append(str);
			sb.append(delimeter);
		}
		return sb.substring(0, sb.lastIndexOf(delimeter));
	}
	
	/**
	 * 功能描述：将String转为Integer
	 * @date 2014年1月7日
	 * @return Integer
	 */
	public static Integer strToInt(String inValue) {
		int outValue = 0;
		try {
			outValue = Integer.parseInt(inValue);
		} catch (NumberFormatException ex) {
		} catch (NullPointerException ex) {
		}
		return Integer.valueOf(outValue);
	}
	
	/**
     * 功能描述:判断字符串数组是否为空
     * @author xiaozhiqiang
     * @date 2014-01-15
     * @param arg 待判断字符串数组
     * @return 判断结果
     */
	public static boolean isEmptyStrArray(String[] arg){
		boolean flag = false;
		if(null==arg || arg.length==0){
			flag = true;
		}
		return flag;
	}
	

	
	/**
	 * 功能描述：将String转为BigDecimal 为空转换为零
	 * @param str
	 * @return
	 */
	public static BigDecimal convertStrToBigDecimal(String str){
		if(isEmptyStr(str)){
			return new BigDecimal(0);
		}
		BigDecimal bigDecimal = new BigDecimal(0);
		 try{
			bigDecimal = new BigDecimal(str);
		 }catch(Exception e){
		 }
		 return bigDecimal;
	}
	/**
	 * 
	 * 功能描述：空字符串置换
	 *@author xiaozhiqiang
	 *@date 2014-1-26
	 *@param in
	 *@param out
	 *@return
	 */
	public static String emptyStrConvert(String in,String out){
		return isNotEmpty(in)?in:out;
	}
	/**
	 * 功能描述：字符串转长整型对象
	 *
	 * 传入参数：inValue (内容：输入字串；类型：字符串)
	 *
	 * 传出参数：outValue (内容：输出长整型对象；类型：类)
	 *
	 * 异常处理：无
	 */
	public static Long strToLong( String inValue ) {
		long outValue = 0 ;
		try{
			outValue = Long.parseLong( inValue );
		}catch(NumberFormatException ex){}
		catch(NullPointerException ex){}
		return  Long.valueOf(outValue);
	}  
        
        /**
         * 功能描述：根据指定长度截取字符串,并追加结束符
         * @author xiaozhiqiang
         * @date 2014-4-14
         * @param string
         * @param length
         * @return
         */
        public static String subString (String s ,int n,String endWith){
            if (s == null) {
                return "";
            }
            Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]|[\\uff08]|[\\uff09]$");
            int i = 0, j = 0;
            char []ch = s.toCharArray();
            char c;
            for (int k=0; k< ch.length; k++) {
                c = ch[k];
                Matcher m = p.matcher(String.valueOf(c));
                i += m.find() ? 2 : 1;
                ++j;
                if(i == n) 
                    break;
                if(i > n) {
                    --j;
                    break;
                }
            }
            return s.substring(0, j) + endWith;
        }
        
        /**
         * 功能描述：通过escape转码
         * @author xiaozhiqiang
         * @date 2014-7-9
         * @param src
         * @return
         */
        public static String escape(String src) {
            int i;
            char j;
            StringBuffer tmp = new StringBuffer();
            tmp.ensureCapacity(src.length() * 6);
            for (i = 0; i < src.length(); i++) {
                j = src.charAt(i);
                if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                    tmp.append(j);
                else if (j < 256) {
                    tmp.append("%");
                    if (j < 16)
                        tmp.append("0");
                    tmp.append(Integer.toString(j, 16));
                } else {
                    tmp.append("%u");
                    tmp.append(Integer.toString(j, 16));
                }
            }
            return tmp.toString();
        }

        /**
         * 功能描述：通过unescape转码
         * @author xiaozhiqiang
         * @date 2014-7-9
         * @param src
         * @return
         */
        public static String unescape(String src) {
            StringBuffer tmp = new StringBuffer();
            tmp.ensureCapacity(src.length());
            int lastPos = 0, pos = 0;
            char ch;
            while (lastPos < src.length()) {
                pos = src.indexOf("%", lastPos);
                if (pos == lastPos) {
                    if (src.charAt(pos + 1) == 'u') {
                        ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                        tmp.append(ch);
                        lastPos = pos + 6;
                    } else {
                        ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                        tmp.append(ch);
                        lastPos = pos + 3;
                    }
                } else {
                    if (pos == -1) {
                        tmp.append(src.substring(lastPos));
                        lastPos = src.length();
                    } else {
                        tmp.append(src.substring(lastPos, pos));
                        lastPos = pos;
                    }
                }
            }
            return tmp.toString();
        }
        

        /**
         * 功能描述：字符串匹配()
         * @author xiaozhiqiang
         * @date 2014-7-9
         * @param 正则表达式
         * @param 匹配Str
         * @return
         */
		public static boolean checkMatcher(String regStr, String matStr){
			Pattern pattern = Pattern.compile(regStr);
			Matcher matcher = pattern.matcher(matStr);
		   	return matcher.matches();
		}
		
		/**
		 * 功能描述：判断两个字符串是否相等（空字符串与Null相等）
		 * @author xiaozhiqiang
		 * @date 2014-10-20
		 * @param str1
		 * @param str2
		 * @return
		 */
		public static boolean isEqualStr(String str1,String str2){
			return cancelNull(str1).equals(cancelNull(str2));
		}
		

		/**
		 * 功能描述：检查是否前后有空格 
		 * @author xiaozhiqiang
		 * @date 2014-12-11
		 * @return
		 */
		 public static boolean isHaveBAblankBankStr(String str){
			 String tmpStr=str.trim();
			 
			return tmpStr.length()!=str.length();
		 }
		 
		 /**
		  * 功能描述：去除str中所有空格(前\中\后),如果入参为空或null，则返回原值
		  * @author xiaozhiqiang
		  * @date 2014-12-31
		  * @param String
		  * @return String
		  */
		 public static String stringTrim(String str){
			 if( isNotEmpty(str) ){
				 str = str.replaceAll("\\s+", "");
				 str = stringFullCornerTrim(str);//去掉全角空格
				 return str;
			 }
			 return str;
		 }

		 /**
		  * 功能描述：去除str中所有空格(前\中\后),如果入参为空或null，则返回原值
		  * @author xiaozhiqiang
		  * @date 2015-02-10
		  * @param String
		  * @return String
		  */
		 public static String getDefaultString(String str){
			 if(isEmptyStr(str)){
				 return "";
			 }
			 return str;
		 }

		 /**
		  * 
		  * 功能描述：去掉全角空格(前\中\后),如果入参为空或null，则返回原值
		  * @author xiaozhiqiang
		  * @date 2015-2-5
		  * @param str
		  * @return
		  */
		 public static String stringFullCornerTrim(String str){
			 if(isNotEmpty(str)){
				 String regStartSpace = "^[　 ]*";//左边空格
		         String regEndSpace = "[　 ]*$";//右边空格
		         //替换左右全角空格(中间不管)
		         str = str.replaceAll(regStartSpace, "").replaceAll(regEndSpace, "");
				 //str = str.replaceAll("\u3000", "");//去掉全角空格
			 }
			 return str;
		 }


		 /**
		  * 功能描述：将null对象转换成空字符串
		  * @author xiaozhiqiang
		  * @date 2015-2-28
		  * @param obj
		  * @return
		  */
		 public static String cancelNullObj(Object obj){
			 return obj == null ? "" : obj.toString();
		 }

		 /**
		  * 
		  * 功能描述：判断是否有一个字符串为空
		  * @author xiaozhiqiang
		  * @date2015-6-5
		  * @param args
		  * @return
		  */
		 public static boolean isHaveOneEmpty(String ...args){
			 boolean  retValue  = false;
			 if(null != args){
				 for(int i = 0; i < args.length; i++){
					 if(isEmptyStr(args[i])){
						 retValue = true;
						 return retValue;
					 }
				 }
			 }
			 return retValue;
		 }


		 /**
		  * 
		  * 功能描述:判断是否为纯数字类型的字符串
		  *@author xiaozhiqiang
		  *@date 2015-9-14
		  *@return
		  */
		 public static boolean isNumStr(String str){
			 boolean ret = false ;
			 ret = checkMatcher("^\\d+$", str);
			 return ret ;
		 }
		 /**
		  * @Desc map的key值从0开始往上增加
		  * @param map
		  * @param append
		  * @return 
		  * @author xiaozhiqiang
		  * @Date 2015-12-3
		  */
		 public static String mapToString(Map<String,String> map ,String append){
			 if(map==null||isEmptyStr(append)){
				 return "";
			 }
			 StringBuffer value = new StringBuffer();
			 int index = 0;
			 while(map.get(index+"")!=null){
				 value.append(map.get(index+"")).append(append);
				 index++;
			 }
			 if(isNotEmpty(value.toString())){
				 return value.substring(0, value.length()-append.length());
			 }
			 return value.toString();
		 }
}
