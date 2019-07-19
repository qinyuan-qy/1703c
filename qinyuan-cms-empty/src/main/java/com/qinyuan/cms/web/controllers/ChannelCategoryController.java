package com.qinyuan.cms.web.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qinyuan.cms.domain.Category;
import com.qinyuan.cms.domain.Channel;
import com.qinyuan.cms.service.ChannelCategoryService;

@Controller
public class ChannelCategoryController {
	
	@Resource
	ChannelCategoryService channelCategoryService;
	
	@RequestMapping("/queryAllChannel")
	@ResponseBody
	public List<Channel> queryAllChannel(){
		return channelCategoryService.getChannels();
	}
	@RequestMapping("/queryCategoryByChannelId")
	@ResponseBody
	public List<Category> queryCategoryByChannelId(Integer channel){
		return channelCategoryService.getCategories(channel);
	}
}
