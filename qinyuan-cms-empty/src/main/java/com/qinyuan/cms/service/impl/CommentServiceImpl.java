package com.qinyuan.cms.service.impl;

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
	public void save(Comment comment) {
		commentMapper.save(comment);
	}
}
