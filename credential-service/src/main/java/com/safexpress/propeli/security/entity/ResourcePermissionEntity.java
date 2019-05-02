package com.safexpress.propeli.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resource_permission")
public class ResourcePermissionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6335667279449610378L;

	@Id
	@Column(name = "resource_permission_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long resourcePermissionId;

	@Column(name = "resource_id")
	private long resourceId;

	@Column(name = "role_id")
	private long roleId;

	@Column(name = "permission_id")
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
