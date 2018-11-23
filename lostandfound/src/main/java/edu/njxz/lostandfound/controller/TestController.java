package edu.njxz.lostandfound.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.njxz.lostandfound.entity.Comment;
import edu.njxz.lostandfound.entity.Message;
import edu.njxz.lostandfound.entity.User;
import edu.njxz.lostandfound.entity.UserExample;
import edu.njxz.lostandfound.mapper.UserMapper;
import edu.njxz.lostandfound.service.TestService;
import edu.njxz.lostandfound.vo.MesAndCom;

@Controller
public class TestController {

	@Autowired
	private TestService tess;

	@Autowired
	private UserMapper userMapper;

	/**
	 * 测试方法 备注：@RestController 等价于 @Controller + @ResponseBody
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/test")
	@ResponseBody
	public List<Message> test(Model model) {

		List<Message> messages = tess.showAllMessage();
		/*
		 * List<List<Comment>> comments = new ArrayList<List<Comment>>(); for (int i =
		 * 0; i < messages.size(); i++) {
		 * comments.add(tess.showAllCommentByMessageId(messages.get(i).getMessageId()));
		 * } model.addAttribute("messages", messages); model.addAttribute("comments",
		 * comments);
		 */
		// 返回json数据
		return messages;
	}

	/**
	 * 返回MesAndCom链表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/test2")
	public String test2(Model model) {
		List<MesAndCom> mac = new ArrayList<MesAndCom>();
		List<Message> messages = tess.showAllMessage();
		List<List<Comment>> comments = new ArrayList<List<Comment>>();
		for (int i = 0; i < messages.size(); i++) {
			comments.add(tess.showAllCommentByMessageId(messages.get(i).getMessageId()));
		}
		for (int i = 0; i < messages.size(); i++) {
			MesAndCom m = new MesAndCom(messages.get(i), comments.get(i));
			mac.add(m);
		}
		model.addAttribute("mac", mac);
		return "test";
	}

	@RequestMapping("/test3")
	@ResponseBody
	public List<User> test3(String username) {

		// 获取用户明细表
		List<User> list = userMapper.selectByExample(new UserExample());

		return list;
	}

}
