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
public class ChnnelCategroyController {
	
	@Resource
	private ChannelCategoryService channelCategoryService;
	
	@RequestMapping("/channelList")
	@ResponseBody
	public List<Channel> chnnelList(){
		List<Channel> channels = channelCategoryService.getChannels();
		return channels;
	}
	
	@RequestMapping("/categroyList")
	@ResponseBody
	public List<Category> categroyList(Integer cid){
		List<Category> channels = channelCategoryService.getCategories(cid);
		return channels;
	}
}
