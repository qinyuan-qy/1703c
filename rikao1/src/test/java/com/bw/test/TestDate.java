package com.bw.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.bw.util.DateUtil;

public class TestDate {
	
	@Test
	public void Test1() throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date date = format.parse("2000-11-20");
		int age = DateUtil.getAge(date);
		System.out.println(age);
	}
	@Test
	public void Test2(){
		
		//DateUtil.getAge(new Date(2000-11-20));
	}
	@Test
	public void Test3() throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date date = format.parse("2000-11-20");
		Date dateByMonthLast = DateUtil.getDateByMonthLast(date);
		System.out.println(dateByMonthLast);
	}
	@Test
	public void Test4(){
		
		//DateUtil.getAge(new Date(2000-11-20));
	}
	@Test
	public void Test5(){
		
		DateUtil.getDaysByDeparted(new Date(2000-11-20));
	}

	
}
