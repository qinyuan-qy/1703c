/**
 * 
 */
package com.qinyuan.cms.dao;

import java.util.List;

import com.qinyuan.cms.domain.Comment;
import com.qinyuan.cms.domain.User;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2018年1月10日 下午1:37:59
 */
public interface UserMapper {

	public void insert(User user);
	
	public void deleteById(int id);

	public User selectById(int id);

	public User selectByUsername(String username);

	public int count(User user);

	public void updateById(User user);

	public List<Comment> commentsList();
	
}
