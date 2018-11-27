package edu.njxz.lostandfound.vo;

public class ComAndUser {

	// id: //id (int)
	// author: //评论人 (String)
	// avatar: //评论人头像 (String)
	// content: //内容 (String)
	// date： //时间 (String)
	// likedCount://点赞数量 (int)
	// status：true/false //当前用户是否点赞 (boolean)

	// 封装数据
	private Integer id;

	// 评论人
	private String author;

	// 评论人头像
	private String avatar;

	// 内容
	private String content;

	// 时间
	private String date;

	// 点赞数量
	private Integer likedCount;

	// 当前用户是否点赞
	private Boolean status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getLikedCount() {
		return likedCount;
	}

	public void setLikedCount(Integer likedCount) {
		this.likedCount = likedCount;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
