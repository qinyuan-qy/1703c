package com.qinyuan.cms.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qinyuan.cms.core.Page;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.utils.PageUtils;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年3月28日 下午4:48:47
 */
public interface ArticleService {

	/**
	 * 功能说明：<br>
	 * @param conditions
	 * @param page
	 * @param orders  排序，默认按创建时间倒排序
	 * @return
	 * List<Article>
	 */
	public abstract List<Article> gets(Article conditions, Page page, LinkedHashMap<String, Boolean> orders);

	public abstract List<Article> hotarticle();

	public abstract void insert(Article article);

	public abstract List<Article> articleList();

	public abstract int count(String title);

	public abstract List<Article> articles(String title, PageUtils pageUtils);

	public abstract int shenhe(Integer id);

	public abstract void save(Article article);

	public abstract int shoucang(Integer aid, HttpServletRequest request);

	public abstract List<Article> listCang();

	public abstract int delcang(HashMap<String, Object> map);
	

}