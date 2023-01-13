package mytalk.study.biz.chatting;

import java.util.Date;

import mytalk.study.biz.user.userVO;



public class chatVO {
	private String chat;
	private userVO user;
	private String date;
	private String type;
	
	
	public String getChat() {
		return chat;
	}
	public chatVO setChat(String chat) {
		this.chat = chat;
		return this;
	}
	public userVO getUser() {
		return user;
	}
	public chatVO setUser(userVO user) {
		this.user = user;
		return this;
	}
	public String getDate() {
		return date;
	}
	public chatVO setDate(String date) {
		this.date = date;
		return this;
	}
	public String getType() {
		return type;
	}
	public chatVO setType(String type) {
		this.type = type;
		return this;
	}
	
	@Override
	public String toString() {
		return "chatVO [chat=" + chat + ", user=" + user + ", date=" + date + ", type=" + type + "]";
	}
	
	
	
}
