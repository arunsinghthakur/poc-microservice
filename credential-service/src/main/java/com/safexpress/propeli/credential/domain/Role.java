package com.safexpress.propeli.credential.domain;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8682742874854129114L;
	private long roleId;
	private String roleName;
	private Set<User> users;

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
