package com.insigma.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.insigma.resolver.AppException;

/**
 * 时间格式的转换
 * @author 周兆巍
 */
public final class DateUtil 
{
	/**
	 * 日期紧凑格式
	 */
	public static final String COMPACT_DATE_FORMAT = "yyyyMMdd";

	/**
	 * 日期普通格式
	 */
	public static final String NORMAL_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 日期格式 年月日 时分秒
	 */
	public static final String NORMAL_DATE_FORMAT_NEW = "yyyy-mm-dd hh24:mi:ss";

	/**
	 * 将字符串日期转换成yyyyMMdd的形式，strDate格式必须"yyyy-MM-dd"。
	 * 将字符串日期转换成yyyyMM的形式，strDate格式必须"yyyy-MM"。
	 * @param strDate
	 * @return
	 * @throws Exception
	 */
	public static Long strDateToNum(String strDate) throws AppException{
		if(strDate==null){
			return null;
		}
		String[] date=null;
		String newDate="";
		if(strDate.indexOf("-")>=0)
		{
		     date=strDate.split("-");
		     for(int i=0;i<date.length;i++){
		    	 newDate=newDate+date[i];
		     }
		     return Long.parseLong(newDate);
		}

		return Long.parseLong(strDate);
	}
	/**
	 * 将字符串日期转换成yyyyMMdd的形式，strDate格式为"yyyy-MM-dd"或"yyyy-M-d"。
	 * 将字符串日期转换成yyyyMM的形式，strDate格式必须"yyyy-M"。
	 * @param strDate
	 * @return
	 * @throws Exception
	 */
	public static Long strDateToNum1(String strDate) throws AppException{
		if(strDate==null){
			return null;
		}
		String[] date=null;
		String newDate="";
		if(strDate.indexOf("-")>=0)
		{
		     date=strDate.split("-");
		     for(int i=0;i<date.length;i++){
		    	 if(date[i].length()== 1){
		    		 newDate=newDate+"0"+date[i];
		    	 }else{
		    		 newDate=newDate+date[i];
		    	 }
		     }
		     return Long.parseLong(newDate);
		}

		return Long.parseLong(strDate);
	}
	/**
	 * 将数字日期转换成yyyy-MM-dd的字符串形式"。
	 * @param numDate
	 * @return
	 */
	public static String numDateToStr(Long numDate)
	{
		if(numDate==null){
			return null;
		}
		String strDate=null;
		strDate=numDate.toString().substring(0, 4)+"-"+numDate.toString().substring(4, 6)+"-"+
		numDate.toString().substring(6, 8);
		return strDate;
	}


    /**
     * 将传入的字符串，根据给定的格式转换为Date类型
     * @param str 待转换的字符串
     * @param format 指定的格式
     * @return 转换后的日期
     * @throws AppException 如果转换出错将抛出此异常
     * @author 冯宁前
     */
    public static Date stringToDate(String str,String format) throws AppException{
    	SimpleDateFormat sdf=new SimpleDateFormat(format);
    	try {
    		if ((str==null)||(str.equalsIgnoreCase(""))){
    			return null;
    		}
			return sdf.parse(str);
		} catch (ParseException e) {
			throw new AppException("解析日期字符串时出错！");
		}
    }

    /**
     * 将传入的日期，根据给定的格式，格式化为字符串
     * @param date 需要转换的日期
     * @param format 指定的格式
     * @return 格式化后的字符串
     * @author 冯宁前
     */
    public static String dateToString(Date date,String format){
    	SimpleDateFormat sdf=new SimpleDateFormat(format);
    	if(date==null){
    		return "";
    	}
    	return sdf.format(date);
    }

    /**
     * 将字符串转换为日期类型，字符串的格式为紧凑格式，格式为 COMPACT_DATE_FORMAT
     * @param str 需要转换的字符串
     * @return 转换后得到的日期
     * @throws AppException 转换失败将抛出此异常
     * @author 冯宁前
     */
    public static Date compactStringToDate(String str) throws AppException{
    	return stringToDate(str, COMPACT_DATE_FORMAT);
    }

    /**
     * 将日期类型格式化为字符串，字符串的格式为紧凑格式，格式为 COMPACT_DATE_FORMAT
     * @param date 需要格式化的日期
     * @return 格式化得到的字符
     * @author 冯宁前
     */
    public static String dateToCompactString(Date date){
    	return dateToString(date, COMPACT_DATE_FORMAT);
    }

    /**
     * 将日期转换为普通日期格式字符串，字符串的格式为 NORMAL_DATE_FORMAT
     * @param date 需要格式化的日期
     * @return 格式化得到的字符
     * @author 冯宁前
     */
    public static String dateToNormalString(Date date){
    	return dateToString(date, NORMAL_DATE_FORMAT);
    }

    /**
     * 将紧凑格式日期字符串转换为普通日期格式字符串
     * @param str 紧凑格式日期字符串
     * @return 普通日期格式字符串
     * @throws AppException 如果转换不成功将抛出此异常
     * @author 冯宁前
     */
    public static String compactStringDateToNormal(String str) throws AppException{
    	return dateToNormalString(compactStringToDate(str));
    }

    /**
     * 取二个日期之间的天数
     * @param date_str 起始日期
     * @param date_end 终止日期
     * @return 日期间天数
     * @author 李桂
     */
    public static int getDaysBetween(Date date_str, Date date_end) throws AppException{
		Calendar d1 = Calendar.getInstance();
		Calendar d2 = Calendar.getInstance();
		d1.setTime(date_str);
		d2.setTime(date_end);
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			throw new AppException("起始日期小于终止日期!");
		}
		int days = d2.get(java.util.Calendar.DAY_OF_YEAR)
				- d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = d2.get(java.util.Calendar.YEAR);
		if (d1.get(java.util.Calendar.YEAR) != y2) {
			d1 = (java.util.Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				d1.add(java.util.Calendar.YEAR, 1);
			} while (d1.get(java.util.Calendar.YEAR) != y2);
		}
		return days;
	}

    /**
     * 日期加N天(正负天数)
     * @param date 日期
     * @param days 天数
     * @return 日期间天数
     * @author 李桂
     */
    public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int days1 = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, days1 + days);
		return calendar.getTime();

	}

    /**
     * 字符型日期加N天(正负天数)
     * @param str 字符型日期
     * @param format 字符型格式(实际的字符型日期格式：yyyyMMdd yyyy-MM-dd)
     * @param days 天数
     * @return 日期间天数
     * @author 李桂
     */
    public static Date addDays(String str,String format, int days) throws AppException{
		Calendar calendar = Calendar.getInstance();
		Date date=stringToDate(str, format);
		calendar.setTime(date);
		int days1 = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, days1 + days);
		return calendar.getTime();

	}

    /**
     * @$comment 将java.util.Date 转成 java.sql.Date
     * @param date java.util.Date
     * @return java.sql.Date
     * @throws AppException
     */
    public static java.sql.Date getSqlDate(Date date) throws AppException{
        java.sql.Date sqlDate= new java.sql.Date(date.getTime());
		return sqlDate;
    }
    
    
	/**
	 * 年月往前往后变化几个月
	 * @param date
	 * @param i
	 * @return
	 */
	
	public static Integer addMonth(Date date,int i) {
		if(date==null){
			return null;
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH,i);
		int yearno=cal.get(Calendar.YEAR);
		int monthno=cal.get(Calendar.MONTH)+1;
		return new Integer(yearno*100+monthno);
	}
    
	/**
	 * 年月往前往后变化几个月
	 * @param idate
	 * @param i
	 * @return
	 * @throws Exception
	 */
	
	public static Integer addMonth(Integer idate,int i) throws Exception{
		if(idate==null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = sdf.parse(idate+"01");
		return addMonth(date,i);
		
	}
	/**
	 * 转换日期
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String formatDate(Date date) throws Exception{
		return dateToString(date,"yyyy-MM-dd");
	}
	/**
	 * 得到系统时间
	 * @return
	 */
	public static String getSysDate() {
		return dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 将日期转换成年-月-日 时:分:秒格式的字符串
	 * @param date
	 * @return
	 */	
	public static String formatDateTime(Date date){
		if(date==null){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 将日期转换成年月日时分秒格式的字符串
	 * @param date
	 * @return
	 */	
	public static String formatDateTime2(Date date){
		if(date==null){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(date);
	}
	
	
	/**
	 * 将格式为年-月-日 时:分:秒格式的字符串转换成日期
	 * @param strDate
	 * @return
	 * @throws Exception
	 */
	public static Date parseDateTime(String strDate) throws Exception{
		if(strDate==null){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(strDate);
	}
	
	/**
	 * 比较两个时间
	 * @param now 当前时间
	 * @param lastdate 过去时间
	 * @param comparemillseconds 间隔毫秒数
	 * @return
	 */
    public static boolean compare(Date now,Date lastdate,long comparemillseconds) {
		return (now.getTime()-lastdate.getTime())>comparemillseconds;
	}
}
