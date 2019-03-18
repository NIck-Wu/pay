package com.pay.validateHelper;

import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;




public class DateUtils {
	public static final String SIMPLE_DATE_STR = "yyyy-MM-dd";
	public static final String FULL_DATE_STR = "yyyy-MM-dd HH:mm:ss";
	public static final String ALL_FULL_DATE_STR = "yyyy-MM-dd HH:mm:ss.S";
	
	public static final String PART_DATE_STR = "yyyy-MM-dd HH:mm";
	public static final String DATEFORMAT_Y2H = "yyyy-MM-dd HH";
	
	public static final String SIMPLE_RULE_DATE_STR = "yyyy/MM/dd";
	public static final String FULL_RULE_DATE_STR = "yyyy/MM/dd HH:mm:ss";
	public static final String PART_RULE_DATE_STR = "yyyy/MM/dd HH:mm";
	public static final String SMALL_PART_RULE_DATE_STR = "HH:mm";
	
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_FORMAT_YYYYMMDDHH = "yyyyMMddHH";
	public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
	"yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	/** 一天的毫秒数*/
	public static final long ONEDAY_VALUE = 24 * 60 * 60 * 1000 ;
	
	/**
	 * 时间转成yyyy-MM-dd格式的字符串(默认)
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		return sdfDay.format(date);
	}
	
	/**
	 * 时间转成指定格式的字符串
	 * @param date
	 * @return
	 */
	public static String format(Date date,String pattern){
		
		SimpleDateFormat sdf = new SimpleDateFormat( pattern );
		
		String dateStr = sdf.format(date);
		
		return dateStr;
	}

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author xiaozhiqiang
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author xiaozhiqiang
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
	
	/**
	 * 
	 * 功能描述：根据指定格式解析日期格式
	 *@author xiaozhiqiang
	 *@date 2014-3-19
	 *@param dateStr
	 *@param formatStr
	 *@return
	 *@throws ParseException
	 */
	public static Date parseToFormatDate(String dateStr,String formatStr) throws ParseException{
		if(StringUtils.isEmptyStr(dateStr)||StringUtils.isEmptyStr(formatStr)){
    		return null;
    	}
		DateFormat dateFormat = new SimpleDateFormat(formatStr);
		return dateFormat.parse(dateStr);
	}
	
	/**
	 * 
	 * 功能描述:日期类型转换字符串
	 * @author xiaozhiqiang
	 * @date 2014-6-4
	 * @param date
	 * @param fmt
	 * @return
	 * @throws ParseException
	 */
	public static String parseToFormatString(Date date, String fmt) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	/**
	 * 
	 * 功能描述:将String类型的字符串转换成为Date类型，可转换六种格式
	 * 
	 * @author xiaozhiqiang
	 * @date 2013-6-19
	 * @param dateString
	 *            待转换的字符串
	 * @return Date类型
	 * @throws ParseException
	 */
	public static Date parseToDate(String dateString) throws ParseException {
		if (dateString == null)
			throw new IllegalArgumentException("dateString should not be null");
		DateFormat dateFormat = new SimpleDateFormat(SIMPLE_DATE_STR);
		DateFormat fullDateFormat = new SimpleDateFormat(FULL_DATE_STR);
		DateFormat partDateFormat = new SimpleDateFormat(PART_DATE_STR);
		
		DateFormat dateRullFormat = new SimpleDateFormat(SIMPLE_RULE_DATE_STR);
		DateFormat fullRullDateFormat = new SimpleDateFormat(FULL_RULE_DATE_STR);
		DateFormat partRullDateFormat = new SimpleDateFormat(PART_RULE_DATE_STR);
		if (dateString.matches(RegexStrings.REG_FULL_DATE)) {
			return fullDateFormat.parse(dateString);
		} else if (dateString.matches(RegexStrings.REG_DATE)) {
			return dateFormat.parse(dateString);
		} else if (dateString.matches(RegexStrings.REG_PART_DATE)) {
			return partDateFormat.parse(dateString);
		} else if (dateString.matches(RegexStrings.REG_RULE_FULL_DATE)) {
			return fullRullDateFormat.parse(dateString);
		} else if (dateString.matches(RegexStrings.REG_RULE_DATE)) {
			return dateRullFormat.parse(dateString);
		} else if (dateString.matches(RegexStrings.REG_RULE_PART_DATE)) {
			return partRullDateFormat.parse(dateString);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 功能描述:判断一个日期是否就是当前日期
	 * 
	 * @author xiaozhiqiang
	 * @date 2013-6-19
	 * @param compareDate
	 *            需要判断的日期
	 * @return 是否是当前日期
	 */
	public static boolean isDateToday(Date compareDate) {
		if (compareDate == null)
			throw new IllegalArgumentException("date should not be null");
		DateFormat dateFormat = new SimpleDateFormat(SIMPLE_DATE_STR);
		String dateStr = dateFormat.format(compareDate);
		Date today = new Date();
		String todayStr = dateFormat.format(today);
		return todayStr.equals(dateStr);
	}

	/**
	 * 
	 * 功能描述:判断两个日期是否是同一天
	 * 
	 * @author xiaozhiqiang
	 * @date 2013-6-19
	 * @param date1
	 *            待判断日期一
	 * @param date2
	 *            待判断日期二
	 * @return 是否是同一天
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException("date should not be null");
		DateFormat dateFormat = new SimpleDateFormat(SIMPLE_DATE_STR);
		String dateStr1 = dateFormat.format(date1);
		String dateStr2 = dateFormat.format(date2);
		return dateStr1.equals(dateStr2);
	}

	/**
	 * 
	 * 功能描述:在startDate上加上dayNum天，dayNum可以是负数，如果是负数相当于减dayNum天
	 * 
	 * @author xiaozhiqiang
	 * @date 2013-6-19
	 * @param startDate
	 *            待操作的日期
	 * @param dayNum
	 *            加上的天数
	 * @return
	 */
	public static Date addDate(Date startDate, int dayNum) {
		if (startDate == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DATE, dayNum);
		return calendar.getTime();
	}
	
	/**
	 * 功能描述：在startDate上加上monthNum月，monthNum可以是负数，如果是负数相当于减monthNum月
	 * @author xiaozhiqiang
	 * @date 2015-7-12
	 * @param startDate 待操作的日期
	 * @param monthNum 加上的月天数
	 * @return
	 */
	public static Date addMonth(Date startDate, int monthNum) {
		if (startDate == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.MONTH, monthNum);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * 功能描述:在startDate上加上hourNum小时，hourNum可以是负数，如果是负数相当于减hourNum小时
	 * 
	 * @author xiaozhiqiang
	 * @date 2015-4-29
	 * @param startDate
	 *            待操作的日期
	 * @param dayNum
	 *            加上的小时
	 * @return
	 */
	public static Date addHour(Date startDate, int hourNum) {
		if (startDate == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.HOUR, hourNum);
		return calendar.getTime();
	}
	/**
	 * 
	 * 功能描述:在startDate上加上minute分钟，minute可以是负数，如果是负数相当于减minute分钟
	 * 
	 * @author xiaozhiqiang
	 * @date 2015-4-29
	 * @param startDate
	 *            待操作的日期
	 * @param minute
	 *            加上的分钟
	 * @return
	 */
    public static Date addMinute(Date startDate,int minute){
		if (startDate == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
    }
    

	/**
	 * 将字符串转换成日期
	 * @param dateString
	 * @param formatStr
	 * @return
	 */
	
	public static Date formatDate(String dateString, String formatStr) {
		Date date = null;
		try {
			date = parseToFormatDate(dateString, formatStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
   /**
    * 将日期格式化成字符串
    * @param date
    * @param formatStr
    * @return
    */
	public static String formatString(Date date, String formatStr) {
		String dateString = null;
		try {
			dateString = parseToFormatString(date, formatStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateString;
	}
	/**
	 * 
	 * 功能描述:获取两个日期的相差天数，不够一天算0天。例如2013-06-19 00:00:00与2013-06-21 23:59:59是相差2天
	 * 
	 * @author xiaozhiqiang
	 * @date 2013-6-19
	 * @param startDay
	 *            开始日期
	 * @param endDay
	 *            结束日期
	 * @return 相差的天数
	 */
	public static int getBetweenFloorDays(Date startDay, Date endDay) {
        long diff = endDay.getTime() - startDay.getTime();
        long betweenDays = diff / (1000L * 24 * 60 * 60);
        return (int) betweenDays;
    }
	
	/**
     * 
     * 功能描述:获取两个日期的相差小时数，不够一小时算0小时。
     * 
     * @author xiaozhiqiang
     * @date 2014-3-3
     * @param startDay
     *            开始日期
     * @param endDay
     *            结束日期
     * @return 相差的小时数
     */
    public static int getBetweenFloorHours(Date startDay, Date endDay) {
	    if (startDay == null || endDay == null) {
	        throw new IllegalArgumentException(
	                    "startDay or endDay or dateFormat should not be null");
	    }
	    long diff = endDay.getTime() - startDay.getTime();
	    int betweenDays = (int) diff / (1000 * 60 * 60);
	    return betweenDays;
    }
        
    /**
     * 功能描述:获取两个日期的除去天后相差的小时数
     * @author xiaozhiqiang
     * @date 2014-12-11
     * @param startDay
     * @param endDay
     * @return
     * @throws ParseException 
     */
    public static int getBetweenFloorHoursResidue(String startDay, String endDay, String dateFormatStr) throws ParseException {
    	if (startDay == null || endDay == null || dateFormatStr == null) {
			throw new IllegalArgumentException(
					"startDay or endDay or dateFormat should not be null");
		}
    	DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
    	int day = getBetweenFloorDays(dateFormat.parse(startDay), dateFormat.parse(endDay));
        long diff = dateFormat.parse(endDay).getTime() - dateFormat.parse(startDay).getTime() - (day * 24 * 60 * 60 * 1000l);
        long betweenDays = diff / (1000l * 60 * 60);
        return (int) betweenDays;
    }
    
    /**
     * 功能描述：获取两个日期的时间间隔，单位是小时，保留一位小数
     * @author xiaozhiqiang
     * @date 2014-12-11
     * @param startDay 开始时间
     * @param endDay 结束时间
     * @param dateFormatStr 格式化
     * @return
     */
    public static double getBetweenDoubleHours(String startDay, String endDay, String dateFormatStr) throws ParseException {
    	if (startDay == null || endDay == null || dateFormatStr == null) {
			throw new IllegalArgumentException(
					"startDay or endDay or dateFormat should not be null");
		}
    	DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
    	DecimalFormat df = new DecimalFormat("0.0");
    	long diff = dateFormat.parse(endDay).getTime() - dateFormat.parse(startDay).getTime();
    	String betweenHours = df.format((double) diff / (1000 * 60 * 60));
    	return Double.valueOf(betweenHours);
    }
    
    /**
     * 功能描述：30 - 获取两个日期的时间间隔，单位是小时，保留一位小数
     * @author xiaozhiqiang
     * @date 2014-12-11
     * @param startDay 开始时间
     * @param endDay 结束时间
     * @param dateFormatStr 格式化
     * @return
     */
    public static double getBetweenDoubleHoursReverse(String startDay, String endDay, String dateFormatStr) throws ParseException {
    	if (startDay == null || endDay == null || dateFormatStr == null) {
			throw new IllegalArgumentException(
					"startDay or endDay or dateFormat should not be null");
		}
    	DecimalFormat df = new DecimalFormat("0.0");
    	double doubleHours = getBetweenDoubleHours(startDay, endDay, dateFormatStr);
    	String timeFormat = df.format(30.0 * 24 - doubleHours); // 30天*24小时
    	return Double.valueOf(timeFormat);
    }

	/**
	 * 
	 * 功能描述:获取两个日期的相差天数，不够一天算0天。例如2013-06-19 00:00:00与2013-06-21 23:59:59是相差2天
	 * 
	 * @author xiaozhiqiang
	 * @date 2013-6-19
	 * @param startDay
	 *            开始日期
	 * @param endDay
	 *            结束日期
	 * @param dateFormatStr
	 *            日期格式
	 * @return 相差的天数
	 * @throws ParseException
	 */
	public static int getBetweenFloorDays(String startDay, String endDay,
			String dateFormatStr) throws ParseException {
		if (startDay == null || endDay == null || dateFormatStr == null) {
			throw new IllegalArgumentException(
					"startDay or endDay or dateFormat should not be null");
		}
		DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		int betweenDays = getBetweenFloorDays(dateFormat.parse(startDay),
				dateFormat.parse(endDay));
		return betweenDays;
	}

	/**
	 * 
	 * 功能描述:获取两个日期的间隔，精确到分钟
	 * 
	 * @date 2013-8-15
	 * @param startDay
	 *            开始日期
	 * @param endDay
	 *            结束日期
	 * @return 相差的分钟数
	 * @throws ParseException
	 */
	public static long getMinutesBetweenFloorDays(String startDay, String endDay)
			throws ParseException {
		if (startDay == null || endDay == null) {
			throw new IllegalArgumentException(
					"startDay or endDay should not be null");
		}
		return (long) (parseToDate(endDay).getTime() - parseToDate(startDay)
				.getTime()) / (1000 * 60);
	}

	
	/**
	 * 
	 * 功能描述:获取指定日期的年月日中文描述
	 * 
	 * @author xiaozhiqiang
	 * @date 2013-6-19
	 * @param date
	 *            指定日期
	 * @return 年月日中文描述
	 */
	public static String getChineseDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONDAY) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return year + "年" + month + "月" + day + "日";
	}

	/**
	 * 
	 * 功能描述：计算相隔天数
	 * 
	 * @author xiaozhiqiang
	 * @date 2013-11-9
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 
	 * 功能描述：计算相隔月份数
	 * 
	 * @author xiaozhiqiang
	 * @date 2013-11-21
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonthsBetween(Date date1, Date date2) {

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);

		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		int d1 = c1.get(Calendar.DATE);
		int d2 = c2.get(Calendar.DATE);

		if (d2 < d1) {
			return (year2 - year1) * 12 + month2 - month1 - 1;
		} else {
			return (year2 - year1) * 12 + month2 - month1;
		}

	}
	
	/**
	 * 功能描述：计算相隔年份数
	 * 
	 * @author xiaozhiqiang
	 * @date 2014-9-17
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getYearBetween(Date date1, Date date2) {
		int monthsBetween = getMonthsBetween(date1, date2);
		return monthsBetween / 12;
	}
	
	/**
	 * 
	 * 功能描述：获取日期间的间隔 XX天XX小时XX分
	 * 
	 * @author xiaozhiqiang
	 * @date 2013-11-25
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	public static String getTimeBetweenDate(String start_time, String end_time) {
		try {
			if (start_time == null || end_time == null)
				return "";
			else {
				java.sql.Timestamp start = java.sql.Timestamp
						.valueOf(start_time);
				java.sql.Timestamp end = java.sql.Timestamp.valueOf(end_time);
				long between_hours = (end.getTime() - start.getTime()) / 1000;
				if (between_hours < 0)
					return "";
				else
					return calcdhm(between_hours);
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 
	 * 功能描述：将时间的长整型转换为天、小时、分
	 * 
	 * @author xiaozhiqiang
	 * @date 2013-11-25
	 * @param timeinseconds
	 * @return
	 */
	public static String calcdhm(long timeinseconds) {
		String dhm = "";
		long days = timeinseconds / (24 * 3600);
		long hours = timeinseconds % (24 * 3600) / 3600;
		long minutes = timeinseconds % 3600 / 60;
		if (days + hours + minutes == 0)
			dhm = "小于1分钟";
		if (days + hours == 0 && days + hours + minutes != 0)
			dhm = minutes + "分钟";
		if (days == 0 && days + hours != 0)
			dhm = hours + "小时" + minutes + "分钟";
		if (days != 0)
			dhm = days + "天" + hours + "小时" + minutes + "分钟";
		return dhm;
	}
	
	/**
	 * 功能描述：将日期长整型转换成String格式
	 * @author xiaozhiqiang
	 * @date 2013-11-25
	 * @param currentTime
	 * @return
	 */
	public static String conversionLongToStr(Object currentTime) {
		if (currentTime == null || currentTime instanceof String) {
			return (String)currentTime;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(FULL_DATE_STR);
		return dateFormat.format(currentTime);
	}
	
	/**
	 * 
	 * 功能描述:将String类型的字符串转换成为Date类型，如果String为空则直接返回null
	 * @author xiaozhiqiang
	 * @date 2014-06-09
	 * @param dateString 待转换的字符串
	 * @return Date类型
	 * @throws ParseException
	 */
	public static Date parse2Date(String dateString) throws ParseException{
		
		if(StringUtils.isEmptyStr(dateString)){
			return null;
		}
		
		return parseToDate(dateString);
	}
	
	/**
	 * 功能描述：返回最大的日期
	 * @author xiaozhiqiang
	 * @date 2014-8-21
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date getMaxDate(Date date1,Date date2) {
	    if(date1 == null && date2 == null){
	        return null;
	    }else if(date1 == null){
	        return date2;
	    }else if(date2 == null){
	        return date1;
	    }else{
	        long time1 = date1.getTime();
	        long time2 = date2.getTime();
	        return time1 > time2 ? date1:date2;
	    }
	}
	
	/**
	 * 
	 * 功能描述:比较时间大小
	 * 
	 * @date 2013-8-15
	 * @param startDay
	 *            开始日期
	 * @param endDay
	 *            结束日期
	 * @return boolean
	 * @throws ParseException
	 */
	public static boolean compareTimeBetweenDate(String startDay, String endDay)
			throws ParseException {
		if (startDay == null || endDay == null) {
			throw new IllegalArgumentException(
					"startDay or endDay should not be null");
		}
		return (parseToDate(endDay).getTime() - parseToDate(startDay)
				.getTime()) > 0?true:false;
	}
	
	/**
	 * 
	 * 功能描述：返回当前时间是星期几
	 *@author xiaozhiqiang
	 *@date 2014-12-16
	 *@param date
	 *@return
	 */
	public static int getWeek(Date date) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	   int hour=c.get(Calendar.DAY_OF_WEEK);
	  // hour中存的就是星期几了，其范围 0~6
	  // 0=星期日 7=星期六，其他类推
	  return hour - 1;
	 }
	
	/**
	 * 功能描述：获取上一季度起始时间
	 * @author xiaozhiqiang
	 * @date 2015-4-14
	 * @param date
	 * @return
	 */
	public static Date getPrevSeasonBegin(Date date) {
		Calendar c = Calendar.getInstance();  
        c.setTime(date);
        int season = getSeason(date); // 当前季度
        int year = 0;
        int month = 0;
        int day = 0;
        year = c.get(Calendar.YEAR);
        if (1 == season) { // 如果是一季度，则获取去年
        	year = c.get(Calendar.YEAR) - 1;
        	month = Calendar.OCTOBER;
		} else if (2 == season) {
        	month = Calendar.JANUARY;
		} else if (3 == season) {
        	month = Calendar.APRIL;
		} else if (4 == season) {
        	month = Calendar.JULY;
		}
        day = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        c.set(year, month, day, 0, 0, 0);
        return c.getTime();
	}
	
	/**
	 * 功能描述：获取上一季度结束时间
	 * @author xiaozhiqiang
	 * @date 2015-4-14
	 * @param date
	 * @return
	 */
	public static Date getPrevSeasonEnd(Date date) {
		Calendar c = Calendar.getInstance();  
        c.setTime(date);
        int season = getSeason(date); // 当前季度
        int year = 0;
        int month = 0;
        int day = 0;
        year = c.get(Calendar.YEAR);
        if (1 == season) { // 如果是一季度，则获取去年
        	year = c.get(Calendar.YEAR) - 1;
        	month = Calendar.DECEMBER;
		} else if (2 == season) {
        	month = Calendar.MARCH;
		} else if (3 == season) {
        	month = Calendar.JUNE;
		} else if (4 == season) {
        	month = Calendar.SEPTEMBER;
		}
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(year, month, day, 23, 59, 59);
        return c.getTime();
	}
	
	/**
	 * 功能描述：获得当前季度
	 * @author xiaozhiqiang
	 * @date 2015-4-14
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {
		int season = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}
	
	/**
	 * 获取 系统时间第二天00:00:00:000
	 * @return
	 */
	public static Date getStartDate(){
		Calendar c = Calendar.getInstance();
		
		c.add(Calendar.DAY_OF_MONTH, 1 );
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c.getTime();
	}
	
	/**
	 * 获取 起保时间加1年
	 * @return
	 */
	public static Date getEndDate(Date startDate){
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.SECOND, -1);
		
		return c.getTime();
	}
	/**
	 * 获取当全年天数
	 * @param dateTime
	 * @return
	 */
	public static int getFullDaysOfYear(Date dateTime){
		return is366(dateTime) ? 366 : 365 ;
	}
	
	/**
	 * 获取当月的天数
	 * @param dateTime
	 * @return
	 */
	public static int getDaysOfMonth(Date dateTime){
		Calendar cl = Calendar.getInstance();
		
		cl.setTime( dateTime);
		int month = cl.get(Calendar.MONTH ) + 1 ;
		
		int day = 0;
		if(month == 1 || month ==3 || month == 5 || month == 7 ||month==8 || month==10 || month==12){
			day = 31;
		}else if(month == 4 || month== 6 || month== 9 || month ==11){
			day = 30;
		}else {
			day = is366(dateTime) ? 29 : 28;
		}
		
		return day;
	}
	
	/**
	 * 判断是否是闰年
	 * @param dateTime
	 * @return
	 */
	public static boolean is366(Date dateTime){
		Calendar cl = Calendar.getInstance();
		cl.setTime( dateTime);
		
		int year = cl.get(Calendar.YEAR);
		
		boolean is366 = (year%4 == 0 && year%100 != 0) || year%400 == 0;
		
		return is366;
	}
	
	
	
	/**
	 * 获取指定日期的0时0分0秒
	 * @param date
	 * @return
	 */
	public static Date getStartTime(Date date){
		
		Calendar cl = Calendar.getInstance();
		if(date != null ){
			cl.setTime(date);
		}
		
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE,0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		
		return cl.getTime();
	}
	
	
	/**
	 * 获取指定日期的23时59分59秒999
	 * @param date
	 * @return
	 */
	public static Date getEndTime(Date date){
		Calendar cl = Calendar.getInstance();
		if(date != null ){
			cl.setTime(date);
		}
		
		cl.set(Calendar.HOUR_OF_DAY, 23);
		cl.set(Calendar.MINUTE,59);
		cl.set(Calendar.SECOND, 59);
		cl.set(Calendar.MILLISECOND, 999);
		
		return cl.getTime();
	}
	
	/**
	 * 2个时间的相差的年
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Integer betweenYear( Date startTime,Date endTime  ){
		
		Calendar cl = Calendar.getInstance();
		
		cl.setTime( startTime);
		int start = cl.get(Calendar.YEAR );
		
		cl.setTime(endTime);
		int end = cl.get(Calendar.YEAR );
		
		return end - start;
	}
	
	/**
	 * 2个时间的相差的月
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int betweenMonth(Date startTime,Date endTime ){
		
		Calendar cl = Calendar.getInstance();
		
		cl.setTime( startTime);
		int start = cl.get(Calendar.MONTH );
		int startYear = cl.get(Calendar.YEAR);
		
		cl.setTime(endTime);
		int end = cl.get(Calendar.MONTH );
		int endYear = cl.get(Calendar.YEAR);
		
		return (end - start) + (endYear-startYear) * 12 ;
	}
	
	/**
	 * 2个时间(yyyy-MM-dd)的相差的日
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int betweenDay(Date startTime,Date endTime ){
		long time = endTime.getTime() -  startTime.getTime();
		
		Long days = (time / ONEDAY_VALUE) + 1  ;
		
		return days.intValue();
	}
	
	/**
	 * 增加一年
	 * @param dateTime
	 * @return
	 */
	public static Date addOneYear( Date dateTime ){
		Calendar cl = Calendar.getInstance();
		cl.setTime( dateTime);
		
		cl.add(Calendar.YEAR, 1 );
		//同时要减掉一秒
		cl.add(Calendar.SECOND, -1);
		
		return cl.getTime();
	}
	
	/**
	 * 增加一年
	 * @param dateTime
	 * @return
	 */
	public static Date addOneYear( Date dateTime ,int years){
		Calendar cl = Calendar.getInstance();
		cl.setTime( dateTime);
		
		cl.add(Calendar.YEAR, years );
		//同时要减掉一秒
		cl.add(Calendar.SECOND, -1);
		
		return cl.getTime();
	}
	
	/**
	 * 日期与时间合并
	 * @param date
	 * @param time
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date combine(Date date,Time time){
		Calendar cl = Calendar.getInstance();
		
		cl.setTime(date);
		
		cl.set(Calendar.HOUR_OF_DAY, time.getHours());
		cl.set(Calendar.MINUTE, time.getMinutes());
		cl.set(Calendar.SECOND, time.getSeconds());
		
		return cl.getTime();
	}
	
	/**
	 * 日期与时间合并
	 * @param date
	 * @param time HH:mm:ss
	 * @return
	 */
//	public static Date combine(Date date,String time){
//		String dateString = format(date) + " " + time;
//		try {
//			return parseToFormatDate(dateString, FULL_DATE_STR);
//		} catch (ParseException e) {
//			throw new ParameterException("时间格式错误");
//		}
//	}
	
    public static void main(String[] args) throws Exception{
//    	System.out.println(getDays());
//    	System.out.println(getAfterDayWeek("3"));
//    	Date startDate = getStartDate();
//    	Date endDate = getEndDate(new Date());
//    	
//    	System.out.println("start=="+parseToFormatString(startDate, FULL_DATE_STR));
//    	System.out.println("end  =="+parseToFormatString(endDate, FULL_DATE_STR));
//    	
//    	Time time = new Time(10, 10, 10);
//    	
//    	System.out.println(time.getTime());
//    	
//    	Date date = combine(new Date(), time);
//    	
//    	System.out.println(format(date,"yyyy-MM-dd HH:mm:ss"));

    }

}
