package edu.njxz.lostandfound.service;

import java.util.List;

import edu.njxz.lostandfound.entity.Comment;

public interface CommentService {

	// 查询评论表所有信息
	List<Comment> getCommmentList();

	// 添加评论
	int insertComment(Comment comm);

	// 根据messageid查询到所有的评论
	List<Comment> selectAllComments(Integer messageId);
}
