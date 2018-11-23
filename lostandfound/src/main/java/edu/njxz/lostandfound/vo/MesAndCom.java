package edu.njxz.lostandfound.vo;

import java.util.List;

import edu.njxz.lostandfound.entity.Comment;
import edu.njxz.lostandfound.entity.Message;

/**
 * 将新闻与该新闻的评论链表绑定成一个Vo对象
 * @author Soapfulness
 *
 */
public class MesAndCom {
	
	private Message mes;
	private List<Comment> com;
	
	public Message getMes() {
		return mes;
	}
	public void setMes(Message mes) {
		this.mes = mes;
	}
	public List<Comment> getCom() {
		return com;
	}
	public void setCom(List<Comment> com) {
		this.com = com;
	}
	public MesAndCom(Message mes, List<Comment> com) {
		super();
		this.mes = mes;
		this.com = com;
	}
	public MesAndCom() {
		super();
	}

}
