/**
 * 
 */
package com.qinyuan.cms.web.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qinyuan.cms.core.Page;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.domain.Category;
import com.qinyuan.cms.domain.Channel;
import com.qinyuan.cms.domain.Slide;
import com.qinyuan.cms.domain.User;
import com.qinyuan.cms.service.ArticleService;
import com.qinyuan.cms.service.SlideService;
import com.qinyuan.cms.web.Constant;

/**
 * 说明:首页
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2018年1月10日 下午8:19:15
 */
@Controller
public class HomeController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private SlideService slideService;
	
	@Resource
	private RedisTemplate redisTemplate;
	
	/*@Resource
	private KafkaTemplate kafkaTemplate;*/
	
	@RequestMapping({"/", "/index", "/home"})
	public String home(
			@RequestParam(required = false) Integer channel, //频道
			@RequestParam(required = false) Integer category,//分类
			@RequestParam(defaultValue = "1") Integer page,//分类
			Model model){
		
		//------------------------------------
		Page _page = new Page(page, 30);
		List<Article> articles = null;
		
		//拼条件
		Article conditions = new Article();
		conditions.setDeleted(false);
		conditions.setStatus(1);

		//默认首页显示热门文章
		if(category == null && channel == null){
			conditions.setHot(true);
			
			//热门文章时显示幻灯片
			List<Slide> slides = slideService.getTops(5);
			model.addAttribute("slides", slides);
		}
		
		//如果频道或分类不为空，则显示分类或频道数据
		if(category != null){
			conditions.setCategory(new Category(category));
		}else if(channel != null){
			conditions.setChannel(new Channel(channel));
		}
		
		articles = articleService.gets(conditions, _page, null);
		model.addAttribute("articles", articles);
		

		//---------------右侧放10条最新文章---------------------
		Article lastArticlesConditions = new Article();
		lastArticlesConditions.setDeleted(false);
		lastArticlesConditions.setStatus(1);
		
		Page lastArticlesPage = new Page(1, 10);
		lastArticlesPage.setTotalCount(100);//设置了总记录数，可以节省统计查询，提高性能。
		
		List<Article> lastArticles = articleService.gets(lastArticlesConditions, lastArticlesPage, null);
		model.addAttribute("lastArticles", lastArticles);

		if(channel != null){
			model.addAttribute("channel", new Channel(channel));
		}
		model.addAttribute("category", category);
		
		return "home";
	}
	
	@SuppressWarnings("all")
	@RequestMapping("/article")
	public String article(Integer aid,Model model,HttpServletRequest request){
		
		/*User user = (User) request.getSession().getAttribute(Constant.LOGIN_USER);
		if(user == null){
			return "redirect:/login";
		}
		BoundValueOperations ops = redisTemplate.boundValueOps("user"+aid+user.getId());
		Object object = ops.get();
		if(object!=null){
			Article article = articleService.articleById(aid);
			List<Article> blogs = articleService.hotarticle();
			model.addAttribute("blog", article);
			model.addAttribute("hitBlogs", blogs);
			return "blog";
		}else{*/
			/*ops.set(""+aid);
			ops.expire(1, TimeUnit.MINUTES);
			kafkaTemplate.send("test","addhis",aid.toString());*/
			Article article = articleService.articleById(aid);
			List<Article> blogs = articleService.hotarticle();
			model.addAttribute("blog", article);
			model.addAttribute("hitBlogs", blogs);
			return "blog";
		/*}*/
		
		
	}
	
}
