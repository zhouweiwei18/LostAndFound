package edu.njxz.lostandfound.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.njxz.lostandfound.entity.Comment;
import edu.njxz.lostandfound.entity.User;
import edu.njxz.lostandfound.service.CommentService;
import edu.njxz.lostandfound.service.UserService;
import edu.njxz.lostandfound.utils.DateTimeUtil;
import edu.njxz.lostandfound.vo.ComAndUser;

/**
 * 评论模块
 * 
 * @author 周伟伟
 * @version 2018/11/27
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@RequestMapping("/list") // List<Map<String, Object>>
	public List<ComAndUser> commentList() {

		// 创建一个List<ComAndUser>用于封装前端需要的数据
		List<ComAndUser> list = new ArrayList<ComAndUser>();

		// 从数据库查询comment表的所有信息
		List<Comment> commentList = commentService.getCommmentList();

		if (commentList != null) {
			// 遍历所有评论，进行数据二次封装
			for (Comment comment : commentList) {
				ComAndUser cau = new ComAndUser();
				cau.setId(comment.getCommentId());
				// 根据评论人的id查询该评论人
				User commentuser = userService.updateUserById(comment.getCommentUserid());
				cau.setAuthor(commentuser.getUserName());
				cau.setAvatar(commentuser.getUserPhoto());

				cau.setContent(comment.getCommentContent());

				// 将时间格式转换为字符串类型
				String dateStr = DateTimeUtil.dateToStr(comment.getCommentDate());
				cau.setDate(dateStr);
				cau.setLikedCount(comment.getCommentSubmitCount());
				cau.setStatus(true);

				// 将ComAndUser添加到集合中
				list.add(cau);
			}
		}

		return list;

	}

}
