/**
 * 
 */
package com.qinyuan.cms.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qinyuan.cms.core.Page;
import com.qinyuan.cms.dao.ArticleMapper;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.domain.Cang;
import com.qinyuan.cms.domain.User;
import com.qinyuan.cms.service.ArticleService;
import com.qinyuan.cms.utils.PageUtils;
import com.qinyuan.cms.web.Constant;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年4月21日 下午9:06:07
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Resource
	ArticleMapper articleMapper;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> gets(Article conditions, Page page, LinkedHashMap<String, Boolean> orders) {
		List<Article> articles = articleMapper.selects(conditions, orders, page);
		if(page != null && page.getPageCount() == 0){
			int totalCount = articleMapper.count(conditions);
			page.setTotalCount(totalCount);
		}
		return articles;
	}

	@Override
	public List<Article> hotarticle() {
		// TODO Auto-generated method stub
		return articleMapper.hotarticle();
	}

	@Override
	public void insert(Article article) {
		
		articleMapper.insert(article);
	}

	@Override
	public List<Article> articleList() {
		// TODO Auto-generated method stub
		return articleMapper.articleList();
	}

	@Override
	public int count(String title) {
		// TODO Auto-generated method stub
		return articleMapper.count2(title);
	}

	@Override
	public List<Article> articles(String title, PageUtils pageUtils) {
		// TODO Auto-generated method stub
		return articleMapper.articles(title,pageUtils);
	}

	@Override
	public int shenhe(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.shenhe(id);
	}

	@Override
	public void save(Article article) {
		// TODO Auto-generated method stub
		articleMapper.insert(article);
	}

	@Override
	public int shoucang(Integer aid, HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute(Constant.LOGIN_USER);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(u != null){
			map.put("aid",aid);
			map.put("uid",u.getId());
			Cang cang= articleMapper.canglist(map);
			if(cang == null){
				articleMapper.shoucang(map);
				return 2;
			}else{
				return 1;
			}
		}
		return 0;
	}

	@Override
	public List<Article> listCang() {
		// TODO Auto-generated method stub
		return articleMapper.listCang();
	}

	@Override
	public int delcang(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return articleMapper.delcang(map);
	}

	@Override
	public Article articleById(Integer aid) {
		// TODO Auto-generated method stub
		return articleMapper.articleById(aid);
	}

	@Override
	public int delarticle(Integer aid) {
		// TODO Auto-generated method stub
		return articleMapper.delarticle(aid);
	}

	@Override
	public Article chaarticle(Integer aid) {
		// TODO Auto-generated method stub
		return articleMapper.chaarticle(aid);
	}

	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.update(article);
	}

	@Override
	public void increaseHit(Integer aid) {
		articleMapper.increaseHit(aid);
		
	}
	
	
}
