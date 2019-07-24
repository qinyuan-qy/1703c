/**
 * 
 */
package com.qinyuan.cms.web.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.domain.Comment;
import com.qinyuan.cms.domain.User;
import com.qinyuan.cms.service.ArticleService;
import com.qinyuan.cms.service.UserService;
import com.qinyuan.cms.utils.FileUploadUtil;
import com.qinyuan.cms.utils.PageHelpUtil;
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

	@Autowired
	ArticleService articleService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping({"/", "/index", "/home"})
	public String home(){
		return "user-space/home";
	}
	
	@RequestMapping({"/profile"})
	public String profile(){
		return "user-space/profile";
	}
	
	@RequestMapping("/blogs")
	public String blogs(Model model,HttpServletRequest request,HttpSession session, @RequestParam(required=false,defaultValue="1")Integer page){
		Article article = new Article();
		PageHelper.startPage(page,3);
		User user = (User)session.getAttribute(Constant.LOGIN_USER);
		article.setAuthor(user);
		List<Article> articlea = articleService.queryAll(article);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articlea,3);
		String page2 = PageHelpUtil.page("/my/blogs", pageInfo, null);
		model.addAttribute("blogs", articlea);
		model.addAttribute("pageList", page2);
		return "user-space/blog_list";
	}
	
	@RequestMapping("/blog/edit")
	public String edit(Integer id,Model model){
		
		Article article = articleService.selectByPrimaryKey(id);
		model.addAttribute("blog", article);
		
		return "user-space/blog_edit";
		
	}
	
	@RequestMapping("/blog/save")
	public String save(Article article,MultipartFile file,HttpServletRequest request){
		String upload = FileUploadUtil.upload(request, file);
		if(!upload.equals("")){
			article.setPicture(upload);
		}
		if(article.getId() != null){
			articleService.updateByKey(article);
		}else{
			article.setHits(0);
			article.setHot(true);
			article.setStatus(1);
			article.setDeleted(false);
			article.setCreated(new Date());
			
			User user = (User)request.getSession().getAttribute(Constant.LOGIN_USER);
			article.setAuthor(user);
			
			articleService.save(article);
		}
		
		return "redirect:/my/blogs";
		
	}
	@RequestMapping("/blog/remove")
	@ResponseBody
	public Integer remove(Integer id){
		int num = articleService.deleteByPrimaryKey(id);
		return num;
		
	}
	
	@RequestMapping("/user/save")
	public String usersave(User user){
		userService.updateById(user);
		return "redirect:/my/userinfo";
	}
	
	@RequestMapping("/userinfo")
	public String userinfo(HttpServletRequest request,Model model){
		
		User user = (User)request.getSession().getAttribute(Constant.LOGIN_USER);
		User userresult = userService.selectById(user.getId());
		model.addAttribute("user", userresult);
		return "user-space/profile";
	}
	
	@RequestMapping("/comments")
	public String commentsList(Model model){
		System.out.println(1);
		List<Comment> list= userService.commentsList();
		model.addAttribute("comments", list);
		return "user-space/comments";
	}
}
