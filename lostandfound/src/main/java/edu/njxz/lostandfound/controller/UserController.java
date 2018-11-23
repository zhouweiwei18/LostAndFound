package edu.njxz.lostandfound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.njxz.lostandfound.entity.User;
import edu.njxz.lostandfound.service.UserService;

/**
 * 用户功能模块
 * 
 * @author 周伟伟
 * 
 * @version 2018/11/23
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用户的登录
	 */
	@RequestMapping("/userLogin")
	public String userLogin(String username, String password) {

		// 判断用户名是否为空
		if (username != null && password != null) {
			// 查询用户是否存在
			User user = userService.userLogin(username, password);
			// 判断user是否为空
			if (user == null) {
				return "false";
			} else {
				// 查看session中是否已经存在该用户
				// 获得session

				// 返回成功标识
				return "success";
			}
		}

		return "false";
	}

}
