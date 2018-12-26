package edu.njxz.lostandfound.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sun.org.apache.bcel.internal.generic.IFNE;

import edu.njxz.lostandfound.entity.Category;
import edu.njxz.lostandfound.entity.Comment;
import edu.njxz.lostandfound.entity.Message;
import edu.njxz.lostandfound.entity.User;
import edu.njxz.lostandfound.service.CategoryService;
import edu.njxz.lostandfound.service.CommentService;
import edu.njxz.lostandfound.service.MessageService;
import edu.njxz.lostandfound.service.UserService;
import edu.njxz.lostandfound.utils.DateTimeUtil;
import edu.njxz.lostandfound.utils.FileUtils;
import edu.njxz.lostandfound.vo.CommUser;
import edu.njxz.lostandfound.vo.MUCVo;

@RestController
@RequestMapping("/message")
@CrossOrigin(allowCredentials = "true")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private CategoryService cateService;

	@Autowired
	private UserService userService;

	@Autowired
	private CommentService commService;

	@Value("${web.upload-path}")
	private String path;

	/**
	 * 获取当前用户发布的信息
	 */
	@RequestMapping("/getMessageByUser")
	public Map<String, Object> getMessageByUserId(String id, HttpServletRequest req) {

		Map<String, Object> map = new HashMap<String, Object>();

		// 获取session
		User user = (User) req.getSession().getAttribute("user");

		if (user == null) {
			// 根据id进行查询
			User user2 = userService.getUserById(id);
			if (user2 == null) {
				// 说明不存在该用户
				map.put("state", false);
				map.put("info", "no user");
				return map;
			}
			// 根据用户该id查询所有的message
			List<Message> list = messageService.getMessageByUser(user2);

			if (list != null) {
				// 放入VO的集合
				List<MUCVo> tempList = new ArrayList<MUCVo>();

				// 对list进行重新封装
				// 遍历list
				for (Message message : list) {

					// 封装MUCVo
					MUCVo vo = new MUCVo();

					vo.setMessageid(message.getMessageId());
					vo.setMesdesc(message.getMessageDescription());
					vo.setMesphoto(message.getMessagePhoto());

					// 对剩余的数据进行改写封装
					// username 根据id查询用户名
					User u = userService.getUserById(message.getMessageUserid());
					if (u != null) {
						vo.setUsername(u.getUserName());
					}
					// categoryname
					Category category = cateService.getCateById(message.getMessageCategotyid());
					if (category != null) {
						vo.setCategoryname(category.getCategoryName());
					}
					// date 转为字符串格式
					Date date = message.getMessageDate();
					String strDate = DateTimeUtil.dateToStr(date);
					vo.setDate(strDate);

					// 下面封装评论
					// 根据message的id查询出来
					List<Comment> comList = commService.selectAllComments(message.getMessageId());
					// vo.setCommlist(comList);

					if (comList != null) {
						List<CommUser> culist = new ArrayList<CommUser>();
						// 二次封装评论
						for (Comment comment : comList) {
							CommUser cu = new CommUser();
							// 根据id查询用户
							User comu = userService.getUserById(comment.getCommentUserid());
							if (comu != null) {
								cu.setCommuser(comu.getUserName());
							}
							cu.setComment(comment.getCommentContent());
							culist.add(cu);
						}
						vo.setCommlist(culist);
					}

					// 将vo添加到集合
					tempList.add(vo);
				}

				map.put("message", tempList);
				map.put("state", true);

				return map;
			} else {
				map.put("state", false);
				map.put("message", "no message");
				return map;
			}
		} else {
			// 根据用户该id查询所有的message
			List<Message> list = messageService.getMessageByUser(user);

			if (list != null) {
				// 放入VO的集合
				List<MUCVo> tempList = new ArrayList<MUCVo>();

				// 对list进行重新封装
				// 遍历list
				for (Message message : list) {

					// 封装MUCVo
					MUCVo vo = new MUCVo();

					vo.setMessageid(message.getMessageId());
					vo.setMesdesc(message.getMessageDescription());
					vo.setMesphoto(message.getMessagePhoto());

					// 对剩余的数据进行改写封装
					// username 根据id查询用户名
					User u = userService.getUserById(message.getMessageUserid());
					if (u != null) {
						vo.setUsername(u.getUserName());
					}
					// categoryname
					Category category = cateService.getCateById(message.getMessageCategotyid());
					if (category != null) {
						vo.setCategoryname(category.getCategoryName());
					}
					// date 转为字符串格式
					Date date = message.getMessageDate();
					String strDate = DateTimeUtil.dateToStr(date);
					vo.setDate(strDate);

					// 下面封装评论
					// 根据message的id查询出来
					List<Comment> comList = commService.selectAllComments(message.getMessageId());
					// vo.setCommlist(comList);

					if (comList != null) {
						List<CommUser> culist = new ArrayList<CommUser>();
						// 二次封装评论
						for (Comment comment : comList) {
							CommUser cu = new CommUser();
							// 根据id查询用户
							User comu = userService.getUserById(comment.getCommentUserid());
							if (comu != null) {
								cu.setCommuser(comu.getUserName());
							}
							cu.setComment(comment.getCommentContent());
							culist.add(cu);
						}
						vo.setCommlist(culist);
					}

					// 将vo添加到集合
					tempList.add(vo);
				}

				map.put("message", tempList);
				map.put("state", true);
				return map;
			} else {
				map.put("state", false);
				map.put("message", "no message");
				return map;
			}
		}
	}

	/**
	 * 添加招领信息
	 */
	@RequestMapping("/messageAdd")
	public Map<String, Object> messageAdd(Message message, @RequestParam("fileName") MultipartFile file,
			Map<String, Object> map, HttpServletRequest request) {

		// 目标：封装一个Message并存入数据库

		// 准备工作
		// 上传成功或者失败的提示
		Map<String, Object> mapFlag = new HashMap<String, Object>();

		// 要上传的目标文件存放路径
		String localPath = path;

		// 获取到当前用户的所有信息
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		if (user == null) {
			// 说明用户没有登录，报错
			mapFlag.put("state", false);
			mapFlag.put("flag", "login in first");

			return mapFlag;
		}
		// 下面正式对Message对象进行封装
		// messageid数据库自动递增
		// 内容描述
		// 发布者的id
		message.setMessageUserid(user.getUserId());
		// 发布日期
		message.setMessageDate(new Date());
		// 最后封装图片的存储路径

		String newPath = FileUtils.upload(file, localPath, file.getOriginalFilename());
		// 保存到对应用户的头像
		message.setMessagePhoto(newPath);

		// 执行添加操作
		messageService.addMessage(message);

		// 上传成功，给出页面提示
		mapFlag.put("state", true);
		return mapFlag;

	}

	/**
	 * 查询所有类别
	 * 
	 * @return
	 */
	@RequestMapping("/getCategories")
	public Map<String, Object> getCategories() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<Category> list = cateService.getAllCategories();
		if (list != null) {
			map.put("category", list);

		}
		return map;
	}

	/**
	 * 信息删除
	 * 
	 * @return
	 */
	@RequestMapping("/deleteMessage")
	public Integer messageDelete(Integer messageId, HttpServletRequest req) {

		// 判断是否是当前用户在操作
		// 根据id查询到当前信息所对应的用户id
		Message message = messageService.selectMessageById(messageId);
		if (message != null) {
			String userid = message.getMessageUserid();
			// 获取到当前用户
			User user = (User) req.getSession().getAttribute("user");
			// 判断是否是当前用户在删除
			if (userid.equals(user.getUserId())) {
				// 进行删除
				int delState = messageService.deleteMessage(messageId);

				return delState;// 返回0或1

			}
			// 非法用户
			return 0;
		}

		// 说明不存在该条信息,返回0
		return 0;
	}

	@RequestMapping("/getMessages")
	public Map<String, Object> getMessages() {

		Map<String, Object> map = new HashMap<String, Object>();

		List<Message> list = messageService.getAllMessages();

		if (list != null) {
			// 放入VO的集合
			List<MUCVo> tempList = new ArrayList<MUCVo>();

			// 对list进行重新封装
			// 遍历list
			for (Message message : list) {

				// 封装MUCVo
				MUCVo vo = new MUCVo();

				vo.setMessageid(message.getMessageId());
				vo.setMesdesc(message.getMessageDescription());
				vo.setMesphoto(message.getMessagePhoto());

				// 对剩余的数据进行改写封装
				// username 根据id查询用户名
				User u = userService.getUserById(message.getMessageUserid());
				if (u != null) {
					vo.setUsername(u.getUserName());
				}
				// categoryname
				Category category = cateService.getCateById(message.getMessageCategotyid());
				if (category != null) {
					vo.setCategoryname(category.getCategoryName());
				}
				// date 转为字符串格式
				Date date = message.getMessageDate();
				String strDate = DateTimeUtil.dateToStr(date);
				vo.setDate(strDate);

				// 下面封装评论
				// 根据message的id查询出来
				List<Comment> comList = commService.selectAllComments(message.getMessageId());
				// vo.setCommlist(comList);

				if (comList != null) {
					List<CommUser> culist = new ArrayList<CommUser>();
					// 二次封装评论
					for (Comment comment : comList) {
						CommUser cu = new CommUser();
						// 根据id查询用户
						User comu = userService.getUserById(comment.getCommentUserid());
						if (comu != null) {
							cu.setCommuser(comu.getUserName());
						}
						cu.setComment(comment.getCommentContent());
						culist.add(cu);
					}
					vo.setCommlist(culist);
				}

				// 将vo添加到集合
				tempList.add(vo);
			}

			map.put("message", tempList);
			map.put("state", true);

			return map;
		}

		// 返回值为空
		map.put("state", false);

		return map;
	}

}
