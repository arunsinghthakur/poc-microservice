package com.safexpress.propeli.credential.dto;

import java.io.Serializable;
import java.util.Set;

public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2943278939534238661L;
	private long userId;
	private String emailAddress;
	private String password;
	private Set<RoleDto> roles;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

}
