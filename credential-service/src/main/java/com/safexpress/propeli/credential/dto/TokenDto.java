package com.safexpress.propeli.credential.dto;

import java.io.Serializable;

public class TokenDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5439321476544159380L;
	private String accessToken;
	private String refreshToken;
	private long keepAlive;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public long getKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(long keepAlive) {
		this.keepAlive = keepAlive;
	}

}
