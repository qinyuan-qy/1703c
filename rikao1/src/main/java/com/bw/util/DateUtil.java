package com.bw.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	
	//方法1：根据传入的日期获取年龄
	
	public static int getAge (Date src) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		String format = sdf.format(new Date());
		
		
		return 0;
	
	}
	
	//方法2：根据传入的参数获取该日期的月初日期，
	//例如给定的日期为“2019-09-19 19:29:39”，返回“2019-09-01 00:00:00”
	public static Date getDateByMonthInit (Date src) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		
		
		return src;
	
	}
	
	//方法3 :根据传入的参数获取该日器的月末日期，
	//例如给定的日期为“2019-09-19 19:29:39”，返回“2019-09-30 23:59:59”，
	//注意月大月小以及闰年。
	public static Date getDateByMonthLast(Date src) {
		
		return src;
		
	}
//	方法4：求未来日期离今天还剩的天数
	public static int getDaysByFuture (Date future) {
		
		
		
		
		return 0;
		
	}
	
	//方法5：求过去日期离今天过去的天数
	public static int getDaysByDeparted (Date departed) {
		
		
		
		return 0;
		
	}


}
