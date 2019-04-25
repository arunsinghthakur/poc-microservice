package com.safexpress.propeli.credential.service;

import com.safexpress.propeli.credential.domain.Employee;

public interface CredentialService {
	boolean isAuthenticated(Employee employee);

}
