package edu.njxz.lostandfound.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.njxz.lostandfound.entity.Comment;
import edu.njxz.lostandfound.entity.CommentExample;
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
	public User getUserById(String id) {

		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User userLogin(String username, String password) {

		// 创建查询对象
		UserExample userExample = new UserExample();

		userExample.createCriteria().andUserNameEqualTo(username);

		List<User> list = userMapper.selectByExample(userExample);

		// 判断是否有该用户的姓名
		if (list.size() != 0) {

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

	@Override
	public User updateUserById(String commentUserid) {

		User user = userMapper.selectByPrimaryKey(commentUserid);

		return user;
	}

	@Override
	public List<User> selectUserByName(String username) {

		UserExample example = new UserExample();

		example.createCriteria().andUserNameEqualTo(username);

		return userMapper.selectByExample(example);
	}

	@Override
	public int userEdit(User user) {

		return userMapper.insert(user);
	}

}
