package edu.njxz.lostandfound.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.njxz.lostandfound.entity.User;
import edu.njxz.lostandfound.service.UserService;
import edu.njxz.lostandfound.utils.FileNameUtils;
import edu.njxz.lostandfound.utils.FileUtils;

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
	 */
	@RequestMapping("/fileUpload")
	public Map<String, String> upload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map,
			HttpServletRequest request) {

		// 要上传的目标文件存放路径
		String localPath = path;

		// 上传成功或者失败的提示
		Map<String, String> mapFlag = new HashMap<String, String>();

		// 获取到当前用户的所有信息
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		if (user != null) {
			// 保存到对应用户的头像
			user.setUserPhoto(path + "/" + FileNameUtils.getFileName(file.getOriginalFilename()));
			userService.updateUserById(user);
			if (FileUtils.upload(file, localPath, file.getOriginalFilename())) {
				// 上传成功，给出页面提示
				mapFlag.put("flag", "success");
			} else {
				mapFlag.put("flag", "error");
			}
		} else {
			mapFlag.put("flag", "error");
		}

		return mapFlag;
	}

	@RequestMapping("/showPhotos")
	public User showPhotos(HttpServletRequest request) {

		// 获取当前用户信息
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		return user;
	}
}
