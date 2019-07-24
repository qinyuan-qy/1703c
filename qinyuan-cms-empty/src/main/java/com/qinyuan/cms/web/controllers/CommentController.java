package com.qinyuan.cms.web.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qinyuan.cms.domain.Comment;
import com.qinyuan.cms.domain.User;
import com.qinyuan.cms.service.CommentService;
import com.qinyuan.cms.utils.Result;
import com.qinyuan.cms.web.Constant;

/**
 * 评论Controller
 * @author ASUS
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@RequestMapping("save")
	@ResponseBody
	public Integer save(Comment comment,HttpSession seesion){
		comment.setDisplayTime(new Date());
		User user = (User) seesion.getAttribute(Constant.LOGIN_USER);
		comment.setUser(user);
		int num = commentService.save(comment);
		return num;
	}
}
