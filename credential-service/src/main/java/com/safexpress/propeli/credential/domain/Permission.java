package com.safexpress.propeli.credential.domain;

import java.io.Serializable;

public class Permission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4171695601133587232L;
	private long permissionId;
	private String name;

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

}
