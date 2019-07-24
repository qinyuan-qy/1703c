package com.qinyuan.cms.domain;

import java.util.Date;

public class Comment {

	private Integer id;
	private String content;
	private Article article;
	private Date displayTime;
	private User user;
	private String nickname;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Date getDisplayTime() {
		return displayTime;
	}
	public void setDisplayTime(Date displayTime) {
		this.displayTime = displayTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", article="
				+ article + ", displayTime=" + displayTime + ", user=" + user
				+ ", nickname=" + nickname + "]";
	}
	
	
}
