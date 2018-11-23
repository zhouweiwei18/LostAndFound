package edu.njxz.lostandfound.service;

import edu.njxz.lostandfound.entity.User;

public interface UserService {

	// 用户登录
	User userLogin(String username, String password);

}
