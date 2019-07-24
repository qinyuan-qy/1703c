package com.qinyuan.cms.dao;

import java.util.List;

import com.qinyuan.cms.domain.Comment;

public interface CommentMapper {

	int save(Comment comment);

	List<Comment> blogs(Integer id);

}
