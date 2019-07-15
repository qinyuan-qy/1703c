package com.qy.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.qy.Utils.AssertUtil;

public class TestExcption {
	
	@Test
	public void TestisTrue(){
		AssertUtil.isTrue(false, "这不是一个true");
	}
	
	@Test
	public void TestisFalse(){
		AssertUtil.isFalse(false, "这不是一个false");
	}
	
	@Test
	public void TestnotNull(){
		AssertUtil.notNull(null, "这个对象为空");
	}
	
	@Test
	public void TestisNull(){
		AssertUtil.isNull("", "这个对象不为空");
	}
	
	@Test
	public void TestnotEmpty(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		//list.add(555);
		AssertUtil.notEmpty(list, "这个集合为空");
	}
	
	@Test
	public void TestnotEmpty2(){
		HashMap<String,Object> map = new HashMap<String, Object>();
		//map.put("aaa", 10);
		AssertUtil.notEmpty(map, "这个集合为空");
	}
	
	@Test
	public void TesthasText(){
		AssertUtil.hasText(" ", "这个字符串为空");
	}
	
	@Test
	public void TestgreaterThanZero(){
		AssertUtil.greaterThanZero(222, "这个值小于等于0");
	}
	
}
