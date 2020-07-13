package com.family.config;
 
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
/***
 * 日期工具类
 * 
 * @author damao
 *
 */
public class DateAndTimeUtil {
	/***
	 * 日期月份减一个月
	 * 
	 * @param datetime
	 *            日期(2014-11)
	 * @return 2014-10
	 */
	public static String dateFormat(String datetime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(datetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.MONTH, -1);
		date = cl.getTime();
		return sdf.format(date);
	}
 
	public static String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}

	public static String dateFormatDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
 
	/****
	 * 传入具体日期 ，返回具体日期减一个月。
	 * 
	 * @param date
	 *            日期(2014-04-20)
	 * @return 2014-03-20
	 * @throws ParseException
	 */
	public static String subMonth(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = sdf.parse(date);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
 
		rightNow.add(Calendar.MONTH, -1);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
 
		return reStr;
	}
 
	/****
	 * 获取月末最后一天
	 * 
	 * @param sDate
	 *            2014-11-24
	 * @return 30
	 */
	private static String getMonthMaxDay(String sDate) {
		SimpleDateFormat sdf_full = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf_full.parse(sDate + "-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		int last = cal.getActualMaximum(Calendar.DATE);
		return String.valueOf(last);
	}
 
	// 判断是否是月末
	public static boolean isMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DATE) == cal
				.getActualMaximum(Calendar.DAY_OF_MONTH))
			return true;
		else
			return false;
	}
 
	/***
	 * 日期减一天、加一天
	 * 
	 * @param option
	 *            传入类型 pro：日期减一天，next：日期加一天
	 * @param _date
	 *            2014-11-24
	 * @return 减一天：2014-11-23或(加一天：2014-11-25)
	 */
	public static String checkOption(String option, String _date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cl = Calendar.getInstance();
		Date date = null;
 
		try {
			date = (Date) sdf.parse(_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cl.setTime(date);
		if ("pre".equals(option)) {
			// 时间减一天
			cl.add(Calendar.DAY_OF_MONTH, -1);
 
		} else if ("next".equals(option)) {
			// 时间加一天
			cl.add(Calendar.DAY_OF_YEAR, 1);
		} else {
			// do nothing
		}
		date = cl.getTime();
		return sdf.format(date);
	}
 
	/***
	 * 判断日期是否为当前月， 是当前月返回当月最小日期和当月目前最大日期以及传入日期上月的最大日和最小日
	 * 不是当前月返回传入月份的最大日和最小日以及传入日期上月的最大日和最小日
	 * 
	 * @param date
	 *            日期 例如：2014-11
	 * @return String[] 开始日期，结束日期，上月开始日期，上月结束日期
	 * @throws ParseException
	 */
	public static String[] getNow_Pre_Date(String date) throws ParseException {
 
		String[] str_date = new String[4];
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf_full = new SimpleDateFormat("yyyy-MM-dd");
		String stMonth = sdf.format(now);
		String stdate = "";// 开始日期
		String endate = "";// 结束日期
		String preDate_start = "";// 上月开始日期
		String preDate_end = "";// 上月结束日期
 
		// 当前月
		if (date.equals(stMonth)) {
			stdate = stMonth + "-01"; // 2014-11-01
			endate = sdf_full.format(now);// 2014-11-24
			preDate_start = subMonth(stdate);// 2014-10-01
			preDate_end = subMonth(endate);// 2014-10-24
		} else {
			// 非当前月
			String monthMaxDay = getMonthMaxDay(date);
			stdate = date + "-01";// 2014-10-01
			endate = date + "-" + monthMaxDay;// 2014-10-31
			preDate_start = subMonth(stdate);// 2014-09-01
			preDate_end = subMonth(endate);// 2014-09-30
		}
		str_date[0] = stdate;
		str_date[1] = endate;
		str_date[2] = preDate_start;
		str_date[3] = preDate_end;
 
		return str_date;
	}

	/**
	 * @Description: 添加小时
	 * @Author: 杜飞龙
	 * @Date: 2020/3/26
	 * @param dateStr:
     * @param hour:
	 * @return: java.lang.String
	 **/
	public static Date addDateMinut(String dateStr, int hour){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date= new Date( );
		try {
			if(StringUtils.isNotEmpty( dateStr )) {
				date = format.parse( dateStr );
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("front:" + format.format(date)); //显示输入的日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);// 24小时制
		date = cal.getTime();
		System.out.println("after:" + format.format(date));  //显示更新后的日期
		cal = null;
		return date;

	}

	public static Date addDateMinut(int hour){
		return addDateMinut(null,hour);
	}

	/** 比较两个字符串时间大小 */
	public static int compareTwoTime(String time1, String time2) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		int flagValue = 0;
		try {
			Date date1, date2;
			date1 = simpleDateFormat.parse(time1);
			date2 = simpleDateFormat.parse(time2);
			long millisecond = date1.getTime() - date2.getTime();
			if (millisecond > 0) {
				flagValue = 1;
			} else if (millisecond < 0) {
				flagValue = -1;
			} else if (millisecond == 0) {
				flagValue = 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (flagValue);
	}

	/** 表两个时间差 */
	public static int compareTwoTime(Date time1, Date time2) {
		int flagValue = 0;
		try {
			long millisecond = time1.getTime() - time2.getTime();
			if (millisecond > 0) {
				flagValue = 1;
			} else if (millisecond < 0) {
				flagValue = -1;
			} else if (millisecond == 0) {
				flagValue = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (flagValue);
	}

	/** 比较两个时间相差天数 */
	public static float calculateTimeGapDay(String time1, String time2) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		float day = 0;
		Date date1 = null;
		Date date2 = null;

		try {
			date1 = simpleDateFormat.parse(time1);
			date2 = simpleDateFormat.parse(time2);
			long millisecond = date2.getTime() - date1.getTime();
			day = millisecond / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (day);
	}

	/** 比较两个时间相差天数 */
	public static float calculateTimeGapDay(Date time1, Date time2) {
		float day = 0;
		try {
			Date date1, date2;
			date1 = time1;
			date2 = time2;
			long millisecond = date2.getTime() - date1.getTime();
			day = millisecond / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (day);
	}

	/** 比较两个时间相差小时 */
	public static double calculatetimeGapHour(String time1, String time2) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		double hour = 0;
		try {
			Date date1, date2;
			date1 = simpleDateFormat.parse(time1);
			date2 = simpleDateFormat.parse(time2);
			double millisecond = date2.getTime() - date1.getTime();
			hour = millisecond / (60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return hour;
	}

	/** 比较两个时间相差小时 */
	public static double calculatetimeGapHour(Date date1, Date date2) {
		double hour = 0;
		double millisecond = date2.getTime() - date1.getTime();
		hour = millisecond / (60 * 60 * 1000);
		return hour;
	}

	/** 比较两个时间相差分钟 */
	public static double calculatetimeGapMinute(String time1, String time2) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		double minute = 0;
		try {
			Date date1, date2;
			date1 = simpleDateFormat.parse(time1);
			date2 = simpleDateFormat.parse(time2);
			double millisecond = date2.getTime() - date1.getTime();
			minute = millisecond / (60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return minute;
	}

	/** 比较两个时间相差分钟 */
	public static double calculatetimeGapMinute(Date date1, Date date2) {
		double minute = 0;
		double millisecond = date2.getTime() - date1.getTime();
		minute = millisecond / (60 * 1000);
		return minute;
	}

	/** 比较两个时间相差秒 */
	public static double calculatetimeGapSecond(String time1, String time2) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		double second = 0;
		try {
			Date date1, date2;
			date1 = simpleDateFormat.parse(time1);
			date2 = simpleDateFormat.parse(time2);
			double millisecond = date2.getTime() - date1.getTime();
			second = millisecond / (1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return second;
	}

	/** 比较两个时间相差秒 */
	public static double calculatetimeGapSecond(Date date1, Date date2) {
		double second = 0;
		double millisecond = date2.getTime() - date1.getTime();
		second = millisecond / (1000);
		return second;
	}

	/**
	 * @Description: 返回动态发布时间和当前时间差值
	 * @Author: 杜飞龙
	 * @Date: 2020/3/27
	 * @param date1:
	 * @param date2:
	 * @return: java.lang.String
	 **/
	public static String getTimestampForMemory(Date date1, Date date2){
		Double cday=Math.floor( calculateTimeGapDay( date1,date2 ) ) ;
		String dayStr;
		if(cday>=1){
			dayStr=String.valueOf( cday );
			dayStr=dayStr.substring( 0, dayStr.indexOf("."));
			return dayStr+"天前";
		}
		cday=Math.floor(calculatetimeGapHour( date1,date2 ) );
		if(cday>=1){
			dayStr=String.valueOf( cday );
			dayStr=dayStr.substring( 0, dayStr.indexOf("."));
			return dayStr+"小时前";
		}
		cday=Math.floor(calculatetimeGapMinute( date1,date2 )  );
		if(cday>=1){
			dayStr=String.valueOf( cday );
			dayStr=dayStr.substring( 0, dayStr.indexOf("."));
			return dayStr+"分钟前";
		}
		return "刚刚";
	}
}