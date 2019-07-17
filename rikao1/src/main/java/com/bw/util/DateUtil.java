package com.bw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	
	//方法1：根据传入的日期获取年龄
	
	public static int getAge (Date src) {
		Calendar cal = Calendar.getInstance(); 
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH); 
	    int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(src); 
        int yearBirth = cal.get(Calendar.YEAR); 
        int monthBirth = cal.get(Calendar.MONTH); 
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = year - yearBirth; 
        if (month <= monthBirth) { 
            if (month == monthBirth) { 
                if (day < dayOfMonthBirth) age--; 
            }else{ 
                age--; 
            } 
        } 
        System.out.println("age:"+age); 
        return age; 
	
	}
	
	//方法2：根据传入的参数获取该日期的月初日期，
	//例如给定的日期为“2019-09-19 19:29:39”，返回“2019-09-01 00:00:00”
	public static Date getDateByMonthInit (Date src) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String string = format.format(src);
		Date date2 = format.parse(string);
		Calendar cale = Calendar.getInstance();
		cale.setTime(date2);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        String firstday = format.format(cale.getTime());
        Date date = format.parse(firstday);
		
		return date;
	
	}
	
	//方法3 :根据传入的参数获取该日器的月末日期，
	//例如给定的日期为“2019-09-19 19:29:39”，返回“2019-09-30 23:59:59”，
	//注意月大月小以及闰年。
	public static Date getDateByMonthLast(Date src) throws Exception {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String string = format.format(src);
			Date date2 = format.parse(string);
			Calendar cale = Calendar.getInstance();
			cale.setTime(date2);
	        cale.add(Calendar.MONTH, 1);
	        cale.set(Calendar.DAY_OF_MONTH, 0);
	        String  lastday = format.format(cale.getTime());
	        Date date = format.parse(lastday);
	       
		return date;
		
	}
//	方法4：求未来日期离今天还剩的天数
	public static int getDaysByFuture (Date future) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String string = format.format(future);
		Date date2 = format.parse(string);
		Date date = new Date();
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);
		
		int day1 = cale.get(Calendar.DAY_OF_YEAR);
		int day2 = cal.get(Calendar.DAY_OF_YEAR);
		int year1 = cale.get(Calendar.YEAR);
		int year2 = cal.get(Calendar.YEAR);
		if(year1 != year2){
			int timedate = 0;
			for (int i = year1; i < year2; i++) {
				if(i%4 == 0 && i%100 != 0 || i%400 == 0){
					timedate+=366;
				}else{
					timedate+=365;
				}
			}
			if((day1-day2)<0){
				return timedate - (day1-day2);
			}else{
				return timedate + (day1-day2);
			}
			
		}else{
			return (day1-day2);
		}
	
		
	}
	
	//方法5：求过去日期离今天过去的天数
	public static int getDaysByDeparted (Date departed) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String string = format.format(departed);
		Date date2 = format.parse(string);
		Date date = new Date();
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);
		
		int day1 = cale.get(Calendar.DAY_OF_YEAR);
		int day2 = cal.get(Calendar.DAY_OF_YEAR);
		int year1 = cale.get(Calendar.YEAR);
		int year2 = cal.get(Calendar.YEAR);
		if(year1 != year2){
			int timedate = 0;
			for (int i = year2; i < year1; i++) {
				if(i%4 == 0 && i%100 != 0 || i%400 == 0){
					timedate+=366;
				}else{
					timedate+=365;
				}
			}
			if((day1-day2)<0){
				return timedate - (day1-day2);
			}else{
				return timedate + (day1-day2);
			}
		}else{
			return (day1-day2);
		}
		
	}
		
	}

