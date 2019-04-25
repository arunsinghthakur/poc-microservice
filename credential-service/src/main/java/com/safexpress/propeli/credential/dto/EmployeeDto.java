package com.safexpress.propeli.credential.dto;

import java.io.Serializable;
import java.util.Set;

public class EmployeeDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2943278939534238661L;
	private long employeeId;
	private String username;
	private String emailAddress;
	private String password;
	private Set<RoleDto> roles;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
