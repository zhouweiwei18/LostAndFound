package edu.njxz.lostandfound.service;

import java.util.List;

import edu.njxz.lostandfound.entity.Message;

public interface MessageService {

	// 查询全部信息
	List<Message> getMessages();

}
