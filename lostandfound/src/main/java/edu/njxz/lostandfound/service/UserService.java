package edu.njxz.lostandfound.service;

import java.util.List;

import edu.njxz.lostandfound.entity.User;

public interface UserService {

	// 用户登录
	User userLogin(String username, String password);

	// 根据id更新用户信息
	void updateUserById(User user);

	User updateUserById(String commentUserid);

	// 根据用户名查询用户
	List<User> selectUserByName(String username);

	//编辑用户
	int userEdit(User user);
	
	//根据id查询用户
	User getUserById(String id);
}
