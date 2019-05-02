package com.safexpress.propeli.security.dto;

import java.io.Serializable;

public class ResourceDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9001763468610522337L;
	private long resourceId;
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
