package edu.njxz.lostandfound.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@RequestMapping("/addComment")
	public Map<String, Object> commentAdd(Integer messageid, String comment, HttpServletRequest req) {

		//**********************************************
		// 首先对于该信息模块的id进行查询，校验是否存在
		// 待续。。。。。
		//**********************************************

		// 封装一个comment用于插入评论
		Comment comm = new Comment();
		// 封装map,给出反馈信息
		Map<String, Object> map = new HashMap<String, Object>();

		// 首先获取当前用户的信息,没有登录的是不允许评论的
		User user = (User) req.getSession().getAttribute("user");

		if (user != null) {
			// 内容
			comm.setCommentContent(comment);
			// 封装时间
			comm.setCommentDate(new Date());
			// 封装信息模块的id
			comm.setCommentMessageid(messageid);
			// 封装用户的id
			comm.setCommentUserid(user.getUserId());

			// 执行插入
			int stateCode = commentService.insertComment(comm);

			if (stateCode > 0) {
				// 添加成功
				map.put("state", true);
			} else {
				map.put("state", false);
				map.put("flag", "add fail");
			}
		}

		map.put("state", false);
		// 用户未登录，comment对象封装不了
		map.put("flag", "login in first");

		return map;
	}

	/**
	 * 评论的查询
	 * 
	 * @return
	 */
	@RequestMapping("/list")
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
