package com.safexpress.propeli.credential.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class PermissionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4171695601133587232L;

	@Id
	@Column(name = "permission_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long permissionId;

	@Column(name = "permission_name")
	private String permissionName;

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

}
