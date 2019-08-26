/**
 * 
 */
package com.qinyuan.cms.web.controllers.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.domain.Channel;
import com.qinyuan.cms.domain.User;
import com.qinyuan.cms.es.dao.ArticleEsDao;
import com.qinyuan.cms.service.ArticleService;
import com.qinyuan.cms.service.ChannelCategoryService;
import com.qinyuan.cms.utils.EsUtil;
import com.qinyuan.cms.utils.PageUtils;
import com.qinyuan.cms.web.Constant;
import com.qinyuan.cms.web.controllers.PassportController;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年3月29日 下午6:54:11
 */
@Controller
@RequestMapping("/admin")
public class AdminHomeController {

	public static Logger log = LoggerFactory.getLogger(PassportController.class);
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private RedisTemplate redisTemplate;
	
	@Resource
	private ArticleEsDao articleEsDao;
	
	@Resource
	private ChannelCategoryService channelCategoryService;
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@RequestMapping({"/", "/index"})
	public String home(){
		return "admin/home";
	}
	
	@RequestMapping("/hotarticle")
	public String hotarticle(Model model){
		List<Article> article = null;
		BoundListOperations ops = redisTemplate.boundListOps("hotarticle");
		List range = ops.range(0, -1);
		if(range!=null&&range.size()>0){
			article=range;
		}else{
			article = articleService.hotarticle();
			for (Object a : article) {
				ops.rightPush(a);
			}
			ops.expire(1, TimeUnit.MINUTES);
		}
		model.addAttribute("article", article);
		return "admin/hotarticle";
	}
	
	@RequestMapping("/seacharticle")
	public String seacharticle(Model model,@RequestParam(defaultValue="")String title,String currentpage){
		int pageSize = 10;
		int size = articleEsDao.findByTitleLike(title).size();
		PageUtils utils = new PageUtils(currentpage, size, pageSize);
		List<Article> list = articleEsDao.findByTitleLike(title,new PageRequest(utils.getCurrentPage()-1, pageSize));
		model.addAttribute("articles", list);
		model.addAttribute("title", title);
		model.addAttribute("page", utils.getPage());
		return "admin/seacharticle";
	}
	
	@RequestMapping("/hlseacharticle")
	public String hlseacharticle(Model model,@RequestParam(defaultValue="")String title,String currentpage){
		EsUtil.articles(model, title, currentpage, articleEsDao, elasticsearchTemplate);
		return "admin/hlseacharticle";
	}
	
	@RequestMapping("/articles")
	public String articles(Model model,@RequestParam(defaultValue="")String title,String currentpage){
		
		 int pageSize = 3;
		 int count = articleService.count(title);
		 PageUtils pageUtils = new PageUtils(currentpage, count, pageSize);
		List<Article> listarticle = articleService.articles(title,pageUtils);
		model.addAttribute("articles", listarticle);
		model.addAttribute("page", pageUtils.getPage());
		model.addAttribute("title", title);
		return "admin/articles";
	}
	
	@RequestMapping("/shenhe")
	@ResponseBody
	public Integer shenhe(Integer id){
		int num = articleService.shenhe(id);
		return num;
		
	}
	
	@RequestMapping("/toadd")
	public String toadd(){
		return "admin/addarticle";
	}
	
	@RequestMapping("/shoucang")
	@ResponseBody
	public Integer shoucang(Integer aid,HttpServletRequest request){
		int num = articleService.shoucang(aid,request);
		return num;
	}
	@RequestMapping("/delarticle")
	@ResponseBody
	public Integer delarticle(Integer aid){
		int num = articleService.delarticle(aid);
		return num;
	}
	
	@RequestMapping("/chaarticle")
	public String chaarticle(Integer aid,Model model){
		Article article = articleService.chaarticle(aid);
		model.addAttribute("articles", article);
		return "admin/articlelist";
	}
	
	@RequestMapping("/xiuarticle")
	public String xiugai(Integer aid,Model model){
		model.addAttribute("aid", aid);
		return "admin/article_edit";
	}
	@RequestMapping(value="/updatearticle", method = RequestMethod.POST , produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object uparticle(Integer aid,Model model){
		Article article = articleService.chaarticle(aid);
		List<Channel> channels = channelCategoryService.getChannels();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("a", article);
		map.put("c", channels);
		String string = JSON.toJSONString(map);
		Object json = JSON.toJSON(string);
		return json;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Integer update(Article article){
		article.setUpdated(new Date());
		int num = articleService.update(article);
		return num;
	}
	
	@RequestMapping("/wenzhang")
	public String wenzhang(){
		return "redirect:/admin/articles";
	}
}
