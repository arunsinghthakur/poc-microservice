package com.safexpress.propeli.credential.dto;

import java.io.Serializable;

public class ResourcePermissionDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6335667279449610378L;

	private long resourcePermissionId;
	private long resourceId;
	private long roleId;
	private long permissionId;

	public long getResourcePermissionId() {
		return resourcePermissionId;
	}

	public void setResourcePermissionId(long resourcePermissionId) {
		this.resourcePermissionId = resourcePermissionId;
	}

	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

}
