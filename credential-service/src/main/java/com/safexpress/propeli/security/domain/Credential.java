package com.safexpress.propeli.security.domain;

import java.io.Serializable;

public class Credential implements Serializable {
	private static final long serialVersionUID = -2908910470966290666L;
	private String username;
	private String password;
	private boolean rememberMe = false;
	private String userType;

	public Credential() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "EmployeeCredential [username=" + username + ", rememberMe=" + rememberMe + ", userType=" + userType
				+ "]";
	}

}
