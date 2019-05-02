package com.safexpress.propeli.security.service;

import com.safexpress.propeli.security.domain.Employee;
import com.safexpress.propeli.security.domain.Credential;

public interface CredentialService {
	boolean isAuthenticated(Employee employee);

	String login(Credential credential);

	void logout(String token);
}
