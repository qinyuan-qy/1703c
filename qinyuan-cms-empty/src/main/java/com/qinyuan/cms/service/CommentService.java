package com.qinyuan.cms.service;

import java.util.List;

import com.qinyuan.cms.domain.Comment;

public interface CommentService {

	int save(Comment comment);

	List<Comment> blogs(Integer id);

}
