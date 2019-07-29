/**
 * 
 */
package com.qinyuan.cms.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.domain.Comment;
import com.qinyuan.cms.domain.Picture;
import com.qinyuan.cms.domain.User;
import com.qinyuan.cms.service.ArticleService;
import com.qinyuan.cms.service.UserService;
import com.qinyuan.cms.utils.FileUploadUtil;
import com.qinyuan.cms.utils.FileUtil;
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
			article.setWts(0);
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
	public String remove(Integer id){
		Article article = new Article();
		article.setId(id);
		article.setDeleted(true);
		 articleService.updateByKey(article);
		return "/my/blogs";
		
	}
	
	@RequestMapping("/user/save")
	public String usersave(User user,HttpServletRequest request){
		userService.updateById(user);
		User u = (User)request.getSession().getAttribute(Constant.LOGIN_USER);
		u.setGender(user.getGender());
		return "redirect:/my/userinfo";
	}
	
	@RequestMapping("/userinfo")
	public String userinfo(HttpServletRequest request,Model model){
		
		User user = (User)request.getSession().getAttribute(Constant.LOGIN_USER);
		User userresult = userService.selectById(user.getId());
		model.addAttribute("user", userresult);
		return "user-space/profile";
	}
	
	@RequestMapping("/comment")
	public String commentsList(Model model,HttpServletRequest request,@RequestParam(required=false,defaultValue="1")Integer page){
		PageHelper.startPage(page,5);
		User user = (User)request.getSession().getAttribute(Constant.LOGIN_USER);
		List<Comment> list= userService.commentsList(user.getId());
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(list,3);
		String page2 = PageHelpUtil.page("/my/comment", pageInfo, null);
		model.addAttribute("comments", list);
		model.addAttribute("pageList", page2);
		return "user-space/comments";
	}

	@RequestMapping("/profile/avatar")
	public String avatar(HttpServletRequest request,Model model){
		
		User user = (User)request.getSession().getAttribute(Constant.LOGIN_USER);
		User user2 = userService.selectById(user.getId());
		model.addAttribute("user", user2);
		return "user-space/avatar";
	}
	@RequestMapping("/updatephtno")
	public String updatephtno(MultipartFile file,HttpServletRequest request){
		User user1 = (User)request.getSession().getAttribute(Constant.LOGIN_USER);
		String upload = FileUploadUtil.upload(request, file);
		User user = new User();
		user.setImage(upload);
		user.setId(user1.getId());
		userService.updatephtno(user);
		user1.setImage(upload);
		return "redirect:/my/profile/avatar";
		
	}
	
	@RequestMapping("/picture")
	public String picture(){
		return "user-space/picture";
	}
	
	@RequestMapping("/blog/picture")
	public String pictures(Article article,MultipartFile phtno,HttpServletRequest request,MultipartFile[] file,String[] describe){
		String upload1 = FileUploadUtil.upload(request, phtno);
		article.setPicture(upload1);
		List<Picture> list = new ArrayList<Picture>();
		for (int i = 0; i < describe.length; i++) {
			Picture picture = new Picture();
			String upload = FileUploadUtil.upload(request, file[i]);
			if(!upload.equals("")){
				picture.setPicture(upload);
			}
			if(!describe.equals("")){
				picture.setDescribe(describe[i]);
			}
			list.add(picture);
		}
		if(list!=null){
			article.setContent(JSON.toJSONString(list));
		}
		if(article.getId() != null){
			articleService.updateByKey(article);
		}else{
			article.setWts(1);
			article.setHits(0);
			article.setHot(true);
			article.setStatus(1);
			article.setDeleted(false);
			article.setCreated(new Date());
			
			User user = (User)request.getSession().getAttribute(Constant.LOGIN_USER);
			article.setAuthor(user);
			
			articleService.save(article);
		}
		return "redirect:/my/picture";
		
	}
}
