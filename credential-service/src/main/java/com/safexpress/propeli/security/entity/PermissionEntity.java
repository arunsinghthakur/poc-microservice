package com.safexpress.propeli.security.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

	@ManyToMany(mappedBy = "permissions")
	private Set<RoleEntity> roles = new HashSet<RoleEntity>();

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

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

}
