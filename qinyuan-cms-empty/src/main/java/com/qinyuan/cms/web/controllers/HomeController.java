/**
 * 
 */
package com.qinyuan.cms.web.controllers;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSONArray;
import com.qinyuan.cms.core.Page;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.domain.Category;
import com.qinyuan.cms.domain.Channel;
import com.qinyuan.cms.domain.Comment;
import com.qinyuan.cms.domain.Picture;
import com.qinyuan.cms.domain.Slide;
import com.qinyuan.cms.service.ArticleService;
import com.qinyuan.cms.service.SlideService;

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
	
	@RequestMapping({"/", "/index", "/home"})
	public String home(
			@RequestParam(required = false) Integer channel, //频道
			@RequestParam(required = false) Integer category,//分类
			@RequestParam(defaultValue = "1") Integer page,//分类
			Model model){
		
		//------------------------------------
		
		
		//拼条件
		Article conditions = new Article();
		conditions.setDeleted(false);
		conditions.setStatus(1);
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//默认首页显示热门文章
				if(category == null && channel == null){
					conditions.setHot(true);
					
					//热门文章时显示幻灯片
					List<Slide> slides = slideService.getTops(5);
					model.addAttribute("slides", slides);
				}
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//如果频道或分类不为空，则显示分类或频道数据
				if(category != null){
					conditions.setCategory(new Category(category));
				}else if(channel != null){
					conditions.setChannel(new Channel(channel));
				}
				Page _page = new Page(page, 30);
				List<Article> articles = null;
				articles = articleService.gets(conditions, _page, null);
				model.addAttribute("articles", articles);
				
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//---------------右侧放10条最新文章---------------------
				Article lastArticlesConditions = new Article();
				lastArticlesConditions.setDeleted(false);
				lastArticlesConditions.setStatus(1);
				
				Page lastArticlesPage = new Page(1, 10);
				lastArticlesPage.setTotalCount(100);//设置了总记录数，可以节省统计查询，提高性能。
				
				List<Article> lastArticles = articleService.gets(lastArticlesConditions, lastArticlesPage, null);
				model.addAttribute("lastArticles", lastArticles);
				
			}
		});

		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				if(channel != null){
					model.addAttribute("channel", new Channel(channel));
				}
				model.addAttribute("category", category);
				
			}
		});
		Thread t5 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//---------------右侧放10条最新文章---------------------
				Article picArticles = new Article();
				picArticles.setDeleted(false);
				picArticles.setStatus(1);
				
				Page lastArticlesPage = new Page(1, 5);
				lastArticlesPage.setTotalCount(50);//设置了总记录数，可以节省统计查询，提高性能。
				
				List<Article> picArticlesList = articleService.gets(picArticles, lastArticlesPage, null);
				model.addAttribute("picArticlesList", picArticlesList);
				
			}
		});
		try {
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			if(t1.isAlive()==false&&t2.isAlive()==false&&t3.isAlive()==false&&t4.isAlive()==false&&t5.isAlive()==false){
				break;
			}
		}
		return "home";
	}
	
	@RequestMapping("/article")
	public String article(Integer id,Model model,HttpServletRequest request){
		articleService.increaseHit(id);
		Article article = articleService.selectByPrimaryKey(id);
		List<Comment> list = articleService.selectById(article.getId());
		model.addAttribute("comments", list);
		if(article.getWts()==1){
			if(article.getContent()!=null&&article.getContent().length()>0){
				List<Picture> parseArray = JSONArray.parseArray(article.getContent(), Picture.class);
				model.addAttribute("pictures", parseArray);
			}
			model.addAttribute("blog", article);
			return "blog";
		}else{
			model.addAttribute("blog", article);
			return "blog";
		}
		
	}
	
	
}
