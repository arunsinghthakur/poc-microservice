package com.safexpress.propeli.security.dto;

import java.io.Serializable;
import java.util.Set;

public class RoleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8682742874854129114L;
	private long roleId;
	private String roleName;
	private Set<EmployeeDto> users;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<EmployeeDto> getUsers() {
		return users;
	}

	public void setUsers(Set<EmployeeDto> users) {
		this.users = users;
	}

}
