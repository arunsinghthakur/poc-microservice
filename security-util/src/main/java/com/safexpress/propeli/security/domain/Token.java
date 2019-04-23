package com.safexpress.propeli.security.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Token implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5439321476544159380L;
	private long userId;
	private Map<String, Set<String>> permissions = new HashMap<String, Set<String>>();

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Map<String, Set<String>> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, Set<String>> permissions) {
		this.permissions = permissions;
	}

}
