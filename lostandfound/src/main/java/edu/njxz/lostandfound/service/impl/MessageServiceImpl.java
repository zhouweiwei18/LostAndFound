package edu.njxz.lostandfound.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.njxz.lostandfound.entity.Message;
import edu.njxz.lostandfound.entity.MessageExample;
import edu.njxz.lostandfound.entity.User;
import edu.njxz.lostandfound.mapper.MessageMapper;
import edu.njxz.lostandfound.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Override
	public List<Message> getMessageByUser(User user) {

		MessageExample example = new MessageExample();

		example.createCriteria().andMessageUseridEqualTo(user.getUserId());

		return messageMapper.selectByExample(example);
	}

	@Override
	public int addMessage(Message message) {

		return messageMapper.insert(message);

	}

	@Override
	public Message selectMessageById(Integer messageId) {

		return messageMapper.selectByPrimaryKey(messageId);

	}

	public List<Message> getAllMessages() {

		List<Message> list = messageMapper.selectByExample(new MessageExample());

		return list;
	}

	public int deleteMessage(Integer id) {

		return messageMapper.deleteByPrimaryKey(id);

	}

}
