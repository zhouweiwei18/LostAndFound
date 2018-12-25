package edu.njxz.lostandfound.vo;

import java.util.List;

import edu.njxz.lostandfound.entity.Comment;

public class MUCVo {

	private Integer messageid;

	// 信息描述
	private String mesdesc;

	private String username;

	// 所属类别名称
	private String categoryname;

	private String date;

	// 图片地址
	private String mesphoto;

	// 评论
	private List<CommUser> commlist;

	public List<CommUser> getCommlist() {
		return commlist;
	}

	public void setCommlist(List<CommUser> commlist) {
		this.commlist = commlist;
	}

	public Integer getMessageid() {
		return messageid;
	}

	public void setMessageid(Integer messageid) {
		this.messageid = messageid;
	}

	public String getMesdesc() {
		return mesdesc;
	}

	public void setMesdesc(String mesdesc) {
		this.mesdesc = mesdesc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMesphoto() {
		return mesphoto;
	}

	public void setMesphoto(String mesphoto) {
		this.mesphoto = mesphoto;
	}

}
