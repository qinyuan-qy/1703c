/**
 * 
 */
package com.qinyuan.cms.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qinyuan.cms.core.Page;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.domain.Cang;
import com.qinyuan.cms.utils.PageUtils;


/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年3月16日 下午9:18:02
 */
public interface ArticleMapper {

	/**
	 * 功能说明：保存文章<br>
	 * @param article
	 * void
	 */
	public void save(Article article);
	

	/**
	 * 功能说明：递增访问量<br>
	 * @param id
	 * void
	 */
	public void increaseHit(int id);
	
	
	/**
	 * 功能说明：查询文章<br>
	 * @return
	 * List<Article>
	 */
	public List<Article> selects(@Param("article") Article article, @Param("order") LinkedHashMap<String, Boolean> orders, @Param("page") Page page);
	
	
	/**
	 * 功能说明：统计<br>
	 * @param article
	 * @return
	 * int
	 */
	public int count(@Param("article") Article article);


	public List<Article> hotarticle();
	
	public List<Article> articleList();

	public void insert(Article article);


	public int count2(String title);


	public List<Article> articles(@Param("title")String title, @Param("pageUtils")PageUtils pageUtils);


	public int shenhe(Integer id);


	public int shoucang(HashMap<String, Object> map);


	public Cang canglist(HashMap<String, Object> map);


	public List<Article> listCang();


	public int delcang(HashMap<String, Object> map);
	
	
}
