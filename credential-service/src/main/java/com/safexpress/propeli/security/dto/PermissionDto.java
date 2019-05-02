package com.safexpress.propeli.security.dto;

import java.io.Serializable;
import java.util.Set;

public class PermissionDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4171695601133587232L;
	private long permissionId;
	private String name;
	private Set<RoleDto> roles;

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

}
