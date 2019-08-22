/**
 * 
 */
package com.qinyuan.cms.web.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.domain.User;
import com.qinyuan.cms.service.ArticleService;
import com.qinyuan.cms.utils.FileUploadUtil;
import com.qinyuan.cms.utils.PageUtils;
import com.qinyuan.cms.utils.WordSortUtil;
import com.qinyuan.cms.web.Constant;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2018年1月10日 下午2:40:38
 */
@Controller
@RequestMapping("/my")
public class UserController {
	
	@Resource
	private ArticleService articleService;
	
	
	@RequestMapping({"/", "/index", "/home"})
	public String home(){
		return "user-space/home";
	}
	
	@RequestMapping({"/profile"})
	public String profile(){
		return "user-space/profile";
	}
	
	@RequestMapping("/blog/edit")
	public String blog_edit(){
		return "user-space/blog_edit";
		
	}
	
	@RequestMapping("/blog/save")
	public String blog_save(Article article,MultipartFile file,HttpServletRequest request){
		String upload = FileUploadUtil.upload(request, file);
		User user = (User)request.getSession().getAttribute(Constant.LOGIN_USER);
		article.setAuthor(user);
		article.setHits(0);
		article.setDeleted(false);
		article.setStatus(0);
		article.setPicture(upload);
		article.setCreated(new Date());
		article.setHot(true);
		article.setKeywords(article.getTitle()+","+WordSortUtil.wordSortDesc(article.getContent()));
		articleService.save(article);
		return "user-space/blog_edit";
		
	}
	@RequestMapping("/cang")
	public String cang(Model model,@RequestParam(required=false,defaultValue="1")Integer pageNum){
		Page<Object> page = PageHelper.startPage(pageNum,3);
		List<Article> cang = articleService.listCang();
		model.addAttribute("cang", cang);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pages", page.getPages());
		model.addAttribute("total", page.getTotal());
		return "user-space/collect";
	}
	@RequestMapping("/delcang")
	@ResponseBody
	public Integer delcang(Integer id,HttpServletRequest request){
		User u = (User) request.getSession().getAttribute(Constant.LOGIN_USER);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("aid", id);
		map.put("uid", u.getId());
		int num = articleService.delcang(map);
		return num;
	}
}
