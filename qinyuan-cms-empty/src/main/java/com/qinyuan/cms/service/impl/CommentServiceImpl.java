package com.qinyuan.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qinyuan.cms.dao.CommentMapper;
import com.qinyuan.cms.domain.Comment;
import com.qinyuan.cms.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;

	@Override
	public int save(Comment comment) {
		return commentMapper.save(comment);
	}

	@Override
	public List<Comment> blogs(Integer id) {
		// TODO Auto-generated method stub
		return commentMapper.blogs(id);
	}
}
