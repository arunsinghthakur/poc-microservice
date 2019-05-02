package com.safexpress.propeli.security.domain;

import java.io.Serializable;

public class CustomClaims implements Serializable {

	private static final long serialVersionUID = 5439321476544159380L;

	private String jsessionid;

	public CustomClaims(String jsessionid) {
		this.jsessionid = jsessionid;
	}

	public CustomClaims() {
		super();
	}

	public String getJsessionid() {
		return jsessionid;
	}

	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

}
