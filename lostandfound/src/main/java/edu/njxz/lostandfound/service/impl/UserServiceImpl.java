package edu.njxz.lostandfound.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.njxz.lostandfound.entity.User;
import edu.njxz.lostandfound.entity.UserExample;
import edu.njxz.lostandfound.mapper.UserMapper;
import edu.njxz.lostandfound.service.UserService;

/**
 * 用户登录的逻辑处理
 * 
 * @author 伟伟
 * 
 * @version 2018/11/23
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User userLogin(String username, String password) {

		// 创建查询对象
		UserExample userExample = new UserExample();

		userExample.createCriteria().andUserNameEqualTo(username);

		List<User> list = userMapper.selectByExample(userExample);

		// 判断是否有该用户的姓名
		if (list.size() != 0) {

			// 同时查询对应的密码是否正确
			/*
			 * userExample.createCriteria().andUserPasswordEqualTo(password); List<User>
			 * list2 = userMapper.selectByExample(userExample);
			 * 
			 * if (list2.size() != 0) { return list2.get(0); }
			 */

			// 遍历用户集合
			for (User user : list) {
				if (user.getUserPassword().equals(password)) {
					return user;
				}
			}

			// 说明密码错误
			return null;
		}

		// 说明用户名不存在
		return null;
	}

	@Override
	public void updateUserById(User user) {

		userMapper.updateByPrimaryKey(user);

	}

}
