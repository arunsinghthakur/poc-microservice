package com.safexpress.propeli.security.dto;

import java.io.Serializable;

public class TokenDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5324794093142868374L;
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public TokenDto(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public TokenDto() {
		super();
	}

}
