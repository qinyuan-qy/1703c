package com.bw.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.bw.util.DateUtil;

public class TestDate {
	
	@Test
	public void Test1() throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2000-11-20");
		int age = DateUtil.getAge(date);
		System.out.println(age);
	}
	@Test
	public void Test2() throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2000-11-20");
		Date date2 = DateUtil.getDateByMonthInit(date);
		String string = format.format(date2);
		System.out.println(string);
	}
	@Test
	public void Test3() throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2000-2-20");
		Date dateByMonthLast = DateUtil.getDateByMonthLast(date);
		String string = format.format(dateByMonthLast);
		System.out.println(string);
	}
	/**
	 *求未来日期离今天还剩的天数
	 * @throws ParseException
	 */
	@Test
	public void Test4() throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2019-11-20");
		int future = DateUtil.getDaysByFuture(date);
		if(future<0){
			System.out.println(-future);
		}else{
			System.out.println(future);
		}
		
	}
	@Test
	public void Test5() throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2000-11-20");
		int future = DateUtil.getDaysByDeparted(date);
		if(future<0){
			System.out.println(-future);
		}else{
			System.out.println(future);
		}
	}

	
}
