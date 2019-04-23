package com.safexpress.propeli.credential.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resource")
public class ResourceEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9001763468610522337L;
	@Id
	@Column(name = "resource_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long resourceId;

	@Column(name = "resource_name")
	private String resourceName;

	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
