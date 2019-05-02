package com.safexpress.propeli.security.domain;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CustomUsernamePasswordToken extends UsernamePasswordToken {
	private static final long serialVersionUID = -6117264480706197613L;
	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public CustomUsernamePasswordToken(String username, String password, boolean rememberMe, String userType) {
		super(username, password, rememberMe);
		this.userType = userType;
	}

	public CustomUsernamePasswordToken() {
		super();
	}
}
