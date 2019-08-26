package com.qinyuan.cms.test;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import com.qinyuan.cms.BaseTestCase;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.domain.User;
import com.qinyuan.cms.es.dao.ArticleEsDao;
import com.qinyuan.cms.service.ArticleService;
import com.qinyuan.cms.web.Constant;

public class EsTest extends BaseTestCase{
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Resource
	private ArticleEsDao articleEsDao;
	
	@Resource
	private ArticleService articleService;
	
	@Test
	public void testes(){
		
		Iterable<Article> findAll = articleEsDao.findAll();
		for (Article article : findAll) {
			System.out.println(article.getTitle());
		}
	}
	
	@Test
	public void testaaa(){
		
		List<Article> hotarticle = articleService.articleList();
		articleEsDao.save(hotarticle);
	}
	
	@Test
	public void testdel(){
		articleEsDao.deleteAll();
	}
	@Test
	public void testaa(){
		String title = "";
		List<Article> list = articleEsDao.findByTitleLike(title);
		for (Article article : list) {
			System.out.println(article);
		}
	}
	@Test
	public void ss(HttpServletRequest request){
		User u = (User) request.getSession().getAttribute(Constant.LOGIN_USER);
		System.out.println(u.getId());
	}
}
