package edu.njxz.lostandfound.service;

import java.util.List;

import edu.njxz.lostandfound.entity.Category;
import edu.njxz.lostandfound.entity.Message;

public interface MessageService {

	// 查询全部信息
	List<Message> getAllMessages();

	// 删除某条信息
	int deleteMessage(Integer id);

	// 查询某条信息
	Message selectMessageById(Integer messageId);

	// 添加一个Message信息
	int addMessage(Message message);

}
