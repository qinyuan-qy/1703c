package com.bw.controller;

import java.util.List;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bw.bean.Moive;
import com.bw.bean.User;
import com.bw.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
public class UserController {
	
	@Resource
	private UserService us;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public Integer login(User user,HttpSession session){
		System.out.println(user);
		User u = us.login(user);
		if(u == null){
			return 0;
		}
		session.setAttribute("user", u);
		return 1;
		
	}
	@RequestMapping("/list.do")
	public String findall(ModelMap map,@RequestParam(required=false,defaultValue="1")Integer pageNum){
		Page<Object> page = PageHelper.startPage(pageNum, 2);
		List<Moive> list = us.findall();
		map.put("list",list);
		map.put("pageNum",pageNum);
		map.put("zongye",page.getPages());
		return "index";
		
	}
	
	@RequestMapping("/shangjia.do")
	@ResponseBody
	public Integer shangjia(Integer mid){
		Moive m = us.shangjia(mid);
		m.setMstatus(0);
		int num = us.updatejia(m);
		return num;
		
	}
	@RequestMapping("/xiajia.do")
	@ResponseBody
	public Integer xiajia(Integer mid){
		Moive m = us.shangjia(mid);
		m.setMstatus(1);
		int num = us.updatejia(m);
		return num;
		
	}
}
