package edu.njxz.lostandfound.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.njxz.lostandfound.entity.Comment;
import edu.njxz.lostandfound.entity.CommentExample;
import edu.njxz.lostandfound.entity.CommentExample.Criteria;
import edu.njxz.lostandfound.mapper.CommentMapper;
import edu.njxz.lostandfound.mapper.UserMapper;
import edu.njxz.lostandfound.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<Comment> selectAllComments(Integer messageId) {

		CommentExample commExample = new CommentExample();

		commExample.createCriteria().andCommentMessageidEqualTo(messageId);

		List<Comment> list = commentMapper.selectByExample(commExample);

		return list;
	}

	@Override
	public List<Comment> getCommmentList() {

		List<Comment> list = commentMapper.selectByExample(new CommentExample());

		return list;
	}

	/**
	 * 添加评论
	 */
	@Override
	public int insertComment(Comment comm) {

		return commentMapper.insert(comm);

	}

}
