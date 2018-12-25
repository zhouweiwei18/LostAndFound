package edu.njxz.lostandfound.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.njxz.lostandfound.entity.User;
import edu.njxz.lostandfound.service.UserService;
import edu.njxz.lostandfound.utils.FileNameUtils;
import edu.njxz.lostandfound.utils.FileUtils;
import edu.njxz.lostandfound.utils.UUIDUtils;

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

	@Value("${web.upload-path}")
	private String path;

	/**
	 * 用户注册功能
	 */
	@RequestMapping("/userRegister")
	public Map<String, Object> userRegister(User user) {

		Map<String, Object> map = new HashMap<String, Object>();

		// 首先查询用户名是否已经存在
		List<User> users = userService.selectUserByName(user.getUserName());

		if (users != null && users.size() > 0) {
			User u = users.get(0);
			if (u != null) {
				// 说明有重复用户名
				map.put("flag", "username has exist!");
				map.put("state", false);
				return map;
			}
		}

		// 注册代码

		// 手动给用户添加一个uuid
		String uuid = UUIDUtils.getUUID();
		user.setUserId(uuid);
		int code = userService.userEdit(user);
		if (code == 1) {
			map.put("state", true);
		}

		return map;
	}

	/**
	 * 用户的登录
	 */
	@RequestMapping("/userLogin")
	public Boolean userLogin(String username, String password, HttpServletRequest request) {

		// 判断用户名是否为空
		if ((username != null && password != null) && (!username.equals("") && !password.equals(""))) {
			// 查询用户是否存在
			User user = userService.userLogin(username, password);
			// 判断user是否为空
			if (user == null) {
				return false;
			} else {
				// 查看session中是否已经存在该用户
				// 获得session
				HttpSession session = request.getSession();
				User userSession = (User) session.getAttribute("user");
				// 若session中没有用户信息，则放入该用户对象
				if (userSession == null) {
					session.setAttribute("user", user);
				}

				// 返回成功标识
				return true;
			}
		}

		return false;
	}

	/**
	 * 存储用户的头像
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/fileUpload")
	public Map<String, Object> upload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map,
			HttpServletRequest request) throws Exception {

		// 要上传的目标文件存放路径
		String localPath = path;

		// 上传成功或者失败的提示
		Map<String, Object> mapFlag = new HashMap<String, Object>();

		// 获取到当前用户的所有信息
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		if (user != null) {
			String newPath = FileUtils.upload(file, localPath, file.getOriginalFilename());
			// 保存到对应用户的头像
			user.setUserPhoto(newPath);
			userService.updateUserById(user);
			// 上传成功，给出页面提示
			mapFlag.put("state", true);

		} else {
			mapFlag.put("state", false);
			mapFlag.put("flag", "login in first");
		}

		return mapFlag;
	}

	@RequestMapping("/showUserInfo")
	public User showPhotos(HttpServletRequest request) {

		// 获取当前用户信息
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		return user;
	}
}
