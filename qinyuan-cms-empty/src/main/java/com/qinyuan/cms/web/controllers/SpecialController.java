package com.qinyuan.cms.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class SpecialController {
	
	@RequestMapping("/Article")
	public String Article(){
		
		
		return "/admin/article";
		
	}
}
