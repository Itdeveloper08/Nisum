package com.appservices.response;

import java.util.List;

import com.appservices.util.UserInfo;

public class InfoResponse {
	
	private int code;
	private List<String> message;
	private UserInfo userInfo;

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	
	
	
	
	

}
