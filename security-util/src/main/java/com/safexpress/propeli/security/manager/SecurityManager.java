package com.safexpress.propeli.security.manager;

public interface SecurityManager {

	String generateToken(String username, String password);

	void persistToken(String token);

	boolean isValidToken(String token);

	boolean isAuthencated(String token);

	void invalidateToken(String token);

}
